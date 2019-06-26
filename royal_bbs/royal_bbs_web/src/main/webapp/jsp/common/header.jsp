<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<script>
    $(function () {
        //1.给登录按钮绑定单机事件
        $("#dl").click(function () {
        //2.发送ajsx请求，提交表单数据
        $.post("user/login.do",$("#sj").serialize(),function (data) {
            if(data!=null){
            //    登录成功
                //alert("登录成功")
                //location.href="index-old.jsp";
                window.location.reload();
            }else {
            //    登录失败
                alert("登录失败")
            }
        },"json")
        })
    })
</script>
<body>
<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l"></div>
        <div class="hm-inner-r r">
            <div class="box">
                <c:if test="${empty user}">
                    <a href="javascript:;" id="login" class="to-login">游客登录</a>
                    <a href="/jsp/register.jsp">【新用户注册】</a>
                </c:if>
                <c:if test="${not empty user}">
                    <c:if test="${user.role == 1}">
                       欢迎 普通用户：<a href="javascript:;" id="login" class="to-login">${user.userName}</a>
                    </c:if>
                    <c:if test="${user.role == 2}">
                        欢迎 高级用户：<a href="javascript:;" id="login" class="to-login">${user.userName}</a>
                    </c:if>
                    <c:if test="${user.role == 3}">
                        欢迎 超级管理员：<a href="javascript:;" id="login" class="to-login">${user.userName}</a>
                    </c:if>
                    <a href="/jsp/userInfo.jsp">个人中心</a>
                    <a href="/user/logout.do">注销</a>

                </c:if>
                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>


                    <%--<form action="user/login.do" method="post">--%>
                    <form id="sj" action="user/login.do" method="post" >
                        <ul class="editInfos">
                            <li>用户名：<input type="text" id="userName" name="userName" class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt"/></li>
                            <%--<li><input type="submit" value="登录" class="submitBtn" id="submit-btn"/></li>--%>
                            <li><input type="button" value="登录" class="submitBtn" id="dl"/></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
  $(function () {
      //显示弹框
      $('.box #login').click(function () {
          var className = $(this).attr('class');
          $('#dialogBg').fadeIn(300);
          $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
          $('#userName').focus();
          $("#j_fixedBar").hide();
      });

      //关闭弹窗
      $('.closeDialogBtn').click(function () {
          $('#dialogBg').fadeOut(300, function () {
              $('#dialog').addClass('bounceOutUp').fadeOut();
              $("#j_fixedBar").show();
          });
      });

  });
</script>
</html>