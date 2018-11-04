#/home/malek/miniconda3/bin/python3

#---------------------------------#
# Mohamed A. Ben Hamida           #
# 21-10-2018                      #
# RI project - main file          #
#---------------------------------#

from indexation import *
from PyQt5.QtWidgets import QApplication, QWidget
import sys

a = indexation("../collection")
a.buildInvertedIndex()
a.buildTfIdfIndex()

app = QApplication(sys.argv)

w = QWidget()
w.setWindowTitle('Home')
w.show()

sys.exit(app.exec_())