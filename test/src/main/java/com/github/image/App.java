/*
 * Copyright (c) 2017.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */

package com.github.image;


import cognitivej.vision.face.scenario.FaceScenarios;
import cognitivej.vision.overlay.builder.ImageOverlayBuilder;

import java.io.IOException;

import static java.security.Security.getProperty;

public class App {

    private static final String IMAGE_URL = "image.png";

    public static void main(String[] args) throws IOException {
        FaceScenarios faceScenarios = new FaceScenarios(getProperty("azure.cognitive.subscriptionKey"),
                getProperty("azure.cognitive.emotion.subscriptionKey"));
        String path = App.class.getClassLoader().getResource(IMAGE_URL).getPath();


        ImageOverlayBuilder imageOverlayBuilder = ImageOverlayBuilder.builder("file://"+path);
        faceScenarios.findFaces(path).stream().forEach(imageOverlayBuilder:: pixelateFaceOnImage);
        imageOverlayBuilder.launchViewer();
    }
}
