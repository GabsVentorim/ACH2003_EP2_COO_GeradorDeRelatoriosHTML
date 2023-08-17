import java.util.List;

public class FiltroSubString implements Filtro {

    @Override
    public boolean filtra(Produto produto, List<String> argFiltro) {
        return produto.getDescricao().toLowerCase().contains(argFiltro.get(0).toLowerCase());
    }
}
