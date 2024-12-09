somaPares :: [Int] -> Int
somaPares [] = 0
somaPares (cabeca:resto)
  | cabeca `mod` 2 == 0 = cabeca + somaPares resto
  | otherwise = 0 + somaPares resto

main :: IO ()
main = do
  input <- getLine
  let lista = map read (words input) :: [Int]
  let soma = somaPares lista
  putStrLn(show soma)
