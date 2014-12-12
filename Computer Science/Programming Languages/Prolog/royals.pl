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
married(princess_anne, mark_phillips).
married(princess_anne, timothy_laurence).
mother(princess_anne, zara_phillips).
mother(princess_anne, peter_phillips).

male(prince_andrew).
married(prince_andrew, sarah_duchess_of_york).
father(prince_andrew, princess_beatrice).
father(prince_andrew, princess_eugenie).

mother(sarah_duchess_of_york, princess_beatrice).
mother(sarah_duchess_of_york, princess_eugenie).

male(prince_edward).
married(prince_edward, sophie_countess_of_wessex).
father(prince_edward, lady_louise).
father(prince_edward, viscount_severn).

female(sophie_countess_of_wessex).
mother(sophie_countess_of_wessex, lady_louise).
mother(sophie_countess_of_wessex, viscount_severn).

female(lady_louise).
male(viscount_severn).

male(mark_phillips).
father(mark_phillips, peter_phillips).
father(mark_phillips, zara_phillips).

male(peter_phillips).
married(peter_phillips, autumn_kelly).

female(zara_phillips).
female(autumn_kelly).

queen(X) :-
	majesty(X),
	female(X).
	
king(X) :-
	majesty(X),
	male(X).
	
couple(X, Y) :- 
	married(X, Y),
	male(X).
couple(X, Y) :- 
	male(X),
	married(Y, X).
couple(X, Y) :- 
	married(X, Y),
	female(X).
couple(X, Y) :- 
	female(X),
	married(Y, X).

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

grandchild(X, Y) :-
	grandparent(Y, X).
	
granddaughter(X, Y) :-
	grandchild(X, Y),
	female(X).
	
grandson(X, Y) :-
	grandchild(X, Y),
	male(X).
	
wife(X, Y) :-
	couple(X, Y),
	female(X).
	
husband(X, Y) :-
	couple(X, Y),
	male(X).