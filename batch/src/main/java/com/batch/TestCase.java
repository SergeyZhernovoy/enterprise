package com.batch;

import org.apache.commons.io.FilenameUtils;

public class TestCase {
    public static void main(String[] args) {

        String test = "catalog.zip";
        System.out.println(FilenameUtils.getBaseName(test) + "_prices." + FilenameUtils.getExtension(test));

    }
}
