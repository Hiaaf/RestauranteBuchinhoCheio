import java.util.Date;

public class Mesa {
    private int numMesa;
    private Date data;
    private int numCadeiras;
    private boolean reservada;

    public int getNumMesa() {
        return numMesa;
    }
    public Date getData() {
        return data;
    }
    public int getNumCadeiras() {
        return numCadeiras;
    }
    public void setNumCadeiras(int numCadeiras) {
        this.numCadeiras = numCadeiras;
    }

    public boolean estaReservada() {
        return reservada;
    }

    public Mesa(int numMesa, int numCadeiras) {
        this.numMesa = numMesa;
        this.data = null;
        this.reservada = false;
        this.numCadeiras = numCadeiras;
    }

    public void reserva() {
        reservada = true;
    }

    public void libera() {
        reservada = false;
    }
}
