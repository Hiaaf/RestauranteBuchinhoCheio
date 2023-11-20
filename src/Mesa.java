import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mesa {
    private final int numMesa; // ID da mesa
    // Mapa com todas as reservas da mesa, com suas datas e clientes
    private final Map<Date, Cliente> reservas;
    private int numCadeiras;

    /*
    * Já que fiz com a lista de reservas, checo se a data foi reservada.
    * A mesa então pode ser reservada por várias pessoas, mas em datas diferentes.
    * */
//    private boolean reservada;


    public Map<Date, Cliente> getReservas() {
        return reservas;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public List<Date> getDatas() {
        return new ArrayList<>(reservas.keySet());
    }
    public Date getData(int index) {
        return getDatas().get(index);
    }

    // Retorna true se a mesa ja foi reservada naquela data. false no contrario
    public boolean dataReservada(Date dataP) {
        for (Date data : getDatas()) {
            if (data.equals(dataP)) return true;
        }

        return false;
    }

    public int getNumCadeiras() {
        return numCadeiras;
    }
    public void setNumCadeiras(int numCadeiras) {
        this.numCadeiras = numCadeiras;
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(reservas.values());
    }
    public Cliente getCliente(int index) {
        return getClientes().get(index);
    }
    public Cliente getCliente(Date data) {
        return reservas.getOrDefault(data, null);
    }


    public Mesa(int numMesa, int numCadeiras) {
        this.numMesa = numMesa;
        this.numCadeiras = numCadeiras;
        this.reservas = new HashMap<>();
    }

    public void reserva(Date data, Cliente cliente) {
        reservas.put(data, cliente);
    }

    public void cancela(Date data) {
        for (var d : getDatas()) {
            if (d.equals(data)) reservas.remove(d);
            return;
        }
    }

    /* Checa se cliente tem reserva na data mencionada
    * retorna true se sim, false se não */
    public boolean checaReserva(Date data, String emailCliente) {
//        if (!reservas.containsKey(data)) return false;

        for (var reserva : reservas.entrySet()) {
            if (reserva.getKey().equals(data) && reserva.getValue().email().equals(emailCliente))
                return true;
        }
        return false;
    }
}
