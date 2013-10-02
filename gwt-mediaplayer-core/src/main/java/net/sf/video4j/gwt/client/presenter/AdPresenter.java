package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.dispatch.AsyncCallbackImpl;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent.PlaylistPlayHandler;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.plugin.client.vast.Ad;
import net.sf.video4j.gwt.plugin.client.vast.AdRequestCallback;
import net.sf.video4j.gwt.plugin.client.vast.InLine;
import net.sf.video4j.gwt.plugin.client.vast.VAST;
import net.sf.video4j.gwt.plugin.client.vast.service.IAdService;
import net.sf.video4j.gwt.shared.FetchAdAction;
import net.sf.video4j.gwt.shared.model.FetchAdResult;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import fr.hd3d.html5.video.client.VideoSource.VideoType;

/**
 * @author gumatias
 */
public class AdPresenter extends PresenterWidget<AdPresenter.AView> implements IPlugin, PlaylistPlayHandler {

    public interface AView extends View {
        void startPlayer(PlayerParameters pParams);
    }
    
    protected Logger mLogger = Logger.getLogger(this.getClass().getName());
    
    private IAdService    mAdService;
    private DispatchAsync mDispatcher;
    
    @Inject
    public AdPresenter(EventBus pEventBus, AView pView, IAdService pAdService, DispatchAsync pDispatcher) {
        super(pEventBus, pView);
        mAdService  = pAdService;
        mDispatcher = pDispatcher;
        addRegisteredHandler(PlaylistPlayEvent.getType(), this);
    }
    
    @Override
    public String getPluginName() {
        return this.getClass().getName();
    }
    
    @Override
    protected void onBind() {
        super.onBind();
        // TODO create a servlet that the user must add in its web.xml so we can 
        // perform ad ws calls to retrieve ad info and get back to this logic
        
        // XXX Servlet implementation
        FetchAdAction oAction = new FetchAdAction();
        mDispatcher.execute(oAction, new AsyncCallbackImpl<FetchAdResult>() {

            @Override
            public void onSuccess(FetchAdResult pResult) {
                // TODO display Ad on playlist event
                mLogger.info("got result=" + pResult);
            }
            
        });
        
        // XXX AJAX implementation
        // is not allowed by Access-Control-Allow-Origin
        String oAdServiceURL = "http://demo.tremorvideo.com/proddev/vast/vast2VPAIDLinear.xml"; // coming from front end (JS) ? 
        mAdService.fetchAds(oAdServiceURL, new AdRequestCallback() {
            
            @Override
            public void onResponseReceived(VAST pVAST) {
                mLogger.log(Level.INFO, "pVAST=" + pVAST);
                mLogger.log(Level.INFO, "pVAST.getAds()=" + pVAST.getAds() + " | size=" + pVAST.getAds().size());
                Ad oAd = pVAST.getAds().get(0);
                mLogger.log(Level.INFO, "oAd=" + oAd);
                // XXX need to check type of ad.. can be an image, video or?
                if (oAd instanceof InLine) {
                    // TODO display inline Ad
                }
                PlayerParameters oParams = new PlayerParameters()
                    .withAutoPlay(true)
                    .withControls(false) //TODO: this should come from ApplicationConfig (?)
//                      .withFileSource(pEvent.getPlayItem().getMedia().getURI()) // coming from ad service?
                    .withHeightInPixels(360) //TODO: this should come from the ApplicationConfig
                    .withVideoType(VideoType.MP4)
                    .withWidthInPixels(640); //TODO: this should come from the ApplicationConfig
//                    getView().startPlayer(oParams);
//                    mPlaying = pEvent.getPlayItem();
//                    pEvent.getPlayItem().isInStream();
                mLogger.log(Level.INFO, "Ad now playing");                    
            }
            
            @Override
            public void onError(Throwable pException) {
                mLogger.log(Level.SEVERE, "Failed to fetch ads from server");
            }
        });
    }

    @Override
    public void onPlaylistPlayEvent(final PlaylistPlayEvent pEvent) {
        mLogger.log(Level.INFO, "Received PlaylistPlayEvent.");
    }
    
}