/**
 * 
 */
package net.sf.video4j.gwt.client.player;

/**
 * @author luc
 *
 */
public class PlayAllDecisionManager implements IPlayItemDecisionManager {

	@Override
	public boolean canPlay(Track pTrack) {
		return true;
	}

}
