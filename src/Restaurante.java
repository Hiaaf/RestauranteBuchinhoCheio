import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Restaurante {
    public Restaurante(String nome, String endereco, int numMesas) {
        var scan = new Scanner(System.in);

        this.nome = nome;
        this.endereco = endereco;

        this.mesas = new ArrayList<>();
        for (int i = 0; i < numMesas; i++) {
            System.out.printf("Mesa %d, nº cadeiras? ", i);
            this.mesas.add(new Mesa(i, scan.nextInt()));
        }

        this.cardapio = new HashMap<>();
        var cpp = CardapioPrePronto.cardapio;
        for (int i = 0; i < cpp.size(); i++) {
            this.cardapio.put(i, cpp.get(i));
        }
    }

    // Parametros
    private final String nome;
    private final String endereco;
    private final List<Mesa> mesas;
    private final Map<Integer, Item> cardapio;

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public Mesa getMesa(int index) {
        return mesas.get(index);
    }

    public int getNumMesas() {
        return mesas.size();
    }
    //


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
            mesaDisponivel.reserva(data, cliente, numPessoas);
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

    public void imprimeCardapio() {
        System.out.println("Cardápio (ID: nome - valor):");
        for (var entry : cardapio.entrySet()) {
            System.out.printf("\t%d: %s%n", entry.getKey(), entry.getValue());
        }
    }

    public Item getItem(int id) {
        return cardapio.get(id);
    }
}
