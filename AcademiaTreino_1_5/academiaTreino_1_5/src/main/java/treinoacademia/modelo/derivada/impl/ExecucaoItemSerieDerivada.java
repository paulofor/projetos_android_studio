package treinoacademia.modelo.derivada.impl;

import  treinoacademia.modelo.derivada.*;
import  treinoacademia.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ExecucaoItemSerieDerivada implements ExecucaoItemSerieDerivadaI{ 
  
  	private ExecucaoItemSerie principal;
  
  	/*
  	public ExecucaoItemSerieDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ExecucaoItemSerieDerivada() {
		super();
  	}
  	*/
  	
  	public ExecucaoItemSerieDerivada(ExecucaoItemSerie item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ExecucaoItemSerieDerivada";
	}
}