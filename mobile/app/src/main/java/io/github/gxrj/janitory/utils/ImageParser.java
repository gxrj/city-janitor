package io.github.gxrj.janitory.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ImageParser {

    public static String stringify( Bitmap bitmap ) {
        ByteArrayOutputStream compressedBitmap = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.PNG, 100, compressedBitmap );
        return Base64
                .getEncoder()
                .encodeToString( compressedBitmap.toByteArray() );
    }

    public static Bitmap toBitmap( String encodedImage ) {
        byte[] decodedBytes = Base64
                .getDecoder()
                .decode( encodedImage );

        return BitmapFactory
                .decodeByteArray(
                        decodedBytes, 0, encodedImage.length() );
    }

}
