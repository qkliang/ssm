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
    <title>员工表</title>
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
            <table class="table" id="table_emp">
                <thead>
                    <tr>
                        <th>客户编号</th>
                        <th>客户姓名</th>
                        <th>性别</th>
                        <th>邮箱</th>
                        <th>部门</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6" id="page_info_area"></div>
        <div class="col-md-6" id="page_nav_area"></div>
    </div>
</div>
<script type="application/javascript">
    $(function () {
        to_page(1);
    });
    function to_page(pn) {
        $.ajax({
            url:"${APP_PATH}/emps",
            data:"pn="+pn,
            type:"GET",
            success:function (result) {
                build_emps_tables(result);
                build_page_info(result);
                build_page_nav(result);
            }
        });
    }

    function build_page_nav(result) {
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var liFirstPage =  $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        var liPrePage = $("<li></li>").append($("<a></a>").append("&laquo;"));

        if (result.extend.pageInfo.hasPreviousPage == false){
            liFirstPage.addClass("disabled");
            liPrePage.addClass("disabled");
        }else{
            liFirstPage.click(function () {
                to_page(1)
            });
            liPrePage.click(function () {
                to_page(result.extend.pageInfo.pageNum -1)
            });
        }
        ul.append(liFirstPage).append(liPrePage);
        $.each(result.extend.pageInfo.navigatepageNums,function (index, pageNum) {
            var liNumPage = $("<li></li>").append($("<a></a>").append(pageNum));
            if(result.extend.pageInfo.pageNum == pageNum){
                liNumPage.addClass("active");
            }
            liNumPage.click(function () {
                to_page(pageNum);
            });
            ul.append(liNumPage);
        });
        var liNextPage = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var liLasttPage =  $("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
        if (result.extend.pageInfo.hasNextPage == false){
            liNextPage.addClass("disabled");
            liLasttPage.addClass("disabled");
        }else{

            liNextPage.click(function () {
                to_page(result.extend.pageInfo.pageNum+1);
            });
            liLasttPage.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
        }
        ul.append(liNextPage).append(liLasttPage);
        var nav = $("<nav></nav>").append(ul);
        nav.appendTo("#page_nav_area");

    }
    function build_page_info(result) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前第 " + result.extend.pageInfo.pageNum + " 页," +
            " 总 " + result.extend.pageInfo.pages + " 页," +
            " 共 " + result.extend.pageInfo.total + " 记录")
    }
    function build_emps_tables(result) {
        $("#table_emp tbody").empty();
        var emps = result.extend.pageInfo.list;
        $.each(emps,function (index, emp) {
            var tdEmpId = $("<td></td>").append(emp.empId);
            var tdEmpName = $("<td></td>").append(emp.empName);
            var tdGender = $("<td></td>").append(emp.gender == "F" ? '男' : '女');
            var tdEmail = $("<td></td>").append(emp.email);
            var tdDepartName = $("<td></td>").append(emp.department.deptName);
            var btnMod = $("<button></button>").addClass("btn btn-primary btn-xs edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            var btnDel = $("<button></button>").addClass("btn btn-danger btn-xs ")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            var tdBtn = $("<td></td>").append(btnMod).append(btnDel);
            $("<tr></tr>").append(tdEmpId)
                          .append(tdEmpName)
                          .append(tdGender)
                          .append(tdEmail)
                          .append(tdDepartName)
                          .append(tdBtn)
                .appendTo("#table_emp tbody");
        });

    }
</script>
</body>

</html>
