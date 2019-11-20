
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
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.ProdutoClienteAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;

public class ProdutoClienteVo implements ProdutoCliente  {

	public ProdutoClienteVo() {
  	}
  	
  	public long getIdObj()
    {
       return idProdutoCliente;
    }

	 // ----- INICIO AGREGADO -----------------
	private ProdutoClienteAgregado agregado = null;
	private ProdutoClienteAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ProdutoClienteAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idProdutoCliente;
	public long getIdProdutoCliente() {
		return idProdutoCliente;
	}
	public void setIdProdutoCliente(long _valor) {
		idProdutoCliente = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}


	private long posicaoProduto;
	public long getPosicaoProduto() {
		return posicaoProduto;
	}
	public void setPosicaoProduto(long _valor) {
		posicaoProduto = _valor;
	}


	private String imagem;
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String _valor) {
		imagem = _valor;
	}


	private float precoAtual;
	public float getPrecoAtual() {
		return precoAtual;
	}
	public void setPrecoAtual(float _valor) {
		precoAtual = _valor;
	}
	
	public String getPrecoAtualTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoAtual);
		return saida;
	}

	private String marca;
	public String getMarca() {
		return marca;
	}
	public void setMarca(String _valor) {
		marca = _valor;
	}


	private String loja;
	public String getLoja() {
		return loja;
	}
	public void setLoja(String _valor) {
		loja = _valor;
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




	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String _valor) {
		url = _valor;
	}


	private String detalhe;
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String _valor) {
		detalhe = _valor;
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
  	
	public long getIdNaturezaProdutoRaLazyLoader() {
		return idNaturezaProdutoRa;
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
		
		
	
	// SemChaveEstrangeira
	
		public InteresseProduto getCorrenteInteresseProduto_Possui() {
			return getAgregado().getCorrenteInteresseProduto_Possui();
		}
		public void addListaInteresseProduto_Possui(InteresseProduto item) {
			getAgregado().addListaInteresseProduto_Possui(item);
		}
		public List<InteresseProduto> getListaInteresseProduto_Possui() {
			return getAgregado().getListaInteresseProduto_Possui();
		}
		public List<InteresseProduto> getListaInteresseProduto_PossuiOriginal() {
			return getAgregado().getListaInteresseProduto_PossuiOriginal();
		}
		public List<InteresseProduto> getListaInteresseProduto_Possui(int qtde) {
			return getAgregado().getListaInteresseProduto_Possui(qtde);
		}
		public void setListaInteresseProduto_Possui(List<InteresseProduto> lista) {
			getAgregado().setListaInteresseProduto_Possui(lista);
		}
		public void setListaInteresseProduto_PossuiByDao(List<InteresseProduto> lista) {
			getAgregado().setListaInteresseProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaInteresseProduto_Possui() {
			getAgregado().criaVaziaListaInteresseProduto_Possui();
		}
		
		public boolean existeListaInteresseProduto_Possui() {
			return getAgregado().existeListaInteresseProduto_Possui();
		}
 		
		public PrecoDiarioCliente getCorrentePrecoDiarioCliente_Possui() {
			return getAgregado().getCorrentePrecoDiarioCliente_Possui();
		}
		public void addListaPrecoDiarioCliente_Possui(PrecoDiarioCliente item) {
			getAgregado().addListaPrecoDiarioCliente_Possui(item);
		}
		public List<PrecoDiarioCliente> getListaPrecoDiarioCliente_Possui() {
			return getAgregado().getListaPrecoDiarioCliente_Possui();
		}
		public List<PrecoDiarioCliente> getListaPrecoDiarioCliente_PossuiOriginal() {
			return getAgregado().getListaPrecoDiarioCliente_PossuiOriginal();
		}
		public List<PrecoDiarioCliente> getListaPrecoDiarioCliente_Possui(int qtde) {
			return getAgregado().getListaPrecoDiarioCliente_Possui(qtde);
		}
		public void setListaPrecoDiarioCliente_Possui(List<PrecoDiarioCliente> lista) {
			getAgregado().setListaPrecoDiarioCliente_Possui(lista);
		}
		public void setListaPrecoDiarioCliente_PossuiByDao(List<PrecoDiarioCliente> lista) {
			getAgregado().setListaPrecoDiarioCliente_PossuiByDao(lista);
		}
		public void criaVaziaListaPrecoDiarioCliente_Possui() {
			getAgregado().criaVaziaListaPrecoDiarioCliente_Possui();
		}
		
		public boolean existeListaPrecoDiarioCliente_Possui() {
			return getAgregado().existeListaPrecoDiarioCliente_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ProdutoClienteContract.COLUNA_ID_PRODUTO_CLIENTE, idProdutoCliente);
    	valores.put(ProdutoClienteContract.COLUNA_NOME, nome);
    	valores.put(ProdutoClienteContract.COLUNA_POSICAO_PRODUTO, posicaoProduto);
    	valores.put(ProdutoClienteContract.COLUNA_IMAGEM, imagem);
    	valores.put(ProdutoClienteContract.COLUNA_PRECO_ATUAL, precoAtual);
    	valores.put(ProdutoClienteContract.COLUNA_MARCA, marca);
    	valores.put(ProdutoClienteContract.COLUNA_LOJA, loja);
    	valores.put(ProdutoClienteContract.COLUNA_DATA, UtilDatas.getDataLong(data));
    	valores.put(ProdutoClienteContract.COLUNA_URL, url);
    	valores.put(ProdutoClienteContract.COLUNA_DETALHE, detalhe);
		valores.put(ProdutoClienteContract.COLUNA_ID_NATUREZA_PRODUTO_RA, idNaturezaProdutoRa);
	
		valores.put(ProdutoClienteContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}