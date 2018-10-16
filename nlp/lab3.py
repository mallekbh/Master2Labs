# Mohamed A. Ben Hamida
# PYTHON -- fichiers et tokenisation
# 2018/2019

import nltk, re, os
from nltk import defaultdict
from urllib.request import urlopen
from bs4 import BeautifulSoup as bs
from nltk.corpus import gutenburg as guten

#1 -----------------------------------------------------

file = open("test.txt","r")
lines = file.readlines()

for line in lines[::-1]:
	print(line)


#2 -----------------------------------------------------

wh_words = ["who","why","where","when","what"]

words = guten.words("austen-emma.txt")
for word in words:
	if(word.startsWith("wh")):
		print(word)

for word in words:
	if word in wh_words:
		print(word)

#3 -----------------------------------------------------

page = urlopen("http://www.usthb.dz/").read().decode("utf-8")
	# ---------------------------
	bsPage = bs(page)
	bsText = bs.get_text(bsPage)
	# ---------------------------
removeStyle = r"<(script|style)(.|\n)*?>(.|\n)*?</(script|style)>"
removeMeta = r"<(meta|link)(.|\n)*?/>"
removeOpenTags = r"<.*?>"
removeCloseTags = r"<[/].*?>"
