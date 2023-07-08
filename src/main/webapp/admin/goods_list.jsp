<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>商品列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container-fluid">

	<jsp:include page="/admin/header.jsp"></jsp:include>
	
	<div class="text-right"><a class="btn btn-warning" href="${pageContext.request.contextPath }/admin/goods_add.jsp">添加商品</a></div>
	
	<br>
		
	<ul role="tablist" class="nav nav-tabs">
        <li <c:if test="${type==0 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath }/admin/goods_list">全部商品</a></li>
        <li <c:if test="${type==1 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath }/admin/goods_list?type=1">条幅推荐</a></li>
        <li <c:if test="${type==2 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath }/admin/goods_list?type=2">热销推荐</a></li>
        <li <c:if test="${type==3 }">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath }/admin/goods_list?type=3">新品推荐</a></li>
    </ul>
    
    
    
    
	
	<br>

	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">图片</th>
		<th width="10%">名称</th>
		<th width="20%">介绍</th>
		<th width="10%">价格</th>
		<th width="10%">类目</th>
		<th width="25%">操作</th>
	</tr>
	
	<c:forEach items="${requestScope.p.list}" var="g">
         <tr>
         	<td><p>${g.id }</p></td>
         	<td><p><a href="${pageContext.request.contextPath }/goods/goods_detail?id=${g.id}" target="_blank"><img src="${pageContext.request.contextPath }${g.cover}" width="100px" height="100px"></a></p></td>
         	<td><p><a href="${pageContext.request.contextPath }/goods/goods_detail?id=${g.id}" target="_blank">${g.name}</a></p></td>
         	<td><p>${g.intro}</p></td>
         	<td><p>${g.price}</p></td>
         	<td><p>${g.type.name}</p></td>
			<td>
				<p>
				<c:choose>
					<c:when test="${g.isScroll }">
						<a class="btn btn-info" href="${pageContext.request.contextPath }/admin/goods_recommend?id=${g.id }&method=remove&typeTarget=1&page=${p.pageNum}&type=${type}">移出条幅</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-primary" href="${pageContext.request.contextPath }/admin/goods_recommend?id=${g.id }&method=add&typeTarget=1&page=${p.pageNum}&type=${type}">加入条幅</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${g.isHot }">
						<a class="btn btn-info" href="${pageContext.request.contextPath }/admin/goods_recommend?id=${g.id }&method=remove&typeTarget=2&page=${p.pageNum}&type=${type}">移出热销</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-primary" href="${pageContext.request.contextPath }/admin/goods_recommend?id=${g.id }&method=add&typeTarget=2&page=${p.pageNum}&type=${type}">加入热销</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${g.isNew }">
						<a class="btn btn-info" href="${pageContext.request.contextPath }/admin/goods_recommend?id=${g.id }&method=remove&typeTarget=3&page=${p.pageNum}&type=${type}">移出新品</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-primary" href="${pageContext.request.contextPath }/admin/goods_recommend?id=${g.id }&method=add&typeTarget=3&page=${p.pageNum}&type=${type}">加入新品</a>
					</c:otherwise>
				</c:choose>
				
				</p>
				<a class="btn btn-success" href="${pageContext.request.contextPath }/admin/goods_editshow?id=${g.id }&pageNum=${p.pageNum}&type=${type}">修改</a>
				<a class="btn btn-danger" href="${pageContext.request.contextPath }/admin/goods_delete?id=${g.id }&page=${p.pageNum}&type=${type}">删除</a>
			</td>
       	</tr>
    </c:forEach> 
     
     
</table>

<br>
<%--	<div style='text-align:center;'>--%>
<%--		<a class='btn btn-info'   <c:if test="${requestScope.p.pageNum==1 }">disabled</c:if>  <c:if test="${requestScope.p.pageNum!=1 }">href="${pageContext.request.contextPath }/admin/goods_list?page=1&type=${type}"</c:if>>首页</a>--%>
<%--		<a class='btn btn-info' <c:if test="${requestScope.p.pageNum==1 }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=1 }">href="${pageContext.request.contextPath }/admin/goods_list?page=${requestScope.p.pageNum-1 }&type=${type}"</c:if>>上一页</a>--%>
<%--		<h3 style='display:inline;'>[${requestScope.p.pageNum }/${requestScope.p.pages}]</h3>--%>
<%--		<h3 style='display:inline;'>[${requestScope.p.total }]</h3>--%>
<%--		<a class='btn btn-info' <c:if test="${requestScope.p.total==0 || requestScope.p.pageNum==requestScope.p.pages }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=requestScope.p.pages }">href="${pageContext.request.contextPath }/admin/goods_list?page=${requestScope.p.pageNum+1 }&status=type=${type}"</c:if>>下一页</a>--%>
<%--		<a class='btn btn-info' <c:if test="${requestScope.p.total==0 || requestScope.p.pageNum==requestScope.p.pages }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=requestScope.p.pages }">href="${pageContext.request.contextPath }/admin/goods_list?page=${requestScope.p.total }&status=type=${type}"</c:if>>尾页</a>--%>
<%--		<input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }/admin/goods_list?type=${type}&page="+(this.previousSibling.value)'>GO</a>--%>
<%--	</div>--%>
<jsp:include page="/page.jsp">
<jsp:param value="/admin/goods_list" name="url"/>
<jsp:param value="&type=${type }" name="param"/>
</jsp:include>
<br>
</div>
</body>
</html>