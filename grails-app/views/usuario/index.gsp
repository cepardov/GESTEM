<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="slider col s12 m12">
    <div id="list-usuario" class="content scaffold-list" role="main">
        <div class="row">
            <table class="responsive-table bordered highlight centered">
                <thead>
                <tr>
                    <th>RUT</th>
                    <th>Usuario</th>
                    <th>nombre</th>
                    <th>Paterno</th>
                    <th>Materno</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="v" in="${usuarioList}">
                    <tr>
                        <td>${v.rut}</td>
                        <td>${v.usuario}</td>
                        <td>${v.nombre}</td>
                        <td>${v.paterno}</td>
                        <td>${v.materno}</td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" id="${v.id}" data-position="left" data-delay="50" data-tooltip="Editar ${controllerName}"><i class="material-icons">edit</i></g:link>
                            <g:link class="btn-floating waves-effect waves-light red tooltipped" action="eliminar" id="${v.id}" data-position="left" data-delay="50" data-tooltip="Eliminar ${controllerName}"><i class="material-icons">delete</i></g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <g:paginate total="${usuarioCount ?: 0}" />
        </div>
    </div>
</div>
<!-- Menu flotante Crear -->
<div class="fixed-action-btn">
    <a class="create waves-effect waves-light btn-floating btn-large teal tooltipped" href="#modalCreate" data-position="left" data-delay="50" data-tooltip="Agregar ${controllerName}"><i class="material-icons">add</i></a>
</div>

<!-- Modal Creacion Structure -->
<div id="modalCreate" class="modal bottom-sheet">
    <div class="modal-content">
        <h5>Crear ${controllerName}</h5>
        <div class="row">
        <!---rut, nombre, paterno, clave, materno, valor, estado, usuario, valorHoraExtra--->
            <g:form action="save">
                <div class="row">
                    <div class="input-field col s12 m2">
                        <f:input class="tooltipped" length="12" maxlength="13" property="rut" id="rut" bean="usuario" data-position="bottom" data-delay="50" data-tooltip="Ej: 12345678-k"/>
                        <label for="rut">RUT</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="nombre">nombre</label>
                        <f:input property="nombre" id="nombre" bean="usuario"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="paterno">Paterno</label>
                        <f:input property="paterno" id="paterno" bean="usuario"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="materno">Materno</label>
                        <f:input property="materno" id="materno" bean="usuario"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="clave">clave</label>
                        <f:input property="clave" id="clave" bean="usuario"/>
                    </div>
                    <div class="input-field inline col s12 m4">
                        <f:input property="usuario" id="usuario" bean="usuario"/>
                        <label for="usuario">usuario</label>
                    </div>
                </div>

                <!-- Menu Modal Create-->
                <div class="fixed-action-btn">
                    <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.create.label', default: 'Create')}" type="submit" data-position="left" data-delay="50" data-tooltip="Guardar ${controllerName}"><i class="material-icons right">send</i></button>
                    <a class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></a>
                </div>
            </g:form>
        </div>
    </div>
</div>

<!-- Modal Edicion Structure -->
<div id="modalEdicion" class="modal bottom-sheet">
    <div class="modal-content">
        <h5>Editar ${controllerName}</h5>
        <div class="row">
        <!---rut, nombre, paterno, clave, materno, valor, estado, usuario, valorHoraExtra--->
            <g:form class="col s12" resource="${this.usuario}" method="PUT">
                <div class="row">
                    <div class="input-field col s12 m2">
                        <f:input class="tooltipped" length="12" maxlength="13" property="rut" id="rut" bean="usuario" data-position="bottom" data-delay="50" data-tooltip="Ej: 12345678-k"/>
                        <label for="rut">RUT</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="nombre">nombre</label>
                        <f:input property="nombre" id="nombre" bean="usuario"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="paterno">Paterno</label>
                        <f:input property="paterno" id="paterno" bean="usuario"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="materno">Materno</label>
                        <f:input property="materno" id="materno" bean="usuario"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="clave">clave</label>
                        <f:input property="clave" id="clave" bean="usuario"/>
                    </div>
                    <div class="input-field inline col s12 m4">
                        <f:input property="usuario" id="usuario" bean="usuario"/>
                        <label for="usuario">usuario</label>
                    </div>
                </div>

                <!-- Menu Modal Update-->
                <div class="fixed-action-btn">
                    <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.update.label', default: 'Update')}" type="submit" data-position="left" data-delay="50" data-tooltip="Actualizar ${controllerName}"><i class="material-icons right">send</i></button>
                    <g:link action="index" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                </div>
            </g:form>
        </div>
    </div>
</div>
<g:if test="${params.id}">
    <a type="hidden" href="#modalEdicion" data-position="left" data-delay="50" id="clickButton"></a>
</g:if>
<g:hasErrors bean="${this.usuario}">
    <ul class="errors" role="alert">
        <g:eachError bean="${this.usuario}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>
</body>
</html>