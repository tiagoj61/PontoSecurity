<%@taglib prefix="s" uri="/struts-tags"%>
<ul class="sidebar-menu" id="nav-accordion">

	<li><a href="listFuncionario" class="${userMenu == "Usuario" ? "active" : ""}" >
			<i class="fas fa-user-circle"></i> <span>Usu&aacute;rio</span>
	</a></li>
	<li><a href="javascript:js_logout('logout')"> <i
			class="fas fa-sign-out-alt"></i> <span>Sair</span>
	</a></li>
</ul>
