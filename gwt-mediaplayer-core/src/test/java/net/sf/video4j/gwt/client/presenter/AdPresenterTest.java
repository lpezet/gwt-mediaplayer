package net.sf.video4j.gwt.client.presenter;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.sf.video4j.gwt.client.event.PlaylistPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.PlayItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gwt.event.shared.EventBus;

/**
 * @author gumatias
 */
@RunWith(MockitoJUnitRunner.class)
public class AdPresenterTest {

	private AdPresenter			mPresenter;

	@Mock
	private AdPresenter.AView	mView;
	@Mock
	private EventBus			mEventBus;

	@Before
	public void setUp() throws Exception {
		mPresenter = new AdPresenter(mEventBus, mView, null, null, null);
	}

	@Test
	public void givenAdMedia_shouldPlay() {
		PlaylistPlayEvent oEvent = mock(PlaylistPlayEvent.class);

		Media oMedia = new Media();
		oMedia.setAd(true);
		when(oEvent.getPlayItem()).thenReturn(new PlayItem(oMedia));

		mPresenter.onPlaylistPlayEvent(oEvent);

		verify(mView).startPlayer(any(PlayerParameters.class));
	}

	@Test
	public void givenNonAdMedia_shouldNotPlay() {
		PlaylistPlayEvent oEvent = mock(PlaylistPlayEvent.class);

		Media oMedia = new Media();
		oMedia.setAd(false);
		when(oEvent.getPlayItem()).thenReturn(new PlayItem(oMedia));

		mPresenter.onPlaylistPlayEvent(oEvent);

		verify(mView, never()).startPlayer(any(PlayerParameters.class));
	}

	@Test
	public void givenAdMedia_whenPlaylistPlayEnded_shouldHidePlayer() {
		PlaylistPlayEndedEvent oEvent = mock(PlaylistPlayEndedEvent.class);

		Media oMedia = new Media();
		oMedia.setAd(true);
		when(oEvent.getPlayItem()).thenReturn(new PlayItem(oMedia));

		mPresenter.onPlaylistPlayEndedEvent(oEvent);

		verify(mView).hidePlayer();
	}

	@Test
	public void givenNonAdMedia_whenPlaylistPlayEnded_shouldHidePlayer() {
		PlaylistPlayEndedEvent oEvent = mock(PlaylistPlayEndedEvent.class);

		Media oMedia = new Media();
		oMedia.setAd(false);
		when(oEvent.getPlayItem()).thenReturn(new PlayItem(oMedia));

		mPresenter.onPlaylistPlayEndedEvent(oEvent);

		verify(mView, never()).startPlayer(any(PlayerParameters.class));
	}

}