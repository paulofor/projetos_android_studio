package br.com.digicom.quadro;

import android.view.View;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.modelo.DCIObjetoDominio;

public interface IFragmentEdicao extends IFragment{

	public void setItem(DCIObjetoDominio item);
	public void setInsercao();
	public void setAlteracao();
	

	// Validar se os objetos que precisam estar no quadro ali estao.
	public boolean validaDadosQuadro();
}
