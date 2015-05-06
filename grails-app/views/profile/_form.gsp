<%@ page import="br.com.ufes.dwws.socialMusic.Profile" %>



<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="profile.name.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${profileInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'avatarURL', 'error')} required">
	<label for="avatarURL">
		<g:message code="profile.avatarURL.label" default="URL para Avatar" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="avatarURL" required="" value="${profileInstance?.avatarURL}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'account', 'error')} required">
	<label for="account">
		<g:message code="profile.account.label" default="Conta" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="account" name="account.id" from="${br.com.ufes.dwws.socialMusic.Account.list()}" optionKey="id" required="" value="${profileInstance?.account?.id}" optionValue="login" class="many-to-one"/>
</div>

