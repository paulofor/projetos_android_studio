package coletapreco.app;

import android.content.Context;
import coletapreco.app.base.SincronizadorBase;

public class Sincronizador extends SincronizadorBase{

	public void principal(Context contexto) {
		super.sincronizaNaturezaProduto(contexto, true);
		super.sincronizaUsuarioPesquisa(contexto, true);
		super.sincronizaOportunidadeDia(contexto, true);
	}
	
	
	
	
}