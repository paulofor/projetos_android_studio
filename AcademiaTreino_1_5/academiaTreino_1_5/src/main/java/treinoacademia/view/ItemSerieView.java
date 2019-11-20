
package  treinoacademia.view;

import treinoacademia.app.R;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.ItemSerieServico;
import treinoacademia.view.base.ItemSerieViewBase;
import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.log.DCLog;
import br.com.digicom.util.UtilDatas;

public class ItemSerieView extends  ItemSerieViewBase{
	
	
	private Button btnInicializa1 = null;
	private Button btnInicializa2 = null;
	private Button btnInicializa3 = null;
	
	
	private TextView txtExercicioTreino = null;

	//private TextView lblRepeticoes1Texto = null;
	private EditText txtRepeticoes1 = null;
	//private TextView lblCarga1Texto = null;
	private EditText txtCargaExecucao1 = null;
	
	
	
	//private TextView lblRepeticoes2Texto = null;
	private EditText txtRepeticoes2 = null;
	//private TextView lblCarga2Texto = null;
	private EditText txtCargaExecucao2 = null;
	
	//private TextView lblRepeticoes3Texto = null;
	private EditText txtRepeticoes3 = null;
	//private TextView lblCarga3Texto = null;
	private EditText txtCargaExecucao3 = null;
	
	private Button btnConcluidoCorrente = null;
	
	private Chronometer chrTempo = null;
	
	private int posicaoSerie = 1;
	
	private Vibrator v = null;
	
	private ItemSerieServico itemSerieSrv = null;
	
	private ExecucaoItemSerieServico execucaoSrv = null;
	private ExecucaoItemSerie execucao1 = null;
	private ExecucaoItemSerie execucao2 = null;
	private ExecucaoItemSerie execucao3 = null;
	
	private ViewGroup lytCmdItemSerie = null;
	
	
	private TextView txtCargaExecucao1Anterior1 = null;
	private TextView txtRepeticoes1Anterior1 = null;
	private TextView txtCargaExecucao2Anterior1 = null;
	private TextView txtRepeticoes2Anterior1 = null;
	private TextView txtCargaExecucao3Anterior1 = null;
	private TextView txtRepeticoes3Anterior1 = null;
	
	private TextView txtCargaExecucao1Anterior2 = null;
	private TextView txtRepeticoes1Anterior2 = null;
	private TextView txtCargaExecucao2Anterior2 = null;
	private TextView txtRepeticoes2Anterior2 = null;
	private TextView txtCargaExecucao3Anterior2 = null;
	private TextView txtRepeticoes3Anterior2 = null;
	
	private TextView txtCargaExecucao1Anterior3 = null;
	private TextView txtRepeticoes1Anterior3 = null;
	private TextView txtCargaExecucao2Anterior3 = null;
	private TextView txtRepeticoes2Anterior3 = null;
	private TextView txtCargaExecucao3Anterior3 = null;
	private TextView txtRepeticoes3Anterior3 = null;
	
	private TextView lblRegulagemExecucao = null;
	
	@Override
	protected void inicializaItensTela() {
		txtExercicioTreino = (TextView) getTela().findViewById(R.id.lblExercicioTreino);
		
		//lblRepeticoes1Texto = (TextView) getTela().findViewById(R.id.lblRepeticoes1Texto);
		txtRepeticoes1 = (EditText) getTela().findViewById(R.id.txtRepeticoes1);
		//lblCarga1Texto = (TextView) getTela().findViewById(R.id.lblCarga1Texto);
		txtCargaExecucao1 = (EditText) getTela().findViewById(R.id.txtCargaExecucao1);
		
		//lblRepeticoes2Texto = (TextView) getTela().findViewById(R.id.lblRepeticoes2Texto);
		txtRepeticoes2 = (EditText) getTela().findViewById(R.id.txtRepeticoes2);
		//lblCarga2Texto = (TextView) getTela().findViewById(R.id.lblCarga2Texto);
		txtCargaExecucao2 = (EditText) getTela().findViewById(R.id.txtCargaExecucao2);
		
		//lblRepeticoes3Texto = (TextView) getTela().findViewById(R.id.lblRepeticoes3Texto);
		txtRepeticoes3 = (EditText) getTela().findViewById(R.id.txtRepeticoes3);
		//lblCarga3Texto = (TextView) getTela().findViewById(R.id.lblCarga3Texto);
		txtCargaExecucao3 = (EditText) getTela().findViewById(R.id.txtCargaExecucao3);
		
		btnConcluidoCorrente = (Button) getTela().findViewById(R.id.btnConcluidoCorrente);
		
		btnInicializa1 = (Button) getTela().findViewById(R.id.btnInicializa1);
		btnInicializa2 = (Button) getTela().findViewById(R.id.btnInicializa2);
		btnInicializa3 = (Button) getTela().findViewById(R.id.btnInicializa3);
		
		lytCmdItemSerie = (ViewGroup) getTela().findViewById(R.id.lytCmdItemSerie);
		
		txtCargaExecucao1Anterior1 = (TextView) getTela().findViewById(R.id.txtCargaExecucao1Anterior1);
		txtRepeticoes1Anterior1 = (TextView) getTela().findViewById(R.id.txtRepeticoes1Anterior1);
		txtCargaExecucao2Anterior1 = (TextView) getTela().findViewById(R.id.txtCargaExecucao2Anterior1);
		txtRepeticoes2Anterior1 = (TextView) getTela().findViewById(R.id.txtRepeticoes2Anterior1);
		txtCargaExecucao3Anterior1 = (TextView) getTela().findViewById(R.id.txtCargaExecucao3Anterior1);
		txtRepeticoes3Anterior1 = (TextView) getTela().findViewById(R.id.txtRepeticoes3Anterior1);
		
		txtCargaExecucao1Anterior2 = (TextView) getTela().findViewById(R.id.txtCargaExecucao1Anterior2);
		txtRepeticoes1Anterior2 = (TextView) getTela().findViewById(R.id.txtRepeticoes1Anterior2);
		txtCargaExecucao2Anterior2 = (TextView) getTela().findViewById(R.id.txtCargaExecucao2Anterior2);
		txtRepeticoes2Anterior2 = (TextView) getTela().findViewById(R.id.txtRepeticoes2Anterior2);
		txtCargaExecucao3Anterior2 = (TextView) getTela().findViewById(R.id.txtCargaExecucao3Anterior2);
		txtRepeticoes3Anterior2 = (TextView) getTela().findViewById(R.id.txtRepeticoes3Anterior2);
		
		txtCargaExecucao1Anterior3 = (TextView) getTela().findViewById(R.id.txtCargaExecucao1Anterior3);
		txtRepeticoes1Anterior3 = (TextView) getTela().findViewById(R.id.txtRepeticoes1Anterior3);
		txtCargaExecucao2Anterior3 = (TextView) getTela().findViewById(R.id.txtCargaExecucao2Anterior3);
		txtRepeticoes2Anterior3 = (TextView) getTela().findViewById(R.id.txtRepeticoes2Anterior3);
		txtCargaExecucao3Anterior3 = (TextView) getTela().findViewById(R.id.txtCargaExecucao3Anterior3);
		txtRepeticoes3Anterior3 = (TextView) getTela().findViewById(R.id.txtRepeticoes3Anterior3);

		lblRegulagemExecucao = (TextView) getTela().findViewById(R.id.lblRegulagemExecucao);
	}

	@Override
	protected void inicializaServicos() {
		v = (Vibrator) this.getContext().getContext().getSystemService(Context.VIBRATOR_SERVICE);
		this.execucaoSrv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		this.itemSerieSrv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);
	}

	@Override
	protected void carregaElementosTela() {
		txtExercicioTreino.setText(getItem().getCorrenteExercicio_ExecucaoDe().getDescricaoExercicio().toString());
		lblRegulagemExecucao.setText("Regulagem: " + getItem().getParametros());
		
		if (getItem().getListaCargaPlanejada_Possui().size()>0) {
			txtRepeticoes1.setText("" +this.getItem().getListaCargaPlanejada_PossuiOriginal().get(0).getQuantidadeRepeticao());
			txtCargaExecucao1.setText("" +this.getItem().getListaCargaPlanejada_PossuiOriginal().get(0).getValorCarga());
		}
		if (getItem().getListaCargaPlanejada_Possui().size()>1) {
			txtRepeticoes2.setText("" +this.getItem().getListaCargaPlanejada_PossuiOriginal().get(1).getQuantidadeRepeticao());
			txtCargaExecucao2.setText("" +this.getItem().getListaCargaPlanejada_PossuiOriginal().get(1).getValorCarga());
		}
		if (getItem().getListaCargaPlanejada_Possui().size()>2) {
			txtRepeticoes3.setText("" +this.getItem().getListaCargaPlanejada_PossuiOriginal().get(2).getQuantidadeRepeticao());
			txtCargaExecucao3.setText("" +this.getItem().getListaCargaPlanejada_PossuiOriginal().get(2).getValorCarga());
		}

		chrTempo = (Chronometer) getTela().findViewById(R.id.chrTempo);
		
		// Cargas passadas.
		this.itemSerieSrv.getFiltro().setItem(getItem());
		this.itemSerieSrv.getFiltro().setQuantidadeUltimasExecucoes(new Long(3));
		this.itemSerieSrv.CarregaUltimasExecucoes(this.getContext());
		
		// Dado inicial
		this.txtRepeticoes1Anterior1.setText("-");
		this.txtRepeticoes2Anterior1.setText("-");
		this.txtRepeticoes3Anterior1.setText("-");
		this.txtCargaExecucao1Anterior1.setText("-");
		this.txtCargaExecucao2Anterior1.setText("-");
		this.txtCargaExecucao3Anterior1.setText("-");
		
		this.txtRepeticoes1Anterior2.setText("-");
		this.txtRepeticoes2Anterior2.setText("-");
		this.txtRepeticoes3Anterior2.setText("-");
		this.txtCargaExecucao1Anterior2.setText("-");
		this.txtCargaExecucao2Anterior2.setText("-");
		this.txtCargaExecucao3Anterior2.setText("-");
		
		this.txtRepeticoes1Anterior3.setText("-");
		this.txtRepeticoes2Anterior3.setText("-");
		this.txtRepeticoes3Anterior3.setText("-");
		this.txtCargaExecucao1Anterior3.setText("-");
		this.txtCargaExecucao2Anterior3.setText("-");
		this.txtCargaExecucao3Anterior3.setText("-");
		
		
		
		if (this.getItem().getListaExecucaoItemSerie_GeraOriginal().size()>0) { 
			this.txtRepeticoes1Anterior1.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(0).getQuantidadeRepeticao());
			this.txtRepeticoes2Anterior1.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(1).getQuantidadeRepeticao());
			this.txtRepeticoes3Anterior1.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(2).getQuantidadeRepeticao());
		
			this.txtCargaExecucao1Anterior1.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(0).getCargaUtilizadaTela());
			this.txtCargaExecucao2Anterior1.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(1).getCargaUtilizadaTela());
			this.txtCargaExecucao3Anterior1.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(2).getCargaUtilizadaTela());
		}
		
		if (this.getItem().getListaExecucaoItemSerie_GeraOriginal().size()>3) { 
			this.txtRepeticoes1Anterior2.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(3).getQuantidadeRepeticao());
			this.txtRepeticoes2Anterior2.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(4).getQuantidadeRepeticao());
			this.txtRepeticoes3Anterior2.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(5).getQuantidadeRepeticao());
		
			this.txtCargaExecucao1Anterior2.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(3).getCargaUtilizadaTela());
			this.txtCargaExecucao2Anterior2.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(4).getCargaUtilizadaTela());
			this.txtCargaExecucao3Anterior2.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(5).getCargaUtilizadaTela());
		}
		if (this.getItem().getListaExecucaoItemSerie_GeraOriginal().size()>6) { 
			this.txtRepeticoes1Anterior3.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(6).getQuantidadeRepeticao());
			this.txtRepeticoes2Anterior3.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(7).getQuantidadeRepeticao());
			this.txtRepeticoes3Anterior3.setText("" + this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(8).getQuantidadeRepeticao());
		
			this.txtCargaExecucao1Anterior3.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(6).getCargaUtilizadaTela());
			this.txtCargaExecucao2Anterior3.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(7).getCargaUtilizadaTela());
			this.txtCargaExecucao3Anterior3.setText(this.getItem().getListaExecucaoItemSerie_GeraOriginal().get(8).getCargaUtilizadaTela());
		}

		
	}
	
	public boolean beep() throws InterruptedException {
		AudioManager manager = (AudioManager)this.getActivity().getSystemService(Context.AUDIO_SERVICE);
		int volume = manager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
	    manager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, volume, 0);
		final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
		Thread.sleep(300);
	    tg.startTone(ToneGenerator.TONE_PROP_BEEP,2000);
	    Thread.sleep(100);
	    tg.startTone(ToneGenerator.TONE_PROP_BEEP,2000);
	    manager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, 0);
		return true;
	}

	@Override
	protected void inicializaListeners() {
		btnConcluidoCorrente.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onConcluidoCorrente();
			}
		});
		btnInicializa1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickInicializa(0);
			}
		});
		btnInicializa2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickInicializa(1);
			}
		});
		btnInicializa3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickInicializa(2);
			}
		});
		
		
		this.chrTempo.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer tempo) {
				long segundos = (SystemClock.elapsedRealtime() - tempo.getBase())/1000;
				//long segundos = tempo.getBase() / 1000;
				if (segundos==50) {
					v.vibrate(500);
					try {
						beep();
					} catch (InterruptedException e) {
						DCLog.e(DCLog.MULTIMIDIA, this, e);
					}
				}
			}
		});
		/*
		txtRepeticoes1.setOnFocusChangeListener(new OnFocusChangeListener() {          
	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus) {
	                inicializaRelogio();
	            }  

	        }
	    });
		txtRepeticoes2.setOnFocusChangeListener(new OnFocusChangeListener() {          
	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus) {
	                inicializaRelogio();
	            }  

	        }
	    });
		txtRepeticoes3.setOnFocusChangeListener(new OnFocusChangeListener() {          
	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus) {
	                inicializaRelogio();
	            }  

	        }
	    });
	    */
	}

	public void inicializaRelogio() {
		chrTempo.stop();
	}
	
	private ExecucaoItemSerie processaExecucao(ExecucaoItemSerie execucao, EditText carga, EditText repeticoes, Button inicializa, boolean ultima, int numeroSerie) {
		if (execucao==null) {
			float cargaNum = Float.parseFloat(carga.getText().toString());
			long repeticoesNum = Long.parseLong(repeticoes.getText().toString());
			execucao = FabricaVo.criaExecucaoItemSerie();
			execucao.setCargaUtilizada(cargaNum);
			execucao.setQuantidadeRepeticao(repeticoesNum);
			execucao.setNumeroSerie(numeroSerie);
			execucao.setDataHoraInicio(UtilDatas.getTimestampAtual());
			execucao.setSucessoRepeticoes(true);
			inicializa.setText("Concluido");
			chrTempo.stop();
			chrTempo.setText("00:00");
			
		} else {
			execucao.setDataHoraFinalizacao(UtilDatas.getTimestampAtual());
			chrTempo.setText("00:00");
			chrTempo.setBase(SystemClock.elapsedRealtime());
			if (!ultima) {
				chrTempo.start();
			} else {
				chrTempo.start();
				this.btnConcluidoCorrente.setVisibility(View.VISIBLE);
				//lytCmdItemSerie.setVisibility(View.VISIBLE);
			}
			inicializa.setVisibility(View.GONE);
		}
		return execucao;
	}
	
	public void onClickInicializa(int i) {
		switch (i) {
			case 0:
				execucao1 = processaExecucao(execucao1,txtCargaExecucao1,txtRepeticoes1,btnInicializa1, false,1);
				break;
			case 1:
				execucao2 = processaExecucao(execucao2,txtCargaExecucao2,txtRepeticoes2,btnInicializa2, false,2);
				break;
			case 2:
				execucao3 = processaExecucao(execucao3,txtCargaExecucao3,txtRepeticoes3,btnInicializa3, true,3);
				
				break;
		}
	}
	
	public void onConcluidoCorrente() {
		chrTempo.stop();
		chrTempo.setText("00:00");
		finalizaItemSerie();
	}
	
	private void finalizaItemSerie() {
		//execucao1.setIdItemSerieRa(getItem().getIdItemSerie());
		//execucao2.setIdItemSerieRa(getItem().getIdItemSerie());
		//execucao3.setIdItemSerieRa(getItem().getIdItemSerie());
		
		getItem().addListaExecucaoItemSerie_Gera(execucao1);
		getItem().addListaExecucaoItemSerie_Gera(execucao2);
		getItem().addListaExecucaoItemSerie_Gera(execucao3);
		this.itemSerieSrv.getFiltro().setItem(getItem());
		this.itemSerieSrv.FinalizaItemSerie(getContext());
		this.retornaTela();
	}
	
	@Override
	protected void inicioJogoTela() {
		// TODO Auto-generated method stub
		super.inicioJogoTela();
	}

	public ItemSerieView(ItemSerie item) {
		super(item);
		// TODO Auto-generated constructor stub
	}

	
	protected ResourceObj getLayoutTela() {
		return new ResourceObj(R.layout.item_serie_view,"R.layout.item_serie_view");
	}

	

	
}