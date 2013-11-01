package net.sf.video4j.gwt.client.model;

import java.util.List;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * @author Gustavo Matias
 *
 */
public interface PlaylistBean {
	@PropertyName("playlist")
	List<PlayItemBean> getPlayItems();
	@PropertyName("playlist")
	void getPlayItems(List<PlayItemBean> pPlayItems);
}
