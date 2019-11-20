package br.com.lojadigicom.olhonoprecogames;


import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;

/**
 * Created by Paulo on 18/06/2016.
 */
public class DatabaseApp extends DatabaseConst {
    @Override
    public int getVersion() {
        return 13;
    }

    @Override
    public String getName() {
        return "olhonoprecogames.db";
    }
}
