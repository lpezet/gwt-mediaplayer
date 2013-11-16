/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent.ApplicationInitHandler;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent.ApplicationLoadHandler;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent.ApplicationReadyHandler;
import net.sf.video4j.gwt.client.event.BandwidthStatusEvent;
import net.sf.video4j.gwt.client.event.PlayerPauseEvent;
import net.sf.video4j.gwt.client.event.PlayerPauseEvent.PlayerPauseHandler;
import net.sf.video4j.gwt.client.event.PlayerPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlayerPlayEndedEvent.PlayerPlayEndedHandler;
import net.sf.video4j.gwt.client.event.PlayerPlayingEvent;
import net.sf.video4j.gwt.client.event.PlayerPlayingEvent.PlayerPlayingHandler;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.model.IPlugin;

import com.google.gwt.core.client.Duration;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author luc
 *
 */
public class BandwidthController  extends BaseController implements 
	ApplicationLoadHandler,
	ApplicationInitHandler, 
	ApplicationReadyHandler, 
	PlayerPlayEndedHandler,
	PlayerPlayingHandler,
	PlayerPauseHandler,
	IPlugin {
	
	private static class ImageSize {
		private String mUrl;
		private int mSizeInBytes;
		public ImageSize(String pUrl, int pSizeInBytes) {
			mUrl = pUrl;
			mSizeInBytes = pSizeInBytes;
		}
		
		public int getSizeInBytes() {
			return mSizeInBytes;
		}
		
		public String getUrl() {
			return mUrl;
		}
	}
	
	private static final List<ImageSize> IMAGES = new ArrayList<ImageSize>();
	
	static {
		//TODO: couple of samples coming from same domain as video: configurable?
		IMAGES.add(new ImageSize("http://l.yimg.com/g/images/sohp_lens.jpg.v3", 86557));
	}
	
	private boolean mChecking;
	private Duration mDuration;
	private Timer mTimer;
	private ImageSize mCurrentSample;
	private Random mRandom;

	@Inject
	public BandwidthController(EventBus pEventBus, Random pRandom) {
		super(pEventBus);
		registerHandlers();
		mRandom = pRandom;
	}
	
	private void registerHandlers() {
		addRegisteredHandler(ApplicationLoadEvent.getType(), this);
		addRegisteredHandler(ApplicationReadyEvent.getType(), this);
		addRegisteredHandler(ApplicationInitEvent.getType(), this);
		addRegisteredHandler(PlayerPlayEndedEvent.getType(), this);
		addRegisteredHandler(PlayerPlayingEvent.getType(), this);
		addRegisteredHandler(PlayerPauseEvent.getType(), this);
	}

	@Override
	public String getPluginId() {
		return this.getClass().getName();
	}

	@Override
	public void onPlayerPlayingEvent(PlayerPlayingEvent pEvent) {
		if (mTimer == null) {
			mLogger.log(Level.INFO, "Creating timer...");
			mTimer = new Timer() {
				@Override
				public void run() {
					checkBandwidth();
				}
			};
			mTimer.scheduleRepeating(1000); // TODO: configurable
		}		
	}
	
	private void checkBandwidth() {
		if (mChecking) return;
		try {
			mLogger.log(Level.INFO, "Checking bandwidth...");
			mChecking = true;
			mCurrentSample = IMAGES.get(0); //TODO: randomize it
			final Image i = new Image(mCurrentSample.getUrl() + "?rnd=" + mRandom.nextInt(10000));
			i.setVisible(false);
			i.addLoadHandler(new LoadHandler() {
				@Override
				public void onLoad(LoadEvent pEvent) {
					loaded();
					RootPanel.get().remove(i);
				}
			});
			i.addAttachHandler(new AttachEvent.Handler() {
				@Override
				public void onAttachOrDetach(AttachEvent pEvent) {
					mLogger.log(Level.INFO, "Received AttachEvent: attached ? " + pEvent.isAttached());
					if (pEvent.isAttached()) mDuration = new Duration();
				}
			});
			i.addErrorHandler(new ErrorHandler() {				
				@Override
				public void onError(ErrorEvent pEvent) {
					mLogger.log(Level.SEVERE, "Error with image : " + pEvent.toDebugString());
				}
			});
			RootPanel.get().add(i);
		} catch (Throwable t) {
			mLogger.log(Level.SEVERE, "Unexpected error calculating bandwidth. Message: " + t.getMessage());
		}
	}
	
	private void loaded() {
		int oElapsedTimeInMillis = mDuration.elapsedMillis();
		double oBitsPerSecond = 8 * mCurrentSample.getSizeInBytes() / (oElapsedTimeInMillis / 1000.0);
		int oKbps = (int) Math.round(oBitsPerSecond / 1024);
		mCurrentSample = null;
		mDuration = null;
		mChecking = false;
		mLogger.log(Level.INFO, "Time to load image = " + oElapsedTimeInMillis + "ms : bandwidth = " + oKbps + "kbps.");
		BandwidthStatusEvent.fire(this, oKbps);
	}

	@Override
	public void onPlayerPlayEndedEvent(PlayerPlayEndedEvent pEvent) {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
	}

	@Override
	public void onApplicationReadyEvent(ApplicationReadyEvent pEvent) {
		mLogger.log(Level.FINE, "Received ApplicationReadyEvent.");
		checkBandwidth();
	}

	@Override
	public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
		mLogger.log(Level.FINE, "Received ApplicationInitEvent.");
		PluginReadyEvent.fire(this, this);
		mLogger.log(Level.FINE, "PluginReadyEvent fired.");
	}

	@Override
	public void onApplicationLoadEvent(ApplicationLoadEvent pEvent) {
		mLogger.log(Level.FINE, "Received ApplicationLoadEvent.");
		pEvent.getApplication().addPlugin(this);
	}
	
	@Override
	public void onPlayerPauseEvent(PlayerPauseEvent pEvent) {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
	}


}
