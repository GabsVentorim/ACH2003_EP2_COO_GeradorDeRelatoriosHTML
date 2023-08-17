import java.io.PrintWriter;

public class FormatoPadrao implements Formato {

    @Override
    public void formatacao(PrintWriter out, Produto p) {
        out.print(p.formataParaImpressao());
    }
}
