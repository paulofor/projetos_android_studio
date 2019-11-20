package treinoacademia.modelo.derivada;

import java.sql.Timestamp;
import java.util.List;
import br.com.digicom.modelo.*;

public interface DiaTreinoDerivadaI { 
 	
 	
	public void setHoraInicio(Timestamp valor);
	public Timestamp getHoraInicio();
	
	public void setHoraFim(Timestamp valor);
	public Timestamp getHoraFim();
	
	public void setDiaSemana(String valor);
	public String getDiaSemana();
	
	public void setTempoConsumido(Timestamp valor);
	public Timestamp getTempoConsumido();
	
	public void setListaExercicio(List valor);
	public List getListaExercicio();
	
	public void setQuantidadeExecutado(long valor);
	public long getQuantidadeExecutado();
	
	public void setQuantidadeTotal(long valor);
	public long getQuantidadeTotal();
	  
	public String getTituloTela();	
}