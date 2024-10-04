public class Functions {
    /*
    2. Fazer um programa em java que calcule e 
    imprima as raízes da equação x² + x - 6 = 0.
    */
    public static void calculatePrintRoots(String[] args) {
        // Define os coeficientes
        double a = 1;
        double b = 1;
        double c = -6;

        // Calcula as raízes
        double delta = Math.pow(b, 2) - 4 * a * c;
        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);

        // Imprime as raízes
        System.out.println("Raiz1 = " + x1 + " | Raiz2 = " + x2);
        System.out.println();
    }
    
    /*
    6. Construa um programa em Java que leia um número x, calcule e escreva o valor da função
    f(x), dada por:
        - 0<=x<5, f(x) = x
        - 5<=x<10, f(x) = 2x+1
        - x>=10, f(x) = x-3 
    */
    public static void calculatePrintResult(String[] args) {
        // Lê o número
        int x = Integer.parseInt(System.console().readLine("Digite um número: "));

        // Calcula o valor da função
        int fx;
        if (x < 5)
            fx = x;
        else if (x < 10)
            fx = 2 * x + 1;
        else
            fx = x - 3;

        // Imprime o valor da função
        System.out.println("f(" + x + ") = " + fx);
        System.out.println();
    }
}
