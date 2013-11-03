package net.sf.video4j.gwt.client.view;

import net.sf.video4j.gwt.client.config.model.jso.JSOApplicationConfig;
import net.sf.video4j.gwt.client.config.model.jso.JSOMedia;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IApplicationConfig;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.client.presenter.Video4JPresenter;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * 
 * @author gumatias
 */
public class Video4JView extends ViewImpl implements Video4JPresenter.V4JView {
    
    public interface Binder extends UiBinder<Widget, Video4JView> {
    }

    @UiField 
    SimplePanel mVideoPlayerPanel;
    @UiField 
    SimplePanel mControlPanel;
    @UiField 
    SimplePanel mAdPanel;

    @Inject
    public Video4JView(Binder pBinder) {
        initWidget(pBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object pSlot, IsWidget pContent) {
        if (pSlot == Video4JPresenter.SLOT_VIDEO_PLAYER) 
            mVideoPlayerPanel.setWidget(pContent);
        else if (pSlot == Video4JPresenter.SLOT_CONTROL) 
            mControlPanel.setWidget(pContent);
        else if (pSlot == Video4JPresenter.SLOT_AD) 
            mAdPanel.setWidget(pContent);   
        else 
            super.setInSlot(pSlot, pContent);
    }
    
    @Override
    public IApplicationConfig getApplicationConfig() {
    	JSOApplicationConfig oJSOConfig = JSOApplicationConfig.build();
        ApplicationConfig oConfig = new ApplicationConfig();
        oConfig.setPlaylist(oJSOConfig.getPlaylist());
        oConfig.setCommon(oJSOConfig.getCommon());
        oConfig.setPlugins(oJSOConfig.getPlugins());
        return oConfig;
    }
    
    private Playlist newPlaylist(JSONArray pPlaylist) {
        Playlist oPlaylist = new Playlist();
        for (int i = 0; i < pPlaylist.size(); i++) {
            JSONValue v = pPlaylist.get(i);
            oPlaylist.add(newMedia(v));
        }
        return oPlaylist;
    }

    private Playlist newPlaylist(JsArray<JSOMedia> pJSOPlaylist) {
        Playlist oPlaylist = new Playlist();
        for (int i = 0; i < pJSOPlaylist.length(); i++) {
            JSOMedia oJSOMedia = pJSOPlaylist.get(i);
            oPlaylist.add(newMedia(oJSOMedia));
        }
        return oPlaylist;
    }
    
    private Media newMedia(JSONValue pMedia) {
        Media m = new Media();
        JSONObject oObj = pMedia.isObject();
        if (oObj != null) {
        	m.setURI(oObj.get("url").isString().stringValue());        	
        	for (String oKey : oObj.keySet()) {
        		m.getProperties().put(oKey, oObj.get(oKey));
        	}
        }
        return m;
    }

    private Media newMedia(JSOMedia pJSOMedia) {
        Media m = new Media();
        m.setURI(pJSOMedia.getURL());
        return m;
    }
    
}