package br.com.lojadigicom.naturarepcom.data.provider;

import br.com.lojadigicom.repcom.data.provider.AplicacaoProvider;
import br.com.lojadigicom.repcom.data.provider.CategoriaProdutoProdutoProvider;
import br.com.lojadigicom.repcom.data.provider.CategoriaProdutoProvider;
import br.com.lojadigicom.repcom.data.provider.ClienteInteresseCategoriaProvider;
import br.com.lojadigicom.repcom.data.provider.ClienteProvider;
import br.com.lojadigicom.repcom.data.provider.ContatoClienteProvider;
import br.com.lojadigicom.repcom.data.provider.DispositivoUsuarioProvider;
import br.com.lojadigicom.repcom.data.provider.ErroExceptionProvider;
import br.com.lojadigicom.repcom.data.provider.EstoqueProvider;
import br.com.lojadigicom.repcom.data.provider.LinhaProdutoProvider;
import br.com.lojadigicom.repcom.data.provider.MesAnoProvider;
import br.com.lojadigicom.repcom.data.provider.PagamentoFornecedorProvider;
import br.com.lojadigicom.repcom.data.provider.ParcelaVendaProvider;
import br.com.lojadigicom.repcom.data.provider.PedidoFornecedorProvider;
import br.com.lojadigicom.repcom.data.provider.PrecoProdutoProvider;
import br.com.lojadigicom.repcom.data.provider.ProdutoPedidoFornecedorProvider;
import br.com.lojadigicom.repcom.data.provider.ProdutoProvider;
import br.com.lojadigicom.repcom.data.provider.ProdutoVendaProvider;
import br.com.lojadigicom.repcom.data.provider.UsuarioProvider;
import br.com.lojadigicom.repcom.data.provider.VendaProvider;

/**
 * Created by Paulo on 31/03/2016.
 */
public class AplicacaoProviderImpl extends AplicacaoProvider {
    @Override
    protected ClienteProvider criaClienteProvider() {
        return new ClienteConsulta();
    }

    @Override
    protected ProdutoProvider criaProdutoProvider() {
        return new ProdutoConsulta();
    }

    @Override
    protected CategoriaProdutoProvider criaCategoriaProdutoProvider() {
        return new CategoriaProdutoConsulta();
    }

    @Override
    protected PedidoFornecedorProvider criaPedidoFornecedorProvider() {
        return new PedidoFornecedorConsulta();
    }

    @Override
    protected VendaProvider criaVendaProvider() {
        return new VendaConsulta();
    }

    @Override
    protected ContatoClienteProvider criaContatoClienteProvider() {
        return new ContatoClienteConsulta();
    }

    @Override
    protected LinhaProdutoProvider criaLinhaProdutoProvider() {
        return new LinhaProdutoConsulta();
    }

    @Override
    protected ProdutoPedidoFornecedorProvider criaProdutoPedidoFornecedorProvider() {
        return new ProdutoPedidoFornecedorConsulta();
    }

    @Override
    protected ProdutoVendaProvider criaProdutoVendaProvider() {
        return new ProdutoVendaConsulta();
    }

    @Override
    protected PagamentoFornecedorProvider criaPagamentoFornecedorProvider() {
        return new PagamentoFornecedorConsulta();
    }

    @Override
    protected ParcelaVendaProvider criaParcelaVendaProvider() {
        return new ParcelaVendaConsulta();
    }

    @Override
    protected ClienteInteresseCategoriaProvider criaClienteInteresseCategoriaProvider() {
        return new ClienteInteresseCategoriaConsulta();
    }

    @Override
    protected EstoqueProvider criaEstoqueProvider() {
        return new EstoqueConsulta();
    }

    @Override
    protected UsuarioProvider criaUsuarioProvider() {
        return new UsuarioConsulta();
    }

    @Override
    protected PrecoProdutoProvider criaPrecoProdutoProvider() {
        return new PrecoProdutoConsulta();
    }

    @Override
    protected CategoriaProdutoProdutoProvider criaCategoriaProdutoProdutoProvider() {
        return new CategoriaProdutoProdutoConsulta();
    }

    @Override
    protected MesAnoProvider criaMesAnoProvider() {
        return new MesAnoConsulta();
    }

    @Override
    protected DispositivoUsuarioProvider criaDispositivoUsuarioProvider() {
        return new DispositivoUsuarioConsulta();
    }

    @Override
    protected ErroExceptionProvider criaErroExceptionProvider() {
        return new ErroExceptionConsulta();
    }
}
