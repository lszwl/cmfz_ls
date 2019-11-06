<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--当前页面更好支持移动端-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!--bootstrapcss样式-->
    <link rel="stylesheet" href="../statics/boot/css/bootstrap.min.css">
    <!--引入jquery核心js-->
    <script src="../statics/boot/js/jquery-3.3.1.min.js"></script>
    <!--引入bootstrap核心js-->
    <script src="../statics/boot/js/bootstrap.min.js"></script>
    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="../statics/jqgrid/css/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <link rel="stylesheet" href="../statics/jqgrid/css/ui.jqgrid.css">

    <script src="../statics/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="../statics/jqgrid/js/jquery.jqGrid.min.js"></script>
    <script src="../statics/jqgrid/js/ajaxfileupload.js"></script>

    <script charset="utf-8" src="./kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="./kindeditor/lang/zh-CN.js"></script>
    <script>
        $(function () {

        })

    </script>
</head>
<body>
<!--导航条-->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!--导航标题-->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持明法洲后台管理系统 <small>v1.0</small></a>
        </div>

        <!--导航条内容-->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:<font color="aqua">${admin.adminname}</font></a></li>
                <li><a href="${pageContext.request.contextPath}/admin/exit">退出登录 <span class="glyphicon glyphicon-log-out"></span> </a></li>
            </ul>
        </div>
    </div>
</nav>
<!--页面主体内容-->
<div class="container-fluid">
    <!--row-->
    <div class="row">

        <!--菜单-->
        <div class="col-sm-2">

            <!--手风琴控件-->
            <div class="panel-group" id="accordion" >

                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="userPanel">
                        <h4 class="panel-title"  style="text-align: center">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#userLists" aria-expanded="true" aria-controls="collapseOne">
                               轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="userLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item" style="text-align: center"><a href="javascript:$('#centerLayout').load('./carousel/carousel-list.jsp');" id="btn">所有轮播图</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--类别面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="categoryPanel">
                        <h4 class="panel-title"  style="text-align: center">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#categoryLists" aria-expanded="true" aria-controls="collapseOne">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="categoryLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item" style="text-align: center"><a href="javascript:$('#centerLayout').load('./album/album_list.jsp')">类别列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="bookPanel">
                        <h4 class="panel-title" style="text-align: center">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#bookLists" aria-expanded="true" aria-controls="collapseOne">
                               文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="bookLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item" style="text-align: center"><a href="javascript:$('#centerLayout').load('./article/article_list.jsp')">图书列表</a></li>
                                <li class="list-group-item" style="text-align: center"><a href="">图书添加</a></li>
                            </ul>
                        </div>
                    </div>
                </div>


                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="orderPanel">
                        <h4 class="panel-title" style="text-align: center">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#orderLists" aria-expanded="true" aria-controls="collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="orderLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item" style="text-align: center"><a href="">订单列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="starPanel">
                        <h4 class="panel-title" style="text-align: center">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#starLists" aria-expanded="true" aria-controls="collapseOne">
                                明星管理
                            </a>
                        </h4>
                    </div>
                    <div id="starLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item" style="text-align: center"><a href="javascript:$('#centerLayout').load('./star/star_list.jsp');" >明星列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--中心布局-->
        <div class="col-sm-10" id="centerLayout" style="margin-bottom: 20px">

            <!--巨幕-->
            <div class="jumbotron">
                <p>欢迎来到持明法洲后台管理系统!</p>
            </div>
           <%-- <div>
                <img src="image/11.jpg" width="100%">
            </div>--%>
           <%-- <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="image/121.jpg" alt="...">
                        <div class="carousel-caption">
                            sss
                        </div>
                    </div>
                    <div class="item ">
                        <img src="image/120.jpg" alt="...">
                        <div class="carousel-caption">
                           sss
                        </div>
                    </div>
                    <div class="item">
                        <img src="image/11.jpg" alt="..." width="1548" height="880">
                        <div class="carousel-caption">
                            sss
                        </div>
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev" id="leftButton">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next" id="rightButton">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>--%>
            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item ">
                        <img src="image/121.jpg" alt="First slide">
                    </div>
                    <div class="item active">
                        <img src="image/120.jpg" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="image/11.jpg" alt="Third slide">
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

        </div>
    </div>
</div>
</body>
</html>