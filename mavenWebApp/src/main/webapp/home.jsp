<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Library Application</title>
      <style>
          h2{
              color: black;
              font: sans-serif;
              background-color: limegreen;
              text-align: center;
              border: 3px solid black;
          }
          body{
              background-color: white;
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
    <div id="home" class="home">
        <p>Welcome. Please use the above tabs to navigate.</p>
        <h3>Terminal</h3>
        First cd into /Users/keyashah/Desktop/example/target/classes. Then run the following command with the arguments below: java hello/InsertInto
        </br>Usage: [add/update/delete/select] [parameter1] [parameter2] ....
        <ul>
            <li>Insert: add [first name] [last name] [username] [passphrase] [admin:1/not admin:0]</li>
            <li>Update: update [column to edit] [value to set] [user id] [modified by]</li>
            <li>Delete: delete [user id]</li>
            <li>Select: select all or select [user id]</li>
        </ul>
        </br>
        <a href="http://localhost:8080/mavenWebApp/select">User Rest Api</a>
        <ul>
            <li>Insert: localhost:8080/mavenWebApp/insert?first=[first name]&last=[last name]&username=[username]&passphrase=[passphrase]&isadmin=[1/0]</li>
            <li>Update: localhost:8080/mavenWebApp/update?column=[column to edit]&value=[value to set]&id=[user id]</li>
            <li>Delete: localhost:8080/mavenWebApp/delete?id=[user id]</li>
            <li>Select: localhost:8080/mavenWebApp/select?id=[user id/null to select all]</li>
        </ul>

        <a href="Start">User Website with static methods</a>
        <ul>
            <li>To select, enter the user id or leave it blank to select all</li>
            <li>To add, click the button Add User and fill out the form and press submit</li>
            <li>To update or delete, first search for the user and then select the radio button and click update or delete.</li>
            <li>For updating, fill out the columns as you desire and click update. </li>
            <li>For deleting, confirm that you want to delete the selected user</li>
        </ul>

        <a href="start.jsp">User Website with Rest Api</a>
        <ul>
            <li>To select, enter the user id or leave it blank to select all</li>
            <li>To add, click the button Add User and fill out the form and press Add.</li>
            <li>To update or delete, first search for the user, then select the radio button and click update or delete.</li>
            <li>For updating, select the column you want to change from the drop down list and fill out the value you want to set it to and click update. </li>
            <li>For deleting, confirm that you want to delete the selected user</li>
        </ul>

        <a href="books.jsp">Book Website with Rest Api</a>
        <ul>
            <li>To search a book, enter the title/part of the title or leave it blank to search all</li>
            <li>To add, click the button Add book and fill out the form and press Add.</li>
            <li>To update or delete, first search for the book, then select the radio button and click update or delete.</li>
            <li>For updating, select the column you want to change from the drop down list and fill out the value you want to set it to and click update. </li>
            <li>For deleting, confirm that you want to delete the selected book</li>
        </ul>
    </div>
  <script>
      function openHome()
      {
          document.getElementById("home").style.display = "block";
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