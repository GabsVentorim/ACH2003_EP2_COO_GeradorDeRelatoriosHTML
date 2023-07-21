public class CriterioPrecoCresc implements Criterio {

	@Override
	public boolean compara(Produto p1, Produto p2) {
		return p1.getPreco() > p2.getPreco();
	}
}