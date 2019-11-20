package br.com.lojadigicom.naturarepcom.aplicacao;


import br.com.lojadigicom.repcom.data.DatabaseConst;

/**
 * Created by Paulo on 18/06/2016.
 */
public class DatabaseApp  extends DatabaseConst {
    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getName() {
        return "repcom.db";
    }
}
