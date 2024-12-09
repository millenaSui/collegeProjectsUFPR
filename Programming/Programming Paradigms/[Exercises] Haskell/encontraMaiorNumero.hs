encontraMaiorNumero :: Int -> Int -> Int -> Int
encontraMaiorNumero a b c
  | a > b && (a > c || a == c) = a
  | a > c && (a > b || a == b) = a
  | b > a && (b > c || b == c) = b
  | b > c && (b > a || b == a) = b
  | c > b && (c > a || c == a) = c
  | c > a && (c > b || c == b) = c
  | otherwise = 0

main :: IO ()
main = do
  inputA <- getLine
  let a = read inputA :: Int
  inputB <- getLine
  let b = read inputB :: Int
  inputC <- getLine
  let c = read inputC :: Int
  let maiorNumero = encontraMaiorNumero a b c
  putStrLn(show maiorNumero)
