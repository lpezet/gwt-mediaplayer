package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.presenter.PlayerPresenter;
import net.sf.video4j.gwt.client.view.PlayerView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;


/**
 * @author gumatias
 */
public class PlayerModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenterWidget(PlayerPresenter.class, PlayerPresenter.PView.class, PlayerView.class);
    }

}