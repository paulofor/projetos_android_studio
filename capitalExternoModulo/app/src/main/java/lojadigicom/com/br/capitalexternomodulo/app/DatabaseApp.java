package lojadigicom.com.br.capitalexternomodulo.app;


import br.com.lojadigicom.capitalexterno.data.DatabaseConst;


public class DatabaseApp  extends DatabaseConst {
    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public String getName() {
        return "capitalexterno.db";
    }
}
