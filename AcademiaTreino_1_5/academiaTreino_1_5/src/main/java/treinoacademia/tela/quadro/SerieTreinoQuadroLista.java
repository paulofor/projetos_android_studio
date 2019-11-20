
package  treinoacademia.tela.quadro;

import java.util.ArrayList;
import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.tela.quadro.base.SerieTreinoQuadroListaBase;
import treinoacademia.tela.trata.impl.SerieTreinoQuadroTrata;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.quadro.IFragmentEdicao;
import br.com.digicom.util.UtilDatas;

public class SerieTreinoQuadroLista extends  SerieTreinoQuadroListaBase{

	private Button btnCriaSerieTreino = null;
	@Override
	protected void inicializaItensTela() {
		btnCriaSerieTreino = (Button) getTela().findViewById(R.id.btnCriaSerieTreino);
	}

	@Override
	protected void inicializaListeners() {
		btnCriaSerieTreino.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaCriaSerieItem();
			}
		});
	}
	public void chamaCriaSerieItem() {
		SerieTreinoQuadroTrata quadro = new SerieTreinoQuadroTrata();
		quadro.setItem(criaNova());
		quadro.setInsercao();
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}
	
	
	
	@Override
	protected ResourceObj getLayoutTela() {
		return new ResourceObj(R.layout.lista_serie_treino,"R.layout.lista_serie_treino");
	}

	@Override
	protected SerieTreino criaNova() {
		SerieTreino nova = FabricaVo.criaSerieTreino();
		//nova.setDataInicial(UtilDatas.getTimestampAtual());
		List<ItemSerie> lista = new ArrayList<ItemSerie>();
		nova.setListaItemSerie_Possui(lista);
		return nova;
	}

	@Override
	protected IFragmentEdicao criaQuadroTrata() {
		return new SerieTreinoQuadroTrata();
	}

	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}

	


	

}