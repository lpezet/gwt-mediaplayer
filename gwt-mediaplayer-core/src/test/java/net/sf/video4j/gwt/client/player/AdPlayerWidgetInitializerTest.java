package net.sf.video4j.gwt.client.player;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.model.Source;

import org.junit.Test;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.VideoWidget.TypeSupport;

/**
 * @author Gustavo Matias
 */
@GwtModule("net.sf.video4j.gwt.client.player.Player")
public class AdPlayerWidgetInitializerTest extends GwtTest {

	private IPlayerWidgetInitializer	mInitializer;

	@Test
	public void givenMediaWithNullSourceType_shouldNotAddSource() throws Exception {
		VideoWidget oVideoWidget = mock(VideoWidget.class);
		PlayerParameters oParams = new PlayerParameters();
		Media oMedia = new Media();
		oMedia.setAd(true);
		Source oSource = new Source();
		oMedia.setSources(Arrays.asList(oSource));
		oParams.withMedia(oMedia);
		mInitializer = new AdPlayerWidgetInitializer(oVideoWidget, oParams);

		when(oVideoWidget.canPlayType(anyString())).thenReturn(TypeSupport.NO);

		mInitializer.start();

		verify(oVideoWidget, never()).addSource(any(VideoSource.class));
	}

}