/* even(X) is true if X is an even natural number.                         */
even(0).
even(s(s(X))):-even(X).

/* odd(X) is true if X is an odd natural number.                           */
odd(s(0)).
odd(s(s(X))):-odd(X).