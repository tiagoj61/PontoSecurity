<%@taglib prefix="s" uri="/struts-tags"%>
<section class="card">
    <header class="card-header">
        ${param.title}
    </header>

    <div class="card-body">
        <form acceptcharset="UTF-8" method="post" action="${param.action}" theme="bootstrap">
            <div class="row">
                <div class="col-lg-3">
                    <div class="form-group">
                        <label>Pesquisar por</label>
                        <div>
                            <s:select
                                name="consulta.campo"
                                list="camposConsultaEnum"
                                listKey="key"
                                listValue="value"
                                cssClass="form-control"
                                />
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="form-group">
                        <label>Exibir</label>
                        <div>
                            <s:select 
                                name="consulta.limiteResultados"
                                list="limiteResultadoEnum"
                                listKey="quantidade"
                                listValue="descricao"
                                cssClass="form-control"
                                />
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <label>Pesquisa</label>
                        <div>
                            <s:textfield name="consulta.valor" placeholder="Digite o que você quer encontrar" cssClass="form-control" />
                        </div>
                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-md-2 offset-md-10">
                    <button type="submit" class="btn btn-white btn-block">
                        <i class="fas fa-search"></i> 
                        Buscar
                    </button>
                </div>
            </div>
        </form>
    </div>
</section>