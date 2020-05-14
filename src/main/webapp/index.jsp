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
<!-- Modal员工修改-->
<div class="modal fade" id="empUpdateModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myEmpUpdateModel">员工修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
<%--                            <input type="text" class="form-control" id="empName_update_input" name="empName" placeholder="empName">--%>
                            <p class="form-control-static" id="empName_update_input"></p>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="email_update_input" name="email" placeholder="email@foxmail.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="genderF_update_input" value="M"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="genderM_update_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId" id="deptName_update_select">
                                <%--                                <option>1</option>--%>
                                <%--                                <option>2</option>--%>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_update_btn_save">更新</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal员工新增 -->
<div class="modal fade" id="empAddModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="empName_add_input" name="empName" placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="email_add_input" name="email" placeholder="email@foxmail.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_M_add_input" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_F_add_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId" id="deptName_add_select">
<%--                                <option>1</option>--%>
<%--                                <option>2</option>--%>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_add_btn_save">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <!-- 第一行 -->
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn-primary" id="emp_add_btn">新增</button>
            <button class="btn-danger" id="emp_del_btn">删除</button>
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
    var emp_del_btn = document.getElementById("emp_del_btn");
    $(emp_del_btn).click(function () {
        alert(1);
    });
    // 点击编辑按钮，打开修改的modal框
    $(document).on("click",".edit_btn",function () {
        var empId = $(this).attr("edid_id");
        getDeptNames("#deptName_update_select");
        getEmpById(empId);
        $("#emp_update_btn_save").attr("edit-id",empId);
        $("#empUpdateModel").modal({
            backdrop: "static"
        });


    });
    function getEmpById(empId){
        $.ajax({
            url:"${APP_PATH}/emp/"+empId,
            type:"GET",
            success:function (result) {
                console.log(result);
                var emp = result.extend.emp;
                $("#empName_update_input").text(emp.empName);
                $("#email_update_input").val(emp.email);
                $("#empUpdateModel input[name=gender]").val([emp.gender]);
                //
                //$("#empUpdateModel select").val([emp.dId]);
                $("#deptName_update_select").val([emp.dId]);
            }
        });
    }
    //change事件，检验用户名是否可用
    $("#empName_add_input").change(function () {
        var empName = $("#empName_add_input").val();
        $.ajax({
            url:"${APP_PATH}/checkUser",
            type:"POST",
            data:"empName="+empName,
            success:function (result) {
                var ele = "#empName_add_input";
                var status = result.codeStatus == "100" ? "success" : "error";
                var msg = result.codeStatus == "100" ? "通过" : "用户名已存在";
                console.log(result.codeStatus);
                show_message(ele,status,msg);
            }
        });
    });

    //显示校验状态
    function show_message(ele, status, msg) {
        //去除上次状态
        console.log(msg);
        $(ele).parent().removeClass("has-success has-error");
        $(ele).parent().next("span").text("");
        if("success" == status){
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        }else if ("error" == status){
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }
    //校验用户名和邮箱的合法性
    function validate_input_form(){
        $("#empName_add_input").parent().removeClass("has-error");
        $("#email_add_input").parent().removeClass("has-error");
        var empName = $("#empName_add_input").val();
        var empEmail = $("#email_add_input").val();
        console.log(empName);
        var regEmpName = /(^[a-zA-Z0-9_-]{4,16}$)|(^[\u2E80-\u9FFF]{2,6})/;

        if(!regEmpName.test(empName)){
            $("#empName_add_input").parent().addClass("has-error");
            $("#empName_add_input").next("span").text("用户名不合法");
            // alert("用户名不合法");
            return false;
        };
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if(!regEmail.test(empEmail)){
            $("#email_add_input").parent().addClass("has-error");
            $("#email_add_input").next("span").text("邮箱不合法");
            // alert("邮箱不合法");
            return false;
        };
        return true;

    }
    $("#emp_update_btn_save").click(function () {
        var s = $("#empUpdateModel form").serialize();
        console.log(s);
        var empId = $(this).attr("edit-id");
        console.log(empId);
        <%--$.ajax({--%>
        <%--    url:"${APP_PATH}/emp/"+empId,--%>
        <%--    type:"POST",--%>
        <%--    data:$("#empUpdateModel form").serialize()+"&_method=put",--%>
        <%--    success:function (result) {--%>

        <%--    }--%>
        <%--});--%>
        $.ajax({
            url:"${APP_PATH}/emp/"+empId,
            type:"PUT",
            data:$("#empUpdateModel form").serialize(),
            success:function (result) {

            }
        });
    });
    $("#emp_add_btn_save").click(function () {
        var s = $("#empAddModel form").serialize();
        if(!validate_input_form()){
            return false;
        }
        console.log(s);
        $.ajax({
            url: "${APP_PATH}/emp",
            type: "POST",
            data: s,
            success:function (result) {
                $("#empAddModel").modal('hide');
                console.log("success");
            }
        });
    });
    $("#emp_add_btn").click(function () {
        getDeptNames("#deptName_add_select");
        $("#empAddModel").modal({
            backdrop: "static"
        });
    });

    function getDeptNames(ele){
        $.ajax({
            url: "${APP_PATH}/depts",
            type: "GET",
            success:function (result) {
                $.each(result.extend.depts,function() {
                    var deptOpinion = $("<option></option>")
                        .append(this.deptName).attr("value",this.deptId);
                    deptOpinion.appendTo(ele);
                })
            }
        })
    }

    $(function () {
        to_page(1);
    });

    function to_page(pn) {
        $.ajax({
            url: "${APP_PATH}/emps",
            data: "pn=" + pn,
            type: "GET",
            success: function (result) {
                build_emps_tables(result);
                build_page_info(result);
                build_page_nav(result);
            }
        });
    }

    function build_page_nav(result) {
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var liFirstPage = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var liPrePage = $("<li></li>").append($("<a></a>").append("&laquo;"));

        if (result.extend.pageInfo.hasPreviousPage == false) {
            liFirstPage.addClass("disabled");
            liPrePage.addClass("disabled");
        } else {
            liFirstPage.click(function () {
                to_page(1)
            });
            liPrePage.click(function () {
                to_page(result.extend.pageInfo.pageNum - 1)
            });
        }
        ul.append(liFirstPage).append(liPrePage);
        $.each(result.extend.pageInfo.navigatepageNums, function (index, pageNum) {
            var liNumPage = $("<li></li>").append($("<a></a>").append(pageNum));
            if (result.extend.pageInfo.pageNum == pageNum) {
                liNumPage.addClass("active");
            }
            liNumPage.click(function () {
                to_page(pageNum);
            });
            ul.append(liNumPage);
        });
        var liNextPage = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var liLasttPage = $("<li></li>").append($("<a></a>").append("尾页").attr("href", "#"));
        if (result.extend.pageInfo.hasNextPage == false) {
            liNextPage.addClass("disabled");
            liLasttPage.addClass("disabled");
        } else {

            liNextPage.click(function () {
                to_page(result.extend.pageInfo.pageNum + 1);
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
        $.each(emps, function (index, emp) {
            var tdEmpId = $("<td></td>").append(emp.empId);
            var tdEmpName = $("<td></td>").append(emp.empName);
            var tdGender = $("<td></td>").append(emp.gender == "M" ? '男' : '女');
            var tdEmail = $("<td></td>").append(emp.email);
            var tdDepartName = $("<td></td>").append(emp.department.deptName);
            var btnMod = $("<button></button>").addClass("btn btn-primary btn-xs edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            btnMod.attr("edid_id",emp.empId);
            var btnDel = $("<button></button>").addClass("btn btn-danger btn-xs del_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            btnDel.attr("del_id",emp.empId);
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
