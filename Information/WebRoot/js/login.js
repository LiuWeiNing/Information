$(document).ready(
		function() {
			$('input').keypress(function(e) {

				// alert(e.which);
				if (e.which == 13) {
					// alert("hello");
					document.getElementById("submit").click();
				}
			});

			$("#submit").click(
					function() {
						if ($("#userName").val().length == 0) {
							var message = '�û�������Ϊ�գ�';
							// $("#userName").attr("placeholder", message);
							$("#userName").next("span").css("color", "red")
									.text("*" + message);
							// alert(message);
							return false;
						}
						$("#userName").next("span").text("");
						if ($("#passWord").val().length == 0) {
							var message = '���벻��Ϊ�գ�';
							$("#passWord").next("span").css("color", "red")
									.text("*" + message);
							// alert(message);
							return false;
						}
						$("#passWord").next("span").text("");
						$.ajax({
							url : 'adminLogin.action',
							type : 'post',
							data : {
								username : $("#userName").val(),
								password : $("#passWord").val()
							},
							dataType : 'json',
							success : function(data) {
								var result = eval("(" + data + ")");
								if (result.flag == "true") {
									sessionStorage.setItem("isLogin", "true");
									//alert(result.msg);
									location.href = result.url;
								} else {
									//alert(result.msg);
								}
							}
						})
					});

		});