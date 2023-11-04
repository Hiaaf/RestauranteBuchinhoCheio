import java.util.List;

public class Restaurante {
    private String nome;
    private String endereco;

    private List<Mesa> mesas;

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEndereco() {
        return endereco;
    }
}
