<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="CSS.css"/>
</head>
<body id="body" >

<div id="con">
<% 
out.println("<table border=2 width='50%' id='table' >");
out.println("<tr><th><h1>Parameter<h1></th><th><h1>value<h1></th></tr>");
%>
<tr><td><h2 class="Color">date_and_time</h2></td><td>${datetime}</td></tr>
<tr><td><h2 class="Color">temperture_in_Celsius     </h2></td><td>${tempertureCelsius}</td></tr>
<tr><td><h2 class="Color">weather_Condition</h2></td><td>${weatherCondition}</td></tr>
<tr><td><h2 class="Color">humidity</h2></td><td>${humidity}</td></tr>
<tr><td><h2 class="Color">windSpeed</h2></td><td>${windSpeed}</td></tr>
<% 
out.println("</table>");
%>
</div>

</body>
</html>

