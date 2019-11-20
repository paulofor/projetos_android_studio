
package treinoacademia.servico.filtro;

import java.util.List;
import treinoacademia.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ItemSerieFiltro {

		private SerieTreino _PrincipalInicializacao;
		public SerieTreino getPrincipalInicializacao () {
			return _PrincipalInicializacao ;
		}
		public void setPrincipalInicializacao (SerieTreino value) {
			_PrincipalInicializacao  = value;
		}
		public SerieTreino validaPrincipalInicializacao() {
			
			if (_PrincipalInicializacao == null) {
				throw new RuntimeException("PrincipalInicializacao eh nulo");
			}
			
			return _PrincipalInicializacao ;
		}
		private ItemSerie _Item;
		public ItemSerie getItem () {
			return _Item ;
		}
		public void setItem (ItemSerie value) {
			_Item  = value;
		}
		public ItemSerie validaItem() {
			
			if (_Item == null) {
				throw new RuntimeException("Item eh nulo");
			}
			
			return _Item ;
		}
		private DiaTreino _Dia;
		public DiaTreino getDia () {
			return _Dia ;
		}
		public void setDia (DiaTreino value) {
			_Dia  = value;
		}
		public DiaTreino validaDia() {
			
			if (_Dia == null) {
				throw new RuntimeException("Dia eh nulo");
			}
			
			return _Dia ;
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


		//public Long QuantidadeUltimasExecucoes;

		public String getRequest() {
			String saida = "";
			if (_QuantidadeUltimasExecucoes!=null)
				saida += "&QuantidadeUltimasExecucoes="+_QuantidadeUltimasExecucoes;
			return saida;	
		}
}