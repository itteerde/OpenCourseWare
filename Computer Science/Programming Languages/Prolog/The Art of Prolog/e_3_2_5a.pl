/* sum(Is,S) is true if S is the sum of the list of integers Is.           */
sum([],0).
sum([I|Is],S):-
  sum(Is,S0),
  plus(I,S0,S).