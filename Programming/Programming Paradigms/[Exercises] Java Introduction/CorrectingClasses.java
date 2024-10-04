import java.util.Scanner;

public class CorrectingClasses {
    /*
    15. Digite e rode o programa de exemplo das Exceções:
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
    funciona e quando a captura não funciona.
    */
    public static void correctExceptionsClass(String[] args) {
        int num1, num2;
        
        try (Scanner teclado = new Scanner(System.in)) {
            System.out.println("Digite o número");
            num1 = teclado.nextInt();
            System.out.println("Digite o número");
            num2 = teclado.nextInt();
            System.out.println("Soma = " + (num1 + num2));
            System.out.println("Subtração = " + (num1 - num2));
            System.out.println("Multiplicação = " + (num1 * num2));
            System.out.println("Divisão = " + (num1 / num2));
        
        } catch (ArithmeticException aex) {
            System.out.println("Erro de divisão por zero!" + aex);
        
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
        
        } finally {
            System.out.println("Fim!");
        }
        System.out.println();
    }
}
