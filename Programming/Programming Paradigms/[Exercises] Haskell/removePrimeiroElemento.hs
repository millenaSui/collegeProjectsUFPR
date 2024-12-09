removePrimeiroElemento :: [Int] -> [Int]
removePrimeiroElemento [] = []
removePrimeiroElemento (primeiro:resto) = resto

main :: IO ()
main = do
  input <- getLine
  let lista = map read (words input) :: [Int]
  let listaModificada = removePrimeiroElemento lista
  putStrLn(show listaModificada)
