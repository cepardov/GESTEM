<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="container">
    <h5>Lista de controladores:</h5>

    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
        <li class="controller">
            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
        </li>
    </g:each>
</div>



</body>
</html>