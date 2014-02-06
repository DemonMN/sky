/*
	SuperBox v1.0.0
	by Todd Motto: http://www.toddmotto.com
	Latest version: https://github.com/toddmotto/superbox
	
	Copyright 2013 Todd Motto
	Licensed under the MIT license
	http://www.opensource.org/licenses/mit-license.php

	SuperBox, the lightbox reimagined. Fully responsive HTML5 image galleries.
*/
;(function($) {
	var superbox  = $('<div class="superbox-show"></div>'),
	superboximg   = $('<img src="" class="superbox-current-img"><div id="imgInfoBox" class="superbox-imageinfo inline-block"> <h1>Image Title</h1><span><p></p><p class="superbox-img-description">Image description</p></span> </div>'),
	superboxclose = $('<div class="superbox-close"><i class="fa fa-times fa-lg"></i></div>');
	

	superbox.append(superboximg).append(superboxclose);

	var imgInfoBox = $('.superbox-imageinfo');

	$.fn.SuperBox = function(data, options) {
		return this.each(function() {			
			$('.superbox-list').click(function() {
				$this = $(this);
				
				var currentimg = $this.find('.superbox-img'),
					imgData = currentimg.data('img'),
					imgDescription = currentimg.attr('alt') || "No description",
					imgLink = imgData,
					imgTitle = currentimg.attr('title') || "No Title";
					
					//console.log(imgData, imgDescription, imgLink, imgTitle)
					
				superboximg.attr('src', imgData);
				$('.superbox-show .social').remove();
				
				$(superbox).append($(".social", this).clone());
				FB.XFBML.parse($(".superbox-show")[0]);
				$(".superbox-show").show();
				$('.superbox-list').removeClass('active');
				$this.addClass('active');
				
				//$('#imgInfoBox em').text(imgLink);
				$('#imgInfoBox >:first-child').text(imgTitle);
				$('#imgInfoBox .superbox-img-description').text(imgDescription);
				
				//console.log("fierd")
				
				if($('.superbox-current-img').css('opacity') == 0) {
					$('.superbox-current-img').animate({opacity: 1});
				}
				
				if ($(this).next().hasClass('superbox-show')) {
					$('.superbox-list').removeClass('active');
					superbox.toggle();
				} else {
					superbox.insertAfter(this).css('display', 'block');
					$this.addClass('active');
				}
							
			});
						
			$('.superbox').on('click', '.superbox-close', function() {
				$('.superbox-list').removeClass('active');
				$('.superbox-current-img').animate({opacity: 0}, 200, function() {
					$('.superbox-show').slideUp();
				});
			});
			
		});
	};
})(jQuery);