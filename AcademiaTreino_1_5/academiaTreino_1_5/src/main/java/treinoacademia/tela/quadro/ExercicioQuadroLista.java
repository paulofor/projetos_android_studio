
package  treinoacademia.tela.quadro;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.Exercicio;
import treinoacademia.modelo.GrupoMuscular;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.ExercicioServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.GrupoMuscularServico;
import treinoacademia.tela.listaedicao.base.ExercicioListaEdicaoBase;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import br.com.digicom.quadro.BundleFragment;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;

public class ExercicioQuadroLista extends  ExercicioListaEdicaoBase implements OnClickListener, OnItemSelectedListener{

	private Spinner spnGrupoMuscular = null;
	private GrupoMuscularServico grupoSrv = null;
	private GrupoMuscular grupoCorrente = null;
	
	
	@Override
	protected void inicializaServicos() {
		grupoSrv = FabricaServico.getInstancia().getGrupoMuscularServico(FabricaServico.TIPO_SQLITE);
	}

	@Override
	protected void inicializaItensTela() {
		List<GrupoMuscular> lista = grupoSrv.getAllTela(this.getContext().getContext());
		GrupoMuscular grupo = FabricaVo.criaGrupoMuscular();
		grupo.setNomeGrupo("Todos");
		grupo.setIdGrupoMuscular(0);
		lista.add(0, grupo);
		this.spnGrupoMuscular = this.getSpinner(R.id.spnGrupoMuscular, "R.id.spnGrupoMuscular", lista, getContext().getContext());
		this.spnGrupoMuscular.setOnItemSelectedListener(this);
		this.spnGrupoMuscular.setBackgroundResource(android.R.drawable.spinner_background);
		//this.spnGrupoMuscular.setBackgroundColor(color.dcPalette1);
	}

	@Override
	protected IFragmentEdicao criaQuadroTrata(BundleFragment bundle) {
		IFragment quadro = FabricaFragment.getInstancia().getExercicioQuadroTrata(bundle);
		return (IFragmentEdicao) quadro;
	}

	@Override
	public String getTituloTela() {
		return "Exercicios";
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		View v = view;
		this.grupoCorrente = (GrupoMuscular) this.spnGrupoMuscular.getSelectedItem();
		this.preencheLista();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<Exercicio> getListaCorrente(Context contexto, ExercicioServico servico) {
		if (this.grupoCorrente==null || this.grupoCorrente.getId()==0) {
			return super.getListaCorrente(contexto, servico);
		} else {
			List<Exercicio> lista = servico.getPorParaGrupoMuscular(this.grupoCorrente.getId());
			return lista;
		}
		
	}

	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}

	

	

	

	

}