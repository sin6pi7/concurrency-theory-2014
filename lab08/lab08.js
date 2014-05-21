// Teoria Współbieżnośi, implementacja problemu 5 filozofów w node.js
// Opis problemu: http://en.wikipedia.org/wiki/Dining_philosophers_problem
// 1. Dokończ implementację funkcji podnoszenia widelca (Fork.acquire).
// 2. Zaimplementuj "naiwny" algorytm (każdy filozof podnosi najpierw lewy, potem
//    prawy widelec, itd.).
// 3. Zaimplementuj rozwiązanie asymetryczne: filozofowie z nieparzystym numerem
//    najpierw podnoszą widelec lewy, z parzystym -- prawy. 
// 4. Zaimplementuj rozwiązanie z kelnerem (opisane jako "Conductor solution" 
//    w wikipedii).
// 5. Uruchom eksperymenty dla różnej liczby filozofów i dla każdego wariantu
//    implementacji zmierz całkowity czas oczekiwania każdego filozofa. 
//    Wyniki przedstaw na wykresach.

var async = require('async');

var Fork = function() {
    this.state = 0;
    return this;
}

Fork.prototype.acquire = function(cb) { 

    // zaimplementuj funkcję acquire, tak by korzystala z algorytmu BEB
    // (http://pl.wikipedia.org/wiki/Binary_Exponential_Backoff), tzn:
    // 1. przed pierwszą próbą podniesienia widelca Filozof odczekuje 1ms
    // 2. gdy próba jest nieudana, zwiększa czas oczekiwania dwukrotnie
    //    i ponawia próbę, itd.
    
    var that = this;

    var tryAcquire = function (callback, time) {
        setTimeout(function () {
            if (that.state === 0) {
                that.state = 1;
                callback();
            } else {
                tryAcquire(callback, 2*time);
            }
        }, 1);
    };

    tryAcquire(cb, 1);
}

Fork.prototype.release = function() { 
    this.state = 0; 
}

var Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.forkLeft = forks[id % forks.length];
    this.forkRight = forks[(id+1) % forks.length];
    return this;
}

Philosopher.prototype.startNaive = function(count) {
    // zaimplementuj rozwiązanie naiwne
    // każdy filozof powinien 'count' razy wykonywać cykl
    // podnoszenia widelców -- jedzenia -- zwalniania widelców

    var forks = this.forks;
    var forkLeft = this.forkLeft;
    var forkRight = this.forkRight;
    var id = this.id;

    var tasks = [];
    for (var i = 0; i < count; i++) {
        tasks.push(
            function (callback) {
                forkLeft.acquire(
                    function () {
                        console.log("Philosopher " + id + " acquired left fork ");
                        forkRight.acquire(
                            function () {
                                console.log("Philosopher " + id + " acquired right fork ");    
                                setTimeout(function () {
                                    forkLeft.release();
                                    forkRight.release();
                                    console.log("Philosopher " + id + " released forks");    
                                    callback();
                                }, 100);
                            }
                        );
                    }
            );
        });
    }

    async.waterfall(tasks, function (error, results) {
        console.log("Philosopher " + id + " finished " + count + " tasks");
    });
}

Philosopher.prototype.startAsym = function(count) {
    var forks = this.forks;
    var forkLeft = this.forkLeft;
    var forkRight = this.forkRight;
    var id = this.id;

    var tasks = [];
    
    if (id % 2 === 0)   {

        for (var i = 0; i < count; i++) {
                tasks.push(
                    function (callback) {
                        
                        forkLeft.acquire(
                            function () {
                                console.log("Philosopher " + id + " acquired left fork with id " + forks.indexOf(forkLeft));
                                
                                forkRight.acquire(
                                    function () {
                                        
                                        console.log("Philosopher " + id + " acquired right fork with id " + forks.indexOf(forkRight));    
                                        setTimeout(function () {
                                            forkLeft.release();
                                            forkRight.release();
                                            console.log("Philosopher " + id + " released forks");    
                                            callback();
                                        }, 100);
                                    }
                                );
                            }
                    );
                });
            }
    } else {

        for (var i = 0; i < count; i++) {
            tasks.push(
                function (callback) {

                    forkRight.acquire(
                        function () {
                            console.log("Philosopher " + id + " acquired right fork with id " + forks.indexOf(forkRight));          

                            forkLeft.acquire(
                                function () {

                                    console.log("Philosopher " + id + " acquired left fork with id " + forks.indexOf(forkLeft));          
                                    setTimeout(function () {
                                        forkLeft.release();
                                        forkRight.release();
                                        console.log("Philosopher " + id + " released forks");    
                                        callback();
                                    }, 100);
                                }
                            );
                        }
                );
            });
        }
    }

    async.waterfall(tasks, function (error, results) {
        console.log("Philosopher " + id + " finished " + count + " tasks");
    });  

    // zaimplementuj rozwiązanie asymetryczne
    // każdy filozof powinien 'count' razy wykonywać cykl
    // podnoszenia widelców -- jedzenia -- zwalniania widelców
}

var Conductor = function () {};

Conductor.prototype.acquireForks = function (forkLeft, forkRight, cb) {

    var tryAcquire = function (callback, time) {
        setTimeout(function () {
            if (forkLeft.state === 0 && forkRight.state === 0) {
                forkLeft.state = forkRight.state = 1;
                callback();
            } else {
                tryAcquire(callback, 2*time);
            }
        }, 1);
    };

    tryAcquire(cb, 1);  
}

Philosopher.prototype.startConductor = function(conductor, count) {
    var forks = this.forks;
    var forkLeft = this.forkLeft;
    var forkRight = this.forkRight;
    var id = this.id;

    var tasks = [];    
    for (var i = 0; i < count; i++) {
            tasks.push(
                function (callback) {
                    conductor.acquireForks(forkLeft, forkRight,
                        function () {
                                    console.log("Philosopher " + id + " acquired both forks");          
                                    setTimeout(function () {
                                        forkLeft.release();
                                        forkRight.release();
                                        console.log("Philosopher " + id + " released forks");    
                                        callback();
                                    }, 100);
                                }
                            );
                        }
            );
    }

    async.waterfall(tasks, function (error, results) {
        console.log("Philosopher " + id + " finished " + count + " tasks");
    });  

    // zaimplementuj rozwiązanie z kelnerem
    // każdy filozof powinien 'count' razy wykonywać cykl
    // podnoszenia widelców -- jedzenia -- zwalniania widelców
}


var N = 5;
var forks = [];
var philosophers = [];
var conductor = new Conductor();
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    // philosophers[i].startNaive(10);
    // philosophers[i].startAsym(10);
    philosophers[i].startConductor(conductor, 10);
}