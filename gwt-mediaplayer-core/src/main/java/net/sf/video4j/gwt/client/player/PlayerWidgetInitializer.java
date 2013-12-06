package net.sf.video4j.gwt.client.player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.model.Source;
import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.VideoWidget.TypeSupport;

/**
 * @author Gustavo Matias
 */
public class PlayerWidgetInitializer implements IPlayerWidgetInitializer {

	private VideoWidget			mPlayerWidget;

	private PlayerParameters	mPlayerParameters;

	protected Logger			mLogger	= Logger.getLogger(this.getClass().getName());

	public PlayerWidgetInitializer(VideoWidget pPlayerWidget, PlayerParameters pPlayerParameters) {
		mPlayerWidget = pPlayerWidget;
		mPlayerParameters = pPlayerParameters;
	}

	public void start() {
		mPlayerWidget.setControls(mPlayerParameters.hasControls());
		mPlayerWidget.setAutoPlay(mPlayerParameters.isAutoPlay());
		mPlayerWidget.setPixelSize(mPlayerParameters.getWidthInPixels(), mPlayerParameters.getHeightInPixels());
		addSources();
	}

	private void addSources() {
		List<VideoType> oSupportedVideoTypes = new ArrayList<VideoType>();
		for (Source s : mPlayerParameters.getMedia().getSources()) {
			if (isNotAdMedia() || (isVideoTypeSupported(s) && isVideoTypeNotAlreadyAdded(oSupportedVideoTypes, s))) {
				oSupportedVideoTypes.add(VideoType.getByType(s.getType()));
				mPlayerWidget.addSource(new VideoSource(s.getURI(), VideoType.getByType(s.getType())));
				mLogger.log(Level.INFO, "Added new source do player=[" + mPlayerWidget + "]");
			}
		}
	}

	private boolean isNotAdMedia() {
		return !mPlayerParameters.getMedia().isAd();
	}

	private boolean isVideoTypeNotAlreadyAdded(List<VideoType> pSupportedVideoTypes, Source pSource) {
		return !pSupportedVideoTypes.contains(VideoType.getByType(pSource.getType()));
	}

	private boolean isVideoTypeSupported(Source s) {
		return TypeSupport.MAYBE.name().equals(mPlayerWidget.canPlayType(s.getType()).name());
	}

}