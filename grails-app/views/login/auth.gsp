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
            <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" autocomplete="off">
                <h3 class="center">Inicio de Sesión</h3>
                <div class="input-field col s12">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="icon_prefix" name="${usernameParameter ?: 'username'}" type="text" class="validate tooltipped" data-position="right" data-tooltip="Ingrese su nombre de usuario">
                    <label for="icon_prefix">Correo electrónico</label>
                </div>
                <div class="input-field col s12">
                    <i class="material-icons prefix">vpn_key</i>
                    <input id="icon_telephone" name="${passwordParameter ?: 'password'}" type="password" class="validate tooltipped" data-position="right" data-tooltip="Ingrese su contraseña">
                    <label for="icon_telephone">Contraseña</label>
                </div>
                <div class="input-field col s12">
                    <button class="btn waves-effect waves-light" style="width:100%; height:50px" type="submit" name="submitButton">Entrar
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>

        </div>
    </div>
    <div class="col m4"></div>
</div>
</body>
</html>