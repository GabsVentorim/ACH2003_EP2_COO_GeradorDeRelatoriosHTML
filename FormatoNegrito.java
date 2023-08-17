import java.io.PrintWriter;

public class FormatoNegrito extends FormatoDecorator {
    public Formato formato;

    public FormatoNegrito(Formato formato) {
        super(formato);
        this.formato = formato;
    }

    @Override
    public void formatacao(PrintWriter out, Produto p) {
        out.print("<span style=\"font-weight:bold\">");
        formato.formatacao(out, p);
        out.print("</span>");
    }
}
