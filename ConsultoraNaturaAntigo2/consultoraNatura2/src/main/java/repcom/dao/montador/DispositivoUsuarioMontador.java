package repcom.dao.montador;

import repcom.modelo.DispositivoUsuario;
import repcom.modelo.vo.FabricaVo;
import android.database.Cursor;
import br.com.digicom.dao.MontadorDaoBase;
import br.com.digicom.dao.MontadorDaoI;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.modelo.ObjetoSinc;


public class DispositivoUsuarioMontador extends MontadorDaoBase implements MontadorDaoI{

	
	// Existem casos em que o tratamento do objeto sinc esta
	// no metodo do DAO porem para aa listaInterna usando DaoComposite
	// que se trata de algo mais complexo o tratamento esta 
	// nesse flag, no futuro poderemos evoluir todos para esse formato.
	private boolean sinc = false;
	public DispositivoUsuarioMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public DispositivoUsuarioMontador() {
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
		objeto = FabricaVo.criaDispositivoUsuario();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		objeto = FabricaVo.criaDispositivoUsuario();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		DispositivoUsuario item = null;
		item = (DispositivoUsuario) entrada;item.setIdDispositivoUsuario(getInt(c,pos++));
		item.setNumeroCelular(getString(c,pos++));
		item.setCodigoDispositivo(getString(c,pos++));
		item.setTipoAcesso(getString(c,pos++));
		item.setMelhorPath(getString(c,pos++));
		item.setIdUsuarioRa(getInt(c,pos++));
		if (sinc) {
			((ObjetoSinc)item).setOperacaoSinc(getString(c,pos++));
		}
		return item;
	}
   	public int quantidadeCampos()  {
   		return (sinc?6+1:6);
 	}
}
