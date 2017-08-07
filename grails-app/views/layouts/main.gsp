<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Grails"/></title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <asset:stylesheet src="materialize.css" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <g:layoutHead/>
</head>

<body class="grey lighten-4">
<div class="navbar-fixed">
    <nav class="nav-extended white">
        <div class="nav-wrapper blue darken-2">
            <ul class="left">
                <a href="http://gestem.liceopac.cl:8080" class="brand-logo"><i class="material-icons">cloud</i>GESTEM</a>
                <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
            </ul>
            <ul class="right">
                <ul class="hide-on-med-and-down">
                    <sec:ifLoggedIn>
                        <li><a class="dropdown-button" href="#!" data-activates="dropdown1"><sec:loggedInUserInfo field='fullName'/><i class="material-icons right">arrow_drop_down</i></a></li>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <li><g:link controller="login" action="auth">Inicio de Sesión</g:link></li>
                    </sec:ifNotLoggedIn>
                </ul>
            </ul>
        </div>
        <div class="nav-content">
            <sec:ifLoggedIn>
                <nav>
                    <div class="nav-wrapper white z-depth-1">
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
                                <g:if test="${params.paisId}">
                                    <a href="<g:createLink controller="pais" action="index"/>" class="breadcrumb-2 flow-text"><g:message code="controllerName.pais.name" /></a>
                                </g:if>
                                <g:if test="${params.regionId}">
                                    <a href="<g:createLink controller="region" action="index" params="[paisId: params.paisId]"/>" class="breadcrumb-2 flow-text"><g:message code="controllerName.region.name" /></a>
                                </g:if>
                                <g:if test="${params.ciudadId}">
                                    <a href="<g:createLink controller="ciudad" action="index" params="[regionId: params.regionId]"/>" class="breadcrumb-2 flow-text"><g:message code="controllerName.ciudad.name" /></a>
                                </g:if>

                                <g:if test="${actionName == 'show'}">
                                    <a href="<g:createLink controller="${controllerName}" action="index"/>" class="breadcrumb-2 flow-text"><g:message code="controllerName.${controllerName}.name" /></a>
                                    <a href="<g:createLink controller="${controllerName}" action="show"/>" class="breadcrumb-2 flow-text">Mostar <g:message code="controllerName.${controllerName}.name" /></a>
                                </g:if>
                                <g:else>
                                    <a href="<g:createLink controller="${controllerName}" action="index"/>" class="breadcrumb-2 flow-text"><g:message code="controllerName.${controllerName}.name" /></a>
                                </g:else>
                            </g:else>
                            <g:if test="${actionName == 'show'}">
                                <a class="flow-text grey-text">
                                    | ${params.name} ${params.lastName}
                                </a>
                            </g:if>
                            <g:else>
                                <a class="flow-text right grey-text">
                                    <g:if test="${controllerName == 'dashboard'}">${grailsApplication.controllerClasses.count {this}} elementos</g:if>
                                    <g:if test="${controllerName == 'pais'}">${paisCount ?: 0} elementos</g:if>
                                    <g:if test="${controllerName == 'usuario'}">${usuarioCount ?: 0} elementos</g:if>
                                    <g:if test="${controllerName == 'region'}">${regionCount ?: 0} elementos</g:if>
                                    <g:if test="${controllerName == 'user'}">${userCount ?: 0} elementos</g:if>
                                    <g:if test="${controllerName == 'pago'}">${pagoCount ?: 0} elementos</g:if>
                                    <g:if test="${controllerName == 'reserva'}">${reservaCount ?: 0} elementos</g:if>
                                    <g:if test="${controllerName == 'sucursal'}">${sucursalCount ?: 0} elementos</g:if>
                                </a>
                            </g:else>
                        </div>
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
<asset:javascript src="jquery-3.2.1.min.js"/>
<asset:javascript src="materialize.js"/>
<asset:javascript src="config.js"/>
<asset:javascript src="typeahead.bundle.min.js"/>
<g:if test="${flash.message}">
    <script>Materialize.toast('${flash.message}', 10000);</script>
</g:if>

<!-- Specific modals -->
<g:if test="${controllerName == 'user'}">
    <g:if test="${actionName == 'show'}">
        <g:if test="${params.idDireccion}">
            <script>
                $(document).ready(function(){
                    $('#modalEdicionDireccion').modal('open');
                });
            </script>
        </g:if>
        <g:if test="${params.idTelefono}">
            <script>
                $(document).ready(function(){
                    $('#modalEdicionTelefono').modal('open');
                });
            </script>
        </g:if>
        <g:if test="params.idCorreo">
            <script>
                $(document).ready(function(){
                    $('#modalEdicionCorreo').modal('open');
                });
            </script>
        </g:if>
    </g:if>
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