import java.util.List;

public class FiltroCatIgual implements Filtro{

    @Override
    public boolean filtra(Produto produto, List<String> argFiltro) {
        return produto.getCategoria().equalsIgnoreCase(argFiltro.get(0));
    }
    
}
