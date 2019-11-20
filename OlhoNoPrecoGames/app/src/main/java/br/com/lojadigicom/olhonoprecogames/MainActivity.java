package br.com.lojadigicom.olhonoprecogames;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.lojadigicom.coletorprecocliente.app.NaturezaProdutoListaEscolhaAcvtivity;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.sincronismo.SincronismoBaseTask;
import br.com.lojadigicom.coletorprecocliente.framework.util.FileUtil;
//import br.com.lojadigicom.coletorprecocliente.sync.SincronismoInicialTask;
import br.com.lojadigicom.coletorprecocliente.sync.SincronizacaoSetupTask;
import br.com.lojadigicom.coletorprecocliente.tela.MainActivityBaseSemGcm;


public class MainActivity extends AppCompatActivity implements SincronismoBaseTask.CallbackSincronismo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Olho no Preço - Games");
        setSupportActionBar(toolbar);

        SincronizacaoSetupTask app = new SincronizacaoSetupTask();
        app.setCallback(this);
        app.execute(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            chamaTelaEscolha();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConcluido() {
        FileUtil.copiaArquivo();
        verificaNaturezaEscolhida();

    }

    private void verificaNaturezaEscolhida() {
        Uri uriInteresseNatureza = NaturezaProdutoContract.buildAll();
        Cursor cursor = this.getContentResolver().query(uriInteresseNatureza,null,null,null,null);
        if (!cursor.moveToNext()) {
            chamaTelaEscolha();
        }
    }

    private void chamaTelaEscolha() {
        Intent mIntent = new Intent(this, NaturezaProdutoListaEscolhaAcvtivity.class);
        mIntent.putExtra(NaturezaProdutoListaEscolhaAcvtivity.CHAVE_TOOLBAR, "Escolha Categoria");
        startActivity(mIntent);
        overridePendingTransition(R.anim.entrar_descendo, R.anim.sair_descendo);
    }
}
