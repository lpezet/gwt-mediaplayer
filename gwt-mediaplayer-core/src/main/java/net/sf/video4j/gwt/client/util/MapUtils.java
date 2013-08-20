/**
 * 
 */
package net.sf.video4j.gwt.client.util;

import java.util.Map;

/**
 * @author luc
 *
 */
public class MapUtils {

	public static <S,T> T get(Map<S, T> pMap, S pKey, T pDefault) {
		T oValue = pMap.get(pKey);
		return (oValue == null) ? pDefault : oValue;
	}
}
