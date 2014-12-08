fibs :: [Int]
fibs = 1 : 2 : [ a+b | (a ,b) <- zip fibs (tail fibs)]

fibsUpTo :: Int -> [Int]
fibsUpTo n = takeWhile (< n) fibs

limit :: Int
limit = 1000000

main :: IO ()
main = do
    print $ sum $ filter even (fibsUpTo limit)