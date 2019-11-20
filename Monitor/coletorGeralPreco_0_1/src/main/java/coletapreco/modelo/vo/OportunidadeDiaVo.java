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
import coletapreco.modelo.agregado.OportunidadeDiaAgregado;
import coletapreco.modelo.derivada.impl.OportunidadeDiaDerivada;
import coletapreco.modelo.display.impl.OportunidadeDiaDisplay;

public class OportunidadeDiaVo implements OportunidadeDia , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idOportunidadeDia;
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
  	
  
  	public OportunidadeDiaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdOportunidadeDia\" : \"" + idOportunidadeDia + "\" "
		+ ", \"UrlProduto\" : \"" + urlProduto + "\" "
		+ ", \"NomeProduto\" : \"" + nomeProduto + "\" "
		+ ", \"DataInicioPrecoAtual\" : \"" + dataInicioPrecoAtual + "\" "
		+ ", \"NomeMarca\" : \"" + nomeMarca + "\" "
		+ ", \"UrlAfiliado\" : \"" + urlAfiliado + "\" "
		+ ", \"DataUltimaPrecoAnterior\" : \"" + dataUltimaPrecoAnterior + "\" "
		+ ", \"ImagemLocal\" : \"" + imagemLocal + "\" "
		+ ", \"UrlImagem\" : \"" + urlImagem + "\" "
		+ ", \"PosicaoProduto\" : \"" + posicaoProduto + "\" "
		+ ", \"PrecoVendaAnterior\" : \"" + precoVendaAnterior + "\" "
		+ ", \"PrecoVendaAtual\" : \"" + precoVendaAtual + "\" "
		+ ", \"PrecoBoletoAnterior\" : \"" + precoBoletoAnterior + "\" "
		+ ", \"PrecoBoletoAtual\" : \"" + precoBoletoAtual + "\" "
		+ ", \"PrecoParcelaAnterior\" : \"" + precoParcelaAnterior + "\" "
		+ ", \"PrecoParcelaAtual\" : \"" + precoParcelaAtual + "\" "
		+ ", \"QuantidadeParcelaAnterior\" : \"" + quantidadeParcelaAnterior + "\" "
		+ ", \"QuantidadeParcelaAtual\" : \"" + quantidadeParcelaAtual + "\" "
		+ ", \"PercentualAjusteVenda\" : \"" + percentualAjusteVenda + "\" "
		+ ", \"PercentualAjusteBoleto\" : \"" + percentualAjusteBoleto + "\" "
		+ ", \"NomeLojaVirtual\" : \"" + nomeLojaVirtual + "\" "
		+ ", \"IdProdutoRa\" : \"" + idProdutoRa + "\" "
		+ ", \"IdNaturezaProdutoPa\" : \"" + idNaturezaProdutoPa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdOportunidadeDia",idOportunidadeDia);
			json.put("UrlProduto",urlProduto);
			json.put("NomeProduto",nomeProduto);
			json.put("DataInicioPrecoAtual",dataInicioPrecoAtual);
			json.put("NomeMarca",nomeMarca);
			json.put("UrlAfiliado",urlAfiliado);
			json.put("DataUltimaPrecoAnterior",dataUltimaPrecoAnterior);
			json.put("ImagemLocal",imagemLocal);
			json.put("UrlImagem",urlImagem);
			json.put("PosicaoProduto",posicaoProduto);
			json.put("PrecoVendaAnterior",precoVendaAnterior);
			json.put("PrecoVendaAtual",precoVendaAtual);
			json.put("PrecoBoletoAnterior",precoBoletoAnterior);
			json.put("PrecoBoletoAtual",precoBoletoAtual);
			json.put("PrecoParcelaAnterior",precoParcelaAnterior);
			json.put("PrecoParcelaAtual",precoParcelaAtual);
			json.put("QuantidadeParcelaAnterior",quantidadeParcelaAnterior);
			json.put("QuantidadeParcelaAtual",quantidadeParcelaAtual);
			json.put("PercentualAjusteVenda",percentualAjusteVenda);
			json.put("PercentualAjusteBoleto",percentualAjusteBoleto);
			json.put("NomeLojaVirtual",nomeLojaVirtual);
			json.put("IdProdutoRa",idProdutoRa);
			json.put("IdNaturezaProdutoPa",idNaturezaProdutoPa);
		
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
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdOportunidadeDia=" + getIdOportunidadeDia() 
		+  " UrlProduto=" + getUrlProduto() 
		+  " NomeProduto=" + getNomeProduto() 
		+  " DataInicioPrecoAtual=" + getDataInicioPrecoAtual() 
		+  " NomeMarca=" + getNomeMarca() 
		+  " UrlAfiliado=" + getUrlAfiliado() 
		+  " DataUltimaPrecoAnterior=" + getDataUltimaPrecoAnterior() 
		+  " ImagemLocal=" + getImagemLocal() 
		+  " UrlImagem=" + getUrlImagem() 
		+  " PosicaoProduto=" + getPosicaoProduto() 
		+  " PrecoVendaAnterior=" + getPrecoVendaAnterior() 
		+  " PrecoVendaAtual=" + getPrecoVendaAtual() 
		+  " PrecoBoletoAnterior=" + getPrecoBoletoAnterior() 
		+  " PrecoBoletoAtual=" + getPrecoBoletoAtual() 
		+  " PrecoParcelaAnterior=" + getPrecoParcelaAnterior() 
		+  " PrecoParcelaAtual=" + getPrecoParcelaAtual() 
		+  " QuantidadeParcelaAnterior=" + getQuantidadeParcelaAnterior() 
		+  " QuantidadeParcelaAtual=" + getQuantidadeParcelaAtual() 
		+  " PercentualAjusteVenda=" + getPercentualAjusteVenda() 
		+  " PercentualAjusteBoleto=" + getPercentualAjusteBoleto() 
		+  " NomeLojaVirtual=" + getNomeLojaVirtual() 
		+  " IdProdutoRa=" + getIdProdutoRa() 
		+  " IdNaturezaProdutoPa=" + getIdNaturezaProdutoPa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idOportunidadeDia;
 	}
 	public String getNomeClasse() {
 		return "OportunidadeDia";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private OportunidadeDiaDisplay display = null;
	private OportunidadeDiaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new OportunidadeDiaDisplay(this);
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
	private OportunidadeDiaAgregado agregado = null;
	private OportunidadeDiaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new OportunidadeDiaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private OportunidadeDiaDerivada derivada = null;
	private OportunidadeDiaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new OportunidadeDiaDerivada(this);
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
  	
		public Produto getProduto_ReferenteA() {
			return getAgregado().getProduto_ReferenteA();
		}
		public void setProduto_ReferenteA(Produto item) {
			getAgregado().setProduto_ReferenteA(item);
		}
		
		public void addListaProduto_ReferenteA(Produto item) {
			getAgregado().addListaProduto_ReferenteA(item);
		}
		public Produto getCorrenteProduto_ReferenteA() {
			return getAgregado().getCorrenteProduto_ReferenteA();
		}
		
		
		public NaturezaProduto getNaturezaProduto_PertenceA() {
			return getAgregado().getNaturezaProduto_PertenceA();
		}
		public void setNaturezaProduto_PertenceA(NaturezaProduto item) {
			getAgregado().setNaturezaProduto_PertenceA(item);
		}
		
		public void addListaNaturezaProduto_PertenceA(NaturezaProduto item) {
			getAgregado().addListaNaturezaProduto_PertenceA(item);
		}
		public NaturezaProduto getCorrenteNaturezaProduto_PertenceA() {
			return getAgregado().getCorrenteNaturezaProduto_PertenceA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public OportunidadeDiaVo(JSONObject json) throws JSONException{
		idOportunidadeDia =  ConversorJson.getInteger(json, "IdOportunidadeDia");
		urlProduto =  ConversorJson.getString(json, "UrlProduto");
		nomeProduto =  ConversorJson.getString(json, "NomeProduto");
		dataInicioPrecoAtual =  ConversorJson.getTimestampData(json, "DataInicioPrecoAtual");
		nomeMarca =  ConversorJson.getString(json, "NomeMarca");
		urlAfiliado =  ConversorJson.getString(json, "UrlAfiliado");
		dataUltimaPrecoAnterior =  ConversorJson.getTimestampData(json, "DataUltimaPrecoAnterior");
		imagemLocal =  ConversorJson.getString(json, "ImagemLocal");
		urlImagem =  ConversorJson.getString(json, "UrlImagem");
		posicaoProduto =  ConversorJson.getInteger(json, "PosicaoProduto");
		precoVendaAnterior =  ConversorJson.getFloat(json, "PrecoVendaAnterior");
		precoVendaAtual =  ConversorJson.getFloat(json, "PrecoVendaAtual");
		precoBoletoAnterior =  ConversorJson.getFloat(json, "PrecoBoletoAnterior");
		precoBoletoAtual =  ConversorJson.getFloat(json, "PrecoBoletoAtual");
		precoParcelaAnterior =  ConversorJson.getFloat(json, "PrecoParcelaAnterior");
		precoParcelaAtual =  ConversorJson.getFloat(json, "PrecoParcelaAtual");
		quantidadeParcelaAnterior =  ConversorJson.getInteger(json, "QuantidadeParcelaAnterior");
		quantidadeParcelaAtual =  ConversorJson.getInteger(json, "QuantidadeParcelaAtual");
		percentualAjusteVenda =  ConversorJson.getFloat(json, "PercentualAjusteVenda");
		percentualAjusteBoleto =  ConversorJson.getFloat(json, "PercentualAjusteBoleto");
		nomeLojaVirtual =  ConversorJson.getString(json, "NomeLojaVirtual");
		idProdutoRa =  ConversorJson.getInteger(json, "IdProdutoRa");
		idNaturezaProdutoPa =  ConversorJson.getInteger(json, "IdNaturezaProdutoPa");
  	}
  	public String toString() {
  		return "" + nomeProduto;
  	}
	private long idOportunidadeDia;
	public long getIdOportunidadeDia() {
		return idOportunidadeDia;
	}
	public void setIdOportunidadeDia(long _valor) {
		idOportunidadeDia = _valor;
	}


	private String urlProduto;
	public String getUrlProduto() {
		return urlProduto;
	}
	public void setUrlProduto(String _valor) {
		urlProduto = _valor;
	}


	private String nomeProduto;
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String _valor) {
		nomeProduto = _valor;
	}


	private Timestamp dataInicioPrecoAtual;
	public Timestamp getDataInicioPrecoAtual() {
		return dataInicioPrecoAtual;
	}
	public void setDataInicioPrecoAtual(Timestamp _valor) {
		dataInicioPrecoAtual = _valor;
	}


	public String getDataInicioPrecoAtualDDMMAAAA() {
		if (dataInicioPrecoAtual==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataInicioPrecoAtual);
    }
    public void setDataInicioPrecoAtualDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInicioPrecoAtual = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private String nomeMarca;
	public String getNomeMarca() {
		return nomeMarca;
	}
	public void setNomeMarca(String _valor) {
		nomeMarca = _valor;
	}


	private String urlAfiliado;
	public String getUrlAfiliado() {
		return urlAfiliado;
	}
	public void setUrlAfiliado(String _valor) {
		urlAfiliado = _valor;
	}


	private Timestamp dataUltimaPrecoAnterior;
	public Timestamp getDataUltimaPrecoAnterior() {
		return dataUltimaPrecoAnterior;
	}
	public void setDataUltimaPrecoAnterior(Timestamp _valor) {
		dataUltimaPrecoAnterior = _valor;
	}


	public String getDataUltimaPrecoAnteriorDDMMAAAA() {
		if (dataUltimaPrecoAnterior==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataUltimaPrecoAnterior);
    }
    public void setDataUltimaPrecoAnteriorDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaPrecoAnterior = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private String imagemLocal;
	public String getImagemLocal() {
		return imagemLocal;
	}
	public void setImagemLocal(String _valor) {
		imagemLocal = _valor;
	}


	private String urlImagem;
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String _valor) {
		urlImagem = _valor;
	}


	private long posicaoProduto;
	public long getPosicaoProduto() {
		return posicaoProduto;
	}
	public void setPosicaoProduto(long _valor) {
		posicaoProduto = _valor;
	}


	private float precoVendaAnterior;
	public float getPrecoVendaAnterior() {
		return precoVendaAnterior;
	}
	public void setPrecoVendaAnterior(float _valor) {
		precoVendaAnterior = _valor;
	}
	
	public String getPrecoVendaAnteriorTela() {
		return String.format("%.2f",precoVendaAnterior).replace(".", ",");
	}

	private float precoVendaAtual;
	public float getPrecoVendaAtual() {
		return precoVendaAtual;
	}
	public void setPrecoVendaAtual(float _valor) {
		precoVendaAtual = _valor;
	}
	
	public String getPrecoVendaAtualTela() {
		return String.format("%.2f",precoVendaAtual).replace(".", ",");
	}

	private float precoBoletoAnterior;
	public float getPrecoBoletoAnterior() {
		return precoBoletoAnterior;
	}
	public void setPrecoBoletoAnterior(float _valor) {
		precoBoletoAnterior = _valor;
	}
	
	public String getPrecoBoletoAnteriorTela() {
		return String.format("%.2f",precoBoletoAnterior).replace(".", ",");
	}

	private float precoBoletoAtual;
	public float getPrecoBoletoAtual() {
		return precoBoletoAtual;
	}
	public void setPrecoBoletoAtual(float _valor) {
		precoBoletoAtual = _valor;
	}
	
	public String getPrecoBoletoAtualTela() {
		return String.format("%.2f",precoBoletoAtual).replace(".", ",");
	}

	private float precoParcelaAnterior;
	public float getPrecoParcelaAnterior() {
		return precoParcelaAnterior;
	}
	public void setPrecoParcelaAnterior(float _valor) {
		precoParcelaAnterior = _valor;
	}
	
	public String getPrecoParcelaAnteriorTela() {
		return String.format("%.2f",precoParcelaAnterior).replace(".", ",");
	}

	private float precoParcelaAtual;
	public float getPrecoParcelaAtual() {
		return precoParcelaAtual;
	}
	public void setPrecoParcelaAtual(float _valor) {
		precoParcelaAtual = _valor;
	}
	
	public String getPrecoParcelaAtualTela() {
		return String.format("%.2f",precoParcelaAtual).replace(".", ",");
	}

	private long quantidadeParcelaAnterior;
	public long getQuantidadeParcelaAnterior() {
		return quantidadeParcelaAnterior;
	}
	public void setQuantidadeParcelaAnterior(long _valor) {
		quantidadeParcelaAnterior = _valor;
	}


	private long quantidadeParcelaAtual;
	public long getQuantidadeParcelaAtual() {
		return quantidadeParcelaAtual;
	}
	public void setQuantidadeParcelaAtual(long _valor) {
		quantidadeParcelaAtual = _valor;
	}


	private float percentualAjusteVenda;
	public float getPercentualAjusteVenda() {
		return percentualAjusteVenda;
	}
	public void setPercentualAjusteVenda(float _valor) {
		percentualAjusteVenda = _valor;
	}
	
	public String getPercentualAjusteVendaTela() {
		return String.format("%.2f",percentualAjusteVenda).replace(".", ",");
	}

	private float percentualAjusteBoleto;
	public float getPercentualAjusteBoleto() {
		return percentualAjusteBoleto;
	}
	public void setPercentualAjusteBoleto(float _valor) {
		percentualAjusteBoleto = _valor;
	}
	
	public String getPercentualAjusteBoletoTela() {
		return String.format("%.2f",percentualAjusteBoleto).replace(".", ",");
	}

	private String nomeLojaVirtual;
	public String getNomeLojaVirtual() {
		return nomeLojaVirtual;
	}
	public void setNomeLojaVirtual(String _valor) {
		nomeLojaVirtual = _valor;
	}

	
	private long idProdutoRa;
	public long getIdProdutoRa() {
		// relacionamento
		if (idProdutoRa==0 && this.getProduto_ReferenteA()!=null) 
			return getProduto_ReferenteA().getId();
		else
			return idProdutoRa;
	}
	public void setIdProdutoRa(long _valor) {
		idProdutoRa = _valor;
	}

	
	private long idNaturezaProdutoPa;
	public long getIdNaturezaProdutoPa() {
		// relacionamento
		if (idNaturezaProdutoPa==0 && this.getNaturezaProduto_PertenceA()!=null) 
			return getNaturezaProduto_PertenceA().getId();
		else
			return idNaturezaProdutoPa;
	}
	public void setIdNaturezaProdutoPa(long _valor) {
		idNaturezaProdutoPa = _valor;
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
  	
	public long getIdProdutoRaLazyLoader() {
		return idProdutoRa;
	} 
		
	public long getIdNaturezaProdutoPaLazyLoader() {
		return idNaturezaProdutoPa;
	} 
		
}