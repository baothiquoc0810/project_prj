<%-- 
    Document   : addNewSlot
    Created on : Mar 12, 2024, 10:16:09 PM
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
        .container-slot {
            width: 980px;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center ;
            margin:40px auto;
        }

        .form-group {
            width: calc(50% - 10px); /* Để các form group hiển thị theo 2 cột */
        }

        .form-group {
            width: calc(50% - 10px);
            margin-bottom: 20px;
        }

        label {
            font-size: 20px;
            font-weight: bold;
            display: block;
            color: #333;
            margin-bottom: 5px;
        }

        select,
        input[type="date"],
        input[type="time"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15);
        }

        select[multiple] {
            height: auto;
        }



        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15);
        }
    </style>
    <body>
        <%@include file="header.jsp" %>
        <form action="addNewSlot" method="post" onsubmit="return validateDateTime();">

            <div class="container-slot" style="background-color: #fff">
                <h3 style="margin:25px; font-weight: bold; color:#40040e">Phim ${movie.title.toUpperCase()}</h3>
                <h3 id="errorMessage" style="color:red">${message}</h3>
                <div class="form-group">
                    <label for="cinemaSelect">Rạp chiếu phim:</label><br>
                    <select id="cinemaSelect" name="cinemaSelect" class="genre-select">
                        <option value="CGV Hùng Vương Plaza">CGV Hùng Vương Plaza</option>
                        <option value="CGV Menas Mall">CGV Menas Mall</option>
                        <option value="CGV Crescent Mall">CGV Crescent Mall</option>
                        <option value="CGV Vincom Center Bà Triệu">CGV Vincom Center Bà Triệu</option>
                        <option value="CGV Hồ Gươm Plaza">CGV Hồ Gươm Plaza</option>
                        <option value="CGV Aeon Long Biên">CGV Aeon Long Biên</option>
                        <option value="CGV Vĩnh Trung Plaza">CGV Vĩnh Trung Plaza</option>
                        <option value="CGV Vincom Đà Nẵng">CGV Vincom Đà Nẵng</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="dateInput">Ngày chiếu:</label><br>
                    <input type="date" id="dateInput" name="dateInput">
                </div>
                <div class="form-group">
                    <label for="theaterNumber">Rạp chiếu phim:</label><br>
                    <select id="theaterNumber" name="theaterNumber" class="theaterNumber" >
                        <option value="1">Phòng 1</option>
                        <option value="2">Phòng 2</option>
                        <option value="3">Phòng 3</option>
                        <option value="4">Phòng 4</option>

                    </select>
                </div>
                <div class="form-group">
                    <label for="startTimeInput">Giờ bắt đầu chiếu: (nên chọn giờ chẵn)</label><br>
                    <input type="time" id="startTimeInput" required name="startTimeInput">
                    <input type="hidden" id="duration" name="durationInput" value="${duration}"/>
                </div>
                <div class="form-group">
                    <label for="endTimeInput">Giờ kết thúc chiếu:</label><br>
                    <input style="appearance: none;" type="time" readonly id="endTimeInput" required name="endTimeInput">
                </div>
                <input type="submit" value="Submit" />
                <input type="hidden" name="movieID" value="${movie.movieID}"/>
            </div>
        </form>
        <%@include file="footer.jsp" %>
    </body>
    <script>
    function validateDateTime() {
        var dateInput = document.getElementById('dateInput').value;
        var startTimeInput = document.getElementById('startTimeInput').value;
        var durationInput = document.getElementById('duration').value;
        
        var date = new Date(dateInput);
        var startTime = new Date(dateInput + ' ' + startTimeInput);
        
        // Round duration to nearest 10 minutes
        var duration = Math.ceil(durationInput / 10) * 10;
        
        // Calculate end time
        var endTime = new Date(startTime.getTime() + duration * 60000);
        
        // Format end time as HH:mm
        var endTimeString = endTime.getHours().toString().padStart(2, '0') + ':' + endTime.getMinutes().toString().padStart(2, '0');
        
        // Fill end time into endTimeInput field
        document.getElementById('endTimeInput').value = endTimeString;
        
        // Check if date is valid and in the future
       if (isNaN(date.getTime()) || date <= new Date()) {
           document.getElementById('errorMessage').innerText = 'Please enter a valid date in the future.';
           return false;
       }
        
        // Check if start time is earlier than end time
        if (startTime > endTime) {
            document.getElementById('errorMessage').innerText = 'Start time must be earlier than end time.';
            return false;
        }
        
        // If all checks pass, return true to allow the form to be submitted
        return true;
    }

    // Add event listener for start time and duration input changes
    document.getElementById('startTimeInput').addEventListener('change', validateDateTime);
    document.getElementById('duration').addEventListener('change', validateDateTime);
</script>

</html>
