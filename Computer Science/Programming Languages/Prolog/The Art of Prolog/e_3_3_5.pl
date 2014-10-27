/* merge_sort(Xs, Ys) is true if the list Ys is a sorted permutation of    */
/*   the list Xs.                                                          */
merge_sort([], []).
merge_sort([X], [X]).
merge_sort([Odd,Even|Xs], Ys):-
  split([Odd,Even|Xs], Odds, Evens),
  merge_sort(Odds, Os),
  merge_sort(Evens, Es),
  ordered_merge(Os, Es, Ys).

/* split(Xs, Os, Es) is true if Os is a list containing the odd positioned */
/*   elements of the list Xs, and Es is a list containing the even         */
/*   positioned elements of Xs.                                            */
split([], [], []).
split([X|Xs], [X|Os], Es):-split(Xs, Es, Os).

/* ordered_merge(Xs, Ys, Zs) is true if Zs is an ordered list obtained     */
/*   from merging the ordered lists Xs and Ys.                             */
ordered_merge([], Ys, Ys).
ordered_merge([X|Xs], [], [X|Xs]).
ordered_merge([X|Xs], [Y|Ys], [X|Zs]):-X < Y,  ordered_merge(Xs, [Y|Ys], Zs).
ordered_merge([X|Xs], [Y|Ys], [Y|Zs]):-X >= Y, ordered_merge([X|Xs], Ys, Zs).