import java.util.List;

public class InsertionSort implements Algoritmo {

	@Override
	public void ordena(List<Produto> produtos, Criterio criterio, int ini, int fim) {

		for (int i = ini; i <= fim; i++) {

			Produto inicial = produtos.get(i);// mudar o nome dessa variavel
			int j = (i - 1);

			while (j >= ini) {
 
				if (criterio.compara(produtos.get(j), inicial)) {
					produtos.set(j + 1, produtos.get(j));
					j--;
				} else
					break;

				produtos.set(j + 1, inicial);
			}

		}

	}
}
