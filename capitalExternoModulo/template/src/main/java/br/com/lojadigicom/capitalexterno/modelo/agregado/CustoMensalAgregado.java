package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class CustoMensalAgregado implements CustoMensalAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private CustoMensalCarregador carregador = null;
	private CustoMensalCarregador getCarregador() {
		if (carregador==null) {
			carregador = new CustoMensalCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private CustoMensal vo;
	public CustoMensalAgregado(CustoMensal item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
