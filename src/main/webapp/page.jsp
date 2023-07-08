<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<div style='text-align:center;'>--%>
<%--<a class='btn btn-info'   <c:if test="${p.pageNo==1 }">disabled</c:if>  <c:if test="${p.pageNo!=1 }">href="${pageContext.request.contextPath }${param.url }?pageNo=1${param.param }"</c:if>>首页</a>--%>
<%--<a class='btn btn-info' <c:if test="${p.pageNo==1 }">disabled</c:if> <c:if test="${p.pageNo!=1 }">href="${pageContext.request.contextPath }${param.url }?pageNo=${p.pageNo-1}${param.param }"</c:if>>上一页</a>--%>
<%--<h3 style='display:inline;'>[${p.pageNo }/${p.totalPage }]</h3>--%>
<%--<h3 style='display:inline;'>[${p.totalCount }]</h3>--%>
<%--<a class='btn btn-info' <c:if test="${p.totalPage==0 || p.pageNo==p.totalPage }">disabled</c:if> <c:if test="${p.pageNo!=p.totalPage }">href="${pageContext.request.contextPath }${param.url }?pageNo=${p.pageNo+1}${param.param }"</c:if>>下一页</a>--%>
<%--<a class='btn btn-info' <c:if test="${p.totalPage==0 || p.pageNo==p.totalPage }">disabled</c:if> <c:if test="${p.pageNo!=p.totalPage }">href="${pageContext.request.contextPath }${param.url }?pageNo=${p.totalPage}${param.param }"</c:if>>尾页</a>--%>
<%--<input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }${param.url }?pageNo="+(this.previousSibling.value)+"${param.param }"'>GO</a>--%>
<%--</div>--%>
<div style='text-align:center;'>
    <a class='btn btn-info'   <c:if test="${requestScope.p.pageNum==1 }">disabled</c:if>  <c:if test="${requestScope.p.pageNum!=1 }">href="${pageContext.request.contextPath }${param.url }?page=1${param.param }"</c:if>>首页</a>
    <a class='btn btn-info' <c:if test="${requestScope.p.pageNum==1 }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=1 }">href="${pageContext.request.contextPath }${param.url }?page=${requestScope.p.pageNum-1 }${param.param }"</c:if>>上一页</a>
    <h3 style='display:inline;'>[${requestScope.p.pageNum }/${requestScope.p.pages}]</h3>
    <h3 style='display:inline;'>[${requestScope.p.total }]</h3>
    <a class='btn btn-info' <c:if test="${requestScope.p.total==0 || requestScope.p.pageNum==requestScope.p.pages }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=requestScope.p.pages }">href="${pageContext.request.contextPath }${param.url }?page=${requestScope.p.pageNum+1 }${param.param }"</c:if>>下一页</a>
    <a class='btn btn-info' <c:if test="${requestScope.p.total==0 || requestScope.p.pageNum==requestScope.p.pages }">disabled</c:if> <c:if test="${requestScope.p.pageNum!=requestScope.p.pages }">href="${pageContext.request.contextPath }${param.url }?page=${requestScope.p.total }${param.param }"</c:if>>尾页</a>
    <input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }${param.url }?page="+(this.previousSibling.value)+"${param.param }"'>GO</a>
</div>