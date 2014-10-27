breadth(Moves):-
  initial_state(Position),
  empty_queue(Queue0),
  enqueue(b(Position, []), Queue0, Queue),
  breadth_first(Queue, [], Moves).

breadth_first(Queue, _, FinalMoves):-
  dequeue(b(Position, Path), Queue, _),
  final_state(Position),
  reverse(Path, [], FinalMoves).
breadth_first(Queue0, History, FinalMoves):-
  dequeue(b(Position, Path), Queue0, Queue1),
  findall(Move, move(Position, Move), Moves),
  filter(Moves, Position, Path, History, Queue1, Queue),
  breadth_first(Queue, [Position|History], FinalMoves).

filter([], _, _, _, Queue, Queue).
filter([Move|Moves], Position, Path, History, Queue0, Queue):-
  update(Position, Move, Position1),
  legal(Position1),
  not(member(Position1, History)),
  !,
  enqueue(b(Position1, [Move|Path]), Queue0, Queue1),
  filter(Moves, Position, Path, History, Queue1, Queue).
filter([_|Moves], Position, Path, History, Queue0, Queue):-
  filter(Moves, Position, Path, History, Queue0, Queue).

empty_queue(q(zero, Ys, Ys)).  

enqueue(X, q(N, Ys, [X|Zs]), q(s(N), Ys, Zs)).

dequeue(X, q(s(N), [X|Ys], Zs), q(N, Ys, Zs)).