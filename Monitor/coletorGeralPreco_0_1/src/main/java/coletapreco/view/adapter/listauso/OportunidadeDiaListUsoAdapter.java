package  coletapreco.view.adapter.listauso;

import java.util.List;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.util.UtilImagem;
import coletapreco.app.R;
import coletapreco.modelo.OportunidadeDia;
import coletapreco.view.adapter.listauso.base.OportunidadeDiaListUsoAdapterBase;


public class OportunidadeDiaListUsoAdapter extends OportunidadeDiaListUsoAdapterBase {
	
	public OportunidadeDiaListUsoAdapter(List<OportunidadeDia> lista, DigicomContexto context) {
		super(lista, context);
	}

	@Override
	protected void montaItemLista(int posicao, OportunidadeDia item, View saida) {
		TextView txtNomeProduto = (TextView) saida.findViewById(R.id.txtNomeProduto);
		txtNomeProduto.setText(item.getNomeProduto());
		TextView txtPrecoAtual = (TextView) saida.findViewById(R.id.txtPrecoAtual);
		txtPrecoAtual.setText(item.getPrecoVendaAtualTela()+ "(" + item.getDataInicioPrecoAtualDDMMAAAA() + ")");
		TextView txtPrecoAnterior = (TextView) saida.findViewById(R.id.txtPrecoAnterior);
		txtPrecoAnterior.setText(item.getPrecoVendaAnteriorTela() + "(" + item.getDataUltimaPrecoAnteriorDDMMAAAA() + ")");
		TextView txtPercentualAjuste = (TextView) saida.findViewById(R.id.txtPercentualAjuste);
		txtPercentualAjuste.setText("Redução de : " + item.getPercentualAjusteVendaTela()  + "%");
		TextView txtLojaVirtual = (TextView) saida.findViewById(R.id.txtLojaVirtual);
		txtLojaVirtual.setText(item.getNomeLojaVirtual());
		
		String urlImagem = item.getUrlImagem();
		if (urlImagem!=null) {
			ImageView imgProduto = (ImageView) saida.findViewById(R.id.imgProduto);
			Bitmap imagem = UtilImagem.getInstancia().getBitmapFromUrl(urlImagem);
			imgProduto.setImageBitmap(imagem);
		}
	}
	
	
}
