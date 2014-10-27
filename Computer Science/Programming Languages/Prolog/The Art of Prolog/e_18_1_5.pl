initial_state(posn(8,[])).

final_state(posn(N,Qs)):-length(Qs, N).

move(posn(Q,Qs), Q):-
  not(member(Q, Qs)),
  not(attack(Qs, 1, Q)).
move(posn(N,Qs), Q):-
  N > 1,
  N1 is N - 1,
  move(posn(N1,Qs), Q).
  
attack([Q0|_], I, Q):-
  Q is Q0 + I, !.
attack([Q0|_], I, Q):-
  Q is Q0 - I, !.
attack([_|Qs], I, Q):-
  I1 is I + 1, 
  attack(Qs, I1, Q).

update(posn(N,Qs), Q, posn(N,[Q|Qs])).  

legal(_).

length(Xs, L):-length_1(Xs, 0, L).

length_1([_|Xs], L0, L):-L1 is L0 + 1, length_1(Xs, L1, L).
length_1([], L, L).

member(X, [X|_]).
member(X, [_|Xs]):-member(X, Xs).