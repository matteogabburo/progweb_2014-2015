<header>
    <div class="container">
        <!--<div class="row nav titleandlogin">
            <a href = "http://localhost:8080/CinemaMultisala_war_exploded"><h1>Cinema Multisala</h1></a>-->
        <nav class="navbar navbar-default navbar-fixed-top titleandlogin">
            <a href = "http://localhost:8080/CinemaMultisala_war_exploded"><h1>Cinema Multisala</h1></a>
            <a href = "http://localhost:8080/CinemaMultisala_war_exploded"><button type="button" class="btn left navbar-btn">Home</button></a>
            <a href = "http://localhost:8080/CinemaMultisala_war_exploded/chisiamo.jsp"><button type="button" class="btn left navbar-btn">Chi siamo</button></a>
            <a href = "http://localhost:8080/CinemaMultisala_war_exploded/informativaprezzi.jsp"><button type="button" class="btn left navbar-btn">Informativa prezzi</button></a>

            <%
                HttpSession s = null;
                s = request.getSession(false);

                if(s.getAttribute("USER_MAIL") == null)
                {
                    out.println("<button type=\"button\" class=\"btn right navbar-btn\"  data-toggle=\"modal\" data-target=\"#modal-head-login\">login</button>");
                    out.println("<button type=\"button\" class=\"btn right navbar-btn\"  data-toggle=\"modal\" data-target=\"#modal-head-register\">register</button>");
                }
                else
                {
                    out.println("<div class = \"user-profile-header\">");

                    out.println("</div>");
                    out.println("<form role=\"form\" action=\"http://localhost:8080/CinemaMultisala_war_exploded/logoff\" method=\"POST\">");
                    out.println("<button type=\"submit\" class=\"btn right navbar-btn\">logoff</button>");
                    out.println("</form>");

                    out.println("<a class=\"userpreview\"href=\"http://localhost:8080/CinemaMultisala_war_exploded/user\">"+session.getAttribute("USER_MAIL")+"</a>");
                }
            %>
        </nav>


        <!--</div>-->

        <!-- Modal -->
        <div class="modal fade" id="modal-head-register" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Registration</h4>
                    </div>

                    <div class="modal-body">
                        <form role="form" action="<%= request.getContextPath() %>/register" method="POST">
                            <div class="form-group">
                                <label for="mail">Mail:</label>
                                <input type="text" class="form-control" id="mail" name = "mail">
                            </div>
                            <div class="form-group">
                                <label for="pwd">Password (more than 6 characters):</label>
                                <input type="password" class="form-control" id="pwd" name = "pwd">
                            </div>
                            <div class="form-group">
                                <label for="pwd2">Please insert your password again:</label>
                                <input type="password" class="form-control" id="pwd2">
                            </div>

                            <button type="reset" class="btn btn-default">Cancel</button>
                            <button type="submit" class="btn btn-default">Register</button>
                        </form>
                    </div>
                    <div class="modal-footer">

                    </div>
                </div>

            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="modal-head-login" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Login</h4>
                    </div>

                    <div class="modal-body">
                        <form role="form" action="<%= request.getContextPath() %>/auth" method="POST">
                            <div class="form-group">
                                <label for="mail">your mail:</label>
                                <input type="text" class="form-control" id="logmail" name = "mail">
                            </div>
                            <div class="form-group">
                                <label for="pwd">your password :</label>
                                <input type="password" class="form-control" id="logpwd" name = "pwd">
                            </div>
                            <div class="form-group">
                                <a href="http://localhost:8080/CinemaMultisala_war_exploded/recpwd.jsp">Recupera password</a>
                            </div>

                            <button type="reset" class="btn btn-default">Cancel</button>
                            <button type="submit" class="btn btn-default">Login</button>

                        </form>
                    </div>
                    <div class="modal-footer">

                    </div>
                </div>

            </div>
        </div>
    </div>
</header>