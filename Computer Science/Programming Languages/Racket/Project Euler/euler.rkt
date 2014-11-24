#!r6rs
(import (rnrs lists (6))
        (rnrs base (6))
        (rnrs io simple (6))
        (rnrs r5rs (6))
        )

; If we list all the natural numbers below 10 that are multiples of 3 or 5, 
; we get 3, 5, 6 and 9. The sum of these multiples is 23.
;
; Find the sum of all multiples of 3 or 5 below 1000.
(define (p1 a b)
  (if 
   (or 
    (= (remainder a 3) 0)
    (= (remainder a 5) 0))
   (+ a (if 
         (< a b)
         (p1 (+ a 1) b)
         0))
   (+ 0 (if 
         (< a b)
         (p1 (+ a 1) b)
         0))))