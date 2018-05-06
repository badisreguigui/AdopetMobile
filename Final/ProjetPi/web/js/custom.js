jQuery(document).ready(function($) {
    "use strict";
	
	/* PrettyPhoto Script
    ======================================================*/
    $('a[data-rel]').each(function () {
        $(this).attr('rel', $(this).data('rel'));
        $(".pretty-gallery a[rel^='prettyPhoto']").prettyPhoto();
    });

    /* Side Bar Menu Js
    ======================================================*/
    if ($('#push, #close').length) {
        $('#push, #close').on('click', function(){
            var $navigacia = $('#cp-slide-menu'),
            val = $navigacia.css('right') === '360px' ? '0px' : '360px';
            //$navigacia.css({'display':'block'});
            if ($navigacia.css('display')=='none') {
                $navigacia.css('display','block');
            }else{

                $navigacia.css('display','none');
            }

            $navigacia.animate({
            right: val,
            }, 500)
        });
    }

    /* Owl Slider For Banner 1
    ======================================================*/
    if ($('#cp-banner-slider').length) {
        $('#cp-banner-slider').owlCarousel({
            loop:true,
            dots: false,
            nav:true,
            navText:'',
            items:1,
            autoplay: false,
            smartSpeed:2000,

        });
    }


     /* Owl Slider Services Slider
    ======================================================*/
    if ($('#cp-services-slider').length) {
        $('#cp-services-slider').owlCarousel({
            loop:true,
            dots: false,
            nav:true,
            navText:'',
            items:4,
            smartSpeed:500,
            padding:0,
            margin: 30,
            responsiveClass:true,
            responsive:{
                0:{
                    items:1,
                },
                768:{
                    items:2,
                },
                992:{
                    items:3,
                },
                1199:{
                    items:4,
                }
            }
        });
    }


    /* Owl Slider For Testimonial Slider
    ======================================================*/
    if ($('#cp-testimonial-slider').length) {
        $('#cp-testimonial-slider').owlCarousel({
            loop:true,
            dots: false,
            nav:true,
            navText:'',
            items:3,
            smartSpeed:500,
            padding:0,
            margin: 30,
            responsiveClass:true,
            responsive:{
                0:{
                    items:1,
                },
                768:{
                    items:2,
                },
                992:{
                    items:2,
                },
                1199:{
                    items:3,
                }
            }
        });
    }


    /* Owl Slider For Blog Page
    ======================================================*/
    if ($('#cp-blog-slider').length) {
        $('#cp-blog-slider').owlCarousel({
            loop:true,
            dots: false,
            nav:true,
            navText:'',
            items:1,
            smartSpeed:500,
            padding:0,
            margin: 30,
            responsiveClass:true,
            responsive:{
                0:{
                    items:1,
                },
                768:{
                    items:1,
                },
                1199:{
                    items:1,
                }
            }
        });
    }

    /* Owl Slider For Product Detail Page
    ======================================================*/
    if ($('#cp_product-slider').length) {
        $('#cp_product-slider').owlCarousel({
            loop:true,
            dots: false,
            nav:false,
            navText:'',
            items:1,
            smartSpeed:500,
            padding:0,
            margin: 30,
            responsiveClass:true,
            responsive:{
                0:{
                    items:1,
                },
                768:{
                    items:1,
                },
                1199:{
                    items:1,
                }
            }
        });
    }


    /* Owl Slider For Product Detail Page
    ======================================================*/
    if ($('#cp_relted-slider').length) {
        $('#cp_relted-slider').owlCarousel({
            loop:true,
            dots: false,
            nav:true,
            navText:'',
            items:4,
            smartSpeed:500,
            padding:0,
            margin: 30,
            responsiveClass:true,
            responsive:{
                0:{
                    items:1,
                },
                768:{
                    items:2,
                },
                1199:{
                    items:4,
                }
            }
        });
    }

    /* Isotope For Gallery Masonary
    ======================================================*/
    if ($(".cp-gallery-masonary .isotope").length) {
        var $container = $('.cp-gallery-masonary .isotope');
		$container.imagesLoaded( function(){
			$container.isotope({
				itemSelector: '.cp-masonary-item',
				transitionDuration: '0.6s',
				masonry: {
					columnWidth: $container.width() / 12
				},
				layoutMode: 'masonry'
			});
		});

        $(window).resize(function() {
            $container.isotope({
                masonry: {
                    columnWidth: $container.width() / 12
                }
            });
        });
    }

    /* Isotope For Blog Masonary
    ======================================================*/
    if ($(".cp-blog-masonary .isotope").length) {
        var $container = $('.cp-blog-masonary .isotope');
        $container.imagesLoaded( function(){
            $container.isotope({
                itemSelector: '.cp-masonary-item',
                transitionDuration: '0.6s',
                masonry: {
                    columnWidth: $container.width() / 12
                },
                layoutMode: 'masonry'
            });
        });

        $(window).resize(function() {
            $container.isotope({
                masonry: {
                    columnWidth: $container.width() / 12
                }
            });
        });

    }


     /* FILTERABLE Masonary For Gallery
    ======================================================*/
    if ($('.cp_portfolioGallery').length) {
        var $container = $('.cp_portfolioGallery');
        $container.imagesLoaded( function(){
            $container.isotope({
                filter: '*',
                animationOptions: {
                    duration: 750,
                    easing: 'linear',
                    queue: false
                }
            });
        });

        $('.cp_galleryFilter a').on('click', function(){
            $('.cp_galleryFilter .current').removeClass('current');
            $(this).addClass('current');

            var selector = $(this).attr('data-filter');
            $container.isotope({
                filter: selector,
                animationOptions: {
                    duration: 750,
                    easing: 'linear',
                    queue: false
                }
            });
            return false;
        });
    }


    /* JQuery UI Range Slider For Product
    ======================================================*/
    if ($('#cp-range-slider').length) {
        $( "#cp-range-slider" ).slider({
            range: true,
            min: 0,
            max: 500,
            values: [ 75, 300 ],
            slide: function( event, ui ) {
                $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
            }
        });
        
        $( "#amount" ).val( "$" + $( "#cp-range-slider" ).slider( "values", 0 ) +
        " - $" + $( "#cp-range-slider" ).slider( "values", 1 ) );
    };

    /* Share Social Links Js
    ======================================================*/
    if ($('.share-push').length) {
        $('.share-push').on('click', function(event){
            var $navigacia = $('.share-holder'),
            val = $navigacia.css('right') === '280px' ? '0px' : '280px';
            $navigacia.animate({
            right: val
            }, 500)
            event.preventDefault();
        });
    }

    /* Audio Player For Blog Page
    ======================================================*/
    if ($('audio').length) {
        $('audio').audioPlayer();
    }

   

   /* ToopTip Js
    ======================================================*/
    $(function () {
      $('[data-toggle="tooltip"]').tooltip()
    })


    /* CountDown For Coming Soon Page
    // ======================================================*/
    if ($('.comingcountdown').length) {
        $(function () {
        var austDay = new Date();
        austDay = new Date(austDay.getFullYear() + 1, 1 - 1, 26);
        $('.comingcountdown').countdown({until: austDay});
        $('#year').text(austDay.getFullYear());
        });
    }

    /* CounterUp For Facts Start
    ======================================================*/
   if ($('.counter').length) {
       $('.counter').counterUp({
           delay: 10,
           time: 1000
       });
   }

    /* Datepicker for About Us Page
    ======================================================*/
   if ($('#datepicker').length) {
       $('#datepicker').datepicker();
   }

    /* Background Image Scrolling
    ======================================================*/
     if ($('.cp-bg-movement').length) {
         $(".cp-bg-movement").interactive_bg({
           strength: 15,
           scale: 0.2,
           animationSpeed: "10s",
           contain: true,
           wrapContent: false
        });
      }

      if ($('.cp-bg-movement').length) {
         $(".cp-bg-movement").interactive_bg(); 
      }

       $(window).resize(function() {
          $(".cp-bg-movement > .ibg-bg").css({
            width: $(window).outerWidth(),
            height: $(window).outerHeight()
          })
       })

    });


    // $('#animate-img').mouseover(function() {
    //     $('#animate-img').css("left", "110px" ,"background", "url('images/pet-contact-img2.jpg') no-repeat");
    // });
    // $('#animate-img').mouseout(function() {
    //     $('#animate-img').css("left", "120px");
    // });
