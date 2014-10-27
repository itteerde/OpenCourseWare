/* adjacent(X,Y,Zs) is true if the elements X and Y are adjacent in the    */
/*   list Zs.                                                              */
adjacent(X,Y,[X,Y|Zs]).
adjacent(X,Y,[Z|Zs]):-adjacent(X,Y,Zs).

/* last(X,Xs) is true if X is the last element in the list Xs.             */
last(X,[X]).
last(X,[Y|Xs]):-last(X,Xs).