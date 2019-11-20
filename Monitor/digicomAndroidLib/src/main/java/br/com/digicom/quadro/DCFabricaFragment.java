package br.com.digicom.quadro;

public abstract class DCFabricaFragment {

	protected IFragment preparaFragment(IFragment fragment, BundleFragment bundle) {
		IFragment saida = fragment;
		saida.setBundle(bundle);
		saida.extraiBundle();
		return saida;
	}
}
