<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'music.label', default: 'Music')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<link rel="stylesheet" href="${resource(dir: 'js/jquery-ui-1.11.4.custom/', file: 'jquery-ui.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'js/jquery-ui-1.11.4.custom/', file: 'jquery-ui.structure.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'js/jquery-ui-1.11.4.custom/', file: 'jquery-ui.theme.css')}" type="text/css">
		<style>
		.ui-autocomplete-loading {
			background: white url("${resource(dir: 'images', file: 'loading.gif')}") right center no-repeat;
		}
		</style>
		<g:javascript src="jquery-ui-1.11.4.custom/external/jquery/jquery.js"/>
		<g:javascript src="jquery-ui-1.11.4.custom/jquery-ui.js"/>
		<g:javascript src="music/create.js"/>

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
