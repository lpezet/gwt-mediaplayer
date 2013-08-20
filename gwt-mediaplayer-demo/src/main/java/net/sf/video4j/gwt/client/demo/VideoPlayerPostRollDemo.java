/**
 * 
 */
package net.sf.video4j.gwt.client.demo;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.demo.VideoPlayerPostRollDemo;


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
 * TODO: figure out how to remove post-roll and re-enable controls
 * 
 * @author luc
 *
 */
public class VideoPlayerPostRollDemo implements EntryPoint {
	
	private Logger mLogger = Logger.getLogger(VideoPlayerPostRollDemo.class.getName());

	private static final String POST_ROLL = "http://static.bouncingminds.com/ads/30secs/country_life_butter.mp4";
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
		final VideoWidget oVideoPlayer = new VideoWidget();
		oVideoPlayer.setControls(true);
		oVideoPlayer.setAutoPlay(true);
        oVideoPlayer.addSource(new VideoSource(VIDEO, VideoType.MP4));
        oVideoPlayer.setPixelSize(500, 400);
        
        OnceOnlyVideoEndedHandler oHandler = new OnceOnlyVideoEndedHandler();
        oHandler.register(oVideoPlayer, new VideoEndedHandler() {
			@Override
			public void onVideoEnded(VideoEndedEvent event) {
				mLogger.log(Level.INFO, "Video done. Now playing post-roll...");
				oVideoPlayer.setSrc(POST_ROLL);
				oVideoPlayer.setControls(false);
				OnceOnlyVideoEndedHandler oSnapshot = new OnceOnlyVideoEndedHandler();
				oSnapshot.register(oVideoPlayer, new VideoEndedHandler() {
					
					@Override
					public void onVideoEnded(VideoEndedEvent event) {
						oVideoPlayer.setPoster("http://i.tripfilmsws.com/t/500x400/tripfilms_snapshots/720p/D93A130B1BC3E02EB7AB99812EFB8C00_0002.jpg");
						oVideoPlayer.setAutoPlay(false);
						oVideoPlayer.setSrc(VIDEO);
						oVideoPlayer.setControls(true);
					}
				});
				oVideoPlayer.playPause();
				
			}
		});
        RootPanel.get("player").add(oVideoPlayer);
	}
}
