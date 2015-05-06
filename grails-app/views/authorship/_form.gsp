<%@ page import="br.com.ufes.dwws.socialMusic.Authorship" %>


<div class="fieldcontain ${hasErrors(bean: authorshipInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="authorship.name.label" default="Nome" />
		
	</label>
	<g:textField name="name" value="${authorshipInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: authorshipInstance, field: 'page', 'error')} ">
	<label for="page">
		<g:message code="authorship.page.label" default="Pagina" />
		
	</label>
	<g:textField name="page" value="${authorshipInstance?.page}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: authorshipInstance, field: 'albuns', 'error')} ">
	<label for="albuns">
		<g:message code="authorship.albuns.label" default="Albuns" />
		
	</label>
	<g:select name="albuns" from="${br.com.ufes.dwws.socialMusic.Album.list()}" multiple="multiple" optionKey="id" size="5" value="${authorshipInstance?.albuns*.id}" optionValue="name" class="many-to-many"/>
</div>