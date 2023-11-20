import java.util.List;

public class Comanda {
    private List<Item> consumo;
    private double valor;

    public List<Item> getConsumo() {
        return consumo;
    }
    public double getValor() {
        return valor;
    }

    public void listarConsumo() {
        for (var item : consumo) {
            System.out.println(item);
        }
    }

    public double calcular10Porcento() {
        return valor * 10/100;
    }

    public double dividirConta(int numPessoas) {
        return valor / numPessoas;
    }
}
