package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//tre sa am o clasa pt imag care gestioneaza op cu ele
public class ImageRepository {
    private final List<ImageItem> images;

    public ImageRepository() {
        this.images = new ArrayList<>();
    }

    public void addImage(ImageItem image) {
        images.add(image);
    }
   //metoda care deschide imaginea
    public void displayImage(ImageItem image) {
        File imageFile = new File(image.path());
        if (!imageFile.exists()) {
            System.err.println("Error: The file " + image.path() + " does not exist.");
            return;
        }
        //verific daca desktopul e suportat
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(imageFile);
            } catch (IOException e) {
                System.out.println("Error: exception thrown while opening the file " );

            }

        } else {
            System.out.println("Desktop is not supported on this system.");
        }
    }
//metoda care ret lista de imag.
    public List<ImageItem> getImages() {
        return new ArrayList<>(images);
    }
}


