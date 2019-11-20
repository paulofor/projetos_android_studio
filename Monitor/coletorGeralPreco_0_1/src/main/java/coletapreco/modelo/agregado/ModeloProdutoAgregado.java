package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class ModeloProdutoAgregado implements ModeloProdutoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ModeloProdutoCarregador carregador = null;
	private ModeloProdutoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ModeloProdutoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private ModeloProduto vo;
	public ModeloProdutoAgregado(ModeloProduto item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaModeloProdutoProduto_ReferenteA() {
		return listamodeloProdutoProdutoReferenteA!= null; 
	}
	private List<ModeloProdutoProduto> listamodeloProdutoProdutoReferenteA;
	private boolean daoListamodeloProdutoProdutoReferenteA = false;
	public void setListaModeloProdutoProduto_ReferenteA(List<ModeloProdutoProduto> value)
	{	
		listamodeloProdutoProdutoReferenteA = value;
	}
	public void setListaModeloProdutoProduto_ReferenteAByDao(List<ModeloProdutoProduto> value)
	{	
		listamodeloProdutoProdutoReferenteA = value;
		daoListamodeloProdutoProdutoReferenteA = true;
	}  
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA()
	{	
		// SEMPRE BUSCANDO NO SQLITE
		// ligando o LazyLoader
		if (!daoListamodeloProdutoProdutoReferenteA)
        {
        ModeloProdutoProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		listamodeloProdutoProdutoReferenteA = srv.getPorReferenteAModeloProduto(vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "ModeloProduto.getListaModeloProdutoProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
        }
        //if (listamodeloProdutoProdutoReferenteA==null) {
		//	DCLog.d(DCLog.ITEM_NULL, this, "ModeloProduto.getListaModeloProdutoProduto_ReferenteA() est? null");
		//}
		return listamodeloProdutoProdutoReferenteA;
	} 
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal()
	{	
		return listamodeloProdutoProdutoReferenteA;
	} 
	public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde)
	{	
        ModeloProdutoProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		listamodeloProdutoProdutoReferenteA = srv.getPorReferenteAModeloProduto(vo.getContexto().getContext(), vo.getId());
		DCLog.dStack(DCLog.LAZY_LOADER, this, "ModeloProduto.getListaModeloProdutoProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
		return listamodeloProdutoProdutoReferenteA;
	} 
	public void addListaModeloProdutoProduto_ReferenteA(ModeloProdutoProduto value) 
	{	
		criaVaziaListaModeloProdutoProduto_ReferenteA();
		listamodeloProdutoProdutoReferenteA.add(value);
		daoListamodeloProdutoProdutoReferenteA = true;
	} 
	public ModeloProdutoProduto getCorrenteModeloProdutoProduto_ReferenteA()
	{	
		return listamodeloProdutoProdutoReferenteA.get(listamodeloProdutoProdutoReferenteA.size()-1);
	} 
	public void criaVaziaListaModeloProdutoProduto_ReferenteA() {
		if (listamodeloProdutoProdutoReferenteA == null)
        {
        	listamodeloProdutoProdutoReferenteA = new ArrayList<ModeloProdutoProduto>();
        }
	}
	
}
