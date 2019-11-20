package br.com.lojadigicom.coletorprecocliente.data.provider;


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
        return new AppProdutoCosulta();
    }

    @Override
    protected OportunidadeInteresseClienteProvider criaOportunidadeInteresseClienteProvider() {
        return  new OportunidadeInteresseClienteConsulta();
    }

    @Override
    protected CompartilhamentoProdutoProvider criaCompartilhamentoProdutoProvider() {
        return  new CompartilhamentoProdutoConsulta();
    }

    @Override
    protected LocalizacaoProvider criaLocalizacaoProvider() {
        return  new LocalizacaoConsulta();
    }

    @Override
    protected AcaoClienteProvider criaAcaoClienteProvider() {
        return  new AcaoClienteConsulta();
    }

    @Override
    protected MensagemProvider criaMensagemProvider() {
        return new MensagemConsulta();
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
    protected PrecoDiarioClienteProvider criaPrecoDiarioClienteProvider() {
        return new PrecoDiarioClienteConsulta();
    }

    @Override
    protected UsuarioProvider criaUsuarioProvider() {
        return new UsuarioConsulta();
    }

    @Override
    protected DispositivoUsuarioProvider criaDispositivoUsuarioProvider() {
        return new DispositivoUsuarioConsulta();
    }


}
