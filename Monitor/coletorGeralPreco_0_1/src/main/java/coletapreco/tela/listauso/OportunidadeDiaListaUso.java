package coletapreco.tela.listauso;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import coletapreco.modelo.NaturezaProduto;
import coletapreco.modelo.OportunidadeDia;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.servico.OportunidadeDiaServico;
import coletapreco.tela.listauso.base.OportunidadeDiaListaUsoBase;

public class OportunidadeDiaListaUso extends OportunidadeDiaListaUsoBase {

	private NaturezaProduto naturezaProduto = null;

	@Override
	protected List<OportunidadeDia> getListaCorrente(Context contexto, OportunidadeDiaServico servico) {
		NaturezaProduto natureza = FabricaVo.criaNaturezaProduto();
		natureza.setIdNaturezaProduto(naturezaProduto.getId());
		servico.getFiltro().setNatureza(natureza);
		return servico.ListaPorNatureza(this.getContext());
	}

	@Override
	public String getTituloTela() {
		return "Oportunidades de Produtos";
	}

	public void setNaturezaProduto(NaturezaProduto naturezaProduto) {
		this.naturezaProduto = naturezaProduto;
	}

}
