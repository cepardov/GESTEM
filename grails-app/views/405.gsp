<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="row">
    <div class="col s12 m3 center-align">
        <h1>:'(</h1>
    </div>
    <div class="col s12 m9">
        <h3>Error de metodo de envío</h3>
        <sec:ifLoggedIn>
            <h4>Vuelva a reingresar los datos del formulario.</h4>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <h4>Probablemente su sesión caducó, vuelva a iniciar sesión.</h4>
        </sec:ifNotLoggedIn>
    </div>
</div>
</body>
</html>