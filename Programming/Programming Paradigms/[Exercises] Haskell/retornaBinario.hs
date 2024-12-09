retornaBinario :: Int -> [Int]
retornaBinario 0 = [0]
retornaBinario decimal
  | decimal > 0 = retornaBinario (decimal `div` 2) ++ [decimal `mod` 2]
  | otherwise = error "Número negativo não suportado"

main :: IO ()
main = do
  input <- getLine
  let decimal = read input :: Int
  let binario = retornaBinario decimal
  putStrLn(show binario)
