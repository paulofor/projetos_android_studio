package br.com.lojadigicom.coletorprecocliente.data.provider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;

/**
 * Created by Paulo on 05/04/2016.
 */
public class InteresseProdutoConsulta extends InteresseProdutoProvider {



    @Override
    protected String queryListaEspera(String param) {
        String sql = "select "  + InteresseProdutoContract.camposOrdenados() + " , " +
                ProdutoClienteContract.camposOrdenados() + " from " +
                InteresseProdutoContract.TABLE_NAME +
                InteresseProdutoContract.outerJoinProdutoCliente_ReferenteA() +
                " where " + InteresseProdutoContract.COLUNA_ESPERA;
        return sql;
    }

    @Override
    protected String queryListaMonitora(String param) {
        String sql = "select "  + InteresseProdutoContract.camposOrdenados() + " , " +
                ProdutoClienteContract.camposOrdenados() + " from " +
                InteresseProdutoContract.TABLE_NAME +
                InteresseProdutoContract.outerJoinProdutoCliente_ReferenteA() +
                " where " + InteresseProdutoContract.COLUNA_MONITORA;
        return sql;
    }

    @Override
    protected String queryObtemComProduto(String param) {
        String sql = "select "  + InteresseProdutoContract.camposOrdenados() + " , " +
                ProdutoClienteContract.camposOrdenados() + " from " +
                InteresseProdutoContract.TABLE_NAME +
                InteresseProdutoContract.outerJoinProdutoCliente_ReferenteA() +
                " where " + InteresseProdutoContract.COLUNA_MONITORA;
        return sql;
    }

    @Override
    protected String queryQuantidadeMonitorado(String param) {
        return " select " +
                "(select count(*) from " + InteresseProdutoContract.TABLE_NAME +
                " where " + InteresseProdutoContract.COLUNA_MONITORA + ") as qtde_monitora, " +
                "(select count(*) from " + InteresseProdutoContract.TABLE_NAME +
                " where " + InteresseProdutoContract.COLUNA_ESPERA + ") as qtde_espera ";
    }



    @Override
    protected String queryQuantidadeMudanca(String param) {
        return "select count(*) as qtde_mudanca from " + InteresseProdutoContract.TABLE_NAME +
                " where " + InteresseProdutoContract.COLUNA_MUDANCA  + " and " +
                InteresseProdutoContract.COLUNA_MONITORA;
    }

    @Override
    protected void notificaOutrasUri(ContentResolver resolver) {
        resolver.notifyChange(ProdutoClienteContract.buildObtemProximoNaoEscolhido(),null);
    }
}
