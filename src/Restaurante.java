import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurante {
    private final String nome;
    private final String endereco;

    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }

    private final List<Mesa> mesas;

    public List<Mesa> getMesas() {
        return mesas;
    }
    public int getNumMesas() {
        return mesas.size();
    }

    public Restaurante(String nome, String endereco, int numMesas) {
        var scan = new Scanner(System.in);

        this.nome = nome;
        this.endereco = endereco;

        mesas = new ArrayList<>();
        for (int i = 0; i < numMesas; i++) {
            System.out.printf("Mesa %d, nº cadeiras? ", i);
            mesas.add(new Mesa(i, scan.nextInt()));
        }
    }

    // Reserva uma mesa para um numero especifico de pessoas, tentando achar a com o menor numero de cadeiras possivel.
    // Retorna a mesa reservada, null se não tiver mesas disponiveis.
    public Mesa reservarMesa(int numPessoas, Date data, Cliente cliente) {
        Mesa mesaDisponivel = null;

        for (Mesa mesa : mesas) {
            if (mesa.getNumCadeiras() < numPessoas) continue;
            if (mesa.dataReservada(data)) continue;

            if (mesaDisponivel != null && mesaDisponivel.getNumCadeiras() < mesa.getNumCadeiras()) continue;

            mesaDisponivel = mesa;
        }

        if (mesaDisponivel != null) {
            mesaDisponivel.reserva(data, cliente);
        }

        return mesaDisponivel;
    }

    // Cancela a reserva da mesa.
    // Retorna true se conseguir, false se não
    public boolean cancelarMesa(int numMesa, Date data, String emailCliente) {
        var mesa = mesas.get(numMesa);

        if (!mesa.checaReserva(data, emailCliente)) return false;

        mesa.cancela(data);

        return true;
    }
}
