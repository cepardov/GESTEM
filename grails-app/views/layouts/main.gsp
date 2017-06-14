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
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
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
        <div class="nav-wrapper blue darken-2">
            <a href="http://gestem.liceopac.cl:8080" class="brand-logo"><i class="material-icons">cloud</i>GESTEM</a>
            <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
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

            <ul id="slide-out" class="side-nav">
                <li><div class="userView">
                    <div class="background">
                        <img src="http://materializecss.com/images/office.jpg">
                    </div>
                    <a href="#!user"><img class="circle" src="http://materializecss.com/images/yuna.jpg"></a>
                    <a href="#!name"><span class="white-text name">John Doe</span></a>
                    <a href="#!email"><span class="white-text email">jdandturk@gmail.com</span></a>
                </div></li>
                <li><a href="#!"><i class="material-icons">cloud</i>First Link With Icon</a></li>
                <li><a href="#!">Second Link</a></li>
                <li><div class="divider"></div></li>
                <li><a class="subheader">Subheader</a></li>
                <li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
            </ul>
        </div>
    </nav>
</div>
<g:if test="${session.usuarioLogueado}">
    <div class="navbar-fixed-2">
        <nav>
            <div class="nav-wrapper white">
                <ul class="left">
                    <a href="#" data-activates="slide-out" class="button-collapse show-on-large grey-text"><i class="material-icons">menu</i></a>
                </ul>
                <ul class="left">
                    <a class="flow-text m_title grey-text darken-1" href="<g:createLink controller="dashboard" action="index" />">Consola de administracion</a>
                </ul>
                <!--
            <ul class="right hide-on-med-and-down">
                <li><a href="sass.html">Sass</a></li>
                <li><a href="badges.html">Components</a></li>
            </ul>
            -->
            </div>
        </nav>
    </div>
</g:if>
<br>
<g:layoutBody/>
<!--Import jQuery before materialize.js-->
<asset:javascript src="jquery-2.1.1.min.js"/>
<asset:javascript src="config.js"/>
<asset:javascript src="materialize.js"/>
</body>
</html>