module FirstScript where

size :: Integer
size = 12+13

square :: Integer -> Integer
square n = n * n

double :: Integer -> Integer
double n = 2 * n

example :: Integer
example = double ( size - square ( 2 + 2 ))

squaredouble :: Integer -> Integer
squaredouble n = square ( double ( n))

doublesquare :: Integer -> Integer
doublesquare n = double ( square (n))