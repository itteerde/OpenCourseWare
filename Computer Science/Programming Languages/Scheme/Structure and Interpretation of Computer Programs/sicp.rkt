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
