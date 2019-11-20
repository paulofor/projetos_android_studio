
package  treinoacademia.app;

import java.util.ArrayList;
import java.util.List;

import treinoacademia.app.base.PrincipalActivityBase;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.tela.listaedicao.SerieTreinoListaEdicao;
import treinoacademia.tela.listauso.DiaTreinoListaUso;
import treinoacademia.tela.listauso.ExercicioListaUsoHistorico;
import treinoacademia.tela.quadro.ExercicioQuadroLista;
import treinoacademia.view.custom.InicioTreino;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import br.com.digicom.activity.ItemNavigator;
import br.com.digicom.quadro.BundleFragment;
import br.com.digicom.quadro.IFragment;

public class TreinoAcademiaPrincipalActivity extends PrincipalActivityBase {
	

	
	private boolean mSincronizaInicio = true;
	
	@Override
	protected boolean sincronizaInicio() {
		return this.mSincronizaInicio;
	}
	public void setSincronizaInicio(boolean valor) {
		this.mSincronizaInicio = valor;
	}
	

	@Override
	protected void inicializaServicos() {
		DiaTreinoServico diaTreinoSrv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
		diaTreinoSrv.LimpezaBase(null);
		//criaAlarm();
	}

	@Override
	protected List<ItemNavigator> getListaItem() {
		List<ItemNavigator> listaItens = new ArrayList<ItemNavigator>();
		listaItens.add(new ItemNavigator("Treino"));
		listaItens.add(new ItemNavigator("Series"));
		listaItens.add(new ItemNavigator("Exercicios"));
		listaItens.add(new ItemNavigator("Historico por data"));
		listaItens.add(new ItemNavigator("Historico por exercicio"));

		return listaItens;
	}

	@Override
	protected IFragment getFragment(int posicao) {
		IFragment saida = null;
		if (posicao==0) {
			BundleFragment bundle = new BundleFragment();
			saida = new InicioTreino();
			saida.setBundle(bundle);
		}
		if (posicao==1) {
			BundleFragment bundle = new BundleFragment();
			saida = new SerieTreinoListaEdicao();
			saida.setBundle(bundle);
		}
		if (posicao==2) {
			BundleFragment bundle = new BundleFragment();
			saida = new ExercicioQuadroLista();
			saida.setBundle(bundle);
		}
		if (posicao==3) {
			BundleFragment bundle = new BundleFragment();
			saida = new DiaTreinoListaUso();
			saida.setBundle(bundle);
		}
		if (posicao==4) {
			BundleFragment bundle = new BundleFragment();
			saida = new ExercicioListaUsoHistorico();
			saida.setBundle(bundle);
		}
		
		return saida;
	}

	protected Intent getIntent(int posicao) {
		Intent saida = null;
		
		return saida;
	}
	
	
	private void criaAlarm() {
		
		
		//AlarmManager alarmMgr = null;
		//PendingIntent alarmIntent = null;
		
		// Hopefully your alarm will have a lower frequency than this!
		//alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		//        AlarmManager.INTERVAL_HALF_HOUR,
		//        AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);
		
       
       

        Intent alarmIntent = new Intent(this, SincronizadorService.AlarmReceiver.class);
        //Wrap in a pending intent which only fires once.
        PendingIntent pi = PendingIntent.getBroadcast(this, 0,alarmIntent,PendingIntent.FLAG_ONE_SHOT);//getBroadcast(context, 0, i, 0);
        AlarmManager am=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
          
        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR, pi);
    }
}