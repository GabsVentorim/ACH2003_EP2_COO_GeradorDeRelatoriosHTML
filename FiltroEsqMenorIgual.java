import java.util.List;

public class FiltroEsqMenorIgual implements Filtro{

    @Override
    public void filtra(Produto produto, String argFiltro, boolean selecionado) {
        if(produto.getQtdEstoque() <= Integer.parseInt(argFiltro))
            selecionado = true;
    }

    @Override
    public boolean filtra(Produto produto, List<String> argFiltro) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtra'");
    }
    
}
 