<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="container">
    <sec:ifLoggedIn>
        <h4>Institucion Cargada:<sec:loggedInUserInfo field='asd'/></h4>
    </sec:ifLoggedIn>

    <h3>Gestem</h3>
    <p>Es un proyecto de software para la gestión de educación que reemplazará completamente a <strong>Napsis</strong>
     que pretende ser inicialmente gratuito para el <strong>Liceo Pedro Aguirre Cerda</strong> bajo la licencia GLP V3.0 CL
    , hasta el momento se han creado ${grailsApplication.controllerClasses.size()} modulo(s) la cual puede tener acceso en el listado de abajo.
    El desarrollo esta a cargo del Ingeniero (E) Informática <strong>Cristian Pardo</strong></p>
    <p>Este sistema esta desarrollado en Java ${System.getProperty('java.version')}, Groovy ${GroovySystem.getVersion()}
    y utilizando el framework Grails <g:meta name="info.app.grailsVersion"/> y Materialize 0.98.</p>
    <p>Actualmente esta en desarrollo (${grails.util.Environment.current.name}) y esta en su versión <g:meta name="info.app.version"/></p>

    <h5>Lista de controladores:</h5>

    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
        <li class="controller">
            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
        </li>
    </g:each>

    <p>Fecha de inicio desarrollo: 23-03-2017</p>
    <p>Fecha de termino: 13-12-2017 (Aproximadamente)</p>

    <p>Lista de cambios: <a href="https://github.com/cepardov/Gestem/commits/master">Lista de cambios</a></p>
</div>
</body>
</html>