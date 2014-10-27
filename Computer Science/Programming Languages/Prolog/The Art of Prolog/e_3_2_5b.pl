/* sum(Is,S) is true if S is the sum of the list of integers Is.           */
sum([],0).
sum([0|Is],S):-sum(Is,S).
sum([s(I)|Is],s(Z)):-sum([I|Is],Z).
