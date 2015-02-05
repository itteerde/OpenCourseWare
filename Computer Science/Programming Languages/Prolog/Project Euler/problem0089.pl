:- use_module(library(pio)).

lines([])           --> call(eos), !.
lines([Line|Lines]) --> line(Line), lines(Lines).

eos([], []).

line([])     --> ( "\n" ; call(eos) ), !.
line([L|Ls]) --> [L], line(Ls).

parseLines([],[]).
parseLines([],ParsedLines).
parseLines([H1|Lines],[S|ParsedLines]) :-
	string_codes(S,H1),
	%string_chars(S,H2),
	parseLines(Lines,ParsedLines).

replace_word(SubString, Replacement, String, Result) :-
	sub_string(String, Before, Length, After, SubString),
	sub_string(String, 0, Before, _, Head),
	sub_string(String, _, After, 0, Tail),
	string_concat(Head, Replacement, Begin),
	string_concat(Begin, Tail, Result),!.
	
optimize(R,O) :-
	replace_word("CCCCC","D",R,RN),
	optimize(RN,O),!.
optimize(R,O) :-
	replace_word("CCCC","CD",R,RN),
	optimize(RN,O),!.
optimize(R,O) :-
	replace_word("DCD","CM",R,RN),
	optimize(RN,O),!.
optimize(R,O) :-
	replace_word("XXXXX","L",R,RN),
	optimize(RN,O),!.
optimize(R,O) :-
	replace_word("XXXX","XL",R,RN),
	optimize(RN,O),!.
optimize(R,O) :-
	replace_word("LXL","XC",R,RN),
	optimize(RN,O),!.
optimize(R,O) :-
	replace_word("IIIII","V",R,RN),
	optimize(RN,O),!.
optimize(R,O) :-
	replace_word("IIII","IV",R,RN),
	optimize(RN,O),!.
optimize(R,O) :-
	replace_word("VIV","IX",R,RN),
	optimize(RN,O),!.
optimize(R,R).	

solve(X) :-
	solve(X,1,0),!.
solve(Acc, 1001, Acc).
solve(X,N,Acc) :-
	data(Data),
	nth1(N, Data, Number),
	string_length(Number, LN),
	optimize(Number,ON),
	string_length(ON, LON),
	NN is N+1,
	AccN is Acc + (LN - LON),
	solve(X, NN, AccN), !.
	
main :-
	phrase_from_file(lines(Ls), 'p089_roman.txt'),
	parseLines(Ls,ParsedLines),
	assert(data(ParsedLines)), !.

