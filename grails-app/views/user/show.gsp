<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
    <asset:stylesheet src="autocomplete.css"/>
</head>
<body>

<div class="col s12">
    <ul class="tabs grey lighten-4 z-depth-1">
        <li class="tab col s3"><a class="blue-grey-text" href="#datospersonales">Datos Personales</a></li>
        <li class="tab col s3"><a class="blue-grey-text" href="#ubicacion">Contacto y ubicación</a></li>
        <li class="tab col s3"><a class="blue-grey-text" href="#roles">Perfil de usuario</a></li>
        <li class="tab col s3 disabled"><a class="blue-grey-text" href="#">Datos Apoderado</a></li>
    </ul>
</div>
<div class="row">
    <div id="datospersonales" class="col s12">
        <g:form class="col s12" resource="${this.user}" method="PUT">
            <h3>Datos Personales</h3>
            <div class="row">
                <div class="input-field col s12 m1">
                    <f:input class="tooltipped" length="12" maxlength="13" property="rut" id="rut" bean="user" data-position="bottom" data-delay="50" data-tooltip="Ej: 12345678-k"/>
                    <label for="rut">RUT</label>
                </div>
                <div class="input-field col s12 m1">
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
                <div class="col s12 m2">
                    <label for="materno">Fecha Nacimiento</label>
                    <input type="text" value="${fechaNacimientoOut}" name="fechaNacimientoDat" class="datepicker">
                </div>
                <div class="input-field inline col s12 m2">
                    <f:input property="username" id="username" bean="user"/>
                    <label for="username">username</label>
                </div>
                <div class="col s12 right-align">
                    <button name="create" class="btn waves-effect waves-light tooltipped" type="submit" data-position="left" data-delay="50" data-tooltip="Guardar Datos Personales">Guardar
                        <i class="material-icons left">save</i>
                    </button>
                </div>
            </div>
        </g:form>
    </div>

    <div id="ubicacion" class="col s12">
        <h3 id="direccion">Direcciones</h3>
        <div class="row">
            <table class="responsive-table bordered highlight-2 centered">
                <thead>
                <tr>
                    <th>Direccion</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="v" in="${direccionList}">
                    <tr>
                        <td>${v.comuna.ciudad.region.pais.name}, ${v.comuna.ciudad.region.name}, ${v.comuna.name}, ${v.address}</td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" controller="user" action="show" id="${this.user.id}" params="[idDireccion : v.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Editar Dirección"><i class="material-icons">edit</i></g:link>
                            <g:link class="btn-floating waves-effect waves-light red tooltipped" controller="direccion" action="eliminar" id="${v.id}" params="[r : 'showUser',idUser : this.user.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Eliminar Dirección"><i class="material-icons">delete</i></g:link>
                        </td>
                    </tr>
                </g:each>
                <tr>
                    <td></td>
                    <td>
                        <a class="create modal-trigger btn-floating waves-effect waves-light green tooltipped" data-target="modalCreateDireccion" data-position="left" data-delay="50" data-tooltip="Agregar Dirección"><i class="material-icons">add</i></a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <g:paginate total="${userCount ?: 0}" />
        </div>
        <h3 id="telefono">Teléfono</h3>
        <div class="row">
            <table class="responsive-table bordered highlight-2 centered">
                <thead>
                <tr>
                    <th>Teléfono</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="v" in="${telefonoList}">
                    <tr>
                        <td>${v.phoneNumber}</td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" controller="user" action="show" id="${this.user.id}" params="[idTelefono : v.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Editar Teléfono"><i class="material-icons">edit</i></g:link>
                            <g:link class="btn-floating waves-effect waves-light red tooltipped" controller="telefono" action="eliminar" id="${v.id}" params="[r : 'showUser',idUser : this.user.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Eliminar Teléfono"><i class="material-icons">delete</i></g:link>
                        </td>
                    </tr>
                </g:each>
                <tr>
                    <td></td>
                    <td>
                        <a class="create modal-trigger btn-floating waves-effect waves-light green tooltipped" data-target="modalCreateTelefono" data-position="left" data-delay="50" data-tooltip="Agregar Teléfono"><i class="material-icons">add</i></a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <g:paginate total="${userCount ?: 0}" />
        </div>
        <h3 id="correo">Correo Electrónico</h3>
        <div class="row">
            <table class="responsive-table bordered highlight-2 centered">
                <thead>
                <tr>
                    <th>Correo</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="v" in="${correoList}">
                    <tr>
                        <td>${v.email}</td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" controller="user" action="show" id="${this.user.id}" params="[idCorreo : v.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Editar Teléfono"><i class="material-icons">edit</i></g:link>
                            <g:link class="btn-floating waves-effect waves-light red tooltipped" controller="correo" action="eliminar" id="${v.id}" params="[r : 'showUser',idUser : this.user.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Eliminar Teléfono"><i class="material-icons">delete</i></g:link>
                        </td>
                    </tr>
                </g:each>
                <tr>
                    <td></td>
                    <td>
                        <a class="create modal-trigger btn-floating waves-effect waves-light green tooltipped" data-target="modalCreateCorreo" data-position="left" data-delay="50" data-tooltip="Agregar Correo"><i class="material-icons">add</i></a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <g:paginate total="${userCount ?: 0}" />
        </div>
    </div>
    <div id="roles" class="col s12">
        <h3>Roles de Usuario</h3>
        <div class="row">
            <table class="responsive-table bordered highlight-2 centered">
                <thead>
                <tr>
                    <th>Role</th>
                    <th>Role Descipción</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="v" in="${userRoleList}">
                    <tr>
                        <td>[100${v.role.id}] ${v.role.name}</td>
                        <td>${v.role.description}</td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light red tooltipped" action="deleteRole" params="[r : 'showUser',idUser : this.user.id, idRole:v.role.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Eliminar Teléfono"><i class="material-icons">delete</i></g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <g:form action="saveRole" params="[r : 'showUser', idUser : this.user.id, name : params.name, lastName : params.lastName]">
            <input name="user" value="${this.user.id}" hidden>
            <div class="input-field col s12 m6">
                <select name="role" required="" id="role">
                    <option value="" disabled selected>Seleccione Rol</option>
                    <g:each var="v" in="${roleList}">
                        <option value="${v.id}">${v.name}</option>
                    </g:each>
                </select>
                <label>Agregar Roles</label>
            </div>
            <div class="col s12 m6">
                <button name="create" class="btn waves-effect waves-light tooltipped" type="submit" data-position="left" data-delay="50" data-tooltip="Agregar Rol Seleccionado">Agregar
                    <i class="material-icons left">save</i>
                </button>
            </div>
        </g:form>
        <div class="pagination">
            <g:paginate total="${userCount ?: 0}" />
        </div>
    </div>
</div>







<g:if test="${actionName != 'save'}">
    <div class="row">






    </div>


    <!-- Modal Create Direccion Structure -->
    <div id="modalCreateDireccion" class="modal bottom-sheet">
        <div class="modal-content">
            <h5>Agregar Direccion</h5>
            <div class="row">
                <g:form action="save" controller="direccion" params="[r : 'showUser', idUser : this.user.id, name : params.name, lastName : params.lastName]">
                    <input name="user" value="${this.user.id}|" hidden>
                    <div class="row">
                        <div id="comunaDiv" class="input-field col s12 m3">
                            <input id="comuna" name="comuna" type="text" class="validate typeahead" placeholder="Escriba una comuna">
                        </div>
                        <div class="input-field col s12 m9">
                            <input id="address" name="address" type="text" class="validate">
                            <label for="address">Direccion</label>
                        </div>
                    </div>
                    <!-- Menu Modal Create Direction Actions-->
                    <div class="fixed-action-btn">
                        <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.create.label', default: 'Create')}" type="submit" data-position="left" data-delay="50" data-tooltip="Guardar ${controllerName}"><i class="material-icons right">send</i></button>
                        <a class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></a>
                    </div>
                </g:form>
            </div>
        </div>
    </div>

    <g:if test="${params.idDireccion}">
        <!-- Modal Edicion Direccion Structure -->
        <div id="modalEdicionDireccion" class="modal bottom-sheet">
            <div class="modal-content">
                <h5>Editar Direccion</h5>
                <div class="row">
                    <g:form class="col s12" action="update" controller="direccion" id="${params.idDireccion}" method="PUT" params="[r : 'showUser', idUser : this.user.id, name : params.name, lastName : params.lastName]">
                        <div class="row">
                            <div class="input-field col s12 m9">
                                <input id="addressEdit" value="${direccion}" name="address" type="text" class="validate">
                                <label for="addressEdit">Direccion</label>
                            </div>
                        </div>
                        <!-- Menu Modal Update-->
                        <div class="fixed-action-btn">
                            <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.update.label', default: 'Update')}" type="submit" data-position="left" data-delay="50" data-tooltip="Actualizar ${controllerName}"><i class="material-icons right">send</i></button>
                            <g:link action="show" id="${this.user.id}" fragment="direccion" params="[name : params.name, lastName : params.lastName]" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>

    </g:if>

    <!-- Modal Create Telefono Structure -->
    <div id="modalCreateTelefono" class="modal bottom-sheet">
        <div class="modal-content">
            <h5>Agregar Telefono</h5>
            <div class="row">
                <g:form action="save" controller="telefono" params="[r : 'showUser', idUser : this.user.id, name : params.name, lastName : params.lastName]">
                    <input name="user" value="${this.user.id}" hidden>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <i class="material-icons prefix">phone</i>
                            <input id="icon_telephone" name="phoneNumber" type="tel" class="validate">
                            <label for="icon_telephone">Teléfono</label>
                        </div>
                    </div>
                    <!-- Menu Modal Create Telefono Actions-->
                    <div class="fixed-action-btn">
                        <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.create.label', default: 'Create')}" type="submit" data-position="left" data-delay="50" data-tooltip="Guardar Teléfono"><i class="material-icons right">send</i></button>
                        <a class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></a>
                    </div>
                </g:form>
            </div>
        </div>
    </div>

    <g:if test="${params.idTelefono}">
        <!-- Modal Edicion Telefono Structure -->
        <div id="modalEdicionTelefono" class="modal bottom-sheet">
            <div class="modal-content">
                <h5>Editar Direccion</h5>
                <div class="row">
                    <g:form class="col s12" action="update" controller="telefono" id="${params.idTelefono}" method="PUT" params="[r : 'showUser', idUser : this.user.id, name : params.name, lastName : params.lastName]">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <i class="material-icons prefix">phone</i>
                                <input id="icon_telephoneE" value="${telefono}" name="phoneNumber" type="tel" class="validate">
                                <label for="icon_telephoneE">Teléfono</label>
                            </div>
                        </div>
                        <!-- Menu Modal Update-->
                        <div class="fixed-action-btn">
                            <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.update.label', default: 'Update')}" type="submit" data-position="left" data-delay="50" data-tooltip="Actualizar Teléfono"><i class="material-icons right">send</i></button>
                            <g:link action="show" id="${this.user.id}" fragment="telefono" params="[name : params.name, lastName : params.lastName]" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </g:if>

    <!-- Modal Create Correo Structure -->
    <div id="modalCreateCorreo" class="modal bottom-sheet">
        <div class="modal-content">
            <h5>Agregar Correo</h5>
            <div class="row">
                <g:form action="save" controller="correo" params="[r : 'showUser', idUser : this.user.id, name : params.name, lastName : params.lastName]">
                    <input name="user" value="${this.user.id}" hidden>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input id="email" name="email" type="email" class="validate">
                            <label for="email">Correo Electrónico</label>
                        </div>
                    </div>
                    <!-- Menu Modal Create Telefono Actions-->
                    <div class="fixed-action-btn">
                        <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.create.label', default: 'Create')}" type="submit" data-position="left" data-delay="50" data-tooltip="Guardar Correo"><i class="material-icons right">send</i></button>
                        <a class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></a>
                    </div>
                </g:form>
            </div>
        </div>
    </div>

    <g:if test="${params.idCorreo}">
        <!-- Modal Edicion Correo Structure -->
        <div id="modalEdicionCorreo" class="modal bottom-sheet">
            <div class="modal-content">
                <h5>Editar Correo</h5>
                <div class="row">
                    <g:form class="col s12" action="update" controller="correo" id="${params.idCorreo}" method="PUT" params="[r : 'showUser', idUser : this.user.id, name : params.name, lastName : params.lastName]">
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="emailE" name="email" value="${correo}" type="email" class="validate">
                                <label for="emailE">Correo Electrónico</label>
                            </div>
                        </div>
                        <!-- Menu Modal Update-->
                        <div class="fixed-action-btn">
                            <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.update.label', default: 'Update')}" type="submit" data-position="left" data-delay="50" data-tooltip="Actualizar Correo"><i class="material-icons right">send</i></button>
                            <g:link action="show" id="${this.user.id}" fragment="correo" params="[name : params.name, lastName : params.lastName]" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </g:if>

    <asset:javascript src="jquery-3.2.1.min.js"/>
    <asset:javascript src="typeahead.bundle.min.js"/>
    <script>
        $(document).ready(function() {
            var substringMatcher = function(strs) {
                return function findMatches(q, cb) {
                    var matches, substringRegex;
                    matches = [];
                    substrRegex = new RegExp(q, 'i');
                    $.each(strs, function(i, str) {
                        if (substrRegex.test(str)) {
                            matches.push(str);
                        }
                    });
                    cb(matches);
                };
            };
            var comuna = [
                <g:each in="${gestem.Comuna.list()}">
                '${it.id} | ${it.name} ${it.ciudad.region.name} ${it.ciudad.region.pais.name}',
                </g:each>
            ];
            $('#comunaDiv .typeahead').typeahead({
                hint: true,
                highlight: true,
                minLength: 1
            }, {
                name: 'comuna',
                source: substringMatcher(comuna)
            });
        });
    </script>
</g:if>
<g:else>
    <h5>Crear Usuario</h5>
    <div class="row">
        <g:form action="save">
            <div class="row">
                <div class="input-field col s12 m2">
                    <f:input class="tooltipped" length="12" maxlength="13" property="rut" id="rut" bean="user" data-position="bottom" data-delay="50" data-tooltip="Ej: 12345678-k"/>
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
            </div>

            <!-- Menu Modal Create-->
            <div class="fixed-action-btn">
                <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.create.label', default: 'Create')}" type="submit" data-position="left" data-delay="50" data-tooltip="Guardar ${controllerName}"><i class="material-icons right">send</i></button>
                <a class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></a>
            </div>
        </g:form>
    </div>
</g:else>
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
