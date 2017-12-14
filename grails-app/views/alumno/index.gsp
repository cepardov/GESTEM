<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="container">
    <h4>Alumnos</h4>
    <div class="row">
        <div class="input-field col s12 m3">
            <select>
                <option value="" disabled selected>Elija Jornada</option>
                <option value="1">Option 1</option>
                <option value="2">Option 2</option>
                <option value="3">Option 3</option>
            </select>
            <label>Jornada</label>
        </div>
        <div class="input-field col s12 m2">
            <select>
                <option value="" disabled selected>Elija Grado</option>
                <option value="1">Option 1</option>
                <option value="2">Option 2</option>
                <option value="3">Option 3</option>
            </select>
            <label>Grado</label>
        </div>
        <div class="input-field col s12 m2">
            <select>
                <option value="" disabled selected>Elija Curso</option>
                <option value="1">Option 1</option>
                <option value="2">Option 2</option>
                <option value="3">Option 3</option>
            </select>
            <label>Curso</label>
        </div>
        <div class="input-field col s12 m3">
            <a class="waves-effect waves-light btn large">button</a>
        </div>
    </div>
</div>
<div class="slider col s12 m12">
    <div id="list-user" class="content scaffold-list" role="main">
        <div class="row">
            <table class="responsive-table bordered highlight-2 centered">
                <thead>
                <tr>
                    <th>RUT</th>
                    <th>User</th>
                    <th>Nombre</th>
                    <th>Nombres</th>
                    <th>Paterno</th>
                    <th>Materno</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="v" in="${alumnoList}">
                    <tr onclick="location='<g:createLink controller="user" action="show" id="${v.id}" params="[name : v.nombre, lastName : v.paterno]"/>'">
                        <td>${v.rut}</td>
                        <td>${v.username}</td>
                        <td>${v.nombre}</td>
                        <td>${v.segNombre}</td>
                        <td>${v.paterno}</td>
                        <td>${v.materno}</td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light blue tooltipped" action="show" params="[name : v.nombre, lastName : v.paterno]" id="${v.id}" data-position="left" data-delay="50" data-tooltip="Ver Usuario"><i class="material-icons">remove_red_eye</i></g:link>
                            <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" id="${v.id}" data-position="left" data-delay="50" data-tooltip="Editar Usuario"><i class="material-icons">edit</i></g:link>
                            <g:link class="btn-floating waves-effect waves-light red tooltipped" action="eliminar" id="${v.id}" data-position="left" data-delay="50" data-tooltip="Eliminar Usuario"><i class="material-icons">delete</i></g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <g:paginate total="${userCount ?: 0}" />
        </div>
    </div>
</div>


</body>
</html>