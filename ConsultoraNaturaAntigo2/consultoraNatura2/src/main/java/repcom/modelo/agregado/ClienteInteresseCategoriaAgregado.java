package repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import repcom.modelo.*;
import repcom.servico.*;

public class ClienteInteresseCategoriaAgregado implements ClienteInteresseCategoriaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ClienteInteresseCategoriaCarregador carregador = null;
	private ClienteInteresseCategoriaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ClienteInteresseCategoriaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Cliente_Associada != null)
			vo.Cliente_Associada.setConexaoCarregador(conexao);
		if (vo.CategoriaProduto_Associada != null)
			vo.CategoriaProduto_Associada.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ClienteInteresseCategoria vo;
	public ClienteInteresseCategoriaAgregado(ClienteInteresseCategoria item) {
		vo = item;
	}
	
	
	
	private Cliente clienteAssociada;
	public void setCliente_Associada(Cliente valor)
	{	
		vo.setIdClienteA(0);
		clienteAssociada = valor;
	} 
	public Cliente getCliente_Associada() 
	{	
		
		if (clienteAssociada==null &&
				vo.getIdClienteALazyLoader() !=0)
		{
			ClienteServico srv = FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
			clienteAssociada = srv.getById(vo.getIdClienteALazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ClienteInteresseCategoria.getCliente_Associada()",QTDE_LOG_LAZY_LOADER);
  		}
		return clienteAssociada;
	} 
	
	public void addListaCliente_Associada(Cliente value)
	{	
		clienteAssociada = value;
	} 
	public Cliente getCorrenteCliente_Associada()
	{	
		return clienteAssociada;
	} 
	
	
	
	private CategoriaProduto categoriaProdutoAssociada;
	public void setCategoriaProduto_Associada(CategoriaProduto valor)
	{	
		vo.setIdCategoriaProdutoA(0);
		categoriaProdutoAssociada = valor;
	} 
	public CategoriaProduto getCategoriaProduto_Associada() 
	{	
		
		if (categoriaProdutoAssociada==null &&
				vo.getIdCategoriaProdutoALazyLoader() !=0)
		{
			CategoriaProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
			categoriaProdutoAssociada = srv.getById(vo.getIdCategoriaProdutoALazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "ClienteInteresseCategoria.getCategoriaProduto_Associada()",QTDE_LOG_LAZY_LOADER);
  		}
		return categoriaProdutoAssociada;
	} 
	
	public void addListaCategoriaProduto_Associada(CategoriaProduto value)
	{	
		categoriaProdutoAssociada = value;
	} 
	public CategoriaProduto getCorrenteCategoriaProduto_Associada()
	{	
		return categoriaProdutoAssociada;
	} 
	
	
 	
 	
 	
 	
 	
}
