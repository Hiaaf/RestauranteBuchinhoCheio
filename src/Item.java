import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {
    private String nome;
    private double valor;

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }

    public Item(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    private static final Item[] itensPre = new Item[] {
            new Item("X-burguer",                    9.99),
            new Item("Sanduiche de presunto",       15.49),
            new Item("Bolinhos de queijo",          14.99),
            new Item("Sopa do dia",                 11.99),
            new Item("Massa à bolonhesa",           24.99),
            new Item("Frango grelhado com legumes", 29.99),
            new Item("Bife de filé mignon",         35.49),
            new Item("Sorvete",                      9.99),
            new Item("Mousse",                       7.99),
            new Item("Refrigerante",                 4.99),
            new Item("Suco (lata)",                  4.49)
    };

    // Metodo que retorna uma lista com alguns itens predeterminados só para testes :)
    public static List<Item> getAlgumasComidas() {
        return Arrays.asList(itensPre);
    }
}
