<%--
  Created by IntelliJ IDEA.
  User: lqk
  Date: 2020/4/22
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>

<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
<link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<head>
    <title>欢迎页面</title>
</head>

<body>

    <div class="container">
        <!-- 第一行 -->
        <div class="row">
            <div class="col-md-12">
                <h1>SSM-CRUD</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn-success">新增</button>
                <button class="btn-danger">删除</button>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <table class="table">
                    <tr>
                        <th>客户编号</th>
                        <th>客户姓名</th>
                        <th>性别</th>
                        <th>邮箱</th>
                        <th>部门</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${pageInfo.list}"  var="emp">
<%--                    <c:forEach items="${emps}"  var="emp">--%>
                        <tr>
                            <th>${emp.empId }</th>
                            <th>${emp.empName }</th>
                            <th>${emp.gender == "F" ? '男' : '女'}</th>
                            <th>${emp.email }</th>
                            <th>${emp.department.deptName}</th>
                            <th>
                                <button class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    编辑
                                </button>
                                <button class="btn btn-danger btn-sm">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    删除
                                </button>
                            </th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                当前第${pageInfo.pageNum}页，总${pageInfo.pages}页，共${pageInfo.total}条记录
            </div>
            <div class="col-lg-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href=${APP_PATH}/emps?pn=1>首页</a>
                        </li>
                        <li>
                            <c:if test="${pageInfo.pageNum != 1}">
                                <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                        </li>
                        <c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">
                            <c:if test="${pageNum == pageInfo.pageNum}">
                                <li class="active">
                                    <a href="#">${pageNum}</a>
                                </li>
                            </c:if>
                            <c:if test="${pageNum != pageInfo.pageNum}">
                                <li>
                                    <a href="${APP_PATH}/emps?pn=${pageNum}">${pageNum}</a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <li>
                            <c:if test="${pageInfo.hasNextPage}">
                                <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                        </li>
                        <li>
                            <a href=${APP_PATH}/emps?pn=${pageInfo.pages}>尾页</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

</body>
</html>
