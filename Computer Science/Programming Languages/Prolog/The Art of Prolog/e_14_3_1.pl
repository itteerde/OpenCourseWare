/* p(Xs) is true if Xs is a list consisting of n a's followed by n b's.    */
p(Xs):-p_1(q0,Xs,[]).

p_1(q1,[],[]).
p_1(q0,[a|Xs],S):-p_1(q0,Xs,[a|S]).
p_1(q0,[b|Xs],[a|S]):-p_1(q1,Xs,S).
p_1(q1,[b|Xs],[a|S]):-p_1(q1,Xs,S).