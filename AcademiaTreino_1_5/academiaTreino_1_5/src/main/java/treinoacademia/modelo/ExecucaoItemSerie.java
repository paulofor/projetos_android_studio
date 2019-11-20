package treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import treinoacademia.modelo.derivada.ExecucaoItemSerieDerivadaI;
import treinoacademia.modelo.agregado.ExecucaoItemSerieAgregadoI;


public interface ExecucaoItemSerie extends DCIObjetoDominio
		, ExecucaoItemSerieDerivadaI, ExecucaoItemSerieAgregadoI, DisplayI<ExecucaoItemSerie>{ 
  
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
	public long getIdExecucaoItemSerie();
	public void setIdExecucaoItemSerie(long valor);


	public Timestamp getDataHoraInicio();
	public void setDataHoraInicio(Timestamp valor);
	public String getDataHoraInicioDDMMAAAA();


	public String getDataHoraInicioHHMM();
	public String getDataHoraInicioHHMMSS();
	public float getCargaUtilizada();
	public void setCargaUtilizada(float valor);
	
	public String getCargaUtilizadaTela();

	public boolean getSucessoRepeticoes();
	public void setSucessoRepeticoes(boolean valor);


	public long getNumeroSerie();
	public void setNumeroSerie(long valor);


	public Timestamp getDataHoraFinalizacao();
	public void setDataHoraFinalizacao(Timestamp valor);
	public String getDataHoraFinalizacaoDDMMAAAA();


	public String getDataHoraFinalizacaoHHMM();
	public String getDataHoraFinalizacaoHHMMSS();
	public long getQuantidadeRepeticao();
	public void setQuantidadeRepeticao(long valor);


	public long getIdItemSerieRa();
	public void setIdItemSerieRa(long valor);


	public long getIdDiaTreinoE();
	public void setIdDiaTreinoE(long valor);


	public long getIdExercicioRa();
	public void setIdExercicioRa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdItemSerieRaLazyLoader(); 
		
	public long getIdDiaTreinoELazyLoader(); 
		
	public long getIdExercicioRaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}