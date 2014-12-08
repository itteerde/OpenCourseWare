module Euler where

-- If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
-- Find the sum of all the multiples of 3 or 5 below 1000.

sum35 :: Integer -> Integer
sum35 x = sum[n | n <- [1..x-1], n `mod` 5 == 0 || n `mod` 3 == 0]

main = do
	let a = sum35 1000
	print a
