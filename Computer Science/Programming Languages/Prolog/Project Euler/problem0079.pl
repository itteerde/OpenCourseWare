solve(X,C) :-
	X is C,
	contains(319, C),
	contains(680, C),
	contains(180, C),
	contains(690, C),
	contains(129, C),
	contains(620, C),
	contains(689, C),
	contains(762, C),
	contains(318, C),
	contains(368, C),
	contains(720, C),
	contains(710, C),
	contains(629, C),
	contains(168, C),
	contains(160, C),
	contains(689, C),
	contains(716, C),
	contains(731, C),
	contains(736, C),
	contains(729, C),
	contains(316, C),
	contains(769, C),
	contains(290, C),
	contains(719, C),
	contains(680, C),
	contains(318, C),
	contains(389, C),
	contains(162, C),
	contains(289, C),
	contains(162, C),
	contains(718, C),
	contains(319, C),
	contains(790, C),
	contains(680, C),
	contains(890, C),
	contains(362, C),
	contains(319, C),
	contains(760, C),
	contains(316, C),
	contains(380, C),
	contains(319, C),
	contains(728, C),
    contains(716, C), !.

solve(X,C) :-
	Cp is C+1,
	solve(X,Cp).
	
contains(H, Counter) :-
    name(Counter, C_ascii),
    name(H, H_ascii),
    containsL(H_ascii, C_ascii).

containsL([], L).

containsL([H|T1], [H|T2]) :-
    containsL(T1, T2), !.

containsL([H1|T1], [H2|T2]) :-
    containsL([H1|T1], T2).
