<%@ page import="br.com.ufes.dwws.socialMusic.Authorship" %>


<div class="fieldcontain ${hasErrors(bean: authorshipInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="authorship.name.label" default="Nome" />
		
	</label>
	<g:textField name="name" value="${authorshipInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: authorshipInstance, field: 'page', 'error')} ">
	<label for="page">
		<g:message code="authorship.page.label" />
		
	</label>
	<g:textField name="page" value="${authorshipInstance?.page}" pattern="https?://.+"/>
</div>
