<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<title>Dig - Chapter 7</title>

<link rel='stylesheet' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.0/themes/cupertino/jquery-ui.css'>
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.js'></script>
<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.js'></script>
<script src='../scripts/strophe.js'></script>
<script src='../scripts/flXHR.js'></script>
<script src='../scripts/strophe.flxhr.js'></script>

<link rel='stylesheet' href='dig.css'>
<script>
	var Dig = {
		connection : null,

		on_items : function(iq, elem) {
			var items = $(iq).find("item");
			if (items.length > 0) {
				$(elem).parent().append("<ul></ul>");

				var list = $(elem).parent().find("ul");

				$(iq).find("item").each(
						function() {
							var node = $(this).attr('node');
							list
									.append("<li><span class='item'>"
											+ $(this).attr("jid")
											+ (node ? ":" + node : "")
											+ "</span></li>");
						});
			}
		},

		on_info : function(iq, elem) {
			if ($('.selected').length > 0 && elem !== $('.selected')[0]) {
				return;
			}

			$('#feature-list').empty();
			$(iq).find("feature").each(
					function() {
						$('#feature-list').append(
								"<li>" + $(this).attr('var') + "</li>");
					});

			$('#identity-list').empty();
			$(iq).find("identity").each(
					function() {
						$('#identity-list').append(
								"<li><dl><dt>Name</dt><dd>"
										+ ($(this).attr('name') || "none")
										+ "</dd><dt>Type</dt><dd>"
										+ ($(this).attr('type') || "none")
										+ "</dd><dt>Category</dt><dd>"
										+ ($(this).attr('category') || "none")
										+ "</dd></dl></li>");
					});
		}
	};

	$(document)
			.ready(
					function() {
						$('#login_dialog').dialog({
							autoOpen : true,
							draggable : false,
							modal : true,
							title : 'Connect to XMPP',
							buttons : {
								"Connect" : function() {
									$(document).trigger('connect', {
										jid : $('#jid').val().toLowerCase(),
										password : $('#password').val(),
									});

									$('#password').val('');
									$(this).dialog('close');
								}
							}
						});

						$('#dig')
								.click(
										function() {
											var service = $('#service').val()
													.toLowerCase();
											$('#service').val('');

											// set up disco info pane
											$('#selected-name').text(service);
											$('#identity-list').empty();
											$('#feature-list').empty();

											// clear tree pane
											$('#tree').empty();

											$('#tree').append(
													"<li><span class='item selected'>"
															+ service
															+ "</span></li>");

											Dig.connection
													.sendIQ(
															$iq({
																to : service,
																type : "get"
															})
																	.c(
																			"query",
																			{
																				xmlns : "http://jabber.org/protocol/disco#info"
																			}),
															function(iq) {
																Dig
																		.on_info(
																				iq,
																				$('.selected')[0]);
															});

											Dig.connection
													.sendIQ(
															$iq({
																to : service,
																type : "get"
															})
																	.c(
																			"query",
																			{
																				xmlns : "http://jabber.org/protocol/disco#items"
																			}),
															function(iq) {
																Dig
																		.on_items(
																				iq,
																				$('.selected')[0]);
															});
										});

						$('#tree .item')
								.live(
										'click',
										function() {
											if ($(this).hasClass("selected")) {
												return;
											}

											$(".selected").removeClass(
													"selected");
											$(this).addClass("selected");

											var serv_node = $(this).text();
											var service, node;
											var idx = serv_node.indexOf(":");
											if (idx < 0) {
												service = serv_node;
												node = null;
											} else {
												service = serv_node.slice(0,
														idx);
												node = serv_node.slice(idx + 1);
											}

											var query_attrs;
											if (node) {
												query_attrs = {
													node : node
												};
											} else {
												query_attrs = {};
											}

											var elem = this;
											query_attrs["xmlns"] = "http://jabber.org/protocol/disco#info";
											Dig.connection.sendIQ($iq({
												to : service,
												type : "get"
											}).c("query", query_attrs),
													function(iq) {
														Dig.on_info(iq, elem);
													});

											if ($(".selected").parent().find(
													"ul").length === 0) {
												query_attrs["xmlns"] = "http://jabber.org/protocol/disco#items";
												Dig.connection.sendIQ($iq({
													to : service,
													type : "get"
												}).c("query", query_attrs),
														function(iq) {
															Dig.on_items(iq,
																	elem);
														});
											}
										});
					});

	$(document).bind(
			'connect',
			function(ev, data) {
				var conn = new Strophe.Connection(
						'http://bosh.metajack.im:5280/xmpp-httpbind');

				conn.connect(data.jid, data.password, function(status) {
					if (status === Strophe.Status.CONNECTED) {
						$(document).trigger('connected');
					} else if (status === Strophe.Status.DISCONNECTED) {
						$(document).trigger('disconnected');
					}
				});

				Dig.connection = conn;
			});

	$(document).bind('connected', function() {
		$('#dig').removeAttr('disabled');
	});
</script>
</head>
<body>
	<h1>Dig</h1>

	<div id='input-bar'>
		<input id='service' type='text'> <input id='dig' type='button' value='Dig!' disabled='disabled'>
	</div>

	<div class='clear'></div>

	<div class='left'>
		<ul id='tree'>
		</ul>
	</div>

	<div class='right'>
		<h2 id='selected-name'></h2>
		<div id='disco-info'>
			<h3>Identities:</h3>
			<ul id='identity-list'>
			</ul>

			<h3>Features:</h3>
			<ul id='feature-list'>
			</ul>
		</div>
	</div>

	<!-- login dialog -->
	<div id='login_dialog' class='hidden'>
		<label>JID:</label><input type='text' id='jid'> <label>Password:</label><input type='password' id='password'>
	</div>
</body>
</html>