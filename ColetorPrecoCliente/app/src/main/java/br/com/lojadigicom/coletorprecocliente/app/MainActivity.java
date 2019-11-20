package br.com.lojadigicom.coletorprecocliente.app;



import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.framework.util.FileUtil;
import br.com.lojadigicom.coletorprecocliente.gcm.RegistrationIntentService;
import br.com.lojadigicom.coletorprecocliente.sync.AplicacaoAsyncTask;
import br.com.lojadigicom.coletorprecocliente.tela.MainActivityBase;

import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListFragmentBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListViewHolderBase;
import br.com.lojadigicom.coletorprecocliente.util.IabHelper;
import br.com.lojadigicom.coletorprecocliente.util.IabResult;
import br.com.lojadigicom.coletorprecocliente.util.Inventory;
import br.com.lojadigicom.coletorprecocliente.util.Purchase;

public class MainActivity extends MainActivityBase implements  NaturezaProdutoListFragmentBase.Callback{

    // Server API Key = AIzaSyCeTKkmPYEzulo8NsZaNrnujufpHpj6Cqo
    // Sender ID = 428483979615

    @Override
    protected void sincronizacaoManual() {
        //AplicacaoAsyncTask app = new AplicacaoAsyncTask();
        //app.execute(this);
        //FileUtil.copiaArquivo();
        getSupportActionBar().setLogo(R.drawable.ico_filefind_48);
        getSupportActionBar().setTitle("  " + getSupportActionBar().getTitle());
        //montandoCobranca();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, VitrineProdutoActivity.class));
            //startActivity(new Intent(this, NaturezaProdutoListaEscolhaAcvtivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemSelected(int idItemLista, NaturezaProdutoListViewHolderBase vh) {
        // Se for um tablet o tratamento é diferente.

        Intent intent = new Intent(getApplicationContext(), NaturezaProdutoDetalheActivity.class);
        DCLog.d("OnClick", this, "Item selecionado ID:" + idItemLista);
        intent.putExtra(Constantes.NATUREZA_PRODUTO_ID,idItemLista);
        if (idItemLista==0) {
            throw new RuntimeException("Id igual a zero");
        }
        //ActivityOptionsCompat activityOptions =
        //        ActivityOptionsCompat.makeSceneTransitionAnimation(this,
        //                new Pair<View, String>(vh.mIconView, getString(R.string.detail_icon_transition_name)));
        //ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        this.startActivity(intent);
    }

    @Override
    protected Class<?> getRegistrationIntentService() {
      return RegistrationIntentService.class;
    }

    @Override
    protected ResourceObj getActivityMainResource() {
        return new ResourceObj(R.layout.activity_main,"R.layout.activity_main");
    }



    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

}
