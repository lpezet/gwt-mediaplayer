/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption;

/**
 * @author luc
 *
 */
public class TimeUtil {

	public static double parseTime(String pTime) {
		String[] oTimes = pTime.split(":");
		double oTotal = 0;
		for (String oTime : oTimes) {
			oTotal = oTotal * 60 + Double.parseDouble(oTime);
		}
		return oTotal;
	}
}
