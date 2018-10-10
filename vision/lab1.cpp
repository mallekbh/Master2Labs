#include "stdafx.h"
#include <opencv2\core.hpp>
#include <opencv2\highgui\highgui.hpp>

// exo1

/*
using namespace cv;

int _tmain(int argc, _TCHAR* argv[]) {

	Mat image2 = Mat(500, 500, CV_64FC1);

	for (int i = 0; i < image2.rows; i++)
	{
		for (int i = 0; i < image2.cols; i++)
		{
			image2.at<double>(i,j) = (i + j +1.) / (image2.rows + image2.cols);
		}
	}

	namedWindow("afficher image resltat", WINDOW_AUTOSIZE);
	imshow("afficher image resultat", image2);
	waitKey(0);
	return 0;
}
*/

//exo2

int _tmain(int argc, _TCHAR* argv[]) {

	Mat img = imread("path to image");
	if (image.empty()) {
		printf("erreur de chargement");
	}else{
		printf("chargement avec success");
		Mat imgResultat = Mat(iwmg.rows, img.cols, CV_8UC1); // img.clone();
		for (int i=0; i<img.rows; i++) {
			for(int i=0; i<img.cols; i++) {
				imgResultat.at<uchar>(i,j) = 255 - img.at<uchar>(i,j);
			}
		}
	}

	namedWindow("image",WINDOW_AUTOSIZE);
	imshow("image",img);

	namedWindow("image resultat", WINDOW_AUTOSIZE);
	imshow("image resultat",imgResultat);

}



