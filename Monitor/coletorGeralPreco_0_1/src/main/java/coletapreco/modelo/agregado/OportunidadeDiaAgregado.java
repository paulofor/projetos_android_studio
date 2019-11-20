package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class OportunidadeDiaAgregado implements OportunidadeDiaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private OportunidadeDiaCarregador carregador = null;
	private OportunidadeDiaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new OportunidadeDiaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Produto_ReferenteA != null)
			vo.Produto_ReferenteA.setConexaoCarregador(conexao);
		if (vo.NaturezaProduto_PertenceA != null)
			vo.NaturezaProduto_PertenceA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private OportunidadeDia vo;
	public OportunidadeDiaAgregado(OportunidadeDia item) {
		vo = item;
	}
	
	
	
	private Produto produtoReferenteA;
	public void setProduto_ReferenteA(Produto valor)
	{	
		vo.setIdProdutoRa(0);
		produtoReferenteA = valor;
	} 
	public Produto getProduto_ReferenteA() 
	{	
		
		if (produtoReferenteA==null &&
				vo.getIdProdutoRaLazyLoader() !=0)
		{
			ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
			produtoReferenteA = srv.getById(vo.getIdProdutoRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "OportunidadeDia.getProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
		return produtoReferenteA;
	} 
	
	public void addListaProduto_ReferenteA(Produto value)
	{	
		produtoReferenteA = value;
	} 
	public Produto getCorrenteProduto_ReferenteA()
	{	
		return produtoReferenteA;
	} 
	
	
	
	private NaturezaProduto naturezaProdutoPertenceA;
	public void setNaturezaProduto_PertenceA(NaturezaProduto valor)
	{	
		vo.setIdNaturezaProdutoPa(0);
		naturezaProdutoPertenceA = valor;
	} 
	public NaturezaProduto getNaturezaProduto_PertenceA() 
	{	
		
		if (naturezaProdutoPertenceA==null &&
				vo.getIdNaturezaProdutoPaLazyLoader() !=0)
		{
			NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
			naturezaProdutoPertenceA = srv.getById(vo.getIdNaturezaProdutoPaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "OportunidadeDia.getNaturezaProduto_PertenceA()",QTDE_LOG_LAZY_LOADER);
  		}
		return naturezaProdutoPertenceA;
	} 
	
	public void addListaNaturezaProduto_PertenceA(NaturezaProduto value)
	{	
		naturezaProdutoPertenceA = value;
	} 
	public NaturezaProduto getCorrenteNaturezaProduto_PertenceA()
	{	
		return naturezaProdutoPertenceA;
	} 
	
	
 	
 	
 	
 	
 	
}
