<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<ul id="order" class="dropdown-content">
    <li><g:link action="index" params="[sort: 'rut', order: 'asc']">RUT Asendente</g:link></li>
    <li><g:link action="index" params="[sort: 'rut', order: 'desc']">RUT descendente</g:link></li>
    <li class="divider"></li>
    <li><g:link action="index" params="[sort: 'username', order: 'asc']">Usuario Asendente</g:link></li>
    <li><g:link action="index" params="[sort: 'username', order: 'desc']">Usuario descendente</g:link></li>
    <li class="divider"></li>
    <li><g:link action="index" params="[sort: 'nombre', order: 'asc']">Nombre Asendente</g:link></li>
    <li><g:link action="index" params="[sort: 'nombre', order: 'desc']">Nombre descendente</g:link></li>
    <li class="divider"></li>
    <li><g:link action="index" params="[sort: 'paterno', order: 'asc']">Paterno Asendente</g:link></li>
    <li><g:link action="index" params="[sort: 'paterno', order: 'desc']">Paterno descendente</g:link></li>
</ul>
<g:if test="${params.q != null}">
    <div class="container">
        <div class="row center-align">
            <h5>Buscar un usuario</h5>
            <g:form class="col s12" action="index" method="get">
                <div class="input-field col s6">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="icon_prefix" name="q" value="${params.q}" type="text" class="validate">
                    <label for="icon_prefix">First Name</label>
                </div>
                <div class="col s12 center-align">
                    <g:link class="waves-effect waves-light btn" action="index"><i class="material-icons left">close</i>Cerrar Búsqueda</g:link>
                    <button class="btn waves-effect waves-light" type="submit">Buscar Usuario
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </g:form>
        </div>
    </div>
</g:if>
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
                <g:each var="v" in="${userList}">
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
<!-- Menu flotante Crear -->
<div class="fixed-action-btn">
    <a class="create modal-trigger waves-effect waves-light btn-floating btn-large teal tooltipped" data-target="modalCreate" data-position="left" data-delay="50" data-tooltip="Agregar ${controllerName}"><i class="material-icons">add</i></a>
</div>

<!-- Modal Creacion Structure -->
<div id="modalCreate" class="modal bottom-sheet">
    <div class="modal-content">
        <h5>Crear Usuario</h5>
        <div class="row">
            <g:form action="save">
                <div class="row">
                    <div class="input-field col s12 m2">
                        <f:input class="tooltipped" length="12" maxlength="13" property="rut" id="rut" bean="user" data-position="bottom" data-delay="50" data-tooltip="Ej: 12345678-k" oninput="checkRut(this)"/>
                        <label for="rut">RUT</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="nombre">Primer Nombre</label>
                        <f:input property="nombre" id="nombre" bean="user"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="nombre">Nombres</label>
                        <f:input property="segNombre" id="segNombre" bean="user"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="paterno">Paterno</label>
                        <f:input property="paterno" id="paterno" bean="user"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="materno">Materno</label>
                        <f:input property="materno" id="materno" bean="user"/>
                    </div>
                    <div class="input-field col s12 m2">
                        <label for="password">password</label>
                        <f:input property="password" id="password" bean="user"/>
                    </div>
                    <div class="input-field inline col s12 m2">
                        <f:input property="username" id="username" bean="user"/>
                        <label for="username">username</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <select name="userType.id" required="" id="userType">
                            <option value="" disabled <g:if test="${!params.userType}">selected</g:if>>Seleccione Tipo Usuario</option>
                            <g:each var="v" in="${userTypeList}">
                                <option value="${v.id}" <g:if test="${v.name == params.userType}">selected</g:if>>${v.name}</option>
                            </g:each>
                        </select>
                        <label>Tipo Usuario</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <select name="institucion" required="" id="institucion">
                            <option value="" disabled <g:if test="${!sec.loggedInUserInfo(field: 'institucion')}">selected</g:if>>Seleccione Institucion</option>
                            <g:if test="${!sec.loggedInUserInfo(field: 'institucion')}">
                                <option value="">Aplicación</option>
                            </g:if>
                            <g:each var="v" in="${institucionList}">
                                <option value="${v.id}" <g:if test="${v.name == sec.loggedInUserInfo(field: 'institucion')}">selected</g:if>>${v.name}</option>
                            </g:each>
                        </select>
                        <label>Institución</label>
                    </div>
                </div>

                <!-- Menu Modal Create-->
                <div class="fixed-action-btn">
                    <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.create.label', default: 'Create')}" type="submit" data-position="left" data-delay="50" data-tooltip="Guardar Usuario"><i class="material-icons right">send</i></button>
                    <a class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></a>
                </div>
            </g:form>
        </div>
    </div>
</div>

<g:if test="${params.id}">
    <!-- Modal Edicion Structure -->
    <div id="modalEdicion" class="modal bottom-sheet">
        <div class="modal-content">
            <h5>Editar Usuario</h5>
            <div class="row">
                <g:form class="col s12" resource="${this.user}" method="PUT">
                    <div class="row">
                        <div class="input-field col s12 m2">
                            <f:input class="tooltipped" length="12" maxlength="13" property="rut" id="rut" bean="user" data-position="bottom" data-delay="50" data-tooltip="Ej: 12345678-k"/>
                            <label for="rut">RUT</label>
                        </div>
                        <div class="input-field col s12 m2">
                            <label for="nombre">nombre</label>
                            <f:input property="nombre" id="nombre" bean="user"/>
                        </div>
                        <div class="input-field col s12 m2">
                            <label for="paterno">Paterno</label>
                            <f:input property="paterno" id="paterno" bean="user"/>
                        </div>
                        <div class="input-field col s12 m2">
                            <label for="materno">Materno</label>
                            <f:input property="materno" id="materno" bean="user"/>
                        </div>
                        <div class="input-field inline col s12 m2">
                            <f:input property="username" id="username" bean="user"/>
                            <label for="username">username</label>
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
</g:if>
<g:hasErrors bean="${this.user}">
    <g:eachError bean="${this.user}" var="error">
        <script>
            window.onload=function(){
                Materialize.toast('<g:message error="${error}"/>', 10000);
            }
        </script>
    </g:eachError>
</g:hasErrors>
</body>
</html>