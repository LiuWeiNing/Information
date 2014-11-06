/**
 * 
 */
$(document).ready(function()
		{
	if (sessionStorage.getItem("isLogin") != "true") {
		$("#myModalLabel").text("Warning");
		$("#modalContent").text("\u5c1a\u672a\u767b\u5f55\uff0c\u8bf7\u8fd4\u56de\u767b\u9646\u754c\u9762\u767b\u5f55")
		$("#myModal").modal();
		$(function () { $('#myModal').on('hide.bs.modal', function () {
			location.href = "login.html";})
		   });
	}
		})