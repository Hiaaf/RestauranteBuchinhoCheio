import java.util.List;

public class Mesa {
    private final int numMesa;
    private List<Date> datas;
    private int numCadeiras;
    private boolean reservada;

    public int getNumMesa() {
        return numMesa;
    }

    public Date getDatas() {
        return datas;
    }
    public void setDatas(Date datas) {
        this.datas = datas;
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
        this.datas = null;
        this.reservada = false;
        this.numCadeiras = numCadeiras;
    }

    public void reserva(Date data) {
        reservada = true;
        this.datas = data;
    }

    public void libera() {
        reservada = false;
        datas = null;
    }
}
