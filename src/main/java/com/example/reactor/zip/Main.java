package com.example.reactor.zip;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        String targetFolderPath = "/home/efggjjp/code/ddm/iota-dm-platform-config/subprojects/api-guide/build/reports/project/dependencies/";
        //String rawZipFilePath = "/home/efggjjp/Documents/api-guide.zip";
        String newZipFilePath = "/home/efggjjp/Documents/api-guide.zip";

        //将Zip文件解压缩到目标目录
        //ZipUtils.decompress(rawZipFilePath , targetFolderPath);

        String [] names = {"api-guide","configuration-api","configuration-domain", "configuration-validation", "fce-reference-impl", "link-registry", "pce-api-support", "pce-app", "pce-device-management-account", "pce-monolith", "pce-spi-library", "pce-spi-testkit", "schema-registry", "security", "shared-test-fixtures", "submission-engine"};

        //将目标目录的文件压缩成Zip文件
        ZipUtils.createZips(names);

    }


}
