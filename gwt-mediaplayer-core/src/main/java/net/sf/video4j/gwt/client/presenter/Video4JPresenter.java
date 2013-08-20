package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Logger;

import net.sf.video4j.gwt.client.controller.ApplicationController;
import net.sf.video4j.gwt.client.controller.PlaylistController;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.place.NameTokens;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

/**
 * 
 * @author gumatias
 */
public class Video4JPresenter extends Presenter<Video4JPresenter.V4JView, Video4JPresenter.V4JProxy> {
    
    private Logger mLogger = Logger.getLogger(this.getClass().getName());

    @ProxyStandard
    @NameToken(NameTokens.video4JModule)
    public interface V4JProxy extends ProxyPlace<Video4JPresenter> {
    }

    public interface V4JView extends View {
        ApplicationConfig getApplicationConfig();
    }

    public static final Object SLOT_VIDEO_PLAYER = new Object();
    public static final Object SLOT_CONTROL      = new Object();

    PlayerPresenter            mPlayerPresenter;
    ControlPresenter           mControlPresenter;
    ApplicationController      mApplicationController;
    PlaylistController         mPlaylistController;
    
    @Inject
    Video4JPresenter(
                EventBus pEventBus, 
                V4JView pView, 
                V4JProxy pProxy, 
                PlayerPresenter pPlayerPresenter, 
                ControlPresenter pControlPresenter,
                ApplicationController pApplicationController,
                PlaylistController pPlaylistController
                ) {
        super(pEventBus, pView, pProxy, RevealType.Root);
        mPlayerPresenter = pPlayerPresenter;
        mControlPresenter = pControlPresenter;
        mApplicationController = pApplicationController;
        mPlaylistController = pPlaylistController;
    }
    
    @Override
    protected void onBind() {
        super.onBind();
        setInSlot(SLOT_VIDEO_PLAYER, mPlayerPresenter);
        setInSlot(SLOT_CONTROL, mControlPresenter);
        ApplicationConfig oConfig = getView().getApplicationConfig();
        mApplicationController.begin(oConfig);
    }

}