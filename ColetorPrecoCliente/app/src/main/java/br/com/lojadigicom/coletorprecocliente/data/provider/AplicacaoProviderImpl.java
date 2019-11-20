package br.com.lojadigicom.coletorprecocliente.data.provider;

import br.com.lojadigicom.coletorprecocliente.aplicacao.ContractApp;
import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;

/**
 * Created by Paulo on 11/11/2015.
 */
public class AplicacaoProviderImpl extends AplicacaoProvider {



    @Override
    protected PalavraChavePesquisaProvider criaPalavraChavePesquisaProvider() {
        return new PalavraChavePesquisaCosulta();
    }

    @Override
    protected AppProdutoProvider criaAppProdutoProvider() {
        return new AppProdutoConsulta();
    }

    @Override
    protected UsuarioPesquisaProvider criaUsuarioPesquisaProvider() {
        return new UsuarioPesquisaConsulta();
    }

    @Override
    protected ProdutoClienteProvider criaProdutoClienteProvider() {
        return new ProdutoClienteConsulta();
    }

    @Override
    protected InteresseProdutoProvider criaInteresseProdutoProvider() {
        return new InteresseProdutoConsulta();
    }

    @Override
    protected NaturezaProdutoProvider criaNaturezaProdutoProvider() {
        return new NaturezaProdutoConsulta();
    }

    @Override
    protected OportunidadeDiaProvider criaOportunidadeDiaProvider() {
        return new OportunidadeDiaConsulta();
    }

    @Override
    protected PrecoDiarioProvider criaPrecoDiarioProvider() {
        return new PrecoDiarioConsulta();
    }

    @Override
    protected UsuarioProvider criaUsuarioProvider() {
        return new UsuarioConsulta();
    }

    @Override
    protected DispositivoUsuarioProvider criaDispositivoUsuarioProvider() {
        return new DispositivoUsuarioConsulta();
    }

    @Override
    public boolean onCreate() {

        return super.onCreate();
    }

   }
