/* int_div(X,Y,Z) is true if Z is the quotient of the integer division of  */
/*   X by Y.                                                               */
int_div(X,Y,0):-gt(Y,X).
int_div(X,Y,s(Z)):-plus(Y,X1,X),int_div(X1,Y,Z).