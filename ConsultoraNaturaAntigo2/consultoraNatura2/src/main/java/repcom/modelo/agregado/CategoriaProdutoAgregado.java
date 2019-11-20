package repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import repcom.modelo.*;
import repcom.servico.*;

public class CategoriaProdutoAgregado implements CategoriaProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private CategoriaProdutoCarregador carregador = null;
	private CategoriaProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new CategoriaProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.CategoriaProduto_Pai != null)
			vo.CategoriaProduto_Pai.setConexaoCarregador(conexao);
		
	}
	*/
	
	private CategoriaProduto vo;
	public CategoriaProdutoAgregado(CategoriaProduto item) {
		vo = item;
	}
	
	
	
	private CategoriaProduto categoriaProdutoPai;
	public void setCategoriaProduto_Pai(CategoriaProduto valor)
	{	
		vo.setIdCategoriaProdutoP(0);
		categoriaProdutoPai = valor;
	} 
	public CategoriaProduto getCategoriaProduto_Pai() 
	{	
		
		if (categoriaProdutoPai==null &&
				vo.getIdCategoriaProdutoPLazyLoader() !=0)
		{
			CategoriaProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
			categoriaProdutoPai = srv.getById(vo.getIdCategoriaProdutoPLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProduto.getCategoriaProduto_Pai()",QTDE_LOG_LAZY_LOADER);
  		}
		return categoriaProdutoPai;
	} 
	
	/*  AutoRelacionamento
	
	public void addListaCategoriaProduto_Pai(CategoriaProduto value)
	{	
		categoriaProdutoPai = value;
	} 
	public CategoriaProduto getCorrenteCategoriaProduto_Pai()
	{	
		return categoriaProdutoPai;
	} 
	
	*/
	
	
 	
 	
 	
 	
 	
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
		listaclienteInteresseCategoriaAssociada = value;
		daoListaclienteInteresseCategoriaAssociada = true;
	}  
	public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaclienteInteresseCategoriaAssociada)
        {
        ClienteInteresseCategoriaServico srv = FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
		listaclienteInteresseCategoriaAssociada = srv.getPorAssociadaCategoriaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProduto.getListaClienteInteresseCategoria_Associada()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaclienteInteresseCategoriaAssociada==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "CategoriaProduto.getListaClienteInteresseCategoria_Associada() est? null");
		//}
		return listaclienteInteresseCategoriaAssociada;
	} 
	public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_AssociadaOriginal()
	{	
		return listaclienteInteresseCategoriaAssociada;
	} 
	public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada(int qtde)
	{	
        ClienteInteresseCategoriaServico srv = FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
		listaclienteInteresseCategoriaAssociada = srv.getPorAssociadaCategoriaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProduto.getListaClienteInteresseCategoria_Associada()",QTDE_LOG_LAZY_LOADER);
		return listaclienteInteresseCategoriaAssociada;
	} 
	public void addListaClienteInteresseCategoria_Associada(ClienteInteresseCategoria value) 
	{	
		criaVaziaListaClienteInteresseCategoria_Associada();
		listaclienteInteresseCategoriaAssociada.add(value);
		daoListaclienteInteresseCategoriaAssociada = true;
	} 
	public ClienteInteresseCategoria getCorrenteClienteInteresseCategoria_Associada()
	{	
		return listaclienteInteresseCategoriaAssociada.get(listaclienteInteresseCategoriaAssociada.size()-1);
	} 
	public void criaVaziaListaClienteInteresseCategoria_Associada() {
		if (listaclienteInteresseCategoriaAssociada == null)
        {
        	listaclienteInteresseCategoriaAssociada = new ArrayList<ClienteInteresseCategoria>();
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
		listacategoriaProdutoProdutoPossui = srv.getPorReferenteACategoriaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProduto.getListaCategoriaProdutoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacategoriaProdutoProdutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "CategoriaProduto.getListaCategoriaProdutoProduto_Possui() est? null");
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
		listacategoriaProdutoProdutoPossui = srv.getPorReferenteACategoriaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProduto.getListaCategoriaProdutoProduto_Possui()",QTDE_LOG_LAZY_LOADER);
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
	
	public boolean existeListaCategoriaProduto_Pai() {
		return listacategoriaProdutoPai!= null; 
	}
	private List<CategoriaProduto> listacategoriaProdutoPai;
	private boolean daoListacategoriaProdutoPai = false;
	public void setListaCategoriaProduto_Pai(List<CategoriaProduto> value)
	{	
		listacategoriaProdutoPai = value;
	}
	public void setListaCategoriaProduto_PaiByDao(List<CategoriaProduto> value)
	{	
		listacategoriaProdutoPai = value;
		daoListacategoriaProdutoPai = true;
	}  
	public List<CategoriaProduto> getListaCategoriaProduto_Pai()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListacategoriaProdutoPai)
        {
        CategoriaProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
		listacategoriaProdutoPai = srv.getPorPaiCategoriaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProduto.getListaCategoriaProduto_Pai()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacategoriaProdutoPai==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "CategoriaProduto.getListaCategoriaProduto_Pai() est? null");
		//}
		return listacategoriaProdutoPai;
	} 
	public List<CategoriaProduto> getListaCategoriaProduto_PaiOriginal()
	{	
		return listacategoriaProdutoPai;
	} 
	public List<CategoriaProduto> getListaCategoriaProduto_Pai(int qtde)
	{	
        CategoriaProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
		listacategoriaProdutoPai = srv.getPorPaiCategoriaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "CategoriaProduto.getListaCategoriaProduto_Pai()",QTDE_LOG_LAZY_LOADER);
		return listacategoriaProdutoPai;
	} 
	public void addListaCategoriaProduto_Pai(CategoriaProduto value) 
	{	
		criaVaziaListaCategoriaProduto_Pai();
		listacategoriaProdutoPai.add(value);
		daoListacategoriaProdutoPai = true;
	} 
	public CategoriaProduto getCorrenteCategoriaProduto_Pai()
	{	
		return listacategoriaProdutoPai.get(listacategoriaProdutoPai.size()-1);
	} 
	public void criaVaziaListaCategoriaProduto_Pai() {
		if (listacategoriaProdutoPai == null)
        {
        	listacategoriaProdutoPai = new ArrayList<CategoriaProduto>();
        }
	}
	
}
