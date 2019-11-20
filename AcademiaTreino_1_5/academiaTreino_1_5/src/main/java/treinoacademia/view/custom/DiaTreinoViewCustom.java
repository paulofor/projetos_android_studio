package treinoacademia.view.custom;

import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.app.R;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.ItemSerieServico;
import treinoacademia.servico.SerieTreinoServico;
import treinoacademia.tela.listauso.ItemSerieListaUso;
import treinoacademia.tela.quadro.FabricaFragment;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseFragment;
import br.com.digicom.quadro.BundleFragment;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.widget.DCCronometro;

public class DiaTreinoViewCustom extends BaseFragment{
	
	private DCCronometro mCronometro = null;
	
	private Context contexto;
	
	// Model
	private DiaTreino item;
	//private SerieTreino serieTreino = null;
	
	// Servicos
	private SerieTreinoServico serieTreinoSrv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
	private DiaTreinoServico diaTreinoSrv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
	private ItemSerieServico itemSerieSrv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
	
	// Tela
	//private TextView dataAtual = null;
	private Button btnOutros = null;
	private Button btnInsereItem = null;
	
	
	@Override
	protected ResourceObj getLayoutTela() {
		return new ResourceObj(R.layout.view_diatreino,"R.layout.view_diatreino");
	}
	@Override
	protected void inicializaItensTela() {
		
		btnOutros = (Button) getTela().findViewById(R.id.btnOutros);
		btnInsereItem = (Button) getTela().findViewById(R.id.btnInsereItem);
		this.mCronometro = (DCCronometro) getTela().findViewById(R.id.dCCronometro1);
	}
	
	
	
	public void setDiaTreino(DiaTreino valor) {
		item = valor;
	}
	
	
	@Override
	protected void carregaElementosTela() {
		//dataAtual.setText(item.getDataDDMMAAAA());
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_DIA_TREINO, item);
		ItemSerieListaUso quadro = (ItemSerieListaUso) FabricaFragment.getInstancia().getItemSerieListaUso(bundle);
		
		TrocaQuadro.getInstancia().setElementoTela(quadro,  R.id.listView, this);
		this.mCronometro.setTempoBeepSegundos(105);
	}
	
	
	@Override
	protected void inicializaListeners() {
		btnOutros.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BundleFragment bundle = new BundleFragment();
				IFragment fragmento =  FabricaFragment.getInstancia().getExercicioListaUso(bundle);
				TrocaQuadro.getInstancia().alteraQuadroPrincipal(fragmento);
			}
		});
		btnInsereItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//diaTreinoSrv.getFiltro().setDiaFinalizado(item);
				finalizaDia();
			}
		});
	}
	
	public void finalizaDia() {
		diaTreinoSrv.finalizaItemTela(item, true,getDCContext());
		Toast.makeText(getDCContext().getContext(), "Dia de treino conclu�do", Toast.LENGTH_SHORT).show();
		TrocaQuadro.getInstancia().retornaQuadro(this);
	}
	
	@Override
	protected void inicioJogoTela() {
	}
	
	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
	}
	
	
	
	@Override
	public void onAlteraQuadro() {
		this.mCronometro.disparaRelogio();
	}
	
	
	
	@Override
	public void onResume() {
		super.onResume();
		atualizaTitulo();
		this.mCronometro.disparaRelogio();
	}
	@Override
	public void extraiBundle() {
		item = (DiaTreino) this.getBundle().getObjeto(Constantes.CHAVE_DIA_TREINO); 
	}
	

	private void atualizaTitulo() {
		
		this.getActivity().getActionBar().setTitle(getTituloTela());
	}
	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getTituloTela() {
	
		return null;
	}
}
