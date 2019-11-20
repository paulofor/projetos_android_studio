
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
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.DispositivoUsuarioAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.DispositivoUsuarioContract;

public class DispositivoUsuarioVo implements DispositivoUsuario  {

	public DispositivoUsuarioVo() {
  	}
  	
  	public long getIdObj()
    {
       return idDispositivoUsuario;
    }

	 // ----- INICIO AGREGADO -----------------
	private DispositivoUsuarioAgregado agregado = null;
	private DispositivoUsuarioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new DispositivoUsuarioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idDispositivoUsuario;
	public long getIdDispositivoUsuario() {
		return idDispositivoUsuario;
	}
	public void setIdDispositivoUsuario(long _valor) {
		idDispositivoUsuario = _valor;
	}


	private String numeroCelular;
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String _valor) {
		numeroCelular = _valor;
	}


	private String codigoDispositivo;
	public String getCodigoDispositivo() {
		return codigoDispositivo;
	}
	public void setCodigoDispositivo(String _valor) {
		codigoDispositivo = _valor;
	}


	private String tipoAcesso;
	public String getTipoAcesso() {
		return tipoAcesso;
	}
	public void setTipoAcesso(String _valor) {
		tipoAcesso = _valor;
	}


	private String melhorPath;
	public String getMelhorPath() {
		return melhorPath;
	}
	public void setMelhorPath(String _valor) {
		melhorPath = _valor;
	}


	private String tokenGcm;
	public String getTokenGcm() {
		return tokenGcm;
	}
	public void setTokenGcm(String _valor) {
		tokenGcm = _valor;
	}


	private Timestamp dataCriacao;
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Timestamp _valor) {
		dataCriacao = _valor;
	}


	public String getDataCriacaoDDMMAAAA() {
		if (dataCriacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataCriacao);
    }




	public String getDataCriacaoHHMM() {
		if (dataCriacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataCriacao);
    }
    public String getDataCriacaoHHMMSS() {
		if (dataCriacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataCriacao);
    }
	private Timestamp dataUltimoAcesso;
	public Timestamp getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}
	public void setDataUltimoAcesso(Timestamp _valor) {
		dataUltimoAcesso = _valor;
	}


	public String getDataUltimoAcessoDDMMAAAA() {
		if (dataUltimoAcesso==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataUltimoAcesso);
    }




	public String getDataUltimoAcessoHHMM() {
		if (dataUltimoAcesso==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataUltimoAcesso);
    }
    public String getDataUltimoAcessoHHMMSS() {
		if (dataUltimoAcesso==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataUltimoAcesso);
    }
	private String tokenGcmMonitor;
	public String getTokenGcmMonitor() {
		return tokenGcmMonitor;
	}
	public void setTokenGcmMonitor(String _valor) {
		tokenGcmMonitor = _valor;
	}


	private long microSd;
	public long getMicroSd() {
		return microSd;
	}
	public void setMicroSd(long _valor) {
		microSd = _valor;
	}


	private Timestamp dataChamada;
	public Timestamp getDataChamada() {
		return dataChamada;
	}
	public void setDataChamada(Timestamp _valor) {
		dataChamada = _valor;
	}


	public String getDataChamadaDDMMAAAA() {
		if (dataChamada==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataChamada);
    }




	public String getDataChamadaHHMM() {
		if (dataChamada==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataChamada);
    }
    public String getDataChamadaHHMMSS() {
		if (dataChamada==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataChamada);
    }
	private boolean temMudanca;
	public boolean getTemMudanca() {
		return temMudanca;
	}
	public void setTemMudanca(boolean _valor) {
		temMudanca = _valor;
	}


	private boolean ativo;
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean _valor) {
		ativo = _valor;
	}

	
	private long idUsuarioRa;
	public long getIdUsuarioRa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getUsuario_ReferenteA()!=null) 
		//	return getUsuario_ReferenteA().getIdObj();
		//else
			return idUsuarioRa;
	}
	public void setIdUsuarioRa(long _valor) {
		idUsuarioRa = _valor;
	}

	
	private long idAppProdutoU;
	public long getIdAppProdutoU() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getAppProduto_Usa()!=null) 
		//	return getAppProduto_Usa().getIdObj();
		//else
			return idAppProdutoU;
	}
	public void setIdAppProdutoU(long _valor) {
		idAppProdutoU = _valor;
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
  	
	public long getIdUsuarioRaLazyLoader() {
		return idUsuarioRa;
	} 
		
	public long getIdAppProdutoULazyLoader() {
		return idAppProdutoU;
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
  	
		public Usuario getUsuario_ReferenteA() {
			return getAgregado().getUsuario_ReferenteA();
		}
		public void setUsuario_ReferenteA(Usuario item) {
			// Coloquei em 10-11-2016
			idUsuarioRa = item.getIdObj();
			getAgregado().setUsuario_ReferenteA(item);
		}
		
		public void addListaUsuario_ReferenteA(Usuario item) {
			getAgregado().addListaUsuario_ReferenteA(item);
		}
		public Usuario getCorrenteUsuario_ReferenteA() {
			return getAgregado().getCorrenteUsuario_ReferenteA();
		}
		
		
		public AppProduto getAppProduto_Usa() {
			return getAgregado().getAppProduto_Usa();
		}
		public void setAppProduto_Usa(AppProduto item) {
			// Coloquei em 10-11-2016
			idAppProdutoU = item.getIdObj();
			getAgregado().setAppProduto_Usa(item);
		}
		
		public void addListaAppProduto_Usa(AppProduto item) {
			getAgregado().addListaAppProduto_Usa(item);
		}
		public AppProduto getCorrenteAppProduto_Usa() {
			return getAgregado().getCorrenteAppProduto_Usa();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(DispositivoUsuarioContract.COLUNA_ID_DISPOSITIVO_USUARIO, idDispositivoUsuario);
    	valores.put(DispositivoUsuarioContract.COLUNA_NUMERO_CELULAR, numeroCelular);
    	valores.put(DispositivoUsuarioContract.COLUNA_CODIGO_DISPOSITIVO, codigoDispositivo);
    	valores.put(DispositivoUsuarioContract.COLUNA_TIPO_ACESSO, tipoAcesso);
    	valores.put(DispositivoUsuarioContract.COLUNA_MELHOR_PATH, melhorPath);
    	valores.put(DispositivoUsuarioContract.COLUNA_TOKEN_GCM, tokenGcm);
    	valores.put(DispositivoUsuarioContract.COLUNA_DATA_CRIACAO, UtilDatas.getDataHoraLong(dataCriacao));
    	valores.put(DispositivoUsuarioContract.COLUNA_DATA_ULTIMO_ACESSO, UtilDatas.getDataHoraLong(dataUltimoAcesso));
    	valores.put(DispositivoUsuarioContract.COLUNA_TOKEN_GCM_MONITOR, tokenGcmMonitor);
    	valores.put(DispositivoUsuarioContract.COLUNA_MICRO_SD, microSd);
    	valores.put(DispositivoUsuarioContract.COLUNA_DATA_CHAMADA, UtilDatas.getDataHoraLong(dataChamada));
    	valores.put(DispositivoUsuarioContract.COLUNA_TEM_MUDANCA, temMudanca);
    	valores.put(DispositivoUsuarioContract.COLUNA_ATIVO, ativo);
		valores.put(DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA, idUsuarioRa);
	
		valores.put(DispositivoUsuarioContract.COLUNA_ID_APP_PRODUTO_U, idAppProdutoU);
	
		return valores;
  	}
}