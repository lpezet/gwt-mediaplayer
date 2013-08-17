package net.sf.video4j.gwt.client.config.model;

import java.util.Set;

/**
 * @author gumatias
 */
public class Playlist {
    
    private Set<Track> mTracks;

    public Set<Track> getTracks() {
        return mTracks;
    }

    public void setTracks(Set<Track> pTracks) {
        mTracks = pTracks;
    }

    @Override
    public String toString() {
        return "Playlist [mTracks=" + mTracks + "]";
    }

}