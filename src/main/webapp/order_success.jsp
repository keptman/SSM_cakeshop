<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>支付成功</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/cart.js"></script>
</head>
<body>
	
	<jsp:include page="/header.jsp"></jsp:include>

	
	<!--cart-items-->
	<div class="cart-items">
		<div class="container">
		<c:if test="${!empty msg }">
			<div class="alert alert-success">${msg }</div>
		</c:if>	
			<p><a class="btn btn-success" href="${pageContext.request.contextPath }/goods/order_list">查看我的订单</a></p>
		</div>
	</div>
	<!--//cart-items-->	
	
	



	<jsp:include page="/footer.jsp"></jsp:include>


</body>
</html>