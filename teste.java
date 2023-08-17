
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class teste {

    public static List<Produto> testecriarCSV() {
        List<Produto> produtos = new ArrayList<>();
        String file = "produtosPadrao.csv";

        try (Scanner s = new Scanner(new File(file))) {
            s.nextLine();
            s.useDelimiter(", |\n");
            s.useLocale(Locale.US);

            while (s.hasNext()) {
                int id = s.nextInt();
                String descricao = s.next();
                String categoria = s.next();
                int qtdEstoque = s.nextInt();
                double preco = s.nextDouble();
                produtos.add(new ProdutoPadrao(id, descricao, categoria, qtdEstoque, preco));
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
        for (Produto p : produtos)
            System.out.println(p);

        return produtos;
    }

    public static void main(String[] args) {
        testecriarCSV();
    }
}