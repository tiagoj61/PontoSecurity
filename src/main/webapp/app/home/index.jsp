<%@taglib prefix="s" uri="/struts-tags"%>

<s:include value="../layout/fragment/header.jsp">
    <s:param name="styles">
        <style type="text/css">
            .card-report {
                margin-bottom: 20px;
            }
            .report-container {
                margin-bottom: 20px;
            }
            .report-container > .value {
                border-top-left-radius: 10px;
                border-top-right-radius: 10px;
                height: 120px;
                line-height: 120px;
                font-size: 40px;
                color: white;
                text-align: center;
                font-weight: bold;
            }
            .report-container > .description {
                border-bottom-left-radius: 10px;
                border-bottom-right-radius: 10px;
                height: 80px;
                background-color: white;
                text-align: center;
                line-height: 80px;
                font-weight: bold;
                color: black;
                text-transform: uppercase;
            }
        </style>
    </s:param> 
</s:include>

<%@include file="frag/garagem.jsp" %>
<hr />
<%@include file="frag/estoque.jsp" %>
<hr />
<%@include file="frag/manutencao.jsp" %>

<s:include value="../layout/fragment/footer.jsp">
    <s:param name="scripts">
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/highcharts-3d.js"></script>
        <script src="https://code.highcharts.com/modules/variable-pie.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/modules/export-data.js"></script>
        <script src="https://code.highcharts.com/modules/accessibility.js"></script>
        <script type="text/javascript">
            Highcharts.setOptions({
                lang: {
                    contextButtonTitle: 'Op\u00e7\u00f5es Avan\u00e7adas',
                    decimalPoint: ',',
                    downloadJPEG: 'Salvar como JPEG',
                    downloadPDF: 'Salvar como PDF',
                    downloadPNG: 'Salvar como PNG',
                    downloadSVG: 'Salvar como SVG',
                    drillUpText: '< Voltar para {series.name}',
                    loading: 'Aguarde...',
                    months: moment.months(),
                    noData: 'Sem dados a exibir para este gr\u00e1fico!',
                    //numericSymbols: null,
                    printChart: 'Imprimir',
                    resetZoom: 'Desfazer zoom',
                    resetZoomTitle: 'Voltar zoom 1:1',
                    shortMonths: moment.monthsShort(),
                    thousandsSep: '.',
                    weekdays: moment.weekdays(),
                    viewFullscreen: 'Tela cheia',
                    exitFullscreen: 'Sair da tela cheia',
                    viewData: 'Ver dados em tabela',
                    hideData: 'Ocultar dados em tabela',
                    exportData: {
                        categoryHeader: 'Categoria'
                    }
                }
            });
            Highcharts.chart('veiculo-chart', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                title: {
                    text: 'Distribui\u00e7\u00e3o da garagem'
                },
                tooltip: {
                    headerFormat: '',
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 40,
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                        }
                    }
                },
                accessibility: {
                    point: {
                        valueSuffix: '%'
                    }
                },
                colors: [${colorStatusVeiculoJS}],
                series: [{
                        name: 'Situa\u00e7\u00e3o',
                        colorByPoint: true,
                        data: [${dataStatusVeiculoJS}]
                    }]
            });
            Highcharts.chart('material-chart', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                title: {
                    text: 'Distribui\u00e7\u00e3o do estoque'
                },
                tooltip: {
                    headerFormat: '',
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 40,
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                        }
                    }
                },
                accessibility: {
                    point: {
                        valueSuffix: '%'
                    }
                },
                colors: [${colorStatusMaterialJS}],
                series: [{
                        name: 'Situa\u00e7\u00e3o',
                        colorByPoint: true,
                        data: [${dataStatusMaterialJS}]
                    }]
            });
            Highcharts.chart('manutencao-servico-chart', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                title: {
                    text: 'Necessidades de manuten\u00e7\u00e3o'
                },
                subtitle: {
                    text: 'Distribui\u00e7\u00e3o por produto ou servi\u00e7o que ser\u00e3o necess\u00e1rios'
                },
                tooltip: {
                    headerFormat: '',
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br/>{point.y} itens'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 40,
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.y}'
                        }
                    }
                },
                accessibility: {
                    point: {
                        valueSuffix: '%'
                    }
                },
                colors: [${colorManutencaoServicoJS}],
                series: [{
                        name: 'Servi\u00e7o',
                        data: [${dataManutencaoServicoJS}]
                    }]
            });
        </script>
    </s:param> 
</s:include>
