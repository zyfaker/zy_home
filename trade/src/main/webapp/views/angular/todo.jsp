<%--
  Created by IntelliJ IDEA.
  User: zhangyuan33
  Date: 2018/8/2
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" ng-app="todoApp">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../../third/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="../../third/bootstrap/css/bootstrap-theme.css">
    <script src="../../third/angular/angular-1.6.9/angular.js"></script>
    <script>
        <!--定义数据 -->
        var model = {
            user: "张元先生",
            items:
                [{action: "买花", done: false},
                    {action: "买礼物", done: false},
                    {action: "订票", done: true},
                    {action: "打电话", done: false}]
        };
        <!--绑定界面 -->
        var todoApp = angular.module("todoApp", []);

        <!--绑定过滤器-->
        todoApp.filter("checkedItems", function () {
            return function (items, showComplete) {
                var resultArr = [];
                angular.forEach(items, function (item) {
                    if (item.done == false || showComplete == true) {
                        resultArr.push(item);
                    }
                });
                return resultArr;

            }
        });
        <!--绑定控制部分-->
        todoApp.controller("ToDoCtrl", function ($scope) {

                <!--绑定model-->
                $scope.todo = model;
                <!--统计未完成数量-->
                $scope.inCompleteCount = function () {
                    var count = 0;
                    angular.forEach($scope.todo.items, function (item) {
                        if (!item.done) {
                            count++
                        }
                    });
                    return count;
                }
                <!--统计完成数量-->
                $scope.CompleteCount = function () {
                    return $scope.todo.items.length - $scope.inCompleteCount();
                }
                <!--未完成预警-->
                $scope.warningLevel = function () {
                    return $scope.inCompleteCount() < 3 ? "label-success" : "label-warning";
                }
                <!--完成展示-->
                $scope.successLevel = function () {
                    return $scope.CompleteCount() > 3 ? "label-success" : "label-info";
                }

                <!--添加新条目-->
                $scope.addNewItem = function (actionText) {
                    $scope.todo.items.push({action: actionText, done: false});

                }
            }
        );
    </script>
</head>
<body ng-controller="ToDoCtrl">

<div class="page-header">
    <h2>
        {{todo.user}} 的相关事务     已完成：
        <span class="label label-default" ng-class="successLevel()" ng-hide="CompleteCount()==0">
            {{CompleteCount()}}
        </span>&nbsp;待完成：
        <span class="label label-default" ng-class="warningLevel()" ng-hide="inCompleteCount()==0">
            {{inCompleteCount()}}
        </span>
    </h2>
</div>
<div class="panel">
    <div class="input-group">
        <input class="form-control" ng-model="actionText"/>
        <span class="input-group-btn">
            <button class="btn btn-default" ng-click="addNewItem(actionText)">
                添加新事务
            </button>
        </span>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>待办事务</th>
            <th>完成状态</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="item in todo.items|checkedItems:showComplete|orderBy:'action'">
            <td>{{item.action}}</td>
            <td><input type="checkbox" ng-model="item.done"/></td>
        </tr>
        </tbody>
    </table>
    <div class="checkbox-inline">
        <label>
            <input type="checkbox" ng-model="showComplete"/>展示所有
        </label>
    </div>
</div>


</body>
</html>
