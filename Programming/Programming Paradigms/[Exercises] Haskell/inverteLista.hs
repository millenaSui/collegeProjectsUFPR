inverteLista :: [Int] -> [Int]
inverteLista [] = []
inverteLista (cabeca:resto) = inverteLista resto ++ [cabeca]

main :: IO ()
main = do
  input <- getLine
  let lista = map read (words input) :: [Int]
  let listaInvertida = inverteLista lista
  putStrLn(show listaInvertida)
