package net.sf.video4j.gwt.client.view;


import net.sf.video4j.gwt.client.handler.ControlUiHandlers;
import net.sf.video4j.gwt.client.presenter.ControlPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author gumatias
 */
public class ControlView extends ViewWithUiHandlers<ControlUiHandlers> implements ControlPresenter.CView {
    
    public interface Binder extends UiBinder<HTMLPanel, ControlView> {
    }

    @UiField SimplePanel mControl;
    
    @Inject
    public ControlView(Binder pBinder) {
        initWidget(pBinder.createAndBindUi(this));
    }

}