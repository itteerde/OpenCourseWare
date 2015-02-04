:- use_module(library(clpfd)).

pandigital(N) :-
	name(N,L),
	pandigitalStart(L),
	pandigitalEnd(L).

pandigitalStart([X1,X2,X3,X4,X5,X6,X7,X8,X9|T]) :-
	[X1,X2,X3,X4,X5,X6,X7,X8,X9] ins 49..57,
	all_distinct([X1,X2,X3,X4,X5,X6,X7,X8,X9]), !.
	
pandigitalEnd([X1,X2,X3,X4,X5,X6,X7,X8,X9]) :-
	[X1,X2,X3,X4,X5,X6,X7,X8,X9] ins 49..57,
	all_distinct([X1,X2,X3,X4,X5,X6,X7,X8,X9]), !.

pandigitalEnd([X1,X2,X3,X4,X5,X6,X7,X8,X9]) :- !, fail.

pandigitalEnd([H|T]) :-
	length(T,X), X > 8,
	pandigitalEnd(T).

solve(Res) :-
	solve(1,Res,0,1).
solve(N,Res,A1,A2) :-
	pandigital(A2),
	Res is N,!.
solve(N,Res,A1,A2) :-
	F is A1 + A2,
	rem(N, 1000) < 1,
	print_message(informational, N),
	NN is N+1,
	solve(NN, Res,A2,F).
solve(N,Res,A1,A2) :-
	F is A1 + A2,
	NN is N+1,
	solve(NN, Res,A2,F).
