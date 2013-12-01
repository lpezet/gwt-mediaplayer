package net.sf.video4j.gwt.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.model.Source;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.MediaType;
import net.sf.video4j.gwt.client.player.PlayItem;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.client.player.PlaylistNavigator;
import net.sf.video4j.gwt.plugin.shared.vast.Ad;
import net.sf.video4j.gwt.plugin.shared.vast.Creative;
import net.sf.video4j.gwt.plugin.shared.vast.InLine;
import net.sf.video4j.gwt.plugin.shared.vast.Linear;
import net.sf.video4j.gwt.plugin.shared.vast.MediaFile;
import net.sf.video4j.gwt.shared.model.FetchAdResult;

/**
 * @author Gustavo Matias
 */
public class PlaylistHelper implements IPlaylistHelper {

	protected Logger			mLogger	= Logger.getLogger(this.getClass().getName());

	private Playlist			mPlaylist;
	private PlaylistNavigator	mPlaylistNavigator;

	public PlaylistHelper(Playlist pPlaylist) {
		mPlaylist = pPlaylist;
		mPlaylistNavigator = new PlaylistNavigator(mPlaylist);
	}

	@Override
	public void putAdsInPlaylist(FetchAdResult pResult) {
		if (pResult == null || pResult.getVAST().getAds() == null || pResult.getVAST().getAds().isEmpty()) {
			mLogger.log(Level.INFO, "Got no Ads.");
		} else {
			mLogger.log(Level.INFO, "Got " + pResult.getVAST().getAds().size() + " Ads.");
			for (Ad oAd : pResult.getVAST().getAds()) {
				if (oAd instanceof InLine) {
					mLogger.log(Level.INFO, "Got InLine Ad");
					InLine oInLine = (InLine) oAd;
					if (oInLine.getCreatives() == null || oInLine.getCreatives().isEmpty()) {
						mLogger.log(Level.SEVERE, "Got no creatives. Something wrong?");
					} else {
						Media oMedia = new Media();
						oMedia.setAd(true);
						oMedia.setType(MediaType.Video);
						for (Creative c : oInLine.getCreatives()) {
							if (c instanceof Linear) {
								mLogger.log(Level.INFO, "Got InLine Linear Ad");
								Linear oLinearAd = (Linear) c;
								if (oLinearAd.getMediaFiles() == null || oLinearAd.getMediaFiles().isEmpty()) {
									mLogger.log(Level.SEVERE, "Got no media files from Linear Ad. Something wrong?");
								} else {
									mLogger.log(Level.INFO, "Got " + oLinearAd.getMediaFiles().size() + " media files");
									for (MediaFile oMediaFile : oLinearAd.getMediaFiles()) {
										Source oSource = newSource(oMediaFile);
										oMedia.getSources().add(oSource);
										/*
										 * if (pMediaType.equalsIgnoreCase(oMediaFile.getType())) {
										 * // use the first one as a test right now
										 * mLogger.log(Level.INFO, "Using media file: type=" +
										 * oMediaFile.getType() + ", uri=" + oMediaFile.getURI() +
										 * ", bitrate=" + oMediaFile.getBitrate() + ", width=" +
										 * oMediaFile.getWidth() + ", height=" +
										 * oMediaFile.getHeight());
										 * return oMediaFile;
										 * }
										 */
									}
									break;
								}
							}
						}
						// Doing pre-roll:
						PlayItem oFirstPlayItem = mPlaylistNavigator.peek();
						Media oParent = oFirstPlayItem.getMedia();
						mPlaylist.addChild(oMedia, oParent, 0);
						break;
					}
				}
			}
		}
	}

	private Source newSource(MediaFile pMediaFile) {
		Source s = new Source();
		s.setType(pMediaFile.getType());
		s.setURI(pMediaFile.getURI());
		s.setBitrate(pMediaFile.getBitrate());
		return s;
	}

}
