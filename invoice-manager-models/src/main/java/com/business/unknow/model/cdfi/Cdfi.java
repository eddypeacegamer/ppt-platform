package com.business.unknow.model.cdfi;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cdfi:Comprobante")
@XmlType(propOrder = { "version", "serie", "folio", "sello", "emisor","receptor","conceptos"})
public class Cdfi {
	private String version;
	private String serie;
	private String folio;
	private Date fecha;
	private String sello;
	private Emisor emisor;
	private Receptor receptor;
	private List<Concepto> conceptos;

	@XmlAttribute
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlAttribute
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@XmlAttribute
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@XmlAttribute
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@XmlAttribute
	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	@XmlElement(name = "cdfi:Receptor")
	public Emisor getEmisor() {
		return emisor;
	}

	public void setEmisor(Emisor emisor) {
		this.emisor = emisor;
	}

	@XmlElement(name = "cdfi:Receptor")
	public Receptor getReceptor() {
		return receptor;
	}

	public void setReceptor(Receptor receptor) {
		this.receptor = receptor;
	}

	@XmlElementWrapper(name = "cdfi:Conceptos")
	@XmlElement(name = "cdfi:Conceptos")
	public List<Concepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}

	public static void main(String[] args) {

		Cdfi fact = new Cdfi();
		fact.setVersion("3.3");
		fact.setSello("asdasdasda");
		fact.setSerie("serie");
		fact.setFolio("123456");

		Emisor emisor = new Emisor();
		emisor.setNombre("emisor");
		emisor.setRfc("rfcEmisor");
		emisor.setRegimenFiscal("regimen fiscal");

		Receptor receptor = new Receptor();
		receptor.setNombre("receptor");
		receptor.setRfc("rfcReceptor");
		receptor.setUsoCdfi("G01");

		fact.setEmisor(emisor);
		fact.setReceptor(receptor);
		fact.setConceptos(new ArrayList<>());

		try {

			File file = new File("/Users/eej000f/Documents/personal-projects/1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Cdfi.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(fact, file);
			jaxbMarshaller.marshal(fact, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
