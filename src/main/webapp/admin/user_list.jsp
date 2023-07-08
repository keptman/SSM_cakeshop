<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>客户列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container-fluid">

	<jsp:include page="/admin/header.jsp"></jsp:include>
	
	<div class="text-right"><a class="btn btn-warning" href="${pageContext.request.contextPath }/admin/user_add.jsp">添加客户</a></div>
	<br>
		<c:if test="${!empty msg }">
			<div class="alert alert-success">${msg }</div>
		</c:if>
		<c:if test="${!empty failMsg }">
			<div class="alert alert-danger">${failMsg }</div>
		</c:if>
	<br>
	
	
	
	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">用户名</th>
		<th width="10%">邮箱</th>
		<th width="10%">收件人</th>
		<th width="10%">电话</th>
		<th width="10%">地址</th>
		<th width="10%">操作</th>
	</tr>
	
	<c:forEach items="${requestScope.p.list}" var="u">
         <tr>
         	<td><p>${u.id }</p></td>
         	<td><p>${u.username }</p></td>
         	<td><p>${u.email }</p></td>
         	<td><p>${u.name }</p></td>
         	<td><p>${u.phone }</p></td>
         	<td><p>${u.address }</p></td>
			<td>
				<a class="btn btn-info" href="${pageContext.request.contextPath }/admin/user_reset.jsp?id=${u.id }&username=${u.username }&email=${u.email }">重置密码</a>
				<a class="btn btn-primary" href="${pageContext.request.contextPath }/admin/user_edit.jsp?id=${u.id }&username=${u.username }&email=${u.email }&name=${u.name }&phone=${u.phone }&address=${u.address}">修改</a>
				<a class="btn btn-danger" href="${pageContext.request.contextPath }/admin/user_delete?id=${u.id }">删除</a>
			</td>
       	</tr>
     </c:forEach>
         
     
     
</table>

<br>
	<div style='text-align:center;'>
		<a class='btn btn-info'   <c:if test="${requestScope.p.pageNum==1 }">disabled</c:if>  <c:if test="${requestScope.p.pageNum!=1 }">href="${pageContext.request.contextPath }/admin/user_list?page=1"</c:if>>首页</a>
		<a class='btn btn-info' <c:if test="${requestScope.p.pageNum==1 }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=1 }">href="${pageContext.request.contextPath }/admin/user_list?page=${requestScope.p.pageNum-1 }"</c:if>>上一页</a>
		<h3 style='display:inline;'>[${requestScope.p.pageNum }/${requestScope.p.pages}]</h3>
		<h3 style='display:inline;'>[${requestScope.p.total }]</h3>
		<a class='btn btn-info' <c:if test="${requestScope.p.total==0 || requestScope.p.pageNum==requestScope.p.pages }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=requestScope.p.pages }">href="${pageContext.request.contextPath }/admin/user_list?page=${requestScope.p.pageNum+1 }"</c:if>>下一页</a>
		<a class='btn btn-info' <c:if test="${requestScope.p.total==0 || requestScope.p.pageNum==requestScope.p.pages }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=requestScope.p.pages }">href="${pageContext.request.contextPath }/admin/user_list?page=${requestScope.p.total }"</c:if>>尾页</a>
		<input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }/admin/user_list?&page="+(this.previousSibling.value)'>GO</a>
	</div>
<%--<jsp:include page="/page.jsp">--%>
<%--<jsp:param value="/admin/user_list" name="url"/>--%>
<%--</jsp:include>--%>
<br>
</div>
</body>
</html>