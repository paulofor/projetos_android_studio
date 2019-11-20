package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.modelo.*;


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
		
	}
	*/
	
	private OportunidadeDia vo;
	public OportunidadeDiaAgregado(OportunidadeDia item) {
		vo = item;
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
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "OportunidadeDia.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
