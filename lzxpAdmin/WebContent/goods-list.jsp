<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort()
			+ request.getContextPath() + "/";

	//折中处理一下
	pageContext.setAttribute("path", path);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统后台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script> -->
<link rel="stylesheet" type="text/css" href=" ${path}css/bootstrap.css" />

<!-- <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/unicorn.main.css" />
<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />  -->

<script type="text/javascript" src="${path}js/jquery-2.1.0.js"></script>
<script src="${path}js/bootstrap.js" type="text/javascript" charset="utf-8"></script>

<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/unicorn.main.css" />
<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" /> 

<!-- <script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.uniform.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/unicorn.js"></script>
	<script src="js/unicorn.tables.js"></script>  -->

	<script type="text/javascript">
	$(function() {
		
		/* 显示所有商品信息 */
	$("#showAll").click(function(){
		
		location.href="${path}GoodsServlet?op=getAllGoods";
		
	});
	
		$('#myModal').modal('hide');
		$('#myModalUpdate').modal('hide');
	
	/* 修改商品信息 */
	$(".update").click(
				function() {

					var goodsName = $(this).parents("tr").find("td").eq(1)
							.text();
					$(".goodsName").val(goodsName);

					var stypeId = $(this).parents("tr").find("td").eq(2)
							.text();
					$(".stypeId").val(stypeId);

					var goodsPrice = $(this).parents("tr").find("td").eq(3)
							.text();
					$(".goodsPrice").val(goodsPrice);

					var goodsContent = $(this).parents("tr").find("td").eq(4)
							.text();
					$(".goodsContent").val(goodsContent);
					
					var goodsStock = $(this).parents("tr").find("td").eq(5)
					.text();
			        $(".goodsStock").val(goodsStock);
			
			        var goodsState = $(this).parents("tr").find("td").eq(6)
			       .text();
			        
	               $(".goodsState").val(goodsState);
	
					var goodsId = $(this).parents("tr").find("td").eq(0)
							.text();
					
					$(".myform").attr(
							"action",
							"${path}GoodsServlet?op=updateGoods&goodsId="
									+ goodsId);

				});
	});
</script>

</head>

<body>

	<div id="header">
		<h1>
			<a href="./dashboard.html">Unicorn Admin</a>
		</h1>
	</div>

	<!-- 左侧菜单栏 -->
	<div id="sidebar">
		<a href="#" class="visible-phone"><i class="icon icon-home"></i>
			Dashboard</a>
		<ul>
			<li class="active"><a href="index.html"><i
					class="icon icon-home"></i> <span>后台首页</span></a></li>


			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>订单管理</span> <span class="label">2</span></a>
				<ul>
					<li><a href="#">已处理</a></li>
					<li><a href="#">未处理</a></li>

				</ul></li>

			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>商品管理</span> <span class="label">2</span></a>
				<ul>
					<li><a href="goods-list.jsp" id="showAll">商品列表</a></li>
					<li><a href="goods-sort.jsp">商品分类</a></li>
					<li><a href="goods-state.jsp">商品状态</a></li>
				</ul></li>

			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>交易记录管理</span> <span class="label">2</span></a>
				<ul>
					<li><a href="#">交易记录管理</a></li>
				</ul></li>

			<li class="submenu"><a href="#"><i class="icon icon-th-list"></i>
					<span>评论管理</span> <span class="label">2</span></a>
				<ul>
					<li><a href="#">评论列表</a></li>
					<li><a href="#">意见反馈</a></li>
				</ul></li>
		</ul>
	</div>

	<div id="style-switcher">
		<i class="icon-arrow-left icon-white"></i> <span>Style:</span> <a
			href="#grey"
			style="background-color: #555555; border-color: #aaaaaa;"></a> <a
			href="#blue" style="background-color: #2D2F57;"></a> <a href="#red"
			style="background-color: #673232;"></a>
	</div> 

	<div id="content">
		<div id="content-header">
			<h1>商品列表</h1>

			<!-- 搜索 -->
			<div id="search">
				<form role="form" class="form-inline" method="post"
					action="GoodsServlet?op=getGoodsByGoodsName">

					<label>商品名： </label> <input type="text" placeholder="搜索..."
						name="goodsName" id="goodsName"
						value="${requestScope.goodsName==null?'':requestScope.goodsName }" />
					<button type="submit" class="tip-right" title="搜索">
						<i class="icon-search icon-white"></i>
					</button>
				</form>
			</div>

			<div class="btn-group">
				<a class="btn btn-large tip-bottom " data-toggle="modal"
					data-target="#myModal" title="新增商品"><i class="icon-ok"></i></a> 
			    <a class="btn btn-large tip-bottom delete " title="删除商品"><i
					class="icon-remove"></i></a>
			    <a class="btn btn-large tip-bottom update" data-toggle="modal"
					data-target="#myModalUpdate" title="修改商品"><i class="icon-pencil"></i></a>
			</div>
		</div>

		
		<!-- 新增商品的模态框 （Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">新增商品</h4>
					</div>
					<div class="modal-body">
						<div class="row clearfix">
							<div class="col-md-12 column"></div>
							<div class="col-md-12 column">

								<!-- 新增表单的开始,设置op的值 -->
								<form class="form-horizontal" role="form" method="post"
									action="${path}GoodsServlet?op=addGoods">
									<div class="form-group">
										<label for="inputEmail3" class="col-md-3 control-label">商品名：</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="goodsName"
												name="goodsName" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-md-3 control-label">类型编号：</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="stypeId"
												name="stypeId" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-md-3 control-label">商品价格：</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="goodsPrice"
												name=""goodsPrice"" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-md-3 control-label">商品描述：</label>
										<div class="col-md-9">
											<textarea class="form-control" id="goodsContent"
												name="goodsContent"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-md-3 control-label">商品库存：</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="goodsStock"
												name="goodsStock" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-md-3 control-label">商品状态：</label>
										<div class="col-md-9">
											<input type="text" class="form-control" id="goodsState"
												name="goodsState" />
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-6">
											<button type="submit" class="btn btn-success btn-block">提交</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-md-4 column"></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 修改商品信息的 模态框（Modal） -->
		<div class="modal fade" id="myModalUpdate" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">修改商品信息</h4>
					</div>
					<div class="modal-body">

						<div class="row clearfix">
							<div class="col-md-12 column"></div>
							<div class="col-md-12 column">
								<form class="form-horizontal myform" role="form" method="post">
										<div class="form-group">
										<label for="inputPassword3" class="col-md-3 control-label">商品名：</label>
										<div class="col-md-9">
											<input type="text" class="form-control goodsName"
												id="goodsName" name="goodsName" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-md-3 control-label">类型编号：</label>
										<div class="col-md-9">
											<input type="text" class="form-control stypeId"
												id="stypeId" name="stypeId" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-md-3 control-label">商品价格：</label>
										<div class="col-md-9">
											<input type="text" class="form-control goodsPrice"
												id="goodsPrice" name="goodsPrice" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-md-3 control-label">商品描述：</label>
										<div class="col-md-9">
											<textarea class="form-control goodsContent"
												id="goodsContent" name="goodsContent"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-md-3 control-label">商品库存：</label>
										<div class="col-md-9">
											<input type="text" class="form-control goodsStock"
												id="goodsStock" name="goodsStock" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-md-3 control-label">商品状态：</label>
										<div class="col-md-9">
											<input type="text" class="form-control goodsState"
												id="goodsState" name="goodsState" />
										</div>
							
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-6">
											<button type="submit" class="btn btn-success btn-block">提交</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-md-4 column"></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
</div>
		<!-- 内容 -->
		<div id="breadcrumb">
			<a href="#" title="Go to Home" class="tip-bottom"><i
				class="icon-home"></i>后台管理</a> <a href="#" class="current">商品列表</a>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">

					<div class="widget-box">
						<div class="widget-title">

							<h5>商品信息列表</h5>
						</div>
						<div class="widget-content nopadding">
							<table class="table table-bordered data-table">
								<thead>
									<tr>
										<th><input type="checkbox"></th>
										<th>商品名</th>
										<th>类别编号</th>
										<th>商品价格</th>
										<th>商品描述</th>
										<th>商品库存</th>
										<th>商品状态</th>
									</tr>
								</thead>
								<tbody>

									<c:if test="${requestScope.list!=null}">
										<c:forEach items="${requestScope.list}" var="goods">

											<tr class="gradeA">
												<td><input type="checkbox" value="${goods.GOODSID}"
													name="GOODSID"></td>
												<td>${goods.GOODSNAME}</td>
												<td>${goods.STYPEID}</td>
												<td>${goods.GOODSPRICE}</td>
												<td>${goods.GOODSCONTENT}</td>
												<td>${goods.GOODSSTOCK}</td>
												<td>${goods.GOODSSTATE}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="row-fluid">
				<div id="footer" class="span12">版权所有 &copy; 2007-2017
					零嘴小铺电子有限公司 闽ICP备14019087号</div>
			</div>
		</div>
	</div>

    <script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.uniform.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/unicorn.js"></script>
	<script src="js/unicorn.tables.js"></script>


	<!--弹框的外部包-->
<link rel="stylesheet"
		href="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	弹框的外部包

	<script type="text/javascript">
	$(function() {
		
		/* 删除商品 */
		$(".delete").click(
                  
						function() {
							
							//提示用户
							$.confirm({
										title : '删除',
										content : '是否确定要删除？',
										buttons : {
											ok : {
												text : "确定",
												btnClass : 'btn-blue',
												action : function() {
													//获取复选框对象
													var checkBox = $("input[type='checkbox']");
													//遍历复选框
													var goodsIdStr = "";
													
													for (var i = 1; i < checkBox
															.size(); i++) {
														if (checkBox
																.eq(i)
																.prop("checked")) {
															//如果复选框的选项被选择，则获取被选中行中隐藏的id
															var goodsId = checkBox
																	.eq(i)
																	.parents(
																			"tr")
																	.find(
																			"input")
																	.val();
															
															goodsIdStr += goodsId
																	+ ",";

														}
													}
													location.href = "GoodsServlet?op=deleteGoods&goodsIdStr="
															+ goodsIdStr;
												}
											},
											cancel : {
												text : "取消",
												btnClass : 'btn-green',
											},
										}
									});
						});
	});
</script>  

</body>
</html>