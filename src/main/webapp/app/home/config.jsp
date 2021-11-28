<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html compressCss="true">
    <s:include value="../layout/fragment/header.jsp">
        <s:param name="styles">
            <style type="text/css">
                #qrcode {
                    border: solid 1px #333;
                }
            </style>
        </s:param> 
    </s:include>

    <section class="card">
        <header class="card-header">
            Configura&ccedil;&atilde;o do App
        </header>

        <div class="card-body">

            <h4>Leia o QRCode ou copie e cole o endpoint para configurar seu App</h4>

            <div class="row">
                <div class="col-lg-8">
                    <div class="form-group">
                        <label>Endpoint</label>
                        <div>
                            <input type="text" readonly="true" class="form-control" id="endpoint"  />
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">

                    <div class="card" style="margin: 10px; background-color: #dfdfdf; padding: 20px">
                        <img src=""
                             class="img-fluid"
                             id="qrcode"
                             />
                        <div class="card-body">
                            <h5 class="card-title text-center" style="font-weight: bold;
                                text-transform: uppercase;">Leia para configurar</h5>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section>




    <s:include value="../layout/fragment/footer.jsp">
        <s:param name="scripts">
            <script type="text/javascript">
                $(function () {
                    $("#qrcode").attr("src", "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + location.protocol + '//' + location.hostname)
                    $("#endpoint").val(location.protocol + '//' + location.hostname);
                });
            </script>
        </s:param> 
    </s:include>
</compress:html>