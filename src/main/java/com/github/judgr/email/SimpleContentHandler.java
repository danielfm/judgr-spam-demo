package com.github.judgr.email;

import java.io.InputStream;
import java.io.IOException;

import org.apache.james.mime4j.parser.AbstractContentHandler;
import org.apache.james.mime4j.stream.ContentDescriptor;
import org.apache.james.mime4j.stream.BodyDescriptor;
import org.apache.james.mime4j.MimeException;

class SimpleContentHandler extends AbstractContentHandler {
    public void body(BodyDescriptor bd, InputStream is)
        throws MimeException, IOException {
        System.out.println("Body detected, contents = "
                           + is + ", header data = " + bd);
    }
    public void field(String fieldData) throws MimeException {
        System.out.println("Header field detected: "
                           + fieldData);
    }
    public void startMultipart(BodyDescriptor bd) throws MimeException {
        System.out.println("Multipart message detexted, header data = "
                           + bd);
    }
}
