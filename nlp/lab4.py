# Mohamed A. Ben Hamida
# PYTHON -- tokenisation stemming and list comprehension
# 2018/2019

#1 -----------------------------------------------------

def load(file):
	with open(file) as f:
		return f.read()

#a

from nltk.tokenize import regexpTokenizer

pat = 	r'''	
			(?x) #verbose flag
			[,;:.]\s #ponctuation
	    '''

tokenizer = regexpTokenizer(pat)
nltk.regexp_tokenize(text,pat)

#b

days = "([0-2][0-9])|30|31"
months = "(0[1-9])|10|11|12"
years = "[1-9][0-9][0-9][0-9]"

pat1 = r"(("+days+")[/]("+months+")[/]("+years+"))"
pat2 =r"(("+days+")-("+months+")-("+years+"))"



pat =   r'''(?x) 			#verbose flag
			[1-9](\d{,2})([.]\d{3})*?(\d{,3})?([$£]|da) 	#pat for currentcies 
			|'''+pat1+'''|'''+pat2+'''			 		    #pat for dates
			|		 										#pat for person names
			|		 										#pat for firm names
		'''


#2 -----------------------------------------------------

result = [(word,len(word)) for word in ["the","dog","gave","john","the","newspaper"]]

words = ["attribution", "confabulation", "elocution", "sequoia", "tenacious", 'unidirectional']
from nltk.stem.porter import *
from nltk.stem.lancaster import *

porter = PorterStemmer()
lancaster = LancasterStemmer()

Pstemmed = [porter.stem(word) for word in words]
Lstemmed = [lancaster.stem(word) for word in words]

# porter stemmer output ==> ['attribut', 'confabul', 'elocut', 'sequoia', 'tenaci', 'unidirect']
# lancaster stemmer output ==> ['attribut', 'confab', 'elocut', 'sequo', 'ten', 'unidirect']

#3 -----------------------------------------------------

Uw nombre moyen de lettres par mot
Us nombre moyen de mots mar phrase



from nltk.corpus import brown as brown

dict = {}
#-----------------------------
for cat in brown.categories:
	Uw = len("".join(list(brown.words(categories=cat))))/len(brown.words(categories=cat))
	Us = len(brown.words(categories=cat))/len(brown.sents(categories=cat))
	dict[cat] = 4.71*Uw + 0.5*Us - 21.43

#-----------------------------

dict = {cat : (4.71*(len("".join(list(brown.words(categories=cat))))/len(brown.words(categories=cat))) + 0.5*(len(brown.words(categories=cat))/len(brown.sents(categories=cat))) - 21.43) for cat in brown.categories() }

Uw = lambda brown,cat : len("".join(list(brown.words(categories=cat))))/len(brown.words(categories=cat))
Us = lambda brown,cat : len(brown.words(categories=cat))/len(brown.sents(categories=cat))

d = {cat : (4.71*Uw(brown,cat) + 0.5*Us(brown,cat) - 21.43) for cat in brown.categories() }

#4 -----------------------------------------------------

["".join([char for char in word if char in "aeiou"]) for word in words]


















