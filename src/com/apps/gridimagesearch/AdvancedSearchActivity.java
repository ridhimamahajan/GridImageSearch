package com.apps.gridimagesearch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class AdvancedSearchActivity extends Activity {

	Spinner spinner_image_size;
	Spinner spinner_color;
	Spinner spinner_image_type;
	EditText etSiteFilter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced_search);
		spinner_color= (Spinner)findViewById(R.id.spinner_color);
		spinner_image_size = (Spinner)findViewById(R.id.spinner_image_size);
		spinner_image_type = (Spinner)findViewById(R.id.spinner_image_type);
		etSiteFilter = (EditText)findViewById(R.id.etSiteFilter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.advanced_search, menu);
		return true;
	}
	
	public void onSave(View v) {
		Intent data = new Intent();
		Log.d("DEBUG",spinner_image_size.getSelectedItem().toString());
		Log.d("DEBUG",spinner_image_type.getSelectedItem().toString());
		Log.d("DEBUG",spinner_color.getSelectedItem().toString());
		Log.d("DEBUG",etSiteFilter.getText().toString());
		//ImageResult imageResult = imageResults.get(position);
		data.putExtra("imageSize",spinner_image_size.getSelectedItem().toString() );
		data.putExtra("imageType",spinner_image_type.getSelectedItem().toString() );
		data.putExtra("color",spinner_color.getSelectedItem().toString() );
		data.putExtra("siteFilter",etSiteFilter.getText().toString() );
		if (getParent() == null) {
		    setResult(Activity.RESULT_OK, data);
		} else {
		    getParent().setResult(Activity.RESULT_OK, data);
		}
		//setResult(RESULT_OK, data); // set result code and bundle data for response
		super.finish();
	}

}
