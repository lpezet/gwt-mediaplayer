package fr.hd3d.html5.video.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.dom.VideoElement;
import fr.hd3d.html5.video.client.dom.VideoSourceElement;
import fr.hd3d.html5.video.client.events.VideoAbortEvent;
import fr.hd3d.html5.video.client.events.VideoCanPlayEvent;
import fr.hd3d.html5.video.client.events.VideoCanPlayThroughEvent;
import fr.hd3d.html5.video.client.events.VideoCuePointEvent;
import fr.hd3d.html5.video.client.events.VideoDurationChangeEvent;
import fr.hd3d.html5.video.client.events.VideoEmptyEvent;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.events.VideoErrorEvent;
import fr.hd3d.html5.video.client.events.VideoLoadDataEvent;
import fr.hd3d.html5.video.client.events.VideoLoadMetadataEvent;
import fr.hd3d.html5.video.client.events.VideoLoadStartEvent;
import fr.hd3d.html5.video.client.events.VideoPauseEvent;
import fr.hd3d.html5.video.client.events.VideoPlayEvent;
import fr.hd3d.html5.video.client.events.VideoPlayingEvent;
import fr.hd3d.html5.video.client.events.VideoProgressEvent;
import fr.hd3d.html5.video.client.events.VideoRateChangeEvent;
import fr.hd3d.html5.video.client.events.VideoSeekedEvent;
import fr.hd3d.html5.video.client.events.VideoSeekingEvent;
import fr.hd3d.html5.video.client.events.VideoStalledEvent;
import fr.hd3d.html5.video.client.events.VideoSuspendEvent;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.events.VideoVolumeChangeEvent;
import fr.hd3d.html5.video.client.events.VideoWaitingEvent;
import fr.hd3d.html5.video.client.handlers.VideoAbortHandler;
import fr.hd3d.html5.video.client.handlers.VideoCanPlayHandler;
import fr.hd3d.html5.video.client.handlers.VideoCanPlayThroughHandler;
import fr.hd3d.html5.video.client.handlers.VideoCuePointHandler;
import fr.hd3d.html5.video.client.handlers.VideoDurationChangeHandler;
import fr.hd3d.html5.video.client.handlers.VideoEmptyHandler;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;
import fr.hd3d.html5.video.client.handlers.VideoErrorHandler;
import fr.hd3d.html5.video.client.handlers.VideoLoadDataHandler;
import fr.hd3d.html5.video.client.handlers.VideoLoadMetadataHandler;
import fr.hd3d.html5.video.client.handlers.VideoLoadStartHandler;
import fr.hd3d.html5.video.client.handlers.VideoPauseHandler;
import fr.hd3d.html5.video.client.handlers.VideoPlayHandler;
import fr.hd3d.html5.video.client.handlers.VideoPlayingHandler;
import fr.hd3d.html5.video.client.handlers.VideoProgressHandler;
import fr.hd3d.html5.video.client.handlers.VideoRateChangeHandler;
import fr.hd3d.html5.video.client.handlers.VideoSeekedHandler;
import fr.hd3d.html5.video.client.handlers.VideoSeekingHandler;
import fr.hd3d.html5.video.client.handlers.VideoStalledHandler;
import fr.hd3d.html5.video.client.handlers.VideoSuspendHandler;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;
import fr.hd3d.html5.video.client.handlers.VideoVolumeChangeHandler;
import fr.hd3d.html5.video.client.handlers.VideoWaitingHandler;


/**
 * A standard HTML5 video widget
 * 
 * @author michael.guiral
 * 
 */
public class VideoWidget extends Widget implements IVideoPlayer
{
    // private static final String UNSUPPORTED_VIDEO_TAG =
    // "Sorry, your browser does not support the &lt;video&gt; element.";
    private VideoElement videoElement;
    // private Element unsupportedElement;
    private HandlerManager videoHandlerManager;

    public enum TypeSupport
    {
        NO, PROBABLY, MAYBE;
    }
    
    private Map<String, Boolean> mRegisteredHandlers = new HashMap<String, Boolean>();

    /**
     * Create a default video HTML tag <br/>
     * <br/>
     * Default values<br/>
     * <li>autoPlay : false</li> <li>controls : false</li> <li>poster : null</li>
     */
    public VideoWidget()
    {
        this(false, false, null);
    }

    /**
     * @param autoPlay
     *            <b>true</b> if you want the user agent automatically begin playback of the media resource as soon as
     *            it can do so without stopping. <b>false</b> otherwise
     * @param controls
     *            - <b>false</b> if you want to have custom scripted controller, <b>true</b> if you would like the user
     *            agent to provide its own set of controls.
     * @param poster
     *            - <b>The image file address</b> that the user agent can show while no video data is available
     */
    public VideoWidget(boolean autoPlay, boolean controls, String poster)
    {
        super();
        videoElement = VideoElement.as(DOM.createElement(VideoElement.TAG));
        this.videoHandlerManager = new HandlerManager(this);
        setDefaultPlaybackRate(1);
        setElement(videoElement);
        // addUnsupportedMessage();
        setAutoPlay(autoPlay);
        setControls(controls);
        setPoster(poster);
    }

	/* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#setPoster(java.lang.String)
	 */
    @Override
	public void setPoster(String poster)
    {
        if (poster != null)
        {
            videoElement.setPoster(poster);
        }
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#getPoster()
	 */
    @Override
	public String getPoster()
    {
        return videoElement.getPoster();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#setAutoPlay(java.lang.Boolean)
	 */
    @Override
	public void setAutoPlay(Boolean autoPlay)
    {
        if (autoPlay == null)
        {
            throw new IllegalArgumentException("autoplay must not be null");
        }
        videoElement.setAutoPlay(autoPlay);
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#setControls(java.lang.Boolean)
	 */
    @Override
	public void setControls(Boolean controls)
    {
        if (controls == null)
        {
            throw new IllegalArgumentException("controls must not be null");
        }
        videoElement.setControls(controls);
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#isAutoPlay()
	 */
    @Override
	public boolean isAutoPlay()
    {
        return videoElement.isAutoPlay();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#isControls()
	 */
    @Override
	public boolean isControls()
    {
        return videoElement.isControls();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#setSources(java.util.List)
	 */
    @Override
	public void setSources(List<VideoSource> sources) {
    	NodeList<Node> oChildren = videoElement.getChildNodes();
    	for (int i = 0; i < oChildren.getLength(); i++) {
    		Node n = oChildren.getItem(i);
    		if (VideoSourceElement.TAG.equalsIgnoreCase(n.getNodeName())) videoElement.removeChild(n); 
    	}
    	
    	for (VideoSource videoSource : sources) {
            addSource(videoSource);
        }
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addSource(java.lang.String)
	 */
    @Override
	public void addSource(String src)
    {
        addSource(new VideoSource(src));
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addSource(fr.hd3d.html5.video.client.VideoSource)
	 */
    @Override
	public void addSource(VideoSource videoSource) {
        VideoSourceElement sourceElement = VideoSourceElement.as(DOM.createElement(VideoSourceElement.TAG));
        if (videoSource.getSrc() == null)
        {
            throw new IllegalArgumentException("src must not be null");
        }
        sourceElement.setSrc(videoSource.getSrc());
        if (videoSource.getVideoType() != null)
        {
            sourceElement.setType(videoSource.getVideoType().getType());
        }
        if (VideoType.WEBM.equals(videoSource.getVideoType()))
        {
            videoElement.insertFirst(sourceElement);
        }
        else
        {
            videoElement.appendChild(sourceElement);
        }
    }

    // /**
    // * Add a message that be show if the user agent can display HTML5 video tag
    // */
    // private void addUnsupportedMessage()
    // {
    // unsupportedElement = DOM.createElement("p");
    // unsupportedElement.setInnerHTML(UNSUPPORTED_VIDEO_TAG);
    // videoElement.appendChild(unsupportedElement);
    // }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#playPause()
	 */
    @Override
	public void playPause()
    {
        videoElement.playPause();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#isPaused()
	 */
    @Override
	public boolean isPaused()
    {
        return videoElement.isPaused();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#isPlayed()
	 */
    @Override
	public boolean isPlayed()
    {
        return videoElement.isPlayed();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#isSeeking()
	 */
    @Override
	public boolean isSeeking()
    {
        return videoElement.isSeeking();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#isSeekable(double)
	 */
    @Override
	public boolean isSeekable(double time)
    {
        return videoElement.isSeekable(time);
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#setCurrentTime(double)
	 */
    @Override
	public void setCurrentTime(double currentTime)
    {
        videoElement.setCurrentTime(currentTime);
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#getCurrentTime()
	 */
    @Override
	public double getCurrentTime()
    {
        Object object = videoElement.getCurrentTime();
        return videoElement.getCurrentTime();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#getInitialTime()
	 */
    @Override
	public double getInitialTime()
    {
        return videoElement.getInitialTime();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#getDuration()
	 */
    @Override
	public double getDuration()
    {
        double duration = videoElement.getDuration();
        if (Double.isNaN(duration))
        {
            throw new NumberFormatException("The video duration isn't available");
        }
        else if (Double.isInfinite(duration))
        {
            duration = -1;
        }
        return duration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#isEnded()
	 */
    @Override
	public boolean isEnded()
    {
        return videoElement.isEnded();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#setDefaultPlaybackRate(double)
	 */
    @Override
	public void setDefaultPlaybackRate(double defaultPlaybackRate)
    {
        videoElement.setDefaultPlaybackRate(defaultPlaybackRate);
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#getDefaultPlaybackRate()
	 */
    @Override
	public double getDefaultPlaybackRate()
    {
        return videoElement.getDefaultPlaybackRate();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#setPlaybackRate(double)
	 */
    @Override
	public void setPlaybackRate(double playbackRate)
    {
        videoElement.setPlaybackRate(playbackRate);
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#getPlaybackRate()
	 */
    @Override
	public double getPlaybackRate()
    {
        return videoElement.getPlaybackRate();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#getBufferedEndTime()
	 */
    @Override
	public double getBufferedEndTime()
    {
        return videoElement.getBufferedEndTime();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#getCurrentSrc()
	 */
    @Override
	public String getCurrentSrc()
    {
        return videoElement.getCurrentSrc();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#canPlayType(java.lang.String)
	 */
    @Override
	public TypeSupport canPlayType(String videoType)
    {
        String canPlayType = videoElement.canPlayType(videoType);
        TypeSupport typeSupport = TypeSupport.NO;
        try
        {
            typeSupport = TypeSupport.valueOf(canPlayType.toUpperCase());
        }
        catch (Exception e)
        {}
        return typeSupport;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#fireEvent(java.lang.Object)
	 */
    @Override
	public void fireEvent(Object event)
    {
        if (event instanceof GwtEvent<?>)
        {
            GwtEvent<?> gwtEvent = (GwtEvent<?>) event;
            if (videoHandlerManager != null)
            {
                videoHandlerManager.fireEvent(gwtEvent);
            }
        }
    }

    /**
     * Handlers
     */
    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addAbortHandler(fr.hd3d.html5.video.client.handlers.VideoAbortHandler)
	 */
    @Override
    public HandlerRegistration addAbortHandler(VideoAbortHandler abortHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoAbortEvent.getType(),
                abortHandler);
        if (mRegisteredHandlers.get("abortHandler") == null) {
        	addAbortEventHandler();
        	mRegisteredHandlers.put("abortHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addCanPlayHandler(fr.hd3d.html5.video.client.handlers.VideoCanPlayHandler)
	 */
    @Override
    public HandlerRegistration addCanPlayHandler(VideoCanPlayHandler canPlayHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoCanPlayEvent.getType(),
                canPlayHandler);
        if (mRegisteredHandlers.get("canPlayHandler") == null) {
        	addCanPlayEventHandler();
        	mRegisteredHandlers.put("canPlayHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addCanPlayThroughHandler(fr.hd3d.html5.video.client.handlers.VideoCanPlayThroughHandler)
	 */
    @Override
    public HandlerRegistration addCanPlayThroughHandler(VideoCanPlayThroughHandler canPlayThroughHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoCanPlayThroughEvent.getType(),
                canPlayThroughHandler);
        if (mRegisteredHandlers.get("canPlayThroughHandler") == null) {
        	addCanPlayThroughEventHandler();
        	mRegisteredHandlers.put("canPlayThroughHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addDurationChangeHandler(fr.hd3d.html5.video.client.handlers.VideoDurationChangeHandler)
	 */
    @Override
    public HandlerRegistration addDurationChangeHandler(VideoDurationChangeHandler durationChangeHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoDurationChangeEvent.getType(),
                durationChangeHandler);
        if (mRegisteredHandlers.get("durationChangeHandler") == null) {
        	addDurationChangeEventHandler();
        	mRegisteredHandlers.put("durationChangeHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addEmptyHandler(fr.hd3d.html5.video.client.handlers.VideoEmptyHandler)
	 */
    @Override
    public HandlerRegistration addEmptyHandler(VideoEmptyHandler emptyHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoEmptyEvent.getType(),
                emptyHandler);
        if (mRegisteredHandlers.get("emptyHandler") == null) {
        	addEmptyEventHandler();
        	mRegisteredHandlers.put("emptyHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addEndedHandler(fr.hd3d.html5.video.client.handlers.VideoEndedHandler)
	 */
    @Override
    public HandlerRegistration addEndedHandler(VideoEndedHandler endedHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoEndedEvent.getType(),
                endedHandler);
        if (mRegisteredHandlers.get("endedHandler") == null) {
        	addEndedEventHandler();
        	mRegisteredHandlers.put("endedHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addErrorHandler(fr.hd3d.html5.video.client.handlers.VideoErrorHandler)
	 */
    @Override
    public HandlerRegistration addErrorHandler(VideoErrorHandler errorHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoErrorEvent.getType(),
                errorHandler);
        if (mRegisteredHandlers.get("errorHandler") == null) {
        	addErrorEventHandler();
        	mRegisteredHandlers.put("errorHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addLoadDataHandler(fr.hd3d.html5.video.client.handlers.VideoLoadDataHandler)
	 */
    @Override
    public HandlerRegistration addLoadDataHandler(VideoLoadDataHandler loadDataHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoLoadDataEvent.getType(),
                loadDataHandler);
        if (mRegisteredHandlers.get("loadDataHandler") == null) {
        	addLoadDataEventHandler();
        	mRegisteredHandlers.put("loadDataHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addLoadMetadataHandler(fr.hd3d.html5.video.client.handlers.VideoLoadMetadataHandler)
	 */
    @Override
    public HandlerRegistration addLoadMetadataHandler(VideoLoadMetadataHandler loadMetadataHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoLoadMetadataEvent.getType(),
                loadMetadataHandler);
        if (mRegisteredHandlers.get("loadMetadataHandler") == null) {
        	addLoadMetadataEventHandler();
        	mRegisteredHandlers.put("loadMetadataHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addLoadStartHandler(fr.hd3d.html5.video.client.handlers.VideoLoadStartHandler)
	 */
    @Override
    public HandlerRegistration addLoadStartHandler(VideoLoadStartHandler loadStartHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoLoadStartEvent.getType(),
                loadStartHandler);
        if (mRegisteredHandlers.get("loadStartHandler") == null) {
        	addLoadStartEventHandler();
        	mRegisteredHandlers.put("loadStartHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addPauseHanlder(fr.hd3d.html5.video.client.handlers.VideoPauseHandler)
	 */
    @Override
    public HandlerRegistration addPauseHanlder(VideoPauseHandler pauseHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoPauseEvent.getType(),
                pauseHandler);
        if (mRegisteredHandlers.get("pauseHandler") == null) {
        	addPauseEventHandler();
        	mRegisteredHandlers.put("pauseHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addPlayHandler(fr.hd3d.html5.video.client.handlers.VideoPlayHandler)
	 */
    @Override
    public HandlerRegistration addPlayHandler(VideoPlayHandler playHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoPlayEvent.getType(), playHandler);
        if (mRegisteredHandlers.get("playHandler") == null) {
        	addPlayEventHandler();
        	mRegisteredHandlers.put("playHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addPlayingHandler(fr.hd3d.html5.video.client.handlers.VideoPlayingHandler)
	 */
    @Override
    public HandlerRegistration addPlayingHandler(VideoPlayingHandler playingHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoPlayingEvent.getType(),
                playingHandler);
        if (mRegisteredHandlers.get("playingHandler") == null) {
        	addPlayingEventHandler();
        	mRegisteredHandlers.put("playingHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addProgressHandler(fr.hd3d.html5.video.client.handlers.VideoProgressHandler)
	 */
    @Override
    public HandlerRegistration addProgressHandler(VideoProgressHandler progressHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoProgressEvent.getType(),
                progressHandler);
        if (mRegisteredHandlers.get("progressHandler") == null) {
        	addProgressEventHandler();
        	mRegisteredHandlers.put("progressHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addRateChangeHandler(fr.hd3d.html5.video.client.handlers.VideoRateChangeHandler)
	 */
    @Override
    public HandlerRegistration addRateChangeHandler(VideoRateChangeHandler rateChangeHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoRateChangeEvent.getType(),
                rateChangeHandler);
        if (mRegisteredHandlers.get("rateChangeHandler") == null) {
        	addRateChangeEventHandler();
        	mRegisteredHandlers.put("rateChangeHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addSeekedHandler(fr.hd3d.html5.video.client.handlers.VideoSeekedHandler)
	 */
    @Override
    public HandlerRegistration addSeekedHandler(VideoSeekedHandler seekedHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoSeekedEvent.getType(),
                seekedHandler);
        if (mRegisteredHandlers.get("seekedHandler") == null) {
        	addSeekedEventHandler();
        	mRegisteredHandlers.put("seekedHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addSeekingHandler(fr.hd3d.html5.video.client.handlers.VideoSeekingHandler)
	 */
    @Override
    public HandlerRegistration addSeekingHandler(VideoSeekingHandler seekingHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoSeekingEvent.getType(),
                seekingHandler);
        if (mRegisteredHandlers.get("seekingHandler") == null) {
        	addSeekingEventHandler();
        	mRegisteredHandlers.put("seekingHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addStalledHandler(fr.hd3d.html5.video.client.handlers.VideoStalledHandler)
	 */
    @Override
    public HandlerRegistration addStalledHandler(VideoStalledHandler stalledHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoStalledEvent.getType(),
                stalledHandler);
        if (mRegisteredHandlers.get("stalledHandler") == null) {
        	addStalledEventHandler();
        	mRegisteredHandlers.put("stalledHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addSuspendHandler(fr.hd3d.html5.video.client.handlers.VideoSuspendHandler)
	 */
    @Override
    public HandlerRegistration addSuspendHandler(VideoSuspendHandler suspendHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoSuspendEvent.getType(),
                suspendHandler);
        if (mRegisteredHandlers.get("suspendHandler") == null) {
        	addSuspendEventHandler();
        	mRegisteredHandlers.put("suspendHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addTimeUpdateHandler(fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler)
	 */
    @Override
    public HandlerRegistration addTimeUpdateHandler(VideoTimeUpdateHandler timeUpdateHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoTimeUpdateEvent.getType(),
                timeUpdateHandler);
        if (mRegisteredHandlers.get("timeUpdateHandler") == null) {
        	addTimeUpdateEventHandler();
        	mRegisteredHandlers.put("timeUpdateHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addVolumeChangeHandler(fr.hd3d.html5.video.client.handlers.VideoVolumeChangeHandler)
	 */
    @Override
    public HandlerRegistration addVolumeChangeHandler(VideoVolumeChangeHandler volumeChangeHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoVolumeChangeEvent.getType(),
                volumeChangeHandler);
        if (mRegisteredHandlers.get("volumeChangeHandler") == null) {
        	addVolumeChangeEventHandler();
        	mRegisteredHandlers.put("volumeChangeHandler", true);
        }
        return handlerRegistration;
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#addWaitingHandler(fr.hd3d.html5.video.client.handlers.VideoWaitingHandler)
	 */
    @Override
    public HandlerRegistration addWaitingHandler(VideoWaitingHandler waitingHandler)
    {
        HandlerRegistration handlerRegistration = videoHandlerManager.addHandler(VideoWaitingEvent.getType(),
                waitingHandler);
        if (mRegisteredHandlers.get("waitingHandler") == null) {
        	addWaitingEventHandler();
        	mRegisteredHandlers.put("waitingHandler", true);
        }
        return handlerRegistration;
    }
    
    @Override
    public HandlerRegistration addCuePointHandler(VideoCuePointHandler pHandler) {
    	HandlerRegistration oRegistration = videoHandlerManager.addHandler(VideoCuePointEvent.getType(), pHandler);
    	return oRegistration;
    }

    /**
     * JNI for event handlers
     */
    private final native void addAbortEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'abort',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoAbortEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addCanPlayEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'canplay',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoCanPlayEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addCanPlayThroughEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'canplaythrough',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoCanPlayThroughEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addDurationChangeEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'durationchange',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoDurationChangeEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addEmptyEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'emptied',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoEmptyEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addEndedEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'ended',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoEndedEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addErrorEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'error',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoErrorEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addLoadDataEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'loadeddata',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoLoadDataEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addLoadMetadataEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'loadedmetadata',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoLoadMetadataEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addLoadStartEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'loadstart',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoLoadStartEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addPauseEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'pause',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoPauseEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addPlayEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'play',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoPlayEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addPlayingEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'playing',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoPlayingEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addProgressEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'progress',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoProgressEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addRateChangeEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'ratechange',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoRateChangeEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addSeekedEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'seeked',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoSeekedEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addSeekingEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'seeking',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoSeekingEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addStalledEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'stalled',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoStalledEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addSuspendEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'suspend',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoSuspendEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addTimeUpdateEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'timeupdate',
						function() {
						    var currentTime = videoElement.currentTime;
						    var duration = videoElement.duration;
							var event = @fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent::new()();
							event.@fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent::setCurrentTime(D)(currentTime);
							event.@fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent::setDuration(D)(duration);
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addVolumeChangeEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'volumechange',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoVolumeChangeEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;

    private final native void addWaitingEventHandler() /*-{
		var videoElement = this.@fr.hd3d.html5.video.client.VideoWidget::videoElement;
		var videoWidget = this;
		videoElement
				.addEventListener(
						'waiting',
						function() {
							var event = @fr.hd3d.html5.video.client.events.VideoWaitingEvent::new()();
							videoWidget.@fr.hd3d.html5.video.client.VideoWidget::fireEvent(Ljava/lang/Object;)(event);
						}, true);
    }-*/;
    
    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#load()
	 */
    @Override
	public void load()
    {
        this.videoElement.load();
    }

    /* (non-Javadoc)
	 * @see fr.hd3d.html5.video.client.IVideoPlayer#setSrc(java.lang.String)
	 */
    @Override
	public void setSrc(String src)
    {
        this.videoElement.setSrc(src);
    }
}
