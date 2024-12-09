retornaElementos :: [Int] -> Int -> [Int]
retornaElementos _ 0 = []
retornaElementos [] _ = []
retornaElementos (primeiro:resto) n = primeiro: retornaElementos resto (n-1)

main :: IO ()
main = do
  inputN <- getLine
  let n = read inputN :: Int
  inputLista <- getLine
  let lista = map read (words inputLista) :: [Int]
  let nElementos = retornaElementos lista n
  putStrLn(show nElementos)
