package net.sf.video4j.gwt.client.model;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

/**
 * Sample JS config:
 * 
var video4j_ads = {
	common: {
		autoPlay: false,
		width: 500,
		height: 400,	
	},
	playlist: [
		{ 
			url: 'http://videos.tripfilms.com/360p/4BAE2BA4EE543703AF8BC2DF6550BB73.mp4',
			LR_DISABLED: true	
		}, 
		{ 
			url: 'http://videos.tripfilms.com/480p/ECAA41D5E30E4BECC13FC1D3DA823750.mp4',
			LR_TITLE: 'My video 02 title',
			LR_TAGS: 'loc123, f123, featured'
		},
		{ 
			url: 'http://videos.tripfilms.com/480p/ECAA41D5E30E4BECC13FC1D3DA823750.mp4',
			LR_VIDEO_ID: 1234,
			LR_TAGS: 'loc123, f123, featured'
		}
	],
	plugins: {
		net.sf.video4j.gwt.plugins.ads.liverail: {
			//LR_PUBLISHER_ID: 111111,
			publisherId: 11111,
			//LR_LAYOUT_SKIN_MESSAGE: 'Advertisement. Video will resume in {COUNTDOWN} seconds.',
			layoutSkingMessage: 'Advertisement. Video will resume in {COUNTDOWN} seconds.'
			//LR_ADMAP: 'in::0;ov::0,120',
			adMap: 'in::0;ov::0,120',
			//recurrent_admap: 'ov::0,120',
			recurrentAdMap: 'ov::0,120',
			//recurrent_init: 120,    // reinitialize ads every 120 seconds (2 minutes)
			recurrentInit: 120,    // reinitialize ads every 120 seconds (2 minutes)
			//stop_on_adstart: true   // true for rtmp live-streaming, false for long form or progressive
			stopOnAdStart: true   // true for rtmp live-streaming, false for long form or progressive
		}
	}
};
 *
 * @author Gustavo Matias
 *
 */
public interface IApplicationConfig {

	public JSONObject getCommon();
	public JSONArray getPlaylist();
	public JSONObject getPlugins();
	
}
