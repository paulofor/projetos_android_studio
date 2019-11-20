package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class UsuarioPesquisaAgregado implements UsuarioPesquisaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private UsuarioPesquisaCarregador carregador = null;
	private UsuarioPesquisaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new UsuarioPesquisaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.NaturezaProduto_Pesquisa != null)
			vo.NaturezaProduto_Pesquisa.setConexaoCarregador(conexao);
		
	}
	*/
	
	private UsuarioPesquisa vo;
	public UsuarioPesquisaAgregado(UsuarioPesquisa item) {
		vo = item;
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
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "UsuarioPesquisa.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
	
	
	
	private NaturezaProduto naturezaProdutoPesquisa;
	public void setNaturezaProduto_Pesquisa(NaturezaProduto valor)
	{	
		naturezaProdutoPesquisa = valor;
	} 
	public NaturezaProduto getNaturezaProduto_Pesquisa() 
	{	
	//	if (naturezaProdutoPesquisa==null &&
	//			vo.getIdNaturezaProdutoPLazyLoader() !=0)
	//	{
	//		NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
	//		naturezaProdutoPesquisa = srv.getById(vo.getIdNaturezaProdutoPLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "UsuarioPesquisa.getNaturezaProduto_Pesquisa()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return naturezaProdutoPesquisa;
	} 
	
	public void addListaNaturezaProduto_Pesquisa(NaturezaProduto value)
	{	
		naturezaProdutoPesquisa = value;
	} 
	public NaturezaProduto getCorrenteNaturezaProduto_Pesquisa()
	{	
		return naturezaProdutoPesquisa;
	} 
	
	
 	
 	
 	
 	
 	
}
