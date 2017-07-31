<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
    <asset:stylesheet src="autocomplete.css"/>
</head>
<body>

<div class="row">
    <g:form class="col s12" resource="${this.usuario}" method="PUT">
        <h3>Datos Personales</h3>
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
    </g:form>

<!-- Menu flotante Crear
    <div class="fixed-action-btn">
        <a class="create waves-effect waves-light btn-floating btn-large teal tooltipped" href="#modalCreateDireccion" data-position="left" data-delay="50" data-tooltip="Agregar ${controllerName}"><i class="material-icons">add</i></a>
    </div>
-->
    <h3>Direcciones</h3>
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
                        <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" controller="usuario" action="show" id="${this.usuario.id}" params="[idDireccion : v.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Editar Dirección"><i class="material-icons">edit</i></g:link>
                        <g:link class="btn-floating waves-effect waves-light red tooltipped" controller="direccion" action="eliminar" id="${v.id}" params="[r : 'showUsuario',idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Eliminar Dirección"><i class="material-icons">delete</i></g:link>
                    </td>
                </tr>
            </g:each>
            <tr>
                <td></td>
                <td>
                    <a class="create btn-floating waves-effect waves-light green tooltipped" href="#modalCreateDireccion" data-position="left" data-delay="50" data-tooltip="Agregar Dirección"><i class="material-icons">add</i></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <g:paginate total="${usuarioCount ?: 0}" />
    </div>
    <h3>Teléfono</h3>
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
                        <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" controller="usuario" action="show" id="${this.usuario.id}" params="[idTelefono : v.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Editar Teléfono"><i class="material-icons">edit</i></g:link>
                        <g:link class="btn-floating waves-effect waves-light red tooltipped" controller="telefono" action="eliminar" id="${v.id}" params="[r : 'showUsuario',idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Eliminar Teléfono"><i class="material-icons">delete</i></g:link>
                    </td>
                </tr>
            </g:each>
            <tr>
                <td></td>
                <td>
                    <a class="create btn-floating waves-effect waves-light green tooltipped" href="#modalCreateTelefono" data-position="left" data-delay="50" data-tooltip="Agregar Teléfono"><i class="material-icons">add</i></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <g:paginate total="${usuarioCount ?: 0}" />
    </div>
</div>


<!-- Modal Create Direccion Structure -->
<div id="modalCreateDireccion" class="modal bottom-sheet">
    <div class="modal-content">
        <h5>Agregar Direccion</h5>
        <div class="row">
            <g:form action="save" controller="direccion" params="[r : 'showUsuario', idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]">
                <input name="usuario" value="${this.usuario.id}|" hidden>
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

<!-- Modal Edicion Direccion Structure -->
<div id="modalEdicionDireccion" class="modal bottom-sheet">
    <div class="modal-content">
        <h5>Editar Direccion</h5>
        <div class="row">
            <g:form class="col s12" action="update" controller="direccion" id="${params.idDireccion}" method="PUT" params="[r : 'showUsuario', idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]">
                <div class="row">
                    <div class="input-field col s12 m9">
                        <input id="addressEdit" value="${direccion}" name="address" type="text" class="validate">
                        <label for="addressEdit">Direccion</label>
                    </div>
                </div>
                <!-- Menu Modal Update-->
                <div class="fixed-action-btn">
                    <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.update.label', default: 'Update')}" type="submit" data-position="left" data-delay="50" data-tooltip="Actualizar ${controllerName}"><i class="material-icons right">send</i></button>
                    <g:link action="show" id="${this.usuario.id}" params="[name : params.name, lastName : params.lastName]" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                </div>
            </g:form>
        </div>
    </div>
</div>

<!-- Modal Create Telefono Structure -->
<div id="modalCreateTelefono" class="modal bottom-sheet">
    <div class="modal-content">
        <h5>Agregar Telefono</h5>
        <div class="row">
            <g:form action="save" controller="telefono" params="[r : 'showUsuario', idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]">
                <input name="usuario" value="${this.usuario.id}" hidden>
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

<!-- Modal Edicion Telefono Structure -->
<div id="modalEdicionTelefono" class="modal bottom-sheet">
    <div class="modal-content">
        <h5>Editar Direccion</h5>
        <div class="row">
            <g:form class="col s12" action="update" controller="telefono" id="${params.idTelefono}" method="PUT" params="[r : 'showUsuario', idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]">
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
                    <g:link action="show" id="${this.usuario.id}" params="[name : params.name, lastName : params.lastName]" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                </div>
            </g:form>
        </div>
    </div>
</div>

<g:if test="${params.idDireccion}">
    <a type="hidden" href="#modalEdicionDireccion" data-position="left" data-delay="50" id="clickButton"></a>
</g:if>
<g:if test="${params.idTelefono}">
    <a type="hidden" href="#modalEdicionTelefono" data-position="left" data-delay="50" id="clickButton"></a>
</g:if>
<asset:javascript src="jquery-2.1.1.min.js"/>
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
</body>
</html>
