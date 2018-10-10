#!/home/malek/miniconda3/bin/python

#0
for x in range(1,5):
	f = open("D"+x+".txt","r")
	f.write("Sunglasses assault order-flow math-urban San Francisco papier-mache modem. Wristwatch cartel narrative disposable corrupted construct sprawl 8-bit network. Sunglasses math-corporation refrigerator semiotics disposable stimulate garage car pistol tanto bicycle systemic sub-orbital-ware papier-mache film. 3D-printed weathered knife pre-market chrome decay smart-digital neural-space office corrupted human.")
	f.close()

#1

D1 , D2 , D3 , D4 , D5 = defaultdict(int)

stopList = r"[^\w]"

for i in range(1,5):
	f = open("D"+str(i)+".txt","r")	
	s = f.read()
	re.sub(stopList," ",s)
	words = s.split()

#2








