package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class PrecoProdutoAgregado implements PrecoProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PrecoProdutoCarregador carregador = null;
	private PrecoProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PrecoProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Produto_PertenceA != null)
			vo.Produto_PertenceA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private PrecoProduto vo;
	public PrecoProdutoAgregado(PrecoProduto item) {
		vo = item;
	}
	
	
	
	private Produto produtoPertenceA;
	public void setProduto_PertenceA(Produto valor)
	{	
		vo.setIdProdutoPa(0);
		produtoPertenceA = valor;
	} 
	public Produto getProduto_PertenceA() 
	{	
		
		if (produtoPertenceA==null &&
				vo.getIdProdutoPaLazyLoader() !=0)
		{
			ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
			produtoPertenceA = srv.getById(vo.getIdProdutoPaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "PrecoProduto.getProduto_PertenceA()",QTDE_LOG_LAZY_LOADER);
  		}
		return produtoPertenceA;
	} 
	
	public void addListaProduto_PertenceA(Produto value)
	{	
		produtoPertenceA = value;
	} 
	public Produto getCorrenteProduto_PertenceA()
	{	
		return produtoPertenceA;
	} 
	
	
 	
 	
 	
 	
 	
}
