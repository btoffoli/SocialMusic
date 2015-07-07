
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
				<li><g:link class="list" action="index"><g:message code="music.show.listbutton"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="music.show.newbutton"/></g:link></li>
				<li><g:link class="rdf" controller="RDF" action="music" id="${musicInstance.id}"><g:message code="authorship.index.newbuttonx" default="RDF"/></g:link></li>
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
					<span id="name-label" class="property-label" style="text-align: left; width: auto"><g:message code="music.data.Name"  /></span>
					
						<span class="property-value" aria-labelledby="name-label" style="margin-left: 110px"><g:fieldValue bean="${musicInstance}" field="name"/></span>
					
				</li>
				</g:if>

				<g:if test="${musicInstance?.album}">
				<li class="fieldcontain">
					<span id="album-label" class="property-label" style="text-align: left; width: auto"><g:message code="music.data.Album"  /></span>
					<span class="property-value" aria-labelledby="album-label" style="margin-left: 110px">
						<g:link controller="album" action="show" id="${musicInstance?.album?.id}">${musicInstance?.album?.name}</g:link>
					</span>
				</li>
				</g:if>

				<g:if test="${rdfMusicData?.maxTime}">
					<li class="fieldcontain">
						<span id="time-label" class="property-label" style="text-align: left; width: auto"><g:message code="music.data.Duration"  default="Time"/></span>

						<span class="property-value" aria-labelledby="name-label" style="margin-left: 110px">${rdfMusicData.minTime} ~ ${rdfMusicData.maxTime}</span>

					</li>
				</g:if>

				<g:if test="${musicInstance?.url}">
					<li class="fieldcontain">
						<span id="name-label" class="property-label" style="text-align: left; width: auto"><g:message code="music.data.URL"  /></span>
						
							<span class="property-value" aria-labelledby="url-label" style="margin-left: 110px"><a href="${musicInstance?.url}" target="new"><g:fieldValue bean="${musicInstance}" field="url"/></a></span>
						
					</li>

					<li class="fieldcontain">
						<g:hiddenField name="videoURL" value="${musicInstance?.url}" />
						<div id="mainPlayer" style="text-align: left"></div>
					</li>
				</g:if>


			
			</ol>
			<g:form url="[resource:musicInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${musicInstance}"><g:message code="music.show.editbutton2"/></g:link>
					<g:actionSubmit class="delete" action="delete" value="Deletar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
