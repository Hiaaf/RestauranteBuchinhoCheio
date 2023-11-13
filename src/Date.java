// Não gostei da classe Date do java, ent fiz a minha meia boca :)

public class Date {
    private final int dia;
    private final int mes;

    public Date(int dia, int mes) {
        // Assume-se que o usuário vai sempre colocar tempos válidos.
        this.dia = dia;
        this.mes = mes;
    }

    public String getData() {
        return String.format("%d/%d", dia, mes);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Date other)) { // Isso aqui é mágica pra mim, IDE só recomendou e achei incrível.
            return false;
        }
        // var other = (Date) obj;

        return (dia == other.dia && mes == other.mes);
    }
}
