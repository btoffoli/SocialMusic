<%@ page import="br.com.ufes.dwws.socialMusic.Account" %>



<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'login', 'error')} ">
	<label for="login">
		<g:message code="account.login.label" default="Login" />
		
	</label>
	<g:textField name="login" value="${accountInstance?.login}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="account.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${accountInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="account.password.label" default="Password" />
		
	</label>
	<g:passwordField name="password" />
</div>

