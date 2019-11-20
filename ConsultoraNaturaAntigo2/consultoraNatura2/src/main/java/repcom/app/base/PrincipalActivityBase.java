
package  repcom.app.base;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.activity.BaseFragmentActivity;
import br.com.digicom.R;
import android.app.ActionBar;
import repcom.app.Sincronizador;
//import repcom.app.R;
import repcom.dao.datasource.DataSourceAplicacao;
import repcom.modelo.Usuario;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.FabricaServico;
import repcom.servico.UsuarioServico;
import android.os.Bundle;
import br.com.digicom.activity.BaseNavigatorActivity;




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
	}
 
	protected boolean sincronizaInicio() {
		return true;
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