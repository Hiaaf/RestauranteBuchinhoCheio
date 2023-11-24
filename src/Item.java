import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Item(String nome, double valor) {
    @Override
    public String toString() {
        return String.format("%s - %.2f", nome, valor);
    }
}

final class CardapioPrePronto {
    public static final List<Item> cardapio = new ArrayList<>(Arrays.asList(
            new Item("Feijão tropeiro", 29.90),
            new Item("Parmegiana",      39.90),
            new Item("Mexidão",         25.90),
            new Item("Macarronada",     24.90),
            new Item("Refri 1L",        08.90),
            new Item("Refri lata",      04.90)
    ));
}
