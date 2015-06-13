<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'authorship.label', default: 'Authorship')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-authorship" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index"><g:message code="authorship.create.label"/></g:link></li>
			</ul>
		</div>
		<div id="create-authorship" class="content scaffold-create" role="main">
			<h1><g:message code="authorship.create.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${authorshipInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${authorshipInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:authorshipInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code:'default.authorship.createform.saveButton')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
