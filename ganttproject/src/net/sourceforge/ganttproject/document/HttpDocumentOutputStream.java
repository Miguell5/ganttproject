/*
 * Created on 18.08.2003
 *
 */
package net.sourceforge.ganttproject.document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import org.apache.webdav.lib.WebdavResource;

/**
 * This class implements an OutputStream for documents on
 * WebDAV-enabled-servers. It is a helper class for HttpDocument.
 *
 * @see HttpDocument
 * @author Michael Haeusler (michael at akatose.de)
 */
class HttpDocumentOutputStream extends ByteArrayOutputStream {

    private final HttpDocument myDocument;

    HttpDocumentOutputStream(HttpDocument document) {
        super();
        myDocument = document;
    }

    public void close() throws IOException {
        super.close();
        WebdavResource wr = myDocument.getWebdavResource();
        wr.lockMethod(myDocument.getUsername(), 60);
        try {
            if (!wr.putMethod(toByteArray())) {
                throw new IOException("Failed to write data: " + wr.getStatusMessage());
            }
        } catch (HTTPException e) {
            throw new IOException("Code: " + e.getStatusCode());
        } finally {
            wr.unlockMethod();
        }
    }

}
