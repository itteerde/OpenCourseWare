:- use_module(library(pio)).

lines([])           --> call(eos), !.
lines([Line|Lines]) --> line(Line), lines(Lines).

eos([], []).

line([])     --> ( "\n" ; call(eos) ), !.
line([L|Ls]) --> [L], line(Ls).

parseLines([],[]).
parseLines([],ParsedLines).
parseLines([H1|Lines],[H2|ParsedLines]) :-
	string_codes(S,H1),
	string_chars(S,H2),
	parseLines(Lines,ParsedLines).

insertsort(L1, L2) :- inso(L1, [], L2).

inso([], A, A).
inso([Cr,Cs|T], [], A) :-
    inso(T, [Cr,Cs], A).
inso([C1r,C1s|T1], [C2r,C2s|T2], A) :-
    card_value(C1r, V1),
    card_value(C2r, V2),
    V1=<V2, !, inso(T1, [C1r, C1s, C2r, C2s|T2], A).
inso([C1r,C1s|T1], [C2r,C2s|T2], A) :- insertsort([C1r,C1s|T2], B), inso(T1, [C2r,C2s|B], A).
	
hands([],[]).
hands([],Hands).
hands([[A1a,A1b,_,A2a,A2b,_,A3a,A3b,_,A4a,A4b,_,A5a,A5b,_,B1a,B1b,_,B2a,B2b,_,B3a,B3b,_,B4a,B4b,_,B5a,B5b]|Lines],[[Hand1,Hand2]|Hands]) :-
	insertsort([A1a,A1b,A2a,A2b,A3a,A3b,A4a,A4b,A5a,A5b],Hand1),
	insertsort([B1a,B1b,B2a,B2b,B3a,B3b,B4a,B4b,B5a,B5b],Hand2),
	hands(Lines,Hands).

royal_flush(['T', C2, 'J', C2, 'Q', C2, 'K', C2, 'A', C2], 10).

straight_flush([C1r,C1s,C2r,C2s,C3r,C3s,C4r,C4s,C5r,C5s], X, SR) :-
	card_value(C1r,C1rV), card_value(C2r,C2rV),
	card_value(C3r,C3rV), card_value(C4r,C4rV), card_value(C5r,C5rV),
	C1rV + 1 =:= C2rV, C2rV + 1 =:= C3rV, C3rV + 1 =:= C4rV, C4rV + 1 =:= C5rV,
	C1s = C2s, C2s = C3s, C4s = C5s,
	card_value(C5r,SR),
	X is 9.

four_of_a_kind([C1, _, C1, _, C1, _, C1, _, C2, _], 8, SR, Kicker) :-
card_value(C1, SR), card_value(C2, Kicker).
four_of_a_kind([C1, _, C2, _, C2, _, C2, _, C2, _], 8, SR, Kicker) :-
card_value(C1, SR), card_value(C2, Kicker).

full_house([C1r,_,C1r,_,C1r,_,C4r,_,C4r,_], X, SR, Kicker) :-
	card_value(C1r,SR), X is 7, card_value(C4r,Kicker).
full_house([C1r,_,C1r,_,C3r,_,C3r,_,C3r,_], X, SR, Kicker) :-
	card_value(C3r,SR), X is 7, card_value(C1r,Kicker).

flush([C1r,C1s,C2r,C1s,C3r,C1s,C4r,C1s,C5r,C1s], X, SR) :-
	X is 6, card_value(C5r,SR).

straight([C1, _, C2, _, C3, _, C4, _, C5, _], 5, V5) :-
    card_value(C1, V1), card_value(C2, V2),card_value(C3, V3), card_value(C4, V4), 
    card_value(C5, V5),
    V1 =:= V2-1, V2 =:= V3-1, V3 =:= V4-1, V4 =:= V5-1.

three_of_a_kind([C1, _, C1, _, C1, _, C2, _, C3, _], 4, SR, Kicker1, Kicker2) :-
card_value(C1, SR), card_value(C3, Kicker1), card_value(C2, Kicker2).
three_of_a_kind([C1, _, C2, _, C2, _, C2, _, C3, _], 4, SR, Kicker1, Kicker2) :-
card_value(C2, SR), card_value(C3, Kicker1), card_value(C1, Kicker2).  
three_of_a_kind([C1, _, C2, _, C3, _, C3, _, C3, _], 4, SR, Kicker1, Kicker2) :-
card_value(C3, SR), card_value(C2, Kicker1), card_value(C1, Kicker2).


two_pairs([C1, _, C1, _, C2, _, C2, _, C3, _], 3, SR, Kicker1, Kicker2) :-
card_value(C2,SR), card_value(C1,Kicker1), card_value(C3, Kicker2).
two_pairs([C1, _, C1, _, C2, _, C3, _, C3, _], 3, SR, Kicker1, Kicker2) :-
card_value(C3,SR), card_value(C1,Kicker1), card_value(C2, Kicker2).
two_pairs([C1, _, C2, _, C2, _, C3, _, C3, _], 3, SR, Kicker1, Kicker2) :-
card_value(C3,SR), card_value(C2,Kicker1), card_value(C1, Kicker2).

one_pair([C1r,C1s,C2r,C2s,C3r,C3s,C4r,C4s,C5r,C5s], X, SR, Kicker1, Kicker2, Kicker3) :-
	C1r = C2r,
	X is 2,
	card_value(C1r,SR),
	card_value(C5r,Kicker1), 
	card_value(C4r,Kicker2), 
	card_value(C3r,Kicker3).
one_pair([C1r,C1s,C2r,C2s,C3r,C3s,C4r,C4s,C5r,C5s], X, SR, Kicker1, Kicker2, Kicker3) :-
	C2r = C3r,
	X is 2,
	card_value(C2r,SR),
	card_value(C5r,Kicker1), 
	card_value(C4r,Kicker2), 
	card_value(C1r,Kicker3).
one_pair([C1r,C1s,C2r,C2s,C3r,C3s,C4r,C4s,C5r,C5s], X, SR, Kicker1, Kicker2, Kicker3) :-
	C3r = C4r,
	X is 2,
	card_value(C3r,SR),
	card_value(C5r,Kicker1), 
	card_value(C2r,Kicker2), 
	card_value(C1r,Kicker3).
one_pair([C1r,C1s,C2r,C2s,C3r,C3s,C4r,C4s,C5r,C5s], X, SR, Kicker1, Kicker2, Kicker3) :-
	C4r = C5r,
	X is 2,
	card_value(C4r,SR),
	card_value(C3r,Kicker1), 
	card_value(C2r,Kicker2), 
	card_value(C1r,Kicker3).

high_card([C1, _, C2, _, C3, _, C4, _, C5, _], 1, Kicker5, Kicker4, Kicker3, Kicker2, Kicker1) :-
    card_value(C1,Kicker1),
    card_value(C2,Kicker2),
    card_value(C3,Kicker3),
    card_value(C4,Kicker4),
    card_value(C5,Kicker5).


card_value('2',1).
card_value('3',2).
card_value('4',3).
card_value('5',4).
card_value('6',5).
card_value('7',6).
card_value('8',7).
card_value('9',8).
card_value('T',9).
card_value('J',10).
card_value('Q',11).
card_value('K',12).
card_value('A',13).
	
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	royal_flush(Hand, X),
	Rank is X, SubRank is 0, Kicker1 is 0, Kicker2 is 0, Kicker3 is 0, Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	straight_flush(Hand, X, SR),
	Rank is X, SubRank is SR, Kicker1 is 0, Kicker2 is 0, Kicker3 is 0,
Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	four_of_a_kind(Hand, X, SR, Kicker1),
	Rank is X, SubRank is SR, Kicker2 is 0, Kicker3 is 0, Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	full_house(Hand, X, SR, Kicker),
	Rank is X, SubRank is SR, Kicker1 is Kicker, Kicker2 is 0, Kicker3 is 0,
Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	flush(Hand, X, SR),
	Rank is X, SubRank is SR, Kicker1 is 0, Kicker2 is 0, Kicker3 is 0,
Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	straight(Hand, X, SR),
	Rank is X, SubRank is SR, Kicker1 is 0, Kicker2 is 0, Kicker3 is 0,
Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	three_of_a_kind(Hand, X, SR, Kicker1, Kicker2),
	Rank is X, SubRank is SR, Kicker3 is 0, Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	two_pairs(Hand, X, SR, Kicker1, Kicker2),
	Rank is X, SubRank is SR, Kicker3 is 0, Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	one_pair(Hand, X, SR, Kicker1, Kicker2,Kicker3),
	Rank is X, SubRank is SR, Kicker4 is 0,!.
hand(Hand,Rank,SubRank,Kicker1,Kicker2,Kicker3, Kicker4) :-
	high_card(Hand, X, SR, Kicker1, Kicker2, Kicker3, Kicker4),
	Rank is X, SubRank is SR,!.
	
is_winner(MyHand,OtherHand,X) :- 
	hand(MyHand, RankA, SubRankA, Kicker1A, Kicker2A, Kicker3A, Kicker4A),
	hand(OtherHand, RankB, SubRankB, Kicker1B, Kicker2B, Kicker3B, Kicker4B),
	RankA > RankB,
	X is 1,!.
is_winner(MyHand,OtherHand,X) :- 
	hand(MyHand, RankA, SubRankA, Kicker1A, Kicker2A, Kicker3A, Kicker4A),
	hand(OtherHand, RankB, SubRankB, Kicker1B, Kicker2B, Kicker3B, Kicker4B),
	RankA = RankB,
	SubRankA > SubRankB,
	X is 1,!.
is_winner(MyHand,OtherHand,X) :- 
	hand(MyHand, RankA, SubRankA, Kicker1A, Kicker2A, Kicker3A, Kicker4A),
	hand(OtherHand, RankB, SubRankB, Kicker1B, Kicker2B, Kicker3B, Kicker4B),
	RankA = RankB,
	SubRankA = SubRankB,
	Kicker1A > Kicker1B,
	X is 1,!.
is_winner(MyHand,OtherHand,X) :- 
	hand(MyHand, RankA, SubRankA, Kicker1A, Kicker2A, Kicker3A, Kicker4A),
	hand(OtherHand, RankB, SubRankB, Kicker1B, Kicker2B, Kicker3B, Kicker4B),
	RankA = RankB,
	SubRankA = SubRankB,
	Kicker1A = Kicker1B,
	Kicker2A > Kicker2B,
	X is 1,!.
is_winner(MyHand,OtherHand,X) :- 
	hand(MyHand, RankA, SubRankA, Kicker1A, Kicker2A, Kicker3A, Kicker4A),
	hand(OtherHand, RankB, SubRankB, Kicker1B, Kicker2B, Kicker3B, Kicker4B),
	RankA = RankB,
	SubRankA = SubRankB,
	Kicker1A = Kicker1B,
	Kicker2A = Kicker2B,
	Kicker3A > Kicker3B,
	X is 1,!.
is_winner(MyHand,OtherHand,X) :- 
	hand(MyHand, RankA, SubRankA, Kicker1A, Kicker2A, Kicker3A, Kicker4A),
	hand(OtherHand, RankB, SubRankB, Kicker1B, Kicker2B, Kicker3B, Kicker4B),
	RankA = RankB,
	SubRankA = SubRankB,
	Kicker1A = Kicker1B,
	Kicker2A = Kicker2B,
	Kicker3A = Kicker3B,
	Kicker4A > Kicker4B,
	X is 1,!.
is_winner(MyHand,OtherHand,0) :- !.

solve(X) :-
	solve(X,1,0),!.
solve(Acc, 1001, Acc).
solve(X,N,Acc) :-
	print_message(informational,N),
	data(Data),
	nth1(N, Data, [Hand1,Hand2]),
	is_winner(Hand1,Hand2,IsWinner),
	NN is N+1,
	AccN is Acc + IsWinner,
	solve(X, NN, AccN), !.

main :-
	phrase_from_file(lines(Ls), 'p054_poker.txt'),
	parseLines(Ls,ParsedLines),
	hands(ParsedLines,Hands),
	assert(data(Hands)), !.

