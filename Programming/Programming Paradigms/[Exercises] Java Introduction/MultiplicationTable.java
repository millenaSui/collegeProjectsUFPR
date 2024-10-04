public class MultiplicationTable {
    /*
    1. Fazer um programa em java que 
    calcule e imprima a tabuada de 6.
    */
    public static void calculatePrintForNumberSix(String[] args) {
        int num = 6;
        System.out.println("Tabuada de " + num + ":");
        for (int i = 1; i <= 10; i++)
            System.out.println(num + " x " + i + " = " + num * i);
        System.out.println();
    }

    /*
    8. Fazer um programa em java que leia um número inteiro e 
    calcule e imprima a tabuada deste número de 1 a 10.
    */
    public static void calculatePrintForReadNumber(String[] args) {
        // Lê o número
        int num = Integer.parseInt(System.console().readLine("Digite um número: "));

        // Calcula e imprime a tabuada
        for (int i = 1; i <= 10; i++)
            System.out.println(num + " x " + i + " = " + num * i);
        System.out.println();
    }
}
