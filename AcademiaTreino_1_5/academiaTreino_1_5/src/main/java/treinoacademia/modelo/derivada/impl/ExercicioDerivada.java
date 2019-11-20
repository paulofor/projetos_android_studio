package treinoacademia.modelo.derivada.impl;

import  treinoacademia.modelo.derivada.*;
import  treinoacademia.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ExercicioDerivada implements ExercicioDerivadaI{ 
  
  	private Exercicio principal;
  
  	/*
  	public ExercicioDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ExercicioDerivada() {
		super();
  	}
  	*/
  	
  	public ExercicioDerivada(Exercicio item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return principal.getTitulo();
	}
}