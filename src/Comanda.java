import java.util.List;
import java.util.Map;

public class Comanda {
    private Map<Integer, Item> consumo;
    private double valor;

    public double getValor() {
        return valor;
    }

    public void listarConsumo() {
        for (Item item : consumo.values()) {
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
