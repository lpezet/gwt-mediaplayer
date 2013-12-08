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

/**
 * @author Gustavo Matias
 */
public abstract class PlayerWidgetInitializer implements IPlayerWidgetInitializer {

	protected Logger			mLogger	= Logger.getLogger(this.getClass().getName());

	protected VideoWidget		mPlayerWidget;

	protected PlayerParameters	mPlayerParameters;

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
			if (isVideoSourceSupported(s) && isVideoTypeNotAlreadyAdded(oSupportedVideoTypes, s)) {
				oSupportedVideoTypes.add(VideoType.getByType(s.getType()));
				mPlayerWidget.addSource(new VideoSource(s.getURI(), VideoType.getByType(s.getType())));
				mLogger.log(Level.INFO, "Added new source do player=[" + mPlayerWidget + "]");
			}
		}
	}

	protected abstract boolean isVideoSourceSupported(Source pSource);

	private boolean isVideoTypeNotAlreadyAdded(List<VideoType> pSupportedVideoTypes, Source pSource) {
		return !pSupportedVideoTypes.contains(VideoType.getByType(pSource.getType()));
	}

}