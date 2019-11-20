package treinoacademia.dao.montador;

import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.modelo.vo.FabricaVo;
import android.database.Cursor;
import br.com.digicom.dao.MontadorDaoBase;
import br.com.digicom.dao.MontadorDaoI;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.modelo.ObjetoSinc;


public class CargaPlanejadaMontador extends MontadorDaoBase implements MontadorDaoI{

	
	// Existem casos em que o tratamento do objeto sinc esta
	// no metodo do DAO porem para aa listaInterna usando DaoComposite
	// que se trata de algo mais complexo o tratamento esta 
	// nesse flag, no futuro poderemos evoluir todos para esse formato.
	private boolean sinc = false;
	public CargaPlanejadaMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public CargaPlanejadaMontador() {
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
		objeto = FabricaVo.criaCargaPlanejada();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		objeto = FabricaVo.criaCargaPlanejada();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		CargaPlanejada item = null;
		item = (CargaPlanejada) entrada;item.setIdCargaPlanejada(getInt(c,pos++));
		item.setValorCarga(getFloat(c,pos++));
		item.setDataInicio(getTimestampData(c,pos++));
		item.setDataFinal(getTimestampData(c,pos++));
		item.setAtiva(getLogic(c,pos++));
		item.setQuantidadeRepeticao(getInt(c,pos++));
		item.setOrdemRepeticao(getInt(c,pos++));
		item.setIdItemSerieRa(getInt(c,pos++));
		item.setIdUsuarioS(getInt(c,pos++));
		if (sinc) {
			((ObjetoSinc)item).setOperacaoSinc(getString(c,pos++));
		}
		return item;
	}
   	public int quantidadeCampos()  {
   		return (sinc?9+1:9);
 	}
}
