package br.com.digicom.animacao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import br.com.digicom.R;
import br.com.digicom.quadro.IFragment;

public class TrocaQuadro {
	
	
	private static TrocaQuadro instancia = null;
	
	public static TrocaQuadro getInstancia() {
		if (instancia==null) {
			instancia = new TrocaQuadro();
		}
		return instancia;
	}

	public void alteraQuadroPrincipal(IFragment novoQuadro, FragmentActivity tela) {
		FragmentTransaction transaction = tela.getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_principal,(Fragment) novoQuadro);
		transaction.addToBackStack(null);
		transaction.commit();
		tela.getActionBar().setTitle(novoQuadro.getTituloTela());
	}
	
	// Alteraçao de um campo de item de lista para detalhe no quadro seguinte.
	public void alteraQuadroListaParaDetalhe(IFragment novoQuadro, FragmentActivity tela) {
		FragmentTransaction transaction = tela.getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_principal,(Fragment) novoQuadro);
		transaction.addToBackStack(null);
		transaction.commit();
		tela.getActionBar().setTitle(novoQuadro.getTituloTela());
	}
}
