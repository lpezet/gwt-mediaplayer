package net.sf.video4j.gwt.client.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.model.Source;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.VideoWidget.TypeSupport;

/**
 * @author Gustavo Matias
 */
@GwtModule("net.sf.video4j.gwt.client.player.Player")
public class PlayerWidgetInitializerTest extends GwtTest {

	private IPlayerWidgetInitializer	mInitializer;

	@Test
	public void givenMediaWithMP4SourceType_shouldAddSource() throws Exception {
		VideoWidget oVideoWidget = mock(VideoWidget.class);
		PlayerParameters oParams = new PlayerParameters();
		Media oMedia = new Media();
		Source oSource = new Source();
		oSource.setType(VideoType.MP4.getType());
		String oURI = "http://uri.com/123.mp4";
		oSource.setURI(oURI);
		oMedia.setSources(Arrays.asList(oSource));
		oParams.withMedia(oMedia);
		mInitializer = new DummyPlayerWidgetInitializer(oVideoWidget, oParams);

		when(oVideoWidget.canPlayType(anyString())).thenReturn(TypeSupport.MAYBE);

		mInitializer.start();

		ArgumentCaptor<VideoSource> oVideoSourceArg = ArgumentCaptor.forClass(VideoSource.class);
		verify(oVideoWidget).addSource(oVideoSourceArg.capture());
		assertThat(oVideoSourceArg.getValue().getSrc(), equalTo(oURI));
		assertThat(oVideoSourceArg.getValue().getVideoType(), equalTo(VideoType.MP4));
	}

	class DummyPlayerWidgetInitializer extends PlayerWidgetInitializer {

		public DummyPlayerWidgetInitializer(VideoWidget pPlayerWidget, PlayerParameters pPlayerParameters) {
			super(pPlayerWidget, pPlayerParameters);
		}

		@Override
		protected boolean isVideoSourceSupported(Source pSource) {
			return true;
		}

	}

}