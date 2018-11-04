#/home/malek/miniconda3/bin/python3

#---------------------------------#
# Mohamed A. Ben Hamida           #
# 21-10-2018                      #
# RI project class Index          #
#---------------------------------#

import nltk
import re
import os

def readDir(path):
	return os.listdir(path)

def readFile(path):
	f = open(path,"r")
	text = f.readlines()
	f.close()
	return text

def removePonctuation(text):
	pat = r"[,;.:!?_-]+?"
	return re.sub(pat," ",text)

def tokenize(text):
	return nltk.word_tokenize(text)

def removeStopWords(tokens):
	#return [word for word in tokens if word not in nltk.corpus.stopwords.words("french")]
	file = open("stopWords.txt")
	stopWordsText = file.read()
	pat = r"\s?(.+?)[,]"
	words = re.findall(pat,stopWordsText)
	return [token for token in tokens if token not in words]


