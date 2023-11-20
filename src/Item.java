public record Item(String nome, double valor) {
    @Override
    public String toString() {
        return String.format("%s - %.2f", nome, valor);
    }
}
