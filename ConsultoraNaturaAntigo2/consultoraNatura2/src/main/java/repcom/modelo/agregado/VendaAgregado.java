package repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import repcom.modelo.*;
import repcom.servico.*;

public class VendaAgregado implements VendaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private VendaCarregador carregador = null;
	private VendaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new VendaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Cliente_FeitaPara != null)
			vo.Cliente_FeitaPara.setConexaoCarregador(conexao);
		
	}
	*/
	
	private Venda vo;
	public VendaAgregado(Venda item) {
		vo = item;
	}
	
	
	
	private Cliente clienteFeitaPara;
	public void setCliente_FeitaPara(Cliente valor)
	{	
		vo.setIdClienteFp(0);
		clienteFeitaPara = valor;
	} 
	public Cliente getCliente_FeitaPara() 
	{	
		
		if (clienteFeitaPara==null &&
				vo.getIdClienteFpLazyLoader() !=0)
		{
			ClienteServico srv = FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
			clienteFeitaPara = srv.getById(vo.getIdClienteFpLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "Venda.getCliente_FeitaPara()",QTDE_LOG_LAZY_LOADER);
  		}
		return clienteFeitaPara;
	} 
	
	public void addListaCliente_FeitaPara(Cliente value)
	{	
		clienteFeitaPara = value;
	} 
	public Cliente getCorrenteCliente_FeitaPara()
	{	
		return clienteFeitaPara;
	} 
	
	
	
	private Usuario usuarioSincroniza;
	public void setUsuario_Sincroniza(Usuario valor)
	{	
		vo.setIdUsuarioS(0);
		usuarioSincroniza = valor;
	} 
	public Usuario getUsuario_Sincroniza() 
	{	
		
		if (usuarioSincroniza==null &&
				vo.getIdUsuarioSLazyLoader() !=0)
		{
			UsuarioServico srv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			usuarioSincroniza = srv.getById(vo.getIdUsuarioSLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "Venda.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
  		}
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
	
	
 	
 	
 	
 	
 	
	public boolean existeListaProdutoVenda_Associada() {
		return listaprodutoVendaAssociada!= null; 
	}
	private List<ProdutoVenda> listaprodutoVendaAssociada;
	private boolean daoListaprodutoVendaAssociada = false;
	public void setListaProdutoVenda_Associada(List<ProdutoVenda> value)
	{	
		listaprodutoVendaAssociada = value;
	}
	public void setListaProdutoVenda_AssociadaByDao(List<ProdutoVenda> value)
	{	
		listaprodutoVendaAssociada = value;
		daoListaprodutoVendaAssociada = true;
	}  
	public List<ProdutoVenda> getListaProdutoVenda_Associada()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaprodutoVendaAssociada)
        {
        ProdutoVendaServico srv = FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
		listaprodutoVendaAssociada = srv.getPorAssociadaVenda(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Venda.getListaProdutoVenda_Associada()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaprodutoVendaAssociada==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Venda.getListaProdutoVenda_Associada() est? null");
		//}
		return listaprodutoVendaAssociada;
	} 
	public List<ProdutoVenda> getListaProdutoVenda_AssociadaOriginal()
	{	
		return listaprodutoVendaAssociada;
	} 
	public List<ProdutoVenda> getListaProdutoVenda_Associada(int qtde)
	{	
        ProdutoVendaServico srv = FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
		listaprodutoVendaAssociada = srv.getPorAssociadaVenda(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Venda.getListaProdutoVenda_Associada()",QTDE_LOG_LAZY_LOADER);
		return listaprodutoVendaAssociada;
	} 
	public void addListaProdutoVenda_Associada(ProdutoVenda value) 
	{	
		criaVaziaListaProdutoVenda_Associada();
		listaprodutoVendaAssociada.add(value);
		daoListaprodutoVendaAssociada = true;
	} 
	public ProdutoVenda getCorrenteProdutoVenda_Associada()
	{	
		return listaprodutoVendaAssociada.get(listaprodutoVendaAssociada.size()-1);
	} 
	public void criaVaziaListaProdutoVenda_Associada() {
		if (listaprodutoVendaAssociada == null)
        {
        	listaprodutoVendaAssociada = new ArrayList<ProdutoVenda>();
        }
	}
	
}
