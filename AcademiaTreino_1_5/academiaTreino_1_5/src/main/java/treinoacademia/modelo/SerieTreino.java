package treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import treinoacademia.modelo.derivada.SerieTreinoDerivadaI;
import treinoacademia.modelo.agregado.SerieTreinoAgregadoI;


public interface SerieTreino extends DCIObjetoDominio
		, SerieTreinoDerivadaI, SerieTreinoAgregadoI, DisplayI<SerieTreino>{ 
  
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
	public long getIdSerieTreino();
	public void setIdSerieTreino(long valor);


	public long getQtdeExecucao();
	public void setQtdeExecucao(long valor);


	public boolean getAtiva();
	public void setAtiva(boolean valor);


	public Timestamp getDataCriacao();
	public void setDataCriacao(Timestamp valor);
	public String getDataCriacaoDDMMAAAA();	
	public void setDataCriacaoDDMMAAAAComBarra(String valor);


	public Timestamp getDataUltimaExecucao();
	public void setDataUltimaExecucao(Timestamp valor);
	public String getDataUltimaExecucaoDDMMAAAA();	
	public void setDataUltimaExecucaoDDMMAAAAComBarra(String valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}