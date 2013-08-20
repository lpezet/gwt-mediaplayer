package net.sf.video4j.gwt.client.view;

import net.sf.video4j.gwt.client.config.model.jso.JSOApplicationConfig;
import net.sf.video4j.gwt.client.config.model.jso.JSOMedia;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.Playlist;
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

    @UiField 
    SimplePanel mVideoPlayerPanel;
    @UiField 
    SimplePanel mControlPanel;

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
    public ApplicationConfig getApplicationConfig() {
        JSOApplicationConfig oJSOConfig = JSOApplicationConfig.build();
        ApplicationConfig oConfig = new ApplicationConfig(newPlaylist(oJSOConfig.getPlaylist()));
        oJSOConfig.isAutoPlay();
        oJSOConfig.getWidth();
        oJSOConfig.getHeight();
        return oConfig;
    }

    private Playlist newPlaylist(JsArray<JSOMedia> pJSOPlaylist) {
        Playlist oPlaylist = new Playlist();
        for (int i = 0; i < pJSOPlaylist.length(); i++) {
            JSOMedia oJSOMedia = pJSOPlaylist.get(i);
            oPlaylist.add(newMedia(oJSOMedia));
        }
        return oPlaylist;
    }

    private Media newMedia(JSOMedia pJSOMedia) {
        Media m = new Media();
        m.setURI(pJSOMedia.getURL());
        return m;
    }
    
}