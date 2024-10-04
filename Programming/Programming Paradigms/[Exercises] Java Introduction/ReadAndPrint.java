public class ReadAndPrint {
    /*
    3. Fazer um programa em java que mostre o seu nome completo, o nome 
    do time de futebol de sua preferência e o bairro onde você mora.
    */
    public static void personalInformations(String[] args) {
        // Lê as informações
        String nome, time, bairro;
        nome = System.console().readLine("Digite seu nome: ");
        time = System.console().readLine("Digite o time de futebol de sua preferência: ");
        bairro = System.console().readLine("Digite o bairro onde você mora: ");

        // Imprime as informações
        System.out.println("Nome: " + nome + " | " + "Time: " + time + " | " + "Bairro: " + bairro);
        System.out.println();
    }
    
    /*
    4. Construa um programa em Java que leia 
    um número e diga se ele é positivo.
    */
    public static void positiveNumbers(String[] args) {
        // Lê o número
        int num = Integer.parseInt(System.console().readLine("Digite um número: "));

        // Verifica se o número é positivo
        if (num > 0)
            System.out.println("O número " + num + " é positivo.");
        else
            System.out.println("O número " + num + " não é positivo.");
        System.out.println();
    }

    /*
    5. Construa um programa em Java que leia um numero 
    inteiro e diga se ele é par ou ímpar.
    */
    public static void evenOddNumbers(String[] args) {
        // Lê o número
        int num = Integer.parseInt(System.console().readLine("Digite um número: "));

        // Verifica se o número é par ou ímpar
        if (num % 2 == 0)
            System.out.println("O número " + num + " é par.");
        else
            System.out.println("O número " + num + " é ímpar.");
        System.out.println();
    }
    
    /*
    7. Construa um programa em Java que leia três números e diga se eles 
    podem ser os lados de um triângulo isósceles, equilátero ou escaleno.
    */
    public static void triangleType(String[] args) {
        // Lê os números
        int a = Integer.parseInt(System.console().readLine("Digite o primeiro número: "));
        int b = Integer.parseInt(System.console().readLine("Digite o segundo número: "));
        int c = Integer.parseInt(System.console().readLine("Digite o terceiro número: "));

        // Verifica se os números formam um triângulo
        if (a + b > c && a + c > b && b + c > a) {
            if (a == b && b == c)
                System.out.println("Os números formam um triângulo equilátero.");
            else if (a == b || a == c || b == c)
                System.out.println("Os números formam um triângulo isósceles.");
            else
                System.out.println("Os números formam um triângulo escaleno.");
        } else
            System.out.println("Os números não formam um triângulo.");
        System.out.println();
    }

    /*
    10. Fazer um programa que leia os valores do peso e da altura de pessoas, 
    enquanto não for digitado o número -1, conte e escreva quantas pessoas 
    estão acima do peso. A condição (peso /(altura*altura)) <= 25 diz que 
    a pessoa está no peso normal.
    */
    public static void overweightPeople(String[] args) {
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
        System.out.println(acimaDoPeso + " pessoas estão acima do peso.");
        System.out.println();
    }

    /*
    21. Construa um método que receba uma temperatura em graus Celsius 
    e calcule e retorne o valor da temperatura equivalente em graus 
    Farenheit. Celsius = 5.0/9.0 (farenheit -32). 
    */
    public static double celsiusToFarenheit(String[] args) {
        double celsius = Double.parseDouble(System.console().readLine("Digite a temperatura em graus Celsius: "));
        double fahrenheit = (celsius * 9.0 / 5.0) + 32;
    
        System.out.println(fahrenheit);
        return fahrenheit;
    }
}
