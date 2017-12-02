app.controller('MainController', ['$scope', function($scope) {
  $scope.title = 'My favorite books'
 // $scope.product = {name: 'The Book of Trees',
 //                   price: 19,
 //                     pubdate: new Date('2014', '03', '08')}
  $scope.products = [{
    name: 'The Book of Trees',
    price: 19,
    pubdate: new Date('2014', '03', '08'),
    cover: 'img/hp and the chamber of secrets.jpeg'
  },
  {
    name: 'Program or be Programmed',
    price: 8,
    pubdate: new Date('2013', '08', '01'),
    cover: 'img/Harry_Potter_and_the_Sorcerers_Stone.jpg'
  }]

  $scope.promo = 'The most popular books this month.';
}]);
