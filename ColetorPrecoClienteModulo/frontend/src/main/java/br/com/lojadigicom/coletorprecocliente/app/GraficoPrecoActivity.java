package br.com.lojadigicom.coletorprecocliente.app;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.PrecoDiarioClienteContract;
import br.com.lojadigicom.coletorprecocliente.data.provider.PrecoDiarioClienteProvider;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DBHelperBase;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.PrecoDiarioCliente;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.PrecoDiarioClienteMontador;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.lista.PrecoDiarioClienteListaBaseActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class GraficoPrecoActivity extends PrecoDiarioClienteListaBaseActivity {



    private LineChart lineChart = null;

    private long idProduto = 0;
    final HashMap<Integer, String> numMap = new HashMap<>();


    @Override
    protected void onCreateComplemento(Bundle savedInstanceState) {
        lineChart = (LineChart) findViewById(R.id.chart);
        Bundle extra = getIntent().getExtras();
        idProduto = (extra!=null?extra.getLong(Constantes.PRODUTO_CLIENTE_ID):0);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_grafico_preco;
    }

    private void montaGrafico(List<PrecoDiarioCliente> lista) {
        float max = lista.get(0).getPrecoVenda();
        float min = lista.get(0).getPrecoVenda();


        List<Entry> entries = new ArrayList<Entry>();
        ArrayList<String> labels = new ArrayList<String>();


        int i = 0;
        for (PrecoDiarioCliente preco : lista) {
            numMap.put(i,  preco.getDataHoraDDMMAAAA());
            entries.add(new Entry(i++,preco.getPrecoVenda(),preco.getDataHoraDDMMAAAA()));

            if (preco.getPrecoVenda()>max) max = preco.getPrecoVenda();
            if (preco.getPrecoVenda()<min) min = preco.getPrecoVenda();
        }

        max = max * 1.15f;
        min = min * 0.85f;


        ;
        LineDataSet dataset = new LineDataSet(entries,"Preços");
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataset);

        //dataset.setColors(ColorTemplate.COLOR_NONE);
        dataset.setDrawValues(false);

        LineData data = new LineData(dataSets);
         //
        //dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        YAxis leftAxis = lineChart.getAxis(YAxis.AxisDependency.LEFT);
        leftAxis.setDrawZeroLine(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyCustomFormatter());
        xAxis.setDrawLabels(true);


        lineChart.setData(data);
        lineChart.animateY(1000);

        lineChart.invalidate();


    }



    protected Uri getUri() {
        Uri uri = PrecoDiarioClienteContract.buildGetPorPertenceAProdutoClienteUri(idProduto);
        return uri;
    }
    protected MontadorDaoI getMontador() {
        return new PrecoDiarioClienteMontador();
    }
    protected String getColunaOrdenacao() {
        return PrecoDiarioClienteContract.COLUNA_DATA_HORA;
    }



    @Override
    protected void trataLista(List<PrecoDiarioCliente> lista) {
        montaGrafico(lista);
    }


    private class MyCustomFormatter implements IAxisValueFormatter {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return numMap.get((int)value);
        }
    }
}
