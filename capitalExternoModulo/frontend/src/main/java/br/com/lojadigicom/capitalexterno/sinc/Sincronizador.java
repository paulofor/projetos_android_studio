package br.com.lojadigicom.capitalexterno.sinc;

import android.content.Context;

import br.com.lojadigicom.capitalexterno.framework.fcm.DCSincronizador;
import br.com.lojadigicom.capitalexterno.framework.tela.DCAplicacao;

/**
 * Created by Paulo on 26/10/2016.
 */

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
