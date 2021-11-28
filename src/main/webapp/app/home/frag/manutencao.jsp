<%@taglib prefix="s" uri="/struts-tags"%>

<h1>Manuten&ccedil;&atilde;o preventiva</h1>
<section class="card">
    <header class="card-header">Registros</header>
    <div class="card-body">
        <p>Atente ao itens e ve&iacute;culos abaixo que em breve precisar&atilde;o agendar manuten&ccedil;&atilde;o</p>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Ve&iacute;culo</th>
                        <th>Servi&ccedil;o</th>
                        <th class="text-right">Relizada com</th>
                        <th class="text-right">Trocar a cada</th>
                        <th class="text-right">Km atual</th>
                        <th class="text-right">Vida &uacute;til <i class="fas fa-percentage"></i></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="manutencaoNecessarias">
                        <tr>
                            <td>
                                ${veiculo.placa}<br/>
                                <strong>${veiculo.modelo}</strong>
                            </td>
                            <td>${tipoServico.nome}</td>
                            <td class="text-right">${quilometragemFormat} km</td>
                            <td class="text-right">${tipoServico.quilometragemFormat} km</td>
                            <td class="text-right">${veiculo.quilometragemFormat} km</td>
                            <td class="text-right ${percentual < 0 ? "text-danger bold" : "" }">
                                <s:text name="format.percent">
                                    <s:param value="percentual"/>
                                </s:text>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
                <tfoot>
                    <tr>
                        <th colspan="5">Registros</th>
                        <th class="text-right">${manutencaoNecessarias.size()}</th>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</section>


<section class="card">
    <div class="card-body">
        <div class="row">
            <div class="col-lg-8"><div id="manutencao-servico-chart" class="chart-container"></div></div>
            <div class="col-lg-4">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Servi&ccedil;o</th>
                            <th class="text-right">Qtd</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:set var="manutencaoServicos_totais" value="0" />
                        <s:iterator value="manutencaoServicos">
                            <tr>
                                <td>${description}</td>
                                <td class="text-right">${value}</td>
                            </tr>
                            <s:set var="manutencaoServicos_totais" value="#manutencaoServicos_totais + value" />
                        </s:iterator>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Totais</th>
                            <th class="text-right">${manutencaoServicos_totais}</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>

    </div>
</section>
