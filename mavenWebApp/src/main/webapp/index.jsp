<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login Application</title>
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
      <button class="tablinks" onclick="openLogin()" style="margin-left: 400px">Login</button>

  </div>
        </br>
        <form name="BookSearch" method="POST" action="Login" style="font-size: 16;font-family: cursive; font-weight: bold">
            Username: <input type="text" name="username" required/>
            Password:   <input type="text" name="passphrase" required/>
            <input type="submit" value="Login" style="background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold"/>
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