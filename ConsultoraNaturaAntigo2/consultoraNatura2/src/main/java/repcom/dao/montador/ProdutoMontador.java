package repcom.dao.montador;

import repcom.modelo.Produto;
import repcom.modelo.vo.FabricaVo;
import android.database.Cursor;
import br.com.digicom.dao.MontadorDaoBase;
import br.com.digicom.dao.MontadorDaoI;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.modelo.ObjetoSinc;


public class ProdutoMontador extends MontadorDaoBase implements MontadorDaoI{

	
	// Existem casos em que o tratamento do objeto sinc esta
	// no metodo do DAO porem para aa listaInterna usando DaoComposite
	// que se trata de algo mais complexo o tratamento esta 
	// nesse flag, no futuro poderemos evoluir todos para esse formato.
	private boolean sinc = false;
	public ProdutoMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public ProdutoMontador() {
		this.sinc = false;
	}
	public void desligaSinc() {
		this.sinc = false;
	}

	public DCIObjetoDominio getItemSinc(Cursor c) {
		return super.getItemSinc(c);
	}

	public boolean getItemListaInterna(Cursor c, DCIObjetoDominio objeto)
    {
    	objeto = ((MontadorDaoI)this).getItem(c);
        return true;
    }
    public boolean getItemRegistroInterno(Cursor c, DCIObjetoDominio objeto)
    {
    	objeto = ((MontadorDaoI)this).getItem(c);
        return true;
    }

	public DCIObjetoDominio getItem(Cursor c) {
		DCIObjetoDominio objeto = null;
		objeto = FabricaVo.criaProduto();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		objeto = FabricaVo.criaProduto();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		Produto item = null;
		item = (Produto) entrada;item.setIdProduto(getInt(c,pos++));
		item.setNome(getString(c,pos++));
		item.setUrl(getString(c,pos++));
		item.setImagem(getString(c,pos++));
		item.setTamanhoImagem(getInt(c,pos++));
		item.setDataInclusao(getTimestampData(c,pos++));
		item.setDataExclusao(getTimestampData(c,pos++));
		item.setIdLinhaProdutoEe(getInt(c,pos++));
		if (sinc) {
			((ObjetoSinc)item).setOperacaoSinc(getString(c,pos++));
		}
		return item;
	}
   	public int quantidadeCampos()  {
   		return (sinc?8+1:8);
 	}
}
