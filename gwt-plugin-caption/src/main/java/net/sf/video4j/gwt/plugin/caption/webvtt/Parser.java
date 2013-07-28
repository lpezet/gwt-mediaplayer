/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.video4j.gwt.plugin.caption.CaptionException;
import net.sf.video4j.gwt.plugin.caption.TimeUtil;
import net.sf.video4j.gwt.plugin.caption.webvtt.impl.VTTCueImpl;

/**
 * @author luc
 *
 */
public class Parser {
	
	// 00:11.000 --> 00:13.000
	// We are in New York City
	private Pattern CUE_TIMINGS_PATTERN = Pattern.compile("(\\d{2}:\\d{2}.\\d{3}) --> (\\d{2}:\\d{2}.\\d{3})");
	
	public WebVTT parse(InputStream pIS) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(pIS));
		String oLine = in.readLine();
		if (!"WEBVTT".equalsIgnoreCase(oLine)) throw new CaptionException("Invalid WebVTT format.");
		oLine = in.readLine();
		
		WebVTT oResult = new WebVTT();
		
		while ((oLine = in.readLine()) != null) {
			Matcher oTimings = CUE_TIMINGS_PATTERN.matcher(oLine);
			if (!oTimings.find()) throw new CaptionException("Invalid line: " + oLine);
			VTTCueImpl oCue = new VTTCueImpl();
			String oStartTimeValue = oTimings.group(1);
			String oEndTimeValue = oTimings.group(2);
			double oStartTime = TimeUtil.parseTime(oStartTimeValue);
			double oEndTime = TimeUtil.parseTime(oEndTimeValue);
			oCue.setStartTime(oStartTime);
			oCue.setEndTime(oEndTime);
			StringBuffer oText = new StringBuffer();
			while ((oLine = in.readLine()) != null) {
				if (oLine.isEmpty()) break;
				oText.append(oLine);
				oText.append("\n");
			}
			oCue.setText(oText.toString());
			oResult.getCues().add(oCue);
		}
		return oResult;
	}
	
	
}
