/* p(WhereTheBoatIs, MissionariesOnLeft, CannibalsOnLeft)                  */
/* m(MissionariesInBoat, CannibalsInBoat)                                  */
 
initial_state(p(left, 3, 3)).

final_state(p(right, 0, 0)).

move(p(left,  M, _), m(1, 0)):-M >= 1.
move(p(left,  _, C), m(0, 1)):-C >= 1.
move(p(left,  M, C), m(1, 1)):-M >= 1, C >= 1.
move(p(left,  M, _), m(2, 0)):-M >= 2.
move(p(left,  _, C), m(0, 2)):-C >= 2.
move(p(right, M, _), m(1, 0)):-(3 - M) >= 1.
move(p(right, _, C), m(0, 1)):-(3 - C) >= 1.
move(p(right, M, C), m(1, 1)):-(3 - M) >= 1, (3 - C) >= 1.
move(p(right, M, _), m(2, 0)):-(3 - M) >= 2.
move(p(right, _, C), m(0, 2)):-(3 - C) >= 2.

update(p(left, M0, C0), m(MB, CB), p(right, M, C)):-
  M is M0 - MB, C is C0 - CB.
update(p(right, M0, C0), m(MB, CB), p(left, M, C)):-
  M is M0 + MB, C is C0 + CB.

/* This uses an (under)estimation of the number of remaining voyages as    */
/*   the evaluation function.                                              */
value(p(_,     M, C), 1):-M + C =:= 1, !.
value(p(left,  M, C), L):-L is (M + C - 2) * 2 + 1.
value(p(right, M, C), L):-L is (M + C) * 2.

/* Ensures that, on each bank, the cannibals are not outnumbered */
legal(p(_, _, 3)):-!.
legal(p(_, _, 0)):-!.
legal(p(_, M, M)).