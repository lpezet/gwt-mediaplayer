package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent.ApplicationInitHandler;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent.ApplicationLoadHandler;
import net.sf.video4j.gwt.client.event.ControlFullScreenEvent;
import net.sf.video4j.gwt.client.event.ControlMuteEvent;
import net.sf.video4j.gwt.client.event.ControlPauseEvent;
import net.sf.video4j.gwt.client.event.ControlPlayEvent;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.handler.ControlUiHandlers;
import net.sf.video4j.gwt.client.model.IPlugin;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * @author gumatias
 */
public class ControlPresenter extends PresenterWidget<ControlPresenter.CView> implements 
	ControlUiHandlers, IPlugin,
	ApplicationLoadHandler,
	ApplicationInitHandler {
	
    public interface CView extends View, HasUiHandlers<ControlUiHandlers> {
    }
    
    private Logger mLogger = Logger.getLogger(ControlPresenter.class.getName());
    
    @Inject
    public ControlPresenter(EventBus pEventBus, CView pView) {
        super(pEventBus, pView);
        getView().setUiHandlers(this);
    }
    
    @Override
    protected void onBind() {
    	super.onBind();
    	addRegisteredHandlers();
    }
    
    private void addRegisteredHandlers() {
    	addRegisteredHandler(ApplicationLoadEvent.getType(), this);
    	addRegisteredHandler(ApplicationInitEvent.getType(), this);
	}
    
    @Override
    public void onApplicationLoadEvent(ApplicationLoadEvent pEvent) {
    	mLogger.log(Level.INFO, "Received ApplicationLoadEvent.");
    	pEvent.getApplication().addPlugin(this);
    }

	@Override
    public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationInitEvent.");
		PluginReadyEvent.fire(this, this);
		mLogger.log(Level.INFO, "PluginReadyEvent fired.");
    	/*
    	GWT.runAsync(new RunAsyncCallback() {
			
			@Override
			public void onSuccess() {
				mLogger.log(Level.INFO, "Firing pluginReadyEvent from ControlPresenter...");
				PluginReadyEvent.fire(ControlPresenter.this, ControlPresenter.this);
			}
			
			@Override
			public void onFailure(Throwable pReason) {
				Window.alert("Error firing plugin ready event for " + ControlPresenter.this.getPluginName() + " plugin. Reason = " + pReason.getMessage());
			}
		});
		*/
    }
    
    @Override
    public String getPluginId() {
    	return "ControlPresenter";
    }

    @Override
    public void onPlay() {
        ControlPlayEvent.fire(this);
    }

    @Override
    public void onPause() {
        ControlPauseEvent.fire(this);
    }

    @Override
    public void onMute() {
        ControlMuteEvent.fire(this);
    }

    @Override
    public void onUnmute() {
        ControlUnmuteEvent.fire(this);
    }

    @Override
    public void onFullScreen() {
        ControlFullScreenEvent.fire(this);
    }

    @Override
    public void onSeeked(double pValue) {
        ControlSeekedEvent.fire(this, pValue);
    }

    @Override
    public void onVolumeChange(double pValue) {
        ControlVolumeChangeEvent.fire(this, pValue);
    }
	
}