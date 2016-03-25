#include <opencv2/opencv.hpp>
#include <cv.h>
#include <highgui.h>
#include "iostream"
#include <stdio.h>


using namespace cv;

//aboslute difference method
void absoluteDifference() {
	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/src1.bmp");
	Mat src2 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/src2.bmp");
	Mat res(src1.rows, src1.cols, CV_8UC3);
	for (int i  = 0; i  < src1.rows; i++) {
		for (int j = 0; j < src1.cols*3;j++) {
			res.at<uchar>(i,j) = abs((int)src1.at<uchar>(i,j) - (int)src2.at<uchar>(i,j));
		}
	}
	imshow("res",res);
}

//blending by factor 1:7 method
void blendingOne() {
	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/coca.jpg");
	Mat src2 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/drops.jpg");
	Mat res(src1.rows, src1.cols, CV_8UC3);
	for (int i  = 0; i  < src1.rows; i++) {
		for (int j = 0; j < src1.cols*3;j++) {
			res.at<uchar>(i,j) = int ((int)src1.at<uchar>(i,j) * 0.1) +
					int((int)src2.at<uchar>(i,j) * 0.9);
		}
	}
	imshow("res",res);
}

//blending by factor 3:4 method
void blendingTwo() {
	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/coca.jpg");
	Mat src2 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/drops.jpg");
	Mat res(src1.rows, src1.cols, CV_8UC3);
	for (int i  = 0; i  < src1.rows; i++) {
		for (int j = 0; j < src1.cols*3;j++) {
			res.at<uchar>(i,j) = int ((int)src1.at<uchar>(i,j) * 0.8) +
					int((int)src2.at<uchar>(i,j) * 0.2);
		}
	}
	imshow("res",res);
}

//adding by 50 method
void addingConstant() {
	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/image2.bmp");
	Mat res(src1.rows,src1.cols,CV_8UC3);
	//Mat res = src1.clone();
	//printf("%d",res.rows);
	for (int i  = 0; i  < res.rows; i++) {
			for (int j = 0; j < res.cols*3;j++) {
				res.at<uchar>(i,j) = (int)src1.at<uchar>(i,j) + 50;
			}
		}
		imshow("res",res);
}

//shift left by 1 bit method
void shiftLeft() {
	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/image2.bmp");
	Mat res(src1.rows,src1.cols,CV_8UC3);
	//Mat res = src1.clone();
	//printf("%d",res.rows);
	for (int i  = 0; i  < res.rows; i++) {
			for (int j = 0; j < res.cols*3;j++) {
				res.at<uchar>(i,j) = (int)src1.at<uchar>(i,j) << 1;
			}
		}
		imshow("res",res);
}

// upsampling by 2 method
void upSampling() {
	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/image3.jpg");
    Mat upMatrix = (Mat_<float>(2,2) << 0.5, 0.0, 0.0, 0.5);
    Mat srcPoint = (Mat_<float>(2,1) << 0.0,0.0);
    Mat resPoint = (Mat_<float>(2,1) << 0.0,0.0);
	Mat res(src1.rows*2, src1.cols*2, CV_8U);
	for (int i  = 0; i  < res.rows; i++) {
		for (int j = 0; j < res.cols ; j++) {
			srcPoint.at<uint>(0,0) = i;
			srcPoint.at<uint>(1,0) = j;
			resPoint = upMatrix*srcPoint;
			res.at<uint>(i,j) = src1.at<uint>((int)resPoint.at<uint>(0,0),(int)resPoint.at<uint>(1,0));
		}
	}
	imshow("res",res);
}

//subsampling by 2 method
void subSampling() {
	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/image3.jpg");
	Mat subMatrix = (Mat_<float>(2,2) << 2.0, 0.0, 0.0, 2,0);
	Mat srcPoint = (Mat_<float>(2,1) << 0.0,0.0);
	Mat resPoint = (Mat_<float>(2,1) << 0.0,0.0);
	Mat res(src1.rows/2, src1.cols/2, src1.type());
		for (int i  = 0; i  < res.rows; i++) {
			for (int j = 0; j < res.cols/2 + 32; j++) {
				srcPoint.at<uint>(0,0) = i;
				srcPoint.at<uint>(1,0) = j;
				resPoint = subMatrix*srcPoint;
				res.at<uint>(i,j) = src1.at<uint>((int)resPoint.at<uint>(0,0),(int)resPoint.at<uint>(1,0));
			}
		}
		imshow("res",res);
}

// flipping method
void flipping() {
	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment I/image3.jpg");
	Mat res(src1.rows, src1.cols, CV_8UC3);
	for (int i  = 0; i  < src1.rows; i++) {
		for (int j = 0; j < src1.cols;j++) {
			res.at<uint>(src1.rows - 1 -i,j) = src1.at<uint>(i,j);
		}
	}
	imshow("res",res);
}

/*int main(int argc, char** argv) {
	//addingConstant();
	//absoluteDifference();
	//blendingOne();
	//blendingTwo();
	//upSampling();
	//flipping();
	subSampling();
	shiftLeft();
	waitKey(100000);
	return 0;
}*/


