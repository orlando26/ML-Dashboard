<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header> <%@ include file="../template/header.jsp"%>
	<script src="../resources/js/main.js"></script> <script
		type="text/javascript" src="https://cdn.plot.ly/plotly-latest.min.js"></script>
	<link rel="stylesheet" href="../resources/css/styles.css">
	</header>
	<article>
	<div class="container">
		<form>

			<button class="btn btn-outline-danger btn-block" type="button"
				id="btn-segregate">segregate</button>
			<button class="btn btn-outline-danger btn-block" type="button"
				id="btn-normalize">Create Network</button>
			<button class="btn btn-outline-danger btn-block" type="button"
				id="btn-train">train network</button>
			<br>
			<div class="row" id="errors-div" style="display: none">
				<div class="col-5">
					<h3>Errors:</h3>
					<p></p>
					<div class="card" id="errors-card">

						<div class="card-body" style="height: 20em; overflow-y: scroll;">
							<ul id="errors"></ul>
						</div>
					</div>
				</div>
				<div class="col-7">
					<div id="plotError" style="width: 100%"></div>
				</div>
			</div>
			<div id="plotExperiments" style="width: 100%"></div>


		</form>
	</div>
	</article>
	<!-- Modal -->
	<div class="modal fade" id="featuresModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<%
			String str = request.getParameter("cnt");
			int cnt = Integer.parseInt(str);
		%>
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Create Network</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active" id="home-tab"
							data-toggle="tab" href="#home" role="tab" aria-controls="home"
							aria-selected="true">Normalize Values</a></li>
						<li class="nav-item"><a class="nav-link" id="profile-tab"
							data-toggle="tab" href="#profile" role="tab"
							aria-controls="profile" aria-selected="false">Network
								Topology</a></li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="home" role="tabpanel"
							aria-labelledby="home-tab">
							<div id="select_container">
								<%
									for (int i = 0; i < cnt; i++) {
								%>
								<label class="sr-only" for="inlineFormInputGroup">Feature
									type</label>
								<div class="input-group mb-2">
									<div class="input-group-prepend">
										<div class="input-group-text normalize-select-text"><%=i%></div>
									</div>
									<select type="text" class="form-control feature-select"
										id="inlineFormInputGroup" placeholder="Feature type">
										<option>Continuous</option>
										<option>Not Continuous</option>
										<option>Ignore</option>
									</select>

								</div>
								<%
									}
								%>
							</div>
						</div>
						<div class="tab-pane fade" id="profile" role="tabpanel"
							aria-labelledby="profile-tab">
							<div class="card" id="errors-card">

								<div class="card-body">
									<h5 class="card-title">1st layer</h5>
									<label class="sr-only" for="inlineFormInputGroup">Feature
										type</label>
									<div class="input-group mb-2">
										<div class="input-group-prepend">
											<div class="input-group-text">Activation function</div>
										</div>
										<select type="text" class="form-control feature-select"
											id="inlineFormInputGroup" placeholder="Feature type">
											<option>Continuous</option>
											<option>Not Continuous</option>
											<option>Ignore</option>
										</select>
									</div>
									<div class="form-check">
										<input type="checkbox" class="form-check-input"
											id="exampleCheck1"> <label class="form-check-label"
											for="exampleCheck1">Bias</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="clode-modal"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="ok-modal">Create</button>
				</div>
			</div>
		</div>
	</div>
	<footer> <%@ include file="../template/footer.jsp"%>
	</footer>
</body>
</html>