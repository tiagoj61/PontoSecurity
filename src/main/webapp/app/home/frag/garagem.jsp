<%@taglib prefix="s" uri="/struts-tags"%>
<h1>Status da garagem</h1>
<div class="card-report">
    <div class="row">
        <s:iterator value="statusVeiculos">
            <div class="col-6 col-lg-2">
                <div class="report-container">
                    <div class="value bg-${css}">${value}</div>
                    <div class="description">${description}</div>
                </div>
            </div>
        </s:iterator>
    </div>
</div>
<div id="veiculo-chart" class="chart-container"></div>
