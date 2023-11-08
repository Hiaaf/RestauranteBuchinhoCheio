import java.util.List;

public class Comanda {
    private List<Item> consumo;
    private double valor;

    public List<Item> getConsumo() {
        return consumo;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
        return valor * 0.1;
    }

    public double dividirConta(int numPessoas) {
        return (valor + calcular10Porcento()) / numPessoas;
    }
}
