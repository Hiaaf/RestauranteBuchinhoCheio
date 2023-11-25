import java.util.HashMap;
import java.util.Map;

public class Comanda {
    public Comanda() {
        this.consumo = new HashMap<>();
    }

    private final Map<Integer, Item> consumo;
    private double valor;

    public void listarConsumo() {
        System.out.println("Consumo");
        for (Item item : consumo.values()) {
            System.out.println("\t" + item);
        }
    }

    public double getValor() {
        return valor;
    }


    public double calcular10Porcento() {
        return valor * 10/100;
    }

    public double dividirConta(int numPessoas) {
        return (valor + calcular10Porcento()) / numPessoas;
    }

    public void adicionarConsumo(int id, Item item) {
        consumo.put(id, item);
        valor += item.valor();
    }
}
