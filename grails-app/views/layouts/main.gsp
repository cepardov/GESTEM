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

<body class="grey lighten-4">
<div class="navbar-fixed">
    <nav class="nav-extended white">
        <div class="nav-wrapper blue darken-2">
            <a href="http://gestem.liceopac.cl:8080" class="brand-logo"><i class="material-icons">cloud</i>GESTEM</a>
            <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
                <sec:ifLoggedIn>
                    <li><a href="http://gestem.liceopac.cl:8080">Inicio</a></li>
                    <li><a class="dropdown-button" href="#!" data-activates="dropdown1"><sec:loggedInUserInfo field='fullName'/><i class="material-icons right">arrow_drop_down</i></a></li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <li><a class="dropdown-button" href="#!" data-activates="dropdown1">Controladores<i class="material-icons right">arrow_drop_down</i></a></li>
                    <li><g:link controller="login" action="auth">Inicio de Sesión</g:link></li>
                </sec:ifNotLoggedIn>
            </ul>
        </div>
        <div class="nav-content">
        <!-- extend -->
            <sec:ifLoggedIn>
                <nav>
                    <div class="nav-wrapper white">
                        <ul class="left">
                            <a href="#" data-activates="slide-out" class="button-collapse show-on-large grey-text"><i class="material-icons">menu</i></a>
                        </ul>
                        <div class="col s12 light">
                            <g:if test="${request.getRequestURI().toString()=='/'}">
                                <a href="" class="breadcrumb-2 flow-text"><g:message code="controllerName.null.name" /></a>
                            </g:if>
                            <g:else>
                                <a href="/" class="breadcrumb-2 flow-text"><g:message code="controllerName.null.name" /></a>
                                <g:if test="${controllerName != 'dashboard'}">
                                    <a href="<g:createLink controller="dashboard" action="index"/>" class="breadcrumb-2 flow-text"><g:message code="controllerName.dashboard.name" /></a>
                                </g:if>
                                <g:if test="${actionName == 'show'}">
                                    <a href="<g:createLink controller="${controllerName}" action="index"/>" class="breadcrumb-2 flow-text"><g:message code="controllerName.${controllerName}.name" /></a>
                                    <a href="<g:createLink controller="${controllerName}" action="show"/>" class="breadcrumb-2 flow-text">Mostar <g:message code="controllerName.${controllerName}.name" /></a>
                                </g:if>
                                <g:else>
                                    <a href="<g:createLink controller="${controllerName}" action="index"/>" class="breadcrumb-2 flow-text"><g:message code="controllerName.${controllerName}.name" /></a>
                                </g:else>
                            </g:else>


                        </div>
                        <!--



                        <ul class="left">
                            <a class="flow-text grey-text darken-1" href="">Consola de administracion</a>
                        </ul>
                        <ul class="left grey-text flow-text"> | </ul>
                        <ul class="left">
                            <a class="flow-text grey-text darken-1" href="<g:createLink controller="${controllerName}" action="index" />"><g:layoutTitle/><g:if test="${params.paisName}"> de ${params.paisName}</g:if></a>
                        </ul>
                        <ul class="left grey-text"> &nbsp;</ul>
                        <g:if test="${actionName == 'show'}">
                            <ul class="left flow-text grey-text">
                                | ${params.name} ${params.lastName}
                            </ul>
                        </g:if>
                        <g:else>
                            <ul class="left grey-text">
                                <g:if test="${controllerName == 'dashboard'}">${grailsApplication.controllerClasses.count {this}} elementos</g:if>
                                <g:if test="${controllerName == 'pais'}">${paisCount ?: 0} elementos</g:if>
                                <g:if test="${controllerName == 'usuario'}">${usuarioCount ?: 0} elementos</g:if>
                                <g:if test="${controllerName == 'region'}">${regionCount ?: 0} elementos</g:if>
                                <g:if test="${controllerName == 'contrato'}">${contratoCount ?: 0} elementos</g:if>
                                <g:if test="${controllerName == 'pago'}">${pagoCount ?: 0} elementos</g:if>
                                <g:if test="${controllerName == 'reserva'}">${reservaCount ?: 0} elementos</g:if>
                                <g:if test="${controllerName == 'sucursal'}">${sucursalCount ?: 0} elementos</g:if>
                            </ul>
                        </g:else>

                        <ul class="right">
                        </ul>
                        -->
                    </div>
                </nav>
            </sec:ifLoggedIn>
        </div>
    </nav>
</div>

<br>
<br>

<sec:ifLoggedIn>
    <ul id="dropdown1" class="dropdown-content">
        <li><g:link controller="login" action="logout">Salir</g:link></li>
        <li class="divider"></li>
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
            <li><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
        </g:each>
    </ul>
    <ul id="session" class="dropdown-content">
        <li><g:link controller="login" action="logout">Cerrar Sesión</g:link></li>
        <li class="divider"></li>
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
            <li><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
        </g:each>
    </ul>
</sec:ifLoggedIn>



<ul id="slide-out" class="side-nav">
    <sec:ifLoggedIn>
        <li><div class="userView">
            <div class="background">
                <asset:image src="material-design.jpg"/>
            </div>
            <a href="#!user"><img class="circle" src="http://materializecss.com/images/yuna.jpg"></a>
            <a href="#!name"><span class="white-text name">NEW_SECURITY</span></a>
            <a href="#!email"><span class="white-text email">NEW_SECURITY@liceopac.cl</span></a>
        </div></li>
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
            <li><g:link controller="${c.logicalPropertyName}"><i class="material-icons">
                <g:if test="${c.name=="Usuario"}">person</g:if>
                <g:elseif test="${c.name=="Dashboard"}">dashboard</g:elseif>
                <g:elseif test="${c.name=="Ciudad"}">location_city</g:elseif>
                <g:elseif test="${c.name=="Comuna"}">location_on</g:elseif>
                <g:elseif test="${c.name=="Funcionario"}">face</g:elseif>
                <g:elseif test="${c.name=="Institucion"}">business</g:elseif>
                <g:else>settings_applications</g:else></i>${c.name}</g:link></li>
        </g:each>
        <li><g:link controller="login" action="logout"><i class="material-icons">exit_to_app</i>Cerrar Sesión</g:link></li>
        <li><div class="divider"></div></li>
        <li><a class="subheader">Subheader</a></li>
        <li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <li><div class="userView">
            <div class="background">
                <asset:image src="material-design.jpg"/>
            </div>
            <a href="#!user"><asset:image src="cropped-Logo.png" width="128"/></a>
            <a href="#!name"><span class="white-text flow-text"><strong>Liceo Pedro Aguirre Cerda</strong></span></a>
        </div></li>
        <li><a href="http://gestem.liceopac.cl:8080"><i class="material-icons">home</i>Inicio</a></li>
        <li><g:link controller="login" action="login"><i class="material-icons">vpn_key</i>Inicio de Sesión</g:link></li>
        <li><div class="divider"></div></li>
        <li><a class="subheader">Subheader</a></li>
        <li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
    </sec:ifNotLoggedIn>
</ul>

<br>
<g:layoutBody/>
<!--Import jQuery before materialize.js-->
<asset:javascript src="jquery-2.1.1.min.js"/>
<asset:javascript src="materialize.js"/>
<asset:javascript src="config.js"/>
<asset:javascript src="typeahead.bundle.min.js"/>
<g:if test="${flash.message}">
    <script>Materialize.toast('${flash.message}', 10000);</script>
</g:if>
<g:if test="${params.id}">
<script>
    window.onload = function() {
        document.getElementById('clickButton').click();
    }
</script>
</g:if>
<g:else>
    <g:if test="${params.r}">
        <script>
            window.onload = function() {
                document.getElementById('clickButtonCreate').click();
            }
        </script>
    </g:if>
</g:else>
</body>
</html>