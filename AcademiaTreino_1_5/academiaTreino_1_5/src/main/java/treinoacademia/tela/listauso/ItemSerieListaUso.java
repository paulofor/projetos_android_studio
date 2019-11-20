package treinoacademia.tela.listauso;

import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.app.R;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.servico.ItemSerieServico;
import treinoacademia.tela.listauso.base.ItemSerieListaUsoBase;
import android.content.Context;
import br.com.digicom.layout.ResourceObj;



public class ItemSerieListaUso extends ItemSerieListaUsoBase {

	
	
	private DiaTreino diaTreino = null;
	public void setDiaTreino(DiaTreino valor) {
		diaTreino = valor;
	}
	public DiaTreino getDiaTreino() {
		return diaTreino;
	}
	
	@Override
	protected List<ItemSerie> getListaCorrente(Context contexto, ItemSerieServico servico) {
		if (diaTreino==null) throw new RuntimeException("DiaTreino esta null");
		if (diaTreino.getSerieTreino_SerieDia()==null) throw new RuntimeException("SerieTreino esta null");
		servico.getFiltro().setDia(diaTreino);
		List<ItemSerie> lista = servico.ListaPorDiaComExecucao(getDCContext());
		//List<ItemSerie> lista = servico.getPorPertencenteASerieTreino(contexto, diaTreino.getSerieTreino_SerieDia().getIdSerieTreino());
		return lista;
	}
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj rec = new ResourceObj(R.layout.lista_uso_padrao_card,"R.layout.lista_uso_padrao_card");
		return rec;
	}
	@Override
	public void extraiBundle() {
		diaTreino = (DiaTreino) this.getBundle().getObjeto(Constantes.CHAVE_DIA_TREINO);
	}
	@Override
	public String getTituloTela() {
		String titulo = diaTreino.getDataDDMMAAAA();
		diaTreino.setContexto(getContext());
		return titulo + " ( " + diaTreino.getQuantidadeExecutado() + " de " + diaTreino.getQuantidadeTotal() + " )";
	}
	
	
	
	
}
