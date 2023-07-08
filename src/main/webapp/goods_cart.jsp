<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>购物车</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/cart.js"></script>
</head>
<body>
	

	<jsp:include page="/header.jsp"></jsp:include>

	
	<!--cart-items-->
	<div class="cart-items">
		<div class="container">
		
		
		
			<h2>我的购物车</h2>
			
			<c:forEach items="${order.itemMap }" var="item">
				<div class="cart-header col-md-6">
					<div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							<a href="${pageContext.request.contextPath }/goods/goods_detail?id=${item.key}">
								<img src="${pageContext.request.contextPath }${item.value.goods.cover}" class="img-responsive">
							</a>
						</div>
						<div class="cart-item-info">
							<h3><a href="${pageContext.request.contextPath }/goods/goods_detail?id=${item.key}">${item.value.goods.name}</a></h3>
							<h3><span>单价: ¥ ${item.value.price}</span></h3>
							<h3><span>数量: ${item.value.amount}</span></h3>
							<a class="btn btn-info" href="javascript:buy(${item.key});">增加</a>
							<a class="btn btn-warning" href="javascript:lessen(${item.key});">减少</a>
							<a class="btn btn-danger" href="javascript:deletes(${item.key});">删除</a>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</c:forEach>
				
			
			<div class="cart-header col-md-12">
				<hr>
				<h3>订单总金额: ¥ ${order.total }</h3>
				<a class="btn btn-success btn-lg" style="margin-left:74%" href="${pageContext.request.contextPath }/user/order_submit">提交订单</a>
			</div>
			
			
			
		</div>
	</div>
	<!--//cart-items-->	
	
	
	<jsp:include page="/footer.jsp"></jsp:include>


</body>
</html>