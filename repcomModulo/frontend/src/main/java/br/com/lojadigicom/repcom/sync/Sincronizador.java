package br.com.lojadigicom.repcom.sync;


import android.content.Context;

import br.com.lojadigicom.repcom.framework.fcm.DCSincronizador;
import br.com.lojadigicom.repcom.framework.tela.DCAplicacao;

public class Sincronizador extends DCSincronizador {


    public Sincronizador(DCAplicacao app) {
        super(app);
    }

    @Override
    protected void sincronizacaoDiaria(Context ctx, String codigoAplicacao) {

    }

    @Override
    public void sincronizacaoUpgradeBd() {

    }

    @Override
    protected void carregaImagens(Context ctx, String codigoAplicacao) {

    }
}
