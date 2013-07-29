package net.sf.video4j.gwt.client.presenter;

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
	
}