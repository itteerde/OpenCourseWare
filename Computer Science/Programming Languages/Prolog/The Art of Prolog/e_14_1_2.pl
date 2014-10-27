/* Goal: generate_and_test([a,b,c,d,e], [p,q,r,s,t], Xs).                  */
/*   or: backtrack([a,b,c,d,e], [p,q,r,s,t], Xs).                          */

/* preferences(Person, List) is true if the Person prefers people of the   */
/*   other sex in the order given in the List.                             */
preferences(a, [q,t,p,r,s]).  preferences(p, [e,a,d,b,c]).
preferences(b, [p,q,r,s,t]).  preferences(q, [d,e,b,a,c]).
preferences(c, [q,r,t,s,p]).  preferences(r, [a,d,b,c,e]).
preferences(d, [p,r,q,s,t]).  preferences(s, [c,b,d,a,e]).
preferences(e, [t,r,q,p,s]).  preferences(t, [d,b,c,e,a]).

/* stable(Men, Women, Marriages) is true if Marriages is a set of stable   */
/*   marriages between the Men and the Women.                              */
stable([], _, _).
stable([Man|Men], Women, Marriages):-
  stable_1(Women, Man, Marriages),
  stable(Men, Women, Marriages).

stable_1([], _, _).
stable_1([Woman|Women], Man, Marriages):-
  not(unstable(Man, Woman, Marriages)),
  stable_1(Women, Man, Marriages).

/* unstable(Man, Woman, Marriages) is true if the Man and the Woman both   */
/*   prefer each other to their spouses as defined by the set of Marriages.*/
unstable(Man, Woman, Marriages):-
  married(Man, Wife, Marriages),
  married(Husband, Woman, Marriages),
  prefers(Man, Woman, Wife),
  prefers(Woman, Man, Husband).
  
/* married(Man, Woman, Marriages) is true if the Man and the Woman are     */
/*   married as defined by the set of Marriages.                           */
married(Man, Woman, Marriages):-
  rest(m(Man, Woman), Marriages, _).  

/* prefers(Person, OtherPerson, Spouse) is true if the Person prefers the  */
/*   OtherPerson to his Spouse.                                            */
prefers(Person, OtherPerson, Spouse):-
  preferences(Person, Preferences),
  rest(OtherPerson, Preferences, Rest),
  rest(Spouse, Rest, _).
  
/* rest(X, Ys, Zs) is true if X is a member of the list Ys, and the list   */
/*   Zs is the rest of the list following X.                               */
rest(X, [X|Ys], Ys):-!.
rest(X, [_|Ys], Zs):-rest(X, Ys, Zs).

/* select(X, Ys, Zs) is true if Zs is the result of removing one           */
/*   occurrence of the element X from the list Ys.                         */
select(X, [X|Ys], Ys).
select(X, [Y|Ys], [Y|Zs]):-select(X, Ys, Zs).

/*
 * Generate and Test
 */
/* generate_and_test(Men, Women, Marriages) is true if Marriages is a set  */
/*   of stable marriages between the Men and the Women.                    */
/* e.g.  generate_and_test([a,b,c,d,e], [p,q,r,s,t], Xs).                  */
generate_and_test(Men, Women, Marriages):-
  generate(Men, Women, Marriages),
  stable(Men, Women, Marriages).

/* generate(Men, Women, Marriages) is true if Marriages is a set of        */
/*   possible marriages between the Men and the Women.                     */
generate([], [], []).
generate([Man|Men], Women, [m(Man,Woman)|Marriages]):-
  select(Woman, Women, Women1),
  generate(Men, Women1, Marriages).

/*
 * Backtrack
 */
/* backtrack(Men, Women, Marriages) is true if Marriages is a set of       */
/*   stable marriages between the Men and the Women.                       */
/* e.g.  backtrack([a,b,c,d,e], [p,q,r,s,t], Xs).                          */
backtrack(Men, Women, Marriages):-
  backtrack_1(Men, Women, Men, Women, [], Marriages).

backtrack_1([], _, _, _, Marriages, Marriages).
backtrack_1([Man|Men], Women, Men0, Women0, Marriages0, Marriages):-
  select(Woman, Women, Women1),
  Marriages1 = [m(Man,Woman)|Marriages0],
  stable(Men0, Women0, Marriages1),
  backtrack_1(Men, Women1, Men0, Women0, Marriages1, Marriages).