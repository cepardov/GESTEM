<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'direccion.label', default: 'Direccion')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <asset:stylesheet src="autocomplete.css"/>
</head>
<body>
<div class="slider col s12 m12">
    <div id="list-direccion" class="content scaffold-list" role="main">
        <div class="row">
            <table class="responsive-table bordered highlight centered">
                <thead>
                <tr>
                    <th>Dirección</th>
                    <th>Usuario</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="v" in="${direccionList}">
                    <tr>
                        <td>${v.comuna.ciudad.region.pais.name}, ${v.comuna.ciudad.region.name}, ${v.comuna.name},${v.address}</td>
                        <td>${v.usuario.nombre} ${v.usuario.paterno} ${v.usuario.materno}</td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" id="${v.id}" params="[paisId : params.paisId, paisName : params.paisName]" data-position="left" data-delay="50" data-tooltip="Editar ${controllerName}"><i class="material-icons">edit</i></g:link>
                            <g:link class="btn-floating waves-effect waves-light red tooltipped" action="eliminar" id="${v.id}" data-position="left" data-delay="50" data-tooltip="Eliminar ${controllerName}"><i class="material-icons">delete</i></g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <g:paginate total="${direccionCount ?: 0}" />
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
            <g:form action="save" params="[r : params.r, idUsuario : params.idUsuario, name : params.name, lastName : params.lastName]">
                <g:if test="${params.idUsuario == null}">
                    <div id="usuarioDiv" class="input-field col s12">
                        <input id="usuario" name="usuario" class="typeahead" placeholder="Usuario">
                    </div>
                </g:if>
                <g:else>
                    <input name="usuario" value="${params.idUsuario}|" hidden>
                </g:else>

                <div id="comunaDiv" class="input-field col s12">
                    <input id="comuna" name="comuna" class="typeahead" placeholder="Comuna">
                </div>

                <div class="input-field inline col s12 m4">
                    <f:input property="address" id="address" bean="direccion"/>
                    <label for="address">Dirección</label>
                </div>
                <!-- Menu Modal Create-->
                <div class="fixed-action-btn">
                    <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.create.label', default: 'Create')}" type="submit" data-position="left" data-delay="50" data-tooltip="Guardar ${controllerName}"><i class="material-icons right">send</i></button>
                    <g:if test="${params.r}">
                        <g:link controller="usuario" action="show" id="${params.idUsuario}" params="[r : params.r, idUsuario : params.idUsuario, name : params.name, lastName : params.lastName]" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                    </g:if>
                    <g:else>
                        <a class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></a>
                    </g:else>
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
            <g:form class="col s12" resource="${this.direccion}" method="PUT" params="[r : params.r, idUsuario : params.idUsuario, name : params.name, lastName : params.lastName]">
                <div  class="input-field inline col s12 m4">
                    <f:input property="address" id="address" bean="direccion"/>
                    <label for="address">Dirección</label>
                </div>
                <!-- Menu Modal Update-->
                <div class="fixed-action-btn">
                    <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.update.label', default: 'Update')}" type="submit" data-position="left" data-delay="50" data-tooltip="Actualizar ${controllerName}"><i class="material-icons right">send</i></button>
                    <g:if test="${params.r}">
                        <g:link controller="usuario" action="show" id="${params.idUsuario}" params="[r : params.r, idUsuario : params.idUsuario, name : params.name, lastName : params.lastName]" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                    </g:if>
                    <g:else>
                        <g:link action="index" params="[r : params.r, idUsuario : params.idUsuario, name : params.name, lastName : params.lastName]" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
                    </g:else>
                </div>
            </g:form>
        </div>
    </div>
</div>
<g:if test="${params.id}">
    <a type="hidden" href="#modalEdicion" data-position="left" data-delay="50" id="clickButton"></a>
</g:if>
<g:if test="${params.r}">
    <a type="hidden" href="#modalCreate" data-position="left" data-delay="50" id="clickButtonCreate"></a>
</g:if>
<g:hasErrors bean="${this.direccion}">
    <ul class="errors" role="alert">
        <g:eachError bean="${this.direccion}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<asset:javascript src="jquery-2.1.1.min.js"/>
<asset:javascript src="typeahead.bundle.min.js"/>
<script>
    $(document).ready(function() {
        var substringMatcher = function(strs) {
            return function findMatches(q, cb) {
                var matches, substringRegex;
                // an array that will be populated with substring matches
                matches = [];
                // regex used to determine if a string contains the substring `q`
                substrRegex = new RegExp(q, 'i');
                // iterate through the pool of strings and for any string that
                // contains the substring `q`, add it to the `matches` array
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
        var usuario = [
            <g:each in="${gestem.Usuario.list()}">
            '${it.id} | ${it.nombre} ${it.paterno} ${it.materno}',
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
        $('#usuarioDiv .typeahead').typeahead({
            hint: true,
            highlight: true,
            minLength: 1
        }, {
            name: 'usuario',
            source: substringMatcher(usuario)
        });
    });
</script>
</body>
</html>