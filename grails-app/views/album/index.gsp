
<%@ page import="br.com.ufes.dwws.socialMusic.Album" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code:'album.label')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-album" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="album.index.newbutton"/></g:link></li>
				<li><g:link class="rdf" controller="RDF" action="albuns"><g:message code="authorship.index.newbuttonx" default="RDF"/></g:link></li>
			</ul>
		</div>
		<div id="list-album" class="content scaffold-list" role="main">
			<h1><g:message code="album.namelist.label"></g:message></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'album.name.label', default: 'Nome')}" />
						<g:sortableColumn property="page" title="${message(code: 'album.page.label', default: 'Pagina')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${albumInstanceList}" status="i" var="albumInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${albumInstance.id}">${fieldValue(bean: albumInstance, field: "name")}</g:link></td>
					
						<td><g:link url="${albumInstance?.page}" target="_blank">Link</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${albumInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
