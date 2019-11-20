
package treinoacademia.servico.filtro;

import java.util.List;
import treinoacademia.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ExecucaoItemSerieFiltro {

		private Long _IdDia;
		public Long getIdDia () {
			return _IdDia ;
		}
		public void setIdDia (Long value) {
			_IdDia  = value;
		}
		public Long validaIdDia() {
			
			if (_IdDia == null) {
				throw new RuntimeException("IdDia eh nulo");
			}
			
			return _IdDia ;
		}
		private Long _IdItemSerie;
		public Long getIdItemSerie () {
			return _IdItemSerie ;
		}
		public void setIdItemSerie (Long value) {
			_IdItemSerie  = value;
		}
		public Long validaIdItemSerie() {
			
			if (_IdItemSerie == null) {
				throw new RuntimeException("IdItemSerie eh nulo");
			}
			
			return _IdItemSerie ;
		}
		private Long _IdUsuario;
		public Long getIdUsuario () {
			return _IdUsuario ;
		}
		public void setIdUsuario (Long value) {
			_IdUsuario  = value;
		}
		public Long validaIdUsuario() {
			
			if (_IdUsuario == null) {
				throw new RuntimeException("IdUsuario eh nulo");
			}
			
			return _IdUsuario ;
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
		private Long _QuantidadeUltimasExecucoes;
		public Long getQuantidadeUltimasExecucoes () {
			return _QuantidadeUltimasExecucoes ;
		}
		public void setQuantidadeUltimasExecucoes (Long value) {
			_QuantidadeUltimasExecucoes  = value;
		}
		public Long validaQuantidadeUltimasExecucoes() {
			
			if (_QuantidadeUltimasExecucoes == null) {
				throw new RuntimeException("QuantidadeUltimasExecucoes eh nulo");
			}
			
			return _QuantidadeUltimasExecucoes ;
		}
		private Long _IdSerieTreino;
		public Long getIdSerieTreino () {
			return _IdSerieTreino ;
		}
		public void setIdSerieTreino (Long value) {
			_IdSerieTreino  = value;
		}
		public Long validaIdSerieTreino() {
			
			if (_IdSerieTreino == null) {
				throw new RuntimeException("IdSerieTreino eh nulo");
			}
			
			return _IdSerieTreino ;
		}


		//public Long IdDia;
		//public Long IdItemSerie;
		//public Long IdUsuario;
		//public Long IdExercicio;
		//public Long QuantidadeUltimasExecucoes;
		//public Long IdSerieTreino;

		public String getRequest() {
			String saida = "";
			if (_IdDia!=null)
				saida += "&IdDia="+_IdDia;
			if (_IdItemSerie!=null)
				saida += "&IdItemSerie="+_IdItemSerie;
			if (_IdUsuario!=null)
				saida += "&IdUsuario="+_IdUsuario;
			if (_IdExercicio!=null)
				saida += "&IdExercicio="+_IdExercicio;
			if (_QuantidadeUltimasExecucoes!=null)
				saida += "&QuantidadeUltimasExecucoes="+_QuantidadeUltimasExecucoes;
			if (_IdSerieTreino!=null)
				saida += "&IdSerieTreino="+_IdSerieTreino;
			return saida;	
		}
}