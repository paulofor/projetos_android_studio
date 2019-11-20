
package  coletapreco.app.base;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.activity.BaseFragmentActivity;
import br.com.digicom.R;
import android.app.ActionBar;
import coletapreco.app.Sincronizador;
import coletapreco.app.MenuFragment;
//import coletapreco.app.R;
import coletapreco.dao.datasource.DataSourceAplicacao;
import coletapreco.modelo.Usuario;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.servico.FabricaServico;
import coletapreco.servico.UsuarioServico;
import android.os.Bundle;




// Nao passei para a arquitetura porque existem
// muitas referencias a objetos R.* da aplica??o alem de
// referencia a DataSource tambem da aplicacao.
public abstract class PrincipalActivityBase extends BaseFragmentActivity implements MenuFragment.OnButtonClick{

	public abstract void onButtonClickMenu(String acao);
	
	protected void processaActionBar(ActionBar actionBar) {
	}
	
	public void posOnCreate(Bundle savedInstanceState) {
		DataSourceAplicacao.criaInstancia(getApplication());
       	// Rever o processo de sincronizacao ate 02/06/2014
       	if (sincronizaInicio())
       		sincroniza();  
       	this.inicializaServicos();
	}
	
	
	 @Override
     public void onCreate(Bundle savedInstanceState) {
     	super.onCreate(savedInstanceState);
     	ActionBar ab = getActionBar();
       	ab.setBackgroundDrawable(getResources().getDrawable(coletapreco.app.R.drawable.bg_action_bar));
       	processaActionBar(ab);
       	//setTheme(android.R.style.Theme_Light_NoTitleBar);
       	DataSourceAplicacao.criaInstancia(getApplication());
       	// Rever o processo de sincronizacao ate 02/06/2014
       	if (sincronizaInicio())
       		sincroniza();  
       	//atualizaUsuarioReferencia();
       
        // Lay out inicial R.layout.main_frame
       	setContentView(R.layout.main_frame);
		// Iniciar a tela com o MenuFragment
       	if (findViewById(R.id.fragment_container) != null) {
           if (savedInstanceState != null) {
               return;
           }
           Fragment firstFragment = getTelaInicial();
           firstFragment.setArguments(getIntent().getExtras());
           getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
       	}
       	this.inicializaServicos();
    }
	protected boolean sincronizaInicio() {
		return true;
	}
	
	protected Fragment getTelaInicial() {
		return new MenuFragment();
	}
	/*
	private void atualizaUsuarioReferencia() {
		 UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		 Usuario usuario = usuarioSrv.ObtemReferenciaAndroid(getContexto());
		 String pathPadrao = DataSourceAplicacao.getInstancia().getPathPadrao();
		 //if (usuario==null) {
		 //	 usuario = FabricaVo.criaUsuario();
		 //}
		 if (usuario.getMelhorPath()==null || (usuario.getMelhorPath().compareTo(pathPadrao)!=0)) {
			 usuario.setMelhorPath(pathPadrao);
			 //usuarioSrv.alteraParaSincronizacao(usuario);
		 }
	}
	*/

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
	
	// 
	protected void alteraQuadro(IFragment quadro) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container,(Fragment) quadro);
		transaction.addToBackStack(null);
		transaction.commit();
		this.getActionBar().setTitle(quadro.getTituloTela());
	}
	/*
	// Poderia ser criado caso existe a defini??o dos comandos 
	// do Menu - Melhoria no Gerenciador2005
	public void onButtonClick(String acao) {
		if ("Produto".equals(acao))
			chamaProduto();
		if ("Projeto".equals(acao))
			chamaProjeto();
		if ("Sincronizador".equals(acao)) {
			Sincronizador sinc = new Sincronizador();
			sinc.principal(getApplication());
		}
	}
	*/

}