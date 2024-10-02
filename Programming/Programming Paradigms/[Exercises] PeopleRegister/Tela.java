/* Criar uma classe Tela que leia os dados de uma pessoa, crie
o objeto p e o insira em um array */

public class Tela {
    public static void main(String[] args) {
        // Create an array of people
        Pessoa[] pessoas = new Pessoa[1];
        // Create a person
        Pessoa p = new Pessoa("Giulia", "giulia.souza9999999999999@gmail.com", "Rua 1", "99999-9999");
        // Register the person in the array
        CadastrarPessoa cadastro = new CadastrarPessoa();
        cadastro.cadastrar(p, pessoas);

        // Print the people in the array
        for (Pessoa pessoa : pessoas) {
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("Endereço: " + pessoa.getEndereco());
            System.out.println("Telefone: " + pessoa.getTelefone());
        }
    }
}