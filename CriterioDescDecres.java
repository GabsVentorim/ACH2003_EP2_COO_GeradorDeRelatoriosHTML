public class CriterioDescDecres implements Criterio {

	@Override
	public boolean compara(Produto p1, Produto p2) {
		return p1.getDescricao().compareToIgnoreCase(p2.getDescricao()) < 0;
	}
}