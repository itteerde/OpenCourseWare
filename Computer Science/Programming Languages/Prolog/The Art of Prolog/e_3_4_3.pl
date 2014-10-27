/* ordered_tree(Tree) is true if Tree is an ordered tree.                  */
ordered_tree(void).
ordered_tree(tree(X,L,R)):-
  ordered_left(X,L),
  ordered_right(X,R).

/* ordered_left(X,Tree) is true if Tree is an ordered tree, and X is       */
/*   greater than all the elements of Tree.                                */
ordered_left(X,void).
ordered_left(X,tree(Y,L,R)):-
  X>Y,
  ordered_tree(tree(Y,L,R)),
  ordered_tree(tree(X,R,void)).

/* ordered_right(X,Tree) is true if Tree is an ordered tree, and X is less */
/*   than all the elements of Tree.                                        */
ordered_right(X,void).
ordered_right(X,tree(Y,L,R)):-
  X < Y,
  ordered_tree(tree(Y,L,R)),
  ordered_tree(tree(X,void,L)).