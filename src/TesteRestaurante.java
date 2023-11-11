import java.util.Scanner;

public class TesteRestaurante {
    public static void main(String[] args) {
        var scan = new Scanner(System.in);

        var buchChei = new Restaurante("Buchinho Cheio 1", "Av. 1", 4);

        do {
            System.out.printf("Restaurante %s%nFaça sua reserva:%n", buchChei.getNome());
            System.out.println("Mesa para quantas pessoas?");
            int numPessoas = scan.nextInt();
            if (numPessoas < 1) break;
            var mesaReservada = buchChei.reservarMesa(numPessoas);

            if (mesaReservada == null) {
                System.out.printf("Perdão. Não há mesas disponíveis para %d pessoas no momento.%n", numPessoas);
                continue;
            }
            System.out.printf("ID da mesa reservada: %d%n", mesaReservada.getNumMesa());

        } while (true);
    }
}
