<%@page import="java.util.Properties"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
    <!DOCTYPE html>
    <html lang="pt-br">
        <head>
            <meta charset="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <link rel="icon" type="image/png" sizes="16x16" href="../layout/img/icon/favicon.svg" />
            <link rel="stylesheet" type="text/css" href="../layout/assets/font-awesome/css/all.min.css" />
            <link rel="stylesheet" type="text/css" href="../layout/css/style-login.min.css" />
            <link rel="preconnect" href="https://fonts.gstatic.com" />
            <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;600;700&display=swap" rel="stylesheet" />
            <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;600;700&display=swap" rel="stylesheet" />
            <meta name="theme-color" content="#373435" />
            <title>Login | Torke</title>
            <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        </head>
        <body>
            <div class="body-container">

                <s:actionerror theme="bootstrap"/>
                <s:actionmessage theme="bootstrap"/>
                <s:fielderror theme="bootstrap"/>


                <nav>
                    <div class="nav-container">
                        <a href="https://audax.mobi/"><img src="../layout/img/back-arrow.svg" alt=""></a>
                    </div>
                </nav>
                <main>
                    <div class="image-content">
                        <div class="text">
                            <p><img src="../layout/img/icon/logo.svg" style="height: 40px" /></p>
                            <p>
                                Sistema de controle e gerenciamento de frota e 
                                veículos especiais
                            </p>
                        </div>
                    </div>
                    <div class="text-content">
                        <h1>Acesse a plataforma</h1>
                        <div class="form-content">
                            <s:form id="form" acceptcharset="UTF-8" method="post" action="login" theme="simple">
                                <s:token/>
                                <s:textfield type="email" name="usuario.email" required="true" placeholder="Usuário" />
                                <s:password name="usuario.senha" required="true" placeholder="Senha" />

                                <%
                                    HttpSession sessao = request.getSession();
                                    if (sessao.getAttribute("tentativa") != null && ((Integer) sessao.getAttribute("tentativa") >= 3)) {
                                        Properties properties = new Properties();
                                        properties.load(this.getClass().getClassLoader().getResourceAsStream("system.properties"));

                                        out.print("<div class=\"g-recaptcha\" data-sitekey=\"" + properties.getProperty("captcha.publickey") + "\"></div>");
                                    }
                                %>

                                <s:submit value="Acessar plataforma" />
                            </s:form>
                        </div>
                    </div>
                </main>
            </div>

            <script type="text/javascript" src="../layout/js/jquery.js"></script>
            <script type="text/javascript">
                setTimeout(function () {
                    $(".alert").fadeOut(400);
                }, 3000);
            </script>

        </body>
    </html>
</compress:html>