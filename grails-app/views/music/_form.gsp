<%@ page import="br.com.ufes.dwws.socialMusic.Music" %>

<div class="fieldcontain ${hasErrors(bean: musicInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="music.name.label" default="Nome" />
		
	</label>
	<g:textField name="name" value="${musicInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: musicInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="music.url.label"  />
		
	</label>
	<g:textField name="url" value="${musicInstance?.url}" pattern="https?://.+"/>
</div>

<div class="fieldcontain ${hasErrors(bean: musicInstance, field: 'album', 'error')} required">
	<label for="album">
		<g:message code="music.album.label"/>
		<span class="required-indicator">*</span>
	</label>
	<g:select id="album" name="album.id" from="${br.com.ufes.dwws.socialMusic.Album.list()}" optionKey="id" required="" value="${musicInstance?.album?.id}" optionValue="name" class="many-to-one"/>
</div>