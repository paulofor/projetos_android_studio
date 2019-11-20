package treinoacademia.modelo.derivada.impl;

import  treinoacademia.modelo.derivada.*;
import  treinoacademia.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ItemSerieDerivada implements ItemSerieDerivadaI{ 
  
  	private ItemSerie principal;
  
  	/*
  	public ItemSerieDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ItemSerieDerivada() {
		super();
  	}
  	*/
  	
  	public ItemSerieDerivada(ItemSerie item) {
  		principal = item;
  	}

  	
	@Override
	public void setConcluidoNoDia(boolean valor) {
	}

	@Override
	public boolean getConcluidoNoDia() {
		return (this.principal.getListaExecucaoItemSerie_GeraOriginal()!=null &&
				this.principal.getListaExecucaoItemSerie_GeraOriginal().size() > 1);
	}
	
	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ItemSerieDerivada";
	}
}