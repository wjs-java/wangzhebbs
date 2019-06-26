<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8"/>
    <title>王者荣耀论坛注册页</title>
    <link rel="stylesheet" href="../css/common.css"/>
    <link rel="stylesheet" href="../css/common-new.css"/>
    <link rel="stylesheet" href="../css/search.css"/>
    <link rel="stylesheet" href="../css/register.css"/>
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script>
        //校验用户名
        var result = false;
        function checkUserName() {
            //1.获取用户名值
            var userName = $("#userName").val();
            //2.定义正则
            var reg_username = /^[a-zA-Z0-9_]*$/;
            //3.判断，给出提示信息
            var flag = reg_username.test(userName);
            if(flag){
                $.post("/user/findByUsername.do",{"username":userName},function(data){
                    //处理服务器响应的数据  data  {flag:true,errorMsg:"注册失败"}
                    if(data){
                        //用户名合法
                        $("#userName").css("border","");
                        result = true;
                    }else{
                        // alert("用户名已存在")
                        $("#userName").css("border","1px solid red");
                        result = false;
                    }
                },"json");
                // $("#userName").css("border","");
            }else{
                //用户名非法,加一个红色边框
                $("#userName").css("border","1px solid red");
            }
            return result;
        }
        //校验密码
        function checkUserPass() {
            //   1.获取密码值
            var userPass = $("#userPass").val();
            //2.定义正则
            var reg_userPass = /^[a-zA-Z0-9]{6,10}$/;

            //3.判断，给出提示信息
            var flag = reg_userPass.test(userPass);
            if(flag){
                //密码合法
                $("#userPass").css("border","");
            }else{
                //密码非法,加一个红色边框
                $("#userPass").css("border","1px solid red");
            }
            return flag;
        }
        //校验邮箱
        function checkEmail() {
            //1.获取邮箱值
            var email = $("#email").val();
            //2.定义正则
            var reg_email =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

            //3.判断，给出提示信息
            var flag = reg_email.test(email);
            if(flag){
                //邮箱合法
                $("#email").css("border","");
            }else{
                //邮箱非法,加一个红色边框
                $("#email").css("border","1px solid red");
            }
            return flag;
        }
        $(function () {
            //当表单提交时，调用所有的校验方法
            $("#registerForm").submit(function(){
                //1.发送数据到服务器
                if(checkUserName() && checkUserPass() && checkEmail()){
                    //校验通过,发送ajax请求，提交表单的数据
                    $.post("/user/saveRegister.do",$(this).serialize(),function(data){
                        //处理服务器响应的数据  data  {flag:true,errorMsg:"注册失败"}
                        if(data){
                            //注册成功，跳转成功页面
                            location.href="/index.jsp";
                        }else{
                            //注册失败,给errorMsg添加提示信息
                            $("#errorMsg").html("注册失败！");

                        }
                    });
                }
                return false;
                //2.不让页面跳转
                //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
            });
            //当某一个组件失去焦点是，调用对应的校验方法
            $("#userName").blur(checkUserName);
            $("#userPass").blur(checkUserPass);
            $("#email").blur(checkEmail);
        });
    </script>
</head>
<body>
<!-- 头部 -->
<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l">
        </div>
        <div class="hm-inner-r r">
            <div class="box">
                <a href="javascript:;" id="login" class="to-login">游客登录</a>
                <a href="/">【新用户注册】</a>
                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="../images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>
                    <form action=" " method="post" >
                        <ul class="editInfos">
                            <li>用户名：<input type="text" name="userName" class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" name="userPass" class="ipt"/></li>
                            <li><input type="submit" value="登录" class="submitBtn"/></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;"><img src="../images/logo.png" height="64" width="168" alt=""/></a>
            </h1>
            <div class="search-box l">
                <form action="javascript:;">
                    <input type="text" class="txt l" placeholder="请输入关键字">
                    <input type="button" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href="index.html">首页</a><span>></span>注册页面
        </div>
    </div>
</div>


<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="reg-box">
            <h2>用户注册<span>（红色型号代表必填）</span></h2>
            <div  class="reg-info" id="errorMsg" style="color: red" ;>
                <form id="registerForm" action="${pageContext.request.contextPath}/user/saveRegister.do" method="post">
                    <ul>
                        <li>
                            <div class="reg-l">
                                <span class="red">*</span> 用户名：
                            </div>
                            <div class="reg-c">
                                <input type="text" id="userName" name="userName" class="txt" value=""/>
                            </div>
                            <span class="tips" id="username-span">用户名必须是英文、数字、下划线成</span>
                        </li>
                        <li>
                            <div class="reg-l">
                                <span class="red">*</span> 密&nbsp;&nbsp;&nbsp;码：
                            </div>
                            <div class="reg-c">
                                <input type="password" id="userPass" name="userPass" class="txt" value=""/>
                            </div>
                            <span class="tips" id="password-span">密码长度必须6~10位的英文或数字</span>
                        </li>
                        <li class="no-tips">
                            <div class="reg-l">&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;箱：</div>
                            <div class="reg-c">
                                <input type="text" id="email" name="email" class="txt" value=""/>
                            </div>
                        </li>
                        <li>
                            <div class="reg-l"></div>
                            <div  class="reg-c">
                                <input  type="submit" class="submit-btn" value="注册"/><br/>
                                <span style="color:red;"></span>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>




<!-- 底部 -->
<div class="hm-footer" style="padding-top:10px;">
    <div class="hm-inner">
        <div class="hm-footer-cpr">
            <p>Copyright@2006-2017 ITCAST. All Rights Reserved</p>
            <p>传智播客 版权所有</p>
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
<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
</html>