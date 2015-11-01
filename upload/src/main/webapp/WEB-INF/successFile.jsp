<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<style type="text/css">
		.container { 
			width: 350px; 
			float: left; 
			background: #000; 
			color: #fff;
			overflow: hidden; 
		}
		.record { 
			width: 350px; 
			height: 20px; 
			background: #fff;
			color: #000; 
		}
	</style>
</head>

<body>
	<h2>Rezultatai</h2>
	
	<div class="container">answerAG:<br>
		<c:forEach var="entry" items="${answerAG}">
		    <div class="record">${entry.key} ----- ${entry.value}<br></div>
		</c:forEach><br>
	</div>
	
	<div class="container">answerHN:<br>
		<c:forEach var="entry" items="${answerHN}">
		    <div class="record">${entry.key} ----- ${entry.value}<br></div>
		</c:forEach><br>
	</div>
	
	<div class="container">answerOU:<br>
		<c:forEach var="entry" items="${answerOU}">
		    <div class="record">${entry.key} ----- ${entry.value}<br></div>
		</c:forEach><br>
	</div>
	
	<div class="container">answerVZ:<br>
		<c:forEach var="entry" items="${answerVZ}">
		    <div class="record">${entry.key} ----- ${entry.value}<br></div>
		</c:forEach><br>
	</div>
</body>

</html>