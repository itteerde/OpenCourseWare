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
