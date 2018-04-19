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

div(X,Y,Z) :- Z is X/Y.

iseven(0).
iseven(N) :- N < 0, N1 is -N, iseven(N1).
iseven(N) :- iseveni(N,2,0).
iseven(N) :- iseveni(N,2,2).
iseveni(N,I,Y) :- I =< N, I2 is I*2, N < I2, Y is N-I.
iseveni(N,I,Y) :- I =< N, I2 is I*2, iseveni(N,I2,Y1), Y1 < I2, Y is Y1.
iseveni(N,I,Y) :- I =< N, I2 is I*2, iseveni(N,I2,Y1), Y is Y1 - I2.

mcd(_,0,0).
mcd(A,B,C) :- mcd2(A,B,C).

mcd2(_,1,1).
mcd2(A,B,C) :- A < B, mcd(B,A,C).
mcd2(A,B,C) :- R is A mod B, mcd(B,R,C).


invertir(L,F) :- invertir2(L,[],F).
invertir2([],L,L).
invertir2([H|T],L,F) :- invertir2(T,[H|L],F).

max(A,B,A) :- A >= B.
max(A,B,B) :- A < B.
maxl([H],H).
maxl([H|T],M):- maxl(T,M1), max(M1,H,M).

lista([],[]).
lista([_|T],[0|I]) :- lista(T,I).
