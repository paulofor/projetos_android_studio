package treinoacademia.tela.trata.impl;

import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.app.R;
import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.modelo.Exercicio;
import treinoacademia.modelo.GrupoMuscular;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.servico.ExercicioServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.GrupoMuscularServico;
import treinoacademia.tela.trata.base.ItemSerieQuadroTrataBase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.ResultadoValidacao;

// Foi alterado, n�o sei se continua funcionando
public class ItemSerieQuadroTrata extends ItemSerieQuadroTrataBase implements OnItemSelectedListener {

	private Spinner spnExercicio = null;
	private Spinner spnGrupoMuscular = null;
	
	private EditText txtRegulagem = null;
	private EditText[] txtRepeticao = null;
	private EditText[] txtCarga = null;
	
	private TextView[][] txtAnterior = null;
	
	private ExercicioServico exercicioSrv = FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
	private ExecucaoItemSerieServico execucaoSrv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
	private GrupoMuscularServico grupoMuscularSrv = FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_SQLITE);

	
	private List<Exercicio> listaExercicio = null;
	private ArrayAdapter<Exercicio> dataAdapter = null;
	
	private GrupoMuscular grupoMuscularCorrente = null;
	
	
	@Override
	protected void inicializaListeners() {
		Button btn = (Button) this.getTela().findViewById(R.id.btnCancelaTrataItemSerie);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				retornaTela();
			}
		});
	}


	

	@Override
	protected void extraiBundleComplemento() {
		this.setSerieTreino((SerieTreino) getBundle().getObjeto(Constantes.CHAVE_SERIE_TREINO));
	}
	
	




	@Override
	protected void inicializaItensTela() {
		
		
		txtRepeticao = new EditText[3];
		txtCarga = new EditText[3];
		
		txtAnterior = new TextView[3][3];
		
		this.txtCarga[0] = (EditText) getTela().findViewById(R.id.txtCarga1);
		this.txtCarga[1] = (EditText) getTela().findViewById(R.id.txtCarga2);
		this.txtCarga[2] = (EditText) getTela().findViewById(R.id.txtCarga3);
		this.txtRepeticao[0] = (EditText) getTela().findViewById(R.id.txtRepeticao1);
		this.txtRepeticao[1] = (EditText) getTela().findViewById(R.id.txtRepeticao2);
		this.txtRepeticao[2] = (EditText) getTela().findViewById(R.id.txtRepeticao3);
		
		txtAnterior[0][0] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior11);
		//txtAnterior[0][1] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior21);
		//txtAnterior[0][2] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior31);
		txtAnterior[1][0] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior12);
		//txtAnterior[1][1] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior22);
		//txtAnterior[1][2] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior32);
		txtAnterior[2][0] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior13);
		//txtAnterior[2][1] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior23);
		//txtAnterior[2][2] = (TextView) getTela().findViewById(R.id.txtExecucaoAnterior33);
		
		
		
		this.spnExercicio = (Spinner) getTela().findViewById(R.id.spnExercicio);
		this.spnExercicio.setEnabled(false);
		this.txtRegulagem = (EditText) getTela().findViewById(R.id.txtRegulagem);
		//this.btnOkTrataItemSerie = (Button) getTela().findViewById(R.id.btnOkTrataItemSerie);
		//carregaSpinnerExercicio();
	}

	
	


	private void carregaSpinnerExercicio() {
		this.spnExercicio.setEnabled(false);
		GrupoMuscular grupoMuscularCarregado = (GrupoMuscular) this.getSpinnerObjeto(R.id.spnGrupoMuscular, "R.id.spnGrupoMuscular");
		if (grupoMuscularCarregado!=null && (grupoMuscularCorrente==null || grupoMuscularCarregado.getId() != grupoMuscularCorrente.getId() || listaExercicio ==  null)) {
			listaExercicio = this.exercicioSrv.getPorParaGrupoMuscular(grupoMuscularCarregado.getId());
			this.spinnerSetLista(R.id.spnExercicio,"R.id.spnExercicio",listaExercicio, this.getContext().getContext());
			this.spnExercicio.setOnItemSelectedListener(this);
		}
		this.spnExercicio.setEnabled(true);
		grupoMuscularCorrente = grupoMuscularCarregado;
	}
	
	private void carregaUltimasExecucoes() {
		// Montar isso na montagem da tela e nao antes.
		// Por dois grandes motivos:
		// 1- Reduz o acoplamento entre a tela e quem chama.
		// 2- Somente dentro da tela se sabe melhor o que vai ser necessario
		Exercicio exercicio = (Exercicio) this.getSpinnerObjeto(R.id.spnExercicio, "R.id.spnExercicio");
		execucaoSrv.getFiltro().setIdExercicio(exercicio.getId());
		execucaoSrv.getFiltro().setQuantidadeUltimasExecucoes(new Long(1));
		List<ExecucaoItemSerie> listaExecucaoSerie = execucaoSrv.UltimasExecucoesUsuarioExercicio(getDCContext());
		getItem().setListaExecucaoItemSerie_Gera(listaExecucaoSerie);
		
		if (listaExecucaoSerie.size() > 0) {
			String data = listaExecucaoSerie.get(0).getDiaTreino_Em().getDataDDMMAAAA();
			
			this.setTexto(R.id.txtData01, "R.id.txtData1", data);
			posicionaTela(0,0,listaExecucaoSerie.get(0));
			posicionaTela(1,0,listaExecucaoSerie.get(1));
			posicionaTela(2,0,listaExecucaoSerie.get(2));
		} else {
			posicionaTela(0,0,null);
			posicionaTela(1,0,null);
			posicionaTela(2,0,null);
		}
		if (listaExecucaoSerie.size() > 3) {
			String data = listaExecucaoSerie.get(3).getDiaTreino_Em().getDataDDMMAAAA();
			//this.setTexto(R.id.txtData2, "R.id.txtData2", data);
			posicionaTela(0,1,listaExecucaoSerie.get(3));
			posicionaTela(1,1,listaExecucaoSerie.get(4));
			posicionaTela(2,1,listaExecucaoSerie.get(5));
		}  else {
			//posicionaTela(0,1,null);
			//posicionaTela(1,1,null);
			//posicionaTela(2,1,null);
		}
		if (listaExecucaoSerie.size() > 6) {
			String data = listaExecucaoSerie.get(6).getDiaTreino_Em().getDataDDMMAAAA();
			//this.setTexto(R.id.txtData3, "R.id.txtData3", data);
			posicionaTela(0,2,listaExecucaoSerie.get(6));
			posicionaTela(1,2,listaExecucaoSerie.get(7));
			posicionaTela(2,2,listaExecucaoSerie.get(8));
		} else {
			//posicionaTela(0,2,null);
			//posicionaTela(1,2,null);
			//posicionaTela(2,2,null);
		}

	}
	

	@Override
	protected void carregaElementosTela() {
		List<GrupoMuscular> listaGrupo = this.grupoMuscularSrv.getAllTela(getContext().getContext());
		this.spnGrupoMuscular = this.getSpinner(R.id.spnGrupoMuscular, "R.id.spnGrupoMuscular", listaGrupo, getContext().getContext());
		this.spnGrupoMuscular.setOnItemSelectedListener(this);
		this.spnGrupoMuscular.setSelection(0);
		
	}
	
	private void posicionaTela(int linha, int coluna, ExecucaoItemSerie execucao) {
		String texto = "-";
		if (execucao!=null)
			texto = execucao.getQuantidadeRepeticao() + "x" + execucao.getCargaUtilizada();
		txtAnterior[linha][coluna].setText(texto);
	}
	private int posicaoAdapter(long id) {
		int posic = 0;
		for (Exercicio exercicio : listaExercicio) {
			if (exercicio.getId()==id) {
				break;
			} else {
				posic ++;
			}
		}
		return posic;
	}
	
	
	@Override
	protected void transfereTelaParaVo(ItemSerie item) {
		String regulagem = this.txtRegulagem.getText().toString();
		Exercicio exercicio = (Exercicio) this.spnExercicio.getSelectedItem();
		// Alterando um ItemSerie
		item.setIdExercicioEd(exercicio.getIdExercicio());
		item.setParametros(regulagem);
		item.setExercicio_ExecucaoDe(exercicio);
		// CargaPlanejada
		int i = 0;
		for (CargaPlanejada cargaPlanejada : item.getListaCargaPlanejada_Possui()) {
			cargaPlanejada.setOrdemRepeticao(i+1);
			cargaPlanejada.setValorCarga(Float.parseFloat(this.txtCarga[i].getText().toString()));
			cargaPlanejada.setQuantidadeRepeticao(Long.parseLong(this.txtRepeticao[i].getText().toString()));
			//cargaPlanejada.setDataInicio(UtilDatas.getTimestampAtual());
			i++;
		}
	}



	@Override
	protected void transfereVoParaTela(ItemSerie item) {
		this.txtRegulagem.setText(item.getParametros());
		grupoMuscularCorrente = item.getExercicio_ExecucaoDe().getGrupoMuscular_Para();
		this.setSpinnerIdObjeto(R.id.spnGrupoMuscular, "R.id.spnGrupoMuscular", grupoMuscularCorrente.getIdGrupoMuscular());
		carregaSpinnerExercicio();
		this.setSpinnerIdObjeto(R.id.spnExercicio, "R.id.spnExercicio", item.getIdExercicioEd());
		int i = 0;
		if (this.txtCarga.length < item.getListaCargaPlanejada_Possui().size()) {
				throw new RuntimeException("Existem " + item.getListaCargaPlanejada_Possui().size() + " cargas para o item (id_item_serie_ra) " + item.getId());
		}
		for (CargaPlanejada cargaPlanejada : item.getListaCargaPlanejada_Possui()) {
			this.txtCarga[i].setText("" + cargaPlanejada.getValorCarga());
			this.txtRepeticao[i].setText("" +cargaPlanejada.getQuantidadeRepeticao());
			i++;
		}
		//carregaUltimasExecucoes();
		//List<ExecucaoItemSerie> listaExecucaoSerie = item.getListaExecucaoItemSerie_Gera(1);
		//String data = listaExecucaoSerie.get(0).getDiaTreino_Em().getDataDDMMAAAA();
		//this.setTexto(R.id.txtData01, "R.id.txtData1", data);
	}


	@Override
	protected ResultadoValidacao validaCamposTela() {
		ResultadoValidacao result = new ResultadoValidacao();
		result.setProsseguir(true);
		return result;
	}



	@Override
	public String getTituloTela() {
		return "Exercicio de Serie";
	}



	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,	long id) {
		if (spinnerCompare(R.id.spnGrupoMuscular,"R.id.spnGrupoMuscular",parent))
			this.carregaSpinnerExercicio();
		if (spinnerCompare(R.id.spnExercicio,"R.id.spnExercicio",parent)) {
			this.carregaUltimasExecucoes();
		}
	}



	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	
}
