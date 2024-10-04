public class ReadAndStorePeopleData {

    public static Pessoa[] readPeopleData(String[] args) {
        Leitor leitor = new Leitor();
        Pessoa[] pessoas = new Pessoa[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Digite o nome da pessoa " + (i + 1) + ":");
            String nome = leitor.leString();
            System.out.println("Digite o endereço da pessoa " + (i + 1) + ":");
            String endereco = leitor.leString();
            System.out.println("Digite o telefone da pessoa " + (i + 1) + ":");
            String telefone = leitor.leString();
            System.out.println("Digite o email da pessoa " + (i + 1) + ":");
            String email = leitor.leString();
            pessoas[i] = new Pessoa(nome, email, endereco, telefone);
        }

        return pessoas;
    }
    
    /*
    1. Usando as classes Leitor e Pessoa, apresentadas nesta unidade construa um 
    programa que leia os dados de 4 pessoas guarde nos objetos e os imprima.
    */
    public static void printPeopleData(String[] args, Pessoa[] pessoas) {
        for (int i = 0; i < 4; i++) {
            System.out.println("Pessoa " + (i + 1) + ":");
            System.out.println("Nome: " + pessoas[i].getNome());
            System.out.println("Endereço: " + pessoas[i].getEndereco());
            System.out.println("Telefone: " + pessoas[i].getTelefone());
            System.out.println("Email: " + pessoas[i].getEmail());
        }
    }
    
    /*
    2. Usando a classe Arquivo e o método public void gravar(String frase, 
    String nomeArquivo), construa um programa que leia os dados de 4 pessoas 
    e grave em um arquivo texto. 
    */
    public static void writePeopleData(String[] args, Pessoa[] pessoas) {
        Arquivo arquivo = new Arquivo();
        for (int i = 0; i < 4; i++) {
            String frase = "Pessoa " + (i + 1) + ":\n" +
                "Nome: " + pessoas[i].getNome() + "\n" +
                "Endereço: " + pessoas[i].getEndereco() + "\n" +
                "Telefone: " + pessoas[i].getTelefone() + "\n" +
                "Email: " + pessoas[i].getEmail() + "\n";
            arquivo.gravar(frase, "pessoas.txt");
        }
    }

    public static void main(String[] args) {
        Pessoa[] pessoas = readPeopleData(args);
        printPeopleData(args, pessoas);
        writePeopleData(args, pessoas);
    }
}
