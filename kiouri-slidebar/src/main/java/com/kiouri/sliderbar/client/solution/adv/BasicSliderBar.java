package com.kiouri.sliderbar.client.solution.adv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.kiouri.sliderbar.client.view.SliderBarHorizontal;

public class BasicSliderBar extends SliderBarHorizontal {
	
	ImagesAdvancedSliderBar images = GWT.create(ImagesAdvancedSliderBar.class);
	
	public BasicSliderBar(){
		Widget drag = new MAdvancedPanel();
		drag.setPixelSize(10, 10);
		this.setScaleWidget(new Image(images.scale().getUrl()), 4);
		this.setDragWidget(drag);
		this.setMaxValue(4);
		this.setWidth("148px");		
	}
	
	interface ImagesAdvancedSliderBar extends ClientBundle {
		@Source("scale.png")
		DataResource scale();
	}	

}