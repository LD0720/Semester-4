
main:-write('Welcome to Pro-Wordle!'),nl,write('----------------------'),build_kb,nl,play.
build_kb:-nl,write('Please enter a word and its category in seperate lines:'),nl,read(X),nl,
(X\=done,read(Y),assert(word(X,Y)),build_kb);(X=done, write('Done building the words database...')).

category(L):-
setof(C,is_category(C),L).

is_category(C):-
word(_,C).


correct_letters(L,L2,CL):-
helper(L,L2,CL).

helper([],_,[]).
helper([H|T1],L2,CL):-
member(H,L2),delete(L2,H,LR),CL=[H|TR],helper(T1,LR,TR),!.

helper([H|T1],L2,CL):-
\+member(H,L2),helper(T1,L2,CL).

pick_word(W,L,C):-
word(W,C),string_length(W,L).

 
correct_positions([],_,[]).
correct_positions([H1|T1],[H2|T2],PL):-
H1=H2,PL=[H1|TR],
correct_positions(T1,T2,TR).

correct_positions([H1|T1],[H2|T2],PL):-
H1\=H2,correct_positions(T1,T2,PL).

check_length(Word1,Int):-
atom_chars(Word1,L),length(L,Int).

number_guesses(0,0).
number_guesses(G,G1):-
G1 is G-1.

available_length(L):-
word(W,_),atom_chars(W,Y),length1(Y,L).

length1([],0).
length1([_|T],L):- length1(T,Prev),L is Prev+1.

lenloop(C,Y,R):-(available_length(Y),((pick_word(W,Y,C), R is Y);(\+pick_word(W,Y,C),
write('There are no words of this length.'),nl, write('Choose a length:'),nl,
read(F),lenloop(C,F,R))));(\+available_length(Y),write('There are no words of this length.'),nl,write('Choose a length:'),nl,
read(F),lenloop(C,F,R)).

loop(_,_,0):-!,write('You lost!').
loop(Y,Match,G):- write('Enter a word composed of '),write(Y),write(' letters:'),nl, read(Word1),
((check_length(Word1,Y),atom_chars(Match,L1),atom_chars(Word1,L2),correct_letters(L1,L2,CL),
correct_positions(L1,L2,LR),((length(LR,Y),nl,!,write('You win!'));(nl,write('Correct letters are: '),write(CL),
nl,write('Correct letters in correct positions are: '),write(LR),nl,
write('Remaining Guesses are '),number_guesses(G,G1),write(G1),nl,loop(Y,Match,G1))));
(\+check_length(Word1,Y),nl,
write('Word is not composed of '),write(Y),write(' letters. Try again.'),nl,write('Remaining Guesses are ')
,write(G),nl, loop(Y,Match,G))).


play:-write('The available categories are: '),category(L),write(L),nl,write('Choose a category: '),nl,
read(X),(( is_category(X), nl, write('choose a length: '),nl,read(Y), nl,lenloop(X,Y,R),!,nl, 
write('Game started. You have '), G is R+1, write(G), write(' guesses.'),nl,pick_word(Match,R,X),nl,!,
loop(R,Match,G));(\+is_category(X),write('This category does not exist.'),playn)).

playn:-nl,write('Choose a category: '),nl,read(X),((is_category(X),playnn(X));
(\+is_category(X),write('This category does not exist.'),playn)).

playnn(X):-nl,write('choose a length: '),nl,read(Y), nl,lenloop(X,Y,R),!,nl, 
write('Game started. You have '), G is R+1, write(G), write(' guesses.'),nl,pick_word(Match,R,X),nl,!,
loop(R,Match,G).

