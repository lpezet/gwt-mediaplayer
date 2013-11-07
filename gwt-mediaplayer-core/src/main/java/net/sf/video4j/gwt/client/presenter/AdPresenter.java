package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.dispatch.AsyncCallbackImpl;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent.ApplicationInitHandler;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent.ApplicationLoadHandler;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent.ApplicationReadyHandler;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent.PlaylistPlayHandler;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.model.IAdBean;
import net.sf.video4j.gwt.client.model.IApplication;
import net.sf.video4j.gwt.client.model.IApplicationConfig;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.MediaType;
import net.sf.video4j.gwt.client.player.PlayItem;
import net.sf.video4j.gwt.client.player.PlaylistNavigator;
import net.sf.video4j.gwt.client.util.BeanFactory;
import net.sf.video4j.gwt.client.util.IAdBeanFactory;
import net.sf.video4j.gwt.plugin.client.vast.dao.IAdService;
import net.sf.video4j.gwt.plugin.shared.vast.Ad;
import net.sf.video4j.gwt.plugin.shared.vast.AdRequestCallback;
import net.sf.video4j.gwt.plugin.shared.vast.Creative;
import net.sf.video4j.gwt.plugin.shared.vast.InLine;
import net.sf.video4j.gwt.plugin.shared.vast.Linear;
import net.sf.video4j.gwt.plugin.shared.vast.MediaFile;
import net.sf.video4j.gwt.plugin.shared.vast.VAST;
import net.sf.video4j.gwt.shared.FetchAdAction;
import net.sf.video4j.gwt.shared.model.FetchAdResult;

import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import fr.hd3d.html5.video.client.VideoSource.VideoType;

/**
 * @author gumatias
 */
public class AdPresenter extends PresenterWidget<AdPresenter.AView> implements 
	IPlugin, 
	PlaylistPlayHandler,
	ApplicationLoadHandler,
	ApplicationInitHandler, 
	ApplicationReadyHandler {

    public interface AView extends View {
        void startPlayer(PlayerParameters pParams);
    }
    
    protected Logger mLogger = Logger.getLogger(this.getClass().getName());
    
	private IAdService		mAdService;
	private DispatchAsync	mDispatcher;
	private IApplication 	mApplication;
	private IAdBeanFactory	mAdBeanFactory;
	private String			mVASTTag;
    
    @Inject
	public AdPresenter(EventBus pEventBus, AView pView, IAdService pAdService, DispatchAsync pDispatcher, IAdBeanFactory pAdBeanFactory) {
        super(pEventBus, pView);
		mAdService = pAdService;
		mDispatcher = pDispatcher;
		mAdBeanFactory = pAdBeanFactory;
		registerHandlers();
        mLogger.log(Level.INFO, "Creating Ad Presenter");
    }
    
    private void registerHandlers() {
		addRegisteredHandler(ApplicationLoadEvent.getType(), this);
		addRegisteredHandler(ApplicationReadyEvent.getType(), this);
		addRegisteredHandler(ApplicationInitEvent.getType(), this);
		addRegisteredHandler(PlaylistPlayEvent.getType(), this);
	}
    
    @Override
    public String getPluginId() {
        return this.getClass().getName();
    }
    
    @Override
    protected void onBind() {
        super.onBind();
		// fetchAdsFromServlet();
        // fetchAdsFromAJAX();
    }
    
    @Override
    public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
    	mLogger.log(Level.FINE, "Received ApplicationInitEvent.");
    	fetchAdsFromServlet();
		mLogger.log(Level.FINE, "PluginReadyEvent fired.");
    }
    
    @Override
    public void onApplicationLoadEvent(ApplicationLoadEvent pEvent) {
    	mLogger.log(Level.FINE, "Received ApplicationLoadEvent...");
    	mApplication = pEvent.getApplication();
		pEvent.getApplication().addPlugin(this);
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
                
                if (pResult == null || pResult.getVAST().getAds() == null || pResult.getVAST().getAds().isEmpty()) {
                	mLogger.log(Level.INFO, "Got no Ads.");
                } else {
                	mLogger.log(Level.INFO, "Got " + pResult.getVAST().getAds().size() + " Ads.");
                	
                	MediaFile oFirstMP4MediaFile = getFirstMP4MediaFile(pResult.getVAST());
                	if (oFirstMP4MediaFile != null) {
                		Media oMedia = new Media();
						oMedia.setURI(oFirstMP4MediaFile.getURI());
						oMedia.setAd(true);
						oMedia.setType(MediaType.Video);
						PlaylistNavigator oNav = new PlaylistNavigator(mApplication.getPlaylist());
						// Doing pre-roll:
						PlayItem oFirstPlayItem = oNav.peek();
						Media oParent = oFirstPlayItem.getMedia();
						mApplication.getPlaylist().addChild(oMedia, oParent, 0);
                	}
	                
                }
                PluginReadyEvent.fire(AdPresenter.this, AdPresenter.this);
            }
            
            private MediaFile getFirstMP4MediaFile(VAST pVAST) {
            	for (Ad oAd : pVAST.getAds()) {
                	if (oAd instanceof InLine) {
                		InLine oInLine = (InLine) oAd;
                		if (oInLine.getCreatives() == null || oInLine.getCreatives().isEmpty()) {
                			mLogger.log(Level.SEVERE, "Got no creatives. Something wrong?");
                		} else {
                			for (Creative c : oInLine.getCreatives()) {
                				if (c instanceof Linear) {
                					Linear oLinearAd = (Linear) c;
                					if (oLinearAd.getMediaFiles() == null || oLinearAd.getMediaFiles().isEmpty()) {
                						mLogger.log(Level.SEVERE, "Got no media files from Linear Ad. Something wrong?");
                					} else {
                						for (MediaFile oMediaFile : oLinearAd.getMediaFiles()) {
                							if ("video/mp4".equalsIgnoreCase(oMediaFile.getType())) {
                								// use the first one as a test right now
                								mLogger.log(Level.INFO, "Using media file: type=" + oMediaFile.getType() + ", uri=" + oMediaFile.getURI() + ", bitrate=" + oMediaFile.getBitrate() + ", width=" + oMediaFile.getWidth() + ", height=" + oMediaFile.getHeight());
                								return oMediaFile;
                							}
                						}
                					}
                				}
                			}
                		}
                	}
                }
            	return null;
            }
        });
    }

    @Override
    public void onPlaylistPlayEvent(final PlaylistPlayEvent pEvent) {
        mLogger.log(Level.INFO, "Received PlaylistPlayEvent.");
    }
    
}