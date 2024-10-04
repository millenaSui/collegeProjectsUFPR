public class NumericalAverage {
    /*
    14. Fazer um programa em Java que leia 10 valores de idades de 
    pessoas, calcule e imprima a média e a seguir imprima quais 
    idades estão acima e quas estão abaixo da media.
    */
    public static void calculatePrintPeopleReadAge(String[] args) {
        // Lê os valores
        int[] idades = new int[10];
        int soma = 0;
        for (int i = 0; i < 10; i++) {
            idades[i] = Integer.parseInt(System.console().readLine("Digite a idade da pessoa " + (i + 1) + ": "));
            soma += idades[i];
        }
        
        // Calcula a média
        double media = (double)soma / 10;
        
        // Imprime os resultados
        System.out.println("A média das idades é " + media);
        System.out.println("As idades acima da média são:");
        for (int i = 0; i < 10; i++)
            if (idades[i] > media)
                System.out.println(idades[i]);
        System.out.println("As idades abaixo da média são:");
        for (int i = 0; i < 10; i++)
            if (idades[i] < media)
                System.out.println(idades[i]);
        System.out.println();
    }
}
