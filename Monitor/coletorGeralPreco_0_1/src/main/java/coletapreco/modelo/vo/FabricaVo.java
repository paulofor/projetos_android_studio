package coletapreco.modelo.vo;

import android.content.Context;
import org.json.JSONObject;
import org.json.JSONException;
import coletapreco.modelo.*;
import coletapreco.modelo.derivada.impl.*;

public class FabricaVo {

	public static Usuario criaUsuario() {
		return new UsuarioVo();
	}
	public static Usuario criaUsuario(JSONObject json) throws JSONException {
		return new UsuarioVo(json);
	}
	public static DispositivoUsuario criaDispositivoUsuario() {
		return new DispositivoUsuarioVo();
	}
	public static DispositivoUsuario criaDispositivoUsuario(JSONObject json) throws JSONException {
		return new DispositivoUsuarioVo(json);
	}
	public static Produto criaProduto() {
		return new ProdutoVo();
	}
	public static Produto criaProduto(JSONObject json) throws JSONException {
		return new ProdutoVo(json);
	}
	public static PrecoProduto criaPrecoProduto() {
		return new PrecoProdutoVo();
	}
	public static PrecoProduto criaPrecoProduto(JSONObject json) throws JSONException {
		return new PrecoProdutoVo(json);
	}
	public static ModeloProduto criaModeloProduto() {
		return new ModeloProdutoVo();
	}
	public static ModeloProduto criaModeloProduto(JSONObject json) throws JSONException {
		return new ModeloProdutoVo(json);
	}
	public static ModeloProdutoProduto criaModeloProdutoProduto() {
		return new ModeloProdutoProdutoVo();
	}
	public static ModeloProdutoProduto criaModeloProdutoProduto(JSONObject json) throws JSONException {
		return new ModeloProdutoProdutoVo(json);
	}
	public static LojaVirtual criaLojaVirtual() {
		return new LojaVirtualVo();
	}
	public static LojaVirtual criaLojaVirtual(JSONObject json) throws JSONException {
		return new LojaVirtualVo(json);
	}
	public static Marca criaMarca() {
		return new MarcaVo();
	}
	public static Marca criaMarca(JSONObject json) throws JSONException {
		return new MarcaVo(json);
	}
	public static CategoriaLoja criaCategoriaLoja() {
		return new CategoriaLojaVo();
	}
	public static CategoriaLoja criaCategoriaLoja(JSONObject json) throws JSONException {
		return new CategoriaLojaVo(json);
	}
	public static CategoriaLojaProduto criaCategoriaLojaProduto() {
		return new CategoriaLojaProdutoVo();
	}
	public static CategoriaLojaProduto criaCategoriaLojaProduto(JSONObject json) throws JSONException {
		return new CategoriaLojaProdutoVo(json);
	}
	public static NaturezaProduto criaNaturezaProduto() {
		return new NaturezaProdutoVo();
	}
	public static NaturezaProduto criaNaturezaProduto(JSONObject json) throws JSONException {
		return new NaturezaProdutoVo(json);
	}
	public static OportunidadeDia criaOportunidadeDia() {
		return new OportunidadeDiaVo();
	}
	public static OportunidadeDia criaOportunidadeDia(JSONObject json) throws JSONException {
		return new OportunidadeDiaVo(json);
	}
	public static LojaNatureza criaLojaNatureza() {
		return new LojaNaturezaVo();
	}
	public static LojaNatureza criaLojaNatureza(JSONObject json) throws JSONException {
		return new LojaNaturezaVo(json);
	}
	public static Palavra criaPalavra() {
		return new PalavraVo();
	}
	public static Palavra criaPalavra(JSONObject json) throws JSONException {
		return new PalavraVo(json);
	}
	public static PalavraProduto criaPalavraProduto() {
		return new PalavraProdutoVo();
	}
	public static PalavraProduto criaPalavraProduto(JSONObject json) throws JSONException {
		return new PalavraProdutoVo(json);
	}
	public static UsuarioPesquisa criaUsuarioPesquisa() {
		return new UsuarioPesquisaVo();
	}
	public static UsuarioPesquisa criaUsuarioPesquisa(JSONObject json) throws JSONException {
		return new UsuarioPesquisaVo(json);
	}
}

