package treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import treinoacademia.modelo.derivada.ExercicioDerivadaI;
import treinoacademia.modelo.agregado.ExercicioAgregadoI;


public interface Exercicio extends DCIObjetoDominio
		, ExercicioDerivadaI, ExercicioAgregadoI, DisplayI<Exercicio>{ 
  
  	/**
 	* @deprecated  Substituir por JSonSimples()
 	*/
	//@Deprecated
  	//public JSONObject JSon() throws JSONException;
  	public JSONObject JSonSimples() throws JSONException;
  	public String debug();
  	public DigicomContexto getContexto();
  	public void setContexto(DigicomContexto ctx);
  	public String toString();
	public long getIdExercicio();
	public void setIdExercicio(long valor);


	public String getDescricaoExercicio();
	public void setDescricaoExercicio(String valor);


	public String getImagem();
	public void setImagem(String valor);


	public String getTitulo();
	public void setTitulo(String valor);


	public String getSubtitulo();
	public void setSubtitulo(String valor);


	public long getIdGrupoMuscularP();
	public void setIdGrupoMuscularP(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdGrupoMuscularPLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}