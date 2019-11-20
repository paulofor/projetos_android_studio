package treinoacademia.view.custom;


import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.app.R;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.Exercicio;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.servico.ExercicioServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.SerieTreinoServico;
import treinoacademia.tela.quadro.FabricaFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.quadro.BaseFragment;
import br.com.digicom.quadro.BundleFragment;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.util.UtilDatas;

public class InicioTreino  extends BaseFragment{

	
	private Button btnInicioTreino = null;
	private Button btnInicioTreino2 = null;
	
	private TextView txtLinha1 = null;
	private TextView txtLinha2 = null;
	private TextView txtLinha3 = null;
	
	private List<DiaTreino> listaTreinoAno = null;
	private List<SerieTreino> listaSerie = null;
	private List<Exercicio> listaExercicio = null;
	
	private DiaTreinoServico diaTreinoSrv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
	private SerieTreinoServico serieTreinoSrv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
	private ExercicioServico exercicioSrv = FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
	
	private String linha1 = "";
	private String linha2 = "";
	private String linha3 = "";
	
	@Override
	public void onAlteraQuadro() {
	}

	@Override
	protected void carregaElementosTela() {
		btnInicioTreino = (Button) this.getTela().findViewById(R.id.btnInicioTreino);
		
		
	}
	
	private void carregaEstatisticas() {
		this.listaExercicio = this.exercicioSrv.getAll(this.getContext().getContext());
		this.linha1 = "Exercicios cadastrados: " + listaExercicio.size();
		String data = UtilDatas.primeiroDiaAnoCorrente();
		this.diaTreinoSrv.getFiltro().setDataPesquisa(data);
		this.listaTreinoAno = this.diaTreinoSrv.TreinosPosDataPesquisa(this.getContext());
		this.linha2 = "Treinos no ano: " + this.listaTreinoAno.size();
		
		this.linha3 = "Mais recente: " + listaTreinoAno.get(0).getDataDDMMAAAA();
	}
	

	@Override
	protected void inicializaListeners() {
		btnInicioTreino.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inicializaTreino();
			}
		});
		
	}

	public void inicializaTreino() {
		//DiaTreinoServico diaTreinoSrv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
		DiaTreino diaNovo = diaTreinoSrv.inicializaItemTela(this.getContext());
		
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_DIA_TREINO, diaNovo);
		IFragment saida = FabricaFragment.getInstancia().getDiaTreinoViewCustom(bundle);
		//((DiaTreinoViewCustom)saida).setDiaTreino(diaNovo);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(saida);
	}
	
	
	
	@Override
	protected void inicializaItensTela() {
		// Campos Informativos
		carregaEstatisticas();
		this.setTexto(R.id.txtLinha1, "R.id.txtLinha1", linha1);
		this.setTexto(R.id.txtLinha2, "R.id.txtLinha2", linha2);
		this.setTexto(R.id.txtLinha3, "R.id.txtLinha3", linha3);
	}

	

	@Override
	protected ResourceObj getLayoutTela() {
		return new ResourceObj(R.layout.inicio_treino,"R.layout.inicio_treino");
	}

	@Override
	protected void inicializaServicos() {
		
	}

	@Override
	protected void inicioJogoTela() {
	}

	@Override
	public String getTituloTela() {
		return Constantes.NOME_APLICACAO;
	}

	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}

}
