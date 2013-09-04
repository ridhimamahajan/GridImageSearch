package com.apps.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
//import org.lucasr.smoothie.AsyncGridView;
//import org.lucasr.smoothie.ItemManager;


public class SearchActivity extends Activity {
	
	EditText etQuery;
	Button btnSearch;
	GridView gvResults;
	String imageSize = "icon"; 
	String color = "black"; 
	String imageType = "face";
	String siteFilter = null; 
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter ;
	static int counter = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long rowId) {
				Intent i = new Intent(getApplicationContext(), Activity_image_show.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
				
			}
			
		});
	 imageSize = (String) getIntent().getStringExtra("imageSize");
	 color = (String) getIntent().getStringExtra("color");
	 imageType = (String) getIntent().getStringExtra("imageType");
	 siteFilter = (String) getIntent().getStringExtra("siteFilter");
//	 Log.d("DEBUG",imageSize);
//	Log.d("DEBUG",color);
//	Log.d("DEBUG",imageType);
//	Log.d("DEBUG",siteFilter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		gvResults = (GridView) findViewById(R.id.gvResults);
	}
	
	public void onImageSearch(View v) {
		String queryText = etQuery.getText().toString();
		Toast.makeText(this, "Searching "+queryText, Toast.LENGTH_SHORT).show();
		
		AsyncHttpClient client = new AsyncHttpClient();
		//http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=Apple+Cake&start=4
		
		client.get("http://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
				"start=" + 0 + "&v=1.0&q=" + Uri.encode(queryText)+ "&as_sitesearch=" + siteFilter + "&imgsz=" + imageSize + "&imgtype=" + imageType + "&imgcolor=" + color,
			new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
            	JSONArray imageJsonResults = null; 
                // Do something with the response
            	try{
            		imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
            		System.out.println(imageJsonResults);
            		imageResults.clear();
            		imageAdapter.addAll(ImageResult.fromJsonArray(imageJsonResults));
            		Log.d("DEBUG",imageResults.toString());
            	}
            	catch (JSONException e)
            	{
            		e.printStackTrace();
            	}
                
            }
        });
	}
	
	public void onAdvancedSettingsClick(MenuItem m){
		Intent i = new Intent(getApplicationContext(), AdvancedSearchActivity.class);
		//ImageResult imageResult = imageResults.get(position);
		//i.putExtra("result", imageResult);
		startActivity(i);
	}
	
	public void onLoadMore (View v){
		counter +=8;
		String queryText = etQuery.getText().toString();
		
		AsyncHttpClient client = new AsyncHttpClient();
		//http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=Apple+Cake&start=4
		
		client.get("http://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
				"start=" + counter + "&v=1.0&q=" + Uri.encode(queryText)+ "&as_sitesearch=" + siteFilter + "&imgsz" + imageSize + "&imgtype" + imageType + "&imgcolor" + color,
			new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
            	JSONArray imageJsonResults = null; 
                // Do something with the response
            	try{
            		imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
            		System.out.println(imageJsonResults);
            		//imageResults.clear();
            		imageAdapter.clear();
            		imageAdapter.addAll(ImageResult.fromJsonArray(imageJsonResults));
            		Log.d("DEBUG",imageResults.toString());
            		
            	}
            	catch (JSONException e)
            	{
            		e.printStackTrace();
            	}
                
            }
        });
	}
}	
	
	

