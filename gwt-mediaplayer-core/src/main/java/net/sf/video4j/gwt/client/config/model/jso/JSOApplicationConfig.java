package net.sf.video4j.gwt.client.config.model.jso;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONValue;

/**
 * @author gumatias
 */
public class JSOApplicationConfig extends JavaScriptObject {
    
    protected JSOApplicationConfig() { }
    
    public final static native JSOApplicationConfig build()/*-{
        return $wnd.video4j;
    }-*/;
    
    /**
     * Gets the JSONValue associated with the specified property.
     * 
     * @param key the property to access
     * @return the value of the specified property, or <code>null</code> if the
     *         property does not exist
     * @throws NullPointerException if key is <code>null</code>
     */
    public final JSONValue get(String key) {
      if (key == null) {
        throw new NullPointerException();
      }
      return get0(key);
    }
    
    private native JSONValue get0(String key) /*-{
	    var jsObject = this;
	    var v;
	    // In Firefox, jsObject.hasOwnProperty(key) requires a primitive string
	    key = String(key);       
	    if (jsObject.hasOwnProperty(key)) {
	      v = jsObject[key];
	    }
	    var func = @com.google.gwt.json.client.JSONParser::typeMap[typeof v];
	    var ret = func ? func(v) : @com.google.gwt.json.client.JSONParser::throwUnknownTypeException(Ljava/lang/String;)(typeof v);
	    return ret;
	  }-*/;
    
    
    //public final native JavaScriptObject getCommon() 
    public final native JSONValue getCommon() /*-{ 
        var v = this['common'];
    	var func = @com.google.gwt.json.client.JSONParser::typeMap[typeof v];
    	return func ? func(v) : @com.google.gwt.json.client.JSONParser::throwUnknownTypeException(Ljava/lang/String;)(typeof v);
    }-*/;
    
    public final native JSONValue getPlaylist() /*-{ 
        var v = this['playlist'];
    	var func = @com.google.gwt.json.client.JSONParser::typeMap[typeof v];
    	return func ? func(v) : @com.google.gwt.json.client.JSONParser::throwUnknownTypeException(Ljava/lang/String;)(typeof v);
    }-*/;
    
    public final native JSONValue getPlugins() /*-{ 
    	var v = this['plugins'];
    	var func = @com.google.gwt.json.client.JSONParser::typeMap[typeof v];
    	return func ? func(v) : @com.google.gwt.json.client.JSONParser::throwUnknownTypeException(Ljava/lang/String;)(typeof v);
	}-*/;

}