
angular.module('app',[]).controller('indexController',function ($scope,$http){
    const contextPath = 'http://localhost:9000/app/api/v1';

    $scope.deleteProduct = function (studentId){
        $http.get(contextPath+'/products/delete/'+studentId)
            .then(function (response){
                $scope.findPrice();
            });
    }

    $scope.changeScore = function (studentId,delta){
        $http({
            url: contextPath + '/products/change',
            method: 'GET',
            params: {
                studentId: studentId,
                delta: delta
            }
        }).then(function (response){
            $scope.findPrice();
        });
    }

    $scope.createProduct = function (){
        $http.post( contextPath + '/products/new',$scope.newProduct)
            .then(function (response){
                $scope.newProduct=null;
                $scope.findPrice();
            });
    }

    $scope.findPrice = function (pageIndex = 1){
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                name: $scope.betweenPrice ? $scope.betweenPrice.name : null,
                min_price: $scope.betweenPrice ? $scope.betweenPrice.min_price : null,
                max_price: $scope.betweenPrice ? $scope.betweenPrice.max_price : null,
                diler: $scope.betweenPrice ? $scope.betweenPrice.diler : null
            }
        }).then(function (response){
            $scope.ProductList = response.data.content;
        });
    }

    $scope.clear = function (){
        $scope.betweenPrice = null;
        };


    $scope.findPrice();
});