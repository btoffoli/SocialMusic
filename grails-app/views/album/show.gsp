
<%@ page import="br.com.ufes.dwws.socialMusic.Album" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'album.label', default: 'Album')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-album" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index"><g:message code="album.show.listbutton"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="album.show.newbutton"/></g:link></li>
			</ul>
		</div>
		<div id="show-album" class="content scaffold-show" role="main">
			<h1><g:message code="album.show.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list album">


				<g:if test="${rdfAlbumData?.image}">
					<li class="fieldcontain">
						<img src="${rdfAlbumData.image}" alt="${rdfAlbumData.image}" style="max-width: 400px; max-height: 400px"/>
					</li>
				</g:if>

				<g:if test="${albumInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="album.data.Name" /></span>
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${albumInstance}" field="name"/></span>
				</li>
				</g:if>
			
				<g:if test="${albumInstance?.page}">
				<li class="fieldcontain">
					<span id="page-label" class="property-label"><g:message code="album.data.Page"  /></span>
						<span class="property-value" aria-labelledby="page-label"><g:link url="${albumInstance?.page}" target="_blank">Link</g:link></span>
					
				</li>
				</g:if>

				<g:if test="${albumInstance?.musics}">
				<li class="fieldcontain">
					<span id="musics-label" class="property-label"><g:message code="album.musics.label"  /></span>
					
						<g:each in="${albumInstance.musics}" var="m">
						<span class="property-value" aria-labelledby="musics-label"><g:link controller="music" action="show" id="${m.id}">${m?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>

				<g:if test="${rdfAlbumData?.release}">
					<li class="fieldcontain">
						<span id="release-label" class="property-label"><g:message code="album.data.ReleaseYear"  /></span>
						<span class="property-value" aria-labelledby="name-label">${rdfAlbumData.release}</span>
					</li>
				</g:if>

				<g:if test="${rdfAlbumData?.awards}">
					<li class="fieldcontain">
						<span id="awards-label" class="property-label"><g:message code="album.data.Awards"   /></span>
						<span class="property-value" aria-labelledby="name-label">${rdfAlbumData.awards}</span>
					</li>
				</g:if>

				<g:if test="${rdfAlbumData?.abstract}">
					<li class="fieldcontain">
						<span>${rdfAlbumData.abstract}</span>
					</li>
				</g:if>


			</ol>
			<g:form url="[resource:albumInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${albumInstance}"><g:message code="album.show.editbutton"/></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code:'default.album.show.deletebutton')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
