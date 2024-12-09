calculaFatorial :: Int -> Int
calculaFatorial 0 = 1
calculaFatorial a = a * calculaFatorial(a-1)

main :: IO ()
main = do
  input <- getLine
  let a = read input :: Int
  let fatorial = calculaFatorial a
  putStrLn(show fatorial)
