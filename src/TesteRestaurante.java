import java.util.List;
import java.util.Scanner;


public class TesteRestaurante {
    public static void main(String[] args) {
        var scan = new Scanner(System.in);

        var buchChei = new Restaurante("Buchinho Cheio 1", "Av. 1", 2);

        int opc;
        do {
            System.out.printf("%n%nRestaurante %s%nEndereço: %s%n", buchChei.getNome(), buchChei.getEndereco());
            System.out.println("""
                    Escolha o que fazer:
                    0. Cancela
                    1. Fazer reserva
                    2. Ver mesas reservadas e suas datas
                    3. Cancelar uma reserva
                    4. Atender uma mesa"""
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
                case 4:
                    OperacoesUsuario.atenderMesa(buchChei);
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
                Grato!%n""", cliente.nome(), mesaReservada.getNumMesa(), cliente.email(), data
        );
    }

    public static void listarReservas(Restaurante res) {
        if (res == null) {
            System.out.println("Parametro null");
            return;
        }

        List<Mesa> mesas = res.getMesas();

        boolean algumaReserva = false;

        for (Mesa mesa : mesas) {
            if (mesa.getReservas().isEmpty()) continue;
            algumaReserva = true;

            System.out.printf("Mesa %d está reservada no(s) dia(s):%n", mesa.getNumMesa());
            for (var reserva : mesa.getReservas()) {
                System.out.printf("\t%s, por %s, para %d pessoas%n", reserva.data(), reserva.cliente(), reserva.numPessoas());
            }
        }

        if (!algumaReserva) System.out.println("Nenhuma reserva feita!");
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

    public static void atenderMesa(Restaurante res) {
        if (res == null) {
            System.out.println("Parametro null");
            return;
        }

        // Como fiz a mesa poder ter varias reservas mas em dias diferentes, pergunta que dia é hoje primeiro :)
        System.out.println("Que dia é hoje? (dia mes)");
        Date hoje = new Date(scan.nextInt(), scan.nextInt());
        scan.nextLine();

        System.out.println("Que mesa você quer atender?");
        Mesa mesa = res.getMesa(scan.nextInt());
        Reserva reserva = mesa.getReserva(hoje);

        if (reserva == null) {
            System.out.println("Não há ninguém nessa mesa hoje!");
            return;
        }

        Comanda comanda = reserva.comanda();

        int opc;
        do {
            System.out.println("""
                    Escolha o que fazer:
                    0. Cancela
                    1. Checar consumo
                    2. Checar total
                    3. Fazer pedido
                    4. Fechar conta (Imprime informações relevantes e remove a reserva!)"""
            );
            opc = scan.nextInt();
            scan.nextLine();

            switch (opc) {
                case 0:
                    break;
                case 1:
                    comanda.listarConsumo();
                    break;
                case 2:
                    System.out.printf("R$%.2f%n", comanda.getValor());
                    break;
                case 3:
                    System.out.println("Informe o ID do item:");
                    res.imprimeCardapio();
                    int id = scan.nextInt();
                    scan.nextLine();
                    comanda.adicionarConsumo(id, res.getItem(id));
                    break;
                case 4:
                    comanda.listarConsumo();
                    System.out.printf("""
                            Mesa: %d
                            SubTotal: R$%.2f
                            10%%: R$%.2f
                            Total: R$%.2f
                            Dividido pelas pessoas da mesa: R$%.2f
                            """,
                            mesa.getNumMesa(),
                            comanda.getValor(), comanda.calcular10Porcento(), (comanda.getValor() + comanda.calcular10Porcento()),
                            comanda.dividirConta(reserva.numPessoas())
                    );
                    System.out.println("Fechar a conta? (0 não, 1 sim)");

                    if (scan.nextInt() == 1) {
                        System.out.println("Fechado!");
                        mesa.cancela(hoje);
                        opc = 0; // Não da para atender a mesa mais! Volta pro menu anterior.
                    }
                    scan.nextLine();

                    break;
                default:
                    System.out.println("Operação invalida! Tente novamente.");
                    break;
            }
        } while (opc != 0);
    }
}
