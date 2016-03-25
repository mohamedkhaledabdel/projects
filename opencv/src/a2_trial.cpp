//#include <opencv2/opencv.hpp>
//#include <cv.h>
//#include <highgui.h>
//#include "iostream"
//#include <stdio.h>
//#include <vector>
//#include "opencv2/imgcodecs.hpp"
//#include "opencv2/core/core.hpp"
//
//using namespace cv;
//
////void fundamentalMatrix() {
////	Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment 2/a2/test/left.jpg");
////	Mat src2 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment 2/a2/test/right.jpg");
////	int point_count = src1.rows * src1.cols;
////	std::vector<Point2f> points1(point_count);
////	std::vector<Point2f> points2(point_count);
////
////	// initialize the points here ... */
////	for( int i = 0; i < point_count; i++ )
////	{
////	    points1[i]= ((float)i,(float)i+1);
////	    points2[i] = ((float)i,(float)i+1);
////	}
////
////	Mat fundamental_matrix =
////	 cv::findFundamentalMat(points1, points2, FM_RANSAC, 3.0, 0.99);
////}
//
///*void fundamentalMatrix() {
// Mat src1 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment 2/a2/test/left.jpg");
// Mat src2 = imread("/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment 2/a2/test/right.jpg");
// KeyPoint keypoints1 = new KeyPoint();
// KeyPoint keypoints2 = new KeyPoint();
// Mat descriptors1 = new Mat();
// Mat descriptors2 = new Mat();
// //Definition of ORB keypoint detector and descriptor extractors
// FeatureDetector detector = new FeatureDetector();
// DescriptorExtractor extractor; = DescriptorExtractor.create(DescriptorExtractor.ORB);
// //Detect keypoints
//
// detector.detect(src1, keypoints1);
// detector.detect(src2, keypoints2);
// //Extract descriptors
// ext
// extractor.compute(src1, keypoints1, descriptors1);
// extractor.compute(src2, keypoints2, descriptors2);
//
// //Definition of descriptor matcher
// DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
//
// //Match points of two images
// MatOfDMatch matches = new MatOfDMatch();
// matcher.match(descriptors1,descriptors2 ,matches);
//
// }*/
//
//int xOfMouse = 0;
//int yOfMouse = 0;
//// This methods calculates the camera calibration matrix
//Mat calculateCameraCalMat(float focalLenght, float width, float height,
//		float FH, float FW, float princX, float princY) {
//	//printf("%s","hohoho");
//	float u = focalLenght * (width / FW);
//	float v = focalLenght * (height / FH);
//	float uo = float(princX);
//	float vo = float(princY);
//	Mat cameraCalMat = (Mat_<float>(3, 3) << u, 0, uo, 0, v, vo, 0, 0, 1);
//	//printf("%i", cameraCalMat.at<uint>(0, 0));
//	return cameraCalMat;
//}
//// This methods calculates the Rotation matrix
//Mat calculateRotationMat(float omega, float phi, float kappa) {
//	Mat omegaMat =
//			(Mat_<float>(3, 3) << 1, 0, 0, 0, cosf(omega * 0.0174533), sinf(
//					omega * 0.0174533), 0, sinf(omega * 0.0174533) * -1, cosf(
//					omega * 0.0174533));
//	Mat phiMat = (Mat_<float>(3, 3) << cosf(phi * 0.0174533), 0, sinf(
//			phi * 0.0174533) * -1, 0, 1, 0, sinf(phi * 0.0174533), 0, cosf(
//			phi * 0.0174533));
//	Mat kappaMat = (Mat_<float>(3, 3) << cosf(kappa * 0.0174533), sinf(
//			kappa * 0.0174533), 0, sinf(kappa * 0.0174533) * -1, cosf(
//			kappa * 0.0174533), 0, 0, 0, 1);
//	//printf("dsadasd");
//	Mat rotationMat = kappaMat * phiMat * omegaMat;
//	return rotationMat;
//}
//
//// This methods calculates the difference between two camera skew matrix
//Mat calculateCameraSkewMat(float leftX, float leftY, float leftZ, float rightX,
//		float rightY, float rightZ) {
////	Mat translationMat =
////			(Mat_<float>(3, 1) << leftX - rightX, leftY - rightY, leftZ - rightZ);
//	Mat skewMat =
//			(Mat_<float>(3, 3) << 0, leftZ - rightZ * -1, leftY - rightY, leftZ
//					- rightZ, 0, leftX - rightX * -1, leftY - rightY * -1, leftX
//					- rightX, 0);
//	return skewMat;
//}
//
////Mat rotationTranslationMat(float omega, float phi, float kappa, float leftX,
////		float leftY, float leftZ, float rightX, float rightY, float rightZ) {
////	Mat translationMat = calculateTranslationMat(leftX, leftY, leftZ, rightX,
////			rightY, rightZ);
////	Mat rotationMat = calculateRotationMat(omega, phi, kappa);
////	Mat zeros = (Mat_<float>(1, 4) << 0.0, 0.0, 0.0, 1.0);
////	Mat res;
////	hconcat(rotationMat, translationMat, res);
////	Mat finalRes(4, 4, CV_32F);
////	for (int i = 0; i < res.rows; i++) {
////		for (int j = 0; j < res.cols; j++) {
////			finalRes.at<float>(i, j) = res.at<float>(i, j);
////		}
////	}
////	//res.copyTo(zeros.row(0));
////	finalRes.at<float>(3, 0) = 0.0;
////	finalRes.at<float>(3, 1) = 0.0;
////	finalRes.at<float>(3, 2) = 0.0;
////	finalRes.at<float>(3, 3) = 1.0;
////	//hconcat(res,zeros,finalRes);
////	return finalRes;
////}
//
//// This method calculates A*R Matrix
//Mat ARMat(float focalLenght, float width, float height, float FH, float FW,
//		float princX, float princY, float omega, float phi, float kappa) {
//	return calculateCameraCalMat(focalLenght, width, height, FH, FW, princX,
//			princY) * calculateRotationMat(omega, phi, kappa);
//
//}
//
//// This method calculates the (A*R)^-1 matrix which is the matrix belonging to the 1st image
//Mat firstImageARInverse(float focalLenght, float width, float height, float FH,
//		float FW, float princX, float princY, float omega, float phi,
//		float kappa) {
//	return ARMat(focalLenght, width, height, FH, FW, princX, princY, omega, phi,
//			kappa).inv();
//}
//
//// This method calculates the Transpose((A*R)^-1) matrix which is the matrix belonging to the 2nd image
//Mat secondImageARInverseTranspose(float focalLenght, float width, float height,
//		float FH, float FW, float princX, float princY, float omega, float phi,
//		float kappa) {
//	Mat trans;
//	transpose(
//			(ARMat(focalLenght, width, height, FH, FW, princX, princY, omega,
//					phi, kappa)).inv(), trans);
//	return trans;
//}
//
////void callBackFun(int event, int x, int y, int flags, void* userdata) {
////	//std::cout << "hohohoho ("<<x<<", "<<y<<")";
////	if(event == EVENT_LBUTTONDOWN) {
////		xOfMouse = x;
////		yOfMouse = y;
////		std::cout << "hohohoho ("<<xOfMouse<<", "<<yOfMouse<<")";
////	}
////	else {
////		std::cout << "fsfsfsfs";
////	}
////}
//
////Mat drawEpipolarLine(Mat fundemtanMat, float x, int float) {
////
////}
//
//// This method computes the fundamental matrix of two images
//Mat fundametalMatrix(float focalLenght, float width, float height, float FH,
//		float FW, float princX, float princY, float omegaLeft, float phiLeft,
//		float kappaLeft, float omegaRight, float phiRight, float kappaRight,
//		float leftX, float leftY, float leftZ, float rightX, float rightY,
//		float rightZ) {
//	return secondImageARInverseTranspose(focalLenght, width, height, FH, FW,
//			princX, princY, omegaRight, phiRight, kappaRight)
//			* calculateCameraSkewMat(leftX, leftY, leftZ, rightX, rightY,
//					rightZ)
//			* firstImageARInverse(focalLenght, width, height, FH, FW, princX,
//					princY, omegaLeft, phiLeft, kappaLeft);
//}
//
////This method calculates the epipole line
//Mat epipoleLine(float focalLenght, float width, float height, float FH,
//		float FW, float princX, float princY, float omegaLeft, float phiLeft,
//		float kappaLeft, float omegaRight, float phiRight, float kappaRight,
//		float leftX, float leftY, float leftZ, float rightX, float rightY,
//		float rightZ) {
//	Mat pointM = (Mat_<float>(3, 1) << 340, 400, 1);
////	Mat fund = fundametalMatrix(focalLenght, width, height, FH, FW, princX, princY,
////			omegaLeft, phiLeft, kappaLeft, omegaRight, phiRight, kappaRight,
////			leftX, leftY, leftZ, rightX, rightY, rightZ);
////		for (int i = 0; i < fund.rows; i++) {
////			for (int j = 0; j < fund.cols; j++) {
////				printf("%f", fund.at<float>(i, j));
////				printf(", ");
////			}
////			printf("\n");
////		}
//	return (fundametalMatrix(focalLenght, width, height, FH, FW, princX, princY,
//			omegaLeft, phiLeft, kappaLeft, omegaRight, phiRight, kappaRight,
//			leftX, leftY, leftZ, rightX, rightY, rightZ)) * pointM;
////	Mat fundMat = (Mat_<float>(3, 3) << -35.845305102,  536.650144219, 498887.870433723,
////			665.357055937, -72.547157909, -1395843.868374783,
////			561912.114861790, 1038097.576395732, -304504752.469864428);
////	return fundMat * pointM;
//
//}
////This method draws the epipole line
//void drawEpipoleLine(Mat matrix) {
//	Mat src1 =
//			imread(
//					"/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment 2/a2/test/right.jpg");
//	Point p;
//	p.x = (int) matrix.at<float>(0, 0);
//	p.y = (int) matrix.at<float>(1, 0);
//	Mat upperBorder = (Mat_<float>(3, 1) << 0, -1 * (src1.cols - 1), 0);
//	Mat lowerBorder = (Mat_<float>(3, 1) << 0, src1.cols - 1, -1
//			* (src1.cols - 1) * (src1.rows - 1));
//	Mat leftBorder = (Mat_<float>(3, 1) << (src1.rows - 1), 0, 0);
//	Mat rightBorder = (Mat_<float>(3, 1) << -1 * (src1.cols - 1), 0, -1
//			* (src1.cols - 1) * (src1.rows - 1));
//	Mat epipoleLine = (Mat_<float>(3, 1) << matrix.at<float>(0, 0), matrix.at<
//			float>(1, 0), matrix.at<float>(2, 0));
//	Mat crossProductWithLeftBorder = rightBorder.cross(epipoleLine);
//	crossProductWithLeftBorder.at<float>(0, 0) = crossProductWithLeftBorder.at<
//			float>(0, 0) / crossProductWithLeftBorder.at<float>(2, 0);
//	crossProductWithLeftBorder.at<float>(1, 0) = crossProductWithLeftBorder.at<
//			float>(1, 0) / crossProductWithLeftBorder.at<float>(2, 0);
//	crossProductWithLeftBorder.at<float>(2, 0) = crossProductWithLeftBorder.at<
//			float>(2, 0) / crossProductWithLeftBorder.at<float>(2, 0);
//	//std::cout << p.x;
//	Point p2;
////	for (int i = 0; i < crossProductWithLeftBorder.rows; i++) {
////		for (int j = 0; j < crossProductWithLeftBorder.cols; j++) {
////			printf("%f", crossProductWithLeftBorder.at<float>(i, j));
////			printf(", ");
////		}
////		printf("\n");
////	}
//	p2.x = (int) crossProductWithLeftBorder.at<float>(0, 0); //262
//	p2.y = (int) crossProductWithLeftBorder.at<float>(1, 0); //210
//	line(src1, p, Point(700, 480), Scalar(200, 0, 0), 2, 8, 0);
//	imshow("src1", src1);
//}
//
////2nd quesiton implementation
//
////This method computes calibration matrix
//Mat calibrationMatrixWithMissingParams(float alphaU, float alphaV,
//		float imageXSize, float imageYSize) {
//	Mat calibMatrix =
//			(Mat_<float>(3, 3) << -alphaU, 0, imageXSize / 2, 0, alphaV, imageYSize
//					/ 2, 0, 0, 1);
//	return calibMatrix;
//}
////This method computes rotation matrix using pan, tilt, and swing
//Mat rotationMatrixUsingPanTiltSwing(float pan, float tilt, float swing) {
//	Mat panMat =
//			(Mat_<float>(3, 3) << cosf(pan * 0.0174533), -sinf(pan * 0.0174533), 0, sinf(
//					pan * 0.0174533), cosf(pan * 0.0174533), 0, 0, 0, 1);
//	Mat tiltMat =
//			(Mat_<float>(3, 3) << 1, 0, 0, 0, cosf(tilt * 0.0174533), sinf(
//					tilt * 0.0174533), 0, -sinf(tilt * 0.0174533), cosf(
//					tilt * 0.0174533));
//	Mat swingMat = (Mat_<float>(3, 3) << cosf(swing * 0.0174533), sinf(
//			swing * 0.0174533), 0, -sinf(swing * 0.0174533), cosf(
//			swing * 0.0174533), 0, 0, 0, 1);
//	return swingMat * tiltMat * panMat;
//}
////This method computes the projection matrix
//Mat computeProjectionMatrixWithRemovedThirdColumn(float alphaU, float alphaV,
//		float imageXSize, float imageYSize, float pan, float tilt, float swing,
//		float cameraX, float cameraY, float cameraZ) {
//	Mat cameraMat = (Mat_<float>(3, 1) << cameraX, cameraY, cameraZ);
//	Mat negativeARC = -1
//			* calibrationMatrixWithMissingParams(alphaU, alphaV, imageXSize,
//					imageYSize)
//			* rotationMatrixUsingPanTiltSwing(pan, tilt, swing) * cameraMat;
//	Mat AR = calibrationMatrixWithMissingParams(alphaU, alphaV, imageXSize,
//			imageYSize) * rotationMatrixUsingPanTiltSwing(pan, tilt, swing);
//	Mat concat;
//	hconcat(AR, negativeARC, concat);
//	Mat res = Mat::zeros(3, 3, CV_32F);
//	//res.col(0) = concat.col(0);
//	res.at<float>(0, 0) = (float) concat.at<float>(0, 0);
//	res.at<float>(0, 1) = (float) concat.at<float>(0, 1);
//	res.at<float>(0, 2) = (float) concat.at<float>(0, 3);
//	res.at<float>(1, 0) = (float) concat.at<float>(1, 0);
//	res.at<float>(1, 1) = (float) concat.at<float>(1, 1);
//	res.at<float>(1, 2) = (float) concat.at<float>(1, 3);
//	res.at<float>(2, 0) = (float) concat.at<float>(2, 0);
//	res.at<float>(2, 1) = (float) concat.at<float>(2, 1);
//	res.at<float>(2, 2) = (float) concat.at<float>(2, 3);
//	return res;
//}
//
//
//Mat computeImageUsingHomogMat(Mat Homog, Mat srcImage, float sizeXOfOutImage,
//		float sizeYOfOutImage) {
//	Mat outputImage = Mat(sizeXOfOutImage, sizeYOfOutImage, srcImage.type());
//	Mat srcPoint = (Mat_<float>(3, 1) << 0.0, 0.0, 1.0);
//	Mat resPoint = (Mat_<float>(3, 1) << 0.0, 0.0, 1.0);
//	for (float i = 0; i < outputImage.rows; i++) {
//		for (float j = 0; j < outputImage.cols; j++) {
//			srcPoint.at<float>(0, 0) = i;
//			srcPoint.at<float>(1, 0) = j;
//			resPoint = Homog.inv() * srcPoint;
//			resPoint.at<float>(0, 0) = resPoint.at<float>(0, 0)
//					/ resPoint.at<float>(2, 0);
//			resPoint.at<float>(1, 0) = resPoint.at<float>(1, 0)
//					/ resPoint.at<float>(2, 0);
//			resPoint.at<float>(2, 0) = resPoint.at<float>(2, 0)
//					/ resPoint.at<float>(2, 0);
////			printf("%f%s%f", resPoint.at<float>(0, 0), ", ",
////					resPoint.at<float>(1, 0));
////			printf("\n");
//			outputImage.at<uchar>(i, j) = srcImage.at<uchar>(
//					abs(((int) resPoint.at<float>(0, 0))),
//					abs((int) resPoint.at<float>(1, 0)));
//		}
//	}
//	return outputImage;
//}
//
////This method outputs the 2nd image after applying the homography matrix on
////source image
//void drawPictureAfterApplyingHomog(float alphaU, float alphaV, float imageXSize,
//		float imageYSize, float pan, float tilt, float swing, float cameraX,
//		float cameraY, float cameraZ, Mat srcImage) {
//	Mat HomogMat = computeProjectionMatrixWithRemovedThirdColumn(alphaU, alphaV,
//			imageXSize, imageYSize, pan, tilt, swing, cameraX, cameraY,
//			cameraZ);
//	Mat show = computeImageUsingHomogMat(HomogMat, srcImage, imageXSize,
//			imageYSize);
//	imshow("show", show);
//}
//
////3rd Question Implementation
////This method is used to compute the m infinity matrix
//Mat mInfinityMat(float focalLenght, float width, float height, float FH,
//		float FW, float princX, float princY, float omegaLeft, float phiLeft,
//		float kappaLeft, Mat point) {
//	Mat mInfinitMat = ARMat(focalLenght, width, height, FH, FW, princX, princY,
//			omegaLeft, phiLeft, kappaLeft);
//	return mInfinitMat.inv() * point;
//}
////This method outputs the 3rd line needed
//Mat midpointMat(Mat firstImageInfinityMat, Mat secondImageInfinityMat,
//		float firstCameraX, float firstCameraY, float firstCameraZ,
//		float secondCameraX, float secondCameraY, float secondCameraZ) {
//	Mat identityMat = (Mat_<float>(3, 3) << 1, 0, 0, 0, 1, 0, 0, 0, 1);
//	Mat transPoseOfFirstMat;
//	transpose(firstImageInfinityMat, transPoseOfFirstMat);
//	Mat transPoseOfSecondMat;
//	transpose(secondImageInfinityMat, transPoseOfSecondMat);
//	Mat firstCameraMat =
//			(Mat_<float>(3, 1) << firstCameraX, firstCameraY, firstCameraZ);
//	Mat secondCameraMat =
//			(Mat_<float>(3, 1) << secondCameraX, secondCameraY, secondCameraZ);
//	Mat transFirstCamera;
//	transpose(firstCameraMat, transFirstCamera);
//	Mat transSecondCamera;
//	transpose(secondCameraMat, transSecondCamera);
//	Mat firstArg =
//			((identityMat - (firstImageInfinityMat * transPoseOfFirstMat))
//					+ (identityMat
//							- (secondImageInfinityMat * transPoseOfSecondMat)));
//	Mat mezawla1 = transFirstCamera * firstImageInfinityMat;
//	Mat mezawla2 = transSecondCamera * secondImageInfinityMat;
//
//	Mat secondArg = (firstCameraMat + secondCameraMat)
//			- (((mezawla1.at<float>(0,0)))
//					* firstImageInfinityMat)
//			- (((mezawla2.at<float>(0,0)))
//					* secondImageInfinityMat);
//	return firstArg.inv() * secondArg;
//}
//
//int main(int argc, char** argv) {
////calculateCameraCalMat(3, 3, 3, 3, 3, 3, 3);
////Mat test = calculateRotationMat(-35.16, -46.77, -155.57);
////	Mat test = epipoleLine(4.3, 640, 480, 2.7446, 3.5311, 320, 240, -35.16,
////			-46.77, -155.57, 25.03, -55.56, -47.08, -1535.21, 1720.84, 1693.22,
////			-1694.56, -142.25, 1585.19);
//	Mat point = (Mat_<float>(3,1) << 543, 207,1);
//	Mat point2 = (Mat_<float>(3,1) << 393, 431,60);
//
//	Mat src1 =
//			imread(
//					"/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment 2/a2/Question2/input.jpg");
//	Mat min1 = mInfinityMat(4.3, 640, 480, 2.7446, 3.5311, 320, 240, -35.16,
//			-46.77, -155.57,point);
//	Mat min2 = mInfinityMat(4.3, 640, 480, 2.7446, 3.5311, 320, 240, 25.03, -55.56, -47.08,point2);
//	Mat test = midpointMat(min1,min2,-1535.21, 1720.84, 1693.22,
//			-1694.56, -142.25, 1585.19);
//	//imshow("src1",src1);
////	Mat homog = computeProjectionMatrixWithRemovedThirdColumn(100, 100, 200,
////			200, 0, 50, 0, 350, 0, 300);
////	Mat test = computeImageUsingHomogMat(homog, src1, 200, 200);
//	//imshow("test", test);
////	for (int i = 0; i < test.rows; i++) {
////		for (int j = 0; j < test.cols; j++) {
////			printf("%f", test.at<float>(i, j));
////			printf(", ");
////		}
////		printf("\n");
////	}
////	Mat src1 =
////			imread(
////					"/media/mohamed/562A2A5F2A2A3BFD/GUC/semester 9/CV/Assignment 2/a2/Question2/input.jpg");
//	//drawPictureAfterApplyingHomog(100, 100, 200, 200,0, 50, 0, 350, 0, 300,src1);
//	//drawEpipoleLine(test);
////printf("%d",4.0);
////namedWindow("testMouse", 1);
////setMouseCallback("testMouse",callBackFun,NULL);
//	waitKey(100000);
//	return 0;
//}
//
