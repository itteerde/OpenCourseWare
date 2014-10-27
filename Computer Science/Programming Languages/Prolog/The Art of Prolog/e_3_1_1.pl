/* lt(X,Y) is true if X and Y are natural numbers such that X is less than */
/*   Y.                                                                    */
lt(0,s(X)):-natural_number(X).
lt(s(X),s(Y)):-lt(X,Y).

/* gt(X,Y) is true if X and Y are natural numbers such that X is greater   */
/*   than Y.                                                               */
gt(s(X),0):-natural_number(X).
gt(s(X),s(Y)):-gt(X,Y).

/* ge(X,Y) is true if X and Y are natural numbers such that X is greater   */
/*   than or equal to Y.                                                   */
ge(X,0):-natural_number(X).
ge(s(X),s(Y)):-ge(X,Y).

/* natural_number(X) is true if X is a natural number.                     */
natural_number(0).
natural_number(s(X)):-natural_number(X).