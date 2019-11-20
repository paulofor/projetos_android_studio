package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class LojaVirtualAgregado implements LojaVirtualAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private LojaVirtualCarregador carregador = null;
	private LojaVirtualCarregador getCarregador() {
		if (carregador==null) {
			carregador = new LojaVirtualCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private LojaVirtual vo;
	public LojaVirtualAgregado(LojaVirtual item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaProduto_Possui() {
		return listaprodutoPossui!= null; 
	}
	private List<Produto> listaprodutoPossui;
	private boolean daoListaprodutoPossui = false;
	public void setListaProduto_Possui(List<Produto> value)
	{	
		listaprodutoPossui = value;
	}
	public void setListaProduto_PossuiByDao(List<Produto> value)
	{	
		listaprodutoPossui = value;
		daoListaprodutoPossui = true;
	}  
	public List<Produto> getListaProduto_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaprodutoPossui)
        {
        ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
		listaprodutoPossui = srv.getPorLidoEmLojaVirtual(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaVirtual.getListaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaprodutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "LojaVirtual.getListaProduto_Possui() est? null");
		//}
		return listaprodutoPossui;
	} 
	public List<Produto> getListaProduto_PossuiOriginal()
	{	
		return listaprodutoPossui;
	} 
	public List<Produto> getListaProduto_Possui(int qtde)
	{	
        ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
		listaprodutoPossui = srv.getPorLidoEmLojaVirtual(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaVirtual.getListaProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaprodutoPossui;
	} 
	public void addListaProduto_Possui(Produto value) 
	{	
		criaVaziaListaProduto_Possui();
		listaprodutoPossui.add(value);
		daoListaprodutoPossui = true;
	} 
	public Produto getCorrenteProduto_Possui()
	{	
		return listaprodutoPossui.get(listaprodutoPossui.size()-1);
	} 
	public void criaVaziaListaProduto_Possui() {
		if (listaprodutoPossui == null)
        {
        	listaprodutoPossui = new ArrayList<Produto>();
        }
	}
	
	public boolean existeListaCategoriaLoja_Possui() {
		return listacategoriaLojaPossui!= null; 
	}
	private List<CategoriaLoja> listacategoriaLojaPossui;
	private boolean daoListacategoriaLojaPossui = false;
	public void setListaCategoriaLoja_Possui(List<CategoriaLoja> value)
	{	
		listacategoriaLojaPossui = value;
	}
	public void setListaCategoriaLoja_PossuiByDao(List<CategoriaLoja> value)
	{	
		listacategoriaLojaPossui = value;
		daoListacategoriaLojaPossui = true;
	}  
	public List<CategoriaLoja> getListaCategoriaLoja_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListacategoriaLojaPossui)
        {
        CategoriaLojaServico srv = FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
		listacategoriaLojaPossui = srv.getPorPertenceALojaVirtual(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaVirtual.getListaCategoriaLoja_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacategoriaLojaPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "LojaVirtual.getListaCategoriaLoja_Possui() est? null");
		//}
		return listacategoriaLojaPossui;
	} 
	public List<CategoriaLoja> getListaCategoriaLoja_PossuiOriginal()
	{	
		return listacategoriaLojaPossui;
	} 
	public List<CategoriaLoja> getListaCategoriaLoja_Possui(int qtde)
	{	
        CategoriaLojaServico srv = FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
		listacategoriaLojaPossui = srv.getPorPertenceALojaVirtual(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaVirtual.getListaCategoriaLoja_Possui()",QTDE_LOG_LAZY_LOADER);
		return listacategoriaLojaPossui;
	} 
	public void addListaCategoriaLoja_Possui(CategoriaLoja value) 
	{	
		criaVaziaListaCategoriaLoja_Possui();
		listacategoriaLojaPossui.add(value);
		daoListacategoriaLojaPossui = true;
	} 
	public CategoriaLoja getCorrenteCategoriaLoja_Possui()
	{	
		return listacategoriaLojaPossui.get(listacategoriaLojaPossui.size()-1);
	} 
	public void criaVaziaListaCategoriaLoja_Possui() {
		if (listacategoriaLojaPossui == null)
        {
        	listacategoriaLojaPossui = new ArrayList<CategoriaLoja>();
        }
	}
	
	public boolean existeListaLojaNatureza_Oferece() {
		return listalojaNaturezaOferece!= null; 
	}
	private List<LojaNatureza> listalojaNaturezaOferece;
	private boolean daoListalojaNaturezaOferece = false;
	public void setListaLojaNatureza_Oferece(List<LojaNatureza> value)
	{	
		listalojaNaturezaOferece = value;
	}
	public void setListaLojaNatureza_OfereceByDao(List<LojaNatureza> value)
	{	
		listalojaNaturezaOferece = value;
		daoListalojaNaturezaOferece = true;
	}  
	public List<LojaNatureza> getListaLojaNatureza_Oferece()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListalojaNaturezaOferece)
        {
        LojaNaturezaServico srv = FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
		listalojaNaturezaOferece = srv.getPorReferenteALojaVirtual(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaVirtual.getListaLojaNatureza_Oferece()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listalojaNaturezaOferece==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "LojaVirtual.getListaLojaNatureza_Oferece() est? null");
		//}
		return listalojaNaturezaOferece;
	} 
	public List<LojaNatureza> getListaLojaNatureza_OfereceOriginal()
	{	
		return listalojaNaturezaOferece;
	} 
	public List<LojaNatureza> getListaLojaNatureza_Oferece(int qtde)
	{	
        LojaNaturezaServico srv = FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
		listalojaNaturezaOferece = srv.getPorReferenteALojaVirtual(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaVirtual.getListaLojaNatureza_Oferece()",QTDE_LOG_LAZY_LOADER);
		return listalojaNaturezaOferece;
	} 
	public void addListaLojaNatureza_Oferece(LojaNatureza value) 
	{	
		criaVaziaListaLojaNatureza_Oferece();
		listalojaNaturezaOferece.add(value);
		daoListalojaNaturezaOferece = true;
	} 
	public LojaNatureza getCorrenteLojaNatureza_Oferece()
	{	
		return listalojaNaturezaOferece.get(listalojaNaturezaOferece.size()-1);
	} 
	public void criaVaziaListaLojaNatureza_Oferece() {
		if (listalojaNaturezaOferece == null)
        {
        	listalojaNaturezaOferece = new ArrayList<LojaNatureza>();
        }
	}
	
}
