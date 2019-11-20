
package  br.com.lojadigicom.treinoacademia.data.contract;

//import android.content.ContentResolver;
import android.app.Application;
import android.net.Uri;
//import android.provider.BaseColumns;

public abstract class AplicacaoContract {

	//public static final String CONTENT_AUTHORITY = "br.com.lojadigicom.treinoacademia";
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
	
		UsuarioContract.setAplicacaoContract(contract);
	
		ExercicioContract.setAplicacaoContract(contract);
	
		SerieTreinoContract.setAplicacaoContract(contract);
	
		ItemSerieContract.setAplicacaoContract(contract);
	
		CargaPlanejadaContract.setAplicacaoContract(contract);
	
		ExecucaoItemSerieContract.setAplicacaoContract(contract);
	
		DiaTreinoContract.setAplicacaoContract(contract);
	
		DispositivoUsuarioContract.setAplicacaoContract(contract);
	
		GrupoMuscularContract.setAplicacaoContract(contract);
	
		ErroExceptionContract.setAplicacaoContract(contract);
	
	}
	
}