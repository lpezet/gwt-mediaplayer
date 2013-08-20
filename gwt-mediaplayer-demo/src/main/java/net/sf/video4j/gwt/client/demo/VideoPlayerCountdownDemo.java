/**
 * 
 */
package net.sf.video4j.gwt.client.demo;

import java.util.logging.Logger;

import net.sf.video4j.gwt.client.demo.VideoPlayerCountdownDemo;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;


import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

/**
 * @author luc
 *
 */
public class VideoPlayerCountdownDemo implements EntryPoint {
	
	private Logger mLogger = Logger.getLogger(VideoPlayerCountdownDemo.class.getName());

	private static final String VIDEO = "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4";
	
	interface Binder extends UiBinder<HTMLPanel, VideoPlayerCountdownDemo> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField Panel container;
	@UiField Panel videoContainer;
	@UiField SpanElement videoAdCountdown;
	
	@Override
	public void onModuleLoad() {
		HTMLPanel outer = binder.createAndBindUi(this);
        RootPanel.get("player").add(outer);
		//RootPanel.get().add(container);
        
		final VideoWidget videoPlayer = new VideoWidget();
		//false, true, "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4");
		videoPlayer.setControls(true);
		videoPlayer.setAutoPlay(true);
        videoPlayer.addSource(new VideoSource(VIDEO, VideoType.MP4));
        videoPlayer.setPixelSize(500, 400);
        
        videoPlayer.addTimeUpdateHandler(new VideoTimeUpdateHandler() {
			
			@Override
			public void onTimeUpdated(VideoTimeUpdateEvent event) {
				videoAdCountdown.setInnerText("Video will end in " + Math.round(event.getDuration() - event.getCurrentTime()) + "s.");
			}
		});
        videoContainer.add(videoPlayer);    
        
	}
}
