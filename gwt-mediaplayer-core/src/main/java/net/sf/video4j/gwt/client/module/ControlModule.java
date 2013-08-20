package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.presenter.ControlPresenter;
import net.sf.video4j.gwt.client.view.ControlView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;


/**
 * @author gumatias
 */
public class ControlModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenterWidget(ControlPresenter.class, ControlPresenter.CView.class, ControlView.class);
    }

}