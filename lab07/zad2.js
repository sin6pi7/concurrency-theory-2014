var file = require("file");
var fs = require("fs");

var filesCount = 0;

// Asynch
// start = Date.now();
// file.walk("./PAM08", countLinesInFilesAsync);
// process.on('exit', function () {
// 	console.log("Liczba linii: " + filesCount + ", czas: " + (Date.now() - start)/1000 + "s");
// });exi

// Synch
start = Date.now();
file.walkSync("./PAM08", countLinesInFilesSync);
process.on('exit', function () {
	console.log("Liczba linii: " + filesCount + ", czas: " + (Date.now() - start)/1000 + "s");
});

function countLinesInFilesAsync (o, dirPath, dirs, files) {

	for (var i = 0; i < files.length; i++) {
		file = files[i];
		countLines(files[i]);
	};	
}

function countLinesInFilesSync (dirPath, dirs, files) {

	for (var i = 0; i < files.length; i++) {
		countLines(dirPath + '/' + files[i]);
	};	
}

function countLines (file) {

	var count = 0;

	fs.createReadStream(file).on('data', function(chunk) {
	                count += chunk.toString('utf8')
	                .split(/\r\n|[\n\r\u0085\u2028\u2029]/g)
	                .length-1;
	            }).on('end', function() {
	                filesCount += count;
	                console.log(file, count);
	            }).on('error', function(err) {
	                console.error(err);
	            });
}