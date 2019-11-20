
package  treinoacademia.app.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.activity.BaseFragmentActivity;
import br.com.digicom.R;
import android.app.ActionBar;
import treinoacademia.app.Sincronizador;
//import treinoacademia.app.R;
import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.modelo.Usuario;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.UsuarioServico;
import android.os.Bundle;
import br.com.digicom.activity.BaseNavigatorActivity;


import android.support.v4.app.FragmentManager;


// Nao passei para a arquitetura porque existem
// muitas referencias a objetos R.* da aplica??o alem de
// referencia a DataSource tambem da aplicacao.
public abstract class PrincipalActivityBase extends BaseNavigatorActivity {

	
	public void posOnCreate(Bundle savedInstanceState) {
		DataSourceAplicacao.criaInstancia(getApplication());
       	// Rever o processo de sincronizacao ate 02/06/2014
       	if (sincronizaInicio())
       		sincroniza();  
       	this.inicializaServicos();
       	/*
       	 	getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                IFragment fragment = (IFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_principal);;
                if (fragment!=null && getActionBar()!=null) getActionBar().setTitle(fragment.getTituloTela());
            }
        });
        */
	}
 
	protected boolean sincronizaInicio() {
		return false;
	}
	
	protected void sincroniza() {
		Sincronizador sinc = new Sincronizador();
		sinc.setContexto(getApplication());
		sinc.start();
		try {
			sinc.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}