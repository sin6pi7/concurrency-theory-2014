var tab = [];

for (var i = 0; i < 10; i++) {

	// sol1	
	tab.push( function(i) {
		return function () {console.log(i);}
	}(i));

	// sol2
	tab.push(console.log.bind(null,i));
};

tab[0]();
tab[1]();
tab[2]();