% Sagar Sikchi
% 65 (B2)
% Lab 4: Family-Tree in Prolog
%
%
% Defining facts
%

man(ramkumar).
man(om).
man(jayesh).
man(vijay).
woman(savita).
woman(sara).
woman(sunita).
woman(kiran).

boy(ganesh).
boy(sagar).
boy(vikas).
girl(babita).
girl(arya).
girl(sejal).

married(ramkumar, savita).
married(om, sara).
married(jayesh, sunita).
married(vijay, kiran).
parents(ramkumar, savita).
parents(om, sara).
parents(jayesh, sunita).

elder_in_children(ganesh).
younger_in_children(vikas).
surname(sikchi).
three_child(ramkumar, savita).
no_child(vijay, kiran).
four_child(jayesh, sunita).
two_child(om, sara).

child_of(vikas, sunita).
child_of(vikas, jayesh).
child_of(sejal, sunita).
child_of(sejal, jayesh).
child_of(arya, sunita).
child_of(arya, jayesh).
child_of(babita, sunita).
child_of(babita, jayesh).
child_of(sagar, sara).
child_of(sagar, om).
child_of(ganesh, sara).
child_of(ganesh, om).
child_of(vijay, savita).
child_of(vijay, ramkumar).
child_of(jayesh, savita).
child_of(jayesh, ramkumar).
child_of(om, savita).
child_of(om, ramkumar).

have_children(P):- (married(P, Y); married(X, P)), parents(X, Y).
grandfather(G):- child_of(I, P), child_of(P, G), (boy(I); girl(I)), man(G).
grandmother(G):- child_of(I, P), child_of(P, G), (boy(I); girl(I)), woman(G).
head_of_family(H):- grandfather(H).
is_married(M):- (not(boy(M)); not(girl(M))), (man(M); woman(M)).
is_unmarried(U):- (not(man(U)); not(woman(U))), (boy(U); girl(U)).
motherOf(Y, C):- child_of(C, Y), woman(Y).
fatherOf(X, C):- child_of(C, X), man(X).
daughterOf(I, P):- child_of(I, P), (girl(I); woman(I)).
sonOf(I, P):- child_of(I, P), (boy(I); man(I)).

byear(vikas, 2008).
byear(arya, 2004).
byear(sejal, 2002).
byear(sagar, 1999).
byear(babita, 1996).
byear(ganesh, 1995).

in_college(I):- byear(I, BY), BY =< 2003.
in_school(I):- byear(I, BY), BY >= 2004.
in_10th_class(I):- byear(I, BY), BY =:= 2004.
in_12th_class(I):- byear(I, BY), BY =:= 2002.

