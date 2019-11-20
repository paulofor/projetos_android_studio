
package  coletapreco.app.base;

import br.com.digicom.servico.ServicoLocal;
import br.com.digicom.servico.ServicoRemoto;
import br.com.digicom.network.WifiTest;
import br.com.digicom.dao.DataSource;
import br.com.digicom.log.DCLog;
import br.com.digicom.dao.DaoSincronismo;
import coletapreco.modelo.*;
import coletapreco.servico.*;
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
	protected void sincronizaModeloProduto(Context contexto) {
		ServicoRemoto<ModeloProduto> servicoRemoto = (ServicoRemoto<ModeloProduto>)FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ModeloProduto> servicoLocal = (ServicoLocal<ModeloProduto>) FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ModeloProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ModeloProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaModeloProduto_Usuario(Context contexto) {
		ServicoRemoto<ModeloProduto> servicoRemoto = (ServicoRemoto<ModeloProduto>)FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ModeloProduto> servicoLocal = (ServicoLocal<ModeloProduto>) FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ModeloProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ModeloProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaModeloProdutoProduto(Context contexto) {
		ServicoRemoto<ModeloProdutoProduto> servicoRemoto = (ServicoRemoto<ModeloProdutoProduto>)FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ModeloProdutoProduto> servicoLocal = (ServicoLocal<ModeloProdutoProduto>) FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<ModeloProdutoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProdutoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<ModeloProdutoProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProdutoProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaModeloProdutoProduto_Usuario(Context contexto) {
		ServicoRemoto<ModeloProdutoProduto> servicoRemoto = (ServicoRemoto<ModeloProdutoProduto>)FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ModeloProdutoProduto> servicoLocal = (ServicoLocal<ModeloProdutoProduto>) FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<ModeloProdutoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProdutoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<ModeloProdutoProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProdutoProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaLojaVirtual(Context contexto) {
		ServicoRemoto<LojaVirtual> servicoRemoto = (ServicoRemoto<LojaVirtual>)FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LojaVirtual> servicoLocal = (ServicoLocal<LojaVirtual>) FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<LojaVirtual> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaVirtual: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<LojaVirtual> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaVirtual: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaLojaVirtual_Usuario(Context contexto) {
		ServicoRemoto<LojaVirtual> servicoRemoto = (ServicoRemoto<LojaVirtual>)FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LojaVirtual> servicoLocal = (ServicoLocal<LojaVirtual>) FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<LojaVirtual> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaVirtual: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<LojaVirtual> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaVirtual: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaMarca(Context contexto) {
		ServicoRemoto<Marca> servicoRemoto = (ServicoRemoto<Marca>)FabricaServico.getInstancia().getMarcaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Marca> servicoLocal = (ServicoLocal<Marca>) FabricaServico.getInstancia().getMarcaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Marca> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Marca: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Marca> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Marca: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaMarca_Usuario(Context contexto) {
		ServicoRemoto<Marca> servicoRemoto = (ServicoRemoto<Marca>)FabricaServico.getInstancia().getMarcaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Marca> servicoLocal = (ServicoLocal<Marca>) FabricaServico.getInstancia().getMarcaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<Marca> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Marca: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<Marca> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Marca: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaCategoriaLoja(Context contexto) {
		ServicoRemoto<CategoriaLoja> servicoRemoto = (ServicoRemoto<CategoriaLoja>)FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaLoja> servicoLocal = (ServicoLocal<CategoriaLoja>) FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<CategoriaLoja> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLoja: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<CategoriaLoja> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLoja: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaCategoriaLoja_Usuario(Context contexto) {
		ServicoRemoto<CategoriaLoja> servicoRemoto = (ServicoRemoto<CategoriaLoja>)FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaLoja> servicoLocal = (ServicoLocal<CategoriaLoja>) FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<CategoriaLoja> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLoja: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<CategoriaLoja> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLoja: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaCategoriaLojaProduto(Context contexto) {
		ServicoRemoto<CategoriaLojaProduto> servicoRemoto = (ServicoRemoto<CategoriaLojaProduto>)FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaLojaProduto> servicoLocal = (ServicoLocal<CategoriaLojaProduto>) FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<CategoriaLojaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLojaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<CategoriaLojaProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLojaProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaCategoriaLojaProduto_Usuario(Context contexto) {
		ServicoRemoto<CategoriaLojaProduto> servicoRemoto = (ServicoRemoto<CategoriaLojaProduto>)FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaLojaProduto> servicoLocal = (ServicoLocal<CategoriaLojaProduto>) FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<CategoriaLojaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLojaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<CategoriaLojaProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLojaProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaNaturezaProduto(Context contexto) {
		ServicoRemoto<NaturezaProduto> servicoRemoto = (ServicoRemoto<NaturezaProduto>)FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<NaturezaProduto> servicoLocal = (ServicoLocal<NaturezaProduto>) FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<NaturezaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"NaturezaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<NaturezaProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"NaturezaProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaNaturezaProduto_Usuario(Context contexto) {
		ServicoRemoto<NaturezaProduto> servicoRemoto = (ServicoRemoto<NaturezaProduto>)FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<NaturezaProduto> servicoLocal = (ServicoLocal<NaturezaProduto>) FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<NaturezaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"NaturezaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<NaturezaProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"NaturezaProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaOportunidadeDia(Context contexto) {
		ServicoRemoto<OportunidadeDia> servicoRemoto = (ServicoRemoto<OportunidadeDia>)FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<OportunidadeDia> servicoLocal = (ServicoLocal<OportunidadeDia>) FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<OportunidadeDia> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"OportunidadeDia: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<OportunidadeDia> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"OportunidadeDia: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaOportunidadeDia_Usuario(Context contexto) {
		ServicoRemoto<OportunidadeDia> servicoRemoto = (ServicoRemoto<OportunidadeDia>)FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<OportunidadeDia> servicoLocal = (ServicoLocal<OportunidadeDia>) FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<OportunidadeDia> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"OportunidadeDia: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<OportunidadeDia> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"OportunidadeDia: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaLojaNatureza(Context contexto) {
		ServicoRemoto<LojaNatureza> servicoRemoto = (ServicoRemoto<LojaNatureza>)FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LojaNatureza> servicoLocal = (ServicoLocal<LojaNatureza>) FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<LojaNatureza> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaNatureza: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<LojaNatureza> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaNatureza: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaLojaNatureza_Usuario(Context contexto) {
		ServicoRemoto<LojaNatureza> servicoRemoto = (ServicoRemoto<LojaNatureza>)FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LojaNatureza> servicoLocal = (ServicoLocal<LojaNatureza>) FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<LojaNatureza> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaNatureza: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<LojaNatureza> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaNatureza: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaPalavra(Context contexto) {
		ServicoRemoto<Palavra> servicoRemoto = (ServicoRemoto<Palavra>)FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Palavra> servicoLocal = (ServicoLocal<Palavra>) FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<Palavra> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Palavra: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<Palavra> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Palavra: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaPalavra_Usuario(Context contexto) {
		ServicoRemoto<Palavra> servicoRemoto = (ServicoRemoto<Palavra>)FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Palavra> servicoLocal = (ServicoLocal<Palavra>) FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<Palavra> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Palavra: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<Palavra> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Palavra: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaPalavraProduto(Context contexto) {
		ServicoRemoto<PalavraProduto> servicoRemoto = (ServicoRemoto<PalavraProduto>)FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PalavraProduto> servicoLocal = (ServicoLocal<PalavraProduto>) FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<PalavraProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PalavraProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<PalavraProduto> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PalavraProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaPalavraProduto_Usuario(Context contexto) {
		ServicoRemoto<PalavraProduto> servicoRemoto = (ServicoRemoto<PalavraProduto>)FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PalavraProduto> servicoLocal = (ServicoLocal<PalavraProduto>) FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<PalavraProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PalavraProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<PalavraProduto> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PalavraProduto: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}

	@Deprecated
	protected void sincronizaUsuarioPesquisa(Context contexto) {
		ServicoRemoto<UsuarioPesquisa> servicoRemoto = (ServicoRemoto<UsuarioPesquisa>)FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<UsuarioPesquisa> servicoLocal = (ServicoLocal<UsuarioPesquisa>) FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_SQLITE);
		try {
			// Upload das mudancas
			List<UsuarioPesquisa> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"UsuarioPesquisa: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc); // coloquei por causa do dispositivo
				listaSinc = null;
			}
			// Download da lista
			List<UsuarioPesquisa> lista = servicoRemoto.listaSincronizada(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"UsuarioPesquisa: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}
	@Deprecated
	protected void sincronizaUsuarioPesquisa_Usuario(Context contexto) {
		ServicoRemoto<UsuarioPesquisa> servicoRemoto = (ServicoRemoto<UsuarioPesquisa>)FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<UsuarioPesquisa> servicoLocal = (ServicoLocal<UsuarioPesquisa>) FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_SQLITE);
		// Upload das mudancas
		try {
			List<UsuarioPesquisa> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"UsuarioPesquisa: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				listaSinc = null;
			}
			// Download da lista
			List<UsuarioPesquisa> lista = servicoRemoto.listaSincronizadaUsuario(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"UsuarioPesquisa: " + lista.size() + " << ");
			servicoLocal.dropCreate(contexto);
			servicoLocal.insertAll(lista, contexto);
			lista = null;
		
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
	}


	// ------------- Sincronismo Vers?o 2 ---------------------
	
	
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
	
	protected void sincronizaModeloProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ModeloProduto> servicoRemoto = (ServicoRemoto<ModeloProduto>)FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ModeloProduto> servicoLocal = (ServicoLocal<ModeloProduto>) FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ModeloProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaModeloProdutoProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<ModeloProdutoProduto> servicoRemoto = (ServicoRemoto<ModeloProdutoProduto>)FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<ModeloProdutoProduto> servicoLocal = (ServicoLocal<ModeloProdutoProduto>) FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<ModeloProdutoProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProdutoProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"ModeloProdutoProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaLojaVirtual(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<LojaVirtual> servicoRemoto = (ServicoRemoto<LojaVirtual>)FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LojaVirtual> servicoLocal = (ServicoLocal<LojaVirtual>) FabricaServico.getInstancia().getLojaVirtualServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<LojaVirtual> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaVirtual: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaVirtual: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaMarca(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Marca> servicoRemoto = (ServicoRemoto<Marca>)FabricaServico.getInstancia().getMarcaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Marca> servicoLocal = (ServicoLocal<Marca>) FabricaServico.getInstancia().getMarcaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Marca> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Marca: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Marca: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaCategoriaLoja(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<CategoriaLoja> servicoRemoto = (ServicoRemoto<CategoriaLoja>)FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaLoja> servicoLocal = (ServicoLocal<CategoriaLoja>) FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<CategoriaLoja> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLoja: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLoja: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaCategoriaLojaProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<CategoriaLojaProduto> servicoRemoto = (ServicoRemoto<CategoriaLojaProduto>)FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<CategoriaLojaProduto> servicoLocal = (ServicoLocal<CategoriaLojaProduto>) FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<CategoriaLojaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLojaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"CategoriaLojaProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaNaturezaProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<NaturezaProduto> servicoRemoto = (ServicoRemoto<NaturezaProduto>)FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<NaturezaProduto> servicoLocal = (ServicoLocal<NaturezaProduto>) FabricaServico.getInstancia().getNaturezaProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<NaturezaProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"NaturezaProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"NaturezaProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaOportunidadeDia(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<OportunidadeDia> servicoRemoto = (ServicoRemoto<OportunidadeDia>)FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<OportunidadeDia> servicoLocal = (ServicoLocal<OportunidadeDia>) FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<OportunidadeDia> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"OportunidadeDia: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"OportunidadeDia: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaLojaNatureza(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<LojaNatureza> servicoRemoto = (ServicoRemoto<LojaNatureza>)FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<LojaNatureza> servicoLocal = (ServicoLocal<LojaNatureza>) FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<LojaNatureza> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaNatureza: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"LojaNatureza: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaPalavra(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<Palavra> servicoRemoto = (ServicoRemoto<Palavra>)FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<Palavra> servicoLocal = (ServicoLocal<Palavra>) FabricaServico.getInstancia().getPalavraServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<Palavra> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Palavra: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"Palavra: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaPalavraProduto(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<PalavraProduto> servicoRemoto = (ServicoRemoto<PalavraProduto>)FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<PalavraProduto> servicoLocal = (ServicoLocal<PalavraProduto>) FabricaServico.getInstancia().getPalavraProdutoServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<PalavraProduto> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PalavraProduto: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"PalavraProduto: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	protected void sincronizaUsuarioPesquisa(Context contexto, boolean forcaAtualizacao) {
		ServicoRemoto<UsuarioPesquisa> servicoRemoto = (ServicoRemoto<UsuarioPesquisa>)FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_WSJSON);
		ServicoLocal<UsuarioPesquisa> servicoLocal = (ServicoLocal<UsuarioPesquisa>) FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_SQLITE);
		boolean teveMudanca = false;
		// Upload das mudancas
		try {
			List<UsuarioPesquisa> listaSinc = servicoLocal.listaSincronizadaAlteracao(contexto);
			DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"UsuarioPesquisa: " + listaSinc.size() + " >> ");
			if (listaSinc!=null && !listaSinc.isEmpty()) {
				servicoRemoto.listaSincronizadaAlteracao(listaSinc,contexto);
				servicoLocal.limpaSinc(listaSinc);
				teveMudanca = true;
			}
			// Download da lista
			if (forcaAtualizacao || teveMudanca) { 
				DaoSincronismo daoSinc = servicoLocal.getDaoSincronismo();
				int tamLista = servicoRemoto.listaSincronizadaDao(daoSinc, contexto);
				DCLog.d(DCLog.SINCRONIZACAO_SINCRONIZADOR,this,"UsuarioPesquisa: " + tamLista +  " << ");
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_SINCRONIZADOR, this, e);
		}
		System.gc();
	}
	
	// ------------------------------------------------------------
}