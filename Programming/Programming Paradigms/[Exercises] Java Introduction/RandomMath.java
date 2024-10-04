public class RandomMath {
    /*
    13. Fazer um programa em java que execute um sorteio entre 5 
    pessoas usando o método int sorteio = (int)(Math.random()*5) 
    e imprima o nome do vencedor.
    */
    public static void drawPrintWinner(String[] args) {
        // Realiza sorteio
        String[] nomes = {"Ana", "Bia", "Carlos", "Daiane", "Eduardo"};
        int sorteio = (int)(Math.random() * 5);
        
        // Imprime o resultado
        System.out.println("O vencedor é " + nomes[sorteio]);
        System.out.println();
    }
}
