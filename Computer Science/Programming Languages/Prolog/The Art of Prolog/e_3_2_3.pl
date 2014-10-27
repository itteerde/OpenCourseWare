/* double(Xs,Ys) is true if every element in the list Xs appears twice in  */
/*   the list Ys.                                                          */
double([],[]).
double([X|Xs],[X,X|Ys]):-double(Xs,Ys).