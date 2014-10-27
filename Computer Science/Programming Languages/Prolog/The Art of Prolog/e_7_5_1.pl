/* no_doubles(Xs, Ys) is true if Ys is the list of the elements appearing  */
/*   in Xs without duplication.  The elements in Ys are in the reverse     */
/*   order of Xs with the first duplicate values being kept.               */
no_doubles(Xs, Ys):-no_doubles_1(Xs, [], Ys).

no_doubles_1([], Ys, Ys).
no_doubles_1([X|Xs], As, Ys):-
  member(X, As),
  no_doubles_1(Xs, As, Ys).
no_doubles_1([X|Xs], As, Ys):-
  nonmember(X, As),
  no_doubles_1(Xs, [X|As], Ys).

/* member(X,Xs) is true if X is a member of the list Xs.                   */
member(X,[X|Xs]).
member(X,[Y|Ys]):-member(X,Ys).

/* nonmember(X,Xs) is true if X is not a member of the list Xs.            */
nonmember(X,[Y|Ys]):-X=\=Y, nonmember(X,Ys).
nonmember(X,[]).