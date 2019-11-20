package br.com.lojadigicom.capitalexterno.servico.base;

import br.com.lojadigicom.capitalexterno.servico.impl.*;

public class FabricaServico {


	private static FabricaServico fabrica = new FabricaServico();

    public static FabricaServico getInstancia() {
        return fabrica;
    }

	private LinhaProdutoServico linhaProdutoServico = null;
    public LinhaProdutoServico getLinhaProdutoServico() {
       if (linhaProdutoServico==null) {
           linhaProdutoServico = new LinhaProdutoServicoImpl();
       }
       return linhaProdutoServico;
    }
	private ProdutoServico produtoServico = null;
    public ProdutoServico getProdutoServico() {
       if (produtoServico==null) {
           produtoServico = new ProdutoServicoImpl();
       }
       return produtoServico;
    }
	private ItemCustoProdutoServico itemCustoProdutoServico = null;
    public ItemCustoProdutoServico getItemCustoProdutoServico() {
       if (itemCustoProdutoServico==null) {
           itemCustoProdutoServico = new ItemCustoProdutoServicoImpl();
       }
       return itemCustoProdutoServico;
    }
	private CustoMensalServico custoMensalServico = null;
    public CustoMensalServico getCustoMensalServico() {
       if (custoMensalServico==null) {
           custoMensalServico = new CustoMensalServicoImpl();
       }
       return custoMensalServico;
    }
	private PrecoVendaProdutoServico precoVendaProdutoServico = null;
    public PrecoVendaProdutoServico getPrecoVendaProdutoServico() {
       if (precoVendaProdutoServico==null) {
           precoVendaProdutoServico = new PrecoVendaProdutoServicoImpl();
       }
       return precoVendaProdutoServico;
    }
	private CenarioServico cenarioServico = null;
    public CenarioServico getCenarioServico() {
       if (cenarioServico==null) {
           cenarioServico = new CenarioServicoImpl();
       }
       return cenarioServico;
    }
	private PrevisaoVendaServico previsaoVendaServico = null;
    public PrevisaoVendaServico getPrevisaoVendaServico() {
       if (previsaoVendaServico==null) {
           previsaoVendaServico = new PrevisaoVendaServicoImpl();
       }
       return previsaoVendaServico;
    }
	private MesAnoServico mesAnoServico = null;
    public MesAnoServico getMesAnoServico() {
       if (mesAnoServico==null) {
           mesAnoServico = new MesAnoServicoImpl();
       }
       return mesAnoServico;
    }
	private NegocioServico negocioServico = null;
    public NegocioServico getNegocioServico() {
       if (negocioServico==null) {
           negocioServico = new NegocioServicoImpl();
       }
       return negocioServico;
    }
	private UsuarioServico usuarioServico = null;
    public UsuarioServico getUsuarioServico() {
       if (usuarioServico==null) {
           usuarioServico = new UsuarioServicoImpl();
       }
       return usuarioServico;
    }
	private DispositivoUsuarioServico dispositivoUsuarioServico = null;
    public DispositivoUsuarioServico getDispositivoUsuarioServico() {
       if (dispositivoUsuarioServico==null) {
           dispositivoUsuarioServico = new DispositivoUsuarioServicoImpl();
       }
       return dispositivoUsuarioServico;
    }
	private ComparacaoConcorrenteServico comparacaoConcorrenteServico = null;
    public ComparacaoConcorrenteServico getComparacaoConcorrenteServico() {
       if (comparacaoConcorrenteServico==null) {
           comparacaoConcorrenteServico = new ComparacaoConcorrenteServicoImpl();
       }
       return comparacaoConcorrenteServico;
    }
	private ValorAgregadoServico valorAgregadoServico = null;
    public ValorAgregadoServico getValorAgregadoServico() {
       if (valorAgregadoServico==null) {
           valorAgregadoServico = new ValorAgregadoServicoImpl();
       }
       return valorAgregadoServico;
    }
	private CaracteristicaMercadoServico caracteristicaMercadoServico = null;
    public CaracteristicaMercadoServico getCaracteristicaMercadoServico() {
       if (caracteristicaMercadoServico==null) {
           caracteristicaMercadoServico = new CaracteristicaMercadoServicoImpl();
       }
       return caracteristicaMercadoServico;
    }
}