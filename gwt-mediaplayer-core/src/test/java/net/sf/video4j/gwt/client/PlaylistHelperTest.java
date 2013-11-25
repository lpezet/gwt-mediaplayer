package net.sf.video4j.gwt.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.plugin.shared.vast.InLine;
import net.sf.video4j.gwt.plugin.shared.vast.Linear;
import net.sf.video4j.gwt.plugin.shared.vast.MediaFile;
import net.sf.video4j.gwt.plugin.shared.vast.VAST;
import net.sf.video4j.gwt.shared.model.FetchAdResult;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Gustavo Matias
 *
 */
public class PlaylistHelperTest {

	@Test
	public void givenFetchAdResult_whenHasNoAdResult_shouldDoNothing() throws Exception {
		Playlist oPlaylist = new Playlist();
		IPlaylistHelper oHelper = new PlaylistHelper(oPlaylist);

		FetchAdResult oResult = new FetchAdResult(new VAST());

		oHelper.putAdsInPlaylist(oResult);

		assertThat(oPlaylist.count(true), equalTo(0));
	}

	@Test
	public void givenFetchAdResult_whenHasSingleInLineAdResultWithNoCreatives_shouldDoNothing() throws Exception {
		Playlist oPlaylist = new Playlist();
		IPlaylistHelper oHelper = new PlaylistHelper(oPlaylist);

		VAST oVAST = new VAST();
		InLine oInLineAd = new InLine();
		oVAST.getAds().add(oInLineAd);
		FetchAdResult oResult = new FetchAdResult(oVAST);

		oHelper.putAdsInPlaylist(oResult);

		assertThat(oPlaylist.count(true), equalTo(0));
	}

	/**
	 * Apparently this route hasn't been decided yet, think then test
	 */
	@Ignore
	@Test
	public void givenFetchAdResult_whenHasSingleAdResultWithNoMediaFiles_shouldAddMedia() throws Exception {
		Playlist oPlaylist = new Playlist();
		oPlaylist.add(new Media()); // empty media to avoid NPE when calling mNavigator.peek()
		IPlaylistHelper oHelper = new PlaylistHelper(oPlaylist);

		VAST oVAST = new VAST();
		InLine oInLineAd = new InLine();
		Linear oLinear = new Linear();
		oInLineAd.getCreatives().add(oLinear);
		oVAST.getAds().add(oInLineAd);
		FetchAdResult oResult = new FetchAdResult(oVAST);

		oHelper.putAdsInPlaylist(oResult);

		assertThat(oPlaylist.count(true), equalTo(2));
	}

	@Test
	public void givenFetchAdResult_whenHasSingleAdResult_shouldHaveOneAdInPlaylist() throws Exception {
		Playlist oPlaylist = new Playlist();
		oPlaylist.add(new Media()); // empty media to avoid NPE when calling mNavigator.peek()
		IPlaylistHelper oHelper = new PlaylistHelper(oPlaylist);

		VAST oVAST = new VAST();
		InLine oInLineAd = new InLine();
		Linear oLinear = new Linear();
		MediaFile oMediaFile = new MediaFile();
		oMediaFile.setType("mp4");
		oMediaFile.setURI("http://the.uri/video.mp4");
		oMediaFile.setBitrate(123);
		oLinear.getMediaFiles().add(oMediaFile);
		oInLineAd.getCreatives().add(oLinear);
		oVAST.getAds().add(oInLineAd);
		FetchAdResult oResult = new FetchAdResult(oVAST);

		oHelper.putAdsInPlaylist(oResult);

		// can we have a function in playlist that return tracks given the track number (index)?
		assertThat(oPlaylist.count(true), equalTo(2));
	}

}
