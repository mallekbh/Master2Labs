#!/home/malek/miniconda3/bin/python

#0 -----------------------------------------------------

# for x in range(1,5):
# 	f = open("D"+x+".txt","w")
# 	f.write("Sunglasses assault order-flow math-urban San Francisco papier-mache modem. Wristwatch cartel narrative disposable corrupted construct sprawl 8-bit network. Sunglasses math-corporation refrigerator semiotics disposable stimulate garage car pistol tanto bicycle systemic sub-orbital-ware papier-mache film. 3D-printed weathered knife pre-market chrome decay smart-digital neural-space office corrupted human.")
# 	f.close()

#1 -----------------------------------------------------
	#1
def fileClean(file):
    ponct,ponct_rep = (r"[,;.:'""-_]"," ")
    stop,stop_rep = (r"\b(the|other|and|or|a|an)\b"," ")
    text = file.read()
    text = re.sub(ponct,ponct_rep,text)
    text = re.sub(stop,stop_rep,text)
    file.close()
    return text.split()

def fileFreq(file):
	freq = defaultdict(int)
	for x in file:
		freq[x] += 1
	return freq

pat = re.compile("D\d\.txt")
docs = []

for doc in os.listdir():
	if pat.match(doc):
		docs.append(doc)

freqs = [fileFreq(fileClean(opendoc))]
freqIndex = {doc:fileFreq(fileClean(open(doc,"r"))) for doc in docs}

	#2
for x in freqIndex.values():
	terms = terms+list(x.keys())

terms = set(terms)

invertedIndex = {}
for term in terms:
    invertedIndex[term] = {doc:freqIndex[doc][term] for doc in freqIndex.keys()}

#2 -----------------------------------------------------

def searchBydoc(doc,invertedIndex):
	#return [(term,invertedIndex[term][doc]) for term in invertedIndex.keys()]
	l = []
	for term in invertedIndex.keys():
		if(invertedIndex[term][doc]>0):
			l.append((term,invertedIndex[term][doc]))
	return l


def serachByTerm(term,invertedIndex):
	return invertedIndex[term]

#3 -----------------------------------------------------

def createTfIdfindex(freqIndex, invertedIndex):
	tfIdfindex = copy(freqIndex)
	for doc in tfIdfindex:
		for term in tfIdfindex[doc].keys():
			tfIdfindex[doc][term] = (freqIndex[doc][term])/ max(freqIndex[doc].values()) * log((len(freqIndex)/len(invertedIndex[term]))+1)
	return tfIdfindex
 

freqIndex["D1.txt"]["digital"]/ (max(freqIndex["D1.txt"].values()) * log(len(freqIndex)/len(invertedIndex["digital"]))+1)



