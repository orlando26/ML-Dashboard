var features = [];
var errors = [];
$(document).ready(function(){

	$('#btn-shuffle').click(function(){
		var btn = $(this).text();
		$.post('/ml-dashboard/NetworkServlet',{
			btnPressed : 'shuffle'
		});
	});
	
	$('#btn-segregate').click(function(){
		var btn = $(this).text();
		$.post('/ml-dashboard/NetworkServlet',{
			btnPressed : 'segregate'
		});
	});
	
	$('#btn-train').click(function(){
		$.post('/ml-dashboard/NetworkServlet',{
			btnPressed : 'train'
		}, function(response){
			errors = [];
			errors = response;
			errors.forEach(function(error, idx, arr){
				errorsStr = 'Epoch ' + (idx+1) + ' - ' + 'Error: ' + error;
				$('#errors').append("<li>" + errorsStr + "</li>");
			});
			$('#errors-div').show();
			plotErrors();
		});
	});

	$('#btn-normalize').click(function(){
		var btn = $(this).text();
		$.post('/ml-dashboard/NetworkServlet',{
			btnPressed : 'setFeaturesType'
		}, function(response){
			response.forEach(function(val){
				feature = {};
				feature['name'] = val;
				feature['type'] = 'Continuous';
				features.push(feature);
			});
			var featuresCnt = features.length;
			console.log(features);
			$('.normalize-select-text').each(function(){
				var num = parseInt($(this).text());
				$(this).text(num + ':' + features[num].name);
			});
			$('#featuresModal').modal();
		});
	});

	$('#ok-modal').click(function(){
		console.log(features);
		var object = JSON.stringify(features);
		$.post('/ml-dashboard/NetworkServlet',{
			btnPressed : 'normalize',
			featureTypes : object
		}, function(response){
			$('#featuresModal').modal('toggle');
		});
	});

	$('.feature-select').change(function(){
		var featureVal = $(this).val();
		var featureName = $(this).prev().find('.input-group-text').text();
		var idx = featureName.split(":")[0];
		features[idx].type = featureVal;
	});

});

function plotErrors() {

	var trace1 = {
			y: errors,
			type: 'lines+markers'
	};

	var data = [trace1];

	var layout = {
			xaxis: {
				title: 'epoch'
			},
			yaxis: {
				title: 'Error'
			}
	}

	Plotly.newPlot('plotError', data, layout);
}
