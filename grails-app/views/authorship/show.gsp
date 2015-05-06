
<%@ page import="br.com.ufes.dwws.socialMusic.Authorship" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'authorship.label', default: 'Authorship')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-authorship" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index">Lista de Autorias</g:link></li>
				<li><g:link class="create" action="create">Nova Autoria</g:link></li>
			</ul>
		</div>
		<div id="show-authorship" class="content scaffold-show" role="main">
			<h1>Dados da Autoria</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list authorship">
			
				<g:if test="${authorshipInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="authorship.name.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${authorshipInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${authorshipInstance?.page}">
				<li class="fieldcontain">
					<span id="page-label" class="property-label"><g:message code="authorship.page.label" default="Pagina" /></span>
					
						<span class="property-value" aria-labelledby="page-label"><g:link url="${authorshipInstance?.page}" target="_blank">Link</g:link></span>
					
				</li>
				</g:if>

				<g:if test="${authorshipInstance?.albuns}">
				<li class="fieldcontain">
					<span id="albuns-label" class="property-label"><g:message code="authorship.albuns.label" default="Albuns" /></span>
					
						<g:each in="${authorshipInstance.albuns}" var="a">
						<span class="property-value" aria-labelledby="albuns-label"><g:link controller="album" action="show" id="${a.id}">${a?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:authorshipInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${authorshipInstance}">Editar</g:link>
					<g:actionSubmit class="delete" action="delete" value="Deletar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
