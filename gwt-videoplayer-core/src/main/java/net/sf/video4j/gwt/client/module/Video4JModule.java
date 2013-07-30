package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.presenter.Video4JPresenter;
import net.sf.video4j.gwt.client.view.Video4JView;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import fr.hd3d.html5.video.client.VideoWidget;

/**
 * 
 * @author gumatias
 */
public class Video4JModule extends AbstractPresenterModule {
    
    @Override
    protected void configure() {
        bind(VideoWidget.class).in(Singleton.class);
        
    	install(new PlayerModule());
    	install(new ControlModule());
    	
        bindPresenter(Video4JPresenter.class, Video4JPresenter.V4JView.class, Video4JView.class, Video4JPresenter.V4JProxy.class);
    }
    
}