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
import repcom.modelo.agregado.VendaAgregado;
import repcom.modelo.derivada.impl.VendaDerivada;
import repcom.modelo.display.impl.VendaDisplay;

public class VendaVo implements Venda , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idVenda;
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
  	
  
  	public VendaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdVenda\" : \"" + idVenda + "\" "
		+ ", \"Data\" : \"" + data + "\" "
		+ ", \"ValorTotal\" : \"" + valorTotal + "\" "
		+ ", \"IdClienteFp\" : \"" + idClienteFp + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdVenda",idVenda);
			json.put("Data",data);
			json.put("ValorTotal",valorTotal);
			json.put("IdClienteFp",idClienteFp);
			json.put("IdUsuarioS",idUsuarioS);
		
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
		JSONArray listaProdutoVenda_Associada = JSonListaProdutoVenda_Associada();
		if (listaProdutoVenda_Associada!=null) {
			json.put("ListaProdutoVendaVo_Associada",listaProdutoVenda_Associada);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaProdutoVenda_Associada() throws JSONException{
		if (getListaProdutoVenda_Associada()==null) return null;
		JSONArray lista = new JSONArray();
		for (ProdutoVenda item:this.getListaProdutoVenda_AssociadaOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdVenda=" + getIdVenda() 
		+  " Data=" + getData() 
		+  " ValorTotal=" + getValorTotal() 
		+  " IdClienteFp=" + getIdClienteFp() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idVenda;
 	}
 	public String getNomeClasse() {
 		return "Venda";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private VendaDisplay display = null;
	private VendaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new VendaDisplay(this);
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
	private VendaAgregado agregado = null;
	private VendaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new VendaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private VendaDerivada derivada = null;
	private VendaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new VendaDerivada(this);
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
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public VendaVo(JSONObject json) throws JSONException{
		idVenda =  ConversorJson.getInteger(json, "IdVenda");
		data =  ConversorJson.getTimestampData(json, "Data");
		valorTotal =  ConversorJson.getFloat(json, "ValorTotal");
		idClienteFp =  ConversorJson.getInteger(json, "IdClienteFp");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
  	}
  	public String toString() {
  		return "" + idVenda;
  	}
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




	private float valorTotal;
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float _valor) {
		valorTotal = _valor;
	}
	
	public String getValorTotalTela() {
		return String.format("%.2f",valorTotal).replace(".", ",");
	}
	
	private long idClienteFp;
	public long getIdClienteFp() {
		// relacionamento
		if (idClienteFp==0 && this.getCliente_FeitaPara()!=null) 
			return getCliente_FeitaPara().getId();
		else
			return idClienteFp;
	}
	public void setIdClienteFp(long _valor) {
		idClienteFp = _valor;
	}

	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento
		if (idUsuarioS==0 && this.getUsuario_Sincroniza()!=null) 
			return getUsuario_Sincroniza().getId();
		else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
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
  	
	public long getIdClienteFpLazyLoader() {
		return idClienteFp;
	} 
		
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
}