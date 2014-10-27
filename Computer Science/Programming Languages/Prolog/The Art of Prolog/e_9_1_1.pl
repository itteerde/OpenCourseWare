/* flatten(Xs,Ys) is true if Ys is a list of the elements in Xs.           */
/* e.g. flatten([[[3,c],5,[4,[]]],[1,b],a],[3,c,5,4,1,b,a]).               */
flatten(Xs,Ys):-flatten(Xs,[],Ys).

flatten([X|Xs],As,Ys):-flatten(Xs,As,As1), flatten(X,As1,Ys).
flatten(X,As,[X|As]):-integer(X).
flatten(X,As,[X|As]):-atom(X), X\=[].
flatten([],Ys,Ys).