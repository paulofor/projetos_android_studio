package br.com.lojadigicom.coletorprecocliente.aplicacao;


import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;

/**
 * Created by Paulo on 18/06/2016.
 */
public class DatabaseApp  extends DatabaseConst {
    @Override
    public int getVersion() {
        return 7;
    }

    @Override
    public String getName() {
        return "coletorprecocliente.db";
    }
}
