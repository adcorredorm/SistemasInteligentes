%---Axiomas y predicados
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

%---Backtracking hacia atras
fact(0,1).
fact(X, Y) :- X1 is X-1, fact(X1,Y1), Y is X * Y1.

%---Backtracking hacia adelante
fib(0,0).
fib(1,1).
fib(N,M) :- fiboi(1,1,3,N,M).

fiboi(B,C,I,N,M) :- I =< N, BC is B + C, I1 is I+1, fiboi(C,BC,I1,N,M).
fiboi(_,C,I,N,C) :- I > N.

iseven(0).
iseven(N) :- N < 0, N1 is -N, iseven(N1).
iseven(N) :- iseveni(N,2,0).
iseven(N) :- iseveni(N,2,2).
iseveni(N,I,Y) :- I =< N, I2 is I*2, N < I2, Y is N-I.
iseveni(N,I,Y) :- I =< N, I2 is I*2, iseveni(N,I2,Y1), Y1 < I2, Y is Y1.
iseveni(N,I,Y) :- I =< N, I2 is I*2, iseveni(N,I2,Y1), Y is Y1 - I2.

%---Funciones utiles

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
maxl([H],H). %Maximo de una lista
maxl([H|T],M):- maxl(T,M1), max(M1,H,M).

longitud([], 0).
longitud([_|T], N) :- longitud(T, N1), N is N1 + 1.

contiene([H|_], H).
contiene([_|T], E) :- contiene(T,E).

eliminar(X,L,F) :- eliminar2(X,L,[],F).
%eliminar2(Elemento,Lista_sinExplorar,Lista_explorada,Final)
eliminar2(_,[],E,E).
eliminar2(X,[H|T],E,F) :- X \== H, append(E,[H],P), eliminar2(X,T,P,F).
eliminar2(X,[H|T],E,F) :- X == H, append(E,T,F).

quicksort([],[]).
quicksort([H|T], Q) :- separar(H, T, Men, May), quicksort(Men, M1), quicksort(May, M2), append(M1,[H|M2],Q).
%separar(Pivote,Lista,Menores,Mayores)
separar(_,[],[],[]).
separar(X,[A|B],[A|Men],May) :- A =< X, separar(X, B, Men, May).
separar(X,[A|B],Men,[A|May]) :- A > X, separar(X, B, Men, May).

%---Permutacion
permutar([],[]).
permutar([E],[[E]]).
permutar([H|T],PT) :- permutar(T,PT2), permutar2(H,PT2,[],PT).

permutar2(_,[],A,A).
permutar2(E, [[HH|HT]|T], A, PT) :- insertar(E,HH,W), append(HT,T,X), permutar2(E,X,[W|A],PT).
permutar2(E, [H|T], A, PT) :- insertar(E,H,W), permutar2(E,T,[W|A],PT).

%inserta el elemento H en cada posicion dentro de la lista X
insertar(H,X,Y) :- insertart(H,[],X,[],Y).
%insertart(Elemento,Izquierda,Derecha,Acumulado,Resultado)
insertart(H,L,[],A,[Z|A]) :- append(L,[H],Z).
insertart(H,L,[HR|TR],A,Y) :- append(L,[H|[HR|TR]],Z), append(L,[HR],NL), insertart(H,NL,TR,[Z|A],Y).


insCab(_,[],[]).
insCab(E, [H|T], [[E|H]|Z]) :- insCab(E,T,Z).


%---Arboles
altura([],0).
altura([I,_,D],A1) :- altura(I,AI), altura(D,AD), max(AI,AD,A), A1 is A + 1.

peso([],0).
peso([I,_,D],P) :- peso(I,PI), peso(D,PD), P is PI+PD+1.

ancho([],0).
ancho([[],_,[]],1).
ancho([[I,_,D]],A) :- ancho(I,AI), ancho(D,AD), A is AI+AD.

crear([],[]).
crear(L,A) :- crear2([],L,A).
crear2(Ac,[],Ac).
crear2(Ac,[H|T],A) :- insertar_a(Ac,H,R), crear2(R,T,A).

inorder(A,L) :- inorder2(A,L,[]).
inorder2([],L,L).
inorder2([I,R,D],L,A) :- inorder2(D,LD,A), inorder2(I,L,[R|LD]).

%insertar_a(Arbol,Elemento,Resultado)
insertar_a([],X,[[],X,[]]).
insertar_a([I,R,D],X,[AI,R,D]) :- X < R, insertar_a(I,X,AI).
insertar_a([I,R,D],X,[I,R,AD]) :- X > R, insertar_a(D,X,AD).
insertar_a([I,R,D],R,[I,R,D]).

balanceo(A,NA) :- inorder(A,L), longitud(L,N), balancear(L,N,NA,_).

%balancear(Lista,ElementosEliminar,ArbolConstruido,ListaRestante)
balancear([],_,[],[]).
balancear(L,0,[],L).
balancear([H|T],1,[[],H,[]],T).
balancear(L,M,[AI,H,AD],LSI) :- M > 1, M2 is M//2, balancear(L,M2,AI,[H|T]), MD is M - M2-1, balancear(T,MD,AD,LSI).

estaBalanceado([I,_,D]) :- altura(I,AI), altura(D,AD), abs(AI - AD) =< 1.

crearBalanceado(L,A) :- crear(L,ANB), balanceo(ANB,A).
