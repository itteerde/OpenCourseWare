/* fib(N,F) is true if F is the Nth Fibonacci number.                      */
fib(0,0).
fib(s(0),s(0)).
fib(s(s(X)),F):-
  fib(X,D),
  fib(s(X),E),
  plus(D,E,F).

/* plus(X,Y,Z) is true if X, Y and Z are natural numbers such that Z is    */
/*   the  sum of X and Y.                                                  */
plus(0,X,X):-natural_number(X).
plus(s(X),Y,s(Z)):-plus(X,Y,Z).