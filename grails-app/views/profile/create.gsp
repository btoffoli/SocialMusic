<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index">Lista de Profiles</g:link></li>
			</ul>
		</div>
		<div id="create-profile" class="content scaffold-create" role="main">
			<h1>Novo Profile</h1>
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
			<g:form url="[resource:profileInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code:'default.profile.create.saveButton')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
