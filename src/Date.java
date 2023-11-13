// Não gostei da classe Date do java, ent fiz a minha meia boca :)

public class Date {
    private int dia;
    private int mes;

    public Date(int dia, int mes) {
        // Assume-se que o usuário vai sempre colocar tempos válidos.
        this.dia = dia;
        this.mes = mes;
    }

    public String getDate() {
        return String.format("%d/%d", dia, mes);
    }
}
