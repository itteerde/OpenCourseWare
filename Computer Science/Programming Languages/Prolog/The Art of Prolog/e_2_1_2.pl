mother_in_law(MotherInLaw, Husband):-
  married_couple(Wife, Husband),
  mother(MotherInLaw, Wife).
mother_in_law(MotherInLaw, Wife):-
  married_couple(Wife, Husband),
  mother(MotherInLaw, Husband).

brother_in_law(BrotherInLaw, Husband):- % Wife's brother
  married_couple(Wife, Husband),
  brother(BrotherInLaw, Wife).
brother_in_law(BrotherInLaw, Wife):-    % Husband's brother
  married_couple(Wife, Husband),
  brother(BrotherInLaw, Husband).
brother_in_law(BrotherInLaw, Person):-  % Sister's husband
  sister(Sister, Person),
  married_couple(Sister, BrotherInLaw).

son_in_law(SonInLaw, Parent):-
  married_couple(Daughter, SonInLaw),
  parent(Parent, Daughter).