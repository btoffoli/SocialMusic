
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
				<li><g:link class="list" action="index"><g:message code="authorship.show.listbutton"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="authorship.show.newbutton"/></g:link></li>
			</ul>
		</div>
		<div id="show-authorship" class="content scaffold-show" role="main">
			<h1><g:message code="authorship.show.label"/> </h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list authorship">


				<g:if test="${authorshipInstance?.name}">
				<li class="fieldcontain" style="text-align: left">
					<span id="name-label" class="property-label" style="text-align: left; width: auto"><g:message code="authorship.data.Name"  /></span>

						<span class="property-value" aria-labelledby="name-label" style="margin-left: 110px" ><g:fieldValue bean="${authorshipInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${authorshipInstance?.page}">
				<li class="fieldcontain" style="text-align: left">
					<span id="page-label" class="property-label" style="text-align: left; width: auto"><g:message code="authorship.data.Page"  /></span>
					
						<span class="property-value" aria-labelledby="page-label" style="margin-left: 110px"><g:link url="${authorshipInstance?.page}" target="_blank">Link</g:link></span>

				</li>
				</g:if>

				<g:if test="${authorshipInstance?.albuns}">
				<li class="fieldcontain" style="text-align: left">
					<span id="albuns-label" class="property-label" style="text-align: left; width: auto"><g:message code="authorship.data.Albums"   /></span>
					
						<g:each in="${authorshipInstance.albuns}" var="a">
						<span class="property-value" aria-labelledby="albuns-label" style="margin-left: 110px"><g:link controller="album" action="show" id="${a.id}">${a?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>

				<g:if test="${rdfAuthorshipData?.releaseYear}">
					<li class="fieldcontain" style="text-align: left">
						<span id="releaseYear-label" class="property-label" style="text-align: left; width: auto"><g:message code="authorship.data.Formation"   /></span>

						<span class="property-value" aria-labelledby="albuns-label" style="margin-left: 110px">${rdfAuthorshipData.releaseYear}</span>


					</li>
				</g:if>

				<g:if test="${rdfAuthorshipData?.image}">
					<li class="fieldcontain">
						<img src="${rdfAuthorshipData.image}" alt="${rdfAuthorshipData.image}" style="max-width: 400px; max-height: 400px; float:left"/>
					</li>
				</g:if>

				<g:if test="${rdfAuthorshipData?.abstract}">
					<li class="fieldcontain">
						<span aria-labelledby="albuns-label">${rdfAuthorshipData.abstract}</span>
					</li>
				</g:if>

				<hr />

				<g:if test="${rdfMembers}">
					<g:each in="${rdfMembers}">
						<li class="fieldcontain" style="margin-left: 10px; float: left; width: 135px; text-align: left">
							<span>${it.name}</span>
						<br>
							<img src="${it.image}" alt="${it.image}" style=" float: left; max-width: 120px; max-height: 150px"/>
						</li>
					</g:each>
				</g:if>
			
			</ol><div style="clear :both"></div>
			<g:form url="[resource:authorshipInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${authorshipInstance}"><g:message code="authorship.show.editbutton"/></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code:'default.music.show.deletebutton')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
