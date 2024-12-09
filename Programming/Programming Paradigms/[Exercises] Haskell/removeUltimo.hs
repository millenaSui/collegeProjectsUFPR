removeUltimo :: [Int] -> [Int]
removeUltimo [] = []
removeUltimo [_] = []
removeUltimo (cabeca:resto) = cabeca : removeUltimo resto

main :: IO ()
main = do
  input <- getLine
  let lista = map read (words input) :: [Int]
  let listaSemUltimo = removeUltimo lista
  putStrLn(show listaSemUltimo)
