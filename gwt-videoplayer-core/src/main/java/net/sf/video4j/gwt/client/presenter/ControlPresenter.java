package net.sf.video4j.gwt.client.presenter;

import net.sf.video4j.gwt.client.event.ControlFullScreenEvent;
import net.sf.video4j.gwt.client.event.ControlMuteEvent;
import net.sf.video4j.gwt.client.event.ControlPauseEvent;
import net.sf.video4j.gwt.client.event.ControlPlayEvent;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent;
import net.sf.video4j.gwt.client.handler.ControlUiHandlers;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * @author gumatias
 */
public class ControlPresenter extends PresenterWidget<ControlPresenter.CView> implements ControlUiHandlers {
	
    public interface CView extends View, HasUiHandlers<ControlUiHandlers> {
    }
	
    @Inject
    public ControlPresenter(EventBus pEventBus, CView pView) {
        super(pEventBus, pView);
        getView().setUiHandlers(this);
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
    public void onSeeked() {
        ControlSeekedEvent.fire(this);
    }

    @Override
    public void onVolumeChange(double pValue) {
        ControlVolumeChangeEvent.fire(this, pValue);
    }
	
}