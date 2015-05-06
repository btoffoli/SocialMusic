
<%@ page import="br.com.ufes.dwws.socialMusic.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create">Novo Profile</g:link></li>
			</ul>
		</div>
		<div id="list-profile" class="content scaffold-list" role="main">
			<h1>Lista de Profiles</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'profile.name.label', default: 'Nome')}" />
						<th><g:message code="profile.account.label" default="Conta" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${profileInstanceList}" status="i" var="profileInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${profileInstance.id}">${fieldValue(bean: profileInstance, field: "name")}</g:link></td>
						<td>${fieldValue(bean: profileInstance, field: "account.login")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${profileInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
