package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent.ApplicationInitHandler;
import net.sf.video4j.gwt.client.event.ControlFullScreenEvent;
import net.sf.video4j.gwt.client.event.ControlFullScreenEvent.ControlFullScreenHandler;
import net.sf.video4j.gwt.client.event.ControlMuteEvent;
import net.sf.video4j.gwt.client.event.ControlMuteEvent.ControlMuteHandler;
import net.sf.video4j.gwt.client.event.ControlPauseEvent;
import net.sf.video4j.gwt.client.event.ControlPauseEvent.ControlPauseHandler;
import net.sf.video4j.gwt.client.event.ControlPlayEvent;
import net.sf.video4j.gwt.client.event.ControlPlayEvent.ControlPlayHandler;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent.ControlSeekedHandler;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent.ControlUnmuteHandler;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent.ControlVolumeChangeHandler;
import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.model.PlayerParameters;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import fr.hd3d.html5.video.client.VideoSource.VideoType;

/**
 * @author gumatias
 */
public class PlayerPresenter extends PresenterWidget<PlayerPresenter.PView> 
    implements PlayerUiHandlers, ControlPlayHandler, ControlPauseHandler, ControlMuteHandler, ControlUnmuteHandler, 
                ControlSeekedHandler, ControlFullScreenHandler, ControlVolumeChangeHandler, 
                ApplicationInitHandler,
                IPlugin {
	
    public interface PView extends View, HasUiHandlers<PlayerUiHandlers> {
        void startPlayer(PlayerParameters pParams);
        void play();
        void pause();
        void mute();
        void unmute();
        void fullScreen();
        void volume(double pValue);
        void seek(double pValue);
    }
    
    private Logger mLogger = Logger.getLogger(PlayerPresenter.class.getName());
	
    @Inject
    public PlayerPresenter(EventBus pEventBus, PView pView) {
        super(pEventBus, pView);
        getView().setUiHandlers(this);
        
    }
    
    @Override
    public String getPluginName() {
    	return "PlayerPresenter";
    }
    
    @Override
    protected void onBind() {
        super.onBind();
        getView().startPlayer(getPlayerParameters());
        addRegisteredHandlers();
    }

    private void addRegisteredHandlers() {
        addRegisteredHandler(ControlPlayEvent.getType(), this);
        addRegisteredHandler(ControlPauseEvent.getType(), this);
        addRegisteredHandler(ControlMuteEvent.getType(), this);
        addRegisteredHandler(ControlUnmuteEvent.getType(), this);
        addRegisteredHandler(ControlSeekedEvent.getType(), this);
        addRegisteredHandler(ControlFullScreenEvent.getType(), this);
        addRegisteredHandler(ControlVolumeChangeEvent.getType(), this);
        addRegisteredHandler(ApplicationInitEvent.getType(), this);
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
    
    @Override
    public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
    	mLogger.log(Level.INFO, "received AppInitEvent...");
    	pEvent.getConfig().getPlugins().add(this);
    	mLogger.log(Level.INFO, "Firing plugin ready event...");
    	PluginReadyEvent.fire(this, this);
    	/*
    	GWT.runAsync(new RunAsyncCallback() {
			
			@Override
			public void onSuccess() {
				mLogger.log(Level.INFO, "Firing plugin ready event...");
				PluginReadyEvent.fire(PlayerPresenter.this, PlayerPresenter.this);
			}
			
			@Override
			public void onFailure(Throwable pReason) {
				Window.alert("Error firing plugin ready event for " + PlayerPresenter.this.getPluginName() + " plugin. Reason = " + pReason.getMessage());
			}
		});
		*/
    }

    @Override
    public void onControlPlayEvent(ControlPlayEvent pEvent) {
        getView().play();
    }
    
    @Override
    public void onControlPauseEvent(ControlPauseEvent pEvent) {
        getView().pause();
    }

    @Override
    public void onControlVolumeChangeEvent(ControlVolumeChangeEvent pEvent) {
        getView().volume(pEvent.getValue());
    }

    @Override
    public void onControlFullScreenEvent(ControlFullScreenEvent pEvent) {
        getView().fullScreen();
    }

    @Override
    public void onControlSeekedEvent(ControlSeekedEvent pEvent) {
        getView().seek(pEvent.getValue());
    }

    @Override
    public void onControlUnmuteEvent(ControlUnmuteEvent pEvent) {
        getView().unmute();
    }

    @Override
    public void onControlMuteEvent(ControlMuteEvent pEvent) {
        getView().mute();
    }

}