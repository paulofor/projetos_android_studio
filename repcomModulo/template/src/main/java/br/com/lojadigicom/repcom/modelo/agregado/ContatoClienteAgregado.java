package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class ContatoClienteAgregado implements ContatoClienteAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ContatoClienteCarregador carregador = null;
	private ContatoClienteCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ContatoClienteCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Cliente_ReferenteA != null)
			vo.Cliente_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ContatoCliente vo;
	public ContatoClienteAgregado(ContatoCliente item) {
		vo = item;
	}
	
	
	
	private Cliente clienteReferenteA;
	public void setCliente_ReferenteA(Cliente valor)
	{	
		clienteReferenteA = valor;
	} 
	public Cliente getCliente_ReferenteA() 
	{	
	//	if (clienteReferenteA==null &&
	//			vo.getIdClienteRaLazyLoader() !=0)
	//	{
	//		ClienteServico srv = FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
	//		clienteReferenteA = srv.getById(vo.getIdClienteRaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "ContatoCliente.getCliente_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return clienteReferenteA;
	} 
	
	public void addListaCliente_ReferenteA(Cliente value)
	{	
		clienteReferenteA = value;
	} 
	public Cliente getCorrenteCliente_ReferenteA()
	{	
		return clienteReferenteA;
	} 
	
	
 	
 	
 	
 	
 	
}
