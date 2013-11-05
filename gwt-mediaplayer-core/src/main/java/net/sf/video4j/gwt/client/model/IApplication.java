/**
 * 
 */
package net.sf.video4j.gwt.client.model;

import java.util.Collection;

import net.sf.video4j.gwt.client.player.Playlist;

/**
 * @author luc
 *
 */
public interface IApplication {
	public IApplicationConfig getConfig();
	public Collection<IPlugin> getPlugins();
	public IPlugin getPlugin(String pPluginId);
	public void addPlugin(IPlugin pPlugin);
	public Playlist getPlaylist();
	public void setPlaylist(Playlist pPlaylist);
	
}