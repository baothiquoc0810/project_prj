<%-- 
    Document   : review
    Created on : Mar 19, 2024, 10:57:57 AM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        /* Center the content */
div {
    text-align: center;
    width: 80%;
    margin: 40px auto;
    background-color: #f9f9f9; /* Màu nền */
    border-radius: 10px; /* Bo góc */
    padding: 20px; /* Khoảng cách bên trong */
    box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1); /* Đổ bóng */
}

h1 {
    margin-bottom: 20px;
    color: red; /* Màu chữ nổi bật */
}

table {
    margin: 0 auto; /* Center the table */
    border:2px solid #ddd;
    border-collapse: collapse;
    width: 90%;
    max-width: 800px; /* Limit table width */
}

td {
    padding: 8px;
    border: 1px solid #ddd;
}

td[colspan="2"], td:first-child {
    font-size: 20px;
    font-weight: bold;
    color: black; /* Màu sắc cho cột bên trái và các cột tiêu đề */
}

input[type="submit"] {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #0056b3;
}

    </style>
    <body>
        <div align="center">
            <h1>Please Review Before </body>Paying</h1>
            <form action="execute_payment" method="post">
                <table>
                    <tr>
                        <td colspan="2"><b>Transaction Details</b></td>
                            <input type="hidden" name="paymentId" value="${param.paymentId}" />
                            <input type="hidden" name="PayerID" value="${param.PayerID}" />
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td>${transaction.description}</td>
                    </tr>
                    <tr>
                        <td>Sub Total:</td>
                        <td>${transaction.amount.details.subtotal}</td>
                    </tr>
                    <tr>
                        <td>Total:</td>
                        <td>${transaction.amount.total}</td>
                    </tr>
                    <tr><td><br/>
                    <tr>
                        <td colspan="2"><b>Payer Information</b></td>
                    </tr>
                    <tr>
                        <td>First Name:</td>
                        <td>${payer.firstName}</td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td>${payer.lastName}</td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td>${payer.email}</td>
                    </tr>
                    <tr><td><br/></td></tr>
                    <tr>
                        <td colspan="2"><b>Shipping Address</b></td>
                    </tr>
                    <tr>
                        <td>Recipient Name:</td>
                        <td>${shippingAddress.recipientName}</td>
                    </tr>
                    <tr>
                        <td>Line 1:</td>
                        <td>${shippingAddress.line1}</td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td>${shippingAddress.city}</td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td>${shippingAddress.state}</td>
                    </tr>
                    <tr>
                        <td>Country Code:</td>
                        <td>${shippingAddress.countryCode}</td>
                    </tr>
                    <tr>
                        <td>Postal Code:</td>
                        <td>${shippingAddress.postalCode}</td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Pay Now"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
