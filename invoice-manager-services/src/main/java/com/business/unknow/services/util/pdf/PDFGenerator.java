package com.business.unknow.services.util.pdf;

import com.business.unknow.model.dto.FacturaDto;
import org.apache.fop.apps.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class PDFGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(PDFGenerator.class.getSimpleName());
    private static FopFactory FOP_FACTORY = null;

    static {
        try {
            FOP_FACTORY = FopFactory.newInstance(
                    new File(ClassLoader.getSystemResource("pdf-config/fop.xconf")
                            .getFile()));
        } catch (SAXException | IOException e) {
            LOG.error("The FOP_FACTORY cannot be initialized", e);
        }
    }

    public void render(Reader in, OutputStream out, Reader xslt) {
        if(FOP_FACTORY == null) throw new RuntimeException("FOP FACTORY is not defined");

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

    public String generateFromXmlFile(String templateFilePath, String xmlFilePath, String outputDirectoryPath, String outputFileName) {
        try {
            return generatePDF(templateFilePath, getXmlFileContent(xmlFilePath), outputDirectoryPath, outputFileName);
        } catch (IOException e) {
            //TODO: Handle exception
            return null;
        }
    }

    public String generateFromFacturaDto(String templateFilePath, FacturaDto facturaDto, String outputDirectoryPath, String outputFileName) {
        return generatePDF(templateFilePath, getXmlDtoContent(facturaDto), outputDirectoryPath, outputFileName);
    }

    public String generateFromXmlContent(String templateFilePath, String xmlContent, String outputDirectoryPath, String outputFileName) {
        return generatePDF(templateFilePath, xmlContent, outputDirectoryPath, outputFileName);
    }

    private String generatePDF(String templateFilePath, String xmlContent, String outputDirectoryPath, String outputFileName) {
        try {
            File templateFile = new File(templateFilePath);
            File outputDirectory = new File(outputDirectoryPath);

            if (!outputDirectory.exists()) outputDirectory.mkdir();
            File outputFile = new File(outputDirectory, outputFileName);

            final FopFactory fopFactory = FopFactory.newInstance(outputDirectory.toURI());

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

            OutputStream out = new FileOutputStream(outputFile);
            out = new java.io.BufferedOutputStream(out);
            try {
                Fop fop;
                fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(new StreamSource(templateFile));

                Source src = new StreamSource(new StringReader(xmlContent));

                Result res = new SAXResult(fop.getDefaultHandler());
                transformer.transform(src, res);
                return outputFile.getAbsolutePath();
            } catch (FOPException | TransformerException e) {
                //TODO: Handle exception
            } finally {
                out.close();
            }
        } catch (IOException exp) {
            //TODO: Handle exception
        }

        return null;
    }

    private String getXmlDtoContent(FacturaDto dto) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FacturaDto.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(dto, sw);
            String xmlContent = sw.toString();
            return xmlContent;

        } catch (JAXBException e) {
            //TODO: Handle exception

        }

        return null;
    }

    private String getXmlFileContent(String xmlFilePath) throws IOException {
        File file = new File(xmlFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fileInputStream.read(data);
        fileInputStream.close();

        return new String(data, "UTF-8");
    }
}
