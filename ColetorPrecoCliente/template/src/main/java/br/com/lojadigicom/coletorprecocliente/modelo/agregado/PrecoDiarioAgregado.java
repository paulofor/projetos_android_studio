package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class PrecoDiarioAgregado implements PrecoDiarioAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PrecoDiarioCarregador carregador = null;
	private PrecoDiarioCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PrecoDiarioCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private PrecoDiario vo;
	public PrecoDiarioAgregado(PrecoDiario item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
