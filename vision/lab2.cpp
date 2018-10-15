#include <opencv2/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>

using namespace cv;

void etirerHistogrammeNVG(Mat src){
    int p_min = 255;
    int p_max = 0;

    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            if(src.at<uchar>(i,j) < p_min) {
                p_min = src.at<uchar>(i,j);
            }
            if(src.at<uchar>(i,j) > p_max ) {
                p_max = src.at<uchar>(i,j);
            }
        }
    }

    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            src.at<uchar>(i,j) = (255 * (src.at<uchar>(i,j) - p_min))/(p_max - p_min);
        }
    }
}

int main(int argc, char** argv) {

    Mat src;

    src = Mat(256,256,CV_8UC1);

    for (int i = 0; i < src.rows; i++) {
        for (int j = 0; j < src.cols; j++) {
            src.at<uchar>(i,j) = (((i+j)*255/(src.rows+src.cols+1))/2.+64);
            if((i == 0 && j == 0 ) || (i == 255 && j == 255)) {
                printf(">>%d\n",src.at<uchar>(i,j));
            }
        }
    }

    if(!src.data){
        return -1;
    }
        imwrite("~/Labs/vision/new_img.png", src);

    namedWindow("avant",WINDOW_FULLSCREEN);
    imshow("image before",src);

    Mat after = src.clone();
    etirerHistogrammeNVG(after);
    namedWindow("after",WINDOW_FULLSCREEN);
    //imshow("image after",after);

    waitKey(0);
    return 0;


}
