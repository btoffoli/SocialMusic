
<%@ page import="br.com.ufes.dwws.socialMusic.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code:'profile.label')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index">Lista de Profiles</g:link></li>
				<li><g:link class="create" action="create">Novo Profile</g:link></li>
			</ul>
		</div>
		<div id="show-profile" class="content scaffold-show" role="main">
			<img src="${profileInstance?.avatarURL}" alt="Avatar" style="max-width:200px; height:auto; margin-left: 10px; margin-top: 10px;" >
			<h1></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list profile">
			
				<g:if test="${profileInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="profile.name.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${profileInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="profile.account.label" default="Conta" /></span>
					
						<span class="property-value" aria-labelledby="account-label">
							<g:link controller="account" action="show" id="${profileInstance?.account?.id}">${profileInstance?.account?.login}</g:link>
						</span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:profileInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${profileInstance}">Editar</g:link>
					<g:actionSubmit class="delete" action="delete" value="Deletar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
