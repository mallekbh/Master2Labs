#include <opencv2/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <opencv2/imgproc.hpp>
#include <algorithm> //pour utiliser la fonction std::sort
using namespace cv;
using namespace std;


void filterMoyenNVG(Mat src,Mat dst, int voisinage) {
    if(src.channels() != 1 || dst.channels() != 1) return;
    if(src.rows != dst.rows || src.cols != dst.cols) return;
    if(voisinage%2 != 1) return;
    Rect rec_roi = Rect(0,0,voisinage,voisinage);
    int moyenne = 0;
    for(int x=0;x<src.rows;x++) {
        for(int y=0; y<src.cols;y++) {
            if(x< (voisinage-1)/ 2 || x>(src.rows -1) || y<(voisinage-1)/2 || y>(src.cols -1 )) {
                dst.at<uchar>(x,y) = src.at<uchar>(x,y);
            }else{
                moyenne = 0;
                rec_roi.y = x-(voisinage-1)/2;
                rec_roi.x = y-(voisinage-1)/2;
                Mat img_roi = src(rec_roi);
                for(int i=0;i<voisinage;i++) {
                    for(int j=0;j<voisinage;j++) {
                        moyenne+=img_roi.at<uchar>(i,j);
                    }
                }
                moyenne /= voisinage*voisinage;
                dst.at<uchar>(x,y) = moyenne;
            }
        }
    }
}

void filterMedianNVG(Mat src,Mat dst,int voisinage) {
    if(src.channels() != 1 || dst.channels() != 1) return;
    if(src.rows != dst.rows || src.cols != dst.cols) return;
    if(voisinage%2 != 1) return;
    Rect rec_roi = Rect(0,0,voisinage,voisinage);
    int *voisins = new int[voisinage*voisinage];
    int median = 0;
    for(int x=0;x<src.rows;x++) {
        for(int y=0; y<src.cols;y++) {
            if(x< (voisinage-1)/ 2 || x>(src.rows -1) || y<(voisinage-1)/2 || y>(src.cols -1 )) {
                dst.at<uchar>(x,y) = src.at<uchar>(x,y);
            }else{
                median = 0;
                rec_roi.y = x-(voisinage-1)/2;
                rec_roi.x = y-(voisinage-1)/2;
                Mat img_roi = src(rec_roi);
                for(int i=0;i<voisinage;i++) {
                    for(int j=0;j<voisinage;j++) {
                        voisins[i*voisinage+j] = img_roi.at<uchar>(i,j);
                    }
                }
                sort(voisins,voisins + (voisinage*voisinage));
                median = voisins[((voisinage*voisinage)-1)/2];
                dst.at<uchar>(x,y) = median;
            }
        }
    }
}
int main(int argc, char** argv) {


    Mat img = imread("a.jpg", IMREAD_GRAYSCALE);
    /*
    Rect mon_rectangle = Rect(57,46,200,100);
    Mat img_ROI= img(mon_rectangle);
    rectangle(img, mon_rectangle,Scalar(0),1,8,0);
    */

    if(img.empty()) {
     printf("img emty");
    }

    namedWindow("img",WINDOW_AUTOSIZE);
    imshow("img",img);
    //namedWindow("img",WINDOW_AUTOSIZE);
    //imshow("img",img_ROI);
    waitKey();
    return 0;


}

