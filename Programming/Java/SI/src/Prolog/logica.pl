padre(jose,jesus).
padre(jesus,tomas).
padre(tomas,alejandro).
padre(tomas,alvaro).
padre(alvaro,tom).
padre(alvaro,jerry).
padre(jeronimo,gustavo).
padre(tom,sergio).
padre(jesus,nicolas).

hijo(X,Y) :- padre(Y,X).
abuelo(X,Y) :- padre(X,Z), padre(Z,Y). 
hermano(X,Y) :- padre(Z,X), padre(Z,Y), X \== Y.
tio(X,Y) :- padre(Z,Y), hermano(Z,X).

fact(0,1).
fact(X, Y) :- X1 is X-1, fact(X1,Y1), Y is X * Y1.

fib(0,0).
fib(1,1).
fib(N,M) :- fiboi(1,1,3,N,M).

fiboi(B,C,I,N,M) :- I =< N, BC is B + C, I1 is I+1, fiboi(C,BC,I1,N,M).
fiboi(_,C,I,N,C) :- I > N.






