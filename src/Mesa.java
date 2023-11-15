import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private final int numMesa;
    private final List<Date> datas;
    private int numCadeiras;
    // Lista de todos os clientes, onde clientes[i] se refere ao cliente da data datas[i]
    private final List<Cliente> clientes;

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

    public int getNumCadeiras() {
        return numCadeiras;
    }
    public void setNumCadeiras(int numCadeiras) {
        this.numCadeiras = numCadeiras;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    public Cliente getCliente(int index) {
        return clientes.get(index);
    }


    public Mesa(int numMesa, int numCadeiras) {
        this.numMesa = numMesa;
        this.datas = new ArrayList<>();
        this.numCadeiras = numCadeiras;
        this.clientes = new ArrayList<>();
    }

    public void reserva(Date data, Cliente cliente) {
        datas.add(data);
        clientes.add(cliente);
    }

    public void cancela(Date data, Cliente cliente) {
        datas.remove(data);
        clientes.remove(cliente);
    }
}
