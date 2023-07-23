import java.util.List;

public class FiltroTodos implements Filtro {

    @Override
    public boolean filtra(Produto produto, List<String> argFiltro) {
        return true;
    }
}
