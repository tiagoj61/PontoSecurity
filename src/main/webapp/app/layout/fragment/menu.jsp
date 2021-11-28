<%@taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.logged != null">
    <ul class="sidebar-menu" id="nav-accordion">
        <li>
            <a href="dashboard" class="${userMenu == "Dashboard" ? "active" : ""}" >
                <i class="fas fa-tachometer-alt"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <li>
            <a href="mapaLocalizacao" class="${userMenu == "Localizacao" ? "active" : ""}" >
                <i class="fas fa-map-marked-alt"></i>
                <span>Mapa</span>
            </a>
        </li>
        <li class="sub-menu">
            <a href="javascript:void(0);" class="${(userMenu == "EntradaAlmoxarifado" || userMenu == "SaidaAlmoxarifado" || userMenu == "DevolucaoAlmoxarifado") ? "active" : ""}" >
                <i class="fas fa-pallet"></i>
                <span>Almoxarifado</span>
            </a>
            <ul class="sub">
                <li class="${userMenu == "EntradaAlmoxarifado" ? "active" : ""}"><a href="listEntradaAlmoxarifado">Entrada</a></li>
                <li class="${userMenu == "SaidaAlmoxarifado" ? "active" : ""}"><a href="listSaidaAlmoxarifado">Sa&iacute;da</a></li>
                <li class="${userMenu == "DevolucaoAlmoxarifado" ? "active" : ""}"><a href="listDevolucaoAlmoxarifado">Devolu&ccedil;&aacute;o</a></li>
            </ul>
        </li>
        <li>
            <a href="listSaidaChecklistVeiculo" class="${userMenu == "SaidaChecklistVeiculo" || userMenu == "EntradaChecklistVeiculo" ? "active" : ""}" >
                <i class="fas fa-truck-monster"></i>
                <span>Controle de Frota</span>
            </a>
        </li>
        <li>
            <a href="listInfracaoTransito" class="${userMenu == "InfracaoTransito" ? "active" : ""}" >
                <i class="fas fa-traffic-light"></i>
                <span>Infra&ccedil;&atilde;o de Transito</span>
            </a>
        </li>
        <li>
            <a href="listSinistro" class="${userMenu == "Sinistro" ? "active" : ""}" >
                <i class="fas fa-car-crash"></i>
                <span>Sinistro</span>
            </a>
        </li>
        <li>
            <a href="listManutencao" class="${userMenu == "Manutencao" ? "active" : ""}" >
                <i class="fas fa-recycle"></i>
                <span>Manuten&ccedil;&atilde;o</span>
            </a>
        </li>
        <li>
            <a href="listChecklist" class="${userMenu == "Checklist" ? "active" : ""}" >
                <i class="far fa-list-alt"></i>
                <span>Checklist</span>
            </a>
        </li>

        <li class="sub-menu">
            <a href="javascript:void(0);" class="${(
                                                   userMenu == "Fornecedor" || 
                                                   userMenu == "Motorista" || 
                                                   userMenu == "TipoVeiculo" ||
                                                   userMenu == "GPS" ||
                                                   userMenu == "Veiculo" ||
                                                   userMenu == "Tombamento" ||
                                                   userMenu == "ChecklistCustomizado" 
                                                   ) ? "active" : ""}" >
                <i class="fas fa-suitcase"></i>
                <span>Cadastr&aacute;veis</span>
            </a>
            <ul class="sub">
                <li class="${userMenu == "Fornecedor" ? "active" : ""}">
                    <a href="listFornecedor">Fornecedor</a>
                </li>
                <li class="${userMenu == "Motorista" ? "active" : ""}">
                    <a href="listMotorista">Motorista</a>
                </li> 
                <li class="${userMenu == "TipoVeiculo" ? "active" : ""}">
                    <a href="listTipoVeiculo">Tipo do Ve&iacute;culo</a>
                </li>
                <li class="${userMenu == "GPS" ? "active" : ""}">
                    <a href="listGPS">GPS</a>
                </li>
                <li class="${userMenu == "Veiculo" ? "active" : ""}">
                    <a href="listVeiculo">Ve&iacute;culo</a>
                </li>
                <li class="${userMenu == "Tombamento" ? "active" : ""}">
                    <a href="listTombamento">Tombamento</a>
                </li>
                <li class="${userMenu == "ChecklistCustomizado" ? "active" : ""}">
                    <a href="listChecklistCustomizado">Checklist customizado</a>
                </li>
            </ul>
        </li>

        <li>
            <a href="listProvaVida" class="${userMenu == "ProvaVida" ? "active" : ""}" >
                <i class="fas fa-heart"></i>
                <span>Prova de vida</span>
            </a>
        </li>
        <li>
            <a href="relatorio" class="${userMenu == "Relatorio" ? "active" : ""}" >
                <i class="fas fa-chart-pie"></i>
                <span>Relat&oacute;rios</span>
            </a>
        </li>

        <li>
            <a href="listUsuario" class="${userMenu == "Usuario" ? "active" : ""}" >
                <i class="fas fa-user-circle"></i>
                <span>Usu&aacute;rio</span>
            </a>
        </li>

        <li>
            <a href="../home/config.jsp">
                <i class="fas fa-qrcode"></i>
                <span>Configura&ccedil;&atilde;o App</span>
            </a>
        </li>
        <li>
            <a href="javascript:js_logout('logout')">
                <i class="fas fa-sign-out-alt"></i>
                <span>Sair</span>
            </a>
        </li>
    </ul>
</s:if>