import java.util.List;

public class FiltroSubString implements Filtro{

    @Override
    public boolean filtra(Produto produto, List<String> argFiltro) {
        return produto.getDescricao().contains(argFiltro.get(0));
    }
}
