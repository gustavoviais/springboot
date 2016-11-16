//criação do módulo principal da aplicação
var app = angular.module("app", []);

app.controller("indexController", function($scope, $http){
	$scope.nome = "João";
	$scope.sobrenome = " da Silva";
	
	//criar requisição para API
	$http({method:'GET', url:'http://localhost:8080/clientes'})
	.then(function (response){
		console.log(response.data);
		$scope.clientes = response.data;
	}, function(response){
		console.log(response.data);
	});
	//primeira função é de sucesso e a segunda de erro
});