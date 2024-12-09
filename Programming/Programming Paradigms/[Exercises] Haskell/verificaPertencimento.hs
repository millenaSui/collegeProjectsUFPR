verificaPertencimento :: [Int] -> Int -> String
verificaPertencimento [] _ = "Nao"
verificaPertencimento (primeiro:resto) elemento
  | primeiro == elemento = "Sim"
  | otherwise = verificaPertencimento resto elemento

main :: IO ()
main = do
  inputElemento <- getLine
  let elemento = read inputElemento :: Int
  inputLista <- getLine
  let lista = map read (words inputLista) :: [Int]
  let pertence = verificaPertencimento lista elemento
  putStrLn (show pertence)
