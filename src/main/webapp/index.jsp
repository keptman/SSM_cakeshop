<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
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
	
<!-- 头部 -->
<jsp:include page="/header.jsp">
	<jsp:param value="1" name="flag"/>
</jsp:include>

	
	<!--banner-->
	
		<div class="banner">
			<div class="container">
				<h2 class="hdng"><a href="${pageContext.request.contextPath }/goods/goods_detail?id=${fGoods.id }">${fGoods.name }</a><span></span></h2>
				<p>今日精选推荐</p>
				<a class="banner_a" href="javascript:;" onclick="buy(${fGoods.id })">立刻购买</a>
				<div class="banner-text">		
					<a href="${pageContext.request.contextPath }/goods/goods_detail?id=${fGoods.id }">
						<img src="${pageContext.request.contextPath }${fGoods.cover}" alt="${fGoods.name }" width="350" height="350">
					</a>	
				</div>
			</div>
		</div>
				
	<!--//banner-->
	
	<div class="subscribe2"></div>
	
	<!--gallery-->
	<div class="gallery">
		<div class="container">
			<div class="alert alert-danger">热销推荐</div>
			<div class="gallery-grids">
				
				<c:forEach items="${hotList}" var="g">
				<div class="col-md-4 gallery-grid glry-two">
						<a href="${pageContext.request.contextPath }/goods/goods_detail?id=${g.id}">
							<img src="${pageContext.request.contextPath }${g.cover}" class="img-responsive" alt="${g.name}" width="350" height="350"/>
						</a>
						<div class="gallery-info galrr-info-two">
							<p>
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
								<a href="${pageContext.request.contextPath }/goods/goods_detail?id=${g.id}">查看详情</a>
							</p>
							<a class="shop" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
							<div class="clearfix"> </div>
						</div>
						<div class="galy-info">
							<p>${g.typename } > ${g.name }</p>
							<div class="galry">
								<div class="prices">
									<h5 class="item_price">¥ ${g.price }</h5>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</c:forEach>
					
				
			</div>
			
			<div class="clearfix"></div>
			<div class="alert alert-info">新品推荐</div>
			<div class="gallery-grids">	
				
				<c:forEach items="${newList}" var="g">
				<div class="col-md-3 gallery-grid ">
						<a href="${pageContext.request.contextPath }/goods/goods_detail?id=${g.id}">
							<img src="${pageContext.request.contextPath }${g.cover}" class="img-responsive" alt="${g.name }"/>
						</a>
						<div class="gallery-info">
							<p>
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 
								<a href="${pageContext.request.contextPath }/goods/goods_detail?id=${g.id}">查看详情</a>
							</p>
							<a class="shop" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
							<div class="clearfix"> </div>
						</div>
						<div class="galy-info">
							<p>${g.typename } > ${g.name }</p>
							<div class="galry">
								<div class="prices">
									<h5 class="item_price">¥ ${g.price }</h5>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</c:forEach>
					
				
					
				
				
			</div>	
		</div>
	</div>
	<!--//gallery-->
	
	<!--subscribe-->
	<div class="subscribe"></div>
	<!--//subscribe-->
	
	
	<!-- 尾部 -->
	<jsp:include page="/footer.jsp"></jsp:include>
	


</body>
</html>