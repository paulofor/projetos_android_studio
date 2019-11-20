
package  br.com.lojadigicom.repcom.data.provider;

import br.com.lojadigicom.repcom.data.helper.AplicacaoDbHelper;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import java.util.List;

public abstract class AplicacaoProvider extends ContentProvider {

	private static final UriMatcher sUriMatcher = buildUriMatcher();
	
	private ClienteProvider clienteProvider = criaClienteProvider();
	protected abstract ClienteProvider criaClienteProvider();
		
	private ProdutoProvider produtoProvider = criaProdutoProvider();
	protected abstract ProdutoProvider criaProdutoProvider();
		
	private CategoriaProdutoProvider categoriaProdutoProvider = criaCategoriaProdutoProvider();
	protected abstract CategoriaProdutoProvider criaCategoriaProdutoProvider();
		
	private PedidoFornecedorProvider pedidoFornecedorProvider = criaPedidoFornecedorProvider();
	protected abstract PedidoFornecedorProvider criaPedidoFornecedorProvider();
		
	private VendaProvider vendaProvider = criaVendaProvider();
	protected abstract VendaProvider criaVendaProvider();
		
	private ContatoClienteProvider contatoClienteProvider = criaContatoClienteProvider();
	protected abstract ContatoClienteProvider criaContatoClienteProvider();
		
	private LinhaProdutoProvider linhaProdutoProvider = criaLinhaProdutoProvider();
	protected abstract LinhaProdutoProvider criaLinhaProdutoProvider();
		
	private ProdutoPedidoFornecedorProvider produtoPedidoFornecedorProvider = criaProdutoPedidoFornecedorProvider();
	protected abstract ProdutoPedidoFornecedorProvider criaProdutoPedidoFornecedorProvider();
		
	private ProdutoVendaProvider produtoVendaProvider = criaProdutoVendaProvider();
	protected abstract ProdutoVendaProvider criaProdutoVendaProvider();
		
	private PagamentoFornecedorProvider pagamentoFornecedorProvider = criaPagamentoFornecedorProvider();
	protected abstract PagamentoFornecedorProvider criaPagamentoFornecedorProvider();
		
	private ParcelaVendaProvider parcelaVendaProvider = criaParcelaVendaProvider();
	protected abstract ParcelaVendaProvider criaParcelaVendaProvider();
		
	private ClienteInteresseCategoriaProvider clienteInteresseCategoriaProvider = criaClienteInteresseCategoriaProvider();
	protected abstract ClienteInteresseCategoriaProvider criaClienteInteresseCategoriaProvider();
		
	private EstoqueProvider estoqueProvider = criaEstoqueProvider();
	protected abstract EstoqueProvider criaEstoqueProvider();
		
	private UsuarioProvider usuarioProvider = criaUsuarioProvider();
	protected abstract UsuarioProvider criaUsuarioProvider();
		
	private PrecoProdutoProvider precoProdutoProvider = criaPrecoProdutoProvider();
	protected abstract PrecoProdutoProvider criaPrecoProdutoProvider();
		
	private CategoriaProdutoProdutoProvider categoriaProdutoProdutoProvider = criaCategoriaProdutoProdutoProvider();
	protected abstract CategoriaProdutoProdutoProvider criaCategoriaProdutoProdutoProvider();
		
	private MesAnoProvider mesAnoProvider = criaMesAnoProvider();
	protected abstract MesAnoProvider criaMesAnoProvider();
		
	private DispositivoUsuarioProvider dispositivoUsuarioProvider = criaDispositivoUsuarioProvider();
	protected abstract DispositivoUsuarioProvider criaDispositivoUsuarioProvider();
		
	private ErroExceptionProvider erroExceptionProvider = criaErroExceptionProvider();
	protected abstract ErroExceptionProvider criaErroExceptionProvider();
		

	public static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        //final String authority = AplicacaoContract.CONTENTI_AUTHORITY;
		
		
		ClienteProvider.buildUriMatcher(matcher);
		
		ProdutoProvider.buildUriMatcher(matcher);
		
		CategoriaProdutoProvider.buildUriMatcher(matcher);
		
		PedidoFornecedorProvider.buildUriMatcher(matcher);
		
		VendaProvider.buildUriMatcher(matcher);
		
		ContatoClienteProvider.buildUriMatcher(matcher);
		
		LinhaProdutoProvider.buildUriMatcher(matcher);
		
		ProdutoPedidoFornecedorProvider.buildUriMatcher(matcher);
		
		ProdutoVendaProvider.buildUriMatcher(matcher);
		
		PagamentoFornecedorProvider.buildUriMatcher(matcher);
		
		ParcelaVendaProvider.buildUriMatcher(matcher);
		
		ClienteInteresseCategoriaProvider.buildUriMatcher(matcher);
		
		EstoqueProvider.buildUriMatcher(matcher);
		
		UsuarioProvider.buildUriMatcher(matcher);
		
		PrecoProdutoProvider.buildUriMatcher(matcher);
		
		CategoriaProdutoProdutoProvider.buildUriMatcher(matcher);
		
		MesAnoProvider.buildUriMatcher(matcher);
		
		DispositivoUsuarioProvider.buildUriMatcher(matcher);
		
		ErroExceptionProvider.buildUriMatcher(matcher);
		
        //matcher.addURI(authority, WeatherContract.PATH_WEATHER, WEATHER);
        //matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
        //matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*/#", WEATHER_WITH_LOCATION_AND_DATE);
        //matcher.addURI(authority, WeatherContract.PATH_LOCATION, LOCATION);
        return matcher;
    }


	private AplicacaoDbHelper mOpenHelper;

	@Override
	public boolean onCreate() {
		mOpenHelper = new AplicacaoDbHelper(getContext());
		
		clienteProvider.setAplicacaoDbHelper(mOpenHelper);
		clienteProvider.setContentProvider(this);
		
		produtoProvider.setAplicacaoDbHelper(mOpenHelper);
		produtoProvider.setContentProvider(this);
		
		categoriaProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		categoriaProdutoProvider.setContentProvider(this);
		
		pedidoFornecedorProvider.setAplicacaoDbHelper(mOpenHelper);
		pedidoFornecedorProvider.setContentProvider(this);
		
		vendaProvider.setAplicacaoDbHelper(mOpenHelper);
		vendaProvider.setContentProvider(this);
		
		contatoClienteProvider.setAplicacaoDbHelper(mOpenHelper);
		contatoClienteProvider.setContentProvider(this);
		
		linhaProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		linhaProdutoProvider.setContentProvider(this);
		
		produtoPedidoFornecedorProvider.setAplicacaoDbHelper(mOpenHelper);
		produtoPedidoFornecedorProvider.setContentProvider(this);
		
		produtoVendaProvider.setAplicacaoDbHelper(mOpenHelper);
		produtoVendaProvider.setContentProvider(this);
		
		pagamentoFornecedorProvider.setAplicacaoDbHelper(mOpenHelper);
		pagamentoFornecedorProvider.setContentProvider(this);
		
		parcelaVendaProvider.setAplicacaoDbHelper(mOpenHelper);
		parcelaVendaProvider.setContentProvider(this);
		
		clienteInteresseCategoriaProvider.setAplicacaoDbHelper(mOpenHelper);
		clienteInteresseCategoriaProvider.setContentProvider(this);
		
		estoqueProvider.setAplicacaoDbHelper(mOpenHelper);
		estoqueProvider.setContentProvider(this);
		
		usuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		usuarioProvider.setContentProvider(this);
		
		precoProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		precoProdutoProvider.setContentProvider(this);
		
		categoriaProdutoProdutoProvider.setAplicacaoDbHelper(mOpenHelper);
		categoriaProdutoProdutoProvider.setContentProvider(this);
		
		mesAnoProvider.setAplicacaoDbHelper(mOpenHelper);
		mesAnoProvider.setContentProvider(this);
		
		dispositivoUsuarioProvider.setAplicacaoDbHelper(mOpenHelper);
		dispositivoUsuarioProvider.setContentProvider(this);
		
		erroExceptionProvider.setAplicacaoDbHelper(mOpenHelper);
		erroExceptionProvider.setContentProvider(this);
		
	    return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		List<String> lista = uri.getPathSegments();
		Cursor retCursor = null;
		
		if ("cliente".equals(lista.get(0)))
			retCursor = clienteProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("produto".equals(lista.get(0)))
			retCursor = produtoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("categoria_produto".equals(lista.get(0)))
			retCursor = categoriaProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("pedido_fornecedor".equals(lista.get(0)))
			retCursor = pedidoFornecedorProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("venda".equals(lista.get(0)))
			retCursor = vendaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("contato_cliente".equals(lista.get(0)))
			retCursor = contatoClienteProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("linha_produto".equals(lista.get(0)))
			retCursor = linhaProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("produto_pedido_fornecedor".equals(lista.get(0)))
			retCursor = produtoPedidoFornecedorProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("produto_venda".equals(lista.get(0)))
			retCursor = produtoVendaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("pagamento_fornecedor".equals(lista.get(0)))
			retCursor = pagamentoFornecedorProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("parcela_venda".equals(lista.get(0)))
			retCursor = parcelaVendaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("cliente_interesse_categoria".equals(lista.get(0)))
			retCursor = clienteInteresseCategoriaProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("estoque".equals(lista.get(0)))
			retCursor = estoqueProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("usuario".equals(lista.get(0)))
			retCursor = usuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("preco_produto".equals(lista.get(0)))
			retCursor = precoProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("categoria_produto_produto".equals(lista.get(0)))
			retCursor = categoriaProdutoProdutoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("mesano".equals(lista.get(0)))
			retCursor = mesAnoProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			retCursor = dispositivoUsuarioProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if ("erro_exception".equals(lista.get(0)))
			retCursor = erroExceptionProvider.query(sUriMatcher, uri, projection, selection, selectionArgs, sortOrder);
		
		if (retCursor==null)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
		
		
		retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
	}

	@Override
	public String getType(Uri uri) {
		List<String> lista = uri.getPathSegments();
		final int match = sUriMatcher.match(uri);
        String tipo = null;
		
		if (tipo==null) 
			tipo = clienteProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = produtoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = categoriaProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = pedidoFornecedorProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = vendaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = contatoClienteProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = linhaProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = produtoPedidoFornecedorProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = produtoVendaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = pagamentoFornecedorProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = parcelaVendaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = clienteInteresseCategoriaProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = estoqueProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = usuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = precoProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = categoriaProdutoProdutoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = mesAnoProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = dispositivoUsuarioProvider.getType(sUriMatcher, uri);
		
		if (tipo==null) 
			tipo = erroExceptionProvider.getType(sUriMatcher, uri);
		
		if (tipo==null)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
        return tipo;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		List<String> lista = uri.getPathSegments();
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri = null;
		
		if ("cliente".equals(lista.get(0)))
			returnUri = clienteProvider.insert(uri, values);
		
		if ("produto".equals(lista.get(0)))
			returnUri = produtoProvider.insert(uri, values);
		
		if ("categoria_produto".equals(lista.get(0)))
			returnUri = categoriaProdutoProvider.insert(uri, values);
		
		if ("pedido_fornecedor".equals(lista.get(0)))
			returnUri = pedidoFornecedorProvider.insert(uri, values);
		
		if ("venda".equals(lista.get(0)))
			returnUri = vendaProvider.insert(uri, values);
		
		if ("contato_cliente".equals(lista.get(0)))
			returnUri = contatoClienteProvider.insert(uri, values);
		
		if ("linha_produto".equals(lista.get(0)))
			returnUri = linhaProdutoProvider.insert(uri, values);
		
		if ("produto_pedido_fornecedor".equals(lista.get(0)))
			returnUri = produtoPedidoFornecedorProvider.insert(uri, values);
		
		if ("produto_venda".equals(lista.get(0)))
			returnUri = produtoVendaProvider.insert(uri, values);
		
		if ("pagamento_fornecedor".equals(lista.get(0)))
			returnUri = pagamentoFornecedorProvider.insert(uri, values);
		
		if ("parcela_venda".equals(lista.get(0)))
			returnUri = parcelaVendaProvider.insert(uri, values);
		
		if ("cliente_interesse_categoria".equals(lista.get(0)))
			returnUri = clienteInteresseCategoriaProvider.insert(uri, values);
		
		if ("estoque".equals(lista.get(0)))
			returnUri = estoqueProvider.insert(uri, values);
		
		if ("usuario".equals(lista.get(0)))
			returnUri = usuarioProvider.insert(uri, values);
		
		if ("preco_produto".equals(lista.get(0)))
			returnUri = precoProdutoProvider.insert(uri, values);
		
		if ("categoria_produto_produto".equals(lista.get(0)))
			returnUri = categoriaProdutoProdutoProvider.insert(uri, values);
		
		if ("mesano".equals(lista.get(0)))
			returnUri = mesAnoProvider.insert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			returnUri = dispositivoUsuarioProvider.insert(uri, values);
		
		if ("erro_exception".equals(lista.get(0)))
			returnUri = erroExceptionProvider.insert(uri, values);
		
		if (returnUri==null)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
		// Como essa notifica??o funciona ?
		getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		List<String> lista = uri.getPathSegments();
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted = 0;
        boolean matchOk = false;
		
		if ("cliente".equals(lista.get(0))) 
			matchOk = clienteProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = clienteProvider.getLinhas();
		
		if ("produto".equals(lista.get(0))) 
			matchOk = produtoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = produtoProvider.getLinhas();
		
		if ("categoria_produto".equals(lista.get(0))) 
			matchOk = categoriaProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = categoriaProdutoProvider.getLinhas();
		
		if ("pedido_fornecedor".equals(lista.get(0))) 
			matchOk = pedidoFornecedorProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = pedidoFornecedorProvider.getLinhas();
		
		if ("venda".equals(lista.get(0))) 
			matchOk = vendaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = vendaProvider.getLinhas();
		
		if ("contato_cliente".equals(lista.get(0))) 
			matchOk = contatoClienteProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = contatoClienteProvider.getLinhas();
		
		if ("linha_produto".equals(lista.get(0))) 
			matchOk = linhaProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = linhaProdutoProvider.getLinhas();
		
		if ("produto_pedido_fornecedor".equals(lista.get(0))) 
			matchOk = produtoPedidoFornecedorProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = produtoPedidoFornecedorProvider.getLinhas();
		
		if ("produto_venda".equals(lista.get(0))) 
			matchOk = produtoVendaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = produtoVendaProvider.getLinhas();
		
		if ("pagamento_fornecedor".equals(lista.get(0))) 
			matchOk = pagamentoFornecedorProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = pagamentoFornecedorProvider.getLinhas();
		
		if ("parcela_venda".equals(lista.get(0))) 
			matchOk = parcelaVendaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = parcelaVendaProvider.getLinhas();
		
		if ("cliente_interesse_categoria".equals(lista.get(0))) 
			matchOk = clienteInteresseCategoriaProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = clienteInteresseCategoriaProvider.getLinhas();
		
		if ("estoque".equals(lista.get(0))) 
			matchOk = estoqueProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = estoqueProvider.getLinhas();
		
		if ("usuario".equals(lista.get(0))) 
			matchOk = usuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = usuarioProvider.getLinhas();
		
		if ("preco_produto".equals(lista.get(0))) 
			matchOk = precoProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = precoProdutoProvider.getLinhas();
		
		if ("categoria_produto_produto".equals(lista.get(0))) 
			matchOk = categoriaProdutoProdutoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = categoriaProdutoProdutoProvider.getLinhas();
		
		if ("mesano".equals(lista.get(0))) 
			matchOk = mesAnoProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = mesAnoProvider.getLinhas();
		
		if ("dispositivo_usuario".equals(lista.get(0))) 
			matchOk = dispositivoUsuarioProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = dispositivoUsuarioProvider.getLinhas();
		
		if ("erro_exception".equals(lista.get(0))) 
			matchOk = erroExceptionProvider.delete(sUriMatcher, uri, selection, selectionArgs);
			if (matchOk) rowsDeleted = erroExceptionProvider.getLinhas();
		
		if (!matchOk)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
		// Rever
		if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		List<String> lista = uri.getPathSegments();
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated = 0;
        boolean matchOk = false;
		
		if ("cliente".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = clienteProvider.update(uri, values);
			} else {
				matchOk = clienteProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = clienteProvider.getLinhas();
		}
		
		if ("produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = produtoProvider.update(uri, values);
			} else {
				matchOk = produtoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = produtoProvider.getLinhas();
		}
		
		if ("categoria_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = categoriaProdutoProvider.update(uri, values);
			} else {
				matchOk = categoriaProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = categoriaProdutoProvider.getLinhas();
		}
		
		if ("pedido_fornecedor".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = pedidoFornecedorProvider.update(uri, values);
			} else {
				matchOk = pedidoFornecedorProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = pedidoFornecedorProvider.getLinhas();
		}
		
		if ("venda".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = vendaProvider.update(uri, values);
			} else {
				matchOk = vendaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = vendaProvider.getLinhas();
		}
		
		if ("contato_cliente".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = contatoClienteProvider.update(uri, values);
			} else {
				matchOk = contatoClienteProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = contatoClienteProvider.getLinhas();
		}
		
		if ("linha_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = linhaProdutoProvider.update(uri, values);
			} else {
				matchOk = linhaProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = linhaProdutoProvider.getLinhas();
		}
		
		if ("produto_pedido_fornecedor".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = produtoPedidoFornecedorProvider.update(uri, values);
			} else {
				matchOk = produtoPedidoFornecedorProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = produtoPedidoFornecedorProvider.getLinhas();
		}
		
		if ("produto_venda".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = produtoVendaProvider.update(uri, values);
			} else {
				matchOk = produtoVendaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = produtoVendaProvider.getLinhas();
		}
		
		if ("pagamento_fornecedor".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = pagamentoFornecedorProvider.update(uri, values);
			} else {
				matchOk = pagamentoFornecedorProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = pagamentoFornecedorProvider.getLinhas();
		}
		
		if ("parcela_venda".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = parcelaVendaProvider.update(uri, values);
			} else {
				matchOk = parcelaVendaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = parcelaVendaProvider.getLinhas();
		}
		
		if ("cliente_interesse_categoria".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = clienteInteresseCategoriaProvider.update(uri, values);
			} else {
				matchOk = clienteInteresseCategoriaProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = clienteInteresseCategoriaProvider.getLinhas();
		}
		
		if ("estoque".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = estoqueProvider.update(uri, values);
			} else {
				matchOk = estoqueProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = estoqueProvider.getLinhas();
		}
		
		if ("usuario".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = usuarioProvider.update(uri, values);
			} else {
				matchOk = usuarioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = usuarioProvider.getLinhas();
		}
		
		if ("preco_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = precoProdutoProvider.update(uri, values);
			} else {
				matchOk = precoProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = precoProdutoProvider.getLinhas();
		}
		
		if ("categoria_produto_produto".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = categoriaProdutoProdutoProvider.update(uri, values);
			} else {
				matchOk = categoriaProdutoProdutoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = categoriaProdutoProdutoProvider.getLinhas();
		}
		
		if ("mesano".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = mesAnoProvider.update(uri, values);
			} else {
				matchOk = mesAnoProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = mesAnoProvider.getLinhas();
		}
		
		if ("dispositivo_usuario".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = dispositivoUsuarioProvider.update(uri, values);
			} else {
				matchOk = dispositivoUsuarioProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = dispositivoUsuarioProvider.getLinhas();
		}
		
		if ("erro_exception".equals(lista.get(0))) { 
			if (selection==null) {
				matchOk = erroExceptionProvider.update(uri, values);
			} else {
				matchOk = erroExceptionProvider.update(uri, values, selection, selectionArgs);
			}
			if (matchOk) rowsUpdated = erroExceptionProvider.getLinhas();
		}
		
		if (!matchOk)
			 throw new UnsupportedOperationException("Uri desconhecida: " + uri);
		// Rever
		if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
	}
	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		List<String> lista = uri.getPathSegments();
		String path = uri.getPath();
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		final int match = sUriMatcher.match(uri);
		int rowsUpdated = 0;
		boolean matchOk = false;
		if ("cliente".equals(lista.get(0)))
			rowsUpdated =  clienteProvider.bulkInsert(uri, values);
		
		if ("produto".equals(lista.get(0)))
			rowsUpdated =  produtoProvider.bulkInsert(uri, values);
		
		if ("categoria_produto".equals(lista.get(0)))
			rowsUpdated =  categoriaProdutoProvider.bulkInsert(uri, values);
		
		if ("pedido_fornecedor".equals(lista.get(0)))
			rowsUpdated =  pedidoFornecedorProvider.bulkInsert(uri, values);
		
		if ("venda".equals(lista.get(0)))
			rowsUpdated =  vendaProvider.bulkInsert(uri, values);
		
		if ("contato_cliente".equals(lista.get(0)))
			rowsUpdated =  contatoClienteProvider.bulkInsert(uri, values);
		
		if ("linha_produto".equals(lista.get(0)))
			rowsUpdated =  linhaProdutoProvider.bulkInsert(uri, values);
		
		if ("produto_pedido_fornecedor".equals(lista.get(0)))
			rowsUpdated =  produtoPedidoFornecedorProvider.bulkInsert(uri, values);
		
		if ("produto_venda".equals(lista.get(0)))
			rowsUpdated =  produtoVendaProvider.bulkInsert(uri, values);
		
		if ("pagamento_fornecedor".equals(lista.get(0)))
			rowsUpdated =  pagamentoFornecedorProvider.bulkInsert(uri, values);
		
		if ("parcela_venda".equals(lista.get(0)))
			rowsUpdated =  parcelaVendaProvider.bulkInsert(uri, values);
		
		if ("cliente_interesse_categoria".equals(lista.get(0)))
			rowsUpdated =  clienteInteresseCategoriaProvider.bulkInsert(uri, values);
		
		if ("estoque".equals(lista.get(0)))
			rowsUpdated =  estoqueProvider.bulkInsert(uri, values);
		
		if ("usuario".equals(lista.get(0)))
			rowsUpdated =  usuarioProvider.bulkInsert(uri, values);
		
		if ("preco_produto".equals(lista.get(0)))
			rowsUpdated =  precoProdutoProvider.bulkInsert(uri, values);
		
		if ("categoria_produto_produto".equals(lista.get(0)))
			rowsUpdated =  categoriaProdutoProdutoProvider.bulkInsert(uri, values);
		
		if ("mesano".equals(lista.get(0)))
			rowsUpdated =  mesAnoProvider.bulkInsert(uri, values);
		
		if ("dispositivo_usuario".equals(lista.get(0)))
			rowsUpdated =  dispositivoUsuarioProvider.bulkInsert(uri, values);
		
		if ("erro_exception".equals(lista.get(0)))
			rowsUpdated =  erroExceptionProvider.bulkInsert(uri, values);
		
		return rowsUpdated;
	}
	
}