package br.com.lojadigicom.olhonomedicamento.aplicacao;

import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;

/**
 * Created by Paulo on 28/09/2016.
 */
public class DatabaseApp extends DatabaseConst {
    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getName() {
        return "olhonoprecosapatos.db";
    }
}