package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class PrevisaoVendaAgregado implements PrevisaoVendaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PrevisaoVendaCarregador carregador = null;
	private PrevisaoVendaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PrevisaoVendaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private PrevisaoVenda vo;
	public PrevisaoVendaAgregado(PrevisaoVenda item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
