import java.util.ArrayList;
import java.util.List;


public class Mesa {
    public Mesa(int numMesa, int numCadeiras) {
        this.numMesa = numMesa;
        this.numCadeiras = numCadeiras;
        this.reservas = new ArrayList<>();
    }

    // Parametros
    private final int numMesa; // ID da mesa
    private final int numCadeiras;
    private final List<Reserva> reservas; // Mapa com todas as reservas da mesa, com suas datas e clientes

    public int getNumMesa() {
        return numMesa;
    }

    public int getNumCadeiras() {
        return numCadeiras;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Reserva getReserva(Date data) {
        for (var reserva : reservas) {
            if (reserva.data().equals(data))
                return reserva;
        }

        return null;
    }

    public List<Date> getDatas() {
        List<Date> datas = new ArrayList<>();
        for (var reserva : reservas) {
            datas.add(reserva.data());
        }
        return datas;
    }

    // Retorna true se a mesa ja foi reservada naquela data. false no contrario
    public boolean dataReservada(Date dataP) {
        for (Date data : getDatas()) {
            if (data.equals(dataP)) return true;
        }

        return false;
    }

    // Adiciona uma nova reserva
    public void reserva(Date data, Cliente cliente, int numPessoas) {
        reservas.add(new Reserva(data, cliente, new Comanda(), numPessoas));
    }

    // Remove uma reserva
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
