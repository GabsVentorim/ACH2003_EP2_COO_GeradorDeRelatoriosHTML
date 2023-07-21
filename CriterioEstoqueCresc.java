public class CriterioEstoqueCresc implements Criterio {

	@Override
	public boolean compara(Produto p1, Produto p2) {
		return p1.getQtdEstoque() > p2.getQtdEstoque();
	}
}