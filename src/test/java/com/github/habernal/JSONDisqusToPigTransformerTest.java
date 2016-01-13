package com.github.habernal;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ivan Habernal
 */
public class JSONDisqusToPigTransformerTest
{

    @Test
    public void testTransformJSON()
            throws Exception
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        for (int i = 0; i < 3; i++) {

            InputStream stream = this.getClass().getClassLoader()
                    .getResourceAsStream("breitbartproduction_2085141126.txt");
            assertNotNull(stream);

            JSONDisqusToPigTransformer.appendJSONContent(stream, pw);
        }

        pw.close();

        System.out.println(sw.toString());
    }
}