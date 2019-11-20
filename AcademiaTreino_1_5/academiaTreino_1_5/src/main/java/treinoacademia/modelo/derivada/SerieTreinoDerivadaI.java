package treinoacademia.modelo.derivada;

import java.sql.Timestamp;
import java.util.List;
import br.com.digicom.modelo.*;

public interface SerieTreinoDerivadaI { 
 	
 	
	public void setDataPrimeiraExecucao(Timestamp valor);
	public Timestamp getDataPrimeiraExecucao();
	
	public void setTempoMedio(Timestamp valor);
	public Timestamp getTempoMedio();
	  
	public String getTituloTela();	
}