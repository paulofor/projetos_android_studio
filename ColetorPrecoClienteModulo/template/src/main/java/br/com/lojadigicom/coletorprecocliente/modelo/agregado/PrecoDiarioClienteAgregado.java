package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class PrecoDiarioClienteAgregado implements PrecoDiarioClienteAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PrecoDiarioClienteCarregador carregador = null;
	private PrecoDiarioClienteCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PrecoDiarioClienteCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.ProdutoCliente_PertenceA != null)
			vo.ProdutoCliente_PertenceA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private PrecoDiarioCliente vo;
	public PrecoDiarioClienteAgregado(PrecoDiarioCliente item) {
		vo = item;
	}
	
	
	
	private ProdutoCliente produtoClientePertenceA;
	public void setProdutoCliente_PertenceA(ProdutoCliente valor)
	{	
		produtoClientePertenceA = valor;
	} 
	public ProdutoCliente getProdutoCliente_PertenceA() 
	{	
	//	if (produtoClientePertenceA==null &&
	//			vo.getIdProdutoClientePaLazyLoader() !=0)
	//	{
	//		ProdutoClienteServico srv = FabricaServico.getInstancia().getProdutoClienteServico(FabricaServico.TIPO_SQLITE);
	//		produtoClientePertenceA = srv.getById(vo.getIdProdutoClientePaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "PrecoDiarioCliente.getProdutoCliente_PertenceA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return produtoClientePertenceA;
	} 
	
	public void addListaProdutoCliente_PertenceA(ProdutoCliente value)
	{	
		produtoClientePertenceA = value;
	} 
	public ProdutoCliente getCorrenteProdutoCliente_PertenceA()
	{	
		return produtoClientePertenceA;
	} 
	
	
	
	private Usuario usuarioSincroniza;
	public void setUsuario_Sincroniza(Usuario valor)
	{	
		usuarioSincroniza = valor;
	} 
	public Usuario getUsuario_Sincroniza() 
	{	
	//	if (usuarioSincroniza==null &&
	//			vo.getIdUsuarioSLazyLoader() !=0)
	//	{
	//		UsuarioServico srv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
	//		usuarioSincroniza = srv.getById(vo.getIdUsuarioSLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "PrecoDiarioCliente.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return usuarioSincroniza;
	} 
	
	public void addListaUsuario_Sincroniza(Usuario value)
	{	
		usuarioSincroniza = value;
	} 
	public Usuario getCorrenteUsuario_Sincroniza()
	{	
		return usuarioSincroniza;
	} 
	
	
 	
 	
 	
 	
 	
}
