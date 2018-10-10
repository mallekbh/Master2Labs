# Mohamed A. Ben Hamida
# PYTHON -- introduction to python and nltk
# 2018/2019


#3
silly = "newly formed bland ideas are inexpressible in an infuriating way"
listeSilly = silly.split(" ")

listeSilly.sort()
for x in listeSilly:
	print(x)

#4
	#4#a
	silly = "newly formed bland ideas are inexpressible in an infuriating way"
	slicedSilly = silly[:silly.index(" in ")]
	#4#b
	for x in listeSilly:
	    if(listeSilly.index(x) < listeSilly.index('in')):
	        slicedSilly+=" "+x

#5
	#5#a
		for x in sentence.split(" "):
			if(x.startsWith("sh")):
				print(x)
	#5#b
		for x in sentence.split(" "):
			if(len(x) > 4):
				print(x)
	#5#c
		string = ""
		for x in sentence.split(" "):
			if(x.startsWith("se")):
				string+="like "+x+" "

#6
def deleteVoy(text):
	voy = ['a','e','i','o','y','u']
	string = ""
	for x in text:
		if x not in voy:
			string+=x
	return string

#7

#8

