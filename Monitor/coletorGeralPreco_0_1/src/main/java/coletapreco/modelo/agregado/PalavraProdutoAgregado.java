package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class PalavraProdutoAgregado implements PalavraProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PalavraProdutoCarregador carregador = null;
	private PalavraProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PalavraProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Palavra_RelaciondoA != null)
			vo.Palavra_RelaciondoA.setConexaoCarregador(conexao);
		if (vo.Produto_RelaciondoA != null)
			vo.Produto_RelaciondoA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private PalavraProduto vo;
	public PalavraProdutoAgregado(PalavraProduto item) {
		vo = item;
	}
	
	
	
	private Palavra palavraRelaciondoA;
	public void setPalavra_RelaciondoA(Palavra valor)
	{	
		vo.setIdPalavraRa(0);
		palavraRelaciondoA = valor;
	} 
	public Palavra getPalavra_RelaciondoA() 
	{	
		
		if (palavraRelaciondoA==null &&
				vo.getIdPalavraRaLazyLoader() !=0)
		{
			PalavraServico srv = FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_SQLITE);
			palavraRelaciondoA = srv.getById(vo.getIdPalavraRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "PalavraProduto.getPalavra_RelaciondoA()",QTDE_LOG_LAZY_LOADER);
  		}
		return palavraRelaciondoA;
	} 
	
	public void addListaPalavra_RelaciondoA(Palavra value)
	{	
		palavraRelaciondoA = value;
	} 
	public Palavra getCorrentePalavra_RelaciondoA()
	{	
		return palavraRelaciondoA;
	} 
	
	
	
	private Produto produtoRelaciondoA;
	public void setProduto_RelaciondoA(Produto valor)
	{	
		vo.setIdProdutoRa(0);
		produtoRelaciondoA = valor;
	} 
	public Produto getProduto_RelaciondoA() 
	{	
		
		if (produtoRelaciondoA==null &&
				vo.getIdProdutoRaLazyLoader() !=0)
		{
			ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
			produtoRelaciondoA = srv.getById(vo.getIdProdutoRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "PalavraProduto.getProduto_RelaciondoA()",QTDE_LOG_LAZY_LOADER);
  		}
		return produtoRelaciondoA;
	} 
	
	public void addListaProduto_RelaciondoA(Produto value)
	{	
		produtoRelaciondoA = value;
	} 
	public Produto getCorrenteProduto_RelaciondoA()
	{	
		return produtoRelaciondoA;
	} 
	
	
 	
 	
 	
 	
 	
}
