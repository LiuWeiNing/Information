/**
 * 
 */
var pageIndex = 1;
var previous = pageIndex;
function Edit(title)
{
	var url = $("#title").text()+"-edit.html";
	sessionStorage.setItem("title", title);
	$("#frame").load(url);
	}
function Delete(title,path)
					{
//						alert(path);
						$.ajax(
								{
									url:'delete.action',
									type:'post',
									data : {
										path : path,
										title : title
									},
									datatype:'json',
									success:function(data)
									{
										var result = eval("(" + data + ")");
//										alert(result.flag);
										if(result.flag=="true")
											{
//											alert(result.msg);
											show(pageIndex);
											}
										else
											{
											alert(result.msg);
											}
										
									}
									
								})
								
					}
function show(data) {
	if ($("#title").text() == "Knowledge") {
		path = "Knowledge";
	} else if ($("#title").text() == "News") {
		path = "New";
	} else if ($("#title").text() == "Notice") {
		path = "Notice";
	} else if ($("#title").text() == "Project") {
		path = "Project";
	}
	$.ajax({
		url : 'getAllTitle.action',
		type : 'post',
		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
		data : {
			path : path,
			pageIndex : data
		},
		dataType : 'json',
		success : function(data) {
		
		//alert(data);
			//			alert("++"+data);
			var result = eval("(" + data + ")");
//			alert(result.length);
		if(result.length == 0)
			{
			
			if(pageIndex==1)
				{
				$("#titleList").append("<tr><td>\u6682\u672a\u6dfb\u52a0\u76f8\u5173\u4fe1\u606f</td></tr>");
			$("#previous").hide();
			$("#next").hide();}
			else
				{
				alert("\u6682\u65e0\u66f4\u591a\u65b0\u95fb");
//				$("#titleList").append("<tr><td>\u6682\u65e0\u66f4\u591a\u65b0\u95fb</td></tr>");
				}
			pageIndex=previous;
			return;
			}
		
		else if(result.length != 0) { 
			var t = document.getElementById("showList"); //获取展示数据的表格 
			while (t.rows.length != 0) { 
			t.removeChild(t.rows[0]); //在读取数据时如果表格已存在行．一律删除 
			}
			$("#titleList").append("<tr><th>#</th><th width='50%'>\u6807\u9898</th><th width='40%'>\u64cd\u4f5c</th></tr>");
			$.each(result,function(i, value) {
				$("#titleList").append("<tr>"
											+ "<td>"
											+ i
											+ "</td>"
											+ "<td>"
											+ value
											+ "</td>"
											+ "<td>"
											+ "<button class='btn btn-small btn-warning  knowledge-edit-button' onclick='Edit(\""+value+"\")'>"
											+ "<span class='glyphicon glyphicon-pencil'>"
											+ "</span>\u2000\u4fee\u6539</button>"
											+ "&nbsp<button class='btn btn-small btn-danger' type='button' onclick='Delete(\""+value+"\",\""+path+"\")'>"
											+ "<span class='glyphicon glyphicon-trash'>"
											+ "</span>\u2000\u5220\u9664</button>"
											+ "</td>"
											+ "</tr>");

				});
		} 
		

	},
	error:function()
	{
		if (result.length != 0) { 
			var t = document.getElementById("showList"); //获取展示数据的表格 
			while (t.rows.length != 0) { 
			t.removeChild(t.rows[0]); //在读取数据时如果表格已存在行．一律删除 
			} 
		} 
	}
});
}
$(document).ready(function() {
					var path;
					show(pageIndex);
					$("#previous").click(function()
							{
						if(pageIndex>1)
							{
							pageIndex = pageIndex-1;
							show(pageIndex);
							}
						else
							{
							alert("\u5df2\u5728\u7b2c\u4e00\u9875");
							}
							});
					$("#next").click(function()
							{
						previous=pageIndex;
						pageIndex = pageIndex + 1;
						show(pageIndex);
							});
					
					

});