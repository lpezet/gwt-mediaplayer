/**
 * 
 */
package net.sf.video4j.gwt.client.demo;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.demo.VideoPlayerPreRollDemo;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootPanel;


import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.handlers.HasVideoHandlers;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;

/**
 * @author luc
 *
 */
public class VideoPlayerPreRollDemo implements EntryPoint {
	
	private Logger mLogger = Logger.getLogger(VideoPlayerPreRollDemo.class.getName());

	private static final String PRE_ROLL = "http://static.bouncingminds.com/ads/30secs/country_life_butter.mp4";
	private static final String VIDEO = "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4";
	
	static class OnceOnlyVideoEndedHandler implements VideoEndedHandler {
		
		private HandlerRegistration mRegistrationHandler;
		private VideoEndedHandler mHandler;
		
		public void register(HasVideoHandlers pRegistrar, VideoEndedHandler pHandler) {
			mRegistrationHandler = pRegistrar.addEndedHandler(this);
			mHandler = pHandler;
		}
		
		@Override
		public void onVideoEnded(VideoEndedEvent event) {
			mRegistrationHandler.removeHandler();
			mHandler.onVideoEnded(event);
		}
	}
	
	@Override
	public void onModuleLoad() {
		final VideoWidget videoPlayer = new VideoWidget();
		//false, true, "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4");
		videoPlayer.setControls(false);
		videoPlayer.setAutoPlay(true);
        videoPlayer.addSource(new VideoSource(PRE_ROLL, VideoType.MP4));
        videoPlayer.setPixelSize(500, 400);
        
        OnceOnlyVideoEndedHandler oHandler = new OnceOnlyVideoEndedHandler();
        oHandler.register(videoPlayer, new VideoEndedHandler() {
			@Override
			public void onVideoEnded(VideoEndedEvent event) {
				mLogger.log(Level.INFO, "Pre-roll done. Now playing videos...");
				videoPlayer.setSrc(VIDEO);
				videoPlayer.setControls(true);
				videoPlayer.playPause();
				
			}
		});
        RootPanel.get().add(videoPlayer);
	}
}
