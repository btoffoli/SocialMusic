	
<%@ page import="br.com.ufes.dwws.socialMusic.Account" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-account" class="skip" tabindex="-1"><g:message code="default.link.skip.label"/></a>
		<div class="nav" role="navigation">
			<ul>
			</ul>
		</div>
		<div id="show-account" class="content scaffold-show" role="main">
			<h1>Dados da Conta</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list account">
			
				<g:if test="${accountInstance?.login}">
				<li class="fieldcontain">
					<span id="login-label" class="property-label"><g:message code="account.login.label" default="Login" /></span>
					
						<span class="property-value" aria-labelledby="login-label"><g:fieldValue bean="${accountInstance}" field="login"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="account.email.label" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${accountInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:accountInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${accountInstance}">Editar</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code:'default.account.show.deletebutton')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Tem certeza?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
