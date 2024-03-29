package br.com.digicom.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.digicom.R;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.quadro.IFragment;


public abstract class BaseNavigatorActivity  extends BaseFragmentActivity{

	private ActionBar ab;
	
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private List<ItemNavigator> listaItens = null;
    
    //private TrocaQuadro trocaQuadro = new TrocaQuadro();
	
    
    protected abstract List<ItemNavigator> getListaItem();
    
    
    private List<String> getListaTitulo() {
    	List<String> saida = new ArrayList<String>();
    	for (ItemNavigator item : listaItens) {
    		saida.add(item.getTitulo());
    	}
    	return saida;
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_dl_frame);
		TrocaQuadro.getInstancia().setBaseActivity(this);
		listaItens = getListaItem();

		 mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	     mDrawerList = (ListView) findViewById(R.id.left_drawer);
	     mTitle = mDrawerTitle = getTitle();
		
	     // set a custom shadow that overlays the main content when the drawer opens
	     //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	     // set up the drawer's list view with items and click listener
	     mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, getListaTitulo()));
	     mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	     
		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dcPalette5)));
		
		 // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
       
        posOnCreate(savedInstanceState);
        if (savedInstanceState == null && unicoActivity()) {
            selectItem(0);
        }
        
	}

	//protected abstract int corActionBar();
	protected abstract void posOnCreate(Bundle savedInstanceState);
	
	protected boolean unicoActivity() {
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.navigator, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	/* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     	//if ("Criar".equals(item.getTitle())) {
    	//	return false;
    	//}
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        /*
        switch(item.getItemId()) {
        case R.id.action_settings:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
        */
        return true;
    }
	
    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    private void selectItem(int position) {
    	IFragment novoQuadro = getFragment(position);
    	if (novoQuadro!=null) {
    		TrocaQuadro.getInstancia().alteraQuadroPrincipal(novoQuadro);
    	} else {
    		Intent novoIntent = getIntent(position);
    		if (novoIntent==null) {
    			throw new RuntimeException("Sem intent/fragment para posição " + position);
    		}
    		TrocaQuadro.getInstancia().enviaIntent(novoIntent,this);
    	}
    	
        // update the main content by replacing fragments
        //Fragment fragment = new MenuFragment();
        //Bundle args = new Bundle();
        //args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        //fragment.setArguments(args);

        //FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(listaItens.get(position).getTitulo());
        mDrawerLayout.closeDrawer(mDrawerList);
    }
    
   
    
    protected abstract IFragment getFragment(int posicao);
    protected abstract Intent getIntent(int posicao);
    
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        //getActionBar().setTitle(mTitle);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
}
