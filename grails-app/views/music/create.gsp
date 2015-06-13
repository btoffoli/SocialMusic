<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'music.label', default: 'Music')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-music" class="skip" tabindex="-1"><g:message code="default.link.skip.label" /></a>
		<div class="nav" role="navigation">
			<ul>
				 <li><g:link class="list" action="index"><g:message code="music.create.listlabel"/></g:link></li>
			</ul>
		</div>
		<div id="create-music" class="content scaffold-create" role="main">
			<h1><g:message code="music.create.label"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${musicInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${musicInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:musicInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code:'default.music.form.saveButton')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
