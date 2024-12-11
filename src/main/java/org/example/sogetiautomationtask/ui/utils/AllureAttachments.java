package org.example.sogetiautomationtask.ui.utils;

import io.qameta.allure.Attachment;

public class AllureAttachments {

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }
}
