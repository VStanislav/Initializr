
angular.module('app',[]).controller('indexController',function ($scope,$http){
    const contextPath = 'http://localhost:9000/app';

    $scope.loadProducts=function (){
        $http.get(contextPath+'/products')
            .then(function (response){
                $scope.ProductList = response.data;
            });
    };

    $scope.deleteProduct = function (studentId){
        $http.get(contextPath+'/products/delete/'+studentId)
            .then(function (response){
                $scope.loadProducts();
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
            $scope.loadProducts();
        });
    }
});