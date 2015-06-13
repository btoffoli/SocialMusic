
<%@ page import="br.com.ufes.dwws.socialMusic.Music" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'music.label', default: 'Music')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-music" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="music.index.create.label"/></g:link></li>
			</ul>
		</div>
		<div id="list-music" class="content scaffold-list" role="main">
			<h1><g:message code="music.index.listlabel"/></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
				<tr>
					<g:sortableColumn property="name" title="${message(code: 'music.name.label', default: 'Nome')}" />
					<th><g:message code="music.album.label" default="Album" /></th>
				</tr>
			</thead>
				<tbody>
				<g:each in="${musicInstanceList}" status="i" var="musicInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${musicInstance.id}">${fieldValue(bean: musicInstance, field: "name")}</g:link></td>
						<td><g:link action="show" controller="album" id="${musicInstance.album.id}">${fieldValue(bean: musicInstance, field: "album.name")}</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${musicInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
