import java.io.PrintWriter;

public abstract class FormatoDecorator implements Formato {

    public Formato formato;

    public FormatoDecorator(Formato formato) {
        this.formato = formato;
    }

    @Override
    public void formatacao(PrintWriter out, Produto p) {
        formato.formatacao(out, p);
    }
}
