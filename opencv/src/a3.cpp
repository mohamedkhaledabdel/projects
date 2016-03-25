//#include <opencv2/opencv.hpp>
//#include <cv.h>
//#include <highgui.h>
//#include "iostream"
//#include <stdio.h>
//#include <vector>
//#include "opencv2/imgcodecs.hpp"
//#include "opencv2/core/core.hpp"
//#include <vector>  //for std::vector
//#include <string>  //for std::string
//#include "string"
//#include <algorithm>
//
//using namespace cv;
//using namespace std;
//
//std::vector<std::string> killedPixelsPostions;
//std::string* p = killedPixelsPostions.data();
//std::vector<std::string> survivedPixelsPostions;
//Mat srcImg =
//		imread(
//				"/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment-3/house_19670.bmp");
//Mat src1 = Mat::zeros(int(srcImg.rows), int(srcImg.cols), srcImg.type());
//
//
//void setImage() {
//	srcImg.convertTo(src1, CV_8UC1);
//}
//
///*
// * This class is a node class, where a node consists
// * of intensity, parent node, i and j which means position of node,
// * mean, variance, and neighboring nodes.
// * This class represents every pixel.
// */
//class Node {
//public:
//	float intensity;
//	Node *parentNode;
//	int i;
//	int j;
//	float mean;
//	float variance;
//	vector<Node> neighboringNodes;
//};
//
//std::vector<Node> killedPixelsWithSurvivedParents;
///*
// * This is a helper method that is used to compute
// * the mean of a single pixel
// */
//static float findMeanOfPixel(Mat srcImage, int i, int j) {
//	float mean = 0;
//	if (i == 0) {
//		if (j == 0) {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j + 1))
//					+ srcImage.at<uchar>((i + 1), j)
//					+ srcImage.at<uchar>((i + 1), (j + 1));
//			mean = mean / 4;
//		} else if (j == srcImage.cols - 1) {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j - 1))
//					+ srcImage.at<uchar>((i + 1), j)
//					+ srcImage.at<uchar>((i + 1), (j - 1));
//			mean = mean / 4;
//		} else {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j + 1))
//					+ srcImage.at<uchar>(i, (j - 1))
//					+ srcImage.at<uchar>((i + 1), j)
//					+ srcImage.at<uchar>((i + 1), (j + 1))
//					+ srcImage.at<uchar>((i + 1), (j - 1));
//			mean = mean / 6;
//		}
//	} else if (i == srcImage.rows - 1) {
//		if (j == 0) {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j + 1))
//					+ srcImage.at<uchar>((i - 1), j)
//					+ srcImage.at<uchar>((i - 1), (j + 1));
//			mean = mean / 4;
//		} else if (j == srcImage.cols - 1) {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j - 1))
//					+ srcImage.at<uchar>((i - 1), j)
//					+ srcImage.at<uchar>((i - 1), (j - 1));
//			mean = mean / 4;
//		} else {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j + 1))
//					+ srcImage.at<uchar>(i, (j - 1))
//					+ srcImage.at<uchar>((i - 1), j)
//					+ srcImage.at<uchar>((i - 1), (j + 1))
//					+ srcImage.at<uchar>((i - 1), (j - 1));
//			mean = mean / 6;
//		}
//	} else {
//		if (j == 0) {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j + 1))
//					+ srcImage.at<uchar>((i - 1), j)
//					+ srcImage.at<uchar>((i - 1), (j + 1))
//					+ srcImage.at<uchar>((i + 1), j)
//					+ srcImage.at<uchar>((i + 1), (j + 1));
//			mean = mean / 6;
//		} else if (j == srcImage.cols - 1) {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j - 1))
//					+ srcImage.at<uchar>((i - 1), j)
//					+ srcImage.at<uchar>((i - 1), (j - 1))
//					+ srcImage.at<uchar>((i + 1), j)
//					+ srcImage.at<uchar>((i + 1), (j - 1));
//			mean = mean / 6;
//		} else {
//			mean = srcImage.at<uchar>(i, j) + srcImage.at<uchar>(i, (j + 1))
//					+ srcImage.at<uchar>(i, (j - 1))
//					+ srcImage.at<uchar>((i - 1), j)
//					+ srcImage.at<uchar>((i - 1), (j + 1))
//					+ srcImage.at<uchar>((i - 1), (j - 1))
//					+ srcImage.at<uchar>((i + 1), j)
//					+ srcImage.at<uchar>((i + 1), (j + 1))
//					+ srcImage.at<uchar>((i + 1), (j - 1));
//
//			mean = mean / 9;
//		}
//	}
//	return mean;
//}
///*
// * This method is used to get mean of all pixels.
// * This method used findMeanOfPixel method as a helper method
// * to achieve the mean of all pixels.
// */
//static Mat findMeanOfAllPixels(Mat srcImage) {
//	Mat meanMat = Mat::zeros(int(srcImage.rows), int(srcImage.cols), CV_32F);
//	for (int i = 0; i < srcImage.rows; i++) {
//		for (int j = 0; j < srcImage.cols; j++) {
//			meanMat.at<float>(i, j) = findMeanOfPixel(srcImage, i, j);
//		}
//
//	}
//	return meanMat;
//}
///*
// * This method is used to calculate the variance
// * of a single pixel.
// */
//static float findVarianceOfPixel(Mat srcImage, int i, int j) {
//	float variance = 0;
//	float mean = findMeanOfPixel(srcImage, i, j);
//	if (i == 0) {
//		if (j == 0) {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), (j + 1)) - mean, 2);
//			variance = variance / 4;
//		} else if (j == srcImage.cols - 1) {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j - 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), (j - 1)) - mean, 2);
//			variance = variance / 4;
//		} else {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j - 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), (j - 1)) - mean, 2);
//			variance = variance / 6;
//		}
//	} else if (i == srcImage.rows - 1) {
//		if (j == 0) {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), (j + 1)) - mean, 2);
//			variance = variance / 4;
//		} else if (j == srcImage.cols - 1) {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j - 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), (j - 1)) - mean, 2);
//			variance = variance / 4;
//		} else {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j - 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), (j - 1)) - mean, 2);
//			variance = variance / 6;
//		}
//	} else {
//		if (j == 0) {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), (j + 1)) - mean, 2);
//			variance = variance / 6;
//		} else if (j == srcImage.cols - 1) {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j - 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), (j - 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), (j - 1)) - mean, 2);
//			variance = variance / 6;
//		} else {
//			variance = pow(srcImage.at<uchar>(i, j) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>(i, (j - 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i - 1), (j - 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), j) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), (j + 1)) - mean, 2)
//					+ pow(srcImage.at<uchar>((i + 1), (j - 1)) - mean, 2);
//
//			variance = variance / 9;
//		}
//	}
//	return variance;
//}
///*
// * This method computes variance of all pixel by using
// * findVarianceOfPixel method as a helper method.
// */
//static Mat findVarianceOfAllPixels(Mat srcImage) {
//	Mat varianceMat = Mat::zeros(int(srcImage.rows), int(srcImage.cols),
//	CV_32F);
//	for (int i = 0; i < srcImage.rows; i++) {
//		for (int j = 0; j < srcImage.cols; j++) {
//			varianceMat.at<float>(i, j) = findVarianceOfPixel(srcImage, i, j);
//		}
//
//	}
//	return varianceMat;
//}
///*
// * A helper method to parse int to string
// */
//string IntToString(int a) {
//	ostringstream temp;
//	temp << a;
//	return temp.str();
//}
///*
// * A helper method to check if a vector of strings
// * contains a specific string.
// */
//bool contains(vector<string> killed, string findIt) {
//	if (find(killedPixelsPostions.begin(), killedPixelsPostions.end(), findIt)
//			!= killedPixelsPostions.end()) {
//		return true;
//	} else {
//		return false;
//	}
//}
///*
// * This method determine if a pixel is killed or not.
// * if it is a killed pixel it's postion is stored in a vector
// * of strings called killedPixelsPostions
// */
//void killedPixel(Mat varianceMat, int i, int j) {
//	std::string killed = "";
//	if (i == 0) {
//		if (j == 0) {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j + 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), (j + 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + ","
//											+ IntToString(j + 1)))) {
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//			}
//		} else if (j == varianceMat.cols - 1) {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j - 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j - 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + ","
//											+ IntToString(j - 1)))) {
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//			}
//
//		} else {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j + 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>(i, (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i) + "," + IntToString(j - 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), (j + 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + ","
//											+ IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + ","
//											+ IntToString(j - 1)))) {
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//			}
//		}
//	} else if (i == varianceMat.rows - 1) {
//		if (j == 0) {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j + 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), (j + 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + ","
//											+ IntToString(j + 1)))) {
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//			}
//		} else if (j == varianceMat.cols - 1) {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j - 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j - 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + ","
//											+ IntToString(j - 1)))) {
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//			}
//		} else {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j + 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>(i, (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i) + "," + IntToString(j - 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), (j + 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + ","
//											+ IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + ","
//											+ IntToString(j - 1)))) {
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//			}
//
//		}
//	} else {
//		if (j == 0) {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j + 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), (j + 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + ","
//											+ IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), (j + 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + ","
//											+ IntToString(j + 1)))) {
//			}
//			killed.clear();
//			killed = killed + IntToString(i) + "," + IntToString(j + 1) + "";
//			killedPixelsPostions.push_back(killed);
//			killed.clear();
//			killed = killed + IntToString(i - 1) + "," + IntToString(j) + "";
//			killedPixelsPostions.push_back(killed);
//			killed.clear();
//			killed = killed + IntToString(i - 1) + "," + IntToString(j + 1)
//					+ "";
//			killedPixelsPostions.push_back(killed);
//			killed.clear();
//			killed = killed + IntToString(i + 1) + "," + IntToString(j) + "";
//			killedPixelsPostions.push_back(killed);
//			killed.clear();
//			killed = killed + IntToString(i + 1) + "," + IntToString(j + 1)
//					+ "";
//			killedPixelsPostions.push_back(killed);
//		} else if (j == varianceMat.cols - 1) {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j - 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j - 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + ","
//											+ IntToString(j - 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + ","
//											+ IntToString(j - 1)))) {
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//			}
//		} else {
//			if ((varianceMat.at<float>(i, j)
//					<= varianceMat.at<float>(i, (j + 1))
//					|| contains(killedPixelsPostions,
//							IntToString(i) + "," + IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>(i, (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i) + "," + IntToString(j - 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), (j + 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + ","
//											+ IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i - 1), (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i - 1) + ","
//											+ IntToString(j - 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), j)
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + "," + IntToString(j)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), (j + 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + ","
//											+ IntToString(j + 1)))
//					&& (varianceMat.at<float>(i, j)
//							<= varianceMat.at<float>((i + 1), (j - 1))
//							|| contains(killedPixelsPostions,
//									IntToString(i + 1) + ","
//											+ IntToString(j - 1)))) {
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i - 1) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j + 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//				killed.clear();
//				killed = killed + IntToString(i + 1) + "," + IntToString(j - 1)
//						+ "";
//				killedPixelsPostions.push_back(killed);
//			}
//		}
//	}
//}
///*
// * This method finds all killed pixels using
// * killedPixel method as a helper method.
// */
//void allKilledPixel(Mat varianceMat) {
//	for (int i = 0; i < varianceMat.rows; i++) {
//		for (int j = 0; j < varianceMat.cols; j++) {
//			if (!contains(killedPixelsPostions,
//					IntToString(i) + "," + IntToString(j))) {
//				killedPixel(varianceMat, i, j);
//			}
//		}
//	}
//}
///*
// * This method is used to find the parent of
// * a killed pixel. This parent shall be
// * a survived pixel
// */
//Node parentOfKilledPixel(int i, int j, Mat srcImage) {
//	Node child;
//	Node *parent;
//	int takenI = 0;
//	int takenJ = 0;
//	float max = 2000.0F;
//	child.intensity = srcImage.at<uchar>(i, j);
//	child.i = i;
//	child.j = j;
//	if (contains(killedPixelsPostions, IntToString(i) + "," + IntToString(j))) {
//		if (i == 0) {
//			if (j == 0) {
//				if (!contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j + 1))) {
//					parent->intensity = srcImage.at<uchar>(i, j + 1);
//					parent->i = i;
//					parent->j = j + 1;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//
//				}
//				if (!contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j + 1))) {
//					parent->intensity = srcImage.at<uchar>(i + 1, j + 1);
//					parent->i = i + 1;
//					parent->j = j + 1;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//				if (!contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j))) {
//					parent->intensity = srcImage.at<uchar>(i + 1, j);
//					parent->i = i + 1;
//					parent->j = j;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//			} else if (j == srcImage.cols - 1) {
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j - 1))) {
//					parent->intensity = srcImage.at<uchar>(i, j - 1);
//					parent->i = i;
//					parent->j = j - 1;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j))) {
//					parent->intensity = srcImage.at<uchar>(i + 1, j);
//					parent->i = i + 1;
//					parent->j = j;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j - 1))) {
//					parent->intensity = srcImage.at<uchar>(i + 1, j - 1);
//					parent->i = i + 1;
//					parent->j = j - 1;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//			} else {
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i, j - 1) <= max) {
//						max = srcImage.at<uchar>(i, j - 1);
//						takenI = i;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i, j + 1) <= max) {
//						max = srcImage.at<uchar>(i, j + 1);
//						takenI = i;
//						takenJ = j + 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j))) {
//					if (srcImage.at<uchar>(i + 1, j) <= max) {
//						max = srcImage.at<uchar>(i + 1, j);
//						takenI = i + 1;
//						takenJ = j;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i + 1, j - 1) <= max) {
//						max = srcImage.at<uchar>(i + 1, j - 1);
//						takenI = i + 1;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i + 1, j + 1) <= max) {
//						max = srcImage.at<uchar>(i + 1, j + 1);
//						takenI = i + 1;
//						takenJ = j + 1;
//					}
//				}
//				parent->intensity = max;
//				parent->i = takenI;
//				parent->j = takenJ;
//				child.parentNode = parent;
//				child.parentNode->parentNode = parent;
//			}
//		} else if (i == srcImage.rows - 1) {
//			if (j == 0) {
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j))) {
//					parent->intensity = srcImage.at<uchar>(i - 1, j);
//					parent->i = i - 1;
//					parent->j = j;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j + 1))) {
//					parent->intensity = srcImage.at<uchar>(i - 1, j + 1);
//					parent->i = i - 1;
//					parent->j = j + 1;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j + 1))) {
//					parent->intensity = srcImage.at<uchar>(i, j + 1);
//					parent->i = i;
//					parent->j = j + 1;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//			} else if (j == srcImage.cols - 1) {
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j))) {
//					parent->intensity = srcImage.at<uchar>(i - 1, j);
//					parent->i = i - 1;
//					parent->j = j;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j - 1))) {
//					parent->intensity = srcImage.at<uchar>(i - 1, j - 1);
//					parent->i = i - 1;
//					parent->j = j - 1;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j - 1))) {
//					parent->intensity = srcImage.at<uchar>(i, j - 1);
//					parent->i = i;
//					parent->j = j - 1;
//					child.parentNode = parent;
//					child.parentNode->parentNode = parent;
//				}
//			} else {
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i, j - 1) <= max) {
//						max = srcImage.at<uchar>(i, j - 1);
//						takenI = i;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i, j + 1) <= max) {
//						max = srcImage.at<uchar>(i, j + 1);
//						takenI = i;
//						takenJ = j + 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j))) {
//					if (srcImage.at<uchar>(i - 1, j) <= max) {
//						max = srcImage.at<uchar>(i - 1, j);
//						takenI = i - 1;
//						takenJ = j;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i - 1, j - 1) <= max) {
//						max = srcImage.at<uchar>(i - 1, j - 1);
//						takenI = i - 1;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i - 1, j + 1) <= max) {
//						max = srcImage.at<uchar>(i - 1, j + 1);
//						takenI = i - 1;
//						takenJ = j + 1;
//					}
//				}
//				parent->intensity = max;
//				parent->i = takenI;
//				parent->j = takenJ;
//				child.parentNode = parent;
//				child.parentNode->parentNode = parent;
//			}
//		} else {
//			if (j == 0) {
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i, j + 1) <= max) {
//						max = srcImage.at<uchar>(i, j + 1);
//						takenI = i;
//						takenJ = j + 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i - 1, j + 1) <= max) {
//						max = srcImage.at<uchar>(i - 1, j + 1);
//						takenI = i - 1;
//						takenJ = j + 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j))) {
//					if (srcImage.at<uchar>(i - 1, j) <= max) {
//						max = srcImage.at<uchar>(i - 1, j);
//						takenI = i - 1;
//						takenJ = j;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j))) {
//					if (srcImage.at<uchar>(i + 1, j) <= max) {
//						max = srcImage.at<uchar>(i + 1, j);
//						takenI = i + 1;
//						takenJ = j;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i + 1, j + 1) <= max) {
//						max = srcImage.at<uchar>(i + 1, j + 1);
//						takenI = i + 1;
//						takenJ = j + 1;
//					}
//				}
//				parent->intensity = max;
//				parent->i = takenI;
//				parent->j = takenJ;
//				child.parentNode = parent;
//				child.parentNode->parentNode = parent;
//			} else if (j == srcImage.cols - 1) {
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i, j - 1) <= max) {
//						max = srcImage.at<uchar>(i, j - 1);
//						takenI = i;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i - 1, j - 1) <= max) {
//						max = srcImage.at<uchar>(i - 1, j - 1);
//						takenI = i - 1;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j))) {
//					if (srcImage.at<uchar>(i - 1, j) <= max) {
//						max = srcImage.at<uchar>(i - 1, j);
//						takenI = i - 1;
//						takenJ = j;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j))) {
//					if (srcImage.at<uchar>(i + 1, j) <= max) {
//						max = srcImage.at<uchar>(i + 1, j);
//						takenI = i + 1;
//						takenJ = j;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i + 1, j - 1) <= max) {
//						max = srcImage.at<uchar>(i + 1, j - 1);
//						takenI = i + 1;
//						takenJ = j - 1;
//					}
//				}
//				parent->intensity = max;
//				parent->i = takenI;
//				parent->j = takenJ;
//				child.parentNode = parent;
//				child.parentNode->parentNode = parent;
//			} else {
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i, j - 1) <= max) {
//						max = srcImage.at<uchar>(i, j - 1);
//						takenI = i;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i - 1, j - 1) <= max) {
//						max = srcImage.at<uchar>(i - 1, j - 1);
//						takenI = i - 1;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j))) {
//					if (srcImage.at<uchar>(i - 1, j) <= max) {
//						max = srcImage.at<uchar>(i - 1, j);
//						takenI = i - 1;
//						takenJ = j;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j))) {
//					if (srcImage.at<uchar>(i + 1, j) <= max) {
//						max = srcImage.at<uchar>(i + 1, j);
//						takenI = i + 1;
//						takenJ = j;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j - 1))) {
//					if (srcImage.at<uchar>(i + 1, j - 1) <= max) {
//						max = srcImage.at<uchar>(i + 1, j - 1);
//						takenI = i + 1;
//						takenJ = j - 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i, j + 1) <= max) {
//						max = srcImage.at<uchar>(i, j + 1);
//						takenI = i;
//						takenJ = j + 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i - 1) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i - 1, j + 1) <= max) {
//						max = srcImage.at<uchar>(i - 1, j + 1);
//						takenI = i - 1;
//						takenJ = j + 1;
//					}
//				}
//				if (contains(survivedPixelsPostions,
//						IntToString(i + 1) + "," + IntToString(j + 1))) {
//					if (srcImage.at<uchar>(i + 1, j + 1) <= max) {
//						max = srcImage.at<uchar>(i + 1, j + 1);
//						takenI = i + 1;
//						takenJ = j + 1;
//					}
//				}
//				parent->intensity = max;
//				parent->i = takenI;
//				parent->j = takenJ;
//				child.parentNode = parent;
//				child.parentNode->parentNode = parent;
//			}
//		}
//	}
//	return child;
//}
///*
// * This method is used to store the positions
// * of survived pixels in vector of strings
// * survivedPixelsPostions
// */
//void allSurvivedPixels() {
//	for (int i = 0; i < src1.rows; i++) {
//		for (int j = 0; j < src1.cols; j++) {
//			if (!contains(killedPixelsPostions,
//					IntToString(i) + "," + IntToString(j))) {
//				survivedPixelsPostions.push_back(
//						IntToString(i) + "," + IntToString(j));
//			}
//		}
//	}
//}
///*
// * This method is used to compute the parent
// * of all pixels. Where every killed pixel should have
// * a surviving parent.
// */
//void parentsOfAllKilledPixels(Mat srcImage) {
//	for (int i = 0; i < srcImage.rows; i++) {
//		for (int j = 0; j < srcImage.cols; j++) {
//			killedPixelsWithSurvivedParents.push_back(
//					parentOfKilledPixel(i, j, srcImage));
//		}
//	}
//}
//
//void testPrint() {
//	srcImg.convertTo(src1, CV_8UC1);
//	imshow("src1", src1);
//}
///*
// * This is a helper method that is used
// * to decide if two killed nodes/pixels have the same
// * surviving parent node.
// */
//bool differentParents(Node *parent1, Node *parent2) {
//	bool res = false;
//	if ((parent1->i == parent2->i) && (parent1->j == parent2->j)) {
//		res = false;
//	} else {
//		res = true;
//	}
//	return res;
//}
///*
// * This method is used to decide if two nodes/pixels
// * are neighbors or not. where these two nodes shall
// * have different parents.
// */
//void findNeighbours(Node node1, Node node2) {
//	if (differentParents(node1.parentNode,
//			node2.parentNode) && node1.parentNode != NULL && node2.parentNode != NULL) {
//		node1.neighboringNodes.push_back(node2);
//	}
//}
///*
// * This method is used to find all neighbors of a certain pixel.
// */
//void findAllNeighbours() {
//	for (int i = 0; i < killedPixelsWithSurvivedParents.size(); i++) {
//		for (int j = 0; j < killedPixelsWithSurvivedParents.size(); j++) {
//			findNeighbours(killedPixelsWithSurvivedParents[i],
//					killedPixelsWithSurvivedParents[j]);
//		}
//	}
//}
///*
// * This method is used to find the mean of a certain node.
// * This is done by finding all nodes that have the same parent of this certain
// * node an get the sum of the intensities and divide it by their sum.
// */
//void meanOfNode(Node n) {
//	int sum = 2;
//	float sumOfIntensities = 0;
//	if (n.parentNode != NULL) {
//		sumOfIntensities = n.intensity + n.parentNode->intensity;
//		for (int i = 0; i < killedPixelsWithSurvivedParents.size(); i++) {
//			if (killedPixelsWithSurvivedParents[i].parentNode != NULL
//					&& !differentParents(n.parentNode,
//							killedPixelsWithSurvivedParents[i].parentNode)
//					&& n.i != killedPixelsWithSurvivedParents[i].i
//					&& n.j != killedPixelsWithSurvivedParents[i].j) {
//				sum++;
//				sumOfIntensities = sumOfIntensities
//						+ killedPixelsWithSurvivedParents[i].intensity;
//			}
//		}
//		n.mean = (float) sumOfIntensities / sum;
//		n.parentNode->mean = n.mean;
//	}
//	sum = 0;
//	sumOfIntensities = 0;
//}
///*
// * This method is used to get mean of every node.
// */
//void meanOfAllNodes() {
//	for (int i = 0; i < killedPixelsWithSurvivedParents.size(); i++) {
//		meanOfNode(killedPixelsWithSurvivedParents[i]);
//	}
//}
///*
// * This method is used to set the intensity of a certain node
// * to an intensity of a neighboring node, where this neiboring node
// * shall have the minimum mean of all neigboring nodes of this certain node.
// */
//void mergeNodes(Node n) {
//	float leastMean = (float) INT_MAX;
//	if (n.neighboringNodes.size() == 0 && n.parentNode != NULL) {
//		for (int i = 0; i < n.neighboringNodes.size(); i++) {
//			if (n.neighboringNodes[i].mean <= leastMean) {
//				leastMean = n.neighboringNodes[i].mean;
//				n.intensity = n.neighboringNodes[i].intensity;
//				n.parentNode->intensity = n.intensity;
//			}
//		}
//	}
//}
///*
// * This method is used to merge all nodes.
// */
//void mergeAllNodes() {
//	for (int i = 0; i < killedPixelsWithSurvivedParents.size(); i++) {
//		mergeNodes(killedPixelsWithSurvivedParents[i]);
//	}
//	for (int i = 0; i < killedPixelsWithSurvivedParents.size(); i++) {
//		killedPixelsWithSurvivedParents[i].intensity =
//				killedPixelsWithSurvivedParents[i].parentNode->intensity;
//	}
//
//}
//
//void allInOne(Mat srcImage) {
//	allKilledPixel(findVarianceOfAllPixels(srcImage));
//	sort(killedPixelsPostions.begin(), killedPixelsPostions.end());
//	killedPixelsPostions.erase(
//			unique(killedPixelsPostions.begin(), killedPixelsPostions.end()),
//			killedPixelsPostions.end());
//	allSurvivedPixels();
//	parentsOfAllKilledPixels(srcImage);
////	sort(killedPixelsWithSurvivedParents.begin(), killedPixelsWithSurvivedParents.end());
////	killedPixelsWithSurvivedParents.erase(
////				unique(killedPixelsWithSurvivedParents.begin(), killedPixelsWithSurvivedParents.end()),
////				killedPixelsWithSurvivedParents.end());
//	findAllNeighbours();
//	meanOfAllNodes();
//	mergeAllNodes();
//	Mat newImage = srcImage.clone();
//	cout << "killedPixelsWithSurvivedParents size :"
//			<< killedPixelsWithSurvivedParents.size();
//	for (int i = 0; i < killedPixelsWithSurvivedParents.size(); i++) {
//		newImage.at<uchar>(killedPixelsWithSurvivedParents[i].i,
//				killedPixelsWithSurvivedParents[i].j) =
//				killedPixelsWithSurvivedParents[i].intensity;
//	}
//	imshow("newImage", newImage);
//}
//
//int main(int argc, char** argv) {
//	srcImg.convertTo(src1, CV_8UC1);
////	Mat org =
////			(Mat_<float>(5, 5) << 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5);
////
//	Mat test = findVarianceOfAllPixels(src1);
//	for (int i = 0; i < test.rows; i++) {
//		for (int j = 0; j < test.cols; j++) {
//			printf("%f", test.at<float>(i, j));
//			printf(" ");
//		}
//		printf("\n");
//	}
//	printf("===================");
//	printf("\n");
//	allKilledPixel(src1);
//	for (int i = 0; i < killedPixelsPostions.size(); i++) {
//		cout << killedPixelsPostions[i] << " ";
//		std::cout << '\n';
//	}
//	sort(killedPixelsPostions.begin(), killedPixelsPostions.end());
//	killedPixelsPostions.erase(
//			unique(killedPixelsPostions.begin(), killedPixelsPostions.end()),
//			killedPixelsPostions.end());
//	printf("========");
//	std::cout << '\n';
//	std::cout << killedPixelsPostions.size();
//	printf("\n");
//	allSurvivedPixels();
//	sort(survivedPixelsPostions.begin(), survivedPixelsPostions.end());
//	killedPixelsPostions.erase(
//			unique(survivedPixelsPostions.begin(),
//					survivedPixelsPostions.end()),
//			survivedPixelsPostions.end());
//	std::cout << survivedPixelsPostions.size();
//	for (int i = 0; i < survivedPixelsPostions.size(); i++) {
//		cout << survivedPixelsPostions[i] << " ";
//		std::cout << '\n';
//	}
//	allInOne(src1);
//	//parentsOfAllKilledPixels(src1);
//	cout << "killedPixelsWithSurvivedParents size :" << killedPixelsWithSurvivedParents.size();
//
////	cout << "Try length of vector of nodes";
////	Node s = parentOfKilledPixel(0, 0, org);
////	cout << "\n intensity is: " << s.parentNode->i;
//
////printf("%f", findMeanOfPixel(test, 3, 2));
//	//testPrint();
//	waitKey(100000);
//	return 0;
//}
//
