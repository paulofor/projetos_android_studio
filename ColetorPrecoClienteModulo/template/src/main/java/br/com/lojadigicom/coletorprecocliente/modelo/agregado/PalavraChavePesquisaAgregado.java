package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class PalavraChavePesquisaAgregado implements PalavraChavePesquisaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PalavraChavePesquisaCarregador carregador = null;
	private PalavraChavePesquisaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PalavraChavePesquisaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.NaturezaProduto_ReferenteA != null)
			vo.NaturezaProduto_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private PalavraChavePesquisa vo;
	public PalavraChavePesquisaAgregado(PalavraChavePesquisa item) {
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
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "PalavraChavePesquisa.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
	
	
	
	private NaturezaProduto naturezaProdutoReferenteA;
	public void setNaturezaProduto_ReferenteA(NaturezaProduto valor)
	{	
		naturezaProdutoReferenteA = valor;
	} 
	public NaturezaProduto getNaturezaProduto_ReferenteA() 
	{	
	//	if (naturezaProdutoReferenteA==null &&
	//			vo.getIdNaturezaProdutoRaLazyLoader() !=0)
	//	{
	//		NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
	//		naturezaProdutoReferenteA = srv.getById(vo.getIdNaturezaProdutoRaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "PalavraChavePesquisa.getNaturezaProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return naturezaProdutoReferenteA;
	} 
	
	public void addListaNaturezaProduto_ReferenteA(NaturezaProduto value)
	{	
		naturezaProdutoReferenteA = value;
	} 
	public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA()
	{	
		return naturezaProdutoReferenteA;
	} 
	
	
 	
 	
 	
 	
 	
}
