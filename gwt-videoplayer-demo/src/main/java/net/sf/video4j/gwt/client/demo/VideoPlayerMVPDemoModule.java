package net.sf.video4j.gwt.client.demo;

import net.sf.video4j.gwt.client.module.Video4JModule;
import net.sf.video4j.gwt.client.place.NameTokens;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;

/**
 * 
 * @author gumatias
 */
public class VideoPlayerMVPDemoModule extends AbstractPresenterModule {
    
    @Override
    protected void configure() {
        install(new DefaultModule(DefaultPlaceManager.class));
        install(new Video4JModule());

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.video4JModule);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.video4JModule);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.video4JModule);
    }
    
}