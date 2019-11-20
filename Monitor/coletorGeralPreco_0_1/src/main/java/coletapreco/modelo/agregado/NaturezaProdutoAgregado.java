package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class NaturezaProdutoAgregado implements NaturezaProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private NaturezaProdutoCarregador carregador = null;
	private NaturezaProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new NaturezaProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private NaturezaProduto vo;
	public NaturezaProdutoAgregado(NaturezaProduto item) {
		vo = item;
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
		listacategoriaLojaPossui = srv.getPorReferenteANaturezaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaCategoriaLoja_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listacategoriaLojaPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "NaturezaProduto.getListaCategoriaLoja_Possui() est? null");
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
		listacategoriaLojaPossui = srv.getPorReferenteANaturezaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaCategoriaLoja_Possui()",QTDE_LOG_LAZY_LOADER);
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
	
	public boolean existeListaLojaNatureza_Encontrada() {
		return listalojaNaturezaEncontrada!= null; 
	}
	private List<LojaNatureza> listalojaNaturezaEncontrada;
	private boolean daoListalojaNaturezaEncontrada = false;
	public void setListaLojaNatureza_Encontrada(List<LojaNatureza> value)
	{	
		listalojaNaturezaEncontrada = value;
	}
	public void setListaLojaNatureza_EncontradaByDao(List<LojaNatureza> value)
	{	
		listalojaNaturezaEncontrada = value;
		daoListalojaNaturezaEncontrada = true;
	}  
	public List<LojaNatureza> getListaLojaNatureza_Encontrada()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListalojaNaturezaEncontrada)
        {
        LojaNaturezaServico srv = FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
		listalojaNaturezaEncontrada = srv.getPorReferenteANaturezaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaLojaNatureza_Encontrada()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listalojaNaturezaEncontrada==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "NaturezaProduto.getListaLojaNatureza_Encontrada() est? null");
		//}
		return listalojaNaturezaEncontrada;
	} 
	public List<LojaNatureza> getListaLojaNatureza_EncontradaOriginal()
	{	
		return listalojaNaturezaEncontrada;
	} 
	public List<LojaNatureza> getListaLojaNatureza_Encontrada(int qtde)
	{	
        LojaNaturezaServico srv = FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
		listalojaNaturezaEncontrada = srv.getPorReferenteANaturezaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaLojaNatureza_Encontrada()",QTDE_LOG_LAZY_LOADER);
		return listalojaNaturezaEncontrada;
	} 
	public void addListaLojaNatureza_Encontrada(LojaNatureza value) 
	{	
		criaVaziaListaLojaNatureza_Encontrada();
		listalojaNaturezaEncontrada.add(value);
		daoListalojaNaturezaEncontrada = true;
	} 
	public LojaNatureza getCorrenteLojaNatureza_Encontrada()
	{	
		return listalojaNaturezaEncontrada.get(listalojaNaturezaEncontrada.size()-1);
	} 
	public void criaVaziaListaLojaNatureza_Encontrada() {
		if (listalojaNaturezaEncontrada == null)
        {
        	listalojaNaturezaEncontrada = new ArrayList<LojaNatureza>();
        }
	}
	
	public boolean existeListaOportunidadeDia_Possui() {
		return listaoportunidadeDiaPossui!= null; 
	}
	private List<OportunidadeDia> listaoportunidadeDiaPossui;
	private boolean daoListaoportunidadeDiaPossui = false;
	public void setListaOportunidadeDia_Possui(List<OportunidadeDia> value)
	{	
		listaoportunidadeDiaPossui = value;
	}
	public void setListaOportunidadeDia_PossuiByDao(List<OportunidadeDia> value)
	{	
		listaoportunidadeDiaPossui = value;
		daoListaoportunidadeDiaPossui = true;
	}  
	public List<OportunidadeDia> getListaOportunidadeDia_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListaoportunidadeDiaPossui)
        {
        OportunidadeDiaServico srv = FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
		listaoportunidadeDiaPossui = srv.getPorPertenceANaturezaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaOportunidadeDia_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listaoportunidadeDiaPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "NaturezaProduto.getListaOportunidadeDia_Possui() est? null");
		//}
		return listaoportunidadeDiaPossui;
	} 
	public List<OportunidadeDia> getListaOportunidadeDia_PossuiOriginal()
	{	
		return listaoportunidadeDiaPossui;
	} 
	public List<OportunidadeDia> getListaOportunidadeDia_Possui(int qtde)
	{	
        OportunidadeDiaServico srv = FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
		listaoportunidadeDiaPossui = srv.getPorPertenceANaturezaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaOportunidadeDia_Possui()",QTDE_LOG_LAZY_LOADER);
		return listaoportunidadeDiaPossui;
	} 
	public void addListaOportunidadeDia_Possui(OportunidadeDia value) 
	{	
		criaVaziaListaOportunidadeDia_Possui();
		listaoportunidadeDiaPossui.add(value);
		daoListaoportunidadeDiaPossui = true;
	} 
	public OportunidadeDia getCorrenteOportunidadeDia_Possui()
	{	
		return listaoportunidadeDiaPossui.get(listaoportunidadeDiaPossui.size()-1);
	} 
	public void criaVaziaListaOportunidadeDia_Possui() {
		if (listaoportunidadeDiaPossui == null)
        {
        	listaoportunidadeDiaPossui = new ArrayList<OportunidadeDia>();
        }
	}
	
	public boolean existeListaUsuarioPesquisa_PesquisadoPor() {
		return listausuarioPesquisaPesquisadoPor!= null; 
	}
	private List<UsuarioPesquisa> listausuarioPesquisaPesquisadoPor;
	private boolean daoListausuarioPesquisaPesquisadoPor = false;
	public void setListaUsuarioPesquisa_PesquisadoPor(List<UsuarioPesquisa> value)
	{	
		listausuarioPesquisaPesquisadoPor = value;
	}
	public void setListaUsuarioPesquisa_PesquisadoPorByDao(List<UsuarioPesquisa> value)
	{	
		listausuarioPesquisaPesquisadoPor = value;
		daoListausuarioPesquisaPesquisadoPor = true;
	}  
	public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListausuarioPesquisaPesquisadoPor)
        {
        UsuarioPesquisaServico srv = FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_SQLITE);
		listausuarioPesquisaPesquisadoPor = srv.getPorPesquisaNaturezaProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaUsuarioPesquisa_PesquisadoPor()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listausuarioPesquisaPesquisadoPor==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "NaturezaProduto.getListaUsuarioPesquisa_PesquisadoPor() est? null");
		//}
		return listausuarioPesquisaPesquisadoPor;
	} 
	public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPorOriginal()
	{	
		return listausuarioPesquisaPesquisadoPor;
	} 
	public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor(int qtde)
	{	
        UsuarioPesquisaServico srv = FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_SQLITE);
		listausuarioPesquisaPesquisadoPor = srv.getPorPesquisaNaturezaProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "NaturezaProduto.getListaUsuarioPesquisa_PesquisadoPor()",QTDE_LOG_LAZY_LOADER);
		return listausuarioPesquisaPesquisadoPor;
	} 
	public void addListaUsuarioPesquisa_PesquisadoPor(UsuarioPesquisa value) 
	{	
		criaVaziaListaUsuarioPesquisa_PesquisadoPor();
		listausuarioPesquisaPesquisadoPor.add(value);
		daoListausuarioPesquisaPesquisadoPor = true;
	} 
	public UsuarioPesquisa getCorrenteUsuarioPesquisa_PesquisadoPor()
	{	
		return listausuarioPesquisaPesquisadoPor.get(listausuarioPesquisaPesquisadoPor.size()-1);
	} 
	public void criaVaziaListaUsuarioPesquisa_PesquisadoPor() {
		if (listausuarioPesquisaPesquisadoPor == null)
        {
        	listausuarioPesquisaPesquisadoPor = new ArrayList<UsuarioPesquisa>();
        }
	}
	
}
