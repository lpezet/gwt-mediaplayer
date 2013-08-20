/**
 * 
 */
package net.sf.video4j.gwt.client.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.video4j.gwt.client.player.Playlist;

/**
 * @author luc
 *
 */
public class ApplicationConfig {

	private Playlist mPlaylist;
	private List<IPlugin> mPlugins = new ArrayList<IPlugin>();

	public ApplicationConfig(Playlist pPlaylist) {
		mPlaylist = pPlaylist;
	}
	
	public Playlist getPlaylist() {
		return mPlaylist;
	}
	
	public List<IPlugin> getPlugins() {
		return mPlugins;
	}
	
	public void setPlugins(List<IPlugin> pPlugins) {
		mPlugins = pPlugins;
	}

}
