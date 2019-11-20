package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class InteresseProdutoAgregado implements InteresseProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private InteresseProdutoCarregador carregador = null;
	private InteresseProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new InteresseProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.ProdutoCliente_ReferenteA != null)
			vo.ProdutoCliente_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private InteresseProduto vo;
	public InteresseProdutoAgregado(InteresseProduto item) {
		vo = item;
	}
	
	
	
	private ProdutoCliente produtoClienteReferenteA;
	public void setProdutoCliente_ReferenteA(ProdutoCliente valor)
	{	
		vo.setIdProdutoClienteRa(0);
		produtoClienteReferenteA = valor;
	} 
	public ProdutoCliente getProdutoCliente_ReferenteA() 
	{	
	//	if (produtoClienteReferenteA==null &&
	//			vo.getIdProdutoClienteRaLazyLoader() !=0)
	//	{
	//		ProdutoClienteServico srv = FabricaServico.getInstancia().getProdutoClienteServico(FabricaServico.TIPO_SQLITE);
	//		produtoClienteReferenteA = srv.getById(vo.getIdProdutoClienteRaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "InteresseProduto.getProdutoCliente_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return produtoClienteReferenteA;
	} 
	
	public void addListaProdutoCliente_ReferenteA(ProdutoCliente value)
	{	
		produtoClienteReferenteA = value;
	} 
	public ProdutoCliente getCorrenteProdutoCliente_ReferenteA()
	{	
		return produtoClienteReferenteA;
	} 
	
	
	
	private Usuario usuarioSincroniza;
	public void setUsuario_Sincroniza(Usuario valor)
	{	
		vo.setIdUsuarioS(0);
		usuarioSincroniza = valor;
	} 
	public Usuario getUsuario_Sincroniza() 
	{	
	//	if (usuarioSincroniza==null &&
	//			vo.getIdUsuarioSLazyLoader() !=0)
	//	{
	//		UsuarioServico srv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
	//		usuarioSincroniza = srv.getById(vo.getIdUsuarioSLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "InteresseProduto.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
