package treinoacademia.app;

import treinoacademia.app.base.SincronizadorBase;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.servico.FabricaServico;
import android.content.Context;

public class Sincronizador extends SincronizadorBase{

	public void principal(Context contexto) {
		this.sincronizaErroException(contexto, false);
		this.sincronizaCargaPlanejada(contexto, false);
		this.sincronizaExercicio(contexto, false);
		this.sincronizaDiaTreino(contexto, true);
		this.sincronizaItemSerie(contexto, false);
		this.sincronizaExecucaoItemSerie(contexto, true);
		this.sincronizaSerieTreino(contexto, false); 
		this.sincronizaGrupoMuscular(contexto, false);
		
	}
	
	
	
	
}
