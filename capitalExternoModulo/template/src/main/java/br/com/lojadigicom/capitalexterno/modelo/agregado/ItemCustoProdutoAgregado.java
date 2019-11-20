package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class ItemCustoProdutoAgregado implements ItemCustoProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ItemCustoProdutoCarregador carregador = null;
	private ItemCustoProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ItemCustoProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private ItemCustoProduto vo;
	public ItemCustoProdutoAgregado(ItemCustoProduto item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
