package treinoacademia.modelo.display.impl;

import treinoacademia.modelo.ExecucaoItemSerie;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class ExecucaoItemSerieDisplay extends ObjetoDisplay<ExecucaoItemSerie> implements DisplayI<ExecucaoItemSerie> { 
  
  	public ExecucaoItemSerieDisplay(ExecucaoItemSerie item) {
		super(item);
	}

	@Override
	public String getItemListaTexto() {
		return 	String.format( "%3s", getPrincipal().getQuantidadeRepeticao()) + " x " + 
				String.format("%s", this.getPrincipal().getCargaUtilizadaTela()) + "   :   " + 
				this.getPrincipal().getDataHoraFinalizacaoHHMMSS();
	} 
  	
  	
  	
  	
  	//execucao.getQuantidadeRepeticao() + " x " + execucao.getCargaUtilizadaTela() + " : " + execucao.getDataHoraFinalizacaoHHMMSS());
  	
  }