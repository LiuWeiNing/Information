/**
 * 
 */
var path;
$(document).ready(function() {

	if ($("#title").text() == "Knowledge") {
		path = "Knowledge";
	} else if ($("#title").text() == "News") {
		path = "New";
	} else if ($("#title").text() == "Notice") {
		path = "Notice";
	} else if ($("#title").text() == "Project") {
		path = "Project";
	}
	var newstitle = sessionStorage.getItem("title");
	// alert(newstitle);
	$("#inputTitle").val(newstitle);
	$.ajax({
		url : 'getContent.action',
		type : 'post',
		data : {
			path : path,
			title : newstitle
		},
		dataType : 'json',
		success : function(data) {

			var content = eval("(" + data + ")");
			alert(content.content);
			$("#content").val(content.content);
		}
	});
	$("#submit").click(function() {
		var param = $("#form").serialize();
		alert(param);
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
		alert(url);
		$.ajax({
			url : url,
			type : 'post',
			data : param,
			dataType : 'json',
			success : function(data) {
				var result = eval("(" + data + ")");
				alert(result);
				if (result.flag == "true") {
					alert(result.msg);
					$("#frame").load(result.url);
				} else {
					alert(result.msg);
				}
			}
		});
	});

});