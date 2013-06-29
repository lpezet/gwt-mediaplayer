/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

/**
 * @author luc
 *
 */
public class VideoPlayerOverlayCloseDemo implements EntryPoint {
	
	private Logger mLogger = Logger.getLogger(VideoPlayerOverlayCloseDemo.class.getName());

	private static final String OVERLAY = "http://openx.openvideoads.org/openx-2.8.2/www/images/fa5b35e2e16d4b2a922e4169c9fcea97.gif";
	private static final String VIDEO = "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4";
	
	interface Binder extends UiBinder<HTMLPanel, VideoPlayerOverlayCloseDemo> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField Panel container;
	@UiField Panel videoContainer;
	@UiField Panel overlayContainer;
	
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
        
        addAdWithCloseButton();

		videoContainer.add(videoPlayer);    
	}

	private void addAdWithCloseButton() {
		final Panel oAdPanel = new HorizontalPanel();
        Image i = new Image(OVERLAY);
		Anchor oClose = new Anchor("close");
		oClose.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				oAdPanel.clear();
				oAdPanel.setVisible(false);
			}
		});
		oAdPanel.add(i);
		oAdPanel.add(oClose);
		overlayContainer.add(oAdPanel);
	}
}
