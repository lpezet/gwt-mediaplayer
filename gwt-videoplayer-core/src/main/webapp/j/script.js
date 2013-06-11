$(document).ready(function()
{
    
    
    // SOCIAL / LOGIN Toggle
    $('.toggle').click(function() {
        $('.social-login').animate({width: 'toggle'});
        return false;
    });
    
    $('.toggle').click(function () {
        $(this).toggleClass("expand");
    });

	// Hover Tooltip
	$('.icon[title]').qtip({
		position: {
			my:'bottom center',
			at:'top center'
		}
	});
	

// Home Page Header Carousel
    $('#carousel-header').cycle({
        fx: 'scrollHorz',
        next: '#ss-h-next',
        prev: '#ss-h-prev',
        nowrap: false,
        speed: 2000,
        timeout: 7000,
        pager: '#dots',
        pause: true,
        // For Prev/Next Buttons
        
        after: function (curr, next, opts) {
            var index = opts.currSlide;
            $('#ss-h-prev')[index == 0 ? [$('#ss-h-prev').addClass('disabled')] : [$('#ss-h-prev').removeClass('disabled')]];
            $('#ss-h-next')[index == opts.slideCount - 1 ? [$('#ss-h-next').addClass('disabled')] : [$('#ss-h-next').removeClass('disabled')]];
        }
    });

	
	// // Home Page HD Carousel
	// $('#carousel-hd').cycle({
		// fx: 'scrollHorz',
		// next: '#ss-hd-next',
		// prev: '#ss-hd-prev',
		// speed: 1000,
		// nowrap: true,
		// timeout: 0, 
		// // For Prev/Next Buttons
		// after: function (curr, next, opts) {
		    // var index = opts.currSlide;
		    // $('#ss-hd-prev')[index == 0 ? [$('#ss-hd-prev').addClass('disabled')] : [$('#ss-hd-prev').removeClass('disabled')]];
		    // $('#ss-hd-next')[index == opts.slideCount - 1 ? [$('#ss-hd-next').addClass('disabled')] : [$('#ss-hd-next').removeClass('disabled')]];
		// }
	// });
// 	
	   // // Home Page Videos Carousel
    // $('#carousel-videos').cycle({
        // fx: 'scrollHorz',
        // next: '#ss-v-next',
        // prev: '#ss-v-prev',
        // speed: 1000,
        // nowrap: true,
        // timeout: 0,
        // // For Prev/Next Buttons
        // after: function (curr, next, opts) {
            // var index = opts.currSlide;
            // $('#ss-v-prev')[index == 0 ? [$('#ss-v-prev').addClass('disabled')] : [$('#ss-v-prev').removeClass('disabled')]];
            // $('#ss-v-next')[index == opts.slideCount - 1 ? [$('#ss-v-next').addClass('disabled')] : [$('#ss-v-next').removeClass('disabled')]];
        // }
    // });



	// Form Text and Textarea Clearing
	$('.input-text input, .input-textarea textarea, .input-textarea-large textarea, .input-date input')
		// Selects all text and changes color of input fields and textareas onfocus
		.focus(function() {
			$(this).closest('li').addClass('f-focus');
			$(this).css("color","#222");
			if(this.value == this.defaultValue)
			{
				this.select();
			}
		})
		// Fix for Chrome to select all text of input fields and textareas onfocus
		.live('focus mouseup', function(e) {
			if (e.type == 'focusin') {
				this.select();
			}
			if (e.type == 'mouseup') {
				return false;
			}
		})
		// Clears input fields and textareas onkeydown
		.keydown(function() {
			if (this.value == this.defaultValue) {
				this.value = '';
			}
		})
		// Restores default value of input fields and textareas onblur
		.blur(function() {
			$(this).closest('li').removeClass('f-focus');
			if (this.value == '') {
				this.value = this.defaultValue;
				$(this).css("color","#777");
			}
			if (this.value == this.defaultValue) {
				this.value = this.defaultValue;
				$(this).css("color","#777");
			}
		})
	;
	
    

	
	// About Tabs
	$(".team-profile").hide(); //Hide all content
	$("#meet-the-team ul.span-3 li:first").addClass("active").show(); //Activate first tab
	$(".team-profile:first").show(); //Show first tab content
	$("#meet-the-team ul.span-3 li").click(function() {
		$("#meet-the-team ul.span-3 li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".team-profile").hide(); //Hide all tab content
		var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active content
		return false;
	});

	// General Tertiary Tabs
	$(".vertical-tab").hide(); //Hide all content
	$(".vertical-tabs li:first a").addClass("selected").show(); //Activate first tab
	$(".vertical-tab:first").show(); //Show first tab content
	$(".vertical-tabs li a").click(function() {
		$(".vertical-tabs li a").removeClass("selected"); //Remove any "active" class
		$(this).addClass("selected"); //Add "active" class to selected tab
		$(".vertical-tab").hide(); //Hide all tab content
		var activeTab = $(this).attr("href"); //Find the rel attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active content
		return false;
	});

	// FAQs
	$("#faq dd").hide();
	$("#faq dt").click(function () {
		$(this).next("dd").slideToggle("fast");
	});


});