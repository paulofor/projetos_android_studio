
package treinoacademia.servico.filtro;

import java.util.List;
import treinoacademia.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class DiaTreinoFiltro {

		private String _DataPesquisa;
		public String getDataPesquisa () {
			return _DataPesquisa ;
		}
		public void setDataPesquisa (String value) {
			_DataPesquisa  = value;
		}
		public String validaDataPesquisa() {
			
			if (_DataPesquisa == null) {
				throw new RuntimeException("DataPesquisa eh nulo");
			}
			
			return _DataPesquisa ;
		}
		private DiaTreino _DiaFinalizado;
		public DiaTreino getDiaFinalizado () {
			return _DiaFinalizado ;
		}
		public void setDiaFinalizado (DiaTreino value) {
			_DiaFinalizado  = value;
		}
		public DiaTreino validaDiaFinalizado() {
			
			if (_DiaFinalizado == null) {
				throw new RuntimeException("DiaFinalizado eh nulo");
			}
			
			return _DiaFinalizado ;
		}
		private Long _IdExercicio;
		public Long getIdExercicio () {
			return _IdExercicio ;
		}
		public void setIdExercicio (Long value) {
			_IdExercicio  = value;
		}
		public Long validaIdExercicio() {
			
			if (_IdExercicio == null) {
				throw new RuntimeException("IdExercicio eh nulo");
			}
			
			return _IdExercicio ;
		}


		//public String DataPesquisa;
		//public Long IdExercicio;

		public String getRequest() {
			String saida = "";
			if (_DataPesquisa!=null)
				saida += "&DataPesquisa="+_DataPesquisa;
			if (_IdExercicio!=null)
				saida += "&IdExercicio="+_IdExercicio;
			return saida;	
		}
}