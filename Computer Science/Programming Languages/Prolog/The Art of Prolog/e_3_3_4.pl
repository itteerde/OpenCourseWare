/* even_permutation(Xs, Ys) is true if Ys is an even permutation of Xs.    */
even_permutation(Xs, Ys):-
  permute(Xs, Ys),
  sign_of_product_of_differences(Xs, 1, D),
  sign_of_product_of_differences(Ys, 1, E),
  D = E.

/* odd_permutation(Xs, Ys) is true if Ys is an odd permutation of Xs.      */
odd_permutation(Xs, Ys):-
  permute(Xs, Ys),
  sign_of_product_of_differences(Xs, 1, D),
  sign_of_product_of_differences(Ys, 1, E),
  D =\= E.

/* permute(Xs, Ys) is true if Ys is a permutation of the list Xs.          */
permute([], []).
permute([X|Xs], Ys1):-
  permute(Xs, Ys), 
  pick(X, Ys1, Ys).

/* pick(X, Ys, Zs) is true if Zs is the result of removing one occurrence  */
/*   of the element X from the list Ys.                                    */
pick(X, [X|Xs], Xs).
pick(X, [Y|Ys], [Y|Zs]):-
  pick(X, Ys, Zs).

sign_of_product_of_differences([], D, D).
sign_of_product_of_differences([Y|Xs], D0, D):-
  sign_of_product_of_differences_1(Xs, Y, D0, D1),
  sign_of_product_of_differences(Xs, D1, D).

sign_of_product_of_differences_1([], _, D, D).
sign_of_product_of_differences_1([X|Xs], Y, D0, D):-
  Y =\= X,
  D1 is D0 * (Y - X) // abs(Y - X),  
  sign_of_product_of_differences_1(Xs, Y, D1, D).