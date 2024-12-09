somaElementos :: [Int] -> Int
somaElementos [] = 0
somaElementos (primeiro:resto) = primeiro + somaElementos resto

main :: IO ()
main = do
  input <- getLine
  let listaElementos = map read (words input) :: [Int]
  let soma = somaElementos listaElementos
  putStrLn (show soma)
