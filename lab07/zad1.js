function printAsync(s, cb) {
   var delay = Math.floor((Math.random()*1000)+500);
   setTimeout(function() {
       console.log(s);
       if (cb) cb();
   }, delay);
}

function nTimes (n) {

	if (n > 0) {
		print123( function() {nTimes (n-1);});
	}
}

function print123(callback) {
	printAsync("1", function () {
		printAsync("2", function () {
			printAsync("3", callback);
		});
	});
}

// Asynch solution
// for (var i = 0; i < 5; i++) {
// 	printAsync("1", function () {
// 		printAsync("2", function () {
// 			printAsync("3");
// 		});
// 	});
// };

// Synch solution (recursion)
// nTimes(5);

// Synch solution (waterfall)
var waterfall = require('async-waterfall');
var tasks = [];
for (var i = 0; i < 5; i++) {
	tasks.push(
		function (callback) {
			print123 (function () {callback(null);});
	});
};
waterfall(tasks);