<%-- 
    Document   : register
    Created on : Feb 27, 2024, 10:13:06 PM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <script>
      function validateForm() {
                  var password = document.getElementById("password").value;
                  var confirm_password = document.getElementById("confirm_password").value;
                  if (password !== confirm_password) {
                  document.getElementById("error").innerHTML = "Password and Confirm Password are not the same";
                  return false;
                  }
                  return true;
              }
    </script>
    <body>
        <%@include file="header.jsp" %>
        <form action="register" onsubmit="return validateForm()" method="post">
            <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                <div class="container h-100">
                    <div style="width: 100%;" class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6" style="margin: 40px 0 40px 0">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5">Create an account</h2>
                                    <form>
                                        <h6 id="error" style="color: red">${requestScope.error}</h6>
                                        <div class="form-outline mb-4">
                                            <label class="form-label"  for="form3Example1cg">Your Name</label>
                                            <input type="text" name="name" required id="form3Example1cg" class="form-control form-control-lg" />
                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="form3Example3cg">Your Username</label>
                                            <input type="text" name="username" required id="form3Example3cg" class="form-control form-control-lg" />
                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label"  for="password">Password</label>
                                            <input type="password" required name="password" id="password" class="form-control form-control-lg" />
                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label"  for="confirmPass">Repeat your password</label>
                                            <input type="password" required name="confirmPass" id="confirm_password" class="form-control form-control-lg" />
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register</button>
                                        </div>
                                        <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="signin" class="fw-bold text-body"><u>Login here</u></a></p>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
