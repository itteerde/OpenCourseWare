/* sum_tree(Tree,S) is true if S is the sum of the elements of the tree    */
/*   Tree.                                                                 */
sum_tree(void,0).
sum_tree(tree(X,L,R),S):-
  sum_tree(L,S1),
  sum_tree(R,S2),
  S is X+S1+S2.