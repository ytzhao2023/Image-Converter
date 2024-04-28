package org.example.finalproject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;

public class ImageManager implements ImageProcessing {

    @Override
    public BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    @Override
    public void saveImage(BufferedImage image, String format, String path) throws IOException {
        ImageIO.write(image, format, new File(path));
    }

    @Override
    public BufferedImage createThumbnail(BufferedImage original, int thumbnailWidth, int thumbnailHeight) throws IOException {
        // Create a new image with desired thumbnail size
        BufferedImage thumbnail = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = thumbnail.createGraphics();

        // Draw the original image resized into the thumbnail
        g2d.drawImage(original, 0, 0, thumbnailWidth, thumbnailHeight, null);
        g2d.dispose();

        return thumbnail;
    }

    @Override
    public Metadata readMetadata(File imageFile) throws IOException {
        try {
            // Use the metadata-extractor library to read metadata
            return ImageMetadataReader.readMetadata(imageFile);
        } catch (Exception e) {
            throw new IOException("Could not read metadata", e);
        }
    }
}
