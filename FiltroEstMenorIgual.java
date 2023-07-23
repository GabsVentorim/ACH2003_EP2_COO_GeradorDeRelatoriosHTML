import java.util.List;

public class FiltroEstMenorIgual implements Filtro{

    @Override
    public boolean filtra(Produto produto, List<String> argFiltro) {
        return produto.getQtdEstoque() <= Integer.parseInt(argFiltro.get(0));
    }
}
