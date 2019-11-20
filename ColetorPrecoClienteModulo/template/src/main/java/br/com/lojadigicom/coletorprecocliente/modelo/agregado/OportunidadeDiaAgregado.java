package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.*;


public class OportunidadeDiaAgregado implements OportunidadeDiaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private OportunidadeDiaCarregador carregador = null;
	private OportunidadeDiaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new OportunidadeDiaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.NaturezaProduto_PertenceA != null)
			vo.NaturezaProduto_PertenceA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private OportunidadeDia vo;
	public OportunidadeDiaAgregado(OportunidadeDia item) {
		vo = item;
	}
	
	
	
	private NaturezaProduto naturezaProdutoPertenceA;
	public void setNaturezaProduto_PertenceA(NaturezaProduto valor)
	{	
		naturezaProdutoPertenceA = valor;
	} 
	public NaturezaProduto getNaturezaProduto_PertenceA() 
	{	
	//	if (naturezaProdutoPertenceA==null &&
	//			vo.getIdNaturezaProdutoPaLazyLoader() !=0)
	//	{
	//		NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
	//		naturezaProdutoPertenceA = srv.getById(vo.getIdNaturezaProdutoPaLazyLoader());
	//		DCLog.dStack(DCLog.LAZY_LOADER, this, "OportunidadeDia.getNaturezaProduto_PertenceA()",QTDE_LOG_LAZY_LOADER);
  	//	}
		return naturezaProdutoPertenceA;
	} 
	
	public void addListaNaturezaProduto_PertenceA(NaturezaProduto value)
	{	
		naturezaProdutoPertenceA = value;
	} 
	public NaturezaProduto getCorrenteNaturezaProduto_PertenceA()
	{	
		return naturezaProdutoPertenceA;
	} 
	
	
 	
 	
 	
 	
 	
}
