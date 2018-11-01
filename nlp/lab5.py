# Mohamed A. Ben Hamida
# PYTHON -- tokenisation stemming and list comprehension
# 2018/2019

import nltk
from nltk.corpus import brown
from nltk import FreqDist
from nltk import ConditionalFreqDist
from operator import itemgetter
from nltk.corpus import stopwords
from operator import itemgetter
	
#1 -----------------------------------------------------

fd = FreqDist(brown.words())
[key for (key,freq) in fd.items() if freq>=3]

#2 -----------------------------------------------------

d = [(genre,len(set(brown.words(categories=genre)))*100/len(brown.words(categories=genre))) for genre in brown.categories()]

cfd= ConditionalFreqDist((genre, word) for genre in brown.categories() for word in brown.words(categories=genre))
for genre in brown.categories():
    sum=0
    for typ in cfd[genre]:
        sum+=cfd[genre][typ]
    print("genre "+genre+" :",sum/len(cfd[genre]))

#3 -----------------------------------------------------

text = brown.words("ca01")+brown.words("ca02")
words = [word for word in [word.lower() for word in text] if word not in stopwords.words("english")]
bigrams = list(nltk.bigrams(words))
freqdist = sorted(FreqDist(bigrams).items(),key=itemgetter(1),reverse=True)

#4 -----------------------------------------------------

confreqdist = ConditionalFreqDist((genre,word) for genre in brown.categories() for word in brown.words(categories=genre))
words = ["mountain","monster","river","eat","run","keys","paper","joke","war"]
confreqdist.tabulate(samples=words)

#5 -----------------------------------------------------

def freqOfWord(word, genre):
    fd= FreqDist(brown.words(categories=genre))
    print(word,'in',genre,':',fd[word])

freqOfWord