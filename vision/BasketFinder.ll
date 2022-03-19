//
// Inputs
//
Inputs
{
	Mat source1;
}

//
// Variables
//
Outputs
{
	Mat resizeImageOutput;
	Mat blur0Output;
	Mat hsvThresholdOutput;
	Mat cvErodeOutput;
	Mat cvDilateOutput;
	Mat blur1Output;
	ContoursReport findContoursOutput;
}

//
// Steps
//

Step Resize_Image0
{
    Mat resizeImageInput = source1;
    Double resizeImageWidth = 320.0;
    Double resizeImageHeight = 240.0;
    InterpolationType resizeImageInterpolation = CUBIC;

    resizeImage(resizeImageInput, resizeImageWidth, resizeImageHeight, resizeImageInterpolation, resizeImageOutput);
}

Step Blur0
{
    Mat blur0Input = resizeImageOutput;
    BlurType blur0Type = BOX;
    Double blur0Radius = 0.9009009009009009;

    blur(blur0Input, blur0Type, blur0Radius, blur0Output);
}

Step HSV_Threshold0
{
    Mat hsvThresholdInput = blur0Output;
    List hsvThresholdHue = [64.74820143884892, 108.18336162988116];
    List hsvThresholdSaturation = [116.95143884892086, 255.0];
    List hsvThresholdValue = [110.07194244604318, 255.0];

    hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);
}

Step CV_erode0
{
    Mat cvErodeSrc = hsvThresholdOutput;
    Mat cvErodeKernel;
    Point cvErodeAnchor = (-1, -1);
    Double cvErodeIterations = 0.0;
    BorderType cvErodeBordertype = BORDER_CONSTANT;
    Scalar cvErodeBordervalue = (-1);

    cvErode(cvErodeSrc, cvErodeKernel, cvErodeAnchor, cvErodeIterations, cvErodeBordertype, cvErodeBordervalue, cvErodeOutput);
}

Step CV_dilate0
{
    Mat cvDilateSrc = cvErodeOutput;
    Mat cvDilateKernel;
    Point cvDilateAnchor = (-1, -1);
    Double cvDilateIterations = 3.0;
    BorderType cvDilateBordertype = BORDER_CONSTANT;
    Scalar cvDilateBordervalue = (-1);

    cvDilate(cvDilateSrc, cvDilateKernel, cvDilateAnchor, cvDilateIterations, cvDilateBordertype, cvDilateBordervalue, cvDilateOutput);
}

Step Blur1
{
    Mat blur1Input = cvDilateOutput;
    BlurType blur1Type = GAUSSIAN;
    Double blur1Radius = 5.6603773584905674;

    blur(blur1Input, blur1Type, blur1Radius, blur1Output);
}

Step Find_Contours0
{
    Mat findContoursInput = blur1Output;
    Boolean findContoursExternalOnly = false;

    findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);
}




