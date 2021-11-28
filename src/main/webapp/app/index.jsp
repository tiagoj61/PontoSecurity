<%

    HttpSession sessao = request.getSession();
    if (sessao.getAttribute("logged") == null) {
        response.sendRedirect("home/login.jsp");
    } else {
        response.sendRedirect("home/dashboard");
    }

%>