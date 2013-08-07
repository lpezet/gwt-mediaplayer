package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.presenter.Video4JPresenter;
import net.sf.video4j.gwt.client.view.Video4JView;

import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * 
 * @author gumatias
 */
public class Video4JModule extends AbstractPresenterModule {
    
    @Override
    protected void configure() {
    	bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		
    	install(new PlayerModule());
    	install(new ControlModule());
    	install(new ApplicationModule());
    	
        bindPresenter(Video4JPresenter.class, Video4JPresenter.V4JView.class, Video4JView.class, Video4JPresenter.V4JProxy.class);
    }
    
}