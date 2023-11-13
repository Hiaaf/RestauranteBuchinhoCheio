import java.util.Scanner;

/*
* Como isso é um teste, coloquei muitas funções que num mundo real não deveriama aparecer para o usuário
* */

public class TesteRestaurante {
    public static void main(String[] args) {
        var scan = new Scanner(System.in);

        var buchChei = new Restaurante("Buchinho Cheio 1", "Av. 1", 4, 30);

        int opc;
        do {
            System.out.printf("Restaurante %s%n%n", buchChei.getNome());
            System.out.println("""
                    Escolha o que fazer:
                    0. Cancela
                    1. Fazer reserva
                    2. Ver mesas reservadas
                    3. Cancelar uma reserva"""
            );
            opc = scan.nextInt();

            switch (opc) {
                case 1:
                    OperacoesUsuario.reservar(buchChei);
                    break;
                default:
                    break;
            }
        } while (opc != 0);
    }
}

final class OperacoesUsuario {
    public static void reservar(Restaurante res) {
        if (res == null) {
            System.out.println("Parametro null");
            return;
        }

        var scan = new Scanner(System.in);

        System.out.println("Mesa para quantas pessoas?");
        int numPessoas = scan.nextInt();
        if (numPessoas <= 0) {
            System.out.println("Número de pessoas invalido");
            return;
        }

        System.out.println("Para quando será a reserva? (formato 'hora:minuto dia/mes')");
        var mesaReservada = res.reservarMesa(
                numPessoas, new Date(scan.nextInt(), scan.nextInt())
        );

        if (mesaReservada == null) {
            System.out.printf("Perdão. Não há mesas disponíveis para %d pessoas neste horário.%n", numPessoas);
            return;
        }
        System.out.printf("ID da mesa reservada: %d%n", mesaReservada.getNumMesa());
    }
}
