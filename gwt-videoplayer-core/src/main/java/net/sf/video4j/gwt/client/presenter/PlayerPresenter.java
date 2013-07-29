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
            /* 
             *  FIXME   
             *  remove after adding custom controls. need to pass the object among presenters, read more at: 
             *  https://code.google.com/p/gwt-platform/wiki/GettingStarted#Setting_the_source_on_fireEvent_with_your_own_objects
             *  https://code.google.com/p/gwt-platform/wiki/GettingStarted#Attaching_events_to_proxies
             *  https://code.google.com/p/gwt-platform/wiki/BoilerplateGeneration#Generate_Event_and_Event_Handler 
             */
            .withControls(true)  
            .withAutoPlay(true)
            .withWidthInPixels(500).withHeightInPixels(400)
            .withVideoType(VideoType.MP4)
            .withFileSource("http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4");
        return oParams;
    }
    
}