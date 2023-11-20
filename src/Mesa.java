import java.util.ArrayList;
import java.util.List;


public class Mesa {
    private final int numMesa; // ID da mesa
    // Mapa com todas as reservas da mesa, com suas datas e clientes
    private final List<Reserva> reservas;
    private int numCadeiras;

    /*
    * Já que fiz com a lista de reservas, checo se a data foi reservada.
    * A mesa então pode ser reservada por várias pessoas, mas em datas diferentes.
    * */
//    private boolean reservada;


    public List<Reserva> getReservas() {
        return reservas;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public List<Date> getDatas() {
        List<Date> datas = new ArrayList<>();
        for (var reserva : reservas) {
            datas.add(reserva.data());
        }
        return datas;
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
        List<Cliente> clientes = new ArrayList<>();
        for (var reserva : reservas) {
            clientes.add(reserva.cliente());
        }
        return clientes;
    }
    public Cliente getCliente(int index) {
        return getClientes().get(index);
    }
    public Cliente getCliente(Date data) {
        for (var reserva : reservas) {
            if (reserva.data().equals(data)) {
                return reserva.cliente();
            }
        }
        return null;
    }


    public Mesa(int numMesa, int numCadeiras) {
        this.numMesa = numMesa;
        this.numCadeiras = numCadeiras;
        this.reservas = new ArrayList<>();
    }

    public void reserva(Date data, Cliente cliente, int numPessoas) {
        reservas.add(new Reserva(data, cliente, numPessoas));
    }

    public void cancela(Date data) {
        reservas.removeIf(reserva -> reserva.data().equals(data));
    }

    /* Checa se cliente tem reserva na data mencionada
    * retorna true se sim, false se não */
    public boolean checaReserva(Date data, String emailCliente) {
        for (var reserva : reservas) {
            if (reserva.data().equals(data) && reserva.cliente().email().equals(emailCliente))
                return true;
        }
        return false;
    }
}
