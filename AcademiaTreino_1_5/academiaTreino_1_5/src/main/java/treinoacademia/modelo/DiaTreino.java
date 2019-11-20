package treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import treinoacademia.modelo.derivada.DiaTreinoDerivadaI;
import treinoacademia.modelo.agregado.DiaTreinoAgregadoI;


public interface DiaTreino extends DCIObjetoDominio
		, DiaTreinoDerivadaI, DiaTreinoAgregadoI, DisplayI<DiaTreino>{ 
  
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
	public long getIdDiaTreino();
	public void setIdDiaTreino(long valor);


	public Timestamp getData();
	public void setData(Timestamp valor);
	public String getDataDDMMAAAA();	
	public void setDataDDMMAAAAComBarra(String valor);


	public boolean getConcluido();
	public void setConcluido(boolean valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public long getIdSerieTreinoSd();
	public void setIdSerieTreinoSd(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	public long getIdSerieTreinoSdLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}