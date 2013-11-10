package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.IConfigProvider;
import net.sf.video4j.gwt.client.LocalJSConfigProvider;
import net.sf.video4j.gwt.client.presenter.Video4JPresenter;
import net.sf.video4j.gwt.client.view.Video4JView;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * 
 * @author gumatias
 */
public class Video4JModule extends AbstractPresenterModule {
    
    @Override
    protected void configure() {
    	install(new PlayerModule());
    	install(new ControlModule());
    	install(new PlaylistModule());
    	install(new AdModule());
    	install(new BandwidthModule());
    	install(new ApplicationModule());
		

		bind(IConfigProvider.class).to(LocalJSConfigProvider.class).in(Singleton.class);

        bindPresenter(Video4JPresenter.class, Video4JPresenter.V4JView.class, Video4JView.class, Video4JPresenter.V4JProxy.class);
    }
    
}