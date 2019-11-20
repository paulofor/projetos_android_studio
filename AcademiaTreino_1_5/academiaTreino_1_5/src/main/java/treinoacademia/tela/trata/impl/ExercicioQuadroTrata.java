package treinoacademia.tela.trata.impl;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.Exercicio;
import treinoacademia.modelo.GrupoMuscular;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.GrupoMuscularServico;
import treinoacademia.tela.trata.base.ExercicioQuadroTrataBase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.com.digicom.quadro.ResultadoValidacao;
import br.com.digicom.widget.util.SpinnerUtil;

public class ExercicioQuadroTrata extends ExercicioQuadroTrataBase {

	private EditText txtTitulo = null;
	private EditText txtSubtitulo = null;
	
	private Spinner spnGrupoMuscularExercicio = null;
	private GrupoMuscularServico grupoMuscularSrv = null;
	
	private void criaSpinner() {
		grupoMuscularSrv = FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_SQLITE);
		List<GrupoMuscular> lista = grupoMuscularSrv.getAllTela(this.getContext().getContext());
		spnGrupoMuscularExercicio  = this.getSpinner(R.id.spnGrupoMuscularExercicio,"R.id.spnGrupoMuscularExercicio", lista, getContext().getContext());
	}
	

	@Override
	protected void transfereTelaParaVo(Exercicio valor) {
		String subtitulo = this.txtSubtitulo.getText().toString();
		String titulo = this.txtTitulo.getText().toString();
		valor.setDescricaoExercicio("");
		valor.setImagem("");
		valor.setTitulo(titulo);
		valor.setSubtitulo(subtitulo);
		valor.setGrupoMuscular_Para((GrupoMuscular)getSpinnerObjeto(R.id.spnGrupoMuscularExercicio, "R.id.spnGrupoMuscularExercicio"));
	}



	@Override
	protected void transfereVoParaTela(Exercicio vo) {
		this.txtSubtitulo.setText(vo.getSubtitulo());
		this.txtTitulo.setText(vo.getTitulo());
		SpinnerUtil.setIdObj(vo.getIdGrupoMuscularP(),this.spnGrupoMuscularExercicio);
		
	}



	@Override
	protected EditText getUltimoCampo() {
		return txtSubtitulo;
	}



	@Override
	protected void inicializaItensTela() {
		this.txtTitulo = (EditText) getTela().findViewById(R.id.txtTitulo);
		this.txtSubtitulo = (EditText) getTela().findViewById(R.id.txtSubtitulo);
		this.criaSpinner();
	}



	@Override
	protected ResultadoValidacao validaCamposTela() {
		ResultadoValidacao result = new ResultadoValidacao();
		result.setProsseguir(true);
		return result;
	}


	@Override
	public String getTituloTela() {
		return "Exercicio";
	}

	
	
	@Override
	protected void inicializaListeners() {
		Button btn = (Button) getTela().findViewById(R.id.btnCancelaTrataExercicio);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				retornaTela();
			}
		});
	}


	
	
}
