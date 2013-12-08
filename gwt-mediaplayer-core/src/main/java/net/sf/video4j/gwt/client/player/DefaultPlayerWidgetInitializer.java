package net.sf.video4j.gwt.client.player;

import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.model.Source;
import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author Gustavo Matias
 */
public class DefaultPlayerWidgetInitializer extends PlayerWidgetInitializer {

	public DefaultPlayerWidgetInitializer(VideoWidget pPlayerWidget, PlayerParameters pPlayerParameters) {
		super(pPlayerWidget, pPlayerParameters);
	}

	protected boolean isVideoSourceSupported(Source pSource) {
		return true;
	}

}