package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.UsuarioAgregadoI;

public interface Usuario  extends DCIObjetoDominio
	, UsuarioAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdUsuario();
	public void setIdUsuario(long valor);


	public String getNomeUsuario();
	public void setNomeUsuario(String valor);


	public String getEmail();
	public void setEmail(String valor);


	public boolean getPlano01();
	public void setPlano01(boolean valor);


	public boolean getPlano02();
	public void setPlano02(boolean valor);


	public boolean getPlano03();
	public void setPlano03(boolean valor);


	public boolean getPlano04();
	public void setPlano04(boolean valor);


	public boolean getPlano05();
	public void setPlano05(boolean valor);


	public Timestamp getDataUltimaAlteracao();
	public void setDataUltimaAlteracao(Timestamp valor);
	public String getDataUltimaAlteracaoDDMMAAAA();


	public String getDataUltimaAlteracaoHHMM();
	public String getDataUltimaAlteracaoHHMMSS();
	public boolean getPermiteSincronizar();
	public void setPermiteSincronizar(boolean valor);


	public String getCodigoExterno();
	public void setCodigoExterno(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}