<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/simpleCart.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/cart.js"></script>
</head>
<body>


<jsp:include page="/header.jsp">
    <jsp:param value="2" name="flag"/>
</jsp:include>

<!--products-->
<div class="products">
    <div class="container">
        			<h2><c:choose> <c:when test="${empty t }">全部系列</c:when><c:otherwise>${t.name }</c:otherwise> </c:choose>  </h2>

        <div class="col-md-12 product-model-sec">

            <c:forEach items="${requestScope.tList.list}" var="g" varStatus="stat">
                <div class="product-grid">
                    <a href="${pageContext.request.contextPath }/goods/goods_detail?id=${g.id}">
                        <div class="more-product"><span> </span></div>
                        <div class="product-img b-link-stripe b-animate-go  thickbox">
                            <img src="${pageContext.request.contextPath }${g.cover}" class="img-responsive" alt="${g.name }" width="240" height="240">
                            <div class="b-wrapper">
                                <h4 class="b-animate b-from-left  b-delay03">
                                    <button>查看详情</button>
                                </h4>
                            </div>
                        </div>
                    </a>
                    <div class="product-info simpleCart_shelfItem">
                        <div class="product-info-cust prt_name">
                            <h4>${g.name }</h4>
                            <span class="item_price">¥ ${g.price }</span>
                            <input type="button" class="item_add items" value="加入购物车" onclick="buy(${g.id})">
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="clearfix"> </div>
        </div>

        <div style='text-align:center;'>
            <a class='btn btn-info'   <c:if test="${requestScope.tList.pageNum==1 }">disabled</c:if>  <c:if test="${requestScope.tList.pageNum!=1 }">href="${pageContext.request.contextPath }/goods/goods-tList?page=1&size=${requestScope.tList.pageSize}&id=${t.id}"</c:if>>首页</a>
            <a class='btn btn-info' <c:if test="${requestScope.tList.pageNum==1}">disabled</c:if> <c:if test="${requestScope.tList.pageNum!=1 }">href="${pageContext.request.contextPath }/goods/goods-tList?page=${requestScope.tList.pageNum-1}&id=${t.id}"</c:if>>上一页</a>
            <h3 style='display:inline;'>[${requestScope.tList.pageNum}/${requestScope.tList.pages}]</h3>
            <h3 style='display:inline;'>[${requestScope.tList.total }]</h3>
            <a class='btn btn-info' <c:if test="${requestScope.tList.total==0 || requestScope.tList.pageNum==requestScope.tList.pages }">disabled</c:if> <c:if test="${requestScope.tList.pageNum!=requestScope.tList.pages }">href="${pageContext.request.contextPath }/goods/goods-tList?page=${requestScope.tList.pageNum+1}&id=${t.id}"</c:if>>下一页</a>
            <a class='btn btn-info' <c:if test="${requestScope.tList.total==0 || requestScope.tList.pageNum==requestScope.tList.pages }">disabled</c:if> <c:if test="${requestScope.tList.pageNum!=requestScope.tList.pages }">href="${pageContext.request.contextPath }/goods/goods-tList?page=${requestScope.tList.pages}&id=${t.id}"</c:if>>尾页</a>
            <input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }/goods/goods-tList?id=${t.id}&page="+(this.previousSibling.value)'>GO</a>
        </div>


    </div>
</div>
<!--//products-->

<jsp:include page="/footer.jsp"/>

</body>
</html>