package treinoacademia.tela.trata.impl;

import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.app.R;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.ItemSerieServico;
import treinoacademia.servico.SerieTreinoServico;
import treinoacademia.tela.quadro.FabricaFragment;
import treinoacademia.tela.trata.base.SerieTreinoQuadroTrataBase;
import treinoacademia.view.adapter.listaedicao.ItemSerieListEdicaoAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.BundleFragment;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IQuadroListaEdicao;
import br.com.digicom.quadro.ResultadoValidacao;

public class SerieTreinoQuadroTrata extends SerieTreinoQuadroTrataBase implements IQuadroListaEdicao {

	protected boolean itemInicializado = false;
	
	private ListView lstItemSerie = null;
	private ImageButton btnNovoExercicio = null;
	private ItemSerie itemSerie = null;
	private CheckBox chkAtivo = null;
	private DatePicker dteInicial = null;

	//ArrayAdapter<ItemSerie> adaptador = null;
	ItemSerieListEdicaoAdapter adaptador = null;
	SerieTreinoServico serieTreinoSrv = FabricaServico.getInstancia().getSerieTreinoServico(FabricaServico.TIPO_SQLITE);
	ItemSerieServico itemSerieSrv = FabricaServico.getInstancia().getItemSerieServico(FabricaServico.TIPO_SQLITE);	

	
	
	@Override
	public void onAlteraQuadro() {
		this.getItem().setListaItemSerie_PossuiByDao(null);
		List<ItemSerie> lista = getItem().getListaItemSerie_Possui();
		adaptador = new ItemSerieListEdicaoAdapter(lista,this,getContext().getContext());
		this.lstItemSerie.setAdapter(adaptador);
	}


	@Override
	public void onResume() {
		super.onResume();
		adaptador.notifyDataSetChanged();
		permiteExclusao();
	}


	@Override
	protected void inicializaItensTela() {
			
		this.lstItemSerie = (ListView) getTela().findViewById(R.id.lstItemSerie);
		this.btnNovoExercicio = (ImageButton) getTela().findViewById(R.id.btnNovoExercicio);
		this.chkAtivo = (CheckBox) getTela().findViewById(R.id.chkAtivo);
		permiteExclusao();
		//this.dteInicial = (DatePicker) getTela().findViewById(R.id.dteInicial);
		//this.setHasOptionsMenu(true);
	}

	
	 
	 
	 
	@Override
	protected void carregaElementosTela() {
		if (!itemInicializado) {
			SerieTreino serie = (SerieTreino) getItem();
			if (serie.getId()!=0) {
				// Carregar dados apenas se for uma edicao��o.
				serieTreinoSrv.getFiltro().setItem(serie);
				serie = serieTreinoSrv.CarregaItemSerie(getDCContext());
				this.setItem(serie);
				itemInicializado = true;
			}
			
			
		}
		adaptador = new ItemSerieListEdicaoAdapter(getItem().getListaItemSerie_Possui(),this,getContext().getContext());
		this.lstItemSerie.setAdapter(adaptador);
		
	}
	

	private void permiteExclusao() {
		if (((SerieTreino)this.getItem()).getDataPrimeiraExecucao()!=null) {
			View btn = (View) this.getTela().findViewById(R.id.btnExcluiSerie);
			btn.setVisibility(View.INVISIBLE);
		}
	}



	@Override
	protected void inicializaListeners() {
		super.setOnOk(getTela()); // Para funcionar os elementos da tela.
		btnNovoExercicio.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onClickBtnNovoExercicio();
			}
		});
	}
	
	
	private void onClickBtnNovoExercicio() {
		
		itemSerieSrv.getFiltro().setPrincipalInicializacao((SerieTreino)getItem());
		ItemSerie itemSerie = itemSerieSrv.inicializaItemTela(getContext());
		
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_SERIE_TREINO, this.getItem());
		bundle.setObjeto(Constantes.CHAVE_ALTERACAO, false);
		bundle.setObjeto(Constantes.CHAVE_ITEM_SERIE, itemSerie);
		IFragment quadro = FabricaFragment.getInstancia().getItemSerieQuadroTrata(bundle);
		TrocaQuadro.getInstancia().criaDialog(quadro, this);

	}
	
	
	@Override
	protected void transfereTelaParaVo(SerieTreino vo) {
		vo.setAtiva(this.chkAtivo.isChecked());
	}


	@Override
	protected void transfereVoParaTela(SerieTreino vo) {
		this.chkAtivo.setChecked(vo.getAtiva());
	}


	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		this.itemSerieSrv.getFiltro().setItem((ItemSerie)obj);
		obj = this.itemSerieSrv.CarregaCompleto(getContext());
		
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_SERIE_TREINO, this.getItem());
		bundle.setObjeto(Constantes.CHAVE_ALTERACAO, true);
		bundle.setObjeto(Constantes.CHAVE_ITEM_SERIE, obj);
		IFragment quadro = FabricaFragment.getInstancia().getItemSerieQuadroTrata(bundle);
		TrocaQuadro.getInstancia().criaDialog(quadro, this);
	}
	
	
	@Override
	protected ResultadoValidacao validaCamposTela() {
		ResultadoValidacao result = new ResultadoValidacao();
		result.setProsseguir(true);
		return result;
	}


	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getTituloTela() {
		return "Alteracao de Serie";
	}


	
	
	
	
}
