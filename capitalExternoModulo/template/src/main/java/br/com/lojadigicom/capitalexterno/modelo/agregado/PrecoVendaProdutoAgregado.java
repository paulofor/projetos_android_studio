package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class PrecoVendaProdutoAgregado implements PrecoVendaProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PrecoVendaProdutoCarregador carregador = null;
	private PrecoVendaProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PrecoVendaProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private PrecoVendaProduto vo;
	public PrecoVendaProdutoAgregado(PrecoVendaProduto item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
