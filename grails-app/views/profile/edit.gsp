<%@ page import="br.com.ufes.dwws.socialMusic.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index">Lista de Profiles</g:link></li>
				<li><g:link class="create" action="create">Novo Profile</g:link></li>
			</ul>
		</div>
		<div id="edit-profile" class="content scaffold-edit" role="main">
			<h1>Editar Profile</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${profileInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${profileInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form url="[resource:profileInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${profileInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="Salvar" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
