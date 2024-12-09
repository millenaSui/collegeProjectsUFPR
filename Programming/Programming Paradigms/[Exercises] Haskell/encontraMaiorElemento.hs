encontraMaiorElemento :: [Int] -> Int
encontraMaiorElemento [] = -1
encontraMaiorElemento (primeiro:resto)
  | primeiro > (encontraMaiorElemento resto) = primeiro
  | otherwise = encontraMaiorElemento resto

main :: IO ()
main = do
  inputLista <- getLine
  let lista = map read (words inputLista) :: [Int]
  let maiorElemento = encontraMaiorElemento lista
  putStrLn(show maiorElemento)
