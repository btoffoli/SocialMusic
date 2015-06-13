
<%@ page import="br.com.ufes.dwws.socialMusic.Music" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code:'music.label')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-music" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index"><g:message code="music.show.listbutton"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="music.show.newbutton"/></g:link></li>
			</ul>
		</div>
		<div id="show-music" class="content scaffold-show" role="main">
			<h1><g:message code="music.show.datalabel"/> </h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list music">

				<g:if test="${musicInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="music.name.label"  /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${musicInstance}" field="name"/></span>
					
				</li>
				</g:if>

				<g:if test="${musicInstance?.album}">
				<li class="fieldcontain">
					<span id="album-label" class="property-label"><g:message code="music.album.label"  /></span>
					<span class="property-value" aria-labelledby="album-label">
						<g:link controller="album" action="show" id="${musicInstance?.album?.id}">${musicInstance?.album?.name}</g:link>
					</span>
				</li>
				</g:if>

				<g:if test="${musicInstance?.url}">
					<li class="fieldcontain">
						<span id="name-label" class="property-label"><g:message code="music.url.label"  /></span>
						
							<span class="property-value" aria-labelledby="url-label"><a href="${fieldValue(bean:itemInstance, field:'uri')}" target="new"><g:fieldValue bean="${musicInstance}" field="url"/></a></span>
						
					</li>

					<li class="fieldcontain">
						<g:hiddenField name="videoURL" value="${musicInstance?.url}" />
						<div id="mainPlayer"></div>
					</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:musicInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${albumInstance}"><g:message code="music.show.editbutton2"/></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code:'default.music.show.deletebutton')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>

		<script>
		
		//STARTING YOUTUBE API
		
		var tag = document.createElement('script');
		tag.src = "https://www.youtube.com/iframe_api";
		var firstScriptTag = document.getElementsByTagName('script')[0];
		firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

		
		//EVENT LOGIC
		
		function onYouTubeIframeAPIReady() {

			var videoURL = document.getElementById("videoURL").value;
			var videoID = extractVideoID(videoURL);
		
			var mainPlayer = new YT.Player('mainPlayer', {
				height: '390',
				width: '640',
				videoId: videoID,
				events: {
					'onReady': function(event) {
						event.target.playVideo();
					}
				}
			});
		}

		//UTILS

		function extractVideoID(url) {
			var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
		    var match = url.match(regExp);
		    if (match&&match[7].length==11){
		        return match[7];
		    }else{
		        console.log("URL invalida");
		        return false;
		    }
		}
	  
    </script>
	</body>
</html>
