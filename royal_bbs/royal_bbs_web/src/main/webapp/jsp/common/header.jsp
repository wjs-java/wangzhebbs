<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>

<body>

<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
    <div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;">
        <img src="${pageContext.request.contextPath}/images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
</div>


<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l"></div>
        <div class="hm-inner-r r">
            <div class="box">
                <c:if test="${empty user}">
                <a href="javascript:;" id="login" class="to-login">用户登录</a>
                <a href="${pageContext.request.contextPath}/jsp/register.jsp">【新用户注册】</a>
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
                    <a href="register.do">【新用户注册】</a>
                </c:if>
                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>


                    <form action="${pageContext.request.contextPath}/user/login.do" method="post" id="sj">
                        <ul class="editInfos">
                            <li>用户名：<input type="text" id="userName" name="userName" class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt"/></li>
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
        //1.给登录按钮绑定单机事件
        $("#dl").click(function () {
            //2.发送ajsx请求，提交表单数据
            $.post("${pageContext.request.contextPath}/user/login.do",$("#sj").serialize(),function (data) {
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

  window.onload = function() {
      $('#loading-mask').fadeOut(400);
  }
</script>
</html>