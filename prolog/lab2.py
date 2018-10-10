# Mohamed A. Ben Hamida
# regular expresison lab
# 2018/2019

#1

#1#a
reg = r"\b(a|an|the)\b"
sentence = "the engeneer stoped the hacker from damaging the system with a virus after an attack"
re.findall(reg,sentence)
	# output => ['the', 'the', 'the', 'a', 'an'] 

#1#b
reel = r"\d+(?:[.]\d+)?"
exp = reel+"([-*/+]"+reel+")*|[(]"+reel+"[-*/+]"+reel+"[)]"
reg = exp+"[-*/+]"+exp
sentence = "hello world (0.22+254)+98 4.0+25+21.4 (78+14/98) (87*45)"
nltk.re_show(reg,sentence)
	#ouput => hello world {(0.22+254)+98} {4.0+25+21.4} ({78+14/98}) {(87*45)}	

#2

#2#a
reg = "<.+[.]txt:"
reg = "<(.)+?@(.)+?>"
sent = "<austen-emma.txt:hart@vmd.cso.uiuc.edu> (internet) <hart@uiucvmd> (bitnet)austen-emma.txt:Internet <72600.2026@compuserve.com>; TEL: (212-254-5093)austen-persuasion.txt:Editing by Martin Ward <Martin.Ward@uk.ac.durham>blake-songs.txt:Prepared by David Price, email <ccx074@coventry.ac.uk>"
"<.+>"

""
#2#b

reg = "<([a-zA-Z0-9-]+[.]txt:)?([a-zA-Z0-9-]@[a-zA-Z0-9.]+)>"
reg = "([^<]+?)@([^>]+?)"
	#b
re.findall(r"([^<]+)@([^>]+)", s)

	#c
re.findall(r"<(.+?)@(.+?)>", s)