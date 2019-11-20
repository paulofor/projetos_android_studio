
package treinoacademia.servico.sqlite.impl;

import java.util.List;

import treinoacademia.dao.datasource.DataSourceAplicacao;
import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.Usuario;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.CargaPlanejadaServico;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.ItemSerieServico;
import treinoacademia.servico.UsuarioServico;
import treinoacademia.servico.sqlite.base.ItemSerieServicoSqliteBase;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.dao.DaoException;
import br.com.digicom.log.DCLog;
import br.com.digicom.util.UtilDatas;

public class ItemSerieServicoSqliteImpl extends ItemSerieServicoSqliteBase {

	
	@Override
	public List<ItemSerie> getPorPertencenteASerieTreino(Context contexto, long id) {
		CargaPlanejadaServico cargaSrv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		List<ItemSerie> saida = null;
		List<CargaPlanejada> listaCarga = null;
		try {
			saida = getDao().getPorPertencenteASerieTreino(contexto, id);
			for (ItemSerie item:saida) {
				listaCarga = cargaSrv.getPorReferenteAItemSerie(contexto, item.getId());
				item.setListaCargaPlanejada_Possui(listaCarga);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saida;
	}
	
	@Override
	public List<ItemSerie> ListaPorDiaComExecucaoImpl(DigicomContexto contexto) {
		DiaTreino dia = getFiltro().getDia();
		CargaPlanejadaServico cargaSrv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		ExecucaoItemSerieServico execucaoSrv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		List<ItemSerie> saida = null;
		List<CargaPlanejada> listaCarga = null;
		execucaoSrv.getFiltro().setIdDia(dia.getId());
		try {
			saida = getDao().getPorPertencenteASerieTreino(contexto.getContext(), dia.getSerieTreino_SerieDia().getId());
			for (ItemSerie item:saida) {
				listaCarga = cargaSrv.getPorReferenteAItemSerie(contexto.getContext(), item.getId());
				//item.setListaCargaPlanejada_Possui(listaCarga);
				item.setListaCargaPlanejada_PossuiByDao(listaCarga);
				execucaoSrv.getFiltro().setIdItemSerie(item.getIdItemSerie());
				List<ExecucaoItemSerie> execucoes = execucaoSrv.ObtemPorDiaItemSerie(contexto);
				//item.setListaExecucaoItemSerie_Gera(execucoes);
				item.setListaExecucaoItemSerie_GeraByDao(execucoes);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saida;
	}

	@Override
	protected ItemSerie FinalizaItemSerieImpl(DigicomContexto contexto) {
		DiaTreinoServico diaTreinoSrv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
		DiaTreino diaTreinoAtual = diaTreinoSrv.ultimoInicializado();
		ExecucaoItemSerieServico execucaoItemSrv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		ItemSerie itemSerie = this.getFiltro().getItem();
		int tamanho = itemSerie.getListaExecucaoItemSerie_Gera().size();
		for (int posic = tamanho -3; posic<tamanho; posic ++ ) {
			ExecucaoItemSerie execucao = itemSerie.getListaExecucaoItemSerie_Gera().get(posic);
			execucao.setIdItemSerieRa(itemSerie.getId());
			execucao.setIdDiaTreinoE(diaTreinoAtual.getId());
			execucao.setIdExercicioRa(itemSerie.getIdExercicioEd());
			execucaoItemSrv.insereParaSincronizacao(execucao);
		}
		return itemSerie;
	}
	
	
	
	
	@Override
	protected ItemSerie inicializaItemTelaImpl(DigicomContexto contexto) {
		ItemSerie novo = FabricaVo.criaItemSerie();
		return inicializaItemTelaImpl(novo,contexto);
	}

	@Override
	protected ItemSerie inicializaItemTelaImpl(ItemSerie itemNovo, DigicomContexto contexto) {
		//itemNovo.setIdSerieTreinoPa(getFiltro().getPrincipalInicializacao().getId());
		//ItemSerieServico servico = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		//servico.insereParaSincronizacao(itemNovo);
		//CargaPlanejadaServico cargaSrv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		itemNovo.setSerieTreino_PertencenteA(getFiltro().getPrincipalInicializacao());
		for (int i=0;i<3;i++) {
			CargaPlanejada carga = FabricaVo.criaCargaPlanejada();
			carga.setIdItemSerieRa(itemNovo.getId());
			carga.setAtiva(true);
			itemNovo.addListaCargaPlanejada_Possui(carga);
			//cargaSrv.insereParaSincronizacao(carga);
		}
		
		return itemNovo;
	}

	@Override
	protected void finalizaItemTelaImpl(ItemSerie itemTela, boolean insercao, DigicomContexto contexto) {
		ItemSerieServico servico = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		if (insercao) {
			servico.insereParaSincronizacao(itemTela);
		} else {
			servico.alteraParaSincronizacao(itemTela); // Pode ter alteracao de regulagem
		}
		//CargaPlanejadaServico cargaSrv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		this.getFiltro().setItem(itemTela);
		this.AtualizaCarga(contexto);
	}
	

	@Override
	protected ItemSerie CarregaCompletoImpl(DigicomContexto contexto) {
		List<ItemSerie> saida = null;
		try {
			saida = getDao().getCompletoPorId(this.getFiltro().getItem().getId());
			if (saida.size()==0) {
				throw new RuntimeException("Nenhuma carga encontrada para o ItemSerie = " +this.getFiltro().getItem().getId());
			}
			return saida.get(0);
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			return null;
		}
		
	}

	@Override
	protected ItemSerie CarregaUltimasExecucoesImpl(DigicomContexto contexto) {
		if (getFiltro().getQuantidadeUltimasExecucoes()==null) {
			throw new RuntimeException("Preencher a quantidade de cargas passadas a serem pesquisadas.");
		}
		ItemSerie item = getFiltro().getItem();
		ExecucaoItemSerieServico execucaoSrv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		execucaoSrv.getFiltro().setIdItemSerie(item.getIdItemSerie());
		List<ExecucaoItemSerie> listaExecucao = null;
		execucaoSrv.getFiltro().setQuantidadeUltimasExecucoes(getFiltro().getQuantidadeUltimasExecucoes());
		if (item.getId()!=0) {
			listaExecucao = execucaoSrv.UltimasExecucoesItemSerie(contexto);
		} else {
			UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario usuario = usuarioSrv.getCorrente();
			execucaoSrv.getFiltro().setIdUsuario(usuario.getId());
			execucaoSrv.getFiltro().setIdExercicio(item.getIdExercicioEd());
			listaExecucao = execucaoSrv.UltimasExecucoesUsuarioExercicio(contexto);
		}
		item.setListaExecucaoItemSerie_Gera(listaExecucao);
		
		return item;
	}

	@Override
	protected ItemSerie AtualizaCargaImpl(DigicomContexto contexto) {
		ItemSerie itemAtualizacao = getFiltro().getItem();
		if (itemAtualizacao==null) {
			throw new RuntimeException("Faltando parametro item");
		}
		this.getFiltro().setQuantidadeUltimasExecucoes(new Long(3));
		this.CarregaUltimasExecucoes(contexto); // Carrega Ultimas Execucoes do item corrente.
		CargaPlanejadaServico cargaSrv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		if (itemAtualizacao.getListaCargaPlanejada_Possui().size()==0) {
			// E novo pode apagar e alterar
			List<CargaPlanejada> listaCarga = itemAtualizacao.getListaCargaPlanejada_Possui();
			for (CargaPlanejada carga : listaCarga) {
				carga.setDataInicio(UtilDatas.getDataTimestampAtual());
				carga.setItemSerie_ReferenteA(itemAtualizacao);
				cargaSrv.insereParaSincronizacao(carga);
			}
		} else {
			// E uma evolucao
			cargaSrv.getFiltro().setIdExercicio(itemAtualizacao.getIdExercicioEd());
			//List<CargaPlanejada> listaCarga = cargaSrv.ListaCargaPorExercicio(contexto);
			List<CargaPlanejada> listaCarga = cargaSrv.ListaCargaAtivaPorExercicio(contexto);
			for (CargaPlanejada carga : listaCarga) {
				carga.setAtiva(false);
				carga.setDataFinal(UtilDatas.getDataTimestampAtual());
				cargaSrv.alteraParaSincronizacao(carga);
			}
			// Novos
			listaCarga = itemAtualizacao.getListaCargaPlanejada_Possui();
			for (CargaPlanejada carga : listaCarga) {
				carga.setAtiva(true);
				carga.setDataInicio(UtilDatas.getDataTimestampAtual());
				carga.setDataFinal(null);
				carga.setItemSerie_ReferenteA(itemAtualizacao);
				cargaSrv.insereParaSincronizacao(carga);
			}
		}
		return itemAtualizacao;
	}
	
	
}