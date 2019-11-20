package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class ProdutoAgregado implements ProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ProdutoCarregador carregador = null;
	private ProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.LinhaProduto_PertenceA != null)
			vo.LinhaProduto_PertenceA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private Produto vo;
	public ProdutoAgregado(Produto item) {
		vo = item;
	}
	
	
	
	private LinhaProduto linhaProdutoPertenceA;
	public void setLinhaProduto_PertenceA(LinhaProduto valor)
	{	
		linhaProdutoPertenceA = valor;
	} 
	public LinhaProduto getLinhaProduto_PertenceA() 
	{	
	//	if (linhaProdutoPertenceA==null &&
	//			vo.getIdLinhaProdutoPaLazyLoader() !=0)
	//	{
	//		LinhaProdutoServico srv = FabricaServico.getInstancia().getLinhaProdutoServico(FabricaServico.TIPO_SQLITE);
	//		linhaProdutoPertenceA = srv.getById(vo.getIdLinhaProdutoPaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getLinhaProduto_PertenceA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return linhaProdutoPertenceA;
	} 
	
	public void addListaLinhaProduto_PertenceA(LinhaProduto value)
	{	
		linhaProdutoPertenceA = value;
	} 
	public LinhaProduto getCorrenteLinhaProduto_PertenceA()
	{	
		return linhaProdutoPertenceA;
	} 
	
	
 	
 	
 	
 	
 	
}
