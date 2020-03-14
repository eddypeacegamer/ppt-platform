package com.business.unknow.services.util.pdf;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

@Component
public class PDFGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(PDFGenerator.class.getSimpleName());
    private FopFactory FOP_FACTORY = null;

    
    @PostConstruct
    public void init() {
    	try {
    		InputStream confStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("pdf-config/fop.xconf");
            FOP_FACTORY = FopFactory.newInstance(new File(".").toURI(), confStream);
        } catch (SAXException | IOException e) {
            LOG.error("The FOP_FACTORY cannot be initialized", e);
        }
    }

    public void render(Reader in, OutputStream out, Reader xslt) {
        if (FOP_FACTORY == null) throw new RuntimeException("FOP FACTORY is not defined");

        try {
            Fop fop = FOP_FACTORY.newFop(MimeConstants.MIME_PDF, out);

            TransformerFactory tfact = TransformerFactory.newInstance();
            Transformer transformer = tfact.newTransformer(new StreamSource(xslt));
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new StreamSource(in), new SAXResult(fop.getDefaultHandler()));

        } catch (TransformerConfigurationException tce) {
            LOG.error("TransformerConfigurationException", tce);
            throw new RuntimeException(tce);
        } catch (TransformerException te) {
            LOG.error("TransformerException", te);
            throw new RuntimeException(te);
        } catch (FOPException e) {
            LOG.error("FOPException", e);
            throw new RuntimeException(e);
        }
    }
}
