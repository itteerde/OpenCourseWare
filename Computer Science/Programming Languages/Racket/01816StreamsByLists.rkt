#lang racket

; selftest 2.1, p. 129
; works, but what does it do?
(define a 'a)

(define square (lambda(x) (* x x)))

(define (average x y) (/ (+ x y) 2))

; streams implemented by lists
; p. 185
(define the-empty-stream '())
(define (cons-stream e s) (cons e s))
(define (head s) (car s))
(define (tail s) (cdr s))
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
  (right-reduce (lambda (x r) (cons-stream (f x) r))
                the-empty-stream
                s))
(define (sum-squares n) (sum (map square (interval 1 n))))

(define (append-streams s1 s2)
  (if (empty-stream? s1)
      s2
      (cons-stream (head s1) (append-streams (tail s1) s2))))

(define (singleton-stream e) (cons-stream e the-empty-stream))

(define (filter p s)
  (right-reduce append-streams the-empty-stream
                (map (lambda (x)
                       (if (p x) (singleton-stream x) the-empty-stream))
                     s)))

(define (flatten-streams s)
  (right-reduce append-streams the-empty-stream s))

; rational numbers
; real numbers
; complex numbers
; associative lists
; binary trees with node content
; n-trees with node content
; compute on tree nodes
; compute on tree leafs
; map on lists
; map on lists of lists and atoms
; map on trees (modify nodes)
; map on tree leafs
; filter on trees
; derivate
; integrate
; nxn matrices
; nxm matrices