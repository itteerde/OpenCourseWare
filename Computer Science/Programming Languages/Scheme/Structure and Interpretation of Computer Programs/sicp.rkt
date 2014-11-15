#!r6rs
(import (rnrs lists (6))
        (rnrs base (6))
        (rnrs io simple (6))
        )

(define pi 3.14159265359)

(define (circumference_circle radius) (* 2 pi radius))

(define (square x) (* x x))

(define (sqrt-iter guess x)
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x) x)))

(define (improve guess x)
  (average guess(/ x guess)))

(define (good-enough? guess x)
  (< (abs (- (square guess) x)) 0.0000001))

(define (average x y)
  (/ (+ x y) 2))

(define (factorial n)
  (define (iter product counter)
    (if (> counter n)
        product
        (iter (* counter product)
              (+ counter 1))))
  (iter 1 1))

; only call this for realy small x, y. If you are not familar with
; the ackermann function think how many recursive calls of itself
; it will generate. You can call it as (ackermann 2 5) or 
; (ackermann 4 2) but that is about as far as you'll get.
(define (ackermann x y)
  (cond ((= y 0) 0)
        ((= x 0) (* 2 y))
        ((= y 1) 2)
        (else (ackermann (- x 1) (ackermann x (- y 1))))))

(define (fib n)
  (fib-iter 1 0 n))
(define (fib-iter a b count)
  (if (= count 0)
      b
      (fib-iter (+ a b) a (- count 1))))


(define (remainder x y) (mod x y))

(define (smallest-divisor n) (find-divisor n 2))
(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor n (+ test-divisor 1)))))
(define (divides? a b) (= (remainder b a) 0))
(define (prime? n)
  (= n (smallest-divisor n)))

(define (expmod base exp m)
  (cond ((= exp 0) 1)
        ((even? exp)
         (remainder
          (square (expmod base (/ exp 2) m))
          m))
        (else
         (remainder
          (* base (expmod base (- exp 1) m))
          m))))
(define (fermat-test n)
  (define (try-it a)
    (= (expmod a n n) a))
  (try-it (+ 1 (random (- n 1)))))
(define (fast-prime? n times)
  (cond ((= times 0) #t)
        ((fermat-test n) (fast-prime? n (- times 1)))
        (else #f)))
; find a random number generator or write one
(define (random n) 42)

(define (sum term a next b)
  (if (> a b)
      0
      (+ (term a)
         (sum term (next a) next b))))
(define (integral f a b dx)
  (* (sum f
          (+ a (/ dx 2.0))
          (lambda (x) (+ x dx))
          b)
     dx))
  
(define (inc n) (+ n 1))
(define (sum-cubes a b)
  (sum cube a inc b))
(define (cube x) (* x x x))

;p 89
(define (search f neg-point pos-point)
  (let ((midpoint (average neg-point pos-point)))
    (if (close-enough? neg-point pos-point)
        midpoint
        (let ((test-value (f midpoint)))
          (cond ((positive? test-value)
                 (search f neg-point midpoint))
                ((negative? test-value)
                 (search f midpoint pos-point))
                (else midpoint))))))
(define (close-enough? x y) (< (abs (- x y)) 0.000001))
(define (half-interval-method f a b)
        (let ((a-value (f a))
              (b-value (f b)))
          (cond ((and (negative? a-value) (positive? b-value))
                 (search f a b))
                ((and (negative? b-value) (positive? a-value))
                 (search f b a))
                (else
                 (error "Values are not of opposite sign" a b)))))

; p. 92
(define tolerance 0.00001)
(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2))
       tolerance))
  (define (try guess)
    (let ((next (f guess)))
      (if (close-enough? guess next)
          next
          (try next))))
  (try first-guess))

; p. 99. Newton's method
(define (deriv g)
  (lambda (x) (/ (- (g (+ x dx)) (g x)) dx)))
(define dx 0.000001)

(define (newton-transform g)
  (lambda (x) (- x (/ (g x) ((deriv g) x)))))
(define (newtons-method g guess)
  (fixed-point (newton-transform g) guess))

; p. 101
(define (fixed-point-of-transform g transform guess)
  (fixed-point (transform g) guess))

; p. 114 with the power of wishful thinking
(define (add-rat x y)
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (sub-rat x y)
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (mul-rat x y)
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))
(define (div-rat x y)
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))
(define (equal-rat? x y)
  (= (* (numer x) (denom y))
     (* (numer y) (denom y))))
(define (make-rat n d)
  (let ((g (gcd n d)))
    (cons (/ n g) (/ d g))))
(define (numer x) (car x))
(define (denom x) (cdr x))
(define (print-rat x)
  (newline)
  (display (numer x))
  (display "/")
  (display (denom x)))

(define nil '())

; p. 158
(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

; p. 159
(define  (enumerate-tree tree)
  (cond ((null? tree) nil)
        ((not (pair? tree)) (list tree))
         (else (append (enumerate-tree (car tree))
                       (enumerate-tree (cdr tree))))))

; p. 167
(define (flatmap proc seq)
  (accumulate append '() (map proc seq)))
(define (permutations s)
  (if (null? s)
      (list nil)
      (flatmap (lambda (x)
                 (map (lambda (p) (cons x p))
                      (permutations (remove x s))))
               s)))

; p. 171 maximum queen puzzle
;(define (queen board-size)
;  (define (queen-cols k)
;    (if (= k 0)
;        (list empty-board)
;        (filter
;         (lambda (positions) (safe? k positions))
;         (flatmap
;          (lambda (rest-of-queens)
;            (map (lambda (new-row)
;                   (adjoin-position
;                    new-row k rest-of-queens))
;                 (enumerate-interval 1 board-size)))
;          (queen-cols (- k 1))))))
;  (queen-cols board size))

; p. 199
(define (derivate exp var)
  (cond ((number? exp) 0)
        ((variable? exp) (if (same-variable? exp var) 1 0))
        ((sum? exp) (make-sum (derivate (addend exp) var)
                              (derivate (augend exp) var)))
        ((product? exp)
         (make-sum
          (make-product (multiplier exp)
                        (derivate (multiplicand exp) var))
          (make-product (derivate (multiplier exp) var)
                        (multiplicand exp))))
        (else
         (error "unknown expression type: DERIVATE" exp))))

(define (variable? x) (symbol? x))
(define (same-variable? v1 v2)
  (and (variable? v1) (variable? v2) (eq? v1 v2)))
(define (make-sum a1 a2)
  (cond ((=number? a1 0) a2)
        ((=number? a2 0) a1)
        ((and (number? a1) (number? a2))
         (+ a1 a2))
        (else (list '+ a1 a2))))
(define (make-product m1 m2)
  (cond ((or (=number? m1 0) (=number? m2 0)) 0)
        ((=number? m1 1) m2)
        ((=number? m2 1) m1)
        ((and (number? m1) (number? m2)) (* m1 m2))
        (else (list '* m1 m2))))
(define (sum? x) (and (pair? x) (eq? (car x) '+)))
(define (addend s) (cadr s))
(define (augend s) (caddr s))
(define (product? x) (and (pair? x) (eq? (car x) '*)))
(define (multiplier p) (cadr p))
(define (multiplicand p) (caddr p))
(define (=number? exp num) (and (number? exp) (= exp num)))

; p. 206
(define (element-of-set? x set)
  (cond ((null? set) #f)
        ((equal? x (car set)) #t)
        (else (element-of-set? x (cdr set)))))
(define (adjoin-set x set)
  (if (element-of-set? x set)
      set
      (cons x set)))
(define (intersection-set set1 set2)
  (cond ((or (null? set1) (null? set2)) '())
        ((element-of-set? (car set1) set2)
         (cons (car set1) (intersection-set (cdr set1) set2)))
        (else (intersection-set (cdr set1) set2))))

; p. 212
(define (entry tree) (car tree))
(define (left-branch tree) (cadr tree))
(define (right-branch tree) (caddr tree))
(define (make-tree entry left right)
  (list entry left right))

(define (element-of-tree? x tree)
  (cond ((null? tree) #f)
        ((= x (entry tree)) #t)
        ((< x (entry tree))
         (element-of-tree? x (left-branch tree)))
        ((> x (entry tree))
         (element-of-tree? x (right-branch tree)))))

(define (adjoin-tree x tree)
  (cond ((null? tree) (make-tree x '() '()))
        ((= x (entry tree)) tree)
        ((< x (entry tree))
         (make-tree (entry tree)
                    (adjoin-tree x (left-branch tree))
                    (right-branch tree)))
        ((> x (entry tree))
         (make-tree (entry tree) (left-branch tree)
                    (adjoin-tree x (right-branch tree))))))

