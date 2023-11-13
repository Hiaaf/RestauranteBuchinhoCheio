import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private final int numMesa;
    private final List<Date> datas;
    private int numCadeiras;

    /*
    * Já que fiz com a lista de datas, checo se a data foi reservada.
    * A mesa então pode ser reservada por várias pessoas, mas em datas diferentes.
    * */
//    private boolean reservada;

    public int getNumMesa() {
        return numMesa;
    }

    public List<Date> getDatas() {
        return datas;
    }
    public Date getData(int index) {
        return datas.get(index);
    }

    // Retorna true se a mesa ja foi reservada naquela data. false no contrario
    public boolean dataReservada(Date dataP) {
        for (Date data : datas) {
            if (data.equals(dataP)) return true;
        }

        return false;
    }

    public void addData(Date data) {
        datas.add(data);
    }

    public int getNumCadeiras() {
        return numCadeiras;
    }
    public void setNumCadeiras(int numCadeiras) {
        this.numCadeiras = numCadeiras;
    }

    public Mesa(int numMesa, int numCadeiras) {
        this.numMesa = numMesa;
        this.datas = new ArrayList<>();
        this.numCadeiras = numCadeiras;
    }

    public void reserva(Date data) {
        addData(data);
    }

    public void cancela(Date data) {
        datas.remove(data);
    }
}
