package net.sf.video4j.gwt.client.player;

import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.model.Source;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.VideoWidget.TypeSupport;

/**
 * @author Gustavo Matias
 */
public class AdPlayerWidgetInitializer extends PlayerWidgetInitializer {

	public AdPlayerWidgetInitializer(VideoWidget pPlayerWidget, PlayerParameters pPlayerParameters) {
		super(pPlayerWidget, pPlayerParameters);
	}

	protected boolean isVideoSourceSupported(Source pSource) {
		return TypeSupport.MAYBE.name().equals(mPlayerWidget.canPlayType(pSource.getType()).name());
	}

}
