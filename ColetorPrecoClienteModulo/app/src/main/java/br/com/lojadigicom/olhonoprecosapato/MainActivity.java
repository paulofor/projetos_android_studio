package br.com.lojadigicom.olhonoprecosapato;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import br.com.lojadigicom.coletorprecocliente.app.AdmAssinaturaClienteActivity;
import br.com.lojadigicom.coletorprecocliente.app.ListaEsperaActivity;
import br.com.lojadigicom.coletorprecocliente.app.ListaMonitoramentoActivity;
import br.com.lojadigicom.coletorprecocliente.app.VitrineProdutoEscolhaActivity;
import br.com.lojadigicom.coletorprecocliente.app.VitrineProdutoEscolhaNovo;
import br.com.lojadigicom.olhonoprecomodulo.R;


public class MainActivity extends VitrineProdutoEscolhaActivity {


    @Override
    protected void complementaOnCreate() {
        super.complementaOnCreate();
        this.existeBack = false;
        this.titulo = "Olho no Preço";

        //SincronizacaoCargaTask task = new SincronizacaoCargaTask();
        //task.execute(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuEspera) {
            Intent mIntent = new Intent(this, ListaEsperaActivity.class);
            startActivity(mIntent);
            return true;
        }
        if (id == R.id.menuMonitora) {
            Intent mIntent = new Intent(this, ListaMonitoramentoActivity.class);
            startActivity(mIntent);
            return true;
        }
        if (id == R.id.menuCompra) {
            Intent mIntent = new Intent(this, AdmAssinaturaClienteActivity.class);
            startActivity(mIntent);
            return true;
        }
        if (id == R.id.vitrineProduto2) {
            Intent mIntent = new Intent(this, VitrineProdutoEscolhaNovo.class);
            startActivity(mIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
