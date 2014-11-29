#lang racket

; selftest 2.1, p. 129
; works, but what does it do?
(define a 'a)

(define square (lambda(x) (* x x)))

(define (average x y) (/ (+ x y) 2))

; streams implemented by lists
; p. 185
(define the-empty-stream '())
;(define (cons-stream e s) (cons e s))
(define (head s) (car s))
(define (tail s) (force (cdr s)))
(define (empty-stream? s) (null? s))

(define (right-reduce combine initial s)
  (if (empty-stream? s)
      initial
      (combine (head s)
               (right-reduce combine initial (tail s)))))

; call (sum '(m ... n)) such as (sum '(1 2 3))
(define (sum s) (right-reduce + 0 s))
(define (product s) (right-reduce * 1 s))
(define (max-element s) (right-reduce max 0 s))
; ... making the above useful by allowing such as
; (sum (interval 1 100))
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

(define (singleton-stream e) (cons-stream e the-empty-stream))

(define (filter p s)
  (if (empty-stream? s)
      the-empty-stream
      (let ((x (head s)))
        (if (p x)
            (cons-stream x (filter p (tail s)))
            (filter p (tail s))))))

(define (flatten-streams s)
  (right-reduce append-streams the-empty-stream s))

; delayed execution
(define-syntax delay
  (syntax-rules ()
    ((delay expr)
     (lambda ()
       expr))))
(define (force promise) (promise))

(define-syntax cons-stream
  (syntax-rules ()
    ((cons-stream x y)
     (cons x (delay y)))))

(define (integers-from a)
  (cons-stream a (integers-from (+ 1 a))))

(define (add-streams s1 s2)
  (cond ((empty-stream? s1) s2)
        ((empty-stream? s2) s1)
        (else (cons-stream (+ (head s1) (head s2))
                           (add-streams (tail s1) (tail s2))))))

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

