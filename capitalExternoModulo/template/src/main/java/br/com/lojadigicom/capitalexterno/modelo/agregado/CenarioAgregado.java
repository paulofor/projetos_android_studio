package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class CenarioAgregado implements CenarioAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private CenarioCarregador carregador = null;
	private CenarioCarregador getCarregador() {
		if (carregador==null) {
			carregador = new CenarioCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private Cenario vo;
	public CenarioAgregado(Cenario item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
