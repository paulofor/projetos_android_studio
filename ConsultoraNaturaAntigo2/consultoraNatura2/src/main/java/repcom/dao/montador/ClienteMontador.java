package repcom.dao.montador;

import repcom.modelo.Cliente;
import repcom.modelo.vo.FabricaVo;
import android.database.Cursor;
import br.com.digicom.dao.MontadorDaoBase;
import br.com.digicom.dao.MontadorDaoI;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.modelo.ObjetoSinc;


public class ClienteMontador extends MontadorDaoBase implements MontadorDaoI{

	
	// Existem casos em que o tratamento do objeto sinc esta
	// no metodo do DAO porem para aa listaInterna usando DaoComposite
	// que se trata de algo mais complexo o tratamento esta 
	// nesse flag, no futuro poderemos evoluir todos para esse formato.
	private boolean sinc = false;
	public ClienteMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public ClienteMontador() {
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
		objeto = FabricaVo.criaCliente();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		objeto = FabricaVo.criaCliente();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		Cliente item = null;
		item = (Cliente) entrada;item.setIdCliente(getInt(c,pos++));
		item.setEnderecoRua(getString(c,pos++));
		item.setEnderecoNumero(getString(c,pos++));
		item.setEnderecoComplemento(getString(c,pos++));
		item.setEnderecoCep(getString(c,pos++));
		item.setEnderecoBairro(getString(c,pos++));
		item.setEnderecoCidade(getString(c,pos++));
		item.setEnderecoUf(getString(c,pos++));
		item.setObservacoes(getString(c,pos++));
		item.setCodigoListaContato(getString(c,pos++));
		item.setNome(getString(c,pos++));
		item.setAtivo(getLogic(c,pos++));
		item.setIdUsuarioS(getInt(c,pos++));
		if (sinc) {
			((ObjetoSinc)item).setOperacaoSinc(getString(c,pos++));
		}
		return item;
	}
   	public int quantidadeCampos()  {
   		return (sinc?13+1:13);
 	}
}
