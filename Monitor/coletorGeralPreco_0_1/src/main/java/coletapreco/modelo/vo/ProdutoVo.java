package coletapreco.modelo.vo;

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
import coletapreco.modelo.*;
import coletapreco.modelo.agregado.ProdutoAgregado;
import coletapreco.modelo.derivada.impl.ProdutoDerivada;
import coletapreco.modelo.display.impl.ProdutoDisplay;

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
		+ ", \"UrlOrigem\" : \"" + urlOrigem + "\" "
		+ ", \"ImagemLocal\" : \"" + imagemLocal + "\" "
		+ ", \"DataInclusao\" : \"" + dataInclusao + "\" "
		+ ", \"Url\" : \"" + url + "\" "
		+ ", \"Nome\" : \"" + nome + "\" "
		+ ", \"PosicaoProduto\" : \"" + posicaoProduto + "\" "
		+ ", \"Imagem\" : \"" + imagem + "\" "
		+ ", \"IdLojaVirtualLe\" : \"" + idLojaVirtualLe + "\" "
		+ ", \"IdMarcaP\" : \"" + idMarcaP + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdProduto",idProduto);
			json.put("UrlOrigem",urlOrigem);
			json.put("ImagemLocal",imagemLocal);
			json.put("DataInclusao",dataInclusao);
			json.put("Url",url);
			json.put("Nome",nome);
			json.put("PosicaoProduto",posicaoProduto);
			json.put("Imagem",imagem);
			json.put("IdLojaVirtualLe",idLojaVirtualLe);
			json.put("IdMarcaP",idMarcaP);
		
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
		JSONArray listaModeloProdutoProduto_ReferenteA = JSonListaModeloProdutoProduto_ReferenteA();
		if (listaModeloProdutoProduto_ReferenteA!=null) {
			json.put("ListaModeloProdutoProdutoVo_ReferenteA",listaModeloProdutoProduto_ReferenteA);
		} 
		JSONArray listaPrecoProduto_Possui = JSonListaPrecoProduto_Possui();
		if (listaPrecoProduto_Possui!=null) {
			json.put("ListaPrecoProdutoVo_Possui",listaPrecoProduto_Possui);
		} 
		JSONArray listaCategoriaLojaProduto_Possui = JSonListaCategoriaLojaProduto_Possui();
		if (listaCategoriaLojaProduto_Possui!=null) {
			json.put("ListaCategoriaLojaProdutoVo_Possui",listaCategoriaLojaProduto_Possui);
		} 
		JSONArray listaOportunidadeDia_PodePossuir = JSonListaOportunidadeDia_PodePossuir();
		if (listaOportunidadeDia_PodePossuir!=null) {
			json.put("ListaOportunidadeDiaVo_PodePossuir",listaOportunidadeDia_PodePossuir);
		} 
		JSONArray listaPalavraProduto_Possui = JSonListaPalavraProduto_Possui();
		if (listaPalavraProduto_Possui!=null) {
			json.put("ListaPalavraProdutoVo_Possui",listaPalavraProduto_Possui);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaModeloProdutoProduto_ReferenteA() throws JSONException{
		if (getListaModeloProdutoProduto_ReferenteA()==null) return null;
		JSONArray lista = new JSONArray();
		for (ModeloProdutoProduto item:this.getListaModeloProdutoProduto_ReferenteAOriginal()) {
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
	
	private JSONArray JSonListaCategoriaLojaProduto_Possui() throws JSONException{
		if (getListaCategoriaLojaProduto_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (CategoriaLojaProduto item:this.getListaCategoriaLojaProduto_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaOportunidadeDia_PodePossuir() throws JSONException{
		if (getListaOportunidadeDia_PodePossuir()==null) return null;
		JSONArray lista = new JSONArray();
		for (OportunidadeDia item:this.getListaOportunidadeDia_PodePossuirOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaPalavraProduto_Possui() throws JSONException{
		if (getListaPalavraProduto_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (PalavraProduto item:this.getListaPalavraProduto_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdProduto=" + getIdProduto() 
		+  " UrlOrigem=" + getUrlOrigem() 
		+  " ImagemLocal=" + getImagemLocal() 
		+  " DataInclusao=" + getDataInclusao() 
		+  " Url=" + getUrl() 
		+  " Nome=" + getNome() 
		+  " PosicaoProduto=" + getPosicaoProduto() 
		+  " Imagem=" + getImagem() 
		+  " IdLojaVirtualLe=" + getIdLojaVirtualLe() 
		+  " IdMarcaP=" + getIdMarcaP() 
		
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
  	
		public LojaVirtual getLojaVirtual_LidoEm() {
			return getAgregado().getLojaVirtual_LidoEm();
		}
		public void setLojaVirtual_LidoEm(LojaVirtual item) {
			getAgregado().setLojaVirtual_LidoEm(item);
		}
		
		public void addListaLojaVirtual_LidoEm(LojaVirtual item) {
			getAgregado().addListaLojaVirtual_LidoEm(item);
		}
		public LojaVirtual getCorrenteLojaVirtual_LidoEm() {
			return getAgregado().getCorrenteLojaVirtual_LidoEm();
		}
		
		
		public Marca getMarca_Possui() {
			return getAgregado().getMarca_Possui();
		}
		public void setMarca_Possui(Marca item) {
			getAgregado().setMarca_Possui(item);
		}
		
		public void addListaMarca_Possui(Marca item) {
			getAgregado().addListaMarca_Possui(item);
		}
		public Marca getCorrenteMarca_Possui() {
			return getAgregado().getCorrenteMarca_Possui();
		}
		
		
	
	// SemChaveEstrangeira
	
		public ModeloProdutoProduto getCorrenteModeloProdutoProduto_ReferenteA() {
			return getAgregado().getCorrenteModeloProdutoProduto_ReferenteA();
		}
		public void addListaModeloProdutoProduto_ReferenteA(ModeloProdutoProduto item) {
			getAgregado().addListaModeloProdutoProduto_ReferenteA(item);
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA() {
			return getAgregado().getListaModeloProdutoProduto_ReferenteA();
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal() {
			return getAgregado().getListaModeloProdutoProduto_ReferenteAOriginal();
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde) {
			return getAgregado().getListaModeloProdutoProduto_ReferenteA(qtde);
		}
		public void setListaModeloProdutoProduto_ReferenteA(List<ModeloProdutoProduto> lista) {
			getAgregado().setListaModeloProdutoProduto_ReferenteA(lista);
		}
		public void setListaModeloProdutoProduto_ReferenteAByDao(List<ModeloProdutoProduto> lista) {
			getAgregado().setListaModeloProdutoProduto_ReferenteAByDao(lista);
		}
		public void criaVaziaListaModeloProdutoProduto_ReferenteA() {
			getAgregado().criaVaziaListaModeloProdutoProduto_ReferenteA();
		}
		
		public boolean existeListaModeloProdutoProduto_ReferenteA() {
			return getAgregado().existeListaModeloProdutoProduto_ReferenteA();
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
 		
		public CategoriaLojaProduto getCorrenteCategoriaLojaProduto_Possui() {
			return getAgregado().getCorrenteCategoriaLojaProduto_Possui();
		}
		public void addListaCategoriaLojaProduto_Possui(CategoriaLojaProduto item) {
			getAgregado().addListaCategoriaLojaProduto_Possui(item);
		}
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui() {
			return getAgregado().getListaCategoriaLojaProduto_Possui();
		}
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_PossuiOriginal() {
			return getAgregado().getListaCategoriaLojaProduto_PossuiOriginal();
		}
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui(int qtde) {
			return getAgregado().getListaCategoriaLojaProduto_Possui(qtde);
		}
		public void setListaCategoriaLojaProduto_Possui(List<CategoriaLojaProduto> lista) {
			getAgregado().setListaCategoriaLojaProduto_Possui(lista);
		}
		public void setListaCategoriaLojaProduto_PossuiByDao(List<CategoriaLojaProduto> lista) {
			getAgregado().setListaCategoriaLojaProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaCategoriaLojaProduto_Possui() {
			getAgregado().criaVaziaListaCategoriaLojaProduto_Possui();
		}
		
		public boolean existeListaCategoriaLojaProduto_Possui() {
			return getAgregado().existeListaCategoriaLojaProduto_Possui();
		}
 		
		public OportunidadeDia getCorrenteOportunidadeDia_PodePossuir() {
			return getAgregado().getCorrenteOportunidadeDia_PodePossuir();
		}
		public void addListaOportunidadeDia_PodePossuir(OportunidadeDia item) {
			getAgregado().addListaOportunidadeDia_PodePossuir(item);
		}
		public List<OportunidadeDia> getListaOportunidadeDia_PodePossuir() {
			return getAgregado().getListaOportunidadeDia_PodePossuir();
		}
		public List<OportunidadeDia> getListaOportunidadeDia_PodePossuirOriginal() {
			return getAgregado().getListaOportunidadeDia_PodePossuirOriginal();
		}
		public List<OportunidadeDia> getListaOportunidadeDia_PodePossuir(int qtde) {
			return getAgregado().getListaOportunidadeDia_PodePossuir(qtde);
		}
		public void setListaOportunidadeDia_PodePossuir(List<OportunidadeDia> lista) {
			getAgregado().setListaOportunidadeDia_PodePossuir(lista);
		}
		public void setListaOportunidadeDia_PodePossuirByDao(List<OportunidadeDia> lista) {
			getAgregado().setListaOportunidadeDia_PodePossuirByDao(lista);
		}
		public void criaVaziaListaOportunidadeDia_PodePossuir() {
			getAgregado().criaVaziaListaOportunidadeDia_PodePossuir();
		}
		
		public boolean existeListaOportunidadeDia_PodePossuir() {
			return getAgregado().existeListaOportunidadeDia_PodePossuir();
		}
 		
		public PalavraProduto getCorrentePalavraProduto_Possui() {
			return getAgregado().getCorrentePalavraProduto_Possui();
		}
		public void addListaPalavraProduto_Possui(PalavraProduto item) {
			getAgregado().addListaPalavraProduto_Possui(item);
		}
		public List<PalavraProduto> getListaPalavraProduto_Possui() {
			return getAgregado().getListaPalavraProduto_Possui();
		}
		public List<PalavraProduto> getListaPalavraProduto_PossuiOriginal() {
			return getAgregado().getListaPalavraProduto_PossuiOriginal();
		}
		public List<PalavraProduto> getListaPalavraProduto_Possui(int qtde) {
			return getAgregado().getListaPalavraProduto_Possui(qtde);
		}
		public void setListaPalavraProduto_Possui(List<PalavraProduto> lista) {
			getAgregado().setListaPalavraProduto_Possui(lista);
		}
		public void setListaPalavraProduto_PossuiByDao(List<PalavraProduto> lista) {
			getAgregado().setListaPalavraProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaPalavraProduto_Possui() {
			getAgregado().criaVaziaListaPalavraProduto_Possui();
		}
		
		public boolean existeListaPalavraProduto_Possui() {
			return getAgregado().existeListaPalavraProduto_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ProdutoVo(JSONObject json) throws JSONException{
		idProduto =  ConversorJson.getInteger(json, "IdProduto");
		urlOrigem =  ConversorJson.getString(json, "UrlOrigem");
		imagemLocal =  ConversorJson.getString(json, "ImagemLocal");
		dataInclusao =  ConversorJson.getTimestampDataHora(json, "DataInclusao");
		url =  ConversorJson.getString(json, "Url");
		nome =  ConversorJson.getString(json, "Nome");
		posicaoProduto =  ConversorJson.getInteger(json, "PosicaoProduto");
		imagem =  ConversorJson.getString(json, "Imagem");
		idLojaVirtualLe =  ConversorJson.getInteger(json, "IdLojaVirtualLe");
		idMarcaP =  ConversorJson.getInteger(json, "IdMarcaP");
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


	private String urlOrigem;
	public String getUrlOrigem() {
		return urlOrigem;
	}
	public void setUrlOrigem(String _valor) {
		urlOrigem = _valor;
	}


	private String imagemLocal;
	public String getImagemLocal() {
		return imagemLocal;
	}
	public void setImagemLocal(String _valor) {
		imagemLocal = _valor;
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




	public String getDataInclusaoHHMM() {
		if (dataInclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataInclusao);
    }
    public String getDataInclusaoHHMMSS() {
		if (dataInclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataInclusao);
    }
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String _valor) {
		url = _valor;
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

	
	private long idLojaVirtualLe;
	public long getIdLojaVirtualLe() {
		// relacionamento
		if (idLojaVirtualLe==0 && this.getLojaVirtual_LidoEm()!=null) 
			return getLojaVirtual_LidoEm().getId();
		else
			return idLojaVirtualLe;
	}
	public void setIdLojaVirtualLe(long _valor) {
		idLojaVirtualLe = _valor;
	}

	
	private long idMarcaP;
	public long getIdMarcaP() {
		// relacionamento
		if (idMarcaP==0 && this.getMarca_Possui()!=null) 
			return getMarca_Possui().getId();
		else
			return idMarcaP;
	}
	public void setIdMarcaP(long _valor) {
		idMarcaP = _valor;
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
  	
	public long getIdLojaVirtualLeLazyLoader() {
		return idLojaVirtualLe;
	} 
		
	public long getIdMarcaPLazyLoader() {
		return idMarcaP;
	} 
		
}