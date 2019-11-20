
package  br.com.lojadigicom.capitalexterno.data.contract;

//import android.content.ContentResolver;
import android.app.Application;
import android.net.Uri;
//import android.provider.BaseColumns;

public abstract class AplicacaoContract {

	//public static final String CONTENT_AUTHORITY = "br.com.lojadigicom.capitalexterno";
	//public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
	
	private static String codigoAplicacaoSinc = null;
	private static AplicacaoContract contract = null;
	protected Application application = null;
	
	// Pode no futuro usar o NomePacote ?
	public abstract String getContentAuthority();

	public String getNomePacoteApp() {
		return application.getPackageName();
	}

	public static void setCodigoAplicacaoSinc(String valor) {
		codigoAplicacaoSinc = valor;
	}
	public static String getCodigoAplicacaoSinc() { 
		return codigoAplicacaoSinc;
	}


	public Uri getBaseContentUri() {
		return Uri.parse("content://" + getContentAuthority());
	}
	
	public static String getIdAplicacao() {
		return contract.getContentAuthority();
	}
	
	public static void setAplicacaoContract(AplicacaoContract contract){
		AplicacaoContract.contract = contract;
	
		LinhaProdutoContract.setAplicacaoContract(contract);
	
		ProdutoContract.setAplicacaoContract(contract);
	
		ItemCustoProdutoContract.setAplicacaoContract(contract);
	
		CustoMensalContract.setAplicacaoContract(contract);
	
		PrecoVendaProdutoContract.setAplicacaoContract(contract);
	
		CenarioContract.setAplicacaoContract(contract);
	
		PrevisaoVendaContract.setAplicacaoContract(contract);
	
		MesAnoContract.setAplicacaoContract(contract);
	
		NegocioContract.setAplicacaoContract(contract);
	
		UsuarioContract.setAplicacaoContract(contract);
	
		DispositivoUsuarioContract.setAplicacaoContract(contract);
	
		ComparacaoConcorrenteContract.setAplicacaoContract(contract);
	
		ValorAgregadoContract.setAplicacaoContract(contract);
	
		CaracteristicaMercadoContract.setAplicacaoContract(contract);
	
	}
	
}