package repcom.app;

import repcom.app.base.SincronizadorBase;
import android.content.Context;

public class Sincronizador extends SincronizadorBase{

	public void principal(Context contexto) {
		
		
		this.sincronizaCliente(contexto, true); // Cliente e Contato deveria estar dentro de cliente.
		
		this.sincronizaClienteInteresseCategoria(contexto, true);
		this.sincronizaContatoCliente(contexto, true);
		
		// comentado para ganhar performance
		this.sincronizaCategoriaProduto(contexto, true);
		this.sincronizaProduto(contexto, true);
		this.sincronizaCategoriaProdutoProduto(contexto, true);
		
		this.sincronizaVenda(contexto, true);
		this.sincronizaProdutoVenda(contexto, true);
		
	}
	
	
	
	
}