package coletapreco.dao.montador;

import coletapreco.modelo.OportunidadeDia;
import coletapreco.modelo.vo.FabricaVo;
import android.database.Cursor;
import br.com.digicom.dao.MontadorDaoBase;
import br.com.digicom.dao.MontadorDaoI;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.modelo.ObjetoSinc;


public class OportunidadeDiaMontador extends MontadorDaoBase implements MontadorDaoI{

	
	// Existem casos em que o tratamento do objeto sinc esta
	// no metodo do DAO porem para aa listaInterna usando DaoComposite
	// que se trata de algo mais complexo o tratamento esta 
	// nesse flag, no futuro poderemos evoluir todos para esse formato.
	private boolean sinc = false;
	public OportunidadeDiaMontador(boolean sinc) {
		this.sinc = sinc;
	}
	public OportunidadeDiaMontador() {
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
		objeto = FabricaVo.criaOportunidadeDia();
		return getItem(c, objeto, 0);
	}
	public DCIObjetoDominio getItem(Cursor c, int pos) {
		DCIObjetoDominio objeto = null;
		objeto = FabricaVo.criaOportunidadeDia();
		return getItem(c, objeto, pos);
	}

	public DCIObjetoDominio getItem(Cursor c, DCIObjetoDominio entrada, int pos) {
		OportunidadeDia item = null;
		item = (OportunidadeDia) entrada;item.setIdOportunidadeDia(getInt(c,pos++));
		item.setUrlProduto(getString(c,pos++));
		item.setNomeProduto(getString(c,pos++));
		item.setDataInicioPrecoAtual(getTimestampData(c,pos++));
		item.setNomeMarca(getString(c,pos++));
		item.setUrlAfiliado(getString(c,pos++));
		item.setDataUltimaPrecoAnterior(getTimestampData(c,pos++));
		item.setImagemLocal(getString(c,pos++));
		item.setUrlImagem(getString(c,pos++));
		item.setPosicaoProduto(getInt(c,pos++));
		item.setPrecoVendaAnterior(getFloat(c,pos++));
		item.setPrecoVendaAtual(getFloat(c,pos++));
		item.setPrecoBoletoAnterior(getFloat(c,pos++));
		item.setPrecoBoletoAtual(getFloat(c,pos++));
		item.setPrecoParcelaAnterior(getFloat(c,pos++));
		item.setPrecoParcelaAtual(getFloat(c,pos++));
		item.setQuantidadeParcelaAnterior(getInt(c,pos++));
		item.setQuantidadeParcelaAtual(getInt(c,pos++));
		item.setPercentualAjusteVenda(getFloat(c,pos++));
		item.setPercentualAjusteBoleto(getFloat(c,pos++));
		item.setNomeLojaVirtual(getString(c,pos++));
		item.setIdProdutoRa(getInt(c,pos++));
		item.setIdNaturezaProdutoPa(getInt(c,pos++));
		if (sinc) {
			((ObjetoSinc)item).setOperacaoSinc(getString(c,pos++));
		}
		return item;
	}
   	public int quantidadeCampos()  {
   		return (sinc?23+1:23);
 	}
}
