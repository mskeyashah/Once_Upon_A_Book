<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Book Application</title>
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
  <% String userId = request.getParameter("userId"); 
  String accesor = org.blog.controller.Test.doGet("select?id="+userId);
  if(accesor.indexOf("true")!=-1){%>
        </br>
        <form name="BookSearch" method="GET" action="BookSearching" style="font-size: 16;font-family: cursive; font-weight: bold">
            Title: <input type="text" name="title"/>
            <input type="hidden" name="userId" value="<%=userId%>" >
            <input type="submit" value="Search" style="background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold"/>
            
        </form>
        </br></br></br>
        <p><button class="open-button" onclick="openForm()" style="font-family: cursive; font-size: 16;font-weight: bold;"> Add Book</button></p>
        <div class="form-popup" id="myForm" style="background-color: limegreen">
            <form method = "GET"  action="BookInserting" class="form-container">
                <p style="font-weight: bold;">Add Book</p>
                <input type="text" placeholder="Book Title" name="title" required>
                <input type="text" placeholder="Author Name" name="author" required>
                <input type="text" placeholder="Year Published" name="year" required>
                <input type="text" placeholder="Genre" name="genre" required>
                <input type="text" placeholder="Added by" name="added" required>
                <input type="hidden" name="userId" value="<%=userId%>" >
                <button type="submit" style="background-color: beige;font-family: cursive; font-size: 14">Add Book</button>
                <button type="button" style="background-color: beige; font-family: cursive; font-size: 14" onclick="closeForm()">Close</button>
            </form>
	<%}
  		else{%>
  		<p>Sorry, you are not allowed to access the books inventory page</p>
  		<%} %>
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