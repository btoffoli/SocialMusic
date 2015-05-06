<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}" />
    <title><g:message code="default.login.label" args="[entityName]" default="Login" /></title>
</head>
<body>
<a href="#create-account" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div id="create-account" class="content scaffold-create" role="main">
    <h1><g:message code="default.login.label" args="[entityName]" default="Entrar" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${accountInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${accountInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form url="[resource:accountInstance, action:'signIn']" >
        <fieldset class="form">
            <%@ page import="br.com.ufes.dwws.socialMusic.Account" %>
            <div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'login', 'error')} ">
                <label for="login">
                    <g:message code="account.login.label" default="Login" />
                </label>
                <g:textField name="login" value="${accountInstance?.login}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'password', 'error')} ">
                <label for="password">
                    <g:message code="account.password.label" default="Password" />
                </label>
                <g:passwordField name="password" value="${accountInstance?.password}"/>
            </div>

        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="loginButton" class="save" value="${message(code: 'default.button.login.label', default: 'Entrar')}" />
            <g:actionSubmit value="Criar" class="save" action="create"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
