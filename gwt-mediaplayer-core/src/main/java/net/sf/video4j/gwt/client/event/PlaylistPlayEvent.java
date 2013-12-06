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
public class PlaylistPlayEvent extends GwtEvent<PlaylistPlayEvent.PlaylistPlayHandler> {
	
	private PlayItem mPlayItem;
    
    protected PlaylistPlayEvent(PlayItem pPlayItem) {
    	mPlayItem = pPlayItem;
    }
    
    public PlayItem getPlayItem() {
		return mPlayItem;
	}
    
    public static void fire(HasHandlers pSource, PlayItem pPlayItem) {
    	PlaylistPlayEvent oEventInstance = new PlaylistPlayEvent(pPlayItem);
        pSource.fireEvent(oEventInstance);
    }

    public interface PlaylistPlayHandlers extends HasHandlers {
        HandlerRegistration addPlaylistPlayHandler(PlaylistPlayHandler pHandler);
    }

    public interface PlaylistPlayHandler extends EventHandler {
        public void onPlaylistPlayEvent(PlaylistPlayEvent pEvent);
    }

    private static final Type<PlaylistPlayHandler> TYPE = new Type<PlaylistPlayHandler>();

    public static Type<PlaylistPlayHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlaylistPlayHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlaylistPlayHandler pHandler) {
        pHandler.onPlaylistPlayEvent(this);
    }
    
}