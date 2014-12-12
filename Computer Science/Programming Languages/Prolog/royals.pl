majesty(elizabeth_ii).
female(elizabeth_ii).
married(elizabeth_ii, philip_duke_of_edinburgh).
mother(elizabeth_ii, charles_prince_of_wales).
mother(elizabeth_ii, princess_anne).
mother(elizabeth_ii, prince_andrew).
mother(elizabeth_ii, prince_edward).

male(philip_duke_of_edinburgh).
father(philip_duke_of_edinburgh, charles_prince_of_wales).
father(philip_duke_of_edinburgh, princess_anne).
father(philip_duke_of_edinburgh, prince_andrew).
father(philip_duke_of_edinburgh, prince_edward).

male(charles_prince_of_wales).
married(charles_prince_of_wales, diana_princess_of_wales).
married(charles_prince_of_wales, camila_duchess_of_cornwall).
father(charles_prince_of_wales, prince_william).
father(charles_prince_of_wales, prince_henry).

female(diana_princess_of_wales).
mother(diana_princess_of_wales, prince_william).
mother(diana_princess_of_wales, prince_henry).

female(camila_duchess_of_cornwall).

female(princess_anne).
male(prince_andrew).
male(prince_edward).

queen(X) :-
	majesty(X),
	female(X).
	
married(X, Y) :- married(Y, X).

sibling(X, Y) :-
	mother(M, X),
	mother(M, Y),
	father(F, X),
	father(F, Y).
	
brother(X, Y) :-
	male(X),
	sibling(X, Y).
	
sister(X, Y) :-
	female(X),
	sibling(X, Y).
	
daughter(X, Y) :-
	female(X),
	mother(Y, X).
daughter(X, Y) :-
	female(X),
	father(Y, X).
	
son(X, Y) :-
	male(X),
	mother(Y, X).
son(X, Y) :-
	male(X),
	father(Y, X).

parent(P, C) :-
	father(P, C).
parent(P, C) :-
	mother(P, C).
	
aunt(A, N) :-
	parent(P, N),
	sibling(A, P),
	female(A).
	
uncle(U, N) :-
	parent(P, N),
	sibling(U, P),
	male(U).
	
grandparent(X, Y) :-
	parent(Z, Y),
	parent(X, Z).
	
grandfather(X, Y) :-
	grandparent(X, Y),
	male(X).
	
grandmother(X, Y) :-
	grandparent(X, Y),
	female(X).