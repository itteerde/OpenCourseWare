/* p(WhereTheBoatIs, PeopleOnIsle, PeopleOnBank) */

initial_state(p(isle, [h1,h2,h3,h4,h5,w1,w2,w3,w4,w5], [])).

final_state(p(bank, [], [h1,h2,h3,h4,h5,w1,w2,w3,w4,w5])).

move(p(isle, I, _), [P1]):-       /* Move one person from isle to bank     */
  rest(I, P1, _).
move(p(isle, I, _), [P1,P2]):-    /* Move two people from isle to bank     */
  rest(I, P1, I1), rest(I1, P2, _), 
  legal_1([P1,P2]).
move(p(isle, I, _), [P1,P2,P3]):- /* Move three people from isle to bank   */
  rest(I, P1, I1), rest(I1, P2, I2), rest(I2, P3, _), 
  legal_1([P1,P2,P3]).
move(p(bank, _, B), [P1]):-       /* Move one person from bank to isle     */
  rest(B, P1, _).
move(p(bank, _, B), [P1,P2]):-    /* Move two people from bank to isle     */
  rest(B, P1, B1), rest(B1, P2, _), 
  legal_1([P1,P2]).
move(p(bank, _, B), [P1,P2,P3]):- /* Move three people from bank to isle   */
  rest(B, P1, B1), rest(B1, P2, B2), rest(B2, P3, _), 
  legal_1([P1,P2,P3]).

update(p(isle, I1, B1), Boat, p(bank, I2, B2)):-
  ordered_delete(Boat, I1, I2), ordered_insert(Boat, B1, B2).
update(p(bank, I1, B1), Boat, p(isle, I2, B2)):-
  ordered_delete(Boat, B1, B2), ordered_insert(Boat, I1, I2).

/* This uses an (under)estimation of the number of remaining voyages as    */
/*   the evaluation function.                                              */
value(p(isle, Xs, _), L):-length(Xs, 0, M), L is (M // 2) * 2 - 1.
value(p(bank, Xs, _), L):-length(Xs, 0, M), L is (M + 1) // 2 * 2.

legal(p(_, Xs, Ys)):-legal_1(Xs), legal_1(Ys).

legal_1(Xs):-only_wives(Xs), !.
legal_1(Xs):-wives_with_husbands(Xs, Xs).

only_wives([]).
only_wives([W|Xs]):-couple(_, W), only_wives(Xs).

wives_with_husbands([], _).
wives_with_husbands([H|Xs], Ys):-
  couple(H, _), !, wives_with_husbands(Xs, Ys).
wives_with_husbands([W|Xs], Ys):-
  couple(H, W), rest(Ys, H, _), !, wives_with_husbands(Xs, Ys).
  
couple(h1,w1). couple(h2,w2). couple(h3,w3). couple(h4,w4). couple(h5,w5). 

/* ordered_delete(Xs, Ys, Zs) is true if Zs is the ordered list obtained   */
/*   by deleting the ordered list Xs from the ordered list Ys.             */
ordered_delete([], Ys, Ys).
ordered_delete([X|Xs], [X|Ys], Zs):-!,
  ordered_delete(Xs, Ys, Zs).
ordered_delete([X|Xs], [Y|Ys], Zs):-
  X > Y, !, Zs = [Y|Ws], ordered_delete([X|Xs], Ys, Ws).
ordered_delete([_|Xs], [Y|Ys], [Y|Zs]):-
  ordered_delete(Xs, Ys, Zs).

/* ordered_insert(Xs, Ys, Zs) is true if Zs is the ordered list obtained   */
/*   by inserting the ordered list Xs in the ordered list Ys.              */
ordered_insert([], Ys, Ys).
ordered_insert([X|Xs], [Y|Ys], Zs):-
  Y =< X, !, Zs = [Y|Ws], ordered_insert([X|Xs], Ys, Ws).
ordered_insert([X|Xs], Ys, [X|Zs]):-
  ordered_insert(Xs, Ys, Zs).

/* rest(Xs, Y, Zs) is true if Zs is the list of elements following the     */
/*   element Y in the list Xs.                                             */
rest([X|Xs], X, Xs).
rest([_|Xs], Y, Zs):-rest(Xs, Y, Zs).

/* length(Xs, L0, L) is true if L is equal to L0 plus the number of        */
/*   elements in the list Xs.                                              */
length([], L, L).
length([_|Xs], L0, L):-L1 is L0 + 1, length(Xs, L1, L).
