<%@ page import="br.com.ufes.dwws.socialMusic.Album" %>


<!--
<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'musics', 'error')} ">
	<label for="musics">
		<g:message code="album.musics.label" default="Musics" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${albumInstance?.musics?}" var="m">
    <li><g:link controller="music" action="show" id="${m.id}">${m?.name}</g:link></li>
</g:each>
<li class="add">
	<g:link controller="music" action="create" params="['album.id': albumInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'music.label', default: 'Music')])}</g:link>
</li>
</ul>

</div>
-->

<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="album.name.label" />
		
	</label>
	<g:textField name="name" value="${albumInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'page', 'error')}  ">
	<label for="page">
		<g:message code="album.page.label"  />
		
	</label>
	<g:textField name="page" value="${albumInstance?.page}" pattern="https?://.+"/>
</div>

<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'authorship', 'error')} required">
	<label for="authorship">
		<g:message code="default.button.myAuthorShip.label"/>
		<span class="required-indicator">*</span>
	</label>
	<g:select id="authorship" name="authorship.id" from="${br.com.ufes.dwws.socialMusic.Authorship.list()}" optionKey="id" required="" value="${musicInstance?.album?.id}" optionValue="name" class="many-to-one"/>
</div>

