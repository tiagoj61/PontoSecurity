<%@taglib prefix="s" uri="/struts-tags"%>
<h1>Status do estoque</h1>

<div class="row">
    <div class="col-lg-8">
        <div id="material-chart" class="chart-container"></div>         
    </div>
    <div class="col-lg-4">
        <div class="card-report">
            <div class="row">
                <s:iterator value="statusMaterials">
                    <div class="col-6">
                        <div class="report-container">
                            <div class="value bg-${css}">${value}</div>
                            <div class="description">${description}</div>
                        </div>
                    </div>
                </s:iterator>
            </div>
        </div>
    </div>
</div>