This is a java program used to get the first and the follow sets of a grammar, and to left factor a grammar. 
This project is done during the compilers course

Here is the project Description.
In this task you are required to implement additional algorithms based on what you did in the previous
task for parsing a given grammar. You will be implementing two of the generic algorithms discussed in
the lectures. The first one is Left Factoring of a grammar. The second on is to compute the first and
follow sets for a given grammar.
• LeftFactoring: Where you have the following in and out sample files:
a) Sample.in
b) Sample2.in
c) Sample3.in
d) Sample.out
e) Sample2.out
f) Sample3.out
In the input files above assume that the format of the input the same as the last task. Where the
head of the rule is given in the first line following the body of the rule in the next line. The epsilon
symbol is refered to using the ! character.
• FirstAndFollow: Where you will find the following in and out sample files:
a) Sample4.in
b) Sample5.in
c) Sample4.out
d) Sample5.out
In the input files above assume that the first line contains the list of Non-terminals in the grammar,
while in the second line the list of terminals are given. The rest of the grammar is given with the
same format as before.


Here is sample.in for the left factoring.
S
aSbS|aS|!
---------------------------------------------
and sample.out for the left factoring.
S->[aSS', !]
S'->[bS, !]

----------------------------------------------------------------------------------------------------
Here is sample.in for the firstFollow
E,E',T,T',F
(,),id,number,+,*
E
TE'
T
FT'
F
(E)|id|number
E'
+TE'|!
T'
*FT'|!

and sample.out for the left firstFollow.
First(+): [+]
First(*): [*]
First((): [(]
First(id): [id]
First()): [)]
First(number): [number]
First(E): [(, id, number]
First(E'): [+, !]
First(T): [(, id, number]
First(T'): [*, !]
First(F): [(, id, number]
Follow(E): [$, )]
Follow(E'): [$, )]
Follow(T): [+, $, )]
Follow(T'): [+, $, )]
Follow(F): [*, +, $, )]


