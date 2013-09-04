package com.apps.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imageUrl;
	private String thumbUrl;
	
	public ImageResult(JSONObject result) {
		try {
			this.imageUrl = result.getString("url");
			this.thumbUrl = result.getString("tbUrl");
		}
		catch (JSONException e){
			this.imageUrl = null;
			this.thumbUrl = null;			
		}
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
	public String toString() {
		return this.thumbUrl;
	}

	public static ArrayList<ImageResult> fromJsonArray(
			JSONArray imageJsonResults) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for(int i=0 ; i<imageJsonResults.length(); i++){
			try{
				results.add(new ImageResult(imageJsonResults.getJSONObject(i)));
			}
			catch (JSONException e){
				e.printStackTrace();
			}			
		}
		return results;
	}
}
