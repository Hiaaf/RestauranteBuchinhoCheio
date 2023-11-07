import java.util.Date;
import java.util.List;

public class Mesa {
    private int numeroMesa;
    private int numeroMaxClientes;
    private Date data = null;
    private boolean reserva = false;
    private List<Cliente> clientes;

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public Date getData() {
        return data;
    }

    // Retorna false se não foi possível reservar. Se for, reserva e retorna true
    public boolean reservar() {
        if (reserva) return false;
        reserva = true;
        return true;
    }

    public Mesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
}
