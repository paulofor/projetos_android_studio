package br.com.lojadigicom.precomedmodulo.app;


import br.com.lojadigicom.precomed.data.DatabaseConst;

public class DatabaseApp  extends DatabaseConst {
    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getName() {
        return "precomed.db";
    }
}
