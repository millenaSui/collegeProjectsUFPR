import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Arquivo {
     
    public void gravar(String frase, String nomeArquivo) {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo, true);
            PrintWriter gravarArq = new PrintWriter(arquivo);
            gravarArq.println(frase);
            gravarArq.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo.");
        }
    }    
}
