package repcom.modelo.vo;

import android.view.View;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.ObjetoSinc;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.activity.DigicomContexto;
import repcom.modelo.*;
import repcom.modelo.agregado.ProdutoAgregado;
import repcom.modelo.derivada.impl.ProdutoDerivada;
import repcom.modelo.display.impl.ProdutoDisplay;

public class ProdutoVo implements Produto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idProduto;
    }
  
  
  	private DigicomContexto _contexto = null;
    public DigicomContexto getContexto() {
    	if (_contexto==null) 
    		throw new RuntimeException("DigicomContexto n?o inicializado");
    	return _contexto;
    }
  	public void setContexto(DigicomContexto ctx) {
  		_contexto = ctx;
  	}
  	
  
  	public ProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdProduto\" : \"" + idProduto + "\" "
		+ ", \"Nome\" : \"" + nome + "\" "
		+ ", \"Url\" : \"" + url + "\" "
		+ ", \"Imagem\" : \"" + imagem + "\" "
		+ ", \"TamanhoImagem\" : \"" + tamanhoImagem + "\" "
		+ ", \"DataInclusao\" : \"" + dataInclusao + "\" "
		+ ", \"DataExclusao\" : \"" + dataExclusao + "\" "
		+ ", \"IdLinhaProdutoEe\" : \"" + idLinhaProdutoEe + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdProduto",idProduto);
			json.put("Nome",nome);
			json.put("Url",url);
			json.put("Imagem",imagem);
			json.put("TamanhoImagem",tamanhoImagem);
			json.put("DataInclusao",dataInclusao);
			json.put("DataExclusao",dataExclusao);
			json.put("IdLinhaProdutoEe",idLinhaProdutoEe);
		
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}

	public JSONObject JSonSimples() throws JSONException{
		return JSonAtributos();
	}

	// apagar em 04-05-2015
	/**
 	* @deprecated  Substituir por JSonSimples()
 	*/
	@Deprecated
	public JSONObject JSon() throws JSONException{
		JSONObject json = JSonAtributos();
		//try {
		JSONArray listaProdutoPedidoFornecedor_Associada = JSonListaProdutoPedidoFornecedor_Associada();
		if (listaProdutoPedidoFornecedor_Associada!=null) {
			json.put("ListaProdutoPedidoFornecedorVo_Associada",listaProdutoPedidoFornecedor_Associada);
		} 
		JSONArray listaProdutoVenda_Associada = JSonListaProdutoVenda_Associada();
		if (listaProdutoVenda_Associada!=null) {
			json.put("ListaProdutoVendaVo_Associada",listaProdutoVenda_Associada);
		} 
		JSONArray listaPrecoProduto_Possui = JSonListaPrecoProduto_Possui();
		if (listaPrecoProduto_Possui!=null) {
			json.put("ListaPrecoProdutoVo_Possui",listaPrecoProduto_Possui);
		} 
		JSONArray listaCategoriaProdutoProduto_Possui = JSonListaCategoriaProdutoProduto_Possui();
		if (listaCategoriaProdutoProduto_Possui!=null) {
			json.put("ListaCategoriaProdutoProdutoVo_Possui",listaCategoriaProdutoProduto_Possui);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaProdutoPedidoFornecedor_Associada() throws JSONException{
		if (getListaProdutoPedidoFornecedor_Associada()==null) return null;
		JSONArray lista = new JSONArray();
		for (ProdutoPedidoFornecedor item:this.getListaProdutoPedidoFornecedor_AssociadaOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaProdutoVenda_Associada() throws JSONException{
		if (getListaProdutoVenda_Associada()==null) return null;
		JSONArray lista = new JSONArray();
		for (ProdutoVenda item:this.getListaProdutoVenda_AssociadaOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaPrecoProduto_Possui() throws JSONException{
		if (getListaPrecoProduto_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (PrecoProduto item:this.getListaPrecoProduto_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaCategoriaProdutoProduto_Possui() throws JSONException{
		if (getListaCategoriaProdutoProduto_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (CategoriaProdutoProduto item:this.getListaCategoriaProdutoProduto_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdProduto=" + getIdProduto() 
		+  " Nome=" + getNome() 
		+  " Url=" + getUrl() 
		+  " Imagem=" + getImagem() 
		+  " TamanhoImagem=" + getTamanhoImagem() 
		+  " DataInclusao=" + getDataInclusao() 
		+  " DataExclusao=" + getDataExclusao() 
		+  " IdLinhaProdutoEe=" + getIdLinhaProdutoEe() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idProduto;
 	}
 	public String getNomeClasse() {
 		return "Produto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ProdutoDisplay display = null;
	private ProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ProdutoDisplay(this);
		}
		return display;
	}
	@Override
	public View getItemListaView() {
		return getObjetoDisplay().getItemListaView();
	}
	@Override
	public String getItemListaTexto() {
		return getObjetoDisplay().getItemListaTexto();
	}
	// ----- FINAL DISPLAY -----------------
 
    // ----- INICIO AGREGADO -----------------
	private ProdutoAgregado agregado = null;
	private ProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ProdutoDerivada derivada = null;
	private ProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ProdutoDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	
	// ----- FINAL DERIVADA -----------------
	
	
	
 
  
  
  	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
		public LinhaProduto getLinhaProduto_EstaEm() {
			return getAgregado().getLinhaProduto_EstaEm();
		}
		public void setLinhaProduto_EstaEm(LinhaProduto item) {
			getAgregado().setLinhaProduto_EstaEm(item);
		}
		
		public void addListaLinhaProduto_EstaEm(LinhaProduto item) {
			getAgregado().addListaLinhaProduto_EstaEm(item);
		}
		public LinhaProduto getCorrenteLinhaProduto_EstaEm() {
			return getAgregado().getCorrenteLinhaProduto_EstaEm();
		}
		
		
	
	// SemChaveEstrangeira
	
		public ProdutoPedidoFornecedor getCorrenteProdutoPedidoFornecedor_Associada() {
			return getAgregado().getCorrenteProdutoPedidoFornecedor_Associada();
		}
		public void addListaProdutoPedidoFornecedor_Associada(ProdutoPedidoFornecedor item) {
			getAgregado().addListaProdutoPedidoFornecedor_Associada(item);
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada() {
			return getAgregado().getListaProdutoPedidoFornecedor_Associada();
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_AssociadaOriginal() {
			return getAgregado().getListaProdutoPedidoFornecedor_AssociadaOriginal();
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada(int qtde) {
			return getAgregado().getListaProdutoPedidoFornecedor_Associada(qtde);
		}
		public void setListaProdutoPedidoFornecedor_Associada(List<ProdutoPedidoFornecedor> lista) {
			getAgregado().setListaProdutoPedidoFornecedor_Associada(lista);
		}
		public void setListaProdutoPedidoFornecedor_AssociadaByDao(List<ProdutoPedidoFornecedor> lista) {
			getAgregado().setListaProdutoPedidoFornecedor_AssociadaByDao(lista);
		}
		public void criaVaziaListaProdutoPedidoFornecedor_Associada() {
			getAgregado().criaVaziaListaProdutoPedidoFornecedor_Associada();
		}
		
		public boolean existeListaProdutoPedidoFornecedor_Associada() {
			return getAgregado().existeListaProdutoPedidoFornecedor_Associada();
		}
 		
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
 		
		public PrecoProduto getCorrentePrecoProduto_Possui() {
			return getAgregado().getCorrentePrecoProduto_Possui();
		}
		public void addListaPrecoProduto_Possui(PrecoProduto item) {
			getAgregado().addListaPrecoProduto_Possui(item);
		}
		public List<PrecoProduto> getListaPrecoProduto_Possui() {
			return getAgregado().getListaPrecoProduto_Possui();
		}
		public List<PrecoProduto> getListaPrecoProduto_PossuiOriginal() {
			return getAgregado().getListaPrecoProduto_PossuiOriginal();
		}
		public List<PrecoProduto> getListaPrecoProduto_Possui(int qtde) {
			return getAgregado().getListaPrecoProduto_Possui(qtde);
		}
		public void setListaPrecoProduto_Possui(List<PrecoProduto> lista) {
			getAgregado().setListaPrecoProduto_Possui(lista);
		}
		public void setListaPrecoProduto_PossuiByDao(List<PrecoProduto> lista) {
			getAgregado().setListaPrecoProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaPrecoProduto_Possui() {
			getAgregado().criaVaziaListaPrecoProduto_Possui();
		}
		
		public boolean existeListaPrecoProduto_Possui() {
			return getAgregado().existeListaPrecoProduto_Possui();
		}
 		
		public CategoriaProdutoProduto getCorrenteCategoriaProdutoProduto_Possui() {
			return getAgregado().getCorrenteCategoriaProdutoProduto_Possui();
		}
		public void addListaCategoriaProdutoProduto_Possui(CategoriaProdutoProduto item) {
			getAgregado().addListaCategoriaProdutoProduto_Possui(item);
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui() {
			return getAgregado().getListaCategoriaProdutoProduto_Possui();
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_PossuiOriginal() {
			return getAgregado().getListaCategoriaProdutoProduto_PossuiOriginal();
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui(int qtde) {
			return getAgregado().getListaCategoriaProdutoProduto_Possui(qtde);
		}
		public void setListaCategoriaProdutoProduto_Possui(List<CategoriaProdutoProduto> lista) {
			getAgregado().setListaCategoriaProdutoProduto_Possui(lista);
		}
		public void setListaCategoriaProdutoProduto_PossuiByDao(List<CategoriaProdutoProduto> lista) {
			getAgregado().setListaCategoriaProdutoProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaCategoriaProdutoProduto_Possui() {
			getAgregado().criaVaziaListaCategoriaProdutoProduto_Possui();
		}
		
		public boolean existeListaCategoriaProdutoProduto_Possui() {
			return getAgregado().existeListaCategoriaProdutoProduto_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ProdutoVo(JSONObject json) throws JSONException{
		idProduto =  ConversorJson.getInteger(json, "IdProduto");
		nome =  ConversorJson.getString(json, "Nome");
		url =  ConversorJson.getString(json, "Url");
		imagem =  ConversorJson.getString(json, "Imagem");
		tamanhoImagem =  ConversorJson.getInteger(json, "TamanhoImagem");
		dataInclusao =  ConversorJson.getTimestampData(json, "DataInclusao");
		dataExclusao =  ConversorJson.getTimestampData(json, "DataExclusao");
		idLinhaProdutoEe =  ConversorJson.getInteger(json, "IdLinhaProdutoEe");
  	}
  	public String toString() {
  		return "" + nome;
  	}
	private long idProduto;
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long _valor) {
		idProduto = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}


	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String _valor) {
		url = _valor;
	}


	private String imagem;
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String _valor) {
		imagem = _valor;
	}


	private long tamanhoImagem;
	public long getTamanhoImagem() {
		return tamanhoImagem;
	}
	public void setTamanhoImagem(long _valor) {
		tamanhoImagem = _valor;
	}


	private Timestamp dataInclusao;
	public Timestamp getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Timestamp _valor) {
		dataInclusao = _valor;
	}


	public String getDataInclusaoDDMMAAAA() {
		if (dataInclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataInclusao);
    }
    public void setDataInclusaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInclusao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private Timestamp dataExclusao;
	public Timestamp getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(Timestamp _valor) {
		dataExclusao = _valor;
	}


	public String getDataExclusaoDDMMAAAA() {
		if (dataExclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataExclusao);
    }
    public void setDataExclusaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataExclusao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }



	
	private long idLinhaProdutoEe;
	public long getIdLinhaProdutoEe() {
		// relacionamento
		if (idLinhaProdutoEe==0 && this.getLinhaProduto_EstaEm()!=null) 
			return getLinhaProduto_EstaEm().getId();
		else
			return idLinhaProdutoEe;
	}
	public void setIdLinhaProdutoEe(long _valor) {
		idLinhaProdutoEe = _valor;
	}





	private String operacaoSinc = null;
	@Override
	public String getOperacaoSinc() {
		return operacaoSinc;
	}
	@Override
	public void setOperacaoSinc(String valor) {
		operacaoSinc = valor;
	}
	@Override
	public JSONObject JSonSinc() throws JSONException {
		JSONObject json = JSonSimples();
		json.put("OperacaoSinc", operacaoSinc);
		return json;
	}
	
	
	private boolean salvaPreliminar = false;
	@Override
	public void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	@Override
	public boolean getSalvaPreliminar() {
		return salvaPreliminar;
	}
	
	// ComChaveEstrangeira
  	
	public long getIdLinhaProdutoEeLazyLoader() {
		return idLinhaProdutoEe;
	} 
		
}