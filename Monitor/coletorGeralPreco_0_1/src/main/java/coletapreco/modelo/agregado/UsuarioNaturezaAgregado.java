package coletapreco.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import coletapreco.modelo.*;

public class UsuarioNaturezaAgregado implements UsuarioNaturezaAgregadoI
{
	/*
	private UsuarioNaturezaCarregador carregador = null;
	private UsuarioNaturezaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new UsuarioNaturezaCarregador();
		}
		return carregador;
	}
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		if (vo.Usuario_PesquisadoPor != null)
			vo.Usuario_PesquisadoPor.setConexaoCarregador(conexao);
		if (vo.NaturezaProduto_Pesquisa != null)
			vo.NaturezaProduto_Pesquisa.setConexaoCarregador(conexao);
		
	}
	*/
	private UsuarioNatureza vo;
	public UsuarioNaturezaAgregado(UsuarioNatureza item) {
		vo = item;
	}
	
	
	
	private Usuario usuarioPesquisadoPor;
	public void setUsuario_PesquisadoPor(Usuario valor)
	{	
		usuarioPesquisadoPor = valor;
	} 
	public Usuario getUsuario_PesquisadoPor() 
	{	
		//if (usuarioPesquisadoPor==null)
		//{
		//	getCarregador().CarregaItemUsuario_PesquisadoPor(vo);
		//}
		return usuarioPesquisadoPor;
	} 
	
	public void addListaUsuario_PesquisadoPor(Usuario value)
	{	
		usuarioPesquisadoPor = value;
	} 
	public Usuario getCorrenteUsuario_PesquisadoPor()
	{	
		return usuarioPesquisadoPor;
	} 
	
	
	
	private NaturezaProduto naturezaProdutoPesquisa;
	public void setNaturezaProduto_Pesquisa(NaturezaProduto valor)
	{	
		naturezaProdutoPesquisa = valor;
	} 
	public NaturezaProduto getNaturezaProduto_Pesquisa() 
	{	
		//if (naturezaProdutoPesquisa==null)
		//{
		//	getCarregador().CarregaItemNaturezaProduto_Pesquisa(vo);
		//}
		return naturezaProdutoPesquisa;
	} 
	
	public void addListaNaturezaProduto_Pesquisa(NaturezaProduto value)
	{	
		naturezaProdutoPesquisa = value;
	} 
	public NaturezaProduto getCorrenteNaturezaProduto_Pesquisa()
	{	
		return naturezaProdutoPesquisa;
	} 
	
	
 	
 	
 	
 	
 	
}
