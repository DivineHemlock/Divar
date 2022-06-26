package com.example.divar3;

import java.io.File;

public class FileHolder {
    private static File pic;

    public static File getPic() {
        return pic;
    }

    public static void setPic(File pic) {
        FileHolder.pic = pic;
    }
}
