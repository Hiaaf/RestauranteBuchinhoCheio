import java.util.List;
import java.util.Scanner;

/*
* Como isso é um teste, coloquei muitas funções que num mundo real não deveriama aparecer para o usuário
* */

public class TesteRestaurante {
    public static void main(String[] args) {
        var scan = new Scanner(System.in);

        var buchChei = new Restaurante("Buchinho Cheio 1", "Av. 1", 4);

        int opc;
        do {
            System.out.printf("%n%nRestaurante %s%nEndereço: %s%n", buchChei.getNome(), buchChei.getEndereco());
            System.out.println("""
                    Escolha o que fazer:
                    0. Cancela
                    1. Fazer reserva
                    2. Ver mesas reservadas e suas datas
                    3. Cancelar uma reserva"""
            );
            opc = scan.nextInt();

            switch (opc) {
                case 0:
                    System.out.println("Obrigado por usar!");
                    break;
                case 1:
                    OperacoesUsuario.reservar(buchChei);
                    break;
                case 2:
                    OperacoesUsuario.listarReservas(buchChei);
                    break;
                case 3:
                    OperacoesUsuario.cancelarReserva(buchChei);
                    break;
                default:
                    System.out.println("Operação invalida! Tente novamente.");
                    break;
            }
        } while (opc != 0);
    }
}

final class OperacoesUsuario {
    private static final Scanner scan = new Scanner(System.in);

    public static void reservar(Restaurante res) {
        if (res == null) {
            System.out.println("Parametro null");
            return;
        }

        System.out.println("Mesa para quantas pessoas?");
        int numPessoas = scan.nextInt();
        if (numPessoas <= 0) {
            System.out.println("Número de pessoas deve ser maior que 0");
            return;
        }

        System.out.println("Para quando será a reserva? (formato 'dia mes')");
        var mesaReservada = res.reservarMesa(
                numPessoas, new Date(scan.nextInt(), scan.nextInt())
        );

        if (mesaReservada == null) {
            System.out.printf("Perdão, não há mesas disponíveis para %d pessoas neste horário.%n", numPessoas);
            return;
        }
        System.out.printf("ID da mesa reservada: %d%n", mesaReservada.getNumMesa());
    }

    public static void listarReservas(Restaurante res) {
        if (res == null) {
            System.out.println("Parametro null");
            return;
        }

        List<Mesa> mesas = res.getMesas();

        for (Mesa mesa : mesas) {
            List<Date> datas = mesa.getDatas();
            if (datas.isEmpty()) continue;

            System.out.printf("Mesa %d está reservada no(s) dia(s):%n", mesa.getNumMesa());
            for (Date data : datas) {
                System.out.printf("\t%s%n", data.getData());
            }
        }
    }

    public static void cancelarReserva(Restaurante res) {
        if (res == null) {
            System.out.println("Parametro null");
            return;
        }

        System.out.println("Qual o número da mesa que deseja cancelar a reserva?");
        int numMesa = scan.nextInt();
        if (numMesa < 0 || numMesa > (res.getNumMesas() - 1)) {
            System.out.println("Não temos uma mesa com este número!");
            return;
        }

        System.out.println("Para quando foi a reserva? (formato 'dia mes')");
        boolean cancela = res.cancelarMesa(numMesa, new Date(scan.nextInt(), scan.nextInt()));

        if (!cancela) {
            System.out.println("Não há reservas para essa mesa nesta data.");
            return;
        }
        System.out.println("Reserva cancelada.");
    }
}
