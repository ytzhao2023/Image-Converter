package org.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

/**
 * The MainController class is responsible for handling user interactions
 * with the main application UI, and updating the UI accordingly.
 */
public class MainController {

    @FXML
    private VBox imageInfoBox;
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox<String> formatComboBox;
    @FXML
    private Button downloadButton;
    private BufferedImage currentImage;
    private File currentImageFile;
    private ImageProcessing imageProcessor = new ImageManager();

    @FXML
    private void initialize() {
        // Populate the format combo box with image format options.
        formatComboBox.getItems().addAll("png", "jpg", "bmp");

        // Disable the download button until an image is uploaded.
        downloadButton.setDisable(true);
    }


    // Handles the action of uploading an image using a FileChooser dialog.
    // Once an image is selected, it displays the image and its properties.
    @FXML
    private void handleUpload() {
        FileChooser fileChooser = new FileChooser();
        currentImageFile = fileChooser.showOpenDialog(null);
        if (currentImageFile != null) {
            try {
                currentImage = imageProcessor.loadImage(currentImageFile.getAbsolutePath());
                Image image = new Image(currentImageFile.toURI().toString(), 100, 100, true, true);
                imageView.setImage(image);
                displayImageProperties();
                downloadButton.setDisable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Handles the action of downloading the currently displayed image
    // in the selected format to the user's file system.
    @FXML
    private void handleDownload() {
        if (currentImage != null && formatComboBox.getValue() != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("converted." + formatComboBox.getValue());
            File saveFile = fileChooser.showSaveDialog(null);
            if (saveFile != null) {
                try {
                    imageProcessor.saveImage(currentImage, formatComboBox.getValue(), saveFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayImageProperties() {
        int width = currentImage.getWidth();
        int height = currentImage.getHeight();

        imageInfoBox.getChildren().clear();
        imageInfoBox.getChildren().add(new Label("Filename: " + currentImageFile.getName()));
        imageInfoBox.getChildren().add(new Label("Width: " + width));
        imageInfoBox.getChildren().add(new Label("Height: " + height));

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(currentImageFile);
            // Try to read the camera make and model
            ExifIFD0Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (directory != null) {
                String cameraMake = directory.getString(ExifIFD0Directory.TAG_MAKE);
                String cameraModel = directory.getString(ExifIFD0Directory.TAG_MODEL);
                imageInfoBox.getChildren().addAll(
                        new Label("Camera make: " + (cameraMake != null ? cameraMake : "Unknown")),
                        new Label("Camera model: " + (cameraModel != null ? cameraModel : "Unknown"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
