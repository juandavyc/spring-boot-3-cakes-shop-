package com.juandavyc.bakery.common.util;

import com.github.slugify.Slugify;

import java.util.Locale;

public class SlugUtil {


    private static final Slugify slugify = Slugify
            .builder()
            .lowerCase(true)
            .locale(Locale.forLanguageTag("es"))
            .build();

    public static String toSlug(String text){
        return slugify.slugify(text);
    }

}
