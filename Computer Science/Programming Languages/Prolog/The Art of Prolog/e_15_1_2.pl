/* pre_order(Tree,L) is true if L is a pre-order traversal of the binary   */
/*   tree Tree.                                                            */
pre_order(T,L):-pre_order(T,L,[]).

pre_order(void,Xs,Xs).
pre_order(tree(X,L,R),[X|Xs],Zs):-
  pre_order(L,Xs,Ys),
  pre_order(R,Ys,Zs).

/* in_order(Tree,L) is true if L is an in-order traversal of the binary    */
/*   tree Tree.                                                            */
in_order(T,L):-in_order(T,L,[]).

in_order(void,Xs,Xs).
in_order(tree(X,L,R),Xs,Zs):-
  in_order(L,Xs,[X|Ys]),
  in_order(R,Ys,Zs).

/* post_order(Tree,L) is true if L is a post-order traversal of the binary */
/*   tree Tree.                                                            */
post_order(T,L):-post_order(T,L,[]).

post_order(void,Xs,Xs).
post_order(tree(X,L,R),Xs,Zs):-
  post_order(L,Xs,Ys),
  post_order(R,Ys,[X|Zs]).