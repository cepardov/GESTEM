<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="col s12">
    <div class="row">
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
            <div class="col s3 m2 center">
                <a class="tooltipped" data-position="" data-delay="2000" data-tooltip="Añade, administra y edita ${c.name}s" href="<g:createLink controller="${c.logicalPropertyName}" action="index" />">
                    <div class="card blue-grey darken-1 waves-effect waves-light">
                        <div class="card-content small white-text">
                            <i class="material-icons medium">
                                <g:if test="${c.name=="Usuario"}">person</g:if>
                                <g:elseif test="${c.name=="Dashboard"}">dashboard</g:elseif>
                                <g:elseif test="${c.name=="Ciudad"}">location_city</g:elseif>
                                <g:elseif test="${c.name=="Comuna"}">location_on</g:elseif>
                                <g:elseif test="${c.name=="Funcionario"}">face</g:elseif>
                                <g:elseif test="${c.name=="Institucion"}">business</g:elseif>
                                <g:else>settings_applications</g:else></i>
                            <p class=""><strong>${c.name}</strong></p>
                        </div>
                    </div>
                </a>
            </div>
        </g:each>
    </div>
</div>

</body>
</html>