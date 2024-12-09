encontraValorMedio :: Int -> Int -> Int -> Int
encontraValorMedio a b c = (a + b + c) `div` 3

encontraQuantosNumerosAcima :: Int -> Int -> Int -> Int -> Int
encontraQuantosNumerosAcima a b c valorMedio
  | a > valorMedio && b > valorMedio && c > valorMedio = 3
  | (a > valorMedio && b > valorMedio) || (a > valorMedio && c > valorMedio) || (b > valorMedio && c > valorMedio) = 2
  | a > valorMedio || b > valorMedio || c > valorMedio = 1
  | otherwise = 0

main :: IO ()
main = do
  inputA <- getLine
  let a = read inputA :: Int
  inputB <- getLine
  let b = read inputB :: Int
  inputC <- getLine
  let c = read inputC :: Int
  let valorMedio = encontraValorMedio a b c
  let quantosNumerosAcima = encontraQuantosNumerosAcima a b c valorMedio
  putStrLn(show quantosNumerosAcima)
