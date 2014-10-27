/* flatten(Xs,Ys) is true if Ys is a list of the elements of Xs (in        */
/*   reversed order).                                                      */
/* e.g. flatten([[[3,c],5,[4,[]]],[1,b],a],[a,b,1,4,5,c,3]).               */
flatten(Xs,Ys):-flatten(Xs,[],Ys).

flatten([X|Xs],As,Ys):-flatten(X,As,As1), flatten(Xs,As1,Ys).
flatten(X,As,[X|As]):-integer(X).
flatten(X,As,[X|As]):-atom(X), X\=[].
flatten([],Ys,Ys).