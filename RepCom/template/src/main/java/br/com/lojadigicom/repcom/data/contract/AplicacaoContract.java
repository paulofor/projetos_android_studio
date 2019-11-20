
package  br.com.lojadigicom.repcom.data.contract;

//import android.content.ContentResolver;
import android.net.Uri;
//import android.provider.BaseColumns;

public abstract class AplicacaoContract {

	//public static final String CONTENT_AUTHORITY = "br.com.lojadigicom.repcom";
	//public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
	
	public abstract String getContentAuthority();

	public Uri getBaseContentUri() {
		return Uri.parse("content://" + getContentAuthority());
	}
	
	public static void setAplicacaoContract(AplicacaoContract contract){
	
		ClienteContract.setAplicacaoContract(contract);
	
		ProdutoContract.setAplicacaoContract(contract);
	
		CategoriaProdutoContract.setAplicacaoContract(contract);
	
		PedidoFornecedorContract.setAplicacaoContract(contract);
	
		VendaContract.setAplicacaoContract(contract);
	
		ContatoClienteContract.setAplicacaoContract(contract);
	
		LinhaProdutoContract.setAplicacaoContract(contract);
	
		ProdutoPedidoFornecedorContract.setAplicacaoContract(contract);
	
		ProdutoVendaContract.setAplicacaoContract(contract);
	
		PagamentoFornecedorContract.setAplicacaoContract(contract);
	
		ParcelaVendaContract.setAplicacaoContract(contract);
	
		ClienteInteresseCategoriaContract.setAplicacaoContract(contract);
	
		EstoqueContract.setAplicacaoContract(contract);
	
		UsuarioContract.setAplicacaoContract(contract);
	
		PrecoProdutoContract.setAplicacaoContract(contract);
	
		CategoriaProdutoProdutoContract.setAplicacaoContract(contract);
	
		MesAnoContract.setAplicacaoContract(contract);
	
		DispositivoUsuarioContract.setAplicacaoContract(contract);
	
		ErroExceptionContract.setAplicacaoContract(contract);
	
	}
	
}