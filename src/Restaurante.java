import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurante {
    private String nome;
    private String endereco;

    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }

    private List<Mesa> mesas;

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
    public Mesa reservarMesa(int numPessoas) {
        Mesa mesaDisponivel = null;

        for (int i = 0; i < mesas.size(); i++) {
            var mesa = mesas.get(i);

            if (mesa.estaReservada()) continue;
            if (mesa.getNumCadeiras() < numPessoas) continue;
            if (mesaDisponivel != null && mesaDisponivel.getNumCadeiras() < mesa.getNumCadeiras()) continue;

            mesaDisponivel = mesa;
            if (mesaDisponivel.getNumCadeiras() == numPessoas) break;
        }

        if (mesaDisponivel != null) mesaDisponivel.reserva();

        return mesaDisponivel;
    }
}
