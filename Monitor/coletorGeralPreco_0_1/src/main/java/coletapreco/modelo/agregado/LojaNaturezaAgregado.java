package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;
import coletapreco.servico.*;

public class LojaNaturezaAgregado implements LojaNaturezaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private LojaNaturezaCarregador carregador = null;
	private LojaNaturezaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new LojaNaturezaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.LojaVirtual_ReferenteA != null)
			vo.LojaVirtual_ReferenteA.setConexaoCarregador(conexao);
		if (vo.NaturezaProduto_ReferenteA != null)
			vo.NaturezaProduto_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private LojaNatureza vo;
	public LojaNaturezaAgregado(LojaNatureza item) {
		vo = item;
	}
	
	
	
	private LojaVirtual lojaVirtualReferenteA;
	public void setLojaVirtual_ReferenteA(LojaVirtual valor)
	{	
		vo.setIdLojaVirtualRa(0);
		lojaVirtualReferenteA = valor;
	} 
	public LojaVirtual getLojaVirtual_ReferenteA() 
	{	
		
		if (lojaVirtualReferenteA==null &&
				vo.getIdLojaVirtualRaLazyLoader() !=0)
		{
			LojaVirtualServico srv = FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_SQLITE);
			lojaVirtualReferenteA = srv.getById(vo.getIdLojaVirtualRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaNatureza.getLojaVirtual_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
		return lojaVirtualReferenteA;
	} 
	
	public void addListaLojaVirtual_ReferenteA(LojaVirtual value)
	{	
		lojaVirtualReferenteA = value;
	} 
	public LojaVirtual getCorrenteLojaVirtual_ReferenteA()
	{	
		return lojaVirtualReferenteA;
	} 
	
	
	
	private NaturezaProduto naturezaProdutoReferenteA;
	public void setNaturezaProduto_ReferenteA(NaturezaProduto valor)
	{	
		vo.setIdNaturezaProdutoRa(0);
		naturezaProdutoReferenteA = valor;
	} 
	public NaturezaProduto getNaturezaProduto_ReferenteA() 
	{	
		
		if (naturezaProdutoReferenteA==null &&
				vo.getIdNaturezaProdutoRaLazyLoader() !=0)
		{
			NaturezaProdutoServico srv = FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
			naturezaProdutoReferenteA = srv.getById(vo.getIdNaturezaProdutoRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "LojaNatureza.getNaturezaProduto_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
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
	
	
 	
 	
 	
 	
 	
}
