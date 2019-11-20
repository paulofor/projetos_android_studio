
package br.com.lojadigicom.coletorprecocliente.modelo;


import android.view.View;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.ContentValues;

import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.PalavraChavePesquisaAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.PalavraChavePesquisaContract;

public class PalavraChavePesquisaVo implements PalavraChavePesquisa  {

	public PalavraChavePesquisaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idPalavraChavePesquisa;
    }

	 // ----- INICIO AGREGADO -----------------
	private PalavraChavePesquisaAgregado agregado = null;
	private PalavraChavePesquisaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PalavraChavePesquisaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idPalavraChavePesquisa;
	public long getIdPalavraChavePesquisa() {
		return idPalavraChavePesquisa;
	}
	public void setIdPalavraChavePesquisa(long _valor) {
		idPalavraChavePesquisa = _valor;
	}


	private String textoBusca;
	public String getTextoBusca() {
		return textoBusca;
	}
	public void setTextoBusca(String _valor) {
		textoBusca = _valor;
	}


	private Timestamp data;
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp _valor) {
		data = _valor;
	}


	public String getDataDDMMAAAA() {
		if (data==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(data);
    }
    public void setDataDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			data = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			data = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }



	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getUsuario_Sincroniza()!=null) 
		//	return getUsuario_Sincroniza().getIdObj();
		//else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
	}

	
	private long idNaturezaProdutoRa;
	public long getIdNaturezaProdutoRa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getNaturezaProduto_ReferenteA()!=null) 
		//	return getNaturezaProduto_ReferenteA().getIdObj();
		//else
			return idNaturezaProdutoRa;
	}
	public void setIdNaturezaProdutoRa(long _valor) {
		idNaturezaProdutoRa = _valor;
	}





	private String operacaoSinc = null;

	public String getOperacaoSinc() {
		return operacaoSinc;
	}

	public void setOperacaoSinc(String valor) {
		operacaoSinc = valor;
	}

	/*
	public JSONObject JSonSinc() throws JSONException {
		JSONObject json = JSonSimples();
		json.put("OperacaoSinc", operacaoSinc);
		return json;
	}
	*/
	
	
	private boolean salvaPreliminar = false;

	public void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}

	public boolean getSalvaPreliminar() {
		return salvaPreliminar;
	}
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
	public long getIdNaturezaProdutoRaLazyLoader() {
		return idNaturezaProdutoRa;
	} 
		
	
	
	private boolean somenteMemoria = true;

	public boolean getSomenteMemoria() {
		return somenteMemoria;
	}

	public void setSomenteMemoria(boolean dado) {
		somenteMemoria = dado;
	} 
	
	
	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza() {
			return getAgregado().getUsuario_Sincroniza();
		}
		public void setUsuario_Sincroniza(Usuario item) {
			// Coloquei em 10-11-2016
			idUsuarioS = item.getIdObj();
			getAgregado().setUsuario_Sincroniza(item);
		}
		
		public void addListaUsuario_Sincroniza(Usuario item) {
			getAgregado().addListaUsuario_Sincroniza(item);
		}
		public Usuario getCorrenteUsuario_Sincroniza() {
			return getAgregado().getCorrenteUsuario_Sincroniza();
		}
		
		
		public NaturezaProduto getNaturezaProduto_ReferenteA() {
			return getAgregado().getNaturezaProduto_ReferenteA();
		}
		public void setNaturezaProduto_ReferenteA(NaturezaProduto item) {
			// Coloquei em 10-11-2016
			idNaturezaProdutoRa = item.getIdObj();
			getAgregado().setNaturezaProduto_ReferenteA(item);
		}
		
		public void addListaNaturezaProduto_ReferenteA(NaturezaProduto item) {
			getAgregado().addListaNaturezaProduto_ReferenteA(item);
		}
		public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA() {
			return getAgregado().getCorrenteNaturezaProduto_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(PalavraChavePesquisaContract.COLUNA_ID_PALAVRA_CHAVE_PESQUISA, idPalavraChavePesquisa);
    	valores.put(PalavraChavePesquisaContract.COLUNA_TEXTO_BUSCA, textoBusca);
    	valores.put(PalavraChavePesquisaContract.COLUNA_DATA, UtilDatas.getDataLong(data));
		valores.put(PalavraChavePesquisaContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		valores.put(PalavraChavePesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_RA, idNaturezaProdutoRa);
	
		return valores;
  	}
}