<html>
<head>
    <title>GESTEM - Login</title>
    <meta name="layout" content="main" />
</head>
<body>
<div class="row">
    <div class="col m4"></div>
    <div class="col s12 m4">
        <div class="row">
            <g:if test="${session?.user}">
            </g:if>
            <g:else>
                <g:form controller="login" action="signin">
                    <h3 class="center">Inicio de Sesión</h3>
                    <g:if test="${flash.message!=null}">
                        <div class="message">${flash.message}</div>
                        <script>Materialize.toast('${flash.message}', 5000)</script>
                    </g:if>
                    <div class="input-field col s12">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="icon_prefix" name="username" type="text" class="validate tooltipped" data-position="right" data-tooltip="Existe una nota de escritorio con los diferentes usuarios">
                        <label for="icon_prefix">Correo electrónico</label>
                    </div>
                    <div class="input-field col s12">
                        <i class="material-icons prefix">vpn_key</i>
                        <input id="icon_telephone" name="password" type="password" class="validate tooltipped" data-position="right" data-tooltip="Ingeniero: qwerty | Administrador: qwerty">
                        <label for="icon_telephone">Contraseña</label>
                    </div>
                    <div class="input-field col s12">
                        <button class="btn waves-effect waves-light" style="width:100%; height:50px" type="submit" name="submitButton">Entrar
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </g:form>
            </g:else>
        </div>
    </div>
    <div class="col m4"></div>
</div>
</body>
</html>