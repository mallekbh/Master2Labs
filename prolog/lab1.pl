%% % 1/

appartient(X,[X|_]).
appartient(X,[_|L]):-appartient(X,L).

%% testing appartient(X,[1,2,3]). X=1; X=2; X=3;

%% 2/

%% premier(X,[X,_]).
%% dernier(X,[X]):-!.
%% dernier(X,[_,Y|L]):-dernier(X,[Y,L]).  %testing premier(X,[1,2]). dernier(X,[1,2,3,4]).

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






















