package repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import repcom.modelo.*;
import repcom.servico.*;

public class ProdutoAgregado implements ProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ProdutoCarregador carregador = null;
	private ProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.LinhaProduto_EstaEm != null)
			vo.LinhaProduto_EstaEm.setConexaoCarregador(conexao);
		
	}
	*/
	
	private Produto vo;
	public ProdutoAgregado(Produto item) {
		vo = item;
	}
	
	
	
	private LinhaProduto linhaProdutoEstaEm;
	public void setLinhaProduto_EstaEm(LinhaProduto valor)
	{	
		vo.setIdLinhaProdutoEe(0);
		linhaProdutoEstaEm = valor;
	} 
	public LinhaProduto getLinhaProduto_EstaEm() 
	{	
		
		if (linhaProdutoEstaEm==null &&
				vo.getIdLinhaProdutoEeLazyLoader() !=0)
		{
			LinhaProdutoServico srv = FabricaServico.getInstancia().getLinhaProdutoServico(FabricaServico.TIPO_SQLITE);
			linhaProdutoEstaEm = srv.getById(vo.getIdLinhaProdutoEeLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getLinhaProduto_EstaEm()",QTDE_LOG_LAZY_LOADER);
  		}
		return linhaProdutoEstaEm;
	} 
	
	public void addListaLinhaProduto_EstaEm(LinhaProduto value)
	{	
		linhaProdutoEstaEm = value;
	} 
	public LinhaProduto getCorrenteLinhaProduto_EstaEm()
	{	
		return linhaProdutoEstaEm;
	} 
	
	
 	
 	
 	
 	
 	
	public boolean existeListaProdutoPedidoFornecedor_Associada() {
		return listaprodutoPedidoFornecedorAssociada!= null; 
	}
	private List<ProdutoPedidoFornecedor> listaprodutoPedidoFornecedorAssociada;
	private boolean daoListaprodutoPedidoFornecedorAssociada = false;
	public void setListaProdutoPedidoFornecedor_Associada(List<ProdutoPedidoFornecedor> value)
	{	
		listaprodutoPedidoFornecedorAssociada = value;
	}
	public void setListaProdutoPedidoFornecedor_AssociadaByDao(List<ProdutoPedidoFornecedor> value)
	{	
		listaprodutoPedidoFornecedorAssociada = value;
		daoListaprodutoPedidoFornecedorAssociada = true;
	}  
	public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaprodutoPedidoFornecedorAssociada)
        {
        ProdutoPedidoFornecedorServico srv = FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
		listaprodutoPedidoFornecedorAssociada = srv.getPorAssociadaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaProdutoPedidoFornecedor_Associada()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaprodutoPedidoFornecedorAssociada==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaProdutoPedidoFornecedor_Associada() est? null");
		//}
		return listaprodutoPedidoFornecedorAssociada;
	} 
	public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_AssociadaOriginal()
	{	
		return listaprodutoPedidoFornecedorAssociada;
	} 
	public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada(int qtde)
	{	
        ProdutoPedidoFornecedorServico srv = FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
		listaprodutoPedidoFornecedorAssociada = srv.getPorAssociadaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaProdutoPedidoFornecedor_Associada()",QTDE_LOG_LAZY_LOADER);
		return listaprodutoPedidoFornecedorAssociada;
	} 
	public void addListaProdutoPedidoFornecedor_Associada(ProdutoPedidoFornecedor value) 
	{	
		criaVaziaListaProdutoPedidoFornecedor_Associada();
		listaprodutoPedidoFornecedorAssociada.add(value);
		daoListaprodutoPedidoFornecedorAssociada = true;
	} 
	public ProdutoPedidoFornecedor getCorrenteProdutoPedidoFornecedor_Associada()
	{	
		return listaprodutoPedidoFornecedorAssociada.get(listaprodutoPedidoFornecedorAssociada.size()-1);
	} 
	public void criaVaziaListaProdutoPedidoFornecedor_Associada() {
		if (listaprodutoPedidoFornecedorAssociada == null)
        {
        	listaprodutoPedidoFornecedorAssociada = new ArrayList<ProdutoPedidoFornecedor>();
        }
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
		listaprodutoVendaAssociada = srv.getPorAssociadaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaProdutoVenda_Associada()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaprodutoVendaAssociada==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaProdutoVenda_Associada() est? null");
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
		listaprodutoVendaAssociada = srv.getPorAssociadaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaProdutoVenda_Associada()",QTDE_LOG_LAZY_LOADER);
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
	
	public boolean existeListaPrecoProduto_Possui() {
		return listaprecoProdutoPossui!= null; 
	}
	private List<PrecoProduto> listaprecoProdutoPossui;
	private boolean daoListaprecoProdutoPossui = false;
	public void setListaPrecoProduto_Possui(List<PrecoProduto> value)
	{	
		listaprecoProdutoPossui = value;
	}
	public void setListaPrecoProduto_PossuiByDao(List<PrecoProduto> value)
	{	
		listaprecoProdutoPossui = value;
		daoListaprecoProdutoPossui = true;
	}  
	public List<PrecoProduto> getListaPrecoProduto_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaprecoProdutoPossui)
        {
        PrecoProdutoServico srv = FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
		listaprecoProdutoPossui = srv.getPorPertenceAProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaPrecoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaprecoProdutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaPrecoProduto_Possui() est? null");
		//}
		return listaprecoProdutoPossui;
	} 
	public List<PrecoProduto> getListaPrecoProduto_PossuiOriginal()
	{	
		return listaprecoProdutoPossui;
	} 
	public List<PrecoProduto> getListaPrecoProduto_Possui(int qtde)
	{	
        PrecoProdutoServico srv = FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
		listaprecoProdutoPossui = srv.getPorPertenceAProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaPrecoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaprecoProdutoPossui;
	} 
	public void addListaPrecoProduto_Possui(PrecoProduto value) 
	{	
		criaVaziaListaPrecoProduto_Possui();
		listaprecoProdutoPossui.add(value);
		daoListaprecoProdutoPossui = true;
	} 
	public PrecoProduto getCorrentePrecoProduto_Possui()
	{	
		return listaprecoProdutoPossui.get(listaprecoProdutoPossui.size()-1);
	} 
	public void criaVaziaListaPrecoProduto_Possui() {
		if (listaprecoProdutoPossui == null)
        {
        	listaprecoProdutoPossui = new ArrayList<PrecoProduto>();
        }
	}
	
	public boolean existeListaCategoriaProdutoProduto_Possui() {
		return listacategoriaProdutoProdutoPossui!= null; 
	}
	private List<CategoriaProdutoProduto> listacategoriaProdutoProdutoPossui;
	private boolean daoListacategoriaProdutoProdutoPossui = false;
	public void setListaCategoriaProdutoProduto_Possui(List<CategoriaProdutoProduto> value)
	{	
		listacategoriaProdutoProdutoPossui = value;
	}
	public void setListaCategoriaProdutoProduto_PossuiByDao(List<CategoriaProdutoProduto> value)
	{	
		listacategoriaProdutoProdutoPossui = value;
		daoListacategoriaProdutoProdutoPossui = true;
	}  
	public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListacategoriaProdutoProdutoPossui)
        {
        CategoriaProdutoProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		listacategoriaProdutoProdutoPossui = srv.getPorReferenteAProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaCategoriaProdutoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacategoriaProdutoProdutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Produto.getListaCategoriaProdutoProduto_Possui() est? null");
		//}
		return listacategoriaProdutoProdutoPossui;
	} 
	public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_PossuiOriginal()
	{	
		return listacategoriaProdutoProdutoPossui;
	} 
	public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui(int qtde)
	{	
        CategoriaProdutoProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		listacategoriaProdutoProdutoPossui = srv.getPorReferenteAProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Produto.getListaCategoriaProdutoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listacategoriaProdutoProdutoPossui;
	} 
	public void addListaCategoriaProdutoProduto_Possui(CategoriaProdutoProduto value) 
	{	
		criaVaziaListaCategoriaProdutoProduto_Possui();
		listacategoriaProdutoProdutoPossui.add(value);
		daoListacategoriaProdutoProdutoPossui = true;
	} 
	public CategoriaProdutoProduto getCorrenteCategoriaProdutoProduto_Possui()
	{	
		return listacategoriaProdutoProdutoPossui.get(listacategoriaProdutoProdutoPossui.size()-1);
	} 
	public void criaVaziaListaCategoriaProdutoProduto_Possui() {
		if (listacategoriaProdutoProdutoPossui == null)
        {
        	listacategoriaProdutoProdutoPossui = new ArrayList<CategoriaProdutoProduto>();
        }
	}
	
}
