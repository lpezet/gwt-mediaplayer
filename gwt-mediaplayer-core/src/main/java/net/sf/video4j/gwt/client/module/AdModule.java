package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.presenter.AdPresenter;
import net.sf.video4j.gwt.client.view.AdView;
import net.sf.video4j.gwt.plugin.client.vast.IVASTParser;
import net.sf.video4j.gwt.plugin.client.vast.VASTParser;
import net.sf.video4j.gwt.plugin.client.vast.service.AdService;
import net.sf.video4j.gwt.plugin.client.vast.service.IAdService;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;


/**
 * @author gumatias
 */
public class AdModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bind(IAdService.class).to(AdService.class).in(Singleton.class);
        bind(IVASTParser.class).to(VASTParser.class).in(Singleton.class);
        
        bindPresenterWidget(AdPresenter.class, AdPresenter.AView.class, AdView.class);
    }

}