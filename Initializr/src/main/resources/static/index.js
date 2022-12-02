
angular.module('app',[]).controller('indexController',function ($scope,$http){
    const contextPath = 'http://localhost:9000/app';

    $scope.loadProducts= function (){
        $http.get( contextPath + '/products')
            .then(function (response){
                $scope.ProductList = response.data;
            });
    }
    $scope.loadProducts();

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

    $scope.findPrice = function (){
        $http({
            url: contextPath + '/products/find',
            method: 'GET',
            params: {
                min: $scope.betweenPrice.minn,
                max: $scope.betweenPrice.maxx
            }
        }).then(function (response){
            $scope.ProductList = response.data;

            // Вот тут я не разобрался до конца : $scope.betweenPrice = null;
            // если эта строка работает, то после получения 1го ответа на запрос, значения в полях-инпутах стираются, и кнопка Find не функционирует
            // видимо в ожидании какого-то ввода и т.е. сама функиця $scope.findPrice не работает.
            // при этом не обновляются данные при нажатии на кнопки изменения цены и удаления продукта ,
            // т.к. они завязаны на это самой функции (обновление содержимого таблины)
            // ---
            // а если эту строку закоментировать(т.е. она не отработает), то все работает, НО!!!
            // значения, введеные ранее в инпутах, сохраняются, и даже если обнулить их там кнопкой Clear(видимо обнуляется просто форма инпут),
            //  и нажать снова на Find  будет произведена сортировка по значениям посл.ввода.
            //  как это обойти я не понимаю.


            // $scope.betweenPrice = null;
        });
    }
});