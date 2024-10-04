public class Pessoa {
    /* 
    Criar classe Pessoa com os atributos nome, email, 
    endereço e telefone e os métodos de acesso
    */
    private String nome;
    private String email;
    private String endereco;
    private String telefone;

    public Pessoa(String nome, String email, String endereco, String telefone) {
        // Constructor method
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Access methods to name
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    // Access methods to email
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    // Access methods to address
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEndereco() {
        return endereco;
    }

    // Access methods to phone
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTelefone() {
        return telefone;
    }
} 