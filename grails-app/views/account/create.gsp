<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-account" class="skip" tabindex="-1"><g:message code="default.button.myAlbum.label"></g:message></a>
		<div class="nav" role="navigation">
			<ul>
			</ul>
		</div>
		<div id="create-account" class="content scaffold-create" role="main">
			<h1>Nova Conta</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${accountInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${accountInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:accountInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="Salvar" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
