#!r6rs
(import (rnrs lists (6))
        (rnrs base (6))
        (rnrs io simple (6))
        )

(define pi 3.14159265359)

(define (circumference_circle radius) (* 2 pi radius))

(define (square x) (* x x))
