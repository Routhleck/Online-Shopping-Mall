<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>购物+</title>
    <link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${cp}/css/style.css" rel="stylesheet">

    <script src="${cp}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${cp}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${cp}/js/layer.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
    <script src="${cp}/js/html5shiv.min.js"></script>
    <script src="${cp}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!--导航栏部分-->
<jsp:include page="include/header.jsp"/>

<!-- 中间内容 -->
<div class="container-fluid bigHead">
    <div class="row">
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <div class="jumbotron">
                <h1>欢迎来到订单页</h1>
                <p>您的购买清单为</p>
            </div>
        </div>
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <div class="row">
                <ul class="nav nav-tabs list-group-diy" role="tablist">
                    <li role="presentation" class="active list-group-item-diy"><a href="#unHandle" aria-controls="unHandle" role="tab" data-toggle="tab">待发货订单&nbsp;<span class="badge" id="unHandleCount">0</span></a></li>
                    <li role="presentation" class="list-group-item-diy"><a href="#transport" aria-controls="transport" role="tab" data-toggle="tab">运输中订单&nbsp;<span class="badge" id="transportCount">0</span></a></li>
                    <li role="presentation" class="list-group-item-diy"><a href="#receive" aria-controls="receive" role="tab" data-toggle="tab">已收货订单&nbsp;<span class="badge" id="receiveCount">0</span></a></li>
                    <li role="presentation" class="list-group-item-diy"><a href="#all" aria-controls="all" role="tab" data-toggle="tab">全部订单&nbsp;<span class="badge" id="allCount">0</span></a></li>
                </ul>

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="unHandle">
                        <table class="table table-hover center" id="unHandleTable">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="transport">
                        <table class="table table-hover center" id="transportTable">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="receive">
                        <table class="table table-hover center" id="receiveTable">
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="all">
                        <table class="table table-hover center" id="allTable">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 尾部 -->
<jsp:include page="include/foot.jsp"/>

<script type="text/javascript">
    var loading = layer.load(0);
    updateShoppingRecords();

    function updateShoppingRecords() {
        var orderArray = new Array;
        orderArray[0] = "未发货";
        orderArray[1] = "配送中";
        orderArray[2] = "已收货";
        var unHandleTable = document.getElementById("unHandleTable");
        var transportTable = document.getElementById("transportTable");
        var receiveTable = document.getElementById("receiveTable");
        var allTable = document.getElementById("allTable");

        var unHandleCount = document.getElementById("unHandleCount");
        var transportCount = document.getElementById("transportCount");
        var receiveCount = document.getElementById("receiveCount");
        var allCount = document.getElementById("allCount");

        var unHandleCounts = parseInt(unHandleCount.innerHTML);
        var transportCounts = parseInt(transportCount.innerHTML);
        var receiveCounts = parseInt(receiveCount.innerHTML);
        var allCounts = parseInt(allCount.innerHTML);

        var allShoppingRecords = getShoppingRecords();
        unHandleTable.innerHTML = "";
        transportTable.innerHTML = "";
        receiveTable.innerHTML = "";
        allTable.innerHTML = "";
        var unHandleHTML = '<tr>'+
            '<th>商品名称</th>'+
            '<th>购买数量</th>'+
            '<th>付款金额</th>'+
            '<th>订单状态</th>'+
            '</tr>';
        var transportHTML = '<tr>'+
            '<th>商品名称</th>'+
            '<th>购买数量</th>'+
            '<th>付款金额</th>'+
            '<th>送货地址</th>'+
            '<th>联系电话</th>'+
            '<th>订单状态</th>'+
            '<th>确认收货</th>'+
            '</tr>';
        var receiveHTML = '<tr>'+
            '<th>商品名称</th>'+
            '<th>购买数量</th>'+
            '<th>付款金额</th>'+
            '<th>订单状态</th>'+
            '<th>评价</th>'+
            '</tr>';
        var allHTML = '<tr>'+
            '<th>商品名称</th>'+
            '<th>购买数量</th>'+
            '<th>付款金额</th>'+
            '<th>订单状态</th>'+
            '</tr>';
        var unHandleHTMLTemp = "";
        var transportHTMLTemp = "";
        var receiveHTMLTemp = "";
        var allHTMLTemp = "";
        for(var i=0;i<allShoppingRecords.length;i++){

            var product = getProductById(allShoppingRecords[i].productId);
            //var id=allShoppingRecords[i].userId;

                allHTMLTemp += '<tr>' +
                    '<td>' + product.name + '</td>' +
                    '<td>' + allShoppingRecords[i].counts + '</td>' +
                    '<td>' + allShoppingRecords[i].productPrice + '</td>' +
                    '<td>' + orderArray[allShoppingRecords[i].orderStatus] + '</td>' +
                    '</tr>';
                allCounts++;
                if (allShoppingRecords[i].orderStatus == 0) {
                    unHandleHTMLTemp += '<tr>' +
                        '<td>' + product.name + '</td>' +
                        '<td>' + allShoppingRecords[i].counts + '</td>' +
                        '<td>' + allShoppingRecords[i].productPrice + '</td>' +
                        '<td>' + orderArray[allShoppingRecords[i].orderStatus] + '</td>' +
                        '</tr>';
                    unHandleCounts++;
                }
                else if (allShoppingRecords[i].orderStatus == 1) {
                    var address = getUserAddress(allShoppingRecords[i].userId);
                    var phoneNumber = getUserPhoneNumber(allShoppingRecords[i].userId)
                    transportHTMLTemp += '<tr>' +
                        '<td>' + product.name + '</td>' +
                        '<td>' + allShoppingRecords[i].counts + '</td>' +
                        '<td>' + allShoppingRecords[i].productPrice + '</td>' +
                        '<td>' + address + '</td>' +
                        '<td>' + phoneNumber + '</td>' +
                        '<td>' + orderArray[allShoppingRecords[i].orderStatus] + '</td>' +
                        '<td>' +
                        '<button class="btn btn-primary btn-sm" onclick="receiveProducts(' + allShoppingRecords[i].userId + ',' + allShoppingRecords[i].productId + ',\'' + allShoppingRecords[i].time + '\')">确认收货</button>' +
                        '</td>' +
                        '</tr>';
                    transportCounts++;
                }
                else if (allShoppingRecords[i].orderStatus == 2) {
                    receiveHTMLTemp += '<tr>' +
                        '<td>' + product.name + '</td>' +
                        '<td>' + allShoppingRecords[i].counts + '</td>' +
                        '<td>' + allShoppingRecords[i].productPrice + '</td>' +
                        '<td>' + orderArray[allShoppingRecords[i].orderStatus] + '</td>' +
                        '<td>' +
                        '<button class="btn btn-primary btn-sm" onclick="productDetail(' + allShoppingRecords[i].productId + ')">评价</button>' +
                        '</td>' +
                        '</tr>';
                    receiveCounts++;
                }

        }
        if(unHandleHTMLTemp == ""){
            unHandleHTML='<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2>没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            unHandleHTML+=unHandleHTMLTemp;
        if(transportHTMLTemp == ""){
            transportHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2>没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            transportHTML+=transportHTMLTemp;
        if(receiveHTMLTemp == ""){
            receiveHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2>没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            receiveHTML+=receiveHTMLTemp;
        if(allHTMLTemp == ""){
            allHTML = '<div class="row">'+
                '<div class="col-sm-3 col-md-3 col-lg-3"></div> '+
                '<div class="col-sm-6 col-md-6 col-lg-6">'+
                '<h2>没有相关订单</h2>'+
                '</div>'+
                '</div>';
        }
        else
            allHTML+=allHTMLTemp;

        unHandleCount.innerHTML = unHandleCounts;
        transportCount.innerHTML = transportCounts;
        receiveCount.innerHTML = receiveCounts;
        allCount.innerHTML = allCounts;

        unHandleTable.innerHTML += unHandleHTML;
        transportTable.innerHTML += transportHTML;
        receiveTable.innerHTML += receiveHTML;
        allTable.innerHTML += allHTML;
        layer.close(loading);
    }

    function getShoppingRecords() {
        judgeIsLogin();
        var shoppingRecordProducts = "";
        var user = {};
        user.userId = ${currentUser.id};
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/getShoppingRecords',
            data : user,
            dataType : 'json',
            success : function(result) {
                shoppingRecordProducts = result.result;
            },
            error : function(result) {
                layer.alert('getShoppingRecords查询错误');
            }
        });

        shoppingRecordProducts = eval("("+shoppingRecordProducts+")");

        return shoppingRecordProducts;
    }

    function getProductById(id) {
        var productResult = "";
        var product = {};
        product.id = id;

        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/getProductById',
            data : product,
            dataType : 'json',
            success : function(result) {
                productResult = result.result;
            },
            error : function(result) {
                layer.alert('getProductById查询错误');
            }
        });
        productResult = JSON.parse(productResult);
        return productResult;
    }

    function getUserAddress(id) {
        var address = "";
        var user = {};
        user.id = id;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/getUserAddressAndPhoneNumber',
            data : user,
            dataType : 'json',
            success : function(result) {
                address = result.address;
            },
            error : function(result) {
                layer.alert('getUserAddress查询错误');
            }
        });
        return address;
    }

    function getUserPhoneNumber(id) {
        var phoneNumber = "";
        var user = {};
        user.id = id;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/getUserAddressAndPhoneNumber',
            data : user,
            dataType : 'json',
            success : function(result) {
                phoneNumber = result.phoneNumber;
            },
            error : function(result) {
                layer.alert('getUserPhoneNumber查询错误');
            }
        });
        return phoneNumber;
    }

    function judgeIsLogin() {
        if("${currentUser.id}" == null || "${currentUser.id}" == undefined || "${currentUser.id}" ==""){
            window.location.href = "${cp}/login";
        }
    }
    function receiveProducts(userId,productId,time) {
        var receiveResult = "";
        var shoppingRecord = {};
        shoppingRecord.userId = userId;
        shoppingRecord.productId = productId;
        shoppingRecord.time = time;
        shoppingRecord.orderStatus = 2;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/changeShoppingRecord',
            data : shoppingRecord,
            dataType : 'json',
            success : function(result) {
                receiveResult = result.result;
            },
            error : function(result) {
                layer.alert('receiveProducts查询错误');
            }
        });
        if(receiveResult = "success")
            window.location.href = "${cp}/shopping_record";
    }

    function productDetail(id) {
        var product = {};
        var jumpResult = '';
        product.id = id;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/productDetail',
            data : product,
            dataType : 'json',
            success : function(result) {
                jumpResult = result.result;
            },
            error : function(resoult) {
                layer.alert('productDetail查询错误');
            }
        });

        if(jumpResult == "success"){
            window.location.href = "${cp}/product_detail";
        }
    }
</script>
</body>
</html>