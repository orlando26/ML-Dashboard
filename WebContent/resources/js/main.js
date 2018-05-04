var features = [];
var aa = 'jkhkj';
$(document).ready(function(){
	
	
	$('#btn-normalize').click(function(){
		var btn = $(this).text();
		$.post('/ml-dashboard/NetworkServlet',{
			btnPressed : btn
		}, function(response){
			response.forEach(function(val){
				feature = {};
				feature['name'] = val;
				feature['type'] = 'numeric';
				features.push(feature);
			});
			var featuresCnt = features.length;
			console.log(features);
			$('.input-group-text').each(function(){
				var num = parseInt($(this).text());
				$(this).text(num + ':' + features[num].name);
			});
			$('#featuresModal').modal();
		});
	});
	
	$('#ok-modal').click(function(){
		console.log(features);
	});
	
	$('.feature-select').change(function(){
		var featureVal = $(this).val();
		var featureName = $(this).prev().find('.input-group-text').text();
		var idx = featureName.split(":")[0];
		console.log(featureVal);
		console.log(featureName);
		features[idx].type = featureVal;
	});
	
	
});
