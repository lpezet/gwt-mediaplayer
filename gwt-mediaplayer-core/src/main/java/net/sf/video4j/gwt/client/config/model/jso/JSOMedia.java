package net.sf.video4j.gwt.client.config.model.jso;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author gumatias
 */
public class JSOMedia extends JavaScriptObject {
    
    protected JSOMedia() { }
    
    public final native String getURL() /*-{ 
        return this.url;
    }-*/;
    
}