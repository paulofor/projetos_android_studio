package repcom.modelo.vo;

import android.content.Context;
import org.json.JSONObject;
import org.json.JSONException;
import repcom.modelo.*;
import repcom.modelo.derivada.impl.*;

public class FabricaVo {

	public static Cliente criaCliente() {
		return new ClienteVo();
	}
	public static Cliente criaCliente(JSONObject json) throws JSONException {
		return new ClienteVo(json);
	}
	public static Produto criaProduto() {
		return new ProdutoVo();
	}
	public static Produto criaProduto(JSONObject json) throws JSONException {
		return new ProdutoVo(json);
	}
	public static CategoriaProduto criaCategoriaProduto() {
		return new CategoriaProdutoVo();
	}
	public static CategoriaProduto criaCategoriaProduto(JSONObject json) throws JSONException {
		return new CategoriaProdutoVo(json);
	}
	public static PedidoFornecedor criaPedidoFornecedor() {
		return new PedidoFornecedorVo();
	}
	public static PedidoFornecedor criaPedidoFornecedor(JSONObject json) throws JSONException {
		return new PedidoFornecedorVo(json);
	}
	public static Venda criaVenda() {
		return new VendaVo();
	}
	public static Venda criaVenda(JSONObject json) throws JSONException {
		return new VendaVo(json);
	}
	public static ContatoCliente criaContatoCliente() {
		return new ContatoClienteVo();
	}
	public static ContatoCliente criaContatoCliente(JSONObject json) throws JSONException {
		return new ContatoClienteVo(json);
	}
	public static LinhaProduto criaLinhaProduto() {
		return new LinhaProdutoVo();
	}
	public static LinhaProduto criaLinhaProduto(JSONObject json) throws JSONException {
		return new LinhaProdutoVo(json);
	}
	public static ProdutoPedidoFornecedor criaProdutoPedidoFornecedor() {
		return new ProdutoPedidoFornecedorVo();
	}
	public static ProdutoPedidoFornecedor criaProdutoPedidoFornecedor(JSONObject json) throws JSONException {
		return new ProdutoPedidoFornecedorVo(json);
	}
	public static ProdutoVenda criaProdutoVenda() {
		return new ProdutoVendaVo();
	}
	public static ProdutoVenda criaProdutoVenda(JSONObject json) throws JSONException {
		return new ProdutoVendaVo(json);
	}
	public static PagamentoFornecedor criaPagamentoFornecedor() {
		return new PagamentoFornecedorVo();
	}
	public static PagamentoFornecedor criaPagamentoFornecedor(JSONObject json) throws JSONException {
		return new PagamentoFornecedorVo(json);
	}
	public static ParcelaVenda criaParcelaVenda() {
		return new ParcelaVendaVo();
	}
	public static ParcelaVenda criaParcelaVenda(JSONObject json) throws JSONException {
		return new ParcelaVendaVo(json);
	}
	public static ClienteInteresseCategoria criaClienteInteresseCategoria() {
		return new ClienteInteresseCategoriaVo();
	}
	public static ClienteInteresseCategoria criaClienteInteresseCategoria(JSONObject json) throws JSONException {
		return new ClienteInteresseCategoriaVo(json);
	}
	public static Estoque criaEstoque() {
		return new EstoqueVo();
	}
	public static Estoque criaEstoque(JSONObject json) throws JSONException {
		return new EstoqueVo(json);
	}
	public static Usuario criaUsuario() {
		return new UsuarioVo();
	}
	public static Usuario criaUsuario(JSONObject json) throws JSONException {
		return new UsuarioVo(json);
	}
	public static PrecoProduto criaPrecoProduto() {
		return new PrecoProdutoVo();
	}
	public static PrecoProduto criaPrecoProduto(JSONObject json) throws JSONException {
		return new PrecoProdutoVo(json);
	}
	public static CategoriaProdutoProduto criaCategoriaProdutoProduto() {
		return new CategoriaProdutoProdutoVo();
	}
	public static CategoriaProdutoProduto criaCategoriaProdutoProduto(JSONObject json) throws JSONException {
		return new CategoriaProdutoProdutoVo(json);
	}
	public static MesAno criaMesAno() {
		return new MesAnoVo();
	}
	public static MesAno criaMesAno(JSONObject json) throws JSONException {
		return new MesAnoVo(json);
	}
	public static DispositivoUsuario criaDispositivoUsuario() {
		return new DispositivoUsuarioVo();
	}
	public static DispositivoUsuario criaDispositivoUsuario(JSONObject json) throws JSONException {
		return new DispositivoUsuarioVo(json);
	}
}

