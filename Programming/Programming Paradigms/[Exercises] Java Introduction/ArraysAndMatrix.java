public class ArraysAndMatrix {    
    /*
    16. Construa um programa em java que leia 10 nomes e a seguir guarde-os 
    em um array e leia um nome e verifique se ele faz parte da lista.
    */
    public static void readAndVerifyNamesInArray(String[] args) {
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
            System.out.println("O nome " + nome + " faz parte da lista.");
        else
            System.out.println("O nome " + nome + " não faz parte da lista.");
        System.out.println();
    }

    /*
    12. Fazer um programa em java que leia as 4 notas de 30 alunos da 
    turma e escreva a maior nota de cada aluno e a maior nota da turma.
    */ 
    public static void calculatePrintHighestGrade(String[] args) {
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
        System.out.println("A maior nota da turma é " + maiorNotaTurma);
        System.out.println();
    }

    /*
    17. Contrua um programa em java que some duas matrizes 
    3x3 e imprima as duas matrizes e o resultado.
    */
    public static void sumPrintMatrixValues(String[] args) {
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
        System.out.println("Matriz 1:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(matriz1[i][j] + " ");
            System.out.println();
        }
        System.out.println("Matriz 2:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(matriz2[i][j] + " ");
            System.out.println();
        }
        System.out.println("Matriz Soma:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(matrizSoma[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}