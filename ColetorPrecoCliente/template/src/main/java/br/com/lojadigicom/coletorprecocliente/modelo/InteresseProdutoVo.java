
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
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.InteresseProdutoAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;

public class InteresseProdutoVo implements InteresseProduto  {

	public InteresseProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idInteresseProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private InteresseProdutoAgregado agregado = null;
	private InteresseProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new InteresseProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idInteresseProduto;
	public long getIdInteresseProduto() {
		return idInteresseProduto;
	}
	public void setIdInteresseProduto(long _valor) {
		idInteresseProduto = _valor;
	}


	private long nota;
	public long getNota() {
		return nota;
	}
	public void setNota(long _valor) {
		nota = _valor;
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




	public String getDataHHMM() {
		if (data==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(data);
    }
    public String getDataHHMMSS() {
		if (data==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(data);
    }
	private float valor;
	public float getValor() {
		return valor;
	}
	public void setValor(float _valor) {
		valor = _valor;
	}
	
	public String getValorTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valor);
		return saida;
	}
	
	private long idProdutoClienteRa;
	public long getIdProdutoClienteRa() {
		// relacionamento
		//if (idProdutoClienteRa==0 && this.getProdutoCliente_ReferenteA()!=null) 
		//	return getProdutoCliente_ReferenteA().getId();
		//else
			return idProdutoClienteRa;
	}
	public void setIdProdutoClienteRa(long _valor) {
		idProdutoClienteRa = _valor;
	}

	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento
		//if (idUsuarioS==0 && this.getUsuario_Sincroniza()!=null) 
		//	return getUsuario_Sincroniza().getId();
		//else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
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
  	
	public long getIdProdutoClienteRaLazyLoader() {
		return idProdutoClienteRa;
	} 
		
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
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
  	
		public ProdutoCliente getProdutoCliente_ReferenteA() {
			return getAgregado().getProdutoCliente_ReferenteA();
		}
		public void setProdutoCliente_ReferenteA(ProdutoCliente item) {
			getAgregado().setProdutoCliente_ReferenteA(item);
		}
		
		public void addListaProdutoCliente_ReferenteA(ProdutoCliente item) {
			getAgregado().addListaProdutoCliente_ReferenteA(item);
		}
		public ProdutoCliente getCorrenteProdutoCliente_ReferenteA() {
			return getAgregado().getCorrenteProdutoCliente_ReferenteA();
		}
		
		
		public Usuario getUsuario_Sincroniza() {
			return getAgregado().getUsuario_Sincroniza();
		}
		public void setUsuario_Sincroniza(Usuario item) {
			getAgregado().setUsuario_Sincroniza(item);
		}
		
		public void addListaUsuario_Sincroniza(Usuario item) {
			getAgregado().addListaUsuario_Sincroniza(item);
		}
		public Usuario getCorrenteUsuario_Sincroniza() {
			return getAgregado().getCorrenteUsuario_Sincroniza();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(InteresseProdutoContract.COLUNA_ID_INTERESSE_PRODUTO, idInteresseProduto);
    	valores.put(InteresseProdutoContract.COLUNA_NOTA, nota);
    	valores.put(InteresseProdutoContract.COLUNA_DATA, UtilDatas.getDataHoraLong(data));
    	valores.put(InteresseProdutoContract.COLUNA_VALOR, valor);
		valores.put(InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA, idProdutoClienteRa);
	
		valores.put(InteresseProdutoContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}