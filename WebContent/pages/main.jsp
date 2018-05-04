<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header> 
		<%@ include file="../template/header.jsp"%>
		<script src="../resources/js/main.js"></script>
		<link rel="stylesheet" href="../resources/css/styles.css">
	</header>
	<article>
		<div class="container">
			<form>
				<button class="btn btn-outline-danger btn-block" type="button" id="btn-shuffle">shuffle</button>
				<button class="btn btn-outline-danger btn-block" type="button" id="btn-segregate">segregate</button>
				<button class="btn btn-outline-danger btn-block" type="button" id="btn-normalize">normalize</button>
			</form>
		</div>
	</article>
	<!-- Modal -->
<div class="modal fade" id="featuresModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<%
    String str = request.getParameter("cnt");
    int cnt = Integer.parseInt(str);
	%>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Features/labels type</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div id="select_container">
         <%for(int i = 0;i < cnt;i++){ %>
        	<label class="sr-only" for="inlineFormInputGroup">Feature type</label>
      <div class="input-group mb-2">
        <div class="input-group-prepend">
          <div class="input-group-text"><%=i %></div>
        </div>
        <select type="text" class="form-control feature-select" id="inlineFormInputGroup" placeholder="Feature type">
        	<option>Numeric</option>
        	<option>Not numeric</option>
        	<option>Ignore</option>
        </select>
       
      </div>
       <%} %>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" id="clode-modal" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="ok-modal">Save changes</button>
      </div>
    </div>
  </div>
</div>
	<footer> 
		<%@ include file="../template/footer.jsp"%>
	</footer>
</body>
</html>