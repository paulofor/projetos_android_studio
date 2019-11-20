package br.com.lojadigicom.treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.treinoacademia.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.treinoacademia.modelo.agregado.GrupoMuscularAgregadoI;

public interface GrupoMuscular  extends DCIObjetoDominio
	, GrupoMuscularAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdGrupoMuscular();
	public void setIdGrupoMuscular(long valor);


	public String getNomeGrupo();
	public void setNomeGrupo(String valor);


	public String getImagemGrupo();
	public void setImagemGrupo(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}