package org.firstapp.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorMixerControllerTest {

    ColorMixerController testColor = new ColorMixerController();

    /**
     * Test result of sliders
     */
    @Test
    public void test() {
        // Test variables on start
        assertEquals(0, testColor.getValueS1());
        assertEquals(0, testColor.getValueS2());
        assertEquals(0, testColor.getValueS3());
        assertEquals(1, testColor.getTransparency());

        // Define RGB color function
        assertEquals(255, testColor.defineRGB(100));
        assertEquals(0, testColor.defineRGB(0));
        assertEquals(127, testColor.defineRGB(50));

        // Testing colors hex values
        assertEquals("#000", testColor.hexValue());

        testColor.setValueS1(255);
        testColor.setValueS2(255);
        testColor.setValueS3(255);
        assertEquals("#ffffff", testColor.hexValue());

        testColor.setValueS1(205);
        testColor.setValueS2(45);
        testColor.setValueS3(89);
        assertEquals("#cd2d59", testColor.hexValue());
    }
}