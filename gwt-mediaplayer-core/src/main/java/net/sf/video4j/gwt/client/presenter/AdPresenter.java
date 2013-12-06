package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Level;

import net.sf.video4j.gwt.client.PlaylistHelper;
import net.sf.video4j.gwt.client.dispatch.AsyncCallbackImpl;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.model.IAdBean;
import net.sf.video4j.gwt.client.model.IApplication;
import net.sf.video4j.gwt.client.model.IApplicationConfig;
import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.player.PlayItem;
import net.sf.video4j.gwt.client.util.BeanFactory;
import net.sf.video4j.gwt.client.util.IAdBeanFactory;
import net.sf.video4j.gwt.plugin.client.vast.dao.IAdService;
import net.sf.video4j.gwt.plugin.shared.vast.Ad;
import net.sf.video4j.gwt.plugin.shared.vast.AdRequestCallback;
import net.sf.video4j.gwt.plugin.shared.vast.InLine;
import net.sf.video4j.gwt.plugin.shared.vast.VAST;
import net.sf.video4j.gwt.shared.FetchAdAction;
import net.sf.video4j.gwt.shared.model.FetchAdResult;

import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;

/**
 * @author gumatias
 */
public class AdPresenter extends BasePlayerPresenterWidget<AdPresenter.AView> {

	public interface AView extends View, HasUiHandlers<PlayerUiHandlers> {
		void startPlayer(PlayerParameters pParams);
		void hidePlayer();
    }
    
	private IAdService		mAdService;
	private DispatchAsync	mDispatcher;
	private IApplication	mApplication;
	private IAdBeanFactory	mAdBeanFactory;
	private String			mVASTTag;
	private PlayItem		mPlaying;
    
    @Inject
	public AdPresenter(EventBus pEventBus, AView pView, IAdService pAdService, DispatchAsync pDispatcher, IAdBeanFactory pAdBeanFactory) {
        super(pEventBus, pView);
		mAdService = pAdService;
		mDispatcher = pDispatcher;
		mAdBeanFactory = pAdBeanFactory;
		getView().setUiHandlers(this);
    }
    
    @Override
    public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
    	mLogger.log(Level.FINE, "Received ApplicationInitEvent.");
    	fetchAdsFromServlet();
		mLogger.log(Level.FINE, "PluginReadyEvent fired.");
    }
    
    @Override
    public void onApplicationLoadEvent(ApplicationLoadEvent pEvent) {
		super.onApplicationLoadEvent(pEvent);
    	mLogger.log(Level.FINE, "Received ApplicationLoadEvent...");
    	mApplication = pEvent.getApplication();
		IApplicationConfig oAppConfig = pEvent.getApplication().getConfig();
		if (oAppConfig.getPlugins().isNull() != null) {
			mLogger.log(Level.SEVERE, "No plugins found in configuration.");
			return;
		} else {
			mLogger.log(Level.FINE, "Plugins : " + oAppConfig.getPlugins());
		}
		JSONValue oConfig = oAppConfig.getPlugins().get("net.sf.video4j.gwt.plugins.ads.liverail");
		if (oConfig.isNull() != null) {
			mLogger.log(Level.SEVERE, "Missing plugin \"net.sf.video4j.gwt.plugins.ads.liverail\" from configuration. No ads will be played.");
			return;
		}
		BeanFactory<IAdBean, IAdBeanFactory> oFactory = new BeanFactory<IAdBean, IAdBeanFactory>(IAdBean.class, mAdBeanFactory);
		IAdBean oAdConfig = oFactory.makeFrom(oConfig.isObject());
		
		StringBuffer oVASTUrl = new StringBuffer("http://ad3.liverail.com/?");
		if (oAdConfig.getPublisherId() != null) oVASTUrl.append("&LR_PUBLISHER_ID=").append(oAdConfig.getPublisherId());
		if (oAdConfig.getAutoplay()) oVASTUrl.append("&LR_AUTOPLAY=1");
		if (oAdConfig.getAdUnit() != null) oVASTUrl.append("&LR_ADUNIT=").append(oAdConfig.getAdUnit());
		if (oAdConfig.getAdMessage() != null) oVASTUrl.append("&LR_LAYOUT_SKIN_MESSAGE=").append(URL.encodeQueryString(oAdConfig.getAdMessage()));
		if (oAdConfig.getTags() != null) oVASTUrl.append("&LR_TAGS=").append(URL.encodeQueryString(oAdConfig.getTags()));
		if (oAdConfig.getVerticals() != null) oVASTUrl.append("&LR_VERTICALS=").append(URL.encodeQueryString(oAdConfig.getVerticals()));
		if (oAdConfig.getVideoId() != null) oVASTUrl.append("&LR_VIDEO_ID=").append(oAdConfig.getVideoId());
		if (oAdConfig.getVideoPosition() != null) oVASTUrl.append("&LR_VIDEO_POSITION=").append(oAdConfig.getVideoPosition());
		
		mVASTTag = oVASTUrl.toString();
		mLogger.log(Level.INFO, "VAST Tag: " + mVASTTag);
    }
    
	@Override
	public void onApplicationReadyEvent(ApplicationReadyEvent pEvent) {
		// TODO Auto-generated method stub
	}

    private void fetchAdsFromAJAX() {
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
                    //.withVideoType(VideoType.MP4)
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

    private void fetchAdsFromServlet() {
        mLogger.log(Level.INFO, "Fetching Ads from servlet");
        FetchAdAction oAction = new FetchAdAction();
//		IAdBeanFactory oAdBeanFactory = GWT.create(IAdBeanFactory.class);
//		BeanFactory<IAdBean, IAdBeanFactory> oFactory = new BeanFactory<IAdBean, IAdBeanFactory>(IAdBean.class, oAdBeanFactory);
//		IAdBean oAdBean = oFactory.makeFrom(oConfig.getAd());
//		mLogger.log(Level.INFO, "oAdBean.getURL()=" + oAdBean.getURL());
//		oAction.setURL(oAdBean.getURL());
        
        oAction.setURL(mVASTTag);
		// oAction.setURL("http://ad3.liverail.com/?LR_PUBLISHER_ID=1331&LR_CAMPAIGN_ID=229&LR_SCHEMA=vast2");
        mDispatcher.execute(oAction, new AsyncCallbackImpl<FetchAdResult>() {

            @Override
            public void onSuccess(FetchAdResult pResult) {
				new PlaylistHelper(mApplication.getPlaylist()).addAds(pResult.getVAST().getAds());
                PluginReadyEvent.fire(AdPresenter.this, AdPresenter.this);
            }

        });
    }

    @Override
    public void onPlaylistPlayEvent(final PlaylistPlayEvent pEvent) {
		mLogger.log(Level.INFO, "Received Ad media in PlaylistPlayEvent.");
		if (!pEvent.getPlayItem().getMedia().isAd()) return;
		mLogger.log(Level.INFO, "Received Ad media in PlaylistPlayEvent. Playing item track #" + pEvent.getPlayItem().getMedia().getId() + "...");
		// TODO: need to pass start and end (e.g. for mid-rolls).
		PlayerParameters oParams = new PlayerParameters()
				// .withAutoPlay(pEvent.getPlayItem().isAutoPlay())
				.withAutoPlay(true)
				.withControls(false) // TODO: this should come from ApplicationConfig (?)
				.withHeightInPixels(360) // TODO: this should come from the ApplicationConfig
				.withWidthInPixels(640) // TODO: this should come from the ApplicationConfig
				.withMedia(pEvent.getPlayItem().getMedia());
		getView().startPlayer(oParams);
		mPlaying = pEvent.getPlayItem();
		mLogger.log(Level.INFO, "Item now playing (or loading...)");
    }

	@Override
	public void onPlaylistPlayEndedEvent(PlaylistPlayEndedEvent pEvent) {
		if (pEvent.getPlayItem().getMedia().isAd()) {
			mLogger.log(Level.INFO, "Ad media finished playing");
			getView().hidePlayer();
		}
	}

	@Override
	public String getPluginId() {
		return this.getClass().getName();
	}

	@Override
	protected PlayItem getPlayingItem() {
		return mPlaying;
	}

}