package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.presenter.Video4JPresenter;
import net.sf.video4j.gwt.client.view.Video4JView;

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
    	
        bindPresenter(Video4JPresenter.class, Video4JPresenter.V4JView.class, Video4JView.class, Video4JPresenter.V4JProxy.class);
    }
    
}