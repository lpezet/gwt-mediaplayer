package net.sf.video4j.gwt.client.config.model.jso;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author gumatias
 */
public class JSOTrack extends JavaScriptObject {
    
    protected JSOTrack() { }
    
    public final native String getURL() /*-{ 
        return this.url;
    }-*/;
    
}