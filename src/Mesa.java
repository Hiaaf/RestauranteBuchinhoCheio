import java.util.Date;

public class Mesa {
    private int numeroMesa;
    private Date data;
    private boolean reserva;

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
}
