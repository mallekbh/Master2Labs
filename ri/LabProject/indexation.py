#/home/malek/miniconda3/bin/python3

#-----------------------------#
# Mohamed A. Ben Hamida       #
# 28-10-2018                  #
# RI project class indexation #
#-----------------------------#

from helpers import *
from nltk import FreqDist
from math import log

class indexation:

	def __init__(self,path):
		self.path = path
		self.documents = readDir(path)
		self.invertedIndex = {}
		self.tfIdfIndex = {}

	def setFiles(self,path):
		self.path = path
		self.documents = readDir(path)

	def buildInvertedIndex(self):
		for doc in self.documents:
			path = self.path+"/"+doc
			text = open(path,"r").read()
			self.tokens = removeStopWords(tokenize(removePonctuation(text)))
			index = list(FreqDist(self.tokens).items())
			for item in index:
				self.invertedIndex[(doc,item[0])] = item[1]

	def buildTfIdfIndex(self):
		for key in self.invertedIndex.keys():
			self.tfIdfIndex[key] = (self.invertedIndex.get(key)/self.maxFreq(key[0]))*log((len(self.documents)/self.numberDocsWithTerm(key[1]))+1)

	def maxFreq(self,key):
		max = 0
		for item in self.invertedIndex.items():
			if(item[0][0] == key):
				if max < item[1]:
					max = item[1]
		return max

	def numberDocsWithTerm(self,term):
		count = 0
		for doc in self.documents:
			if self.invertedIndex.get((doc,term)) and self.invertedIndex.get((doc,term)) > 0:
				count+=1
		return count	

	def searchByDoc(self,doc):
		return {key[1]:(self.invertedIndex[key],self.tfIdfIndex[key]) for key in self.invertedIndex.keys() if key[0] == doc}
		
	def searchByTerm(self,term):
		return {key[0]:(self.invertedIndex[key],self.tfIdfIndex[key]) for key in self.invertedIndex.keys() if key[1] == term}