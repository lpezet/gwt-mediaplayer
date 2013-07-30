package net.sf.video4j.gwt.client.presenter;

import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.model.PlayerParameters;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import fr.hd3d.html5.video.client.VideoSource.VideoType;

/**
 * @author gumatias
 */
public class PlayerPresenter extends PresenterWidget<PlayerPresenter.PView> implements PlayerUiHandlers {
	
    public interface PView extends View, HasUiHandlers<PlayerUiHandlers> {
        void startPlayer(PlayerParameters pParams);
    }
	
    @Inject
    public PlayerPresenter(EventBus pEventBus, PView pView) {
        super(pEventBus, pView);
        getView().setUiHandlers(this);
    }
    
    @Override
    protected void onBind() {
        super.onBind();
        getView().startPlayer(getPlayerParameters());
    }
    
    private PlayerParameters getPlayerParameters() {
        PlayerParameters oParams = new PlayerParameters()
            .withControls(false)  
            .withAutoPlay(false)
            .withWidthInPixels(500).withHeightInPixels(400)
            .withVideoType(VideoType.MP4)
            .withFileSource("http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4");
        return oParams;
    }
    
}