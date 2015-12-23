<div class="container">
     <div class="row">
        <h1>Cinema Multisala</h1>
        <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#modal-head-login">login</button>
        <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#modal-head-register">register</button>
    </div>

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
                    <form role="form" action="<%= request.getRequestURL() %>register" method="POST">
                        <div class="form-group">
                            <label for="mail">Mail:</label>
                            <input type="text" class="form-control" id="mail" name = "mail">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" id="pwd" name = "pwd">
                        </div>
                        <div class="form-group">
                            <label for="pwd2">Please insert your password again:</label>
                            <input type="password" class="form-control" id="pwd2">
                        </div>

                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
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
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <p>Some text in the modal.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
</div>
