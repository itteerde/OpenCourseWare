#lang racket ;works with scheme, maybee a library function missing to be imported but if so they are in the scheme libraries

(define square (lambda(x) (* x x)))
(define (average x y) (/ (+ x y) 2))

; lazy evaluation
(define-syntax delay
  (syntax-rules ()
    ((delay expr)
     (lambda ()
       expr))))
(define (force promise) (promise))

; streams
(define the-empty-stream '())
(define (head s) (car s))
(define (tail s) (force (cdr s)))
(define (empty-stream? s) (null? s))

(define-syntax cons-stream
  (syntax-rules ()
    ((cons-stream x y)
     (cons x (delay y)))))

(define (right-reduce combine initial s)
  (if (empty-stream? s)
      initial
      (combine (head s)
               (right-reduce combine initial (tail s)))))

(define (sum s) (right-reduce + 0 s))
(define (product s) (right-reduce * 1 s))
(define (max-element s) (right-reduce max 0 s))

(define (accumulates s f n)
  (cond ((empty-stream? s) n)
        (else (f (head s) (accumulates (tail s) f n)))))

(define (interval a b)
  (if (> a b)
      the-empty-stream
      (cons-stream a (interval (+ 1 a) b))))
(define (fac n) (product (interval 1 n)))

(define (map f s)
  (if (empty-stream? s)
      the-empty-stream
      (cons-stream (f (head s)) (map f (tail s)))))
(define (sum-squares n) (sum (map square (interval 1 n))))

(define (append-streams s1 s2)
  (if (empty-stream? s1)
      s2
      (cons-stream (head s1) (append-streams (tail s1) s2))))

(define (singleton-stream e) 
  (cons-stream e the-empty-stream))

(define (const-stream x) 
  (cons-stream x (const-stream x)))

(define (filter p s)
  (if (empty-stream? s)
      the-empty-stream
      (let ((x (head s)))
        (if (p x)
            (cons-stream x (filter p (tail s)))
            (filter p (tail s))))))


(define (integers-from a)
  (cons-stream a (integers-from (+ 1 a))))

(define (add-streams s1 s2)
  (cond ((empty-stream? s1) s2)
        ((empty-stream? s2) s1)
        (else (cons-stream (+ (head s1) (head s2))
                           (add-streams (tail s1) (tail s2))))))

; just for showing that (speaking generally) tree recursion is not a good idea
(define fibs
  (cons-stream 0
               (cons-stream 1
                            (add-streams fibs (tail fibs)))))

(define (nth n s)
  (cond ((empty-stream? s) (error "Strom erschoepft!"))
        ((= n 0) (head s))
        (else (nth (- n 1) (tail s)))))

(define (divisible? x y) (= 0 (remainder x y)))
(define (sieve n)
  (cons-stream n (filter
                  (lambda (x) (not (divisible? x n)))
                  (sieve (+ 1 n)))))
(define primes (cons-stream 2 (filter prime?
                                      (integers-from 3))))
(define (prime? n)
  (define (iter ps)
    (cond ((> (square (head ps)) n) #t)
          ((divisible? n (head ps)) #f)
          (else (iter (tail ps)))))
  (iter primes))

(define (fib n) (nth n (fib-stream)))

(define fib-stream 
  ((lambda ()
     (define (fibgen a b)
       (cons-stream a
                    (fibgen (+ a b) a)))
     (cons-stream 0
                  (fibgen 1 0)))))

(define (even? x)
  (if (= (remainder x 2) 0)
      #t
      #f))

(define (has-even-fib) 
  (map
   (lambda (s) (head s))
     (filter 
      (lambda (x) (even? (head (cdr x)))) 
      (fib-stream))))

; some rational number stuff
(define (ggT n d)
  (cond
    ((= n d) n)
    ((= n 0) d)
    ((= d 0) n)
    ((< n d) (ggT n (- d n)))
    ((< d n) (ggT d (- n d)))))

(define (canceled? r)
  (if (= (ggT (car r) (cdr r)) 1)
      #t
      #f))

(define (cancel r)
  (let (
        (t (ggT (car r) (cdr r)))
        )
  (cons (/ (car r) t) (/ (cdr r) t))))

(define (nextr r)
  (cond
    ((< (+ (car r) 1) (cdr r)) (cons (+ (car r) 1) (cdr r)))
    (else (cons 1 (+ (cdr r) 1)))))
  
(define (brueche-stream)
  (define (nextb n d)
    (cons-stream (cons n d)
                 (nextb 
                  (cond
                    ((< (+ n 1) d) (+ n 1))
                    (else 1))
                  (cond
                    ((< (+ n 1) d) d)
                    (else (+ d 1))))))
    (cons-stream (cons 1 2)
                 (nextb 1 2)))

(define (brueche)
  (filter 
   (lambda (x) (canceled? x))
   (brueche-stream)))
        
(define (drops n s)
  (if (<= n 0)
      s
      (drops (- n 1) (tail s))))

(define (maps f s)
  (if (empty-stream? s)
      the-empty-stream
      (cons-stream (f (head s)) (maps f (tail s)))))

(define pi 3.14159265359)
(define (circumference_circle radius) (* 2 pi radius))

(define (sqrt-iter guess x)
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x) x)))

(define (improve guess x)
  (average guess(/ x guess)))

(define (good-enough? guess x)
  (< (abs (- (square guess) x)) 0.0000001))

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


(define (smallest-divisor n) (find-divisor n 2))
(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor n (+ test-divisor 1)))))
(define (divides? a b) (= (remainder b a) 0))

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

(define (call-n-times f n)
  (if (= 0 n)
      '()
      (cons (f) (call-n-times f (- n 1)))))

(define (compose f g) (lambda (x) (f (g x))))
(define (compose-n f n)
  (cond ((= 1 n) f)
        ((> n 1) (compose f (compose-n f (- n 1))))))

(define (takel n l)
  (cond
    ((= n 0) '())
    ((> n 0) (cons (car l) (takel (- n 1) (cdr l))))))

(define (takes n s)
  (cond 
    ((= n 0) the-empty-stream)
    ((> n 0) (cons-stream (head s) (takes (- n 1) (tail s))))))


(define (selectl l p)
  (cond 
    ((null? l) '())
    ((p (car l)) (append (list (car l)) (selectl (cdr l) p)))
    (else (selectl (cdr l) p))))

(define (list_product l)
  (cond ((= 0 (length l)) 0)
    	   ((= 1 (length l)) (car l))
    	  (else (* (car l) (list_product (cdr l))))))

(define (accumulatel l f)
  (cond ((= 0 (length l)) 0)
        ((= 1 (length l)) (car l))
        (else (f (car l) (accumulatel (cdr l) f)))))


(define (reverse l) 
  (if 
   (null? l) 
   l 
   (append (reverse (cdr l)) (list (car l)
                                   ))))

(define (lists n s)
  (cond ((= n 0) '())
        ((empty-stream? s) '())
        (else (cons (head s) (lists (- n 1) (tail s))))))