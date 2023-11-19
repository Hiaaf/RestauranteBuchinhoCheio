import java.util.List;
import java.util.Scanner;

/*
* Como isso é um teste, coloquei muitas funções que num mundo real não deveriam aparecer para o usuário
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
            scan.nextLine(); // limpa o buffer??? não sei???? tava pulando alguns .nextLine() eu to ficando maluco

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

        System.out.println("Informe seu nome e seu email para entrarmos em contato! (Separe-os com uma nova linha)");
        var cliente = new Cliente(scan.nextLine(), scan.nextLine());

        System.out.println("Mesa para quantas pessoas?");
        int numPessoas = scan.nextInt();
        if (numPessoas <= 0) {
            System.out.println("Número de pessoas deve ser maior que 0");
            return;
        }

        System.out.println("Para quando será a reserva? (formato 'dia mes')");
        var data = new Date(scan.nextInt(), scan.nextInt());
        scan.nextLine();

        var mesaReservada = res.reservarMesa(numPessoas, data, cliente);

        if (mesaReservada == null) {
            System.out.printf("Perdão, não há mesas disponíveis para %d pessoas neste horário.%n", numPessoas);
            return;
        }

        System.out.printf("""
                Mesa reservada com sucesso, %s!
                ID da mesa reservada: %d
                Email do reservante: %s
                Data da reserva: %s
                
                Caso precise cancelar a reserva, guarde o email usado, a data da reserva, e o ID de sua mesa!
                Grato!%n""", cliente.nome(), mesaReservada.getNumMesa(), cliente.email(), data.getData()
        );
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
                // TODO: deixa mais bunitin issaq pelo amor
                Cliente cliente = mesa.getCliente(mesa.getDatas().indexOf(data));
                System.out.printf("\t%s, por %s (email: %s)%n", data.getData(), cliente.nome(), cliente.email());
            }
        }
    }

    public static void cancelarReserva(Restaurante res) {
        if (res == null) {
            System.out.println("Parametro null");
            return;
        }

        System.out.println("Qual email você usou para fazer a reserva?");
        String email = scan.nextLine();

        System.out.println("Qual o ID de sua mesa?");
        int numMesa = scan.nextInt();
        scan.nextLine();
        if (numMesa < 0 || numMesa > (res.getNumMesas() - 1)) {
            System.out.println("Não temos uma mesa com este número!");
            return;
        }

        System.out.println("Para quando foi a reserva? (formato 'dia mes')");
        var data = new Date(scan.nextInt(), scan.nextInt());
        scan.nextLine();

        boolean cancela = res.cancelarMesa(numMesa, data, email);

        if (!cancela) {
            System.out.println("Não foi possível encontrar uma reserva com essas informações!");
            return;
        }
        System.out.println("""
                Reserva cancelada com sucesso!

                Volte algum outro dia!"""
        );
    }
}
