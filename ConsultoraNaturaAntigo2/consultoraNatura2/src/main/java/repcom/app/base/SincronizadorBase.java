
package  repcom.app.base;

import br.com.digicom.servico.ServicoLocal;
import br.com.digicom.servico.ServicoRemoto;
import br.com.digicom.network.WifiTest;
import br.com.digicom.dao.DataSource;
import br.com.digicom.log.DCLog;
import br.com.digicom.dao.DaoSincronismo;
import repcom.modelo.*;
import repcom.servico.*;
import java.util.List;
import android.content.Context;
import android.util.Log;

// extends AsyncTask<URL, Integer, Long> 
public abstract class SincronizadorBase  extends Thread{
	
	Context ctx = null;
	private String TAG = "Sincronizacao";
	
	public void setContexto(Context contexto) {
		ctx = contexto;
	}
	public void run()  {
		if (WifiTest.existe(ctx)) {
			Log.d(TAG , "Inicio Sincronizacao");
			sincronizaDispositivoUsuario(ctx);
			sincronizaUsuario(ctx); // parece que vai precisar sempre. nao faz sentido Usuario_Usuario
			principal(ctx);
			atualizaDispositivo();
			Log.d(TAG , "Final Sincronizacao");
		} else {
			Log.d(TAG , "Sem Sincronizacao - Wifi Indisponivel");
		}
	}
	
	public static void atualizaDispositivo() {
		DispositivoUsuarioServico dispositivoSrv = FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
		DispositivoUsuario dispositivo = dispositivoSrv.getCorrente();
		if (dispositivo!=null && dispositivo.getMelhorPath()!=null) {
			if (dispositivo.getMelhorPath().compareTo(DataSource.getValorPath())!=0) {
				dispositivo.setMelhorPath(DataSource.getValorPath());
				dispositivoSrv.alteraParaSincronizacao(dispositivo);
			}
		}
	}
	
	protected abstract void principal(Context contexto);

	@Deprecated
	protected void sincronizaCliente(Context contexto) {
		ServicoRemoto<Cliente> servicoRemoto = (ServicoRemoto<Cliente>)FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Cliente> servicoLocal = (ServicoLocal<Cliente>) FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Cliente> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Cliente: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Cliente> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Cliente: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
			// Dependente
			ServicoRemoto<ClienteInteresseCategoria> ClienteInteresseCategoriaRemotoDep = (ServicoRemoto<ClienteInteresseCategoria>)FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ClienteInteresseCategoria> ClienteInteresseCategoriaLocalDep = (ServicoLocal<ClienteInteresseCategoria>) FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<ClienteInteresseCategoria> listaClienteInteresseCategoriaDepSincAlteracao = ClienteInteresseCategoriaLocalDep.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: servicoLocal.listaSincronizadaAlteracao : " + listaClienteInteresseCategoriaDepSincAlteracao.size());
			if (listaClienteInteresseCategoriaDepSincAlteracao!=null && !listaClienteInteresseCategoriaDepSincAlteracao.isEmpty()) {
				ClienteInteresseCategoriaRemotoDep.listaSincronizadaAlteracao(listaClienteInteresseCategoriaDepSincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<ClienteInteresseCategoria> listaClienteInteresseCategoriaDep = ClienteInteresseCategoriaRemotoDep.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: servicoRemoto.listaSincronizada : " + listaClienteInteresseCategoriaDep.size());
			ClienteInteresseCategoriaLocalDep.dropCreate(contexto);
			ClienteInteresseCategoriaLocalDep.insertAll(listaClienteInteresseCategoriaDep, contexto);
			
		
			
			// Dependente
			ServicoRemoto<ContatoCliente> ContatoClienteRemotoDep = (ServicoRemoto<ContatoCliente>)FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ContatoCliente> ContatoClienteLocalDep = (ServicoLocal<ContatoCliente>) FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<ContatoCliente> listaContatoClienteDepSincAlteracao = ContatoClienteLocalDep.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: servicoLocal.listaSincronizadaAlteracao : " + listaContatoClienteDepSincAlteracao.size());
			if (listaContatoClienteDepSincAlteracao!=null && !listaContatoClienteDepSincAlteracao.isEmpty()) {
				ContatoClienteRemotoDep.listaSincronizadaAlteracao(listaContatoClienteDepSincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<ContatoCliente> listaContatoClienteDep = ContatoClienteRemotoDep.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: servicoRemoto.listaSincronizada : " + listaContatoClienteDep.size());
			ContatoClienteLocalDep.dropCreate(contexto);
			ContatoClienteLocalDep.insertAll(listaContatoClienteDep, contexto);
			
		
			
			// Dependente
			ServicoRemoto<Venda> VendaRemotoDep = (ServicoRemoto<Venda>)FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<Venda> VendaLocalDep = (ServicoLocal<Venda>) FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<Venda> listaVendaDepSincAlteracao = VendaLocalDep.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: servicoLocal.listaSincronizadaAlteracao : " + listaVendaDepSincAlteracao.size());
			if (listaVendaDepSincAlteracao!=null && !listaVendaDepSincAlteracao.isEmpty()) {
				VendaRemotoDep.listaSincronizadaAlteracao(listaVendaDepSincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<Venda> listaVendaDep = VendaRemotoDep.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: servicoRemoto.listaSincronizada : " + listaVendaDep.size());
			VendaLocalDep.dropCreate(contexto);
			VendaLocalDep.insertAll(listaVendaDep, contexto);
			
		
			
			// Dependente Nivel 2
			ServicoRemoto<ProdutoVenda> servicoRemotoDep2 = (ServicoRemoto<ProdutoVenda>)FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ProdutoVenda> servicoLocalDep2 = (ServicoLocal<ProdutoVenda>) FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<ProdutoVenda> listaDep2SincAlteracao = servicoLocalDep2.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: servicoLocal.listaSincronizadaAlteracao : " + listaDep2SincAlteracao.size());
			if (listaDep2SincAlteracao!=null && !listaDep2SincAlteracao.isEmpty()) {
				servicoRemotoDep2.listaSincronizadaAlteracao(listaDep2SincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<ProdutoVenda> listaProdutoVendaDep2 = servicoRemotoDep2.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: servicoRemoto.listaSincronizada : " + listaProdutoVendaDep2.size());
			servicoLocalDep2.dropCreate(contexto);
			servicoLocalDep2.insertAll(listaProdutoVendaDep2, contexto);
			
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaCliente_Usuario(Context contexto) {
		ServicoRemoto<Cliente> servicoRemoto = (ServicoRemoto<Cliente>)FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Cliente> servicoLocal = (ServicoLocal<Cliente>) FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<Cliente> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Cliente: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<Cliente> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Cliente: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
			// Dependente
			ServicoRemoto<ClienteInteresseCategoria> ClienteInteresseCategoriaRemotoDep = (ServicoRemoto<ClienteInteresseCategoria>)FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ClienteInteresseCategoria> ClienteInteresseCategoriaLocalDep = (ServicoLocal<ClienteInteresseCategoria>) FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
			if (listaSinc.isEmpty()) {
				List<ClienteInteresseCategoria> listaLocalClienteInteresseCategoria = ClienteInteresseCategoriaLocalDep.listaSincronizadaAlteracao(contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria>: servicoLocal : " + listaLocalClienteInteresseCategoria.size());
				if (listaLocalClienteInteresseCategoria!=null && !listaLocalClienteInteresseCategoria.isEmpty()) {
					ClienteInteresseCategoriaRemotoDep.listaSincronizadaAlteracao(listaLocalClienteInteresseCategoria,contexto);
					ClienteInteresseCategoriaLocalDep.limpaSinc(listaLocalClienteInteresseCategoria);
				}
			}
			List<ClienteInteresseCategoria> listaClienteInteresseCategoriaDep = ClienteInteresseCategoriaRemotoDep.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: servicoRemoto : " + listaClienteInteresseCategoriaDep.size());
			ClienteInteresseCategoriaLocalDep.dropCreate(contexto);
			ClienteInteresseCategoriaLocalDep.insertAll(listaClienteInteresseCategoriaDep, contexto);
			
			
			// Dependente
			ServicoRemoto<ContatoCliente> ContatoClienteRemotoDep = (ServicoRemoto<ContatoCliente>)FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ContatoCliente> ContatoClienteLocalDep = (ServicoLocal<ContatoCliente>) FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_SQLITE);
			if (listaSinc.isEmpty()) {
				List<ContatoCliente> listaLocalContatoCliente = ContatoClienteLocalDep.listaSincronizadaAlteracao(contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente>: servicoLocal : " + listaLocalContatoCliente.size());
				if (listaLocalContatoCliente!=null && !listaLocalContatoCliente.isEmpty()) {
					ContatoClienteRemotoDep.listaSincronizadaAlteracao(listaLocalContatoCliente,contexto);
					ContatoClienteLocalDep.limpaSinc(listaLocalContatoCliente);
				}
			}
			List<ContatoCliente> listaContatoClienteDep = ContatoClienteRemotoDep.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: servicoRemoto : " + listaContatoClienteDep.size());
			ContatoClienteLocalDep.dropCreate(contexto);
			ContatoClienteLocalDep.insertAll(listaContatoClienteDep, contexto);
			
			
			// Dependente
			ServicoRemoto<Venda> VendaRemotoDep = (ServicoRemoto<Venda>)FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<Venda> VendaLocalDep = (ServicoLocal<Venda>) FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
			if (listaSinc.isEmpty()) {
				List<Venda> listaLocalVenda = VendaLocalDep.listaSincronizadaAlteracao(contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda>: servicoLocal : " + listaLocalVenda.size());
				if (listaLocalVenda!=null && !listaLocalVenda.isEmpty()) {
					VendaRemotoDep.listaSincronizadaAlteracao(listaLocalVenda,contexto);
					VendaLocalDep.limpaSinc(listaLocalVenda);
				}
			}
			List<Venda> listaVendaDep = VendaRemotoDep.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: servicoRemoto : " + listaVendaDep.size());
			VendaLocalDep.dropCreate(contexto);
			VendaLocalDep.insertAll(listaVendaDep, contexto);
			
			
			// Dependente Nivel 2
			ServicoRemoto<ProdutoVenda> ProdutoVendaRemotoDep2 = (ServicoRemoto<ProdutoVenda>)FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ProdutoVenda> ProdutoVendaLocalDep2 = (ServicoLocal<ProdutoVenda>) FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
			List<ProdutoVenda> listaProdutoVendaDep2 = ProdutoVendaRemotoDep2.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: servicoRemoto : " + listaProdutoVendaDep2.size());
			ProdutoVendaLocalDep2.dropCreate(contexto);
			ProdutoVendaLocalDep2.insertAll(listaProdutoVendaDep2, contexto);
			
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaProduto(Context contexto) {
		ServicoRemoto<Produto> servicoRemoto = (ServicoRemoto<Produto>)FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Produto> servicoLocal = (ServicoLocal<Produto>) FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Produto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Produto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Produto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Produto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaProduto_Usuario(Context contexto) {
		ServicoRemoto<Produto> servicoRemoto = (ServicoRemoto<Produto>)FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Produto> servicoLocal = (ServicoLocal<Produto>) FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<Produto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Produto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<Produto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Produto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaCategoriaProduto(Context contexto) {
		ServicoRemoto<CategoriaProduto> servicoRemoto = (ServicoRemoto<CategoriaProduto>)FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaProduto> servicoLocal = (ServicoLocal<CategoriaProduto>) FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<CategoriaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<CategoriaProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaCategoriaProduto_Usuario(Context contexto) {
		ServicoRemoto<CategoriaProduto> servicoRemoto = (ServicoRemoto<CategoriaProduto>)FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaProduto> servicoLocal = (ServicoLocal<CategoriaProduto>) FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<CategoriaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<CategoriaProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaPedidoFornecedor(Context contexto) {
		ServicoRemoto<PedidoFornecedor> servicoRemoto = (ServicoRemoto<PedidoFornecedor>)FabricaServico.getInstancia().getPedidoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PedidoFornecedor> servicoLocal = (ServicoLocal<PedidoFornecedor>) FabricaServico.getInstancia().getPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<PedidoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PedidoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<PedidoFornecedor> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PedidoFornecedor: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaPedidoFornecedor_Usuario(Context contexto) {
		ServicoRemoto<PedidoFornecedor> servicoRemoto = (ServicoRemoto<PedidoFornecedor>)FabricaServico.getInstancia().getPedidoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PedidoFornecedor> servicoLocal = (ServicoLocal<PedidoFornecedor>) FabricaServico.getInstancia().getPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<PedidoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PedidoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<PedidoFornecedor> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PedidoFornecedor: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaVenda(Context contexto) {
		ServicoRemoto<Venda> servicoRemoto = (ServicoRemoto<Venda>)FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Venda> servicoLocal = (ServicoLocal<Venda>) FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Venda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Venda> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
			// Dependente
			ServicoRemoto<ProdutoVenda> ProdutoVendaRemotoDep = (ServicoRemoto<ProdutoVenda>)FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ProdutoVenda> ProdutoVendaLocalDep = (ServicoLocal<ProdutoVenda>) FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
			// Evitar que as alteracoes em itens dependentes fiquem esquecidass no mobile
			List<ProdutoVenda> listaProdutoVendaDepSincAlteracao = ProdutoVendaLocalDep.listaSincronizadaDependenteSoAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: servicoLocal.listaSincronizadaAlteracao : " + listaProdutoVendaDepSincAlteracao.size());
			if (listaProdutoVendaDepSincAlteracao!=null && !listaProdutoVendaDepSincAlteracao.isEmpty()) {
				ProdutoVendaRemotoDep.listaSincronizadaAlteracao(listaProdutoVendaDepSincAlteracao, contexto);
			}
			// Recuperacao do remote para o mobile
			List<ProdutoVenda> listaProdutoVendaDep = ProdutoVendaRemotoDep.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: servicoRemoto.listaSincronizada : " + listaProdutoVendaDep.size());
			ProdutoVendaLocalDep.dropCreate(contexto);
			ProdutoVendaLocalDep.insertAll(listaProdutoVendaDep, contexto);
			
		
			
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaVenda_Usuario(Context contexto) {
		ServicoRemoto<Venda> servicoRemoto = (ServicoRemoto<Venda>)FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Venda> servicoLocal = (ServicoLocal<Venda>) FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<Venda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<Venda> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
			// Dependente
			ServicoRemoto<ProdutoVenda> ProdutoVendaRemotoDep = (ServicoRemoto<ProdutoVenda>)FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_WSJSON);
			ServicoLocal<ProdutoVenda> ProdutoVendaLocalDep = (ServicoLocal<ProdutoVenda>) FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
			if (listaSinc.isEmpty()) {
				List<ProdutoVenda> listaLocalProdutoVenda = ProdutoVendaLocalDep.listaSincronizadaAlteracao(contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda>: servicoLocal : " + listaLocalProdutoVenda.size());
				if (listaLocalProdutoVenda!=null && !listaLocalProdutoVenda.isEmpty()) {
					ProdutoVendaRemotoDep.listaSincronizadaAlteracao(listaLocalProdutoVenda,contexto);
					ProdutoVendaLocalDep.limpaSinc(listaLocalProdutoVenda);
				}
			}
			List<ProdutoVenda> listaProdutoVendaDep = ProdutoVendaRemotoDep.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: servicoRemoto : " + listaProdutoVendaDep.size());
			ProdutoVendaLocalDep.dropCreate(contexto);
			ProdutoVendaLocalDep.insertAll(listaProdutoVendaDep, contexto);
			
			
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaContatoCliente(Context contexto) {
		ServicoRemoto<ContatoCliente> servicoRemoto = (ServicoRemoto<ContatoCliente>)FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ContatoCliente> servicoLocal = (ServicoLocal<ContatoCliente>) FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ContatoCliente> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ContatoCliente> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaContatoCliente_Usuario(Context contexto) {
		ServicoRemoto<ContatoCliente> servicoRemoto = (ServicoRemoto<ContatoCliente>)FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ContatoCliente> servicoLocal = (ServicoLocal<ContatoCliente>) FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ContatoCliente> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ContatoCliente> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaLinhaProduto(Context contexto) {
		ServicoRemoto<LinhaProduto> servicoRemoto = (ServicoRemoto<LinhaProduto>)FabricaServico.getInstancia().getLinhaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LinhaProduto> servicoLocal = (ServicoLocal<LinhaProduto>) FabricaServico.getInstancia().getLinhaProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<LinhaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LinhaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<LinhaProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LinhaProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaLinhaProduto_Usuario(Context contexto) {
		ServicoRemoto<LinhaProduto> servicoRemoto = (ServicoRemoto<LinhaProduto>)FabricaServico.getInstancia().getLinhaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LinhaProduto> servicoLocal = (ServicoLocal<LinhaProduto>) FabricaServico.getInstancia().getLinhaProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<LinhaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LinhaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<LinhaProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LinhaProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaProdutoPedidoFornecedor(Context contexto) {
		ServicoRemoto<ProdutoPedidoFornecedor> servicoRemoto = (ServicoRemoto<ProdutoPedidoFornecedor>)FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ProdutoPedidoFornecedor> servicoLocal = (ServicoLocal<ProdutoPedidoFornecedor>) FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ProdutoPedidoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoPedidoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ProdutoPedidoFornecedor> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoPedidoFornecedor: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaProdutoPedidoFornecedor_Usuario(Context contexto) {
		ServicoRemoto<ProdutoPedidoFornecedor> servicoRemoto = (ServicoRemoto<ProdutoPedidoFornecedor>)FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ProdutoPedidoFornecedor> servicoLocal = (ServicoLocal<ProdutoPedidoFornecedor>) FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ProdutoPedidoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoPedidoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ProdutoPedidoFornecedor> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoPedidoFornecedor: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaProdutoVenda(Context contexto) {
		ServicoRemoto<ProdutoVenda> servicoRemoto = (ServicoRemoto<ProdutoVenda>)FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ProdutoVenda> servicoLocal = (ServicoLocal<ProdutoVenda>) FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ProdutoVenda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ProdutoVenda> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaProdutoVenda_Usuario(Context contexto) {
		ServicoRemoto<ProdutoVenda> servicoRemoto = (ServicoRemoto<ProdutoVenda>)FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ProdutoVenda> servicoLocal = (ServicoLocal<ProdutoVenda>) FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ProdutoVenda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ProdutoVenda> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaPagamentoFornecedor(Context contexto) {
		ServicoRemoto<PagamentoFornecedor> servicoRemoto = (ServicoRemoto<PagamentoFornecedor>)FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PagamentoFornecedor> servicoLocal = (ServicoLocal<PagamentoFornecedor>) FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<PagamentoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PagamentoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<PagamentoFornecedor> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PagamentoFornecedor: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaPagamentoFornecedor_Usuario(Context contexto) {
		ServicoRemoto<PagamentoFornecedor> servicoRemoto = (ServicoRemoto<PagamentoFornecedor>)FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PagamentoFornecedor> servicoLocal = (ServicoLocal<PagamentoFornecedor>) FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<PagamentoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PagamentoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<PagamentoFornecedor> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PagamentoFornecedor: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaParcelaVenda(Context contexto) {
		ServicoRemoto<ParcelaVenda> servicoRemoto = (ServicoRemoto<ParcelaVenda>)FabricaServico.getInstancia().getParcelaVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ParcelaVenda> servicoLocal = (ServicoLocal<ParcelaVenda>) FabricaServico.getInstancia().getParcelaVendaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ParcelaVenda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ParcelaVenda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ParcelaVenda> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ParcelaVenda: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaParcelaVenda_Usuario(Context contexto) {
		ServicoRemoto<ParcelaVenda> servicoRemoto = (ServicoRemoto<ParcelaVenda>)FabricaServico.getInstancia().getParcelaVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ParcelaVenda> servicoLocal = (ServicoLocal<ParcelaVenda>) FabricaServico.getInstancia().getParcelaVendaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ParcelaVenda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ParcelaVenda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ParcelaVenda> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ParcelaVenda: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaClienteInteresseCategoria(Context contexto) {
		ServicoRemoto<ClienteInteresseCategoria> servicoRemoto = (ServicoRemoto<ClienteInteresseCategoria>)FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ClienteInteresseCategoria> servicoLocal = (ServicoLocal<ClienteInteresseCategoria>) FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ClienteInteresseCategoria> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ClienteInteresseCategoria> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaClienteInteresseCategoria_Usuario(Context contexto) {
		ServicoRemoto<ClienteInteresseCategoria> servicoRemoto = (ServicoRemoto<ClienteInteresseCategoria>)FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ClienteInteresseCategoria> servicoLocal = (ServicoLocal<ClienteInteresseCategoria>) FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ClienteInteresseCategoria> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ClienteInteresseCategoria> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaEstoque(Context contexto) {
		ServicoRemoto<Estoque> servicoRemoto = (ServicoRemoto<Estoque>)FabricaServico.getInstancia().getEstoqueServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Estoque> servicoLocal = (ServicoLocal<Estoque>) FabricaServico.getInstancia().getEstoqueServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Estoque> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Estoque: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Estoque> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Estoque: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaEstoque_Usuario(Context contexto) {
		ServicoRemoto<Estoque> servicoRemoto = (ServicoRemoto<Estoque>)FabricaServico.getInstancia().getEstoqueServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Estoque> servicoLocal = (ServicoLocal<Estoque>) FabricaServico.getInstancia().getEstoqueServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<Estoque> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Estoque: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<Estoque> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Estoque: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	private void sincronizaUsuario(Context contexto) {
		ServicoRemoto<Usuario> servicoRemoto = (ServicoRemoto<Usuario>)FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Usuario> servicoLocal = (ServicoLocal<Usuario>) FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Usuario> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Usuario: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Usuario> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Usuario: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaPrecoProduto(Context contexto) {
		ServicoRemoto<PrecoProduto> servicoRemoto = (ServicoRemoto<PrecoProduto>)FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PrecoProduto> servicoLocal = (ServicoLocal<PrecoProduto>) FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<PrecoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PrecoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<PrecoProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PrecoProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaPrecoProduto_Usuario(Context contexto) {
		ServicoRemoto<PrecoProduto> servicoRemoto = (ServicoRemoto<PrecoProduto>)FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PrecoProduto> servicoLocal = (ServicoLocal<PrecoProduto>) FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<PrecoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PrecoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<PrecoProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PrecoProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaCategoriaProdutoProduto(Context contexto) {
		ServicoRemoto<CategoriaProdutoProduto> servicoRemoto = (ServicoRemoto<CategoriaProdutoProduto>)FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaProdutoProduto> servicoLocal = (ServicoLocal<CategoriaProdutoProduto>) FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<CategoriaProdutoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProdutoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<CategoriaProdutoProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProdutoProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaCategoriaProdutoProduto_Usuario(Context contexto) {
		ServicoRemoto<CategoriaProdutoProduto> servicoRemoto = (ServicoRemoto<CategoriaProdutoProduto>)FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaProdutoProduto> servicoLocal = (ServicoLocal<CategoriaProdutoProduto>) FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<CategoriaProdutoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProdutoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<CategoriaProdutoProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProdutoProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaMesAno(Context contexto) {
		ServicoRemoto<MesAno> servicoRemoto = (ServicoRemoto<MesAno>)FabricaServico.getInstancia().getMesAnoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<MesAno> servicoLocal = (ServicoLocal<MesAno>) FabricaServico.getInstancia().getMesAnoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<MesAno> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"MesAno: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<MesAno> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"MesAno: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaMesAno_Usuario(Context contexto) {
		ServicoRemoto<MesAno> servicoRemoto = (ServicoRemoto<MesAno>)FabricaServico.getInstancia().getMesAnoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<MesAno> servicoLocal = (ServicoLocal<MesAno>) FabricaServico.getInstancia().getMesAnoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<MesAno> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"MesAno: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<MesAno> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"MesAno: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	private void sincronizaDispositivoUsuario(Context contexto) {
		ServicoRemoto<DispositivoUsuario> servicoRemoto = (ServicoRemoto<DispositivoUsuario>)FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<DispositivoUsuario> servicoLocal = (ServicoLocal<DispositivoUsuario>) FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<DispositivoUsuario> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DispositivoUsuario: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<DispositivoUsuario> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DispositivoUsuario: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}


	// ------------- Sincronismo Vers?o 2 ---------------------
	
	
	protected void sincronizaCliente(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Cliente> servicoRemoto = (ServicoRemoto<Cliente>)FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Cliente> servicoLocal = (ServicoLocal<Cliente>) FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Cliente> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Cliente: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Cliente: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Produto> servicoRemoto = (ServicoRemoto<Produto>)FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Produto> servicoLocal = (ServicoLocal<Produto>) FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Produto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Produto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Produto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaCategoriaProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<CategoriaProduto> servicoRemoto = (ServicoRemoto<CategoriaProduto>)FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaProduto> servicoLocal = (ServicoLocal<CategoriaProduto>) FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<CategoriaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaPedidoFornecedor(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<PedidoFornecedor> servicoRemoto = (ServicoRemoto<PedidoFornecedor>)FabricaServico.getInstancia().getPedidoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PedidoFornecedor> servicoLocal = (ServicoLocal<PedidoFornecedor>) FabricaServico.getInstancia().getPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<PedidoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PedidoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PedidoFornecedor: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaVenda(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Venda> servicoRemoto = (ServicoRemoto<Venda>)FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Venda> servicoLocal = (ServicoLocal<Venda>) FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Venda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Venda: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaContatoCliente(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ContatoCliente> servicoRemoto = (ServicoRemoto<ContatoCliente>)FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ContatoCliente> servicoLocal = (ServicoLocal<ContatoCliente>) FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ContatoCliente> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ContatoCliente: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaLinhaProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<LinhaProduto> servicoRemoto = (ServicoRemoto<LinhaProduto>)FabricaServico.getInstancia().getLinhaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LinhaProduto> servicoLocal = (ServicoLocal<LinhaProduto>) FabricaServico.getInstancia().getLinhaProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<LinhaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LinhaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LinhaProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaProdutoPedidoFornecedor(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ProdutoPedidoFornecedor> servicoRemoto = (ServicoRemoto<ProdutoPedidoFornecedor>)FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ProdutoPedidoFornecedor> servicoLocal = (ServicoLocal<ProdutoPedidoFornecedor>) FabricaServico.getInstancia().getProdutoPedidoFornecedorServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ProdutoPedidoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoPedidoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoPedidoFornecedor: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaProdutoVenda(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ProdutoVenda> servicoRemoto = (ServicoRemoto<ProdutoVenda>)FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ProdutoVenda> servicoLocal = (ServicoLocal<ProdutoVenda>) FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ProdutoVenda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ProdutoVenda: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaPagamentoFornecedor(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<PagamentoFornecedor> servicoRemoto = (ServicoRemoto<PagamentoFornecedor>)FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PagamentoFornecedor> servicoLocal = (ServicoLocal<PagamentoFornecedor>) FabricaServico.getInstancia().getPagamentoFornecedorServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<PagamentoFornecedor> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PagamentoFornecedor: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PagamentoFornecedor: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaParcelaVenda(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ParcelaVenda> servicoRemoto = (ServicoRemoto<ParcelaVenda>)FabricaServico.getInstancia().getParcelaVendaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ParcelaVenda> servicoLocal = (ServicoLocal<ParcelaVenda>) FabricaServico.getInstancia().getParcelaVendaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ParcelaVenda> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ParcelaVenda: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ParcelaVenda: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaClienteInteresseCategoria(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ClienteInteresseCategoria> servicoRemoto = (ServicoRemoto<ClienteInteresseCategoria>)FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ClienteInteresseCategoria> servicoLocal = (ServicoLocal<ClienteInteresseCategoria>) FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ClienteInteresseCategoria> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ClienteInteresseCategoria: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaEstoque(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Estoque> servicoRemoto = (ServicoRemoto<Estoque>)FabricaServico.getInstancia().getEstoqueServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Estoque> servicoLocal = (ServicoLocal<Estoque>) FabricaServico.getInstancia().getEstoqueServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Estoque> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Estoque: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Estoque: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaUsuario(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Usuario> servicoRemoto = (ServicoRemoto<Usuario>)FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Usuario> servicoLocal = (ServicoLocal<Usuario>) FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Usuario> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Usuario: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Usuario: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaPrecoProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<PrecoProduto> servicoRemoto = (ServicoRemoto<PrecoProduto>)FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PrecoProduto> servicoLocal = (ServicoLocal<PrecoProduto>) FabricaServico.getInstancia().getPrecoProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<PrecoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PrecoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PrecoProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaCategoriaProdutoProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<CategoriaProdutoProduto> servicoRemoto = (ServicoRemoto<CategoriaProdutoProduto>)FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaProdutoProduto> servicoLocal = (ServicoLocal<CategoriaProdutoProduto>) FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<CategoriaProdutoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProdutoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaProdutoProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaMesAno(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<MesAno> servicoRemoto = (ServicoRemoto<MesAno>)FabricaServico.getInstancia().getMesAnoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<MesAno> servicoLocal = (ServicoLocal<MesAno>) FabricaServico.getInstancia().getMesAnoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<MesAno> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"MesAno: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"MesAno: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaDispositivoUsuario(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<DispositivoUsuario> servicoRemoto = (ServicoRemoto<DispositivoUsuario>)FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<DispositivoUsuario> servicoLocal = (ServicoLocal<DispositivoUsuario>) FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<DispositivoUsuario> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DispositivoUsuario: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"DispositivoUsuario: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	// ------------------------------------------------------------
}