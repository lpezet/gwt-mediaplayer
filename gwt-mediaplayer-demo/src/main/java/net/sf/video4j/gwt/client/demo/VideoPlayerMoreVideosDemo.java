/**
 * 
 */
package net.sf.video4j.gwt.client.demo;

import java.util.logging.Logger;

import net.sf.video4j.gwt.client.demo.VideoPlayerMoreVideosDemo;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;


import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.handlers.HasVideoHandlers;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;

/**
 * 
 * @author gumatias
 *
 */
public class VideoPlayerMoreVideosDemo implements EntryPoint {
	
	private Logger mLogger = Logger.getLogger(VideoPlayerMoreVideosDemo.class.getName());

	private static final String THUMBNAIL_VIDEO1 = "http://i1.ytimg.com/vi/LdWbPJOrW4c/mqdefault.jpg";
	private static final String THUMBNAIL_VIDEO2 = "http://i1.ytimg.com/vi/-Do645Qvayo/mqdefault.jpg";
	private static final String VIDEO = "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4";
	
	interface Binder extends UiBinder<HTMLPanel, VideoPlayerMoreVideosDemo> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField Panel container;
	@UiField Panel videoContainer;
	@UiField Panel overlayContainer; 
	
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
		HTMLPanel outer = binder.createAndBindUi(this);
        RootPanel.get("player").add(outer);
        
		final VideoWidget videoPlayer = new VideoWidget();
		videoPlayer.setControls(true);
		videoPlayer.setAutoPlay(true); 
        videoPlayer.addSource(new VideoSource(VIDEO, VideoType.MP4));
        videoPlayer.setPixelSize(500, 400);
        
        OnceOnlyVideoEndedHandler oHandler = new OnceOnlyVideoEndedHandler();   
        oHandler.register(videoPlayer, new VideoEndedHandler() {
			@Override
			public void onVideoEnded(VideoEndedEvent event) {
				showMoreVideosScreen();
			}
		});
        videoContainer.add(videoPlayer);    
	}
	
	private void showMoreVideosScreen() {
		Panel oPanel = new HorizontalPanel();
        
		Anchor oAnchorVideo1 = new Anchor(); 
        Image oThumbnailVideo1 = new Image(THUMBNAIL_VIDEO1);
        oAnchorVideo1.setHref("/demo-preroll.html"); 
        oAnchorVideo1.getElement().appendChild(oThumbnailVideo1.getElement());
        oPanel.add(oAnchorVideo1);
        
        Anchor oAnchorVideo2 = new Anchor(); 
        Image oThumbnailVideo2 = new Image(THUMBNAIL_VIDEO2);
        oAnchorVideo2.setHref("/demo-postroll.html"); 
        oAnchorVideo2.getElement().appendChild(oThumbnailVideo2.getElement());
        oPanel.add(oAnchorVideo2);
        
        overlayContainer.add(oPanel);
	}
	
}
