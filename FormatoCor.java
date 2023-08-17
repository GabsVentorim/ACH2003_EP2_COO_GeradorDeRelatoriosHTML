import java.io.PrintWriter;

public class FormatoCor extends FormatoDecorator {
    public Formato formato;
    public String cor;

    public FormatoCor(Formato formato, String cor) {
        super(formato);
        this.formato = formato;
        this.cor = cor;
    }

    @Override
    public void formatacao(PrintWriter out, Produto p) {
        out.print("<span style=\"color:" + cor + "\">");
        formato.formatacao(out, p);
        out.print("</span>");
    }
}
