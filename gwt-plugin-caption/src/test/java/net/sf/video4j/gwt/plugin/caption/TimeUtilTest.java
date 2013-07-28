/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author luc
 *
 */
public class TimeUtilTest {
	
	@Test
	public void parse() {
		double oTotal = TimeUtil.parseTime("00:00.000");
		assertEquals(0.0, oTotal, 0.0);
		
		oTotal = TimeUtil.parseTime("00:01.000");
		assertEquals(1.0, oTotal, 0.0);
	
		oTotal = TimeUtil.parseTime("01:01.000");
		assertEquals(61.0, oTotal, 0.0);
		
	}

}
