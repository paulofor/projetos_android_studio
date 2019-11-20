package treinoacademia.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import treinoacademia.modelo.*;
import treinoacademia.servico.*;

public class CargaPlanejadaAgregado implements CargaPlanejadaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private CargaPlanejadaCarregador carregador = null;
	private CargaPlanejadaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new CargaPlanejadaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.ItemSerie_ReferenteA != null)
			vo.ItemSerie_ReferenteA.setConexaoCarregador(conexao);
		
	}
	*/
	
	private CargaPlanejada vo;
	public CargaPlanejadaAgregado(CargaPlanejada item) {
		vo = item;
	}
	
	
	
	private ItemSerie itemSerieReferenteA;
	public void setItemSerie_ReferenteA(ItemSerie valor)
	{	
		vo.setIdItemSerieRa(0);
		itemSerieReferenteA = valor;
	} 
	public ItemSerie getItemSerie_ReferenteA() 
	{	
		
		if (itemSerieReferenteA==null &&
				vo.getIdItemSerieRaLazyLoader() !=0)
		{
			ItemSerieServico srv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
			itemSerieReferenteA = srv.getById(vo.getIdItemSerieRaLazyLoader());
			DCLog.dStack(DCLog.LAZY_LOADER, this, "CargaPlanejada.getItemSerie_ReferenteA()",QTDE_LOG_LAZY_LOADER);
  		}
		return itemSerieReferenteA;
	} 
	
	public void addListaItemSerie_ReferenteA(ItemSerie value)
	{	
		itemSerieReferenteA = value;
	} 
	public ItemSerie getCorrenteItemSerie_ReferenteA()
	{	
		return itemSerieReferenteA;
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
			DCLog.dStack(DCLog.LAZY_LOADER, this, "CargaPlanejada.getUsuario_Sincroniza()",QTDE_LOG_LAZY_LOADER);
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
	
	
 	
 	
 	
 	
 	
}
