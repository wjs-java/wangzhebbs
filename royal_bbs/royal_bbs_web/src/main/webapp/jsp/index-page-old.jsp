<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>王者荣耀论坛</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index-new.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>
</head>
<body>

<!-- 头部 -->
<jsp:include page="common/header.jsp"/>


<!-- 主体部分 -->
<div class="hm-header"></div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="hm-banner"></div>




        <!--头部，帖子统计，搜索-->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="../images/bbs-icon.png" height="80"/></span>
            </div>
                <div class="t clearfix"><h2 class="l">王者荣耀</h2></div>
                <p>
                    <span>今日帖子<strong>${todayArticle}</strong></span>
                    <span>全部帖子<strong>${pageInfo.total}</strong></span>
                </p>
            </div>
            <div class="search-box l">
                <form action="${pageContext.request.contextPath}/article/findByLike.do" method="post">
                    <input type="text" class="txt l" placeholder="请输入关键字" name="msg">
                    <input type="submit" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>




        <!-- 导航 -->
        <ul class="hm-bbs-nav border-lrb clearfix">
            <li class="current">
                <a href="#"><em></em>综合交流区</a>
            </li>
            <li>
                <a href="#"><em></em>BUG反馈区</a>
            </li>
            <li>
                <a href="#"><em></em>新闻公告区</a>
            </li>
            <li>
                <a href="#"><em></em>活动专区</a>
            </li>
        </ul>




        <!-- 主体部分 -->
        <div class="hm-bbs-main border-lrb clearfix">
            <!-- 左侧列表 -->

            <div class="list-view l">



                <ul>

                    <c:forEach items="${pageInfo.list}" var="article">

                    <li class="clearfix ding">
                        <div class="hm-index-title">
                            <i class="set-to-top">${article.isTop}</i> <a href="getArticle.do">${article.title}</a>
                        </div>
                        <div class="hm-index-con">${article.content}</div>
                        <div class="hm-index-info l">
                            <span class="article-username">${article.senderName}</span>
                            <span class="post-time">${article.sendTime}</span>
                        </div>
                        <div class="hm-index-fun r">
                            <span class="icon-like"><i></i>${article.upvoteCount}</span>
                            <span class="icon-talk"><i></i>${article.replyCount}</span>
                        </div>
                    </li>

                    </c:forEach>

                </ul>
                <div class="clearfix ding">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            总共${pageInfo.pages} 页，共${pageInfo.total} 条数据。 每页
                            <select class="form-control" id="changePageSize" onchange="changePageSize()">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select> 条
                        </div>
                    </div>

                    <div class="box-tools pull-right">
                        <ul class="pagination">

                                <a href="${pageContext.request.contextPath}/article/findAll.do?page=1&size=${pageInfo.pageSize}" aria-label="Previous">首页</a>

                            <a href="${pageContext.request.contextPath}/article/findAll.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">

                                <a href="${pageContext.request.contextPath}/article/findAll.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>

                            </c:forEach>

                            <a href="${pageContext.request.contextPath}/article/findAll.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a>
                                <a href="${pageContext.request.contextPath}/article/findAll.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}" aria-label="Next">尾页</a>
                        </ul>
                    </div>

                </div>

            </div>





            <!-- 右侧侧边栏,在线用户 -->
            <div class="aside l">
                <div class="aside-box">
                    <h3 class="t">
                        <a href="javascript:;">在线用户(2)</a>
                    </h3>
                    <ul class="b clearfix">
                        <li>
                            <div><img src="../images/default.png" height="55"/> </div>
                            <p>Mr.King</p>
                        </li>
                        <li>
                            <div><img src="../images/default.png" height="55"/></div>
                            <p>疯子</p>
                        </li>
                    </ul>
                </div>
            </div>



        </div>
    </div>
</div>


<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>



<!-- 右边发帖，回顶部 -->
<div class="fixedBar" id="j_fixedBar">
    <a id="newTopicBtn" href="javascript:;" class="newTopic"><span></span>发帖</a>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>

<!-- 发帖弹出框 -->
<form action="article/save.do" method="post">
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">主题帖</h4><span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_t">
                    <input type="text" id="title" name="title" placeholder="帖子标题"/>
                </div>
                <div class="win_bd_b">
                    <textarea id="content" name="content" placeholder="正文"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="submit" class="btn" value="发表" onclick="test()"/>
                </div>
            </div>
        </div>
    </div>
</form>


</body>

<script>
  /*  $(function () {
        location.href="${pageContext.request.contextPath}/article/findTodayArticle.do";
    });*/

    function changePageSize() {
        //获取下拉框的值
        var pageSize = $("#changePageSize").val();

        //向服务器发送请求，改变没页显示条数
        location.href = "${pageContext.request.contextPath}/article/findAll.do?page=1&size="
            + pageSize;
    }
</script>
</html>