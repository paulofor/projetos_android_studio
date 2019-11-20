package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class EstoqueAgregado implements EstoqueAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private EstoqueCarregador carregador = null;
	private EstoqueCarregador getCarregador() {
		if (carregador==null) {
			carregador = new EstoqueCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private Estoque vo;
	public EstoqueAgregado(Estoque item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
