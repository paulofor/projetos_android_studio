package repcom.tela.listauso;

import java.util.List;

import repcom.modelo.CategoriaProduto;
import repcom.servico.CategoriaProdutoServico;
import repcom.tela.listauso.base.CategoriaProdutoListaUsoBase;
import android.content.Context;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.IFragment;

public class CategoriaProdutoListaUso extends CategoriaProdutoListaUsoBase {

	private CategoriaProduto categoriaPai = null;
	
	public void setCategoriaPai(CategoriaProduto categoria) {
		categoriaPai = categoria;
	}

	@Override
	protected List<CategoriaProduto> getListaCorrente(Context contexto, CategoriaProdutoServico servico) {
		List<CategoriaProduto> saida = null;
		if (categoriaPai==null) {
			saida = servico.ListaNivel0(getContext());
		} else {
			if (categoriaPai.existeListaCategoriaProduto_Pai()) {
				saida = categoriaPai.getListaCategoriaProduto_Pai();
			} else {
				saida = servico.getPorPaiCategoriaProduto(contexto, categoriaPai.getId());
			}
		}
		return saida;
	}

	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
		CategoriaProduto categoria = (CategoriaProduto) obj;
		List<CategoriaProduto> filhos = getServico().getPorPaiCategoriaProduto(getContext().getContext(), obj.getId()); 
		if (filhos.size()>0) {
			CategoriaProdutoListaUso quadro = new CategoriaProdutoListaUso();
			categoria.setListaCategoriaProduto_Pai(filhos);
			quadro.setCategoriaPai(categoria);
			alteraQuadro(quadro);
		} else { 
			ProdutoListaUso quadro = new ProdutoListaUso();
			quadro.setCategoriaPai(categoria);
			alteraQuadro(quadro);
		}
		
		
	}

	@Override
	public String getTituloTela() {
		return "Categorias";
	}
	
	
	
}
