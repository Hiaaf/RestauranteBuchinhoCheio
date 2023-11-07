import java.util.ArrayList;
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

    public Restaurante(int numMesas) {
        mesas = new ArrayList<Mesa>(numMesas);
        for (int i = 0; i < numMesas; i++) {
            mesas.add(new Mesa(i));
        }
    }
}
