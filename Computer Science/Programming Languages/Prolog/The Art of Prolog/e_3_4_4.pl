/* tree_insert(X,Tree,Tree1) is true if Tree1 is the result of inserting   */
/*   the element X into the ordered tree Tree.                             */
tree_insert(X,void,tree(X,void,void)).
tree_insert(X,tree(X,L,R),tree(X,L,R)).
tree_insert(X,tree(Y,L,R),tree(Y,L1,R)):-
  X < Y,
  tree_insert(X,L,L1).
tree_insert(X,tree(Y,L,R),tree(Y,L,R1)):-
  X > Y,
  tree_insert(X,R,R1).