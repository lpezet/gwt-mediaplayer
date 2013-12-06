package net.sf.video4j.gwt.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.plugin.shared.vast.Ad;
import net.sf.video4j.gwt.plugin.shared.vast.InLine;
import net.sf.video4j.gwt.plugin.shared.vast.Linear;
import net.sf.video4j.gwt.plugin.shared.vast.MediaFile;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Gustavo Matias
 *
 */
public class PlaylistHelperTest {

	@Test
	public void givenAds_whenHasNoAdResult_shouldDoNothing() throws Exception {
		Playlist oPlaylist = new Playlist();
		IPlaylistHelper oHelper = new PlaylistHelper(oPlaylist);
		List<Ad> oAds = new ArrayList<Ad>();

		oHelper.addAds(oAds);

		assertThat(oPlaylist.count(true), equalTo(0));
	}

	@Test
	public void givenAds_whenHasSingleInLineAdResultWithNoCreatives_shouldDoNothing() throws Exception {
		Playlist oPlaylist = new Playlist();
		IPlaylistHelper oHelper = new PlaylistHelper(oPlaylist);

		List<Ad> oAds = new ArrayList<Ad>();
		InLine oInLineAd = new InLine();
		oAds.add(oInLineAd);

		oHelper.addAds(oAds);

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

		List<Ad> oAds = new ArrayList<Ad>();
		InLine oInLineAd = new InLine();
		Linear oLinear = new Linear();
		oInLineAd.getCreatives().add(oLinear);
		oAds.add(oInLineAd);

		oHelper.addAds(oAds);

		assertThat(oPlaylist.count(true), equalTo(2));
	}

	@Test
	public void givenFetchAdResult_whenHasSingleAdResult_shouldHaveOneAdInPlaylist() throws Exception {
		Playlist oPlaylist = new Playlist();
		oPlaylist.add(new Media()); // empty media to avoid NPE when calling mNavigator.peek()
		IPlaylistHelper oHelper = new PlaylistHelper(oPlaylist);

		List<Ad> oAds = new ArrayList<Ad>();
		InLine oInLineAd = new InLine();
		Linear oLinear = new Linear();
		MediaFile oMediaFile = new MediaFile();
		oMediaFile.setType("mp4");
		oMediaFile.setURI("http://the.uri/video.mp4");
		oMediaFile.setBitrate(123);
		oLinear.getMediaFiles().add(oMediaFile);
		oInLineAd.getCreatives().add(oLinear);
		oAds.add(oInLineAd);

		oHelper.addAds(oAds);

		// can we have a function in playlist that return tracks given the track number (index)?
		assertThat(oPlaylist.count(true), equalTo(2));
	}

}