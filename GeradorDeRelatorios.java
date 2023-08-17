import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class GeradorDeRelatorios {

	private List<Produto> produtos;
	private Algoritmo algoritmo;
	private Criterio criterio;
	private Filtro filtro;
	private List<String> argFiltro;

	public GeradorDeRelatorios(List<Produto> produtos, Algoritmo algoritmo, Criterio criterio, Filtro filtro,
			List<String> argFiltro) {

		this.produtos = produtos;
		this.algoritmo = algoritmo;
		this.criterio = criterio;
		this.filtro = filtro;
		this.argFiltro = argFiltro;
	}

	private void ordena(int ini, int fim) {

		algoritmo.ordena(produtos, criterio, ini, fim);
	}

	public void debug() {

		System.out.println("Gerando relatório para array contendo " + produtos.size() + " produto(s)");
		if (!(filtro instanceof FiltroTodos)) {
			if (filtro instanceof FiltroIntervalo)
				System.out.println("parametro filtro = '" + argFiltro.get(0) + " e " + argFiltro.get(1) + "'");
			else {
				System.out.println("parametro filtro = '" + argFiltro + "'");
			}
		}
	}

	public void geraRelatorio(String arquivoSaida) throws IOException {

		debug();

		ordena(0, produtos.size() - 1);

		PrintWriter out = new PrintWriter(arquivoSaida);

		out.println("<!DOCTYPE html><html>");
		out.println("<head><title>Relatorio de produtos</title></head>");
		out.println("<body>");
		out.println("Relatorio de Produtos:");
		out.println("<ul>");

		int count = 0;

		for (int i = 0; i < produtos.size(); i++) {

			Produto p = produtos.get(i);

			if (filtro.filtra(produtos.get(i), argFiltro)) {

				out.print("<li>");

				Formato formato = new FormatoPadrao();
				if (p instanceof ProdutoComFormatacao) {
					ProdutoComFormatacao pComFormat = (ProdutoComFormatacao) p;
					if (pComFormat.getNegrito())
						formato = new FormatoNegrito(formato);
					if (pComFormat.getItalico())
						formato = new FormatoItalico(formato);
					formato = new FormatoCor(formato, pComFormat.getCor());
					formato.formatacao(out, pComFormat);
				} else {
					System.err.println("Erro: Formato invalido");
				}

				out.println("</li>");
				count++;
			}
		}

		out.println("</ul>");
		out.println(count + " produtos listados, de um total de " + produtos.size() + ".");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	public static List<Produto> lerCSV(String file) {
		List<Produto> produtos = new ArrayList<>();

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
				boolean negrito = s.nextBoolean();
				boolean italico = s.nextBoolean();
				String cor = s.next();
				produtos.add(
						new ProdutoComFormatacao(id, descricao, categoria, qtdEstoque, preco, negrito, italico, cor));
			}

		} catch (IOException e) {
			System.err.print(e.getMessage());
		}

		return produtos;
	}

	public static void main(String[] args) {

		if (args.length < 3) {

			System.out.println("Uso:");
			System.out.println("\tjava " + GeradorDeRelatorios.class.getName()
					+ " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem> <opções de formatação>");
			System.out.println("Onde:");
			System.out.println("\talgoritmo: 'quick' ou 'insertion'");
			System.out.println(
					"\tcriterio de ordenação: 'preco_c' ou 'preco_d' ou 'descricao_c' ou 'descricao_d' ou 'estoque_c' ou 'estoque_d'");
			System.out.println(
					"\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual' ou 'substring' ou 'intervalo_preco'");
			System.out.println("\tparâmetro de filtragem: argumentos adicionais necessários para a filtragem");
			System.out.println();
			System.exit(1);
		}

		Algoritmo algoritmo;
		if (args[0].equals("quick"))
			algoritmo = new QuickSort();

		else if (args[0].equals("insertion"))
			algoritmo = new InsertionSort();

		else {
			algoritmo = null;// colocou um metodo de ordenacao invalido
		}

		Criterio criterio;
		if (args[1].equals("preco_c"))
			criterio = new CriterioPrecoCresc();

		else if (args[1].equals("descricao_c"))
			criterio = new CriterioDescCresc();

		else if (args[1].equals("estoque_c"))
			criterio = new CriterioEstoqueCresc();

		else if (args[1].equals("preco_d"))
			criterio = new CriterioPrecoDecres();

		else if (args[1].equals("descricao_d"))
			criterio = new CriterioDescDecres();

		else if (args[1].equals("estoque_d"))
			criterio = new CriterioEstoqueDecres();
		else {
			criterio = null;// criterio invalido
		}

		Filtro filtro;
		if (args[2].equals("todos"))
			filtro = new FiltroTodos();

		else if (args[2].equals("estoque_menor_igual"))
			filtro = new FiltroEstMenorIgual();
		else if (args[2].equals("categoria_igual"))
			filtro = new FiltroCatIgual();
		else if (args[2].equals("intervalo_preco"))
			filtro = new FiltroIntervalo();
		else if (args[2].equals("substring"))
			filtro = new FiltroSubString();
		else {
			filtro = null;// filtro invalido
		}

		List<String> opcao_parametro_filtro = new ArrayList<>();

		if (!(filtro instanceof FiltroTodos))
			opcao_parametro_filtro.add(args[3]);

		if (filtro instanceof FiltroIntervalo)
			opcao_parametro_filtro.add(args[4]);

		GeradorDeRelatorios gdr = new GeradorDeRelatorios(lerCSV("produtos.csv"),
				algoritmo,
				criterio,
				filtro,
				opcao_parametro_filtro);

		try {
			gdr.geraRelatorio("saida.html");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
