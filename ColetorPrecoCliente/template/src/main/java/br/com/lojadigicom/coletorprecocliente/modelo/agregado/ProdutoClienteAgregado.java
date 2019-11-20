package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class ProdutoClienteAgregado implements ProdutoClienteAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ProdutoClienteCarregador carregador = null;
	private ProdutoClienteCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ProdutoClienteCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.NaturezaProduto_ReferenteA != null)
			vo.NaturezaProduto_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private ProdutoCliente vo;
	public ProdutoClienteAgregado(ProdutoCliente item) {
		vo = item;
	}
	
	
	
	private NaturezaProduto naturezaProdutoReferenteA;
	public void setNaturezaProduto_ReferenteA(NaturezaProduto valor)
	{	
		vo.setIdNaturezaProdutoRa(0);
		naturezaProdutoReferenteA = valor;
	} 
	public NaturezaProduto getNaturezaProduto_ReferenteA() 
	{	
	//	if (naturezaProdutoReferenteA==null &&
	//			vo.getIdNaturezaProdutoRaLazyLoader() !=0)
	//	{
	//		NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
	//		naturezaProdutoReferenteA = srv.getById(vo.getIdNaturezaProdutoRaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoCliente.getNaturezaProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return naturezaProdutoReferenteA;
	} 
	
	public void addListaNaturezaProduto_ReferenteA(NaturezaProduto value)
	{	
		naturezaProdutoReferenteA = value;
	} 
	public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA()
	{	
		return naturezaProdutoReferenteA;
	} 
	
	
 	
 	
 	
 	
 	
	public boolean existeListaInteresseProduto_Possui() {
		return listainteresseProdutoPossui!= null; 
	}
	private List<InteresseProduto> listainteresseProdutoPossui;
	private boolean daoListainteresseProdutoPossui = false;
	public void setListaInteresseProduto_Possui(List<InteresseProduto> value)
	{	
		listainteresseProdutoPossui = value;
	}
	public void setListaInteresseProduto_PossuiByDao(List<InteresseProduto> value)
	{	
	//	listainteresseProdutoPossui = value;
	//	daoListainteresseProdutoPossui = (value!=null);
	}  
	public List<InteresseProduto> getListaInteresseProduto_Possui()
	{	
	//	if (!daoListainteresseProdutoPossui)
    //    {
    //    InteresseProdutoServico srv = FabricaServico.getInstancia().getInteresseProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listainteresseProdutoPossui = srv.getPorReferenteAProdutoCliente(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoCliente.getListaInteresseProduto_Possui()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listainteresseProdutoPossui;
	} 
	public List<InteresseProduto> getListaInteresseProduto_PossuiOriginal()
	{	
		return listainteresseProdutoPossui;
	} 
	public List<InteresseProduto> getListaInteresseProduto_Possui(int qtde)
	{	
    //    InteresseProdutoServico srv = FabricaServico.getInstancia().getInteresseProdutoServico(FabricaServico.TIPO_SQLITE);
	//	listainteresseProdutoPossui = srv.getPorReferenteAProdutoCliente(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "ProdutoCliente.getListaInteresseProduto_Possui()",QTDE_LOG_LAZY_LOADER);
		return listainteresseProdutoPossui;
	} 
	public void addListaInteresseProduto_Possui(InteresseProduto value) 
	{	
		criaVaziaListaInteresseProduto_Possui();
		listainteresseProdutoPossui.add(value);
	//	daoListainteresseProdutoPossui = true;
	} 
	public InteresseProduto getCorrenteInteresseProduto_Possui()
	{	
		if (listainteresseProdutoPossui==null) return null;
		return listainteresseProdutoPossui.get(listainteresseProdutoPossui.size()-1);
	} 
	public void criaVaziaListaInteresseProduto_Possui() {
		if (listainteresseProdutoPossui == null)
        {
        	listainteresseProdutoPossui = new ArrayList<InteresseProduto>();
        }
	}
	
}
