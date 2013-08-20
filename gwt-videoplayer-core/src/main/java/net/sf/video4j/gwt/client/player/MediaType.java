/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.video4j.gwt.client.util.MapUtils;

/**
 * @author luc
 *
 */
public enum MediaType {
	Image,
	Video,
	Audio,
	Unknown;
	
	
	private static final String[] FLASH_VIDEO_EXTENSIONS = { "f4b", "f4p", "f4v", "flv" };
	private static final String[] VIDEO_EXTENSIONS = { "3g2", "3gp", "aac", "m4a", "m4v", "mov", "mp4", "vp6", "mpeg4", "video" };
	private static final String[] IMAGE_EXTENSIONS = { "png", "jpg", "jpeg", "gif" };
	
	private static final String[] FLASH_VIDEO_MIME_TYPES = { "video/flv", "video/x-flv"};
	private static final String[] VIDEO_MIME_TYPES = { "application/x-fcs", "video/3gpp", "video/h264", "video/mp4", "video/x-3gpp", "video/x-m4v", "video/x-mp4"};
	private static final String[] IMAGE_MIME_TYPES = { "image/gif", "image/jpeg", "image/jpg", "image/png"};
	private static final String[] AUDIO_MIME_TYPES = { "audio/aac", "audio/m4a", "audio/mp4", "audio/mp3", "audio/mpeg", "audio/x-3gpp", "audio/x-m4a"};
	
	private static final Map<String, MediaType> EXTENSIONS_TYPE = new HashMap<String, MediaType>();
	private static final Map<String, MediaType> MIME_TYPE = new HashMap<String, MediaType>();
	
	static {
		for (String e : FLASH_VIDEO_EXTENSIONS) EXTENSIONS_TYPE.put(e, MediaType.Video);
		for (String e : VIDEO_EXTENSIONS) EXTENSIONS_TYPE.put(e, MediaType.Video);
		for (String e : IMAGE_EXTENSIONS) EXTENSIONS_TYPE.put(e, MediaType.Image);
		
		for (String e : FLASH_VIDEO_MIME_TYPES) MIME_TYPE.put(e, MediaType.Video);
		for (String e : VIDEO_MIME_TYPES) MIME_TYPE.put(e, MediaType.Video);
		for (String e : IMAGE_MIME_TYPES) MIME_TYPE.put(e, MediaType.Image);
		for (String e : AUDIO_MIME_TYPES) MIME_TYPE.put(e, MediaType.Audio);		
	}
	
	static {
		Arrays.sort(FLASH_VIDEO_EXTENSIONS);
		Arrays.sort(VIDEO_EXTENSIONS);
		Arrays.sort(IMAGE_EXTENSIONS);
	}
	
	public static MediaType fromExtension(String pExtension) {
		String oExt = pExtension.toLowerCase().replaceAll("\\.", "").trim();
		return MapUtils.get(EXTENSIONS_TYPE, oExt, MediaType.Unknown);
	}
	
	public static MediaType fromMime(String pMime) {
		String oMime = pMime.toLowerCase().trim();
		return MapUtils.get(MIME_TYPE, oMime, MediaType.Unknown);
	}
	
	public static boolean isFlashExtension(String pExtension) {
		String oExt = pExtension.toLowerCase().replaceAll("\\.", "").trim();
		return Arrays.binarySearch(FLASH_VIDEO_EXTENSIONS, oExt) >= 0;
	}
	
	public static boolean isFlashMime(String pMime) {
		String oMime = pMime.toLowerCase().trim();
		return Arrays.binarySearch(FLASH_VIDEO_MIME_TYPES, oMime) >= 0;
	}
}
