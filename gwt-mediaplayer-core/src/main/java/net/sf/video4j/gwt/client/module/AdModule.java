package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.presenter.AdPresenter;
import net.sf.video4j.gwt.client.view.AdView;
import net.sf.video4j.gwt.plugin.client.vast.dao.AdService;
import net.sf.video4j.gwt.plugin.client.vast.dao.IAdService;
import net.sf.video4j.gwt.plugin.shared.vast.IVASTParser;
import net.sf.video4j.gwt.plugin.shared.vast.VASTParser;

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author gumatias
 */
public class AdModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DispatchAsyncModule());
        bind(IAdService.class).to(AdService.class).in(Singleton.class);
        bind(IVASTParser.class).to(VASTParser.class).in(Singleton.class);
        bindPresenterWidget(AdPresenter.class, AdPresenter.AView.class, AdView.class);
    }

}