<%-- 
    Document   : updateAccount
    Created on : Mar 1, 2024, 2:51:48 PM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <!-- <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var confirm_password = document.getElementById("confirm_password").value;
            if (password !== confirm_password) {
                document.getElementById("error").innerHTML = "Password and Confirm Password are not the same";
                return false;
            }
            return true;
        }
    </script> -->
    <body>
        <%@include file="header.jsp" %>
        <form action="update" method="post">
            <div class="container">
                <div class="my-account">
                    <div class="left-content">
                    <h1 class="block-title" style="color:red">TÀI KHOẢN</h1>
                    <div class="block-cotent">
                        <ul style="padding:0">
                            <li style="background-color:${backgroundColorSecond}">
                                <a href="account" style="color: ${colorSecond}"
                                   >
                                    THÔNG TIN CHUNG
                                </a>
                            </li>
                            <li style="background-color:${backgroundColorMain}">
                                <a style="color: ${colorMain}" href="update">
                                CHI TIẾT TÀI KHOẢN
                                </a>
                            </li>
                            <li style="background-color:${backgroundColorSecond}">
                                <a style="color: ${colorSecond}"  href="#">
                                LỊCH SỬ GIAO DỊCH
                                </a>
                            </li>
                        </ul>

                    </div>
                </div>
                    <div class="dashboard">
                        <div class="page-title">
                            <h1>THAY ĐỔI THÔNG TIN</h1>
                        </div>
                        <h6 id="error" style="color: red">${requestScope.error}</h6>
                        <div class="format-profile" style="font-weight: bold; margin-bottom: 15px">
                            <div class="form-outline mb-4">
                                <label class="form-label"  for="form3Example1cg">Your Name</label>
                                <input type="text" value="${sessionScope.account.displayName}" name="displayName" required id="form3Example1cg" class="form-control form-control-lg" />
                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form3Example3cg">Your Username</label>
                                <input type="text" value="${sessionScope.account.username}" readonly name="username" required id="form3Example3cg" class="form-control form-control-lg" />
                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label"  for="password">Old Password</label>
                                <input type="password"  name="oldPassword" id="password" class="form-control form-control-lg" />
                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label"  for="password">Password</label>
                                <input type="password"  name="newPassword" id="password" class="form-control form-control-lg" />
                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label"  for="confirmPass">Repeat your password</label>
                                <input type="password"   name="confirmPassword" id="confirm_password" class="form-control form-control-lg" />
                            </div>
                            <div class="d-flex justify-content-center" style="width: 300px;" >
                                <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Update</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <%@include file="footer.jsp" %>

    </body>
</html>
