#include <opencv2/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <opencv2/imgproc.hpp>
#include <algorithm> //pour utiliser la fonction std::sort
using namespace cv;
using namespace std;

int lire_frame() {
    //la capture video ici un fichier
    //VideoCapture cap("webcam.avi");
    //la capture video ici une webcam
    VideoCapture cap;
    if(!cap.open(0)) {
        return 0;
    }
    namedWindow("camera",WINDOW_AUTOSIZE);
    for (; ;) {
        Mat frame;
        cap >> frame; // pour recuperer la frame et la convertir en Mat (et la décompresser)
        if(frame.empty()) break; // end of video stream
        imshow("camera",frame);
        if(waitKey(10) == 27) break;
    }
    cap.release();
    return 0;
}

int ecrire_frame() {
    //la capture video ici une webcam
    VideoCapture cap;
    if(!cap.open(0)) return 0;

    namedWindow("camera",WINDOW_AUTOSIZE);
    int frame_width = cap.get(CAP_PROP_FRAME_WIDTH);
    int frame_hieght = cap.get(CAP_PROP_FRAME_HEIGHT);

    VideoWriter video("outcpp.avi",VideoWriter::fourcc('M','J','P','G'),10,Size(frame_width,frame_hieght));

    for(;;) {
        Mat frame;

        cap >> frame;
        if(frame.empty()) break;
        video.write(frame);
        if(waitKey(50) == 27) break;
        imshow("camera",frame);
    }
    cap.release();
    video.release();
    return 0;
}


int main(int argc, char** argv) {
    //ecrire_frame();
    lire_frame();
}
