#!r6rs
(import (rnrs lists (6))
        (rnrs base (6))
        (rnrs io simple (6))
        )

(define (fac n)
  (define (fac1 n f)
    (if (= n 0) f (fac1 (- n 1) (* n f))))
  (fac1 n 1))

(define (fib n)
  (define (fib1 a b n)
    (if (= n 0)
        a
        (fib1 (+ a b) a (- n 1))))
  (fib1 0 1 n))

(define atom? (lambda (x)
                (or (number? x)
                    (symbol? x)
                    (string? x)
                    (boolean? x))))

(define (sigma term from next to)
  (if (> from to) 0
      (+ (term from) (sigma term (next from) next to))))

(define (pi-approx a b)
  (sigma (lambda (x) (/ 1 (* x (+ x 2))))
         a
         (lambda (x) (+ x 4))
         b))

(define (square x) (* x x))

(define (sum term a next b)
  (if (> a b)
      0
      (+ (term a)
         (sum term 
              (next a)
              next
              b))))

(define (identity a) a)

(define (insert x l)
  (if (null? l)
      (list x)
      (let ((y (car l))
            (ys (cdr l)))
        (if (<= x y)
            (cons x l)
            (cons y (insert x ys))))))

(define (insert-sort l)
  (if (null? l)
      '()
      (insert (car l)
              (insert-sort (cdr l)))))


              
(define (ableiten f dx)
  (lambda (x)
    (/ (- (f (+ x dx)) (f (- x dx))) (* 2 dx))))

(define (sqrtn x)
    (define (good-enough? guess)
      (< (absolute (- (square guess) x)) .001))
    (define (improve guess)
      (average guess (/ x guess)))
    (define (sqrt-iter guess)
      (if (good-enough? guess)
          guess
          (sqrt-iter (improve guess))))
    (sqrt-iter 1))

(define (average x y) (/ (+ x y) 2))

(define (absolute x) (if (< x 0) (- x) x))

(define (integrate f a b n)
  (define h (/ (- b a) n))
  (define (fsum i)
    (if (>= i n) 0
        (+ (fsum (+ i 1)) (f (+ a (* i h))))))
  (* h (+ (average (f a) (f b)) (fsum 1))))

