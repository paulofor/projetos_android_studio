package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.InteresseProdutoAgregadoI;

public interface InteresseProduto  extends DCIObjetoDominio
	, InteresseProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdInteresseProduto();
	public void setIdInteresseProduto(long valor);


	public long getNota();
	public void setNota(long valor);


	public Timestamp getData();
	public void setData(Timestamp valor);
	public String getDataDDMMAAAA();


	public String getDataHHMM();
	public String getDataHHMMSS();
	public float getValor();
	public void setValor(float valor);
	
	public String getValorTela();

	public boolean getEspera();
	public void setEspera(boolean valor);


	public boolean getMonitora();
	public void setMonitora(boolean valor);


	public boolean getVisualizacaoConcluida();
	public void setVisualizacaoConcluida(boolean valor);


	public float getPrecoMedio();
	public void setPrecoMedio(float valor);
	
	public String getPrecoMedioTela();

	public float getPrecoMinimo();
	public void setPrecoMinimo(float valor);
	
	public String getPrecoMinimoTela();

	public boolean getNovo();
	public void setNovo(boolean valor);


	public boolean getMudanca();
	public void setMudanca(boolean valor);


	public float getDiferencaAnterior();
	public void setDiferencaAnterior(float valor);
	
	public String getDiferencaAnteriorTela();

	public long getMinhaAvaliacao();
	public void setMinhaAvaliacao(long valor);


	public Timestamp getDataUltimaMudanca();
	public void setDataUltimaMudanca(Timestamp valor);
	public String getDataUltimaMudancaDDMMAAAA();


	public String getDataUltimaMudancaHHMM();
	public String getDataUltimaMudancaHHMMSS();
	public float getPrecoAnterior();
	public void setPrecoAnterior(float valor);
	
	public String getPrecoAnteriorTela();

	public Timestamp getDataUltimaVerificacao();
	public void setDataUltimaVerificacao(Timestamp valor);
	public String getDataUltimaVerificacaoDDMMAAAA();


	public String getDataUltimaVerificacaoHHMM();
	public String getDataUltimaVerificacaoHHMMSS();
	public long getIdProdutoClienteRa();
	public void setIdProdutoClienteRa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoClienteRaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}