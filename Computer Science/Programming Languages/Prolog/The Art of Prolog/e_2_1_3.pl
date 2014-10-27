or_gate(Input1, Input2, Output):-
  transistor(Input1, Output, ground),
  resistor(power, Output).
or_gate(Input1, Input2, Output):-
  transistor(Input2, Output, ground),
  resistor(power, Output).

nor_gate(Input1, Input2, Output):-
  or_gate(Input1, Input2, X),
  inverter(X, Output).