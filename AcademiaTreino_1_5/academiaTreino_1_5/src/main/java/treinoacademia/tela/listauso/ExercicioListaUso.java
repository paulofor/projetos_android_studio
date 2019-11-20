package treinoacademia.tela.listauso;

import java.util.List;

import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.modelo.Exercicio;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.modelo.Usuario;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.CargaPlanejadaServico;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.UsuarioServico;
import treinoacademia.tela.listauso.base.ExercicioListaUsoBase;
import treinoacademia.view.ItemSerieView;
import android.widget.Toast;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.IFragment;

public class ExercicioListaUso extends ExercicioListaUsoBase {

	//private ExercicioServico exercicioSrv = FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
	private CargaPlanejadaServico cargaSrv = FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
	private ExecucaoItemSerieServico execucaoSrv = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
	private UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
	
	
	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
		Toast.makeText(getContext().getContext(), "selecionado: " + obj.getId(), Toast.LENGTH_SHORT).show();
		ItemSerie itemFake = FabricaVo.criaItemSerie();
		itemFake.setIdItemSerie(0);
		Exercicio exercicio = (Exercicio) obj;
		//carregaExercicio(exercicio);
		carregaCarga(itemFake,exercicio);
		itemFake.setExercicio_ExecucaoDe(exercicio);
		IFragment quadroTela = new ItemSerieView(itemFake);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadroTela);
	}
	
	private void carregaExercicio(Exercicio exercicio) 	{
		execucaoSrv.getFiltro().setIdExercicio(exercicio.getId());
		Usuario usuario = usuarioSrv.getCorrente();
		execucaoSrv.getFiltro().setIdUsuario(usuario.getId());
		List<ExecucaoItemSerie> anteriores = execucaoSrv.UltimasExecucoesUsuarioExercicio(getContext());
		exercicio.setListaExecucaoItemSerie_Executado(anteriores);
	}
	
	private void carregaCarga(ItemSerie itemSerie, Exercicio exercicio) {
		cargaSrv.getFiltro().setIdExercicio(exercicio.getId());
		List<CargaPlanejada> cargas = cargaSrv.ListaCargaPorExercicio(getContext());
		itemSerie.setListaCargaPlanejada_Possui(cargas);
	}

	@Override
	public String getTituloTela() {
		return "Exercicios";
	}

	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
