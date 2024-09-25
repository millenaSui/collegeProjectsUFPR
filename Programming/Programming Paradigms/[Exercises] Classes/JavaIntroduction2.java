import java.util.Scanner;

public class JavaIntroduction2 {
    
    /*1. Fazer um programa em java que execute um sorteio entre 5 pessoas usando o 
    método int sorteio = (int)(Math.random()*5) e imprima o nome do vencedor.*/
    public static void SorteiaImprimeVencedor(String[] args) {
        // Realiza sorteio
        String[] nomes = {"Ana", "Bia", "Carlos", "Daiane", "Eduardo"};
        int sorteio = (int)(Math.random() * 5);
        
        // Imprime o resultado
        System.out.println("#1. O vencedor é " + nomes[sorteio]);
        System.out.println();
    }

    /*2. Fazer um programa em Java que leia 10 valores de idades de pessoas, calcule e imprima 
    a média e a seguir imprima quais idades estão acima e quas estão abaixo da media.*/
    public static void CalculaImprimeMedia(String[] args) {
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
        System.out.println("#2. A média das idades é " + media);
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

    /*3. Digite e rode o programa de exemplo das Exceções:
    public static void Excessoes (String args[]) {
        int num1,num2;
        try {
            BufferedReader teclado;
            teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println('Digite o numero');
            num1 = Integer.parseInt(teclado.readLine());
            System.out.println('Digite o numero');
            num2 = Integer.parseInt(teclado.readLine());
            System.out.println("Soma = " + (num1+num2));
            System.out.println("Subtracao = " + (num1-num2));
            System.out.println("Multiplicacao = " + (num1*num2));
            System.out.println("Divisao = " + (num1/num2));
        }
        catch (ArithmeticException aex) {
            System.out.println("Erro de divisao por zero!"+aex);
        }
        catch (IOException ioex) {
            System.out.println("Numero de argumentos invalidos!"+ioex);
        }
        catch (NumberFormatException nfex) {
            System.out.println("Digite apenas numeros inteiros!"+nfex);
        }
        finally {
            System.out.println("Fim!");
        }
    }
    e mude as exceções capturadas para testar quando a captura 
    funciona e quando a captura não funciona.*/
    public static void Excessoes(String[] args) {
        int num1, num2;
        try {
            Scanner teclado = new Scanner(System.in);
            System.out.println("Digite o número");
            num1 = teclado.nextInt();
            System.out.println("Digite o número");
            num2 = teclado.nextInt();
            System.out.println("Soma = " + (num1 + num2));
            System.out.println("Subtração = " + (num1 - num2));
            System.out.println("Multiplicação = " + (num1 * num2));
            System.out.println("Divisão = " + (num1 / num2));
        } catch (ArithmeticException aex) {
            System.out.println("#3. Erro de divisão por zero!" + aex);
        } catch (Exception ex) {
            System.out.println("#3. Erro: " + ex);
        } finally {
            System.out.println("#3. Fim!");
        }
        System.out.println();
    }

    /*4.Construa um programa em java que leia 10 nomes e a seguir guarde-os 
    em um array e leia um nome e verifique se ele faz parte da lista.*/
    public static void LeNomesVerifica(String[] args) {
        // Lê os nomes
        String[] nomes = new String[10];
        for (int i = 0; i < 10; i++)
            nomes[i] = System.console().readLine("Digite o nome " + (i + 1) + ": ");
        
        // Lê o nome a ser verificado
        String nome = System.console().readLine("Digite o nome a ser verificado: ");
        
        // Verifica se o nome faz parte da lista
        boolean achou = false;
        for (int i = 0; i < 10; i++)
            if (nomes[i].equals(nome)) {
                achou = true;
                break;
            }
        
        // Imprime o resultado
        if (achou)
            System.out.println("#4. O nome " + nome + " faz parte da lista.");
        else
            System.out.println("#4. O nome " + nome + " não faz parte da lista.");
        System.out.println();
    }

    /*5.Contrua um programa em java que some duas matrizes 3x3 e imprima as duas matrizes e o resultado.*/
    public static void SomaMatrizes(String[] args) {
        // Lê as matrizes
        int[][] matriz1 = new int[3][3];
        int[][] matriz2 = new int[3][3];
        int[][] matrizSoma = new int[3][3];
        System.out.println("Digite a primeira matriz:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                matriz1[i][j] = Integer.parseInt(System.console().readLine("Digite o elemento da linha " + (i + 1) + " e coluna " + (j + 1) + ": "));
        System.out.println("Digite a segunda matriz:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                matriz2[i][j] = Integer.parseInt(System.console().readLine("Digite o elemento da linha " + (i + 1) + " e coluna " + (j + 1) + ": "));
        
        // Soma as matrizes
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                matrizSoma[i][j] = matriz1[i][j] + matriz2[i][j];
        
        // Imprime as matrizes e o resultado
        System.out.println("#5. Matriz 1:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(matriz1[i][j] + " ");
            System.out.println();
        }
        System.out.println("#5. Matriz 2:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(matriz2[i][j] + " ");
            System.out.println();
        }
        System.out.println("#5. Matriz Soma:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(matrizSoma[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    /*6. Fazer um programa em Java que leia uma frase e conte e escreva quantas palavras existem.*/
    public static void ContaPalavras(String[] args) {
        // Lê a frase
        String frase = System.console().readLine("Digite a frase: ");
        
        // Conta as palavras
        int numPalavras = 0;
        for (int i = 0; i < frase.length(); i++)
            if (frase.charAt(i) == ' ')
                numPalavras++;
        numPalavras++;
        
        // Imprime o resultado
        System.out.println("#6. A frase tem " + numPalavras + " palavras.");
        System.out.println();
    }

    /*7. Fazer um programa em Java que leia um nome no formato comum, 
    por exemplo “José Silva”, e o imprima no formato “Silva, J.”.*/
    public static void ImprimeNome(String[] args) {
        // Lê o nome
        String nome = System.console().readLine("Digite o nome no formato comum: ");
        
        // Separa o nome
        String[] partes = nome.split(" ");
        
        // Imprime o nome no formato desejado
        System.out.println("#7. " + partes[1] + ", " + partes[0].charAt(0) + ".");
        System.out.println();
    }

    public static void main(String[] args) {
        SorteiaImprimeVencedor(args);
        CalculaImprimeMedia(args);
        Excessoes(args);
        LeNomesVerifica(args);
        SomaMatrizes(args);
        ContaPalavras(args);
        ImprimeNome(args);
    }
}