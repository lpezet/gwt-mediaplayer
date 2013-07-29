package net.sf.video4j.gwt.client.presenter;

import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * @author gumatias
 */
public class PlayerPresenter extends PresenterWidget<PlayerPresenter.PView> implements PlayerUiHandlers {
	
    public interface PView extends View, HasUiHandlers<PlayerUiHandlers> {
    }
	
    @Inject
    public PlayerPresenter(EventBus pEventBus, PView pView) {
        super(pEventBus, pView);
        getView().setUiHandlers(this);
    }
	
}