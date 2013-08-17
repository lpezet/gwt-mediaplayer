package net.sf.video4j.gwt.client.config.model;

/**
 * @author gumatias
 */
public class Video4JConfig {

    private boolean mAutoPlay;
    
    private String mWidth;
    
    private String mHeight;
    
    private Playlist mPlaylist;

    public boolean isAutoPlay() {
        return mAutoPlay;
    }

    public void setAutoPlay(boolean pAutoPlay) {
        mAutoPlay = pAutoPlay;
    }

    public String getWidth() {
        return mWidth;
    }

    public void setWidth(String pWidth) {
        mWidth = pWidth;
    }

    public String getHeight() {
        return mHeight;
    }

    public void setHeight(String pHeight) {
        mHeight = pHeight;
    }

    public Playlist getPlaylist() {
        return mPlaylist;
    }

    public void setPlaylist(Playlist pPlaylist) {
        mPlaylist = pPlaylist;
    }

    @Override
    public String toString() {
        return "Video4JConfig [mAutoPlay=" + mAutoPlay + ", mWidth=" + mWidth
                + ", mHeight=" + mHeight + ", mPlaylist=" + mPlaylist + "]";
    }
    
}