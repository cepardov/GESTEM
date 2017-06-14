<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Grails"/></title>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <asset:stylesheet src="materialize.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <g:layoutHead/>
</head>

<body>
<div class="navbar-fixed">
    <ul id="dropdown1" class="dropdown-content">
        <li><g:link controller="login" action="logout">Salir</g:link></li>
        <li class="divider"></li>
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
            <li><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
        </g:each>
        <!--
        <li><a href="#!">one</a></li>
        <li><a href="#!">two</a></li>
        <li class="divider"></li>
        <li><a href="#!">three</a></li>
        -->
    </ul>
    <ul id="session" class="dropdown-content">
        <li><g:link controller="login" action="logout">Cerrar Sesión</g:link></li>
        <li class="divider"></li>
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
            <li><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
        </g:each>
    <!--
        <li><a href="#!">one</a></li>
        <li><a href="#!">two</a></li>
        <li class="divider"></li>
        <li><a href="#!">three</a></li>
        -->
    </ul>
    <nav>
        <div class="nav-wrapper blue-grey lighten-1">
            <a href="#!" class="brand-logo"><i class="material-icons">cloud</i>GESTEM</a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <g:if test="${session.usuarioLogueado}">
                    <li><a href="http://gestem.liceopac.cl:8080">Inicio</a></li>
                    <li><a class="dropdown-button" href="#!" data-activates="dropdown1">${session.usuarioLogueado.nombre} ${session.usuarioLogueado.paterno} ${session.usuarioLogueado.materno}<i class="material-icons right">arrow_drop_down</i></a></li>
                </g:if>
                <g:else>
                    <li><a class="dropdown-button" href="#!" data-activates="dropdown1">Controladores<i class="material-icons right">arrow_drop_down</i></a></li>
                    <li><g:link controller="login" action="login">Inicio de Sesión</g:link></li>
                </g:else>
            </ul>

            <ul class="side-nav" id="mobile-demo">
                <li><a href="sass.html">Sass</a></li>
                <li><a href="badges.html">Components</a></li>
                <li><a href="collapsible.html">Javascript</a></li>
                <li><a href="mobile.html">Mobile</a></li>
            </ul>
        </div>
    </nav>
</div>
<g:layoutBody/>
<!--Import jQuery before materialize.js-->
<asset:javascript src="jquery-2.1.1.min.js"/>
<asset:javascript src="config.js"/>
<asset:javascript src="materialize.js"/>
</body>
</html>