<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<style>
	body{
		background: url("../img/bg2.jpg") repeat scroll 0 0 rgba(0, 0, 0, 0);
    	color: #666666;
		height: auto;
		padding: 10px 20px 0;
	}
	.content{
		background: url("../img/bg-wrapper.jpg") repeat scroll 0 0 rgba(0, 0, 0, 0);
   	 	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.35);
	}
	.container{    	
		border: none; 
	}
	.superbox {
		padding: 28px;
		text-align: center;
	}
	.superbox-show{
		width: 1105px;
	}
	.superbox-list{
		background: #FFF;
		box-shadow: 0 1px 1px rgba(0, 0, 0, 0.35);
		width: 180px;
		height: 160px;
		margin: 10px 5px 10px 0px;
		overflow: hidden;
		vertical-align: middle;
		padding: 4px;
	}
	.box{
		width: 172x;
		height: 142px;
		overflow: hidden;
		padding: 4px;
	}
	.superbox-list img{
		width: 180px;
	}
	.superbox-current-img{
		width: 420px;
	}
	.span9 {
    	width: 870px;
	}
	.span2 {
	    width: 170px;
		float: left;
	    margin-left: 30px;
	    min-height: 1px;
	}
	#footer-menu{
		margin-top: 15px;
	}
	#footer-menu-links li:first-child {
    	list-style: none outside none;
	}
	#footer-menu-links ul li {
		color: #444444;
	    float: left;
	    position: relative;
	    transition: all 0.3s ease-in-out 0s;
	    z-index: 100;
	}
	#footer-menu-links ul li a {
	    color: #444444;
	    display: inline-block;
	    margin: 0 10px 0 0;
	    padding: 10px 15px 10px 0;
	    text-decoration: none;
	    text-transform: lowercase;
	    transition: all 0.3s ease-in-out 0s;
	}
	.brand {
	    color: #DDDDDD !important;
	    font-size: 28px !important;
	    line-height: 60px !important;
	    margin-bottom: -3px;
	    margin-left: -10px !important;
	    margin-top: 3px;
	    text-decoration: none !important;
	    text-shadow: none !important;
	}
	.sixteen{
		padding: 10px;
		text-align: center;
	}
</style>
<body>
	<div id=“fb-root”></div>
	<section>
		<div class="container content">
			<!-- SuperBox -->
			<div class="superbox" ng-controller="galleryController" >
				<div class="superbox-list" ng-repeat="file in photos | filter:query" >
					<div class="box">
						<img ng-show="file" src="{{file.mediaLink}}" data-img="{{file.mediaLink}}" alt="" title="Элдэв зураг" class="superbox-img">
						<div class="social">
							<div class="fb-like" data-href="http://www.skynews.mn/#/photo/view?large={{file.selfLink}}&token={{pageToken}}" data-layout="standard" data-action="like" data-show-faces="true" data-share="true"></div>
						</div>
						<div class="social">
							<div class="fb-comments" data-href="http://www.skynews.mn/#/photo/view?large={{file.selfLink}}&token={{pageToken}}" data-numposts="5" data-colorscheme="light"></div>
						</div>
					</div>
				</div>
				<button class="btn btn-block  btn-primary" ng-click="next()">Load more...</button>
			</div>
		</div>
	</section>
	<section>
		<div class="hidden-tablet hidden-phone " id="footer-menu">
	
			<!-- start: Container -->
			<div class="container">
				
				<!-- start: Row -->
				<div class="row">
					<div class="span2">
						<div id="footer-menu-logo">
							<a href="#" class="brand">SKY<span>NEWS</span></a>
						</div>
					</div>
					<!-- start: Footer Menu Links-->
					<div class="span9">
						
						<div id="footer-menu-links">
	
							<ul id="footer-nav">
	
								<li><a href="index.html">Start</a></li>
	
								<li><a href="about.html">About</a></li>
	
								<li><a href="services.html">Services</a></li>
	
								<li><a href="pricing.html">Pricing</a></li>
								
								<li><a href="contact.html">Contact</a></li>
	
							</ul>
	
						</div>
						
					</div>
					<!-- end: Footer Menu Links-->
	
					<!-- start: Footer Menu Back To Top -->
					<div class="span1">
							
						<div id="footer-menu-back-to-top">
							<a href="#"></a>
						</div>
					
					</div>
					<!-- end: Footer Menu Back To Top -->
				
				</div>
				<!-- end: Row -->
				
			</div>
			<!-- end: Container  -->	
	
		</div>
	</section>
	<div id="copyright">
	
		<!-- start: Container -->
		<div class="container">
		
			<div class="sixteen columns">
				<p>
					&copy; 2014, <a href="http://www.skynews.mn">skynews</a>. Designed by <a href="http://clabs.co">sky news</a> in Mongolia
				</p>
			</div>
	
		</div>
		<!-- end: Container  -->
		
	</div>
	
	
	<!--================================================== -->	

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->

	    <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script> if (!window.jQuery) { document.write('<script src="js/libs/jquery-2.0.2.min.js"><\/script>');} </script>

	    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script> if (!window.jQuery.ui) { document.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>

		<!-- BOOTSTRAP JS -->		
		<script src="js/bootstrap/bootstrap.min.js"></script>

		<!-- CUSTOM NOTIFICATION -->
		<script src="js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="js/smartwidgets/jarvis.widget.min.js"></script>
		
		<!-- EASY PIE CHARTS -->
		<script src="js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
		
		<!-- SPARKLINES -->
		<script src="js/plugin/sparkline/jquery.sparkline.min.js"></script>
		
		<!-- JQUERY VALIDATE -->
		<script src="js/plugin/jquery-validate/jquery.validate.min.js"></script>
		
		<!-- JQUERY MASKED INPUT -->
		<script src="js/plugin/masked-input/jquery.maskedinput.min.js"></script>
		
		<!-- JQUERY SELECT2 INPUT -->
		<script src="js/plugin/select2/select2.min.js"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script src="js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>
		
		<!-- browser msie issue fix -->
		<script src="js/plugin/msie-fix/jquery.mb.browser.min.js"></script>
		
		<!--[if IE 7]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular.min.js"></script>
    	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular-resource.min.js"></script>
    	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular-route.js"></script>
    	
		<script src="/js/angular-storage.js"></script>
		
		<script src="//connect.facebook.net/en_US/all.js"></script>
		
		<!-- DEMO JS -->
		<script src="/js/demo.js"></script>
		
		<!-- MAIN APP JS FILE -->
		<script src="/js/app.js"></script>
		
		<!-- MAIN APP JS FILE -->
		<script src="/js/login.js"></script>
		<script src="/js/plugin/superbox/superbox.js"></script>
		
		<script type="text/javascript">
		$(function() {
            // Call SuperBox
           
    	});
		
		</script>
</body>
</html>