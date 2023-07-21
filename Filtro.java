import java.util.List;

public interface Filtro {

    public boolean filtra(Produto produto, List<String> argFiltro);
}