<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>

<div class="row">
<!---rut, nombre, paterno, clave, materno, valor, estado, usuario, valorHoraExtra--->
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
        <!-- Menu Modal Update-->
        <!--
        <div class="fixed-action-btn">
            <button name="create" class="save waves-effect waves-light btn-floating btn-large teal tooltipped" value="${message(code: 'default.button.update.label', default: 'Update')}" type="submit" data-position="left" data-delay="50" data-tooltip="Actualizar ${controllerName}"><i class="material-icons right">send</i></button>
            <g:link action="index" class="modal-action modal-close waves-effect waves-light btn-floating btn-large red tooltipped" href="#!" data-position="left" data-delay="50" data-tooltip="Cancelar"><i class="material-icons">cancel</i></g:link>
        </div>
        -->
    </g:form>
    <h3>Direcciones</h3>
    <div class="slider col s12 m12">
        <div id="list-usuario" class="content scaffold-list" role="main">
            <div class="row">
                <table class="responsive-table bordered highlight centered">
                    <thead>
                    <tr>
                        <th>Direccion</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each var="v" in="${gestem.Direccion.findAllWhere(usuario: this.usuario)}">
                        <tr>
                            <td>${v.comuna.ciudad.region.pais.name}, ${v.comuna.ciudad.region.name}, ${v.comuna.name}, ${v.address}</td>
                            <td>
                                <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" controller="direccion" action="index" id="${v.id}" params="[r : 'showUsuario',idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Editar direccion"><i class="material-icons">edit</i></g:link>
                                <g:link class="btn-floating waves-effect waves-light red tooltipped" controller="direccion" action="eliminar" id="${v.id}" params="[r : 'showUsuario',idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Eliminar Dirección"><i class="material-icons">delete</i></g:link>
                            </td>
                        </tr>
                    </g:each>
                    <tr>
                        <td></td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light green tooltipped" controller="direccion" action="index" id="" params="[r : 'showUsuario',idUsuario : this.usuario.id, name : params.name, lastName : params.lastName]" data-position="left" data-delay="50" data-tooltip="Agregar Dirección"><i class="material-icons">add</i></g:link>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <g:paginate total="${usuarioCount ?: 0}" />
            </div>
        </div>
    </div>
</div>
</body>
</html>
