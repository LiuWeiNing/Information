/**
 * 
 */
$(document).ready(function() {
//	alert($("#title").text());
	$("#submit").click(function() {
		var param = $("#form").serialize();
//		alert(param);
		var url;
		if ($("#title").text() == "Knowledge") {
			url = 'addKnowledge.action';
		} else if ($("#title").text() == "News") {
			url = 'addNews.action';
		} else if ($("#title").text() == "Notice") {
			url = 'addNotice.action';
		} else if ($("#title").text() == "Project") {
			url = 'addProject.action';
		}
//		alert(url);
		$.ajax({
			url : url,
			type : 'post',
			data : param,
			dataType : 'json',
			success : function(data) {
				var result = eval("(" + data + ")");
//				alert(result);
				if (result.flag == "true") {
					alert(result.msg);
					$("#frame").load(result.url);
				} else {
					alert(result.msg);
				}
			}
		});

	})

});
