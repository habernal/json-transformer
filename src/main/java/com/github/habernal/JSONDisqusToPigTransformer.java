package com.github.habernal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Locale;

/**
 * Reads all files in the given directory where each file is a array of JSON objects (the
 * output of Disqus API) and merges them into one file, where each entry is on a single
 * line (Pig JSON format)
 *
 * @author Ivan Habernal
 */
public class JSONDisqusToPigTransformer
{
    public static void main(String[] args)
            throws Exception
    {
        File inputDir = new File(args[0]);
        String outputFile = args[1];

        if (!inputDir.exists()) {
            throw new IOException("Input dir does not exist. " + inputDir);
        }

        if (!inputDir.isDirectory()) {
            throw new IllegalArgumentException(inputDir + " is not a directory");
        }

        File[] files = inputDir.listFiles();
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException(inputDir + " is empty");
        }

        System.out.printf(Locale.ENGLISH, "Found %d files%n", files.length);

        PrintWriter printWriter = new PrintWriter(outputFile);
        for (File file : files) {
            InputStream inputStream = new FileInputStream(file);

            appendJSONContent(inputStream, printWriter);

            inputStream.close();
        }

        // clean up
        printWriter.close();
    }

    /**
     * Loads JSON array from input stream and append each item in a new line in the print writer.
     *
     * @param inputStream Input stream with JSON
     * @param printWriter output
     * @throws IOException    exception
     * @throws ParseException exception
     */
    public static void appendJSONContent(InputStream inputStream, PrintWriter printWriter)
            throws IOException, ParseException
    {
        Object parse = new JSONParser().parse(new InputStreamReader(inputStream));

        JSONArray commentList = (JSONArray) parse;
        for (Object commentObj : commentList) {

            JSONObject comment = (JSONObject) commentObj;

            // print as a single line
            printWriter.println(comment);
        }

        printWriter.flush();
    }
}
