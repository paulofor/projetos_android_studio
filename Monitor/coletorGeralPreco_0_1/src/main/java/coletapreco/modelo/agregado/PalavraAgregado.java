package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class PalavraAgregado implements PalavraAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private PalavraCarregador carregador = null;
	private PalavraCarregador getCarregador() {
		if (carregador==null) {
			carregador = new PalavraCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private Palavra vo;
	public PalavraAgregado(Palavra item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaPalavraProduto_Possui() {
		return listapalavraProdutoPossui!= null; 
	}
	private List<PalavraProduto> listapalavraProdutoPossui;
	private boolean daoListapalavraProdutoPossui = false;
	public void setListaPalavraProduto_Possui(List<PalavraProduto> value)
	{	
		listapalavraProdutoPossui = value;
	}
	public void setListaPalavraProduto_PossuiByDao(List<PalavraProduto> value)
	{	
		listapalavraProdutoPossui = value;
		daoListapalavraProdutoPossui = true;
	}  
	public List<PalavraProduto> getListaPalavraProduto_Possui()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListapalavraProdutoPossui)
        {
        PalavraProdutoServico srv = FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_SQLITE);
		listapalavraProdutoPossui = srv.getPorRelaciondoAPalavra(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Palavra.getListaPalavraProduto_Possui()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listapalavraProdutoPossui==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "Palavra.getListaPalavraProduto_Possui() est? null");
		//}
		return listapalavraProdutoPossui;
	} 
	public List<PalavraProduto> getListaPalavraProduto_PossuiOriginal()
	{	
		return listapalavraProdutoPossui;
	} 
	public List<PalavraProduto> getListaPalavraProduto_Possui(int qtde)
	{	
        PalavraProdutoServico srv = FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_SQLITE);
		listapalavraProdutoPossui = srv.getPorRelaciondoAPalavra(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "Palavra.getListaPalavraProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listapalavraProdutoPossui;
	} 
	public void addListaPalavraProduto_Possui(PalavraProduto value) 
	{	
		criaVaziaListaPalavraProduto_Possui();
		listapalavraProdutoPossui.add(value);
		daoListapalavraProdutoPossui = true;
	} 
	public PalavraProduto getCorrentePalavraProduto_Possui()
	{	
		return listapalavraProdutoPossui.get(listapalavraProdutoPossui.size()-1);
	} 
	public void criaVaziaListaPalavraProduto_Possui() {
		if (listapalavraProdutoPossui == null)
        {
        	listapalavraProdutoPossui = new ArrayList<PalavraProduto>();
        }
	}
	
}
