/* functor_(Term,F,Arity) is true if Term is a term whose principal        */
/*   functor has name F and arity Arity.                                   */
/* e.g. functor_(a(1,b),a,2).                                              */
functor_(Term,F,N):-Term=..[F|Args], length(Args,N).

/* arg_(N,Term,Arg) is true if Arg is the Nth argument of Term.            */
/* e.g. arg_(2,a(b,c,d),c).                                                */
arg_(N,Term,Arg):-Term=..[F|Args], position(N,Args,Arg).

/* position(N,Xs,X) is true if X is the Nth element in the list Xs.        */
position(1,[X|Xs],X).
position(N,[X|Xs],Y):-N>1, N1 is N-1, position(N1,Xs,Y).