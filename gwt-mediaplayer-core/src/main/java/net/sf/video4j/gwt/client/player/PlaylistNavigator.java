/**
 * 
 */
package net.sf.video4j.gwt.client.player;

/**
 * @author luc
 *
 */
public class PlaylistNavigator {
	
	private static final PlayItem BEFORE_HEAD = new PlayItem(null);
	//private static final PlayItem AFTER_TAIL = new PlayItem(null);
	
	private PlayItem mCursor;
	private Playlist mPlaylist;

	public PlaylistNavigator(Playlist pPlaylist) {
		mPlaylist = pPlaylist;
		mCursor = BEFORE_HEAD;
	}
	
	private boolean isTail() {
		//System.out.println("Cursor = " + mCursor + ", tail = " + mPlaylist.getTail());
		return mCursor == mPlaylist.getTail();
	}
	
	private boolean isHead() {
		return mCursor == mPlaylist.getHead();
	}
	
	public boolean hasNext() {
		return (!isTail() && mPlaylist.getHead() != null);
	}
	
	public boolean hasPrevious() {
		return !isHead();
	}
	
	public PlayItem peek() {
		if (!hasNext()) return null;
		return mCursor.getNext();
	}
	
	public PlayItem next() {
		if (!hasNext()) return null;
		mCursor = (mCursor == BEFORE_HEAD) ? mPlaylist.getHead() : mCursor.getNext();
		return mCursor;
	}
	
	public PlayItem previous() {
		if (!hasPrevious()) return null;
		mCursor = mCursor.getPrevious();
		return mCursor;
	}
	
	public void reset() {
		mCursor = BEFORE_HEAD;
	}

}
