<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ML-Dashboard</title>
</head>
<body>
	<header> 
		<%@ include file="template/header.jsp"%>
		<script src="resources/js/index.js"></script>
		<link rel="stylesheet" href="resources/css/styles.css">
	</header>
	<article>
	<div class="container">
		<div class="jumbotron">
			<form method="post" action="UploadServlet" enctype="multipart/form-data">
				<!-- COMPONENT START -->
				<div class="form-group">
					<div class="input-group input-file" name="Fichier1">
						<input type="text" class="form-control"
							placeholder='Choose a file...' /> <span class="input-group-btn">
							<button class="btn btn-default btn-choose" type="button">Choose</button>
						</span>


					</div>
				</div>
				<!-- COMPONENT END -->
				<div class="form-group">
					<button class="btn btn-lg btn-outline-success btn-block"type="submit">Upload</button>
					<button class="btn btn-lg btn-outline-danger btn-block"type="reset">Reset</button>
				</div>
			</form>
		</div>
	</div>
	</article>
	<footer> <%@ include file="template/footer.jsp"%>
	</footer>
</body>
</html>