/**
 * Fernuni-Hagen Skript 01816, Prof. Christoph Beierle
 */

eight_queens([X1,X2,X3,X4,X5,X6,X7,X8]) :-
	permutation([1,2,3,4,5,6,7,8], [X1,X2,X3,X4,X5,X6,X7,X8]),
	safe([X1,X2,X3,X4,X5,X6,X7,X8]).
	
permutation([],[]).
permutation([X|Xs],Ls) :-
	delete(X,Ls,Rs),
	permutation(Xs,Rs).
	
delete(X, [X|Xs], Xs).
delete(X, [Y|Ys], [Y|Rs]) :-
	delete(X, Ys, Rs).
	
safe([]).
safe([X|Xs]) :-
	noattack(X,Xs),
	safe(Xs).
	
noattack(X,Xs) :-
	noattack(X,Xs,1).
	
noattack(X, [], Nb).
noattack(X, [Y|Ys], Nb) :-
	X =\= Y - Nb,
	X =\= Y + Nb,
	Nb1 is Nb + 1,
	noattack(X, Ys, Nb1).