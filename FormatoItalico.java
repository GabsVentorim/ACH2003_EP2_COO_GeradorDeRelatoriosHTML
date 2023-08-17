import java.io.PrintWriter;

public class FormatoItalico extends FormatoDecorator {
    public Formato formato;

    public FormatoItalico(Formato formato) {
        super(formato);
        this.formato = formato;
    }

    @Override
    public void formatacao(PrintWriter out, Produto p) {
        out.print("<span style=\"font-style:italic\">");
        formato.formatacao(out, p);
        out.print("</span>");
    }
}