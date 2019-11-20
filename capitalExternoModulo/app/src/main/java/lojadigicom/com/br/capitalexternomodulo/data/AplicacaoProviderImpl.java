package lojadigicom.com.br.capitalexternomodulo.data;


import br.com.lojadigicom.capitalexterno.data.provider.AplicacaoProvider;
import br.com.lojadigicom.capitalexterno.data.provider.CaracteristicaMercadoProvider;
import br.com.lojadigicom.capitalexterno.data.provider.CenarioProvider;
import br.com.lojadigicom.capitalexterno.data.provider.ComparacaoConcorrenteProvider;
import br.com.lojadigicom.capitalexterno.data.provider.CustoMensalProvider;
import br.com.lojadigicom.capitalexterno.data.provider.DispositivoUsuarioProvider;
import br.com.lojadigicom.capitalexterno.data.provider.ItemCustoProdutoProvider;
import br.com.lojadigicom.capitalexterno.data.provider.LinhaProdutoProvider;
import br.com.lojadigicom.capitalexterno.data.provider.MesAnoProvider;
import br.com.lojadigicom.capitalexterno.data.provider.NegocioProvider;
import br.com.lojadigicom.capitalexterno.data.provider.PrecoVendaProdutoProvider;
import br.com.lojadigicom.capitalexterno.data.provider.PrevisaoVendaProvider;
import br.com.lojadigicom.capitalexterno.data.provider.ProdutoProvider;
import br.com.lojadigicom.capitalexterno.data.provider.UsuarioProvider;
import br.com.lojadigicom.capitalexterno.data.provider.ValorAgregadoProvider;

/**
 * Created by Paulo on 12/12/2016.
 */

public class AplicacaoProviderImpl extends AplicacaoProvider {
    @Override
    protected LinhaProdutoProvider criaLinhaProdutoProvider() {
        return new LinhaProdutoConsulta();
    }

    @Override
    protected ProdutoProvider criaProdutoProvider() {
        return new ProdutoConsulta();
    }

    @Override
    protected ItemCustoProdutoProvider criaItemCustoProdutoProvider() {
        return new ItemCustoProdutoConsulta();
    }

    @Override
    protected CustoMensalProvider criaCustoMensalProvider() {
        return new CustoMensalConsulta();
    }

    @Override
    protected PrecoVendaProdutoProvider criaPrecoVendaProdutoProvider() {
        return new PrecoVendaProdutoConsulta();
    }

    @Override
    protected CenarioProvider criaCenarioProvider() {
        return new CenarioConsulta();
    }

    @Override
    protected PrevisaoVendaProvider criaPrevisaoVendaProvider() {
        return new PrevisaoVendaConsulta();
    }

    @Override
    protected MesAnoProvider criaMesAnoProvider() {
        return new MesAnoConsulta();
    }

    @Override
    protected NegocioProvider criaNegocioProvider() {
        return new NegocioConsulta();
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
    protected ComparacaoConcorrenteProvider criaComparacaoConcorrenteProvider() {
        return new ComparacaoConcorrenteConsulta();
    }

    @Override
    protected ValorAgregadoProvider criaValorAgregadoProvider() {
        return new ValorAgregadoConsulta();
    }

    @Override
    protected CaracteristicaMercadoProvider criaCaracteristicaMercadoProvider() {
        return new CaracteristicaMercadoConsulta();
    }
}
