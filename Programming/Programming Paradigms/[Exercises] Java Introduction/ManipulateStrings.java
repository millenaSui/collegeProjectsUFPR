public class ManipulateStrings {
    /*
    18. Fazer um programa em Java que leia uma frase 
    e conte e escreva quantas palavras existem.
    */
    public static void countPrintNumberOfWords(String[] args) {
        // Lê a frase
        String frase = System.console().readLine("Digite a frase: ");
        
        // Conta as palavras
        int numPalavras = 0;
        for (int i = 0; i < frase.length(); i++)
            if (frase.charAt(i) == ' ')
                numPalavras++;
        numPalavras++;
        
        // Imprime o resultado
        System.out.println("A frase tem " + numPalavras + " palavras.");
        System.out.println();
    }

    /*
    19. Fazer um programa em Java que leia um nome no formato comum, 
    por exemplo “José Silva”, e o imprima no formato “Silva, J.”.
    */
    public static void abbreviateName(String[] args) {
        // Lê o nome
        String nome = System.console().readLine("Digite o nome no formato comum: ");
        
        // Separa o nome
        String[] partes = nome.split(" ");
        
        // Imprime o nome no formato desejado
        System.out.println(partes[1] + ", " + partes[0].charAt(0) + ".");
        System.out.println();
    }

    /*
    20. Construa um método que receba uma String e imprima esta string invertida. 
    */
    public static void printInvertedString(String[] args) {
        // Lê a string
        String str = System.console().readLine("Digite a string: ");
        
        // Imprime a string invertida
        for (int i = str.length() - 1; i >= 0; i--)
            System.out.print(str.charAt(i));
        System.out.println();
    }
}
