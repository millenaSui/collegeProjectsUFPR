-- Define os tipos para Cadastro e Agenda
type Cadastro = (String, String) -- Um cadastro é uma tupla com (nome, número)
type Agenda = [Cadastro]         -- Uma agenda é uma lista de cadastros

montaAgenda :: Cadastro -> Agenda -> Agenda
montaAgenda novoCadastro agenda = novoCadastro : agenda

main :: IO ()
main = do
  let agenda = []
  nome <- getLine
  numero <-getLine
  let novoCadastro = (nome, numero)
  let novaAgenda = montaAgenda novoCadastro agenda
  putStrLn(show novaAgenda)
