package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class ParcelaVendaAgregado implements ParcelaVendaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ParcelaVendaCarregador carregador = null;
	private ParcelaVendaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ParcelaVendaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Venda_PertenceA != null)
			vo.Venda_PertenceA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ParcelaVenda vo;
	public ParcelaVendaAgregado(ParcelaVenda item) {
		vo = item;
	}
	
	
	
	private Venda vendaPertenceA;
	public void setVenda_PertenceA(Venda valor)
	{	
		vo.setIdVendaPa(0);
		vendaPertenceA = valor;
	} 
	public Venda getVenda_PertenceA() 
	{	
	//	if (vendaPertenceA==null &&
	//			vo.getIdVendaPaLazyLoader() !=0)
	//	{
	//		VendaServico srv = FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
	//		vendaPertenceA = srv.getById(vo.getIdVendaPaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "ParcelaVenda.getVenda_PertenceA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return vendaPertenceA;
	} 
	
	public void addListaVenda_PertenceA(Venda value)
	{	
		vendaPertenceA = value;
	} 
	public Venda getCorrenteVenda_PertenceA()
	{	
		return vendaPertenceA;
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
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "ParcelaVenda.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
