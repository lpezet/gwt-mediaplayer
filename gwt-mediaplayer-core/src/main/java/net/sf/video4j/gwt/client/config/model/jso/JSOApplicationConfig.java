package net.sf.video4j.gwt.client.config.model.jso;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

/**
 * @author gumatias
 */
public class JSOApplicationConfig extends JavaScriptObject {
    
    protected JSOApplicationConfig() { }
    
    public final static native JSOApplicationConfig build()/*-{
        return $wnd.video4j;
    }-*/;
    
    public final native JSONObject getCommon() /*-{ 
        return this.common;
    }-*/;
    
    public final native JSONArray getPlaylist() /*-{ 
        return this.playlist;
    }-*/;
    
    public final native JSONObject getPlugins() /*-{ 
    	return this.plugins;
	}-*/;
}
