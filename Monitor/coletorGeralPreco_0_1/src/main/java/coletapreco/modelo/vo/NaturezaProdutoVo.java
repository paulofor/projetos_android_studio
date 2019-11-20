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
import coletapreco.modelo.agregado.NaturezaProdutoAgregado;
import coletapreco.modelo.derivada.impl.NaturezaProdutoDerivada;
import coletapreco.modelo.display.impl.NaturezaProdutoDisplay;

public class NaturezaProdutoVo implements NaturezaProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idNaturezaProduto;
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
  	
  
  	public NaturezaProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdNaturezaProduto\" : \"" + idNaturezaProduto + "\" "
		+ ", \"NomeNaturezaProduto\" : \"" + nomeNaturezaProduto + "\" "
		+ ", \"CodigoNaturezaProduto\" : \"" + codigoNaturezaProduto + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdNaturezaProduto",idNaturezaProduto);
			json.put("NomeNaturezaProduto",nomeNaturezaProduto);
			json.put("CodigoNaturezaProduto",codigoNaturezaProduto);
		
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
		JSONArray listaCategoriaLoja_Possui = JSonListaCategoriaLoja_Possui();
		if (listaCategoriaLoja_Possui!=null) {
			json.put("ListaCategoriaLojaVo_Possui",listaCategoriaLoja_Possui);
		} 
		JSONArray listaLojaNatureza_Encontrada = JSonListaLojaNatureza_Encontrada();
		if (listaLojaNatureza_Encontrada!=null) {
			json.put("ListaLojaNaturezaVo_Encontrada",listaLojaNatureza_Encontrada);
		} 
		JSONArray listaOportunidadeDia_Possui = JSonListaOportunidadeDia_Possui();
		if (listaOportunidadeDia_Possui!=null) {
			json.put("ListaOportunidadeDiaVo_Possui",listaOportunidadeDia_Possui);
		} 
		JSONArray listaUsuarioPesquisa_PesquisadoPor = JSonListaUsuarioPesquisa_PesquisadoPor();
		if (listaUsuarioPesquisa_PesquisadoPor!=null) {
			json.put("ListaUsuarioPesquisaVo_PesquisadoPor",listaUsuarioPesquisa_PesquisadoPor);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaCategoriaLoja_Possui() throws JSONException{
		if (getListaCategoriaLoja_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (CategoriaLoja item:this.getListaCategoriaLoja_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaLojaNatureza_Encontrada() throws JSONException{
		if (getListaLojaNatureza_Encontrada()==null) return null;
		JSONArray lista = new JSONArray();
		for (LojaNatureza item:this.getListaLojaNatureza_EncontradaOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaOportunidadeDia_Possui() throws JSONException{
		if (getListaOportunidadeDia_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (OportunidadeDia item:this.getListaOportunidadeDia_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaUsuarioPesquisa_PesquisadoPor() throws JSONException{
		if (getListaUsuarioPesquisa_PesquisadoPor()==null) return null;
		JSONArray lista = new JSONArray();
		for (UsuarioPesquisa item:this.getListaUsuarioPesquisa_PesquisadoPorOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdNaturezaProduto=" + getIdNaturezaProduto() 
		+  " NomeNaturezaProduto=" + getNomeNaturezaProduto() 
		+  " CodigoNaturezaProduto=" + getCodigoNaturezaProduto() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idNaturezaProduto;
 	}
 	public String getNomeClasse() {
 		return "NaturezaProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private NaturezaProdutoDisplay display = null;
	private NaturezaProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new NaturezaProdutoDisplay(this);
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
	private NaturezaProdutoAgregado agregado = null;
	private NaturezaProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new NaturezaProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private NaturezaProdutoDerivada derivada = null;
	private NaturezaProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new NaturezaProdutoDerivada(this);
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
  	
	
	// SemChaveEstrangeira
	
		public CategoriaLoja getCorrenteCategoriaLoja_Possui() {
			return getAgregado().getCorrenteCategoriaLoja_Possui();
		}
		public void addListaCategoriaLoja_Possui(CategoriaLoja item) {
			getAgregado().addListaCategoriaLoja_Possui(item);
		}
		public List<CategoriaLoja> getListaCategoriaLoja_Possui() {
			return getAgregado().getListaCategoriaLoja_Possui();
		}
		public List<CategoriaLoja> getListaCategoriaLoja_PossuiOriginal() {
			return getAgregado().getListaCategoriaLoja_PossuiOriginal();
		}
		public List<CategoriaLoja> getListaCategoriaLoja_Possui(int qtde) {
			return getAgregado().getListaCategoriaLoja_Possui(qtde);
		}
		public void setListaCategoriaLoja_Possui(List<CategoriaLoja> lista) {
			getAgregado().setListaCategoriaLoja_Possui(lista);
		}
		public void setListaCategoriaLoja_PossuiByDao(List<CategoriaLoja> lista) {
			getAgregado().setListaCategoriaLoja_PossuiByDao(lista);
		}
		public void criaVaziaListaCategoriaLoja_Possui() {
			getAgregado().criaVaziaListaCategoriaLoja_Possui();
		}
		
		public boolean existeListaCategoriaLoja_Possui() {
			return getAgregado().existeListaCategoriaLoja_Possui();
		}
 		
		public LojaNatureza getCorrenteLojaNatureza_Encontrada() {
			return getAgregado().getCorrenteLojaNatureza_Encontrada();
		}
		public void addListaLojaNatureza_Encontrada(LojaNatureza item) {
			getAgregado().addListaLojaNatureza_Encontrada(item);
		}
		public List<LojaNatureza> getListaLojaNatureza_Encontrada() {
			return getAgregado().getListaLojaNatureza_Encontrada();
		}
		public List<LojaNatureza> getListaLojaNatureza_EncontradaOriginal() {
			return getAgregado().getListaLojaNatureza_EncontradaOriginal();
		}
		public List<LojaNatureza> getListaLojaNatureza_Encontrada(int qtde) {
			return getAgregado().getListaLojaNatureza_Encontrada(qtde);
		}
		public void setListaLojaNatureza_Encontrada(List<LojaNatureza> lista) {
			getAgregado().setListaLojaNatureza_Encontrada(lista);
		}
		public void setListaLojaNatureza_EncontradaByDao(List<LojaNatureza> lista) {
			getAgregado().setListaLojaNatureza_EncontradaByDao(lista);
		}
		public void criaVaziaListaLojaNatureza_Encontrada() {
			getAgregado().criaVaziaListaLojaNatureza_Encontrada();
		}
		
		public boolean existeListaLojaNatureza_Encontrada() {
			return getAgregado().existeListaLojaNatureza_Encontrada();
		}
 		
		public OportunidadeDia getCorrenteOportunidadeDia_Possui() {
			return getAgregado().getCorrenteOportunidadeDia_Possui();
		}
		public void addListaOportunidadeDia_Possui(OportunidadeDia item) {
			getAgregado().addListaOportunidadeDia_Possui(item);
		}
		public List<OportunidadeDia> getListaOportunidadeDia_Possui() {
			return getAgregado().getListaOportunidadeDia_Possui();
		}
		public List<OportunidadeDia> getListaOportunidadeDia_PossuiOriginal() {
			return getAgregado().getListaOportunidadeDia_PossuiOriginal();
		}
		public List<OportunidadeDia> getListaOportunidadeDia_Possui(int qtde) {
			return getAgregado().getListaOportunidadeDia_Possui(qtde);
		}
		public void setListaOportunidadeDia_Possui(List<OportunidadeDia> lista) {
			getAgregado().setListaOportunidadeDia_Possui(lista);
		}
		public void setListaOportunidadeDia_PossuiByDao(List<OportunidadeDia> lista) {
			getAgregado().setListaOportunidadeDia_PossuiByDao(lista);
		}
		public void criaVaziaListaOportunidadeDia_Possui() {
			getAgregado().criaVaziaListaOportunidadeDia_Possui();
		}
		
		public boolean existeListaOportunidadeDia_Possui() {
			return getAgregado().existeListaOportunidadeDia_Possui();
		}
 		
		public UsuarioPesquisa getCorrenteUsuarioPesquisa_PesquisadoPor() {
			return getAgregado().getCorrenteUsuarioPesquisa_PesquisadoPor();
		}
		public void addListaUsuarioPesquisa_PesquisadoPor(UsuarioPesquisa item) {
			getAgregado().addListaUsuarioPesquisa_PesquisadoPor(item);
		}
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor() {
			return getAgregado().getListaUsuarioPesquisa_PesquisadoPor();
		}
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPorOriginal() {
			return getAgregado().getListaUsuarioPesquisa_PesquisadoPorOriginal();
		}
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor(int qtde) {
			return getAgregado().getListaUsuarioPesquisa_PesquisadoPor(qtde);
		}
		public void setListaUsuarioPesquisa_PesquisadoPor(List<UsuarioPesquisa> lista) {
			getAgregado().setListaUsuarioPesquisa_PesquisadoPor(lista);
		}
		public void setListaUsuarioPesquisa_PesquisadoPorByDao(List<UsuarioPesquisa> lista) {
			getAgregado().setListaUsuarioPesquisa_PesquisadoPorByDao(lista);
		}
		public void criaVaziaListaUsuarioPesquisa_PesquisadoPor() {
			getAgregado().criaVaziaListaUsuarioPesquisa_PesquisadoPor();
		}
		
		public boolean existeListaUsuarioPesquisa_PesquisadoPor() {
			return getAgregado().existeListaUsuarioPesquisa_PesquisadoPor();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public NaturezaProdutoVo(JSONObject json) throws JSONException{
		idNaturezaProduto =  ConversorJson.getInteger(json, "IdNaturezaProduto");
		nomeNaturezaProduto =  ConversorJson.getString(json, "NomeNaturezaProduto");
		codigoNaturezaProduto =  ConversorJson.getString(json, "CodigoNaturezaProduto");
  	}
  	public String toString() {
  		return "" + nomeNaturezaProduto;
  	}
	private long idNaturezaProduto;
	public long getIdNaturezaProduto() {
		return idNaturezaProduto;
	}
	public void setIdNaturezaProduto(long _valor) {
		idNaturezaProduto = _valor;
	}


	private String nomeNaturezaProduto;
	public String getNomeNaturezaProduto() {
		return nomeNaturezaProduto;
	}
	public void setNomeNaturezaProduto(String _valor) {
		nomeNaturezaProduto = _valor;
	}


	private String codigoNaturezaProduto;
	public String getCodigoNaturezaProduto() {
		return codigoNaturezaProduto;
	}
	public void setCodigoNaturezaProduto(String _valor) {
		codigoNaturezaProduto = _valor;
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
  	
}