data ArvoreBinaria = NodoNulo
                   | NodoInt a (ArvoreBinaria a) (ArvoreBinaria a) -- a sendo um valor de tipo genérico
  deriving (Show, Eq)
