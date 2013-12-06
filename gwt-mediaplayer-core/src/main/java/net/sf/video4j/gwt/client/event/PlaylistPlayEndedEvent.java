/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import net.sf.video4j.gwt.client.player.PlayItem;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class PlaylistPlayEndedEvent extends GwtEvent<PlaylistPlayEndedEvent.PlaylistPlayEndedHandler> {
	
	private PlayItem mPlayItem;

    protected PlaylistPlayEndedEvent(PlayItem pPlayItem) {
    	mPlayItem = pPlayItem;
    }
    
    public PlayItem getPlayItem() {
		return mPlayItem;
	}
    
    public static void fire(HasHandlers pSource, PlayItem pPlayItem) {
    	PlaylistPlayEndedEvent oEventInstance = new PlaylistPlayEndedEvent(pPlayItem);
        pSource.fireEvent(oEventInstance);
    }

	public interface PlaylistPlayEndedHandlers extends HasHandlers {
		HandlerRegistration addPlaylistPlayEndedHandler(PlaylistPlayEndedHandler pHandler);
    }

	public interface PlaylistPlayEndedHandler extends EventHandler {
		public void onPlaylistPlayEndedEvent(PlaylistPlayEndedEvent pEvent);
    }

	private static final Type<PlaylistPlayEndedHandler>	TYPE	= new Type<PlaylistPlayEndedHandler>();

	public static Type<PlaylistPlayEndedHandler> getType() {
        return TYPE;
    }

    @Override
	public Type<PlaylistPlayEndedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
	protected void dispatch(PlaylistPlayEndedHandler pHandler) {
		pHandler.onPlaylistPlayEndedEvent(this);
    }
    
}