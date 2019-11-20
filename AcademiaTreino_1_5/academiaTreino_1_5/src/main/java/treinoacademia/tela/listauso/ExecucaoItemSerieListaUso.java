package treinoacademia.tela.listauso;

import java.util.List;

import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.tela.listauso.base.ExecucaoItemSerieListaUsoBase;
import android.content.Context;
import br.com.digicom.modelo.DCIObjetoDominio;

public class ExecucaoItemSerieListaUso extends ExecucaoItemSerieListaUsoBase {

	private DiaTreino diaTreino = null;
	public void setDiaTreino(DCIObjetoDominio obj) {
		diaTreino = (DiaTreino) obj;
	}
	@Override
	protected List<ExecucaoItemSerie> getListaCorrente(Context contexto, ExecucaoItemSerieServico servico) {
		ExecucaoItemSerieServico srv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		srv.getFiltro().setIdDia(diaTreino.getId());
		List<ExecucaoItemSerie> lista = srv.CarregaCompletoPorDia(getContext());
		return lista;
	}
	@Override
	public String getTituloTela() {
		return "Execucoes";
	}
	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}
	
	
	// ONDE VAI SER A MONTAGEM DA LISTA ????
	
	
}
