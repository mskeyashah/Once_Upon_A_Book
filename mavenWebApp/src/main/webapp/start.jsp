<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Users' Application</title>
      <style>
          .form-popup
          {
            display: none;
            border: 3px solid black;
            z-index: 9;
            text-align: center;
          }
          form,p {
              text-align: center;
          }
          .open-button  {
              text-align: center;
              background-color: limegreen;
              text-decoration-color: black;
          }
          .tab {
              overflow: hidden;
              border: 3px solid black;
              background-color: limegreen;
              text-align: center;
          }
          .tab button {
              background-color: inherit;
              float: left;
              border: none;
              outline: none;
              cursor: pointer;
              padding: 14px 16px;
              transition: 0.3s;
              font-size: 17px;
          }
          .tab button:hover {
              background-color: greenyellow;
          }
      </style>
  </head>
  <body>
  <div class="tab">
      <button class="tablinks" onclick="openHome()" style="margin-left: 300px">Home</button>
      <button class="tablinks" onclick="openUser()">Users</button>
      <button class="tablinks" onclick="openBooks()">Books</button>
      <button class="tablinks" onclick="openReservation()">Reservation</button>
  </div>
  		<% String userId = request.getParameter("userId"); %>
        </br>
        <form name="Search" method="GET" action="Searching" style="font-size: 16;font-family: cursive; font-weight: bold">
            ID: <input type="number" name="id"/>
            <input type="hidden" name="userId" value="<%=userId%>" >
            <input type="submit" value="Search" style="background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold"/>
        </form>
        </br></br></br>
        <p><button class="open-button" onclick="openForm()" style="font-family: cursive; font-size: 16;font-weight: bold;"> Add User</button></p>
        <div class="form-popup" id="myForm" style="background-color: limegreen">
            <form name="Insert" method="GET" action="Inserting" class="form-container">
                <p style="font-weight: bold;">Add User</p>
                <input type="text" placeholder=" First Name" name="first" required>
                <input type="text" placeholder="Last Name" name="last" required>
                <input type="text" placeholder="UserName" name="username" required>
                <input type="text" placeholder="Passphrase" name="passphrase" required>
                <input type="text" placeholder="Admin" name="is_admin" required> 
                <button type="submit" style="background-color: beige;font-family: cursive; font-size: 14">Add</button>
                <button type="button" style="background-color: beige; font-family: cursive; font-size: 14" onclick="closeForm()">Close</button>
            </form>

     </div>
     <script>
        function openForm()
        {
            document.getElementById("myForm").style.display = "block";
        }
        function closeForm()
        {
            document.getElementById("myForm").style.display = "none";
        }
        function openHome()
        {
            window.location.href = "home.jsp?userId="+<%= request.getParameter("userId") %>;
        }
       function openUser()
       {
           window.location.href = "start.jsp?userId="+<%= request.getParameter("userId") %>;
       }
       function openBooks()
       {
           window.location.href = "books.jsp?userId="+<%= request.getParameter("userId") %>;
       }
       function openReservation()
       {
           window.location.href = "reservation.jsp?userId="+<%= request.getParameter("userId") %>;
       }
     </script>
  </body>
</html>