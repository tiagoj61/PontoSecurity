<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html compressCss="true">

    <s:include value="../layout/fragment/header.jsp">
        <s:param name="styles"></s:param> 
    </s:include>

    <s:include value="../layout/fragment/searchable.jsp">
        <s:param name="action">listUsuario</s:param>
        <s:param name="title">Pesquisar Usu&aacute;rio</s:param>
    </s:include>


    <section class="card">
        <header class="card-header">Registros</header>

        <div class="card-body text-right">
            <button type="button" class="btn btn-primary" onclick="window.location.href = 'prepareUsuario';">
                <i class="fas fa-plus"></i>
                Adicionar Novo
            </button>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped table-advance table-hover">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Login</th>
                            <th>&Uacute;ltimo Acesso</th>
                            <th>N&iacute;vel</th>
                            <th class="text-center">Ativo</th>
                            <th class="text-right" style="width: 172px">A&ccedil;&otilde;es</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="usuarios">
                            <tr>
                                <td>${nome}</td>
                                <td>${email}</td>
                                <td><s:date name="ultimoAcesso" nice="true" /></td>
                                <td>${tipoUsuario.getName()}</td>
                                <td class="text-center"><i class="${ativo ? 'far fa-check-circle text-success' : 'fas fa-times text-danger'}"></i></td>
                                <td class="text-right">
                                    <button type="button" class="btn btn-outline-primary btn-xs" onclick="window.location.href = 'prepareUsuario?usuario.id=${id}'">
                                        <i class="fas fa-edit"></i> editar
                                    </button>
                                    <button type="button" class="btn btn-outline-danger btn-xs" onclick="js_remove(${id}, '<s:property value="nome" escapeJavaScript="true"/>', 'deleteUsuario?usuario.id=');">
                                        <i class="fas fa-trash-alt"></i> excluir
                                    </button>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>

        <section class="card">
            <div class="card-body">
                <div>
                    <nav>
                        <ul class="pagination justify-content-end" id="pagination"></ul>
                    </nav>
                </div>
            </div>
        </section>

    </section>


    <s:include value="../layout/fragment/footer.jsp">
        <s:param name="scripts">
            <script type="text/javascript" src="../layout/js/pagination.js"></script>
            <script type="text/javascript">
                                    $(function () {
                                        paginate.create({
                                            "limite": "${consulta.limiteResultados}",
                                            "atual": "${consulta.paginaAtual}",
                                            "paginas": "${consulta.paginas}",
                                            "operador": "${consulta.operador}",
                                            "campo": "${consulta.campo}",
                                            "valor": "${consulta.valor}",
                                            "url": "listUsuario"
                                        });
                                    });
            </script>
        </s:param> 
    </s:include>


</compress:html>