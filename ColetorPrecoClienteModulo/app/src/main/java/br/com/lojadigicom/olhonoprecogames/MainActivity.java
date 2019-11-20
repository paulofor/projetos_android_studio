package br.com.lojadigicom.olhonoprecogames;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import br.com.lojadigicom.coletorprecocliente.app.NaturezaProdutoDetalheImagemActivity;
import br.com.lojadigicom.coletorprecocliente.sync.SincronizacaoCargaTask;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.olhonoprecomodulo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("message");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_principal);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //inicializaSincronizador();
        SincronizacaoCargaTask task = new SincronizacaoCargaTask();
        task.execute(this);


        dialog.hide();
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
        if (id == R.id.action_settings) {
            Intent mIntent = new Intent(this, NaturezaProdutoDetalheImagemActivity.class);
            mIntent.putExtra(Constantes.NATUREZA_PRODUTO_ID, 17);
            mIntent.putExtra(Constantes.CHAVE_TOOLBAR, "Oportunidade do Dia");
            //Intent mIntent = new Intent(this, VitrineProdutoActivity.class);

            startActivity(mIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
