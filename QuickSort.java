

import java.util.Collections;
import java.util.List;

public class QuickSort implements Algoritmo {

    private int particiona(List<Produto> produtos, Criterio criterio, int ini, int fim) {

		int i = (ini - 1);
		int j = (fim + 1);

		while (true) {
        do {
            j--;
        } while (criterio.compara(produtos.get(j), produtos.get(ini)));

        do {
            i++;
        } while (criterio.compara(produtos.get(ini), produtos.get(i)));

			if (i < j)
				Collections.swap(produtos, i, j);
			else
				break;
		}
		return j;
	}
    
    @Override
    public void ordena(List<Produto> produtos, Criterio criterio, int ini, int fim) {
        if (ini < fim) {
            int q = particiona(produtos, criterio, ini, fim);//ver oq faco com isso
            ordena(produtos, criterio, ini, q);
            ordena(produtos, criterio, q + 1, fim);
        }
    }

}
