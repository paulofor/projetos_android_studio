package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class ClienteAgregado implements ClienteAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ClienteCarregador carregador = null;
	private ClienteCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ClienteCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private Cliente vo;
	public ClienteAgregado(Cliente item) {
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
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "Cliente.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
	
	
 	
 	
 	
 	
 	
	public boolean existeListaClienteInteresseCategoria_Associada() {
		return listaclienteInteresseCategoriaAssociada!= null; 
	}
	private List<ClienteInteresseCategoria> listaclienteInteresseCategoriaAssociada;
	private boolean daoListaclienteInteresseCategoriaAssociada = false;
	public void setListaClienteInteresseCategoria_Associada(List<ClienteInteresseCategoria> value)
	{	
		listaclienteInteresseCategoriaAssociada = value;
	}
	public void setListaClienteInteresseCategoria_AssociadaByDao(List<ClienteInteresseCategoria> value)
	{	
	//	listaclienteInteresseCategoriaAssociada = value;
	//	daoListaclienteInteresseCategoriaAssociada = (value!=null);
	}  
	public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada()
	{	
	//	if (!daoListaclienteInteresseCategoriaAssociada)
    //    {
    //    ClienteInteresseCategoriaServico srv = FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
	//	listaclienteInteresseCategoriaAssociada = srv.getPorAssociadaCliente(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Cliente.getListaClienteInteresseCategoria_Associada()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaclienteInteresseCategoriaAssociada;
	} 
	public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_AssociadaOriginal()
	{	
		return listaclienteInteresseCategoriaAssociada;
	} 
	public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada(int qtde)
	{	
    //    ClienteInteresseCategoriaServico srv = FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
	//	listaclienteInteresseCategoriaAssociada = srv.getPorAssociadaCliente(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Cliente.getListaClienteInteresseCategoria_Associada()",QTDE_LOG_LAZY_LOADER);
		return listaclienteInteresseCategoriaAssociada;
	} 
	public void addListaClienteInteresseCategoria_Associada(ClienteInteresseCategoria value) 
	{	
		criaVaziaListaClienteInteresseCategoria_Associada();
		listaclienteInteresseCategoriaAssociada.add(value);
	//	daoListaclienteInteresseCategoriaAssociada = true;
	} 
	public ClienteInteresseCategoria getCorrenteClienteInteresseCategoria_Associada()
	{	
		if (listaclienteInteresseCategoriaAssociada==null) return null;
		return listaclienteInteresseCategoriaAssociada.get(listaclienteInteresseCategoriaAssociada.size()-1);
	} 
	public void criaVaziaListaClienteInteresseCategoria_Associada() {
		if (listaclienteInteresseCategoriaAssociada == null)
        {
        	listaclienteInteresseCategoriaAssociada = new ArrayList<ClienteInteresseCategoria>();
        }
	}
	
	public boolean existeListaContatoCliente_Estabelece() {
		return listacontatoClienteEstabelece!= null; 
	}
	private List<ContatoCliente> listacontatoClienteEstabelece;
	private boolean daoListacontatoClienteEstabelece = false;
	public void setListaContatoCliente_Estabelece(List<ContatoCliente> value)
	{	
		listacontatoClienteEstabelece = value;
	}
	public void setListaContatoCliente_EstabeleceByDao(List<ContatoCliente> value)
	{	
	//	listacontatoClienteEstabelece = value;
	//	daoListacontatoClienteEstabelece = (value!=null);
	}  
	public List<ContatoCliente> getListaContatoCliente_Estabelece()
	{	
	//	if (!daoListacontatoClienteEstabelece)
    //    {
    //    ContatoClienteServico srv = FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_SQLITE);
	//	listacontatoClienteEstabelece = srv.getPorReferenteACliente(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Cliente.getListaContatoCliente_Estabelece()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listacontatoClienteEstabelece;
	} 
	public List<ContatoCliente> getListaContatoCliente_EstabeleceOriginal()
	{	
		return listacontatoClienteEstabelece;
	} 
	public List<ContatoCliente> getListaContatoCliente_Estabelece(int qtde)
	{	
    //    ContatoClienteServico srv = FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_SQLITE);
	//	listacontatoClienteEstabelece = srv.getPorReferenteACliente(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Cliente.getListaContatoCliente_Estabelece()",QTDE_LOG_LAZY_LOADER);
		return listacontatoClienteEstabelece;
	} 
	public void addListaContatoCliente_Estabelece(ContatoCliente value) 
	{	
		criaVaziaListaContatoCliente_Estabelece();
		listacontatoClienteEstabelece.add(value);
	//	daoListacontatoClienteEstabelece = true;
	} 
	public ContatoCliente getCorrenteContatoCliente_Estabelece()
	{	
		if (listacontatoClienteEstabelece==null) return null;
		return listacontatoClienteEstabelece.get(listacontatoClienteEstabelece.size()-1);
	} 
	public void criaVaziaListaContatoCliente_Estabelece() {
		if (listacontatoClienteEstabelece == null)
        {
        	listacontatoClienteEstabelece = new ArrayList<ContatoCliente>();
        }
	}
	
	public boolean existeListaVenda_Comprou() {
		return listavendaComprou!= null; 
	}
	private List<Venda> listavendaComprou;
	private boolean daoListavendaComprou = false;
	public void setListaVenda_Comprou(List<Venda> value)
	{	
		listavendaComprou = value;
	}
	public void setListaVenda_ComprouByDao(List<Venda> value)
	{	
	//	listavendaComprou = value;
	//	daoListavendaComprou = (value!=null);
	}  
	public List<Venda> getListaVenda_Comprou()
	{	
	//	if (!daoListavendaComprou)
    //    {
    //    VendaServico srv = FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
	//	listavendaComprou = srv.getPorFeitaParaCliente(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Cliente.getListaVenda_Comprou()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listavendaComprou;
	} 
	public List<Venda> getListaVenda_ComprouOriginal()
	{	
		return listavendaComprou;
	} 
	public List<Venda> getListaVenda_Comprou(int qtde)
	{	
    //    VendaServico srv = FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
	//	listavendaComprou = srv.getPorFeitaParaCliente(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "Cliente.getListaVenda_Comprou()",QTDE_LOG_LAZY_LOADER);
		return listavendaComprou;
	} 
	public void addListaVenda_Comprou(Venda value) 
	{	
		criaVaziaListaVenda_Comprou();
		listavendaComprou.add(value);
	//	daoListavendaComprou = true;
	} 
	public Venda getCorrenteVenda_Comprou()
	{	
		if (listavendaComprou==null) return null;
		return listavendaComprou.get(listavendaComprou.size()-1);
	} 
	public void criaVaziaListaVenda_Comprou() {
		if (listavendaComprou == null)
        {
        	listavendaComprou = new ArrayList<Venda>();
        }
	}
	
}
