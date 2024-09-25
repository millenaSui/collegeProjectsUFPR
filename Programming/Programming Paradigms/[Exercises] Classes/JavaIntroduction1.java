import java.util.Scanner;

public class JavaIntroduction1 {
    
    /*1. Fazer um programa em java que calcule e imprima a tabuada de 6.*/
    public static void CalculaImprimeTabuadaDeSeis(String[] args) {
        int num = 6;
        System.out.println("#1. Tabuada de " + num + ":");
        for (int i = 1; i <= 10; i++)
            System.out.println(num + " x " + i + " = " + num * i);
        System.out.println();
    }
    
    /*2. Fazer um programa em java que calcule e imprima as raízes da equação x² + x - 6 = 0.*/
    public static void CalculaImprimeRaizes(String[] args) {
        // Define os coeficientes
        double a = 1;
        double b = 1;
        double c = -6;

        // Calcula as raízes
        double delta = Math.pow(b, 2) - 4 * a * c;
        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);

        // Imprime as raízes
        System.out.println("#2. Raiz1 = " + x1 + " | Raiz2 = " + x2);
        System.out.println();
    }

    /*3. Fazer um programa em java que mostre o seu nome completo, o nome 
    do time de futebol de sua preferência e o bairro onde você mora.*/
    public static void ImprimeInformacoesPessoais(String[] args) {
        // Lê as informações
        String nome, time, bairro;
        nome = System.console().readLine("Digite seu nome: ");
        time = System.console().readLine("Digite o time de futebol de sua preferência: ");
        bairro = System.console().readLine("Digite o bairro onde você mora: ");
    
        // Imprime as informações
        System.out.println("#3. Nome: " + nome + " | " + "Time: " + time + " | " + "Bairro: " + bairro);
        System.out.println();
    }

    /*4. Construa um programa em Java que leia um número e diga se ele é positivo.*/
    public static void VerificaNumeroPositivo(String[] args) {
        // Lê o número
        int num = Integer.parseInt(System.console().readLine("Digite um número: "));

        // Verifica se o número é positivo
        if (num > 0)
            System.out.println("#4. O número " + num + " é positivo.");
        else
            System.out.println("#4. O número " + num + " não é positivo.");
        System.out.println();
    }

    /*5. Construa um programa em Java que leia um numero inteiro e diga se ele é par ou ímpar.*/
    public static void VerificaNumeroParImpar(String[] args) {
        // Lê o número
        int num = Integer.parseInt(System.console().readLine("Digite um número: "));

        // Verifica se o número é par ou ímpar
        if (num % 2 == 0)
            System.out.println("#5. O número " + num + " é par.");
        else
            System.out.println("#5. O número " + num + " é ímpar.");
        System.out.println();
    }

    /*6. Construa um programa em Java que leia um número x, calcule e escreva o valor da função
    f(x), dada por:
        - 0<=x<5, f(x) = x
        - 5<=x<10, f(x) = 2x+1
        - x>=10, f(x) = x-3 */
    public static void CalculaFuncao(String[] args) {
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
        System.out.println("#6. f(" + x + ") = " + fx);
        System.out.println();
    }

    /*7. Construa um programa em Java que leia três números e diga se eles 
    podem ser os lados de um triângulo isósceles, equilátero ou escaleno.*/
    public static void VerificaTriangulo(String[] args) {
        // Lê os números
        int a = Integer.parseInt(System.console().readLine("Digite o primeiro número: "));
        int b = Integer.parseInt(System.console().readLine("Digite o segundo número: "));
        int c = Integer.parseInt(System.console().readLine("Digite o terceiro número: "));

        // Verifica se os números formam um triângulo
        if (a + b > c && a + c > b && b + c > a) {
            if (a == b && b == c)
                System.out.println("#7. Os números formam um triângulo equilátero.");
            else if (a == b || a == c || b == c)
                System.out.println("#7. Os números formam um triângulo isósceles.");
            else
                System.out.println("#7. Os números formam um triângulo escaleno.");
        } else
            System.out.println("#7. Os números não formam um triângulo.");
        System.out.println();
    }

    /*8. Fazer um programa em java que leia um número inteiro e 
    calcule e imprima a tabuada deste número de 1 a 10.*/
    public static void CalculaImprimeTabuada(String[] args) {
        // Lê o número
        int num = Integer.parseInt(System.console().readLine("Digite um número: "));

        // Calcula e imprime a tabuada
        for (int i = 1; i <= 10; i++)
            System.out.println("#8. " + num + " x " + i + " = " + num * i);
        System.out.println();
    }

    /*9.Fazer um programa em java que leia números inteiros enquanto não for 
    digitado o número -1, e calcule e imprima a soma destes números.*/
    public static void CalculaImprimeSoma(String[] args) {
        // Lê os números
        int num = 0;
        int soma = 0;
        do {
            soma += num;
            num = Integer.parseInt(System.console().readLine("Digite um número: "));
        } while (num != -1);

        // Imprime a soma
        System.out.println("#9. A soma dos números é " + soma);
        System.out.println();
    }

    /*10. Fazer um programa que leia os valores do peso e da altura de pessoas, enquanto não 
    for digitado o número -1, conte e escreva quantas pessoas estão acima do peso. A condição
    (peso /(altura*altura)) <= 25 diz que a pessoa está no peso normal.*/
    public static void VerificaPeso(String[] args) {
        // Lê os valores
        int acimaDoPeso = 0;
        double peso = 0;
        double altura = 0;
        do {
            if (peso / (altura * altura) > 25)
                acimaDoPeso++;
            peso = Double.parseDouble(System.console().readLine("Digite o peso: "));
            altura = Double.parseDouble(System.console().readLine("Digite a altura: "));    
        } while (peso != -1 || altura != -1);

        // Imprime o resultado
        System.out.println("#10. " + acimaDoPeso + " pessoas estão acima do peso.");
        System.out.println();
    }

    /*11. Fazer um programa em java que calcule e imprima a soma dos 10 primeiros múltiplos de 3.*/
    public static void CalculaImprimeSomaMultiplosDeTres(String[] args) {
        int soma = 0;
        for (int i = 1; i <= 10; i++)
            soma += 3 * i;
        System.out.println("#11. A soma dos 10 primeiros múltiplos de 3 é " + soma);
        System.out.println();
    }

    /*12. Fazer um programa em java que leia as 4 notas de 30 alunos da 
    turma e escreva a maior nota de cada aluno e a maior nota da turma.*/ 
    public static void CalculaImprimeMaiorNota(String[] args) {
        // Lê as notas
        int[][] notas = new int[30][4];
        for (int i = 0; i < 30; i++) {
            String[] entrada = System.console().readLine("Digite as 4 notas do aluno " + (i + 1) + ": ").split(" ");
            for (int j = 0; j < 4; j++)
                notas[i][j] = Integer.parseInt(entrada[j]);
        }

        // Calcula e imprime a maior nota de cada aluno
        for (int i = 0; i < 30; i++) {
            int maiorNota = 0;
            for (int j = 0; j < 4; j++)
                if (notas[i][j] > maiorNota)
                    maiorNota = notas[i][j];
            System.out.println("A maior nota do aluno " + (i + 1) + " é " + maiorNota);
        }

        // Calcula e imprime a maior nota da turma
        int maiorNotaTurma = 0;
        for (int i = 0; i < 30; i++)
            for (int j = 0; j < 4; j++)
                if (notas[i][j] > maiorNotaTurma)
                    maiorNotaTurma = notas[i][j];
        System.out.println("#12. A maior nota da turma é " + maiorNotaTurma);
        System.out.println();
    }

    public static void main(String[] args) {
        CalculaImprimeTabuadaDeSeis(args);
        CalculaImprimeRaizes(args);
        ImprimeInformacoesPessoais(args);
        VerificaNumeroPositivo(args);
        VerificaNumeroParImpar(args);
        CalculaFuncao(args);
        VerificaTriangulo(args);
        CalculaImprimeTabuada(args);
        CalculaImprimeSoma(args);
        VerificaPeso(args);
        CalculaImprimeSomaMultiplosDeTres(args);
        CalculaImprimeMaiorNota(args);
    }
}