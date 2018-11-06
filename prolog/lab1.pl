%% % 1/

appartient(X,[X|_]).
appartient(X,[_|L]):-appartient(X,L).

%% testing appartient(X,[1,2,3]). X=1; X=2; X=3;

%% 2/

%% premier(X,[X,_]).
%% dernier(X,[X]):-!.
dernier(X,[_,Y|L]):-dernier(X,[Y,L]).  %testing premier(X,[1,2]). dernier(X,[1,2,3,4]).

%% 3/

%% avant_dernier(X,[X,_]):-!.
%% avant_dernier(X,[_,Y,Z|L]):-avant_dernier(X,[Y,Z|L]).

%% 4/ prédicat avant dernier  

avant_dernier(X,[X,_]):-!.
avant_dernier(X,[_,Z|L]) :- avant_dernier(X,[Z|L]).

%% prédicat de suppression du kéme element d'une liste L1 la le copier dans L2 Prédicat supprimer_k/3

supprimer_k(1,[_|L],L).
supprimer_k(K,[X1,X2|L1],[X1|L2]):-
K2 is K-1,
supprimer_k(K2,[X2|L1],L2).

%% prédicat de calcul de la taille d'une liste

longeur(0,[]):-!.
longeur(K,[_,Y|L]):-
longeur(K2,[Y|L]),
K2 is K-1.

%% concatenation de deux listes
concat([],L,L).
concat([X|L1],L2,[X|L3]):-
concat(L1,L2,L3).

%% test palindrome
palindrome([]).
palindrome([_]).
palindorme([X,Y|L]):-
dernier(Z,[Y|L]),
X==Z,
longeur([Y|L],K),
supprimer_k(K,[Y|L],L2),
palindrome(L2).

%% exo2

homme(ali).
homme(hacene).
homme(hakim).
homme(mohamed).
homme(said).
homme(samir).

femme(djamila).
femme(fatma).
femme(houria).
femme(linda).
femme(lilia).

pere(mohamed,samir).
pere(samir,lilia).
pere(samir,said).
pere(said,hacene).
pere(said,linda).
pere(hakim,ali).

mere(fatma,samir).
mere(houria,lilia).
mere(houria,said).
mere(lilia,ali).
mere(djamila,hacene).
mere(djamila,linda).

parent(X,Y):-
pere(X,Y).
parent(X,Y):-
mere(X,Y).

fils(X,Y):-
homme(Y),
pere(Y,X).

fils(X,Y):-
homme(X),
mere(Y,X).

fille(X,Y):-
femme(Y),
pere(Y,X).

fille(X,Y):-
femme(X),
mere(Y,X).

grand_pere(X,Y):-
pere(X,Z),
parent(Z,Y).

grand_mere(X,Y):-
mere(X,Z),
parent(Z,Y).

frere(X,Y):-
homme(X),
pere(Z,X),
pere(Z,Y).

soeur(X,Y):-
femme(X),
pere(Z,X),
pere(Z,Y).

oncle(X,Y):-
frere(X,Z),
parent(Z,Y).

tonte(X,Y):-
soeur(X,Z),
parent(Z,Y).

%% es ce que x est homme ?
homme(said).
homme(lilia).

%% es ce que x est femme ?
femme(lilia).
femme(houria).

%% qui sont les fils de said ?
pere(said,X).
pere(houria,X).  

%%fusion de deux listes

%
fusion([],L,L).
fusion(L,[],L).
fusion([X|L1],[Y|L2],[X|L3]):-X<Y,!,fusion(L1,[Y|L2],L3).
fusion(L1,[X|L2],[X|L3]):-fusion(L1,L2,L3).

% supprimer les occurences d'une liste

supptoutes(_,[],[]).
supptoutes(X,[X|L1],L2):-
!,
supptoutes(X,L1,L2).
supptoutes(X,[Y|L1],[Y|L2]):-supptoutes(X,L1,L2).


suppocc([],[]).
suppocc([X|L1],[X|L2]):-
supptoutes(X,L1,L3),
suppocc(L3,L2).











