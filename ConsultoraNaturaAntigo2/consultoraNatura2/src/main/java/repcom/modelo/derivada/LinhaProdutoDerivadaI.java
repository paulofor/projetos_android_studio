package repcom.modelo.derivada;

import java.sql.Timestamp;
import java.util.List;

public interface LinhaProdutoDerivadaI { 
 	
 	
	public void setQtdeProdutos(long valor);
	public long getQtdeProdutos();
	
	public void setQtdeCategorias(long valor);
	public long getQtdeCategorias();
	  
	public String getTituloTela();	
}