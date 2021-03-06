<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
	<body class="no-right-panel" ng-controller="userController"> <!-- possible classes: minified, no-right-panel, fixed-ribbon, fixed-header, fixed-width-->
		<header id="header">
			<!--<span id="logo"></span>-->
			<div id="logo-group">
				<span id="logo">
					<img src="img/logo.png" alt="SmartAdmin">
				</span>
				<span id="activity" class="activity-dropdown">
					<i class="fa fa-user"></i>
					<b class="badge">
						21
					</b>
				</span>
				<div class="ajax-dropdown">
					
					<div class="btn-group btn-group-justified" data-toggle="buttons">
				        <label class="btn btn-default">
				          <input type="radio" name="activity" id="ajax/notify/mail.html"> Msgs (14)
				        </label>
				        <label class="btn btn-default">
				          <input type="radio" name="activity" id="ajax/notify/notifications.html"> notify (3)
				        </label>
				        <label class="btn btn-default">
				          <input type="radio" name="activity" id="ajax/notify/tasks.html"> Tasks (4)
				        </label>
				    </div>

					<div class="ajax-notifications custom-scroll">
						<div class="alert alert-transparent">
							<h4>Click a button to show messages here</h4>
						This blank page message helps protect your privacy, or you can show the first message here automatically.
						</div>
						
						<i class="fa fa-lock fa-4x fa-border"></i>
						
					</div>
					
					<span>
						Last updated on: 12/12/2013 9:43AM
						<button type="button" data-loading-text="<i class='fa fa-refresh fa-spin'></i> Loading..." class="btn btn-xs btn-default pull-right">
						  <i class="fa fa-refresh"></i>
						</button>
					</span>
				</div>
				<!-- END AJAX-DROPDOWN -->
			</div>
			

			<div id="project-context">
				<span class="label">Projects:</span>
				<span id="project-selector" class="popover-trigger-element dropdown-toggle" data-toggle="dropdown">Recent projects <i class="fa fa-angle-down"></i></span>
				<ul class="dropdown-menu">
					<li>
						<a href="javascript:void(0);">Online e-merchant management system - attaching integration with the iOS</a>
					</li>
					<li>
						<a href="javascript:void(0);">Notes on pipeline upgradee</a>
					</li>
					<li>
						<a href="javascript:void(0);">Assesment Report for merchant account</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="javascript:void(0);"><i class="fa fa-power-off"></i> Clear</a>
					</li>
				</ul>
			</div>
			<div class="pull-right">
				<div id="hide-menu" class="btn-header pull-right">
					<span>
						<a href="javascript:void(0);"><i class="fa fa-reorder"></i></a>
					</span>
				</div>
				<div id="logout" class="btn-header transparent pull-right">
					<span>
						<a href="login.jsp"><i class="fa fa-sign-out"></i></a>
					</span>
				</div>
				<div id="search-mobile" class="btn-header transparent pull-right">
					<span>
						<a href="javascript:void(0)"><i class="fa fa-search"></i></a>
					</span>
				</div>
					
				<form action="#ajax/search.html" class="header-search pull-right">
					<input type="text" placeholder="Find reports and more" id="search-fld">
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
					<a href="javascript:void(0);" id="cancel-search-js"><i class="fa fa-times"></i></a>
				</form>					
					
				<!-- This right panel will be released in the future version of SmartAdmin
					<div id="right-panel-overlap-btn" class="btn-header transparent pull-right">
					<span>
						<a href="javascript:void(0);" rel="tooltip" title="Show Right Panel" data-placement="bottom"><i class="fa fa-long-arrow-left"></i></a>
					</span>
				</div>
				-->	
				<ul class="header-dropdown-list hidden-xs">
					<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img alt="" src="img/flags/us.png">
							<span>
								US
							</span>
							<i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-right">
							<li class="active"><a href="javascript:void(0);"><img alt="" src="img/flags/us.png"> US</a></li>
							<li><a href="javascript:void(0);"><img alt="" src="img/flags/es.png"> Spanish</a></li>
							<li><a href="javascript:void(0);"><img alt="" src="img/flags/de.png"> German</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</header>
		<aside id="left-panel" ng-controller="loginController">
			<div class="login-info">
				<span>
					<img src="https://graph.facebook.com/{{me.id}}/picture?width=50&height=50" alt="me" class="online" />	
					<a href="javascript:void(0);" id="show-shortcut">{{me.name}}<i class="fa fa-angle-down"></i></a>
				</span>
			</div>
			<nav>
				<ul>
					<li class=""><a href="ajax/dashboard.html" title="Dashboard"><i class="fa fa-lg fa-fw fa-home"></i>  <span class="menu-item-parent">Dashboard</span></a></li>
					<li><a href="ajax/inbox.html"><i class="fa fa-lg fa-fw fa-inbox"></i>  <span class="menu-item-parent">Inbox</span><span class="badge pull-right inbox-badge">14</span></a></li>
					<li><a href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i>  <span class="menu-item-parent">Graphs</span></a>
						<ul>
							<li><a href="ajax/flot.html">Flot Chart</a></li>
							<li><a href="ajax/morris.html">Morris Charts</a></li>
							<li><a href="ajax/inline-charts.html">Inline Charts</a></li>
						</ul>
					</li>
					<li><a href="#"><i class="fa fa-lg fa-fw fa-table"></i>  <span class="menu-item-parent">Tables</span></a>
						 	<ul>
								 <li><a href="ajax/table.html">Normal Tables</a></li>
								 <li><a href="ajax/datatables.html">Data Tables</a></li>
						 	</ul>
					</li>
					<li><a href="#"><i class="fa fa-lg fa-fw fa-pencil-square-o"></i>  <span class="menu-item-parent">Forms</span></a>
					 	<ul>
							 <li><a href="ajax/form-elements.html">Smart Form Elements</a></li>
							 <li><a href="ajax/form-templates.html">Smart Form Layouts</a></li>
							 <li><a href="ajax/validation.html">Smart Form Validation</a></li>
							 <li><a href="ajax/bootstrap-forms.html">Bootstrap Form Elements</a></li>
							 <li><a href="ajax/plugins.html">Form Plugins</a></li>
							 <li><a href="ajax/wizard.html">Wizards</a></li>
							 <li><a href="ajax/other-editors.html">Bootstrap Editors</a></li>
							 <li><a href="ajax/dropzone.html">Dropzone <span class="badge pull-right inbox-badge bg-color-yellow">new</span></a></li>
					 	</ul>
					</li>
					<li><a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i>  <span class="menu-item-parent">UI Elements</span></a>
						<ul>
							 <li><a href="ajax/general-elements.html">General Elements</a></li>
							 <li><a href="ajax/buttons.html">Buttons</a></li>
							 <li><a href="#">Icons</a>
							 	<ul>
									 <li><a href="ajax/fa.html"><i class="fa fa-plane"></i> Font Awesome</a>
									 <li><a href="ajax/glyph.html"><i class="glyphicon glyphicon-plane"></i> Glyph Icons </a>	
							 	</ul>
							 </li>
							 <li><a href="ajax/grid.html">Grid</a></li>
							 <li><a href="ajax/treeview.html">Tree View</a></li>
							 <li><a href="ajax/nestable-list.html">Nestable Lists</a></li>
							 <li><a href="ajax/jqui.html">JQuery UI</a></li>
						</ul>
					</li>
					<li><a href="#"><i class="fa fa-lg fa-fw fa-folder-open"></i>  <span class="menu-item-parent">6 Level Navigation</span></a>
						<ul>
							<li><a href="#"><i class="fa fa-fw fa-folder-open"></i> 2nd Level</a>
							 	<ul>
									 <li><a href="#"><i class="fa fa-fw fa-folder-open"></i> 3ed Level </a>
									 	<ul>
									 		 <li><a href="#"><i class="fa fa-fw fa-file-text"></i> File</a></li>
											 <li><a href="#"><i class="fa fa-fw fa-folder-open"></i> 4th Level</a>
											 	<ul>
											 		 <li><a href="#"><i class="fa fa-fw fa-file-text"></i> File</a></li>
													 <li><a href="#"><i class="fa fa-fw fa-folder-open"></i> 5th Level</a>
													 	<ul>
													 		<li><a href="#"><i class="fa fa-fw fa-file-text"></i> File</a></li>
													 		<li><a href="#"><i class="fa fa-fw fa-file-text"></i> File</a></li>
													 	</ul>
													 </li>
											 	</ul>
											 </li>
									 	</ul>
									 </li>
							 	</ul>
							 </li>
							 <li><a href="#"><i class="fa fa-fw fa-folder-open"></i> Folder</a>
							 	
								<ul>
									 <li><a href="#"><i class="fa fa-fw fa-folder-open"></i> 3ed Level </a>
									 	<ul>
									 		 <li><a href="#"><i class="fa fa-fw fa-file-text"></i> File</a></li>
											 <li><a href="#"><i class="fa fa-fw fa-file-text"></i> File</a></li>
									 	</ul>
									 </li>
							 	</ul>
							 	
							 </li>
						</ul>
					</li>	
					<li><a href="ajax/calendar.html"><i class="fa fa-lg fa-fw fa-calendar"><em>3</em></i>  <span class="menu-item-parent">Calendar</span></a></li>
					<li><a href="ajax/widgets.html"><i class="fa fa-lg fa-fw fa-list-alt"></i>  <span class="menu-item-parent">Widgets</span></a></li>
					<li><a href="ajax/gallery.html"><i class="fa fa-lg fa-fw fa-picture-o"></i>  <span class="menu-item-parent">Gallery</span></a></li>
					<li><a href="ajax/gmap-xml.html"><i class="fa fa-lg fa-fw fa-map-marker"></i>  <span class="menu-item-parent">Google Map Skins</span><span class="badge bg-color-greenLight pull-right inbox-badge">9</span></a></li>					
					<li><a href="#"><i class="fa fa-lg fa-fw fa-windows"></i>  <span class="menu-item-parent">Miscellaneous</span></a>
						<ul>
							 <li><a href="ajax/typography.html">Typography</a></li>
							 <li><a href="ajax/pricing-table.html">Pricing Tables</a></li>
							 <li><a href="ajax/invoice.html">Invoice</a></li>
							 <li><a href="login.html" target="_top">Login</a></li>
							 <li><a href="register.html" target="_top">Register</a></li>
							 <li><a href="lock.html" target="_top">Locked Screen</a></li>
							 <li><a href="ajax/error404.html">Error 404</a></li>
							 <li><a href="ajax/error500.html">Error 500</a></li>
							 <li><a href="ajax/blank_.html">Blank Page</a></li>
							 <li><a href="ajax/email-template.html">Email Template</a></li>
							 <li><a href="ajax/search.html">Search Page</a></li>
							 <li><a href="ajax/ckeditor.html">CK Editor</a></li>
						</ul>
					</li>						
				</ul>
			</nav>
			<span class="minifyme">
				<i class="fa fa-arrow-circle-left hit"></i>
			</span>

			
		</aside>
		<aside id="right-panel">
			
		</aside>
		
		<div id="main" role="main">
			<div id="ribbon">
				
				<span class="ribbon-button-alignment">
					<span id="refresh" class="btn btn-ribbon" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true"><i class="fa fa-refresh"></i></span>
				</span>

				<ol class="breadcrumb">
					<!-- This is auto generated -->
				</ol>			
				
				<!--
				<span class="ribbon-button-alignment pull-right">
					<span id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa-grid"></i> Change Grid</span>
					<span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa-plus"></i> Add</span>
					<span id="search" class="btn btn-ribbon" data-title="search"><i class="fa-search"></i> <span class="hidden-mobile">Search</span></span>
				</span>-->
				
			</div>
			
			<!-- MAIN CONTENT -->
			<div id="content" ng-controller="galleryController">

				
				
			</div>				
			
		</div>

		<div id="shortcut">
			<ul>
				<li>
					<a href="#ajax/inbox.html" class="jarvismetro-tile big-cubes bg-color-blue">
						<span class="iconbox">
							<i class="fa fa-envelope fa-4x"></i>
							<span>Mail <span class="label pull-right bg-color-darken">14</span></span>
						</span>
					</a>
				</li>
				<li>
					<a href="#ajax/calendar.html" class="jarvismetro-tile big-cubes bg-color-orangeDark">
						<span class="iconbox">
							<i class="fa fa-calendar fa-4x"></i>
							<span>Calendar</span>
						</span>
					</a>
				</li>
				<li>
					<a href="#ajax/gmap-xml.html" class="jarvismetro-tile big-cubes bg-color-purple">
						<span class="iconbox">
							<i class="fa fa-map-marker fa-4x"></i>
							<span>Maps</span>
						</span>
					</a>
				</li>
				<li>
					<a href="#ajax/invoice.html" class="jarvismetro-tile big-cubes bg-color-blueDark">
						<span class="iconbox">
							<i class="fa fa-book fa-4x"></i>
							<span>Invoice <span class="label pull-right bg-color-darken">99</span></span>
						</span>
					</a>
				</li>
				<li>
					<a href="#ajax/gallery.html" class="jarvismetro-tile big-cubes bg-color-greenLight">
						<span class="iconbox">
							<i class="fa fa-picture-o fa-4x"></i>
							<span>Gallery </span>
						</span>
					</a>
				</li>
				<li>
					<a href="javascript:void(0);" class="jarvismetro-tile big-cubes selected bg-color-pinkDark">
						<span class="iconbox">
							<i class="fa fa-user fa-4x"></i>
							<span>My Profile </span>
						</span>
					</a>
				</li>
			</ul>
		</div>
		<div id=“fb-root”></div>
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
	</body>	
	
</html>