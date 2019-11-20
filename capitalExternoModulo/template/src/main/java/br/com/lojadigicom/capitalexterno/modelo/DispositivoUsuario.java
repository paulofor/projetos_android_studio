package br.com.lojadigicom.capitalexterno.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.agregado.DispositivoUsuarioAgregadoI;

public interface DispositivoUsuario  extends DCIObjetoDominio
	, DispositivoUsuarioAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdDispostivoUsuario();
	public void setIdDispostivoUsuario(long valor);


	public String getNumeroCelular();
	public void setNumeroCelular(String valor);


	public String getCodigoDispositivo();
	public void setCodigoDispositivo(String valor);


	public String getTipoAcesso();
	public void setTipoAcesso(String valor);


	public String getMelhorPath();
	public void setMelhorPath(String valor);


	public String getTokenGcm();
	public void setTokenGcm(String valor);


	public long getMicroSd();
	public void setMicroSd(long valor);


	public Timestamp getDataCriacao();
	public void setDataCriacao(Timestamp valor);
	public String getDataCriacaoDDMMAAAA();


	public String getDataCriacaoHHMM();
	public String getDataCriacaoHHMMSS();
	public Timestamp getDataUltimoAcesso();
	public void setDataUltimoAcesso(Timestamp valor);
	public String getDataUltimoAcessoDDMMAAAA();


	public String getDataUltimoAcessoHHMM();
	public String getDataUltimoAcessoHHMMSS();
	public long getIdUsuarioRa();
	public void setIdUsuarioRa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioRaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}