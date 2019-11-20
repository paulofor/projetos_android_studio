package repcom.tela.listauso;

import java.util.List;

import repcom.app.R;
import repcom.modelo.Cliente;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.ClienteServico;
import repcom.servico.FabricaServico;
import repcom.tela.listauso.base.ClienteListaUsoBase;
import repcom.view.custom.ClienteTabs;
import android.app.ActionBar;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.modelo.DCIObjetoDominio;



public class ClienteListaUso extends ClienteListaUsoBase implements OnQueryTextListener {


	private Button btnAgenda = null;
	private SearchView pesquisaCliente = null;
	ClienteServico clienteSrv = FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
	Cliente clientePesquisa = FabricaVo.criaCliente();
	ClienteTabs detalheCliente = new ClienteTabs();

	@Override
	protected void inicializaItensTela() {
		btnAgenda = (Button) getTela().findViewById(R.id.btnAgenda);
		pesquisaCliente = (SearchView) getTela().findViewById(R.id.pesquisaCliente);
		detalheCliente.limpezaTabs(this);
	}

	@Override
	protected void inicializaListeners() {
		btnAgenda.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnAgendaOnClick();
			}
		});
		pesquisaCliente.setOnQueryTextListener(this);
	}
	
	public void btnAgendaOnClick() {
		List<Cliente> lista = getServico().SincronizaAgendaTel(getContext());
		
		Toast.makeText(getContext().getContext(), "sinconização realizada", Toast.LENGTH_SHORT);
	}
	
	@Override
	protected List<Cliente> getListaCorrente(Context contexto,ClienteServico servico) {
		
		List<Cliente> saida = servico.ListaAtivos(this.getContext());
		return saida;
		
	}
	
	@Override
	public boolean onQueryTextChange(String nome) {
		clientePesquisa.setNome(nome);
		clienteSrv.getFiltro().setItem(clientePesquisa);
		List<Cliente> listaCliente = clienteSrv.PesquisaTrechoNome(getContext());
		this.atualizaLista(listaCliente);
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String nome) {
		clientePesquisa.setNome(nome);
		clienteSrv.getFiltro().setItem(clientePesquisa);
		List<Cliente> listaCliente = clienteSrv.PesquisaTrechoNome(getContext());
		this.atualizaLista(listaCliente);
		return false;
	}

	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
		//IFragment detalheCliente = new ClienteView((Cliente)obj);
		//alteraQuadro(detalheCliente);
		
		detalheCliente.setCliente((Cliente) obj);
		alteraQuadro(detalheCliente);
	}

	@Override
	public String getTituloTela() {
		return "Clientes";
	}

	@Override
	protected ResourceObj getLayoutTela() {
		return new ResourceObj(R.layout.lista_uso_cliente,"R.layout.lista_uso_cliente");
	}
	
	
	
	
}
