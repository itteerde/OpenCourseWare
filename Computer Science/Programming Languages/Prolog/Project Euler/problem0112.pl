solve(X) :-
    solve(0, 10, 90, X).

solve(CBouncy, Last, P, Next) :-
    nextBouncy(Last,Next),
    NewCBouncy is CBouncy + 1,
    Ratio is NewCBouncy*100/Next,
	%Next =:= 1587000,
	rem(Next,1000) < 1,
	print_message(informational,Ratio),
    Ratio >= P, !.

solve(CBouncy, Last, P, Next) :-
    nextBouncy(Last,Next),
    NewCBouncy is CBouncy + 1,
    Ratio is NewCBouncy*100/Next,
    Ratio >= P, !.

solve(CBouncy, Last, P, Res) :-
    nextBouncy(Last,NextB),
    NewCBouncy is CBouncy + 1,
    solve(NewCBouncy, NextB, P, Res).

nextBouncy(Last, Next) :-
    NewB is Last+1,
    bouncy(NewB),
    NewB = Next.

nextBouncy(Last, Next) :-
    NewB is Last+1,
    \+ bouncy(NewB),
    nextBouncy(NewB, Next).

bouncy(X) :-
	ascendingNumber(X),!,fail.
bouncy(X) :-
	descendingNumber(X),!,fail.
bouncy(X) :-
	constantNumber(X),!,fail.

bouncy(_).

ascendingNumber(X) :-
    name(X, Ascii),
    ascending(Ascii).

descendingNumber(X) :-
    name(X, Ascii),
    descending(Ascii).


ascending([E]).
ascending([H1,H2|T]) :-
    H1 =< H2, ascending([H2|T]).

descending([E]).
descending([H1,H2|T]) :-
    H1 >= H2, descending([H2|T]).

constantNumber(X) :-
	name(X, Ascii),
	constant(Ascii).
constant([E]).
constant([H,HT|T]) :-   
	H =:= HT,
	constant([HT|T]).
