package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class DispositivoUsuarioAgregado implements DispositivoUsuarioAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private DispositivoUsuarioCarregador carregador = null;
	private DispositivoUsuarioCarregador getCarregador() {
		if (carregador==null) {
			carregador = new DispositivoUsuarioCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Usuario_ReferenteA != null)
			vo.Usuario_ReferenteA.setConexaoCarregador(conexao);
		if (vo.AppProduto_Usa != null)
			vo.AppProduto_Usa.setConexaoCarregador(conexao);
		
	}
	*/
	
	private DispositivoUsuario vo;
	public DispositivoUsuarioAgregado(DispositivoUsuario item) {
		vo = item;
	}
	
	
	
	private Usuario usuarioReferenteA;
	public void setUsuario_ReferenteA(Usuario valor)
	{	
		usuarioReferenteA = valor;
	} 
	public Usuario getUsuario_ReferenteA() 
	{	
	//	if (usuarioReferenteA==null &&
	//			vo.getIdUsuarioRaLazyLoader() !=0)
	//	{
	//		UsuarioServico srv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
	//		usuarioReferenteA = srv.getById(vo.getIdUsuarioRaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "DispositivoUsuario.getUsuario_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return usuarioReferenteA;
	} 
	
	public void addListaUsuario_ReferenteA(Usuario value)
	{	
		usuarioReferenteA = value;
	} 
	public Usuario getCorrenteUsuario_ReferenteA()
	{	
		return usuarioReferenteA;
	} 
	
	
	
	private AppProduto appProdutoUsa;
	public void setAppProduto_Usa(AppProduto valor)
	{	
		appProdutoUsa = valor;
	} 
	public AppProduto getAppProduto_Usa() 
	{	
	//	if (appProdutoUsa==null &&
	//			vo.getIdAppProdutoULazyLoader() !=0)
	//	{
	//		AppProdutoServico srv = FabricaServico.getInstancia().getAppProdutoServico(FabricaServico.TIPO_SQLITE);
	//		appProdutoUsa = srv.getById(vo.getIdAppProdutoULazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "DispositivoUsuario.getAppProduto_Usa()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return appProdutoUsa;
	} 
	
	public void addListaAppProduto_Usa(AppProduto value)
	{	
		appProdutoUsa = value;
	} 
	public AppProduto getCorrenteAppProduto_Usa()
	{	
		return appProdutoUsa;
	} 
	
	
 	
 	
 	
 	
 	
}
