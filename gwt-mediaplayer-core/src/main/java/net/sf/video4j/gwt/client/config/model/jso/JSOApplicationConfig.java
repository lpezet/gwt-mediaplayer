package net.sf.video4j.gwt.client.config.model.jso;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * @author gumatias
 */
public class JSOApplicationConfig extends JavaScriptObject {
    
    protected JSOApplicationConfig() { }
    
    public final static native JSOApplicationConfig build()/*-{
        return $wnd.video4j;
    }-*/;
    
    public final native boolean isAutoPlay() /*-{ 
        return this.autoPlay;
    }-*/;
    
    public final native String getWidth() /*-{ 
        return this.width;
    }-*/;
    
    public final native String getHeight() /*-{ 
        return this.height;
    }-*/;
    
    public final native JsArray<JSOMedia> getPlaylist() /*-{ 
        return this.playlist;
    }-*/;
    
}
