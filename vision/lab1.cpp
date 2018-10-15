// ben hamida mohamed amine
// lab intro to opencv


#include <opencv2/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>

// exo1


using namespace cv;

int main(int argc, char** argv) {

	Mat image2 = Mat(500, 500, CV_64FC1);

	for (int i = 0; i < image2.rows; i++)
	{
		for (int j = 0; j < image2.cols; j++)
		{
			image2.at<double>(i,j) = (i + j +1.) / (image2.rows + image2.cols);
		}
	}

	namedWindow("afficher image resltat", WINDOW_AUTOSIZE);
	imshow("afficher image resultat", image2);
	waitKey(0);
	return 0;
}


//exo2

//using namespace cv;
//
//int _tmain(int argc, char* argv[]) {
//
//    Mat img = imread("path to image");
//    if (img.empty()) {
//        printf("erreur de chargement");
//    }else{
//        printf("chargement avec success");
//        Mat imgResultat = Mat(img.rows, img.cols, CV_8UC1); // img.clone();
//        for (int i=0; i<img.rows; i++) {
//            for(int j=0; i<img.cols; i++) {
//                imgResultat.at<uchar>(i,j) = 255 - img.at<uchar>(i,j);
//            }
//        }
//    }
//
//    namedWindow("image",WINDOW_AUTOSIZE);
//    imshow("image",img);
//
//    namedWindow("image resultat", WINDOW_AUTOSIZE);
//    imshow("image resultat",imgResultat);

