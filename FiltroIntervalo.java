import java.util.Collections;
import java.util.List;

public class FiltroIntervalo implements Filtro{

    @Override
    public boolean filtra(Produto produto, List<String> argFiltro) {//se estiver no intervalo, filtrar 
        if(Double.parseDouble(argFiltro.get(0)) > Double.parseDouble(argFiltro.get(1)))
            Collections.swap(argFiltro, 0, 1);

        return produto.getPreco() >= Double.parseDouble(argFiltro.get(0)) && produto.getPreco() <= Double.parseDouble(argFiltro.get(1));
    }
    
}
