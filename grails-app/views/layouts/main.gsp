<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Gestem"/></title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <asset:stylesheet src="materialize.css" media="screen,projection"/>
    <asset:stylesheet src="print.css" media="print"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <g:layoutHead/>
</head>
<body class="grey lighten-3">
<div class="navbar-fixed">
    <nav class="nav-extended">
        <div class="nav-wrapper blue darken-2">
            <a href="/" class="brand-logo">GESTEM
                <sec:ifLoggedIn>
                    <g:if test="${sec.loggedInUserInfo(field: 'institucion')}">
                        <span class="flow-text hide-on-med-and-down"><sec:loggedInUserInfo field='institucion'/></span>
                    </g:if>
                    <g:else>
                        <span class="flow-text hide-on-med-and-down">Consola administración</span>
                    </g:else>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <span class="flow-text hide-on-med-and-down grey-text text-lighten-1">
                        Gestión Educacional
                    </span>
                </sec:ifNotLoggedIn>
            </a>
            <a href="#" data-target="mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <sec:ifLoggedIn>
                    <li><a class="dropdown-trigger" href="#!" data-target="menu-functions">Herramientas<i class="material-icons right">arrow_drop_down</i></a></li>
                    <li><a class="dropdown-trigger" href="#!" data-target="menu-user"><sec:loggedInUserInfo field='fullName'/><i class="material-icons right">arrow_drop_down</i></a></li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <li><g:link controller="login" action="auth">Inicio de Sesión</g:link></li>
                </sec:ifNotLoggedIn>
            </ul>
            <ul class="sidenav" id="mobile">
                <!-- Menu Mobile -->
                <li><g:link controller="login" action="logout">Salir</g:link></li>
                <li class="divider"></li>
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
                    <li><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
                </g:each>
            </ul>
        </div>
        <div class="nav-content">
            <sec:ifLoggedIn>
                <nav class="hide-on-med-and-down">
                    <div class="nav-wrapper white z-depth-1">
                        <ul>
                            <g:if test="${controllerName}">
                                <li><a href="javascript:history.back()"><i class="material-icons grey-text darken-3">keyboard_backspace</i></a></li>
                            </g:if>

                            <g:if test="${request.getRequestURI().toString()=='/'}">
                                <li><span class="flow-text grey-text"><g:message code="controllerName.null.name" /></span></li>
                            </g:if>
                            <g:else>
                                <g:if test="${controllerName == 'user'}">
                                    <g:if test="${this.user.isStudent}">
                                        <li><g:link controller="alumno" action="index" class="flow-text grey-text"><g:message code="alumno.label" /></g:link></li>
                                    </g:if>
                                    <g:else>
                                        <li><a href="/${controllerName}" class="flow-text grey-text"><g:message code="controllerName.${controllerName}.name" /></a></li>
                                    </g:else>
                                </g:if>
                                <g:else>
                                    <li><a href="/${controllerName}" class="flow-text grey-text"><g:message code="controllerName.${controllerName}.name" /></a></li>
                                </g:else>
                            </g:else>

                            <g:if test="${controllerName == 'user' && actionName == 'show'}">
                                <li><a class="flow-text grey-text">${user.nombre} ${user.paterno}</a></li>
                            </g:if>

                            <g:if test="${actionName == 'show'}">
                                <li class="right"><a onclick="window.print()" class="grey-text"><i class="material-icons">print</i></a></li>
                                <li class="right"><a onclick="myFunction()" class="grey-text"><i class="material-icons">save</i></a></li>
                            </g:if>
                        </ul>
                    </div>
                </nav>
            </sec:ifLoggedIn>
        </div>
    </nav>
    <sec:ifLoggedIn>
        <ul id="menu-user" class="dropdown-content">
            <li><a href="#!"><sec:loggedInUserInfo field='fullName'/></a></li>
            <li class="divider"></li>
            <li><g:link controller="login" action="logout">Salir</g:link></li>
        </ul>
        <ul id="menu-functions" class="dropdown-content">
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
                <g:if test="${c.logicalPropertyName == 'login'}"></g:if>
                <g:elseif test="${c.logicalPropertyName == 'logout'}"></g:elseif>
                <g:elseif test="${c.logicalPropertyName == 'dashboard'}"></g:elseif>
                <g:else>
                    <li><g:link controller="${c.logicalPropertyName}"><g:message code="${c.logicalPropertyName}.label" /></g:link></li>
                </g:else>
            </g:each>
        </ul>
    </sec:ifLoggedIn>

</div>
<br>
<br>
<br>
<g:layoutBody/>
<asset:javascript src="jquery-3.2.1.min.js"/>
<asset:javascript src="materialize.js"/>
<asset:javascript src="init-v1.js"/>

<g:if test="${flash.message}">
    <script>
        window.onload=function(){M.toast({html: '${flash.message}'});}
    </script>
</g:if>
<!-- Generic Modals -->
<g:if test="${actionName == 'index'}">
    <g:if test="${params.id}">
        <script>
            $(document).ready(function(){
                $('#modalEdicion').modal('open');
            });
        </script>
    </g:if>
</g:if>

</body>
</html>