package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class PagamentoFornecedorAgregado implements PagamentoFornecedorAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PagamentoFornecedorCarregador carregador = null;
	private PagamentoFornecedorCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PagamentoFornecedorCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private PagamentoFornecedor vo;
	public PagamentoFornecedorAgregado(PagamentoFornecedor item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
