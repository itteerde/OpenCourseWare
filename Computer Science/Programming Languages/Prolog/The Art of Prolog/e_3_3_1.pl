/* substitute(X,Y,Xs,Ys) is true if the list Ys is the result of           */
/*   substituting Y for all occurrences of X in the list Xs.               */
substitute(X,Y,[],[]).
substitute(X,Y,[X|Xs],[Y|Ys]):-substitute(X,Y,Xs,Ys).
substitute(X,Y,[Z|Xs],[Z|Ys]):-X\=Z, substitute(X,Y,Xs,Ys).