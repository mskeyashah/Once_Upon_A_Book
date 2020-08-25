<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Reservation Application</title>
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
  		<%@ page import="org.blog.controller.Test"%>
  		<% String userId = request.getParameter("userId"); %>
		<% String x1 = Test.doGet("select?id=" + userId); 
		String name = x1.substring(x1.indexOf("first")+8,x1.indexOf("last")-3);%> 
		<p>Welcome <%=name%></p>     
        
        <form name="ReservationSearch" method="GET" action="ReservationSearching" style="font-size: 16;font-family: cursive; font-weight: bold">
            Title: <input type="text" name="title"/>
            <input type="hidden" name="userId" value="<%=userId%>" >
            <input type="submit" value="Search" style="background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold"/>
        </form>
       
     <script>
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