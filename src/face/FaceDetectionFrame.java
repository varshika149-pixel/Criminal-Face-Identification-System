package face;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;
import java.io.File;

public class FaceDetectionFrame {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(null);

        if(result != JFileChooser.APPROVE_OPTION){
            return;
        }

        File file = chooser.getSelectedFile();

        String imagePath = file.getAbsolutePath();

        Mat image = Imgcodecs.imread(imagePath);
        System.out.println("Image Loaded: " + !image.empty());
        String xmlPath = "models/haarcascade_frontalface_default.xml";

        File xml = new File(xmlPath);

        System.out.println("XML Absolute Path: " + xml.getAbsolutePath());
        System.out.println("File Exists: " + xml.exists());

        CascadeClassifier detector = new CascadeClassifier(xmlPath);
        if(detector.empty()){
            System.out.println("Cannot load Haar Cascade XML");
            return;
        }

        org.opencv.core.MatOfRect faces =
                new org.opencv.core.MatOfRect();

        detector.detectMultiScale(image, faces);

        Rect[] faceArray = faces.toArray();

        System.out.println("Faces Detected : " + faceArray.length);

        for(Rect rect : faceArray){

            Imgproc.rectangle(
                    image,
                    rect,
                    new Scalar(0,255,0),
                    3
            );

        }

        Imgcodecs.imwrite("images/output.jpg", image);

        System.out.println("Output saved inside images folder.");

    }

}