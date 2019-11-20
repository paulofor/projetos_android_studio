package repcom.view.custom;

import repcom.modelo.Cliente;
import repcom.view.adapter.tab.ClienteTabAdapter;
import br.com.digicom.adapter.TabAdapter;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.quadro.QuadroTabs;

public class ClienteTabs extends QuadroTabs {
	
	private Cliente cliente = null;
	public void setCliente(Cliente valor) {
		cliente = valor;
	}
	public Cliente getCliente() {
		return cliente;
	}



	@Override
	protected TabAdapter getTabAdapter() {
		return new ClienteTabAdapter(this,this.getFragmentManager());
	}

	

	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicioJogoTela() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTituloTela() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean validaDadosQuadro() {
		if (this.cliente==null) {
			throw new RuntimeException("Cliente está com valor null");
		}
		return true;
	}
	@Override
	public void onAlteraQuadro() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResourceObj getRecurso() {
		return null;
	}

}
