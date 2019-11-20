
package  br.com.lojadigicom.coletorprecocliente.data.contract;

//import android.content.ContentResolver;
import android.app.Application;
import android.net.Uri;
//import android.provider.BaseColumns;

public abstract class AplicacaoContract {

	//public static final String CONTENT_AUTHORITY = "br.com.lojadigicom.coletorprecocliente";
	//public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
	
	private static AplicacaoContract contract = null;
	protected Application application = null;
	
	// Pode no futuro usar o NomePacote ?
	public abstract String getContentAuthority();

	public String getNomePacoteApp() {
		return application.getPackageName();
	}

	public Uri getBaseContentUri() {
		return Uri.parse("content://" + getContentAuthority());
	}

	public static String getIdAplicacao() {
		return contract.getContentAuthority();
	}
	
	public static void setAplicacaoContract(AplicacaoContract contract){
		AplicacaoContract.contract = contract;
	
		NaturezaProdutoContract.setAplicacaoContract(contract);
	
		OportunidadeDiaContract.setAplicacaoContract(contract);
	
		PrecoDiarioContract.setAplicacaoContract(contract);
	
		UsuarioContract.setAplicacaoContract(contract);
	
		DispositivoUsuarioContract.setAplicacaoContract(contract);
	
		UsuarioPesquisaContract.setAplicacaoContract(contract);
	
		ProdutoClienteContract.setAplicacaoContract(contract);
	
		InteresseProdutoContract.setAplicacaoContract(contract);
	
		PalavraChavePesquisaContract.setAplicacaoContract(contract);
	
		AppProdutoContract.setAplicacaoContract(contract);
	
	}
	
}