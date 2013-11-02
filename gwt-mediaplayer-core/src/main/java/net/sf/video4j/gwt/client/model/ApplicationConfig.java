/**
 * 
 */
package net.sf.video4j.gwt.client.model;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

/**
 * @author luc
 *
 */
public class ApplicationConfig implements IApplicationConfig {
	
	private JSONArray mPlaylist;

	@Override
	public JSONObject getCommon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray getPlaylist() {
		return mPlaylist;
	}

	@Override
	public JSONObject getPlugins() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPlaylist(JSONArray pJSON) {
		mPlaylist = pJSON;
	}

}
