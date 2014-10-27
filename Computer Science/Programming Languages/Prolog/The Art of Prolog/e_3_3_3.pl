/* no_doubles(Xs, Ys) is true if Ys is the list of the elements appearing  */
/*   in Xs without duplication.  The elements in Ys are in the same order  */
/*   as in Xs with the last duplicate values being kept.                   */
no_doubles([], []).
no_doubles([X|Xs], Ys):-
  member(X, Xs),
  no_doubles(Xs, Ys).
no_doubles([X|Xs], [X|Ys]):-
  nonmember(X, Xs), 
  no_doubles(Xs, Ys).

/* member(X,Xs) is true if X is a member of the list Xs.                   */
member(X,[X|Xs]).
member(X,[Y|Ys]):-member(X,Ys).

/* nonmember(X,Xs) is true if X is not a member of the list Xs.            */
nonmember(_, []).
nonmember(X, [Y|Ys]):-X =\= Y, nonmember(X, Ys).