# Mohamed A. Ben Hamida
# PYTHON -- introduction to regular expressions
# 2018/2019

#1 -----------------------------------------------------

sentence1 = "the goalkeeper stoped the apponent from scoring a goal after an attack"
sentence2 = "5432+55 (12.5*870.2)+0.22"
#1#a
pat = r"\b(a|an|the)\b"
nltk.re_show(pat,sentence)
#1#b
reel = r"\d+([.]\d+)?"
exp = reel+"([-*/+]"+reel+")?|([(]"+reel+"[-*/+]"+reel+"[)])"
pat = exp+"([-*/+]"+exp+")?"

#2 -----------------------------------------------------

sentence = "<austen-emma.txt:hart@vmd.cso.uiuc.edu> (internet) <hart@uiucvmd> (bitnet) austen-emma.txt:Internet <72600.2026@compuserve.com>; TEL: (212-254-5093) austen-persuasion.txt:Editing by Martin Ward <Martin.Ward@uk.ac.durham> blake-songs.txt:Prepared by David Price, email <ccx074@coventry.ac.uk>"
#2#a
pat = r"<(.+)@(.+)>"
#2#b
pat = r"(.[a-zA-Z0-9]+)@(.[a-zA-Z0-9]+)" #pat = r"([^<])+?@([^>])+"
#2#c
pat = r"<(.+?)@(.+?)>"
