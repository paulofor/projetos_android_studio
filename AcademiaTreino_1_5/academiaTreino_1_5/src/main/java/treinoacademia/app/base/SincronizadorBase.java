
package  treinoacademia.app.base;

import br.com.digicom.servico.ServicoLocal;
import br.com.digicom.servico.ServicoRemoto;
import br.com.digicom.network.WifiTest;
import br.com.digicom.dao.DataSource;
import br.com.digicom.log.DCLog;
import br.com.digicom.dao.DaoSincronismo;
import treinoacademia.modelo.*;
import treinoacademia.servico.*;
import java.util.List;
import android.content.Context;
import android.util.Log;

// extends AsyncTask<URL, Integer, Long> 
public abstract class SincronizadorBase  extends Thread{
	
	Context ctx = null;
	private String TAG = "Sincronizacao";
	
	public void setContexto(Context contexto) {
		ctx = contexto;
	}
	public void run()  {
		if (WifiTest.existe(ctx)) {
			Log.d(TAG , "Inicio Sincronizacao");
			sincronizaDispositivoUsuario(ctx);
			sincronizaUsuario(ctx); // parece que vai precisar sempre. nao faz sentido Usuario_Usuario
			principal(ctx);
			atualizaDispositivo();
			Log.d(TAG , "Final Sincronizacao");
		} else {
			Log.d(TAG , "Sem Sincronizacao - Wifi Indisponivel");
		}
	}
	
	public static void atualizaDispositivo() {
		DispositivoUsuarioServico dispositivoSrv = FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
		DispositivoUsuario dispositivo = dispositivoSrv.getCorrente();
		if (dispositivo!=null && dispositivo.getMelhorPath()!=null) {
			if (dispositivo.getMelhorPath().compareTo(DataSource.getValorPath())!=0) {
				dispositivo.setMelhorPath(DataSource.getValorPath());
				dispositivoSrv.alteraParaSincronizacao(dispositivo);
			}
		}
	}
	
	protected abstract void principal(Context contexto);

	
	@Deprecated
	private void sincronizaUsuario(Context contexto) {
		ServicoRemoto<Usuario> servicoRemoto = (ServicoRemoto<Usuario>)FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Usuario> servicoLocal = (ServicoLocal<Usuario>) FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Usuario> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Usuario: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Usuario> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Usuario: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	

	
	@Deprecated
	protected void sincronizaExercicio(Context contexto) {
		ServicoRemoto<Exercicio> servicoRemoto = (ServicoRemoto<Exercicio>)FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Exercicio> servicoLocal = (ServicoLocal<Exercicio>) FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Exercicio> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Exercicio: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Exercicio> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Exercicio: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	
	@Deprecated
	protected void sincronizaExercicio_Usuario(Context contexto) {
		ServicoRemoto<Exercicio> servicoRemoto = (ServicoRemoto<Exercicio>)FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Exercicio> servicoLocal = (ServicoLocal<Exercicio>) FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<Exercicio> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Exercicio: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<Exercicio> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Exercicio: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	
	@Deprecated
	protected void sincronizaSerieTreino(Context contexto) {
		ServicoRemoto<SerieTreino> servicoRemoto = (ServicoRemoto<SerieTreino>)FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<SerieTreino> servicoLocal = (ServicoLocal<SerieTreino>) FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<SerieTreino> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"SerieTreino: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<SerieTreino> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"SerieTreino: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
			// Dependente
			ServicoRemoto<ItemSerie> ItemSerieRemotoDep = (ServicoRemoto<ItemSerie>)FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ItemSerie> ItemSerieLocalDep = (ServicoLocal<ItemSerie>) FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<ItemSerie> listaItemSerieDepSincAlteracao = ItemSerieLocalDep.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: servicoLocal.listaSincronizadaAlteracao : " + listaItemSerieDepSincAlteracao.size());
			if (listaItemSerieDepSincAlteracao!=null && !listaItemSerieDepSincAlteracao.isEmpty()) {
				ItemSerieRemotoDep.listaSincronizadaAlteracao(listaItemSerieDepSincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<ItemSerie> listaItemSerieDep = ItemSerieRemotoDep.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: servicoRemoto.listaSincronizada : " + listaItemSerieDep.size());
			ItemSerieLocalDep.dropCreate(contexto);
			ItemSerieLocalDep.insertAll(listaItemSerieDep, contexto);
			
		
			
			// Dependente Nivel 2
			ServicoRemoto<CargaPlanejada> servicoRemotoDep2 = (ServicoRemoto<CargaPlanejada>)FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<CargaPlanejada> servicoLocalDep2 = (ServicoLocal<CargaPlanejada>) FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<CargaPlanejada> listaDep2SincAlteracao = servicoLocalDep2.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: servicoLocal.listaSincronizadaAlteracao : " + listaDep2SincAlteracao.size());
			if (listaDep2SincAlteracao!=null && !listaDep2SincAlteracao.isEmpty()) {
				servicoRemotoDep2.listaSincronizadaAlteracao(listaDep2SincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<CargaPlanejada> listaCargaPlanejadaDep2 = servicoRemotoDep2.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: servicoRemoto.listaSincronizada : " + listaCargaPlanejadaDep2.size());
			servicoLocalDep2.dropCreate(contexto);
			servicoLocalDep2.insertAll(listaCargaPlanejadaDep2, contexto);
			
			// Dependente
			ServicoRemoto<DiaTreino> DiaTreinoRemotoDep = (ServicoRemoto<DiaTreino>)FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<DiaTreino> DiaTreinoLocalDep = (ServicoLocal<DiaTreino>) FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<DiaTreino> listaDiaTreinoDepSincAlteracao = DiaTreinoLocalDep.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: servicoLocal.listaSincronizadaAlteracao : " + listaDiaTreinoDepSincAlteracao.size());
			if (listaDiaTreinoDepSincAlteracao!=null && !listaDiaTreinoDepSincAlteracao.isEmpty()) {
				DiaTreinoRemotoDep.listaSincronizadaAlteracao(listaDiaTreinoDepSincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<DiaTreino> listaDiaTreinoDep = DiaTreinoRemotoDep.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: servicoRemoto.listaSincronizada : " + listaDiaTreinoDep.size());
			DiaTreinoLocalDep.dropCreate(contexto);
			DiaTreinoLocalDep.insertAll(listaDiaTreinoDep, contexto);
			
		
			
			// Dependente Nivel 2
			ServicoRemoto<ExecucaoItemSerie> servicoRemotoDep2 = (ServicoRemoto<ExecucaoItemSerie>)FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ExecucaoItemSerie> servicoLocalDep2 = (ServicoLocal<ExecucaoItemSerie>) FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<ExecucaoItemSerie> listaDep2SincAlteracao = servicoLocalDep2.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: servicoLocal.listaSincronizadaAlteracao : " + listaDep2SincAlteracao.size());
			if (listaDep2SincAlteracao!=null && !listaDep2SincAlteracao.isEmpty()) {
				servicoRemotoDep2.listaSincronizadaAlteracao(listaDep2SincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<ExecucaoItemSerie> listaExecucaoItemSerieDep2 = servicoRemotoDep2.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: servicoRemoto.listaSincronizada : " + listaExecucaoItemSerieDep2.size());
			servicoLocalDep2.dropCreate(contexto);
			servicoLocalDep2.insertAll(listaExecucaoItemSerieDep2, contexto);
			
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	
	@Deprecated
	protected void sincronizaSerieTreino_Usuario(Context contexto) {
		ServicoRemoto<SerieTreino> servicoRemoto = (ServicoRemoto<SerieTreino>)FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<SerieTreino> servicoLocal = (ServicoLocal<SerieTreino>) FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<SerieTreino> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"SerieTreino: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<SerieTreino> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"SerieTreino: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
			// Dependente
			ServicoRemoto<ItemSerie> ItemSerieRemotoDep = (ServicoRemoto<ItemSerie>)FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ItemSerie> ItemSerieLocalDep = (ServicoLocal<ItemSerie>) FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
			if (listaSinc.isEmpty()) {
				List<ItemSerie> listaLocalItemSerie = ItemSerieLocalDep.listaSincronizadaAlteracao(contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie>: servicoLocal : " + listaLocalItemSerie.size());
				if (listaLocalItemSerie!=null && !listaLocalItemSerie.isEmpty()) {
					ItemSerieRemotoDep.listaSincronizadaAlteracao(listaLocalItemSerie,contexto);
					ItemSerieLocalDep.limpaSinc(listaLocalItemSerie);
				}
			}
			List<ItemSerie> listaItemSerieDep = ItemSerieRemotoDep.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: servicoRemoto : " + listaItemSerieDep.size());
			ItemSerieLocalDep.dropCreate(contexto);
			ItemSerieLocalDep.insertAll(listaItemSerieDep, contexto);
			
			
			// Dependente Nivel 2
			ServicoRemoto<CargaPlanejada> CargaPlanejadaRemotoDep2 = (ServicoRemoto<CargaPlanejada>)FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<CargaPlanejada> CargaPlanejadaLocalDep2 = (ServicoLocal<CargaPlanejada>) FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
			List<CargaPlanejada> listaCargaPlanejadaDep2 = CargaPlanejadaRemotoDep2.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: servicoRemoto : " + listaCargaPlanejadaDep2.size());
			CargaPlanejadaLocalDep2.dropCreate(contexto);
			CargaPlanejadaLocalDep2.insertAll(listaCargaPlanejadaDep2, contexto);
			
			// Dependente
			ServicoRemoto<DiaTreino> DiaTreinoRemotoDep = (ServicoRemoto<DiaTreino>)FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<DiaTreino> DiaTreinoLocalDep = (ServicoLocal<DiaTreino>) FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
			if (listaSinc.isEmpty()) {
				List<DiaTreino> listaLocalDiaTreino = DiaTreinoLocalDep.listaSincronizadaAlteracao(contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino>: servicoLocal : " + listaLocalDiaTreino.size());
				if (listaLocalDiaTreino!=null && !listaLocalDiaTreino.isEmpty()) {
					DiaTreinoRemotoDep.listaSincronizadaAlteracao(listaLocalDiaTreino,contexto);
					DiaTreinoLocalDep.limpaSinc(listaLocalDiaTreino);
				}
			}
			List<DiaTreino> listaDiaTreinoDep = DiaTreinoRemotoDep.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: servicoRemoto : " + listaDiaTreinoDep.size());
			DiaTreinoLocalDep.dropCreate(contexto);
			DiaTreinoLocalDep.insertAll(listaDiaTreinoDep, contexto);
			
			
			// Dependente Nivel 2
			ServicoRemoto<ExecucaoItemSerie> ExecucaoItemSerieRemotoDep2 = (ServicoRemoto<ExecucaoItemSerie>)FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ExecucaoItemSerie> ExecucaoItemSerieLocalDep2 = (ServicoLocal<ExecucaoItemSerie>) FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
			List<ExecucaoItemSerie> listaExecucaoItemSerieDep2 = ExecucaoItemSerieRemotoDep2.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: servicoRemoto : " + listaExecucaoItemSerieDep2.size());
			ExecucaoItemSerieLocalDep2.dropCreate(contexto);
			ExecucaoItemSerieLocalDep2.insertAll(listaExecucaoItemSerieDep2, contexto);
			
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	
	@Deprecated
	protected void sincronizaItemSerie(Context contexto) {
		ServicoRemoto<ItemSerie> servicoRemoto = (ServicoRemoto<ItemSerie>)FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ItemSerie> servicoLocal = (ServicoLocal<ItemSerie>) FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ItemSerie> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ItemSerie> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
			// Dependente
			ServicoRemoto<CargaPlanejada> CargaPlanejadaRemotoDep = (ServicoRemoto<CargaPlanejada>)FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<CargaPlanejada> CargaPlanejadaLocalDep = (ServicoLocal<CargaPlanejada>) FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<CargaPlanejada> listaCargaPlanejadaDepSincAlteracao = CargaPlanejadaLocalDep.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: servicoLocal.listaSincronizadaAlteracao : " + listaCargaPlanejadaDepSincAlteracao.size());
			if (listaCargaPlanejadaDepSincAlteracao!=null && !listaCargaPlanejadaDepSincAlteracao.isEmpty()) {
				CargaPlanejadaRemotoDep.listaSincronizadaAlteracao(listaCargaPlanejadaDepSincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<CargaPlanejada> listaCargaPlanejadaDep = CargaPlanejadaRemotoDep.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: servicoRemoto.listaSincronizada : " + listaCargaPlanejadaDep.size());
			CargaPlanejadaLocalDep.dropCreate(contexto);
			CargaPlanejadaLocalDep.insertAll(listaCargaPlanejadaDep, contexto);
			
		
			
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	
	@Deprecated
	protected void sincronizaItemSerie_Usuario(Context contexto) {
		ServicoRemoto<ItemSerie> servicoRemoto = (ServicoRemoto<ItemSerie>)FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ItemSerie> servicoLocal = (ServicoLocal<ItemSerie>) FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ItemSerie> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ItemSerie> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
			// Dependente
			ServicoRemoto<CargaPlanejada> CargaPlanejadaRemotoDep = (ServicoRemoto<CargaPlanejada>)FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<CargaPlanejada> CargaPlanejadaLocalDep = (ServicoLocal<CargaPlanejada>) FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
			if (listaSinc.isEmpty()) {
				List<CargaPlanejada> listaLocalCargaPlanejada = CargaPlanejadaLocalDep.listaSincronizadaAlteracao(contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada>: servicoLocal : " + listaLocalCargaPlanejada.size());
				if (listaLocalCargaPlanejada!=null && !listaLocalCargaPlanejada.isEmpty()) {
					CargaPlanejadaRemotoDep.listaSincronizadaAlteracao(listaLocalCargaPlanejada,contexto);
					CargaPlanejadaLocalDep.limpaSinc(listaLocalCargaPlanejada);
				}
			}
			List<CargaPlanejada> listaCargaPlanejadaDep = CargaPlanejadaRemotoDep.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: servicoRemoto : " + listaCargaPlanejadaDep.size());
			CargaPlanejadaLocalDep.dropCreate(contexto);
			CargaPlanejadaLocalDep.insertAll(listaCargaPlanejadaDep, contexto);
			
			
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	
	@Deprecated
	protected void sincronizaCargaPlanejada(Context contexto) {
		ServicoRemoto<CargaPlanejada> servicoRemoto = (ServicoRemoto<CargaPlanejada>)FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CargaPlanejada> servicoLocal = (ServicoLocal<CargaPlanejada>) FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<CargaPlanejada> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<CargaPlanejada> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	
	@Deprecated
	protected void sincronizaCargaPlanejada_Usuario(Context contexto) {
		ServicoRemoto<CargaPlanejada> servicoRemoto = (ServicoRemoto<CargaPlanejada>)FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CargaPlanejada> servicoLocal = (ServicoLocal<CargaPlanejada>) FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<CargaPlanejada> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<CargaPlanejada> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	
	@Deprecated
	protected void sincronizaExecucaoItemSerie(Context contexto) {
		ServicoRemoto<ExecucaoItemSerie> servicoRemoto = (ServicoRemoto<ExecucaoItemSerie>)FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ExecucaoItemSerie> servicoLocal = (ServicoLocal<ExecucaoItemSerie>) FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ExecucaoItemSerie> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ExecucaoItemSerie> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	
	@Deprecated
	protected void sincronizaExecucaoItemSerie_Usuario(Context contexto) {
		ServicoRemoto<ExecucaoItemSerie> servicoRemoto = (ServicoRemoto<ExecucaoItemSerie>)FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ExecucaoItemSerie> servicoLocal = (ServicoLocal<ExecucaoItemSerie>) FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ExecucaoItemSerie> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ExecucaoItemSerie> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	
	@Deprecated
	protected void sincronizaDiaTreino(Context contexto) {
		ServicoRemoto<DiaTreino> servicoRemoto = (ServicoRemoto<DiaTreino>)FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<DiaTreino> servicoLocal = (ServicoLocal<DiaTreino>) FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<DiaTreino> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<DiaTreino> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
			// Dependente
			ServicoRemoto<ExecucaoItemSerie> ExecucaoItemSerieRemotoDep = (ServicoRemoto<ExecucaoItemSerie>)FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ExecucaoItemSerie> ExecucaoItemSerieLocalDep = (ServicoLocal<ExecucaoItemSerie>) FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<ExecucaoItemSerie> listaExecucaoItemSerieDepSincAlteracao = ExecucaoItemSerieLocalDep.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: servicoLocal.listaSincronizadaAlteracao : " + listaExecucaoItemSerieDepSincAlteracao.size());
			if (listaExecucaoItemSerieDepSincAlteracao!=null && !listaExecucaoItemSerieDepSincAlteracao.isEmpty()) {
				ExecucaoItemSerieRemotoDep.listaSincronizadaAlteracao(listaExecucaoItemSerieDepSincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<ExecucaoItemSerie> listaExecucaoItemSerieDep = ExecucaoItemSerieRemotoDep.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: servicoRemoto.listaSincronizada : " + listaExecucaoItemSerieDep.size());
			ExecucaoItemSerieLocalDep.dropCreate(contexto);
			ExecucaoItemSerieLocalDep.insertAll(listaExecucaoItemSerieDep, contexto);
			
		
			
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	
	@Deprecated
	protected void sincronizaDiaTreino_Usuario(Context contexto) {
		ServicoRemoto<DiaTreino> servicoRemoto = (ServicoRemoto<DiaTreino>)FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<DiaTreino> servicoLocal = (ServicoLocal<DiaTreino>) FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<DiaTreino> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<DiaTreino> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
			// Dependente
			ServicoRemoto<ExecucaoItemSerie> ExecucaoItemSerieRemotoDep = (ServicoRemoto<ExecucaoItemSerie>)FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ExecucaoItemSerie> ExecucaoItemSerieLocalDep = (ServicoLocal<ExecucaoItemSerie>) FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
			if (listaSinc.isEmpty()) {
				List<ExecucaoItemSerie> listaLocalExecucaoItemSerie = ExecucaoItemSerieLocalDep.listaSincronizadaAlteracao(contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie>: servicoLocal : " + listaLocalExecucaoItemSerie.size());
				if (listaLocalExecucaoItemSerie!=null && !listaLocalExecucaoItemSerie.isEmpty()) {
					ExecucaoItemSerieRemotoDep.listaSincronizadaAlteracao(listaLocalExecucaoItemSerie,contexto);
					ExecucaoItemSerieLocalDep.limpaSinc(listaLocalExecucaoItemSerie);
				}
			}
			List<ExecucaoItemSerie> listaExecucaoItemSerieDep = ExecucaoItemSerieRemotoDep.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: servicoRemoto : " + listaExecucaoItemSerieDep.size());
			ExecucaoItemSerieLocalDep.dropCreate(contexto);
			ExecucaoItemSerieLocalDep.insertAll(listaExecucaoItemSerieDep, contexto);
			
			
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	
	@Deprecated
	private void sincronizaDispositivoUsuario(Context contexto) {
		ServicoRemoto<DispositivoUsuario> servicoRemoto = (ServicoRemoto<DispositivoUsuario>)FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<DispositivoUsuario> servicoLocal = (ServicoLocal<DispositivoUsuario>) FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<DispositivoUsuario> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DispositivoUsuario: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<DispositivoUsuario> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DispositivoUsuario: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	

	
	@Deprecated
	protected void sincronizaGrupoMuscular(Context contexto) {
		ServicoRemoto<GrupoMuscular> servicoRemoto = (ServicoRemoto<GrupoMuscular>)FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<GrupoMuscular> servicoLocal = (ServicoLocal<GrupoMuscular>) FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<GrupoMuscular> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"GrupoMuscular: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<GrupoMuscular> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"GrupoMuscular: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	
	@Deprecated
	protected void sincronizaGrupoMuscular_Usuario(Context contexto) {
		ServicoRemoto<GrupoMuscular> servicoRemoto = (ServicoRemoto<GrupoMuscular>)FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<GrupoMuscular> servicoLocal = (ServicoLocal<GrupoMuscular>) FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<GrupoMuscular> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"GrupoMuscular: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<GrupoMuscular> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"GrupoMuscular: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	
	@Deprecated
	protected void sincronizaErroException(Context contexto) {
		ServicoRemoto<ErroException> servicoRemoto = (ServicoRemoto<ErroException>)FabricaServico.getInstancia().getErroExceptionServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ErroException> servicoLocal = (ServicoLocal<ErroException>) FabricaServico.getInstancia().getErroExceptionServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ErroException> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ErroException: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ErroException> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ErroException: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
			
		/*
		
		*/
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	
	@Deprecated
	protected void sincronizaErroException_Usuario(Context contexto) {
		ServicoRemoto<ErroException> servicoRemoto = (ServicoRemoto<ErroException>)FabricaServico.getInstancia().getErroExceptionServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ErroException> servicoLocal = (ServicoLocal<ErroException>) FabricaServico.getInstancia().getErroExceptionServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ErroException> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ErroException: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ErroException> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ErroException: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}


	// ------------- Sincronismo Vers?o 2 ---------------------
	
	
	protected void sincronizaUsuario(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Usuario> servicoRemoto = (ServicoRemoto<Usuario>)FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Usuario> servicoLocal = (ServicoLocal<Usuario>) FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Usuario> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Usuario: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Usuario: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaExercicio(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Exercicio> servicoRemoto = (ServicoRemoto<Exercicio>)FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Exercicio> servicoLocal = (ServicoLocal<Exercicio>) FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Exercicio> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Exercicio: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Exercicio: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaSerieTreino(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<SerieTreino> servicoRemoto = (ServicoRemoto<SerieTreino>)FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<SerieTreino> servicoLocal = (ServicoLocal<SerieTreino>) FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<SerieTreino> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"SerieTreino: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"SerieTreino: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaItemSerie(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ItemSerie> servicoRemoto = (ServicoRemoto<ItemSerie>)FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ItemSerie> servicoLocal = (ServicoLocal<ItemSerie>) FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ItemSerie> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ItemSerie: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaCargaPlanejada(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<CargaPlanejada> servicoRemoto = (ServicoRemoto<CargaPlanejada>)FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CargaPlanejada> servicoLocal = (ServicoLocal<CargaPlanejada>) FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<CargaPlanejada> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CargaPlanejada: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaExecucaoItemSerie(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ExecucaoItemSerie> servicoRemoto = (ServicoRemoto<ExecucaoItemSerie>)FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ExecucaoItemSerie> servicoLocal = (ServicoLocal<ExecucaoItemSerie>) FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ExecucaoItemSerie> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ExecucaoItemSerie: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaDiaTreino(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<DiaTreino> servicoRemoto = (ServicoRemoto<DiaTreino>)FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<DiaTreino> servicoLocal = (ServicoLocal<DiaTreino>) FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<DiaTreino> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DiaTreino: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaDispositivoUsuario(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<DispositivoUsuario> servicoRemoto = (ServicoRemoto<DispositivoUsuario>)FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<DispositivoUsuario> servicoLocal = (ServicoLocal<DispositivoUsuario>) FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<DispositivoUsuario> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DispositivoUsuario: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DispositivoUsuario: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaGrupoMuscular(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<GrupoMuscular> servicoRemoto = (ServicoRemoto<GrupoMuscular>)FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<GrupoMuscular> servicoLocal = (ServicoLocal<GrupoMuscular>) FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<GrupoMuscular> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"GrupoMuscular: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"GrupoMuscular: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaErroException(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ErroException> servicoRemoto = (ServicoRemoto<ErroException>)FabricaServico.getInstancia().getErroExceptionServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ErroException> servicoLocal = (ServicoLocal<ErroException>) FabricaServico.getInstancia().getErroExceptionServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ErroException> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ErroException: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ErroException: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	// ------------------------------------------------------------
}