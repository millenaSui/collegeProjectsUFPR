/* Criar uma classe CadastrarPessoa que possua um método que
receba um objeto p da classe Pessoa e um array de Pessoas e
coloque p no array */

public class CadastrarPessoa {
    // Method to register a person in an array of people
    public void cadastrar(Pessoa p, Pessoa[] pessoas) {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] == null) {
                pessoas[i] = p;
                break;
            }
        }
    }
}