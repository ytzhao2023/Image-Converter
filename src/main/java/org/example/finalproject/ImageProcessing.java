package org.example.finalproject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.drew.metadata.Metadata;

/**
 * The ImageProcessing interface defines the operations
 * that can be performed on an image such as loading,
 * saving, creating thumbnails, and reading metadata.
 */
public interface ImageProcessing {
    // Loads an image from the specified file path.
    BufferedImage loadImage(String path) throws IOException;

    // Saves a BufferedImage to the specified path with the given format.
    void saveImage(BufferedImage image, String format, String path) throws IOException;

    // Creates a thumbnail of a given BufferedImage.
    BufferedImage createThumbnail(BufferedImage original, int thumbnailWidth, int thumbnailHeight) throws IOException;

    // Reads the metadata from an image file.
    Metadata readMetadata(File imageFile) throws IOException;
}
