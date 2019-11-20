
package br.com.lojadigicom.repcom.modelo;


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

import br.com.lojadigicom.repcom.framework.util.UtilDatas;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.agregado.VendaAgregado;
import br.com.lojadigicom.repcom.data.contract.VendaContract;

public class VendaVo implements Venda  {

	public VendaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idVenda;
    }

	 // ----- INICIO AGREGADO -----------------
	private VendaAgregado agregado = null;
	private VendaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new VendaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idVenda;
	public long getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(long _valor) {
		idVenda = _valor;
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




	private float valorTotal;
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float _valor) {
		valorTotal = _valor;
	}
	
	public String getValorTotalTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valorTotal);
		return saida;
	}

	private boolean vendaFechada;
	public boolean getVendaFechada() {
		return vendaFechada;
	}
	public void setVendaFechada(boolean _valor) {
		vendaFechada = _valor;
	}

	
	private long idClienteFp;
	public long getIdClienteFp() {
		// relacionamento
		//if (idClienteFp==0 && this.getCliente_FeitaPara()!=null) 
		//	return getCliente_FeitaPara().getId();
		//else
			return idClienteFp;
	}
	public void setIdClienteFp(long _valor) {
		idClienteFp = _valor;
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
  	
	public long getIdClienteFpLazyLoader() {
		return idClienteFp;
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
  	
		public Cliente getCliente_FeitaPara() {
			return getAgregado().getCliente_FeitaPara();
		}
		public void setCliente_FeitaPara(Cliente item) {
			getAgregado().setCliente_FeitaPara(item);
		}
		
		public void addListaCliente_FeitaPara(Cliente item) {
			getAgregado().addListaCliente_FeitaPara(item);
		}
		public Cliente getCorrenteCliente_FeitaPara() {
			return getAgregado().getCorrenteCliente_FeitaPara();
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
	
		public ProdutoVenda getCorrenteProdutoVenda_Associada() {
			return getAgregado().getCorrenteProdutoVenda_Associada();
		}
		public void addListaProdutoVenda_Associada(ProdutoVenda item) {
			getAgregado().addListaProdutoVenda_Associada(item);
		}
		public List<ProdutoVenda> getListaProdutoVenda_Associada() {
			return getAgregado().getListaProdutoVenda_Associada();
		}
		public List<ProdutoVenda> getListaProdutoVenda_AssociadaOriginal() {
			return getAgregado().getListaProdutoVenda_AssociadaOriginal();
		}
		public List<ProdutoVenda> getListaProdutoVenda_Associada(int qtde) {
			return getAgregado().getListaProdutoVenda_Associada(qtde);
		}
		public void setListaProdutoVenda_Associada(List<ProdutoVenda> lista) {
			getAgregado().setListaProdutoVenda_Associada(lista);
		}
		public void setListaProdutoVenda_AssociadaByDao(List<ProdutoVenda> lista) {
			getAgregado().setListaProdutoVenda_AssociadaByDao(lista);
		}
		public void criaVaziaListaProdutoVenda_Associada() {
			getAgregado().criaVaziaListaProdutoVenda_Associada();
		}
		
		public boolean existeListaProdutoVenda_Associada() {
			return getAgregado().existeListaProdutoVenda_Associada();
		}
 		
		public ParcelaVenda getCorrenteParcelaVenda_Possui() {
			return getAgregado().getCorrenteParcelaVenda_Possui();
		}
		public void addListaParcelaVenda_Possui(ParcelaVenda item) {
			getAgregado().addListaParcelaVenda_Possui(item);
		}
		public List<ParcelaVenda> getListaParcelaVenda_Possui() {
			return getAgregado().getListaParcelaVenda_Possui();
		}
		public List<ParcelaVenda> getListaParcelaVenda_PossuiOriginal() {
			return getAgregado().getListaParcelaVenda_PossuiOriginal();
		}
		public List<ParcelaVenda> getListaParcelaVenda_Possui(int qtde) {
			return getAgregado().getListaParcelaVenda_Possui(qtde);
		}
		public void setListaParcelaVenda_Possui(List<ParcelaVenda> lista) {
			getAgregado().setListaParcelaVenda_Possui(lista);
		}
		public void setListaParcelaVenda_PossuiByDao(List<ParcelaVenda> lista) {
			getAgregado().setListaParcelaVenda_PossuiByDao(lista);
		}
		public void criaVaziaListaParcelaVenda_Possui() {
			getAgregado().criaVaziaListaParcelaVenda_Possui();
		}
		
		public boolean existeListaParcelaVenda_Possui() {
			return getAgregado().existeListaParcelaVenda_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(VendaContract.COLUNA_ID_VENDA, idVenda);
    	valores.put(VendaContract.COLUNA_DATA, UtilDatas.getDataLong(data));
    	valores.put(VendaContract.COLUNA_VALOR_TOTAL, valorTotal);
    	valores.put(VendaContract.COLUNA_VENDA_FECHADA, vendaFechada);
		valores.put(VendaContract.COLUNA_ID_CLIENTE_FP, idClienteFp);
	
		valores.put(VendaContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}