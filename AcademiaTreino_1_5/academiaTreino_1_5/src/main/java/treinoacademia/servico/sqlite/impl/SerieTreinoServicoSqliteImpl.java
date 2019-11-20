
package treinoacademia.servico.sqlite.impl;

import java.util.ArrayList;
import java.util.List;

import treinoacademia.dao.SerieTreinoDBHelper;
import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.modelo.Usuario;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.CargaPlanejadaServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.ItemSerieServico;
import treinoacademia.servico.SerieTreinoServico;
import treinoacademia.servico.UsuarioServico;
import treinoacademia.servico.sqlite.base.SerieTreinoServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.util.UtilDatas;

public class SerieTreinoServicoSqliteImpl extends SerieTreinoServicoSqliteBase {

	

	@Override
	protected SerieTreino ObtemProximaImpl(DigicomContexto contexto) {
		SerieTreinoDBHelper dao = this.getDao();
		SerieTreino serie = dao.obtemProxima();
		return serie;
	}

	@Override
	protected SerieTreino CriaSerieCompletaImpl(DigicomContexto contexto) {
		CargaPlanejadaServico cargaSrv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		ItemSerieServico itemSrv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		
		Usuario usuario = usuarioSrv.ObtemNoAparelho(contexto);
		
		SerieTreino serie = getFiltro().getItem();
		SerieTreinoDBHelper dao = this.getDao();
		serie.setAtiva(false);
		serie.setIdUsuarioS(usuario.getId());
		dao.insertSinc(serie);
		int ordem = 1;
		for (ItemSerie itemSerie:serie.getListaItemSerie_Possui()) {
			itemSerie.setIdSerieTreinoPa(serie.getId());
			itemSerie.setOrdemExecucao(ordem++);
			itemSrv.insereParaSincronizacao(itemSerie);
			int ordemCarga = 1;
			for (CargaPlanejada carga:itemSerie.getListaCargaPlanejada_Possui()) {
				carga.setAtiva(true);
				carga.setOrdemRepeticao(ordemCarga++);
				carga.setIdItemSerieRa(itemSerie.getId());
				cargaSrv.insereParaSincronizacao(carga);
			}
		}
		return serie;
	}

	@Override
	protected SerieTreino ObtemMontadaPorIdImpl(DigicomContexto contexto) {
		CargaPlanejadaServico cargaSrv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		ItemSerieServico itemSrv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);

		SerieTreinoDBHelper dao = this.getDao();
		SerieTreino serie  = dao.getById(getFiltro().getIdSerieTreino());
		if (serie==null) 
				throw new RuntimeException("Nao existe serie treino com id " + getFiltro().getIdSerieTreino() + " verificar em dia_treino o id_serie_treino");
		List<ItemSerie> listaItemSerie = itemSrv.getPorPertencenteASerieTreino(contexto.getContext(), serie.getId());
		for (ItemSerie itemSerie:listaItemSerie) {
			List<CargaPlanejada> listaCarga = cargaSrv.getPorReferenteAItemSerie(contexto.getContext(), itemSerie.getId());
			itemSerie.setListaCargaPlanejada_PossuiByDao(listaCarga);
		}
		serie.setListaItemSerie_PossuiByDao(listaItemSerie);
		return serie;
	}

	@Override
	protected SerieTreino CarregaItemSerieImpl(DigicomContexto contexto) {
		getFiltro().setIdSerieTreino(getFiltro().getItem().getId());
		return this.ObtemMontadaPorIdImpl(contexto);
	}

	@Override
	protected SerieTreino inicializaItemTelaImpl(SerieTreino serieNova, DigicomContexto contexto) {
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		serieNova.setDataCriacao(UtilDatas.getDataTimestampAtual());
		SerieTreinoServico serieTreinoSrv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		serieNova.setIdUsuarioS(usuario.getId());
		serieTreinoSrv.insereParaSincronizacao(serieNova);
		serieNova.setSomenteMemoria(false);
		List<ItemSerie> listaItem = new ArrayList<ItemSerie>();
		serieNova.setListaItemSerie_Possui(listaItem);
		return serieNova;
	}

	@Override
	protected void finalizaItemTelaImpl(SerieTreino itemTela, boolean insercao, DigicomContexto contexto) {
		SerieTreinoServico serieTreinoSrv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		serieTreinoSrv.alteraParaSincronizacao(itemTela);
	}

	
	
}