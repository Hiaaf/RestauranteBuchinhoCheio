// Não gostei da classe Date do java, ent fiz a minha meia boca :)

public record Date(int dia, int mes) {
    @Override
    public String toString() {
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
