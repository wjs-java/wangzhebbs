<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>帖信息管理页面</title>

</head>
<style type="text/css">
    html, body {
        overflow: auto;
        height: 100%;
    }

    .line-limit-length {
        max-width: 220px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }



</style>

<script>


    function changePageSize() {
        //获取下拉框的值
        var pageSize = $("#changePageSize").val();
        //向服务器发送请求，改变每页显示条数
        location.href = "${pageContext.request.contextPath}/manage/findAll.do?page=1&size="+pageSize;
    }

</script>
<body>
<div class="hrms_dept_container">
    <!-- 导航栏-->
    <%@ include file="commom/head.jsp" %>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div class="hrms_dept_body">
        <!-- 左侧栏 -->
        <%@ include file="commom/leftsidebar.jsp" %>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">
            <div class="panel panel-success">
                <!-- 路径导航 -->
                <div>
                    <ol class="breadcrumb">
                        <li><a href="#">用户帖管理</a></li>
                        <li class="active">帖子信息</li>
                    </ol>
                </div>
                <hr>
                <!-- Table -->
                <div>
                    <div style="float: left">
                        <form action="${pageContext.request.contextPath}/manage/findByLike.do" method="post" id="articleSearchForm">
                            <table>
                                <tr>
                                    <th>
                                        <label for="title" class="control-label">标题:</label>
                                    </th>
                                    <th>
                                        <input type="text" id="title" class="form-control"
                                               name="title" value="">
                                        <input type="hidden" id="pageNum" name="pn" value="">
                                    </th>
                                    <th>
                                        <label for="article_sendername" class="control-label">创帖人:</label>
                                    </th>
                                    <th>
                                        <input type="text" id="article_sendername" class="form-control"
                                               name="sendername" value="">
                                    </th>
                                    <th colspan="2">
                                        <input type="submit" value="查询" class="form-control btn-primary">
                                    </th>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
                <div style="clear:both"></div>
                <hr>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>标题</th>
                        <th>内容</th>
                        <th>创帖人</th>
                        <th>是否置顶</th>
                        <th>回复数</th>
                        <th>点赞数</th>
                        <th>浏览数</th>
                        <th>所在交流区</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="article">
                        <tr>
                            <td width="15%">${article.title}</td>
                            <td width="30%" class="line-limit-length">${article.content}</td>
                            <td width="5%" class="line-limit-length">${article.senderName}</td>
                            <td width="5%" class="line-limit-length">${article.isTop}</td>
                            <td width="5%">${article.replyCount}</td>
                            <td width="5%">${article.upvoteCount}</td>
                            <td width="5%">${article.browseCount}</td>
                            <td width="15%">${article.zoneId}</td>
                            <td width="15%">
                                <a href="${pageContext.request.contextPath}/manage/deleteArticle.do?id=${article.articleId}&pn=${articleMsgs.pageNum}&title=${articleSearch.title}&sendername=${articleSearch.sendername}"
                                   role="button" class="btn btn-primary">屏蔽</a>
                                <c:if test="${article.isTop==0}">
                                    <a href="${pageContext.request.contextPath}/manage/topOrNo.do?id=${article.articleId}&istop=1&pn=${articleMsgs.pageNum}&title=${articleSearch.title}&sendername=${articleSearch.sendername}"
                                       role="button" class="btn btn-danger">置顶</a>
                                </c:if>
                                <c:if test="${article.isTop==1}">
                                    <a href="${pageContext.request.contextPath}/manage/topOrNo.do?id=${article.articleId}&istop=0&pn=${articleMsgs.pageNum}&title=${articleSearch.title}&sendername=${articleSearch.sendername}"
                                       role="button" class="btn btn-info">取消</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div><!-- /.hrms_dept_body -->

            <%--分页导航栏--%>
            <div class="box-footer">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        总共${pageInfo.pages}页,共${pageInfo.total}条数据。每页
                        <select class="form-control" style="height: 25px;width: 15px" id="changePageSize" onchange="changePageSize()">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>条
                    </div>
                </div>

                <div class="box-tools pull-right">
                    <ul class="pagination">
                        <li>
                            <a href="${pageContext.request.contextPath}/manage/findAll.do?page=1&size=${pageInfo.pageSize}"
                               aria-label="Previous">首页</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/manage/findAll.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a>
                        </li>
                        <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                            <li>
                                <a href="${pageContext.request.contextPath}/manage/findAll.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
                            </li>
                        </c:forEach>
                        <li>
                            <a href="${pageContext.request.contextPath}/manage/findAll.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/manage/findAll.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}"
                               aria-label="Next">尾页</a>
                        </li>
                    </ul>
                </div>

            </div>
            <!-- /.box-footer-->


            <!-- 尾部信息-->
            <%@ include file="commom/foot.jsp" %>

        </div><!-- /.hrms_dept_container -->
        <!-- .box-footer-->


        <%--<%@ include file="ArticleAdd.jsp"%>--%>
        <%@ include file="ArticleUpdate.jsp" %>
</body>
</html>
