public class NumbersSum {
    /*
    9.Fazer um programa em java que leia números inteiros enquanto não for 
    digitado o número -1, e calcule e imprima a soma destes números.
    */
    public static void calculatePrintForReadNumbers(String[] args) {
        // Lê os números
        int num = 0;
        int soma = 0;
        do {
            soma += num;
            num = Integer.parseInt(System.console().readLine("Digite um número: "));
        } while (num != -1);

        // Imprime a soma
        System.out.println("A soma dos números é " + soma);
        System.out.println();
    }

    /*
    11. Fazer um programa em java que calcule e 
    imprima a soma dos 10 primeiros múltiplos de 3.
    */
    public static void calculatePrintForMultipleOfThree(String[] args) {
        int soma = 0;
        for (int i = 1; i <= 10; i++)
            soma += 3 * i;
        System.out.println("A soma dos 10 primeiros múltiplos de 3 é " + soma);
        System.out.println();
    }
}
