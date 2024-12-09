verificaComuns :: [Char] -> [Char] -> Bool
verificaComuns [] _ = False
verificaComuns _ [] = False
verificaComuns (cabecaA:restoA) listaB = verificaComunsAux listaB cabecaA || verificaComuns restoA listaB

verificaComunsAux :: [Char] -> Char -> Bool
verificaComunsAux [] _ = False
verificaComunsAux (cabecaB:restoB) elemento
  | cabecaB == elemento = True
  | otherwise = verificaComunsAux restoB elemento

main :: IO ()
main = do
  listaA <- getLine
  listaB <- getLine
  let temComuns = verificaComuns listaA listaB
  putStrLn(show temComuns)
