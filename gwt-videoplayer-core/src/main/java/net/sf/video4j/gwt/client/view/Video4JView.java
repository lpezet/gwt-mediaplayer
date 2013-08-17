package net.sf.video4j.gwt.client.view;

import java.util.LinkedHashSet;
import java.util.Set;

import net.sf.video4j.gwt.client.config.model.Playlist;
import net.sf.video4j.gwt.client.config.model.Track;
import net.sf.video4j.gwt.client.config.model.Video4JConfig;
import net.sf.video4j.gwt.client.config.model.jso.JSOTrack;
import net.sf.video4j.gwt.client.config.model.jso.JSOVideo4JConfig;
import net.sf.video4j.gwt.client.presenter.Video4JPresenter;

import com.google.gwt.core.client.JsArray;
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

    @UiField SimplePanel mVideoPlayerPanel;
    @UiField SimplePanel mControlPanel;

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
        else 
            super.setInSlot(pSlot, pContent);
    }

    @Override
    public Video4JConfig getVideo4JConfig() {
        JSOVideo4JConfig oJSOConfig = JSOVideo4JConfig.build();
        Video4JConfig oConfig = new Video4JConfig();
        oConfig.setAutoPlay(oJSOConfig.isAutoPlay());
        oConfig.setWidth(oJSOConfig.getWidth());
        oConfig.setHeight(oJSOConfig.getHeight());
        oConfig.setPlaylist(newPlaylist(oJSOConfig.getPlaylist()));
        return oConfig;
    }
    

    private Playlist newPlaylist(JsArray<JSOTrack> pJSOPlaylist) {
        Playlist oPlaylist = new Playlist();
        Set<Track> oTracks = new LinkedHashSet<Track>();
        for (int i = 0; i < pJSOPlaylist.length(); i++) {
            JSOTrack oJSOTrack = pJSOPlaylist.get(i);
            oTracks.add(newTrack(oJSOTrack));
        }
        oPlaylist.setTracks(oTracks);
        return oPlaylist;
    }

    private Track newTrack(JSOTrack pJSOTrack) {
        Track oTrack = new Track();
        oTrack.setURL(pJSOTrack.getURL());
        return oTrack;
    }

}