
<%@ page import="br.com.ufes.dwws.socialMusic.Music" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:javascript src="youtube.js"/>
		<g:javascript src="music/show.js"/>
		<g:set var="entityName" value="${message(code: 'music.label', default: 'Music')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-music" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index">Lista de Musicas</g:link></li>
				<li><g:link class="create" action="create">Nova Musica</g:link></li>
			</ul>
		</div>
		<div id="show-music" class="content scaffold-show" role="main">
			<h1>Dados da Musica</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list music">

				<g:if test="${musicInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="music.name.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${musicInstance}" field="name"/></span>
					
				</li>
				</g:if>

				<g:if test="${musicInstance?.album}">
				<li class="fieldcontain">
					<span id="album-label" class="property-label"><g:message code="music.album.label" default="Album" /></span>
					<span class="property-value" aria-labelledby="album-label">
						<g:link controller="album" action="show" id="${musicInstance?.album?.id}">${musicInstance?.album?.name}</g:link>
					</span>
				</li>
				</g:if>

				<g:if test="${musicInstance?.url}">
					<li class="fieldcontain">
						<span id="name-label" class="property-label"><g:message code="music.url.label" default="URL" /></span>
						
							<span class="property-value" aria-labelledby="url-label"><a href="${musicInstance?.url}" target="new"><g:fieldValue bean="${musicInstance}" field="url"/></a></span>
						
					</li>

					<li class="fieldcontain">
						<g:hiddenField name="videoURL" value="${musicInstance?.url}" />
						<div id="mainPlayer"></div>
					</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:musicInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${musicInstance}">Editar</g:link>
					<g:actionSubmit class="delete" action="delete" value="Deletar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
