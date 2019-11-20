package br.com.lojadigicom.treinoacademia.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.treinoacademia.modelo.*;

public interface GrupoMuscularAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public Exercicio getCorrenteExercicio_ReferenteA();
		public void addListaExercicio_ReferenteA(Exercicio item);
		//public List<Exercicio> getListaExercicio_ReferenteA();
		public List<Exercicio> getListaExercicio_ReferenteAOriginal();
		//public List<Exercicio> getListaExercicio_ReferenteA(int qtde);
		//public void setListaExercicio_ReferenteA(List<Exercicio> lista); 
		//public void setListaExercicio_ReferenteAByDao(List<Exercicio> lista); 
		//public void criaVaziaListaExercicio_ReferenteA();
		public boolean existeListaExercicio_ReferenteA();
 		
	
	// UmPraUm
	
}
