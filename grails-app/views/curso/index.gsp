<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="slider col s12 m12">
    <div id="list-curso" class="content scaffold-list" role="main">
        <div class="row">
            <table class="responsive-table bordered highlight-2 centered">
                <thead>
                <tr>
                    <th>Codigo</th>
                    <th>Nombre</th>
                    <th>Educación</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="v" in="${cursoList}">
                    <tr>
                        <td>${v.code}</td>
                        <td>${v.name}</td>
                        <td>${v.educacion}</td>
                        <td>
                            <g:link class="btn-floating waves-effect waves-light yellow darken-2 tooltipped" id="${v.id}" params="[]" data-position="left" data-delay="50" data-tooltip="Editar ${controllerName}"><i class="material-icons">edit</i></g:link>
                            <g:link class="btn-floating waves-effect waves-light red tooltipped" action="eliminar" id="${v.id}" data-position="left" data-delay="50" data-tooltip="Eliminar ${controllerName}"><i class="material-icons">delete</i></g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <g:paginate total="${cursoCount ?: 0}" />
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
        <h5>Crear ${controllerName}</h5>
        <div class="row">
            <g:form action="save">
                <div class="row">
                    <div class="input-field col s12 m2">
                        <f:input property="code" id="code" bean="curso"/>
                        <label for="code">Codigo</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <f:input property="name" id="name" bean="curso"/>
                        <label for="name">Nombre</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <select name="educacion.id" required="true" id="educacion">
                            <option value="" disabled <g:if test="${!params.educacionName}">selected</g:if>>Seleccione Educación</option>
                            <g:each var="v" in="${educacionList}">
                                <option value="${v.id}" <g:if test="${v.name == params.educacionName}">selected</g:if>>${v.name}</option>
                            </g:each>
                        </select>
                        <label>País</label>
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
            <g:form class="col s12" resource="${this.curso}" method="PUT">
                <div class="row">
                    <div class="input-field col s12 m2">
                        <f:input property="code" id="code" bean="curso"/>
                        <label for="code">Codigo</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <f:input property="name" id="name" bean="curso"/>
                        <label for="name">Nombre</label>
                    </div>
                    <div class="input-field col s12 m2">
                        <select name="educacion.id" required="true" id="educacion">
                            <option value="" disabled <g:if test="${!curso.educacion}">selected</g:if>>Seleccione Educación</option>
                            <g:each var="v" in="${educacionList}">
                                <option value="${v.id}" <g:if test="${v.name == curso.educacion}">selected</g:if>>${v.name}</option>
                            </g:each>
                        </select>
                        <label>País</label>
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
<g:hasErrors bean="${this.curso}">
    <ul class="errors red-text" role="alert">
        <g:eachError bean="${this.curso}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>
</body>
</html>