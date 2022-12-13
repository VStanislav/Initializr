
angular.module('app',[]).controller('indexController',function ($scope,$http){
    const contextPath = 'http://localhost:9000/app/api/v1';


    $scope.findPrice = function (pageIndex = 1){
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                name: $scope.betweenPrice ? $scope.betweenPrice.name : null,
                min_price: $scope.betweenPrice ? $scope.betweenPrice.min_price : null,
                max_price: $scope.betweenPrice ? $scope.betweenPrice.max_price : null
            }
        }).then(function (response){
            $scope.ProductList = response.data;
        });
    }

    $scope.clear = function (){
        $scope.betweenPrice = null;
    };

    $scope.showOrder = function (){
        $http.get(contextPath+'/products/order/show')
            .then(function (response){
            $scope.OrderList = response.data;
        });
    }

    $scope.addIntoOrder = function (productId){
    $http.get(contextPath+'/products/order/add/'+productId)
        .then(function (response){
            $scope.showOrder();
        });
    }

    $scope.deleteFromOrder = function (productId){
    $http.get(contextPath+'/products/order/remove/'+productId)
        .then(function (response){
            alert("Noooo");
            $scope.showOrder();
        });
    }

    $scope.findPrice();
    $scope.showOrder();
});


//Напоминалки:

// $scope.changeScore = function (studentId,delta){
//     $http({
//         url: contextPath + '/products/change',
//         method: 'GET',
//         params: {
//             studentId: studentId,
//             delta: delta
//         }
//     }).then(function (response){
//         $scope.findPrice();
//     });
// }

// $scope.createProduct = function (){
//     $http.post( contextPath + '/products/new',$scope.newProduct)
//         .then(function (response){
//             $scope.newProduct=null;
//             $scope.findPrice();
//         });
// }

//