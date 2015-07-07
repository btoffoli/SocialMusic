
<%@ page import="br.com.ufes.dwws.socialMusic.Authorship" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'authorship.label', default: 'Authorship')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-authorship" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="authorship.index.newbutton"/></g:link></li>
				<li><g:link class="rdf" controller="RDF" action="authorships" target="_blank"><g:message code="rdf.button.label" default="RDF"/></g:link></li>
			</ul>
		</div>
		<div id="list-authorship" class="content scaffold-list" role="main">
			<h1><g:message code="authorship.index.label"/></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
				<tr>
					<g:sortableColumn property="name" title="${message(code: 'authorship.name.label', default: 'Nome')}" />
					<g:sortableColumn property="page" title="${message(code: 'authorship.page.label', default: 'Pagina')}" />
				</tr>
				</thead>
				<tbody>
				<g:each in="${authorshipInstanceList}" status="i" var="authorshipInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${authorshipInstance.id}">${fieldValue(bean: authorshipInstance, field: "name")}</g:link></td>
					
						<td><g:link url="${authorshipInstance?.page}" target="_blank">Link</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${authorshipInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
