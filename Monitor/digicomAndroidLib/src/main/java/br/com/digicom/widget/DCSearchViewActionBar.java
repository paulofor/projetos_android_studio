package br.com.digicom.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import br.com.digicom.R;

public class DCSearchViewActionBar extends SearchView {

	public DCSearchViewActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		trataVisual(context);
	}
	
	public DCSearchViewActionBar(Context context) {
		super(context);
		trataVisual(context);
	}
	
	private void trataVisual(Context context) {
		
		
		int searchIconId = context.getResources().getIdentifier("android:id/search_button", null, null);
		ImageView searchIcon = (ImageView) findViewById(searchIconId);
		searchIcon.setImageResource(br.com.digicom.R.drawable.ic_action_search);
		
		
		int id = context.getResources().getIdentifier("android:id/search_src_text", null, null);
		EditText searchPlate = (EditText) findViewById(id);
	    searchPlate.setTextColor(context.getResources().getColor(android.R.color.white));
	    //searchPlate.setBackgroundResource(android.R.drawable.edit_text);
	    //searchPlate.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
	    searchPlate.setHintTextColor(context.getResources().getColor(android.R.color.white));
		
	    //searchPlate.setBackgroundResource(R.drawable.textfield_searchview_holo_light);
	    
        
	}
	
	/*
	 * <item name="searchViewTextField">@drawable/textfield_searchview_holo_dark</item>
    <item name="searchViewTextFieldRight">@drawable/textfield_searchview_right_holo_dark</item>
    <item name="searchViewCloseIcon">@android:drawable/ic_clear</item>
    <item name="searchViewSearchIcon">@android:drawable/ic_search</item>
    <item name="searchViewGoIcon">@android:drawable/ic_go</item>
    <item name="searchViewVoiceIcon">@android:drawable/ic_voice_search</item>
    <item name="searchViewEditQuery">@android:drawable/ic_commit_search_api_holo_dark</item>
    <item name="searchViewEditQueryBackground">?attr/selectableItemBackground</item>
	 */
	
	/*
	 * https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/res/res/values/themes_holo.xml
	 * 
	 *  <style name="Theme.Holo">
	 *   <item name="searchViewStyle">@style/Widget.Holo.SearchView</item>
        <item name="searchDialogTheme">@style/Theme.Holo.SearchBar</item>
	 */

}
