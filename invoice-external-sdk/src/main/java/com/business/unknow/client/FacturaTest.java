package com.business.unknow.client;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequest;
import Utils.Responses.IResponse;
import Utils.Responses.SuccessAuthResponse;

public class FacturaTest extends SWService {

	public FacturaTest(String user, String password, String URI) {
		super(user, password, URI);
	}

	public FacturaTest(String token, String URI) {
		super(token, URI);
	}

	public IResponse Stamp(String xml, String version) throws AuthException, GeneralException {

		if (getToken() == null) {
			generateToken();
		}
		StampOptionsRequest settings;
		settings = new StampOptionsRequest(getToken(), getURI(), xml, version);

		StampRequest req = new StampRequest();
		return req.sendRequest(settings);
	}

	public SuccessAuthResponse Token() throws GeneralException, AuthException {
		AuthOptionsRequest settings;
		settings = new AuthOptionsRequest(getURI(), getUser(), getPassword());

		AuthRequest req = new AuthRequest();
		return (SuccessAuthResponse) req.sendRequest(settings);

	}

//	public static void main(String[] args) {
//		FacturaTest test = new FacturaTest("edgar.picon@cybord.net", "cybord+sw", "http://services.test.sw.com.mx");
//		try {
//			SuccessAuthResponse response = test.Token();
//			System.out.println(response.Status);
//			System.out.println(response.messageDetail);
//			System.out.println(response.token);
//
//			String cadena = "<?xml version=\"1.0\" encoding=\"utf-8\"?><cfdi:Comprobante xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\" Version=\"3.3\" Serie=\"Testing1\" Folio=\"515\" Fecha=\"2019-11-16T08:36:01\" Sello=\"IaH97Qt0Xv8mFZhqtX8QfzSkQeLpHbruKyIcnwRBcCg0G9hsXBvdFGrGQXPpoARTEXi3JIKYRLe72Kg5qWSxYZp+dC2xbXQFgp2b+dg2n9j0BOIrYG3xY9jg3IzJimAbcivGlKqv5CZ8oI8Fl0LVED2uYreIKJaLxMxAr3QYlNNeNgg5TuXNKnHgEJbp3OnlXbQzYTksgSMkZgrgJp9m7roNC6LEz7vnOd0cSItANgndpSdjVzn7lGm+V+dUJRW/zwctPQYJ2DypcjgGl7piyjRb2tlfTHn2+ESyCZE4JVC5GkVKNDKtmNGARuQBNeppeqVlXcPu/7D7DJIfK+fiGQ==\" FormaPago=\"04\" NoCertificado=\"20001000000300022815\" Certificado=\"MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=\" SubTotal=\"5000.00\" Moneda=\"MXN\" TipoCambio=\"1\" Total=\"5800.00\" TipoDeComprobante=\"I\" MetodoPago=\"PUE\" LugarExpedicion=\"01210\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><cfdi:Emisor Rfc=\"LAN7008173R5\" Nombre=\"CINDEMEX SA DE CV\" RegimenFiscal=\"601\" /><cfdi:Receptor Rfc=\"XEXX010101000\" Nombre=\"Cliente\" ResidenciaFiscal=\"USA\" NumRegIdTrib=\"152849858\" UsoCFDI=\"P01\" /><cfdi:Conceptos><cfdi:Concepto ClaveProdServ=\"81112201\" NoIdentificacion=\"001\" Cantidad=\"1\" ClaveUnidad=\"E48\" Unidad=\"E48\" Descripcion=\"Test\" ValorUnitario=\"5000.00\" Importe=\"5000.00\"><cfdi:Impuestos><cfdi:Traslados><cfdi:Traslado Base=\"5000.00\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"800.00\" /></cfdi:Traslados></cfdi:Impuestos></cfdi:Concepto></cfdi:Conceptos><cfdi:Impuestos TotalImpuestosTrasladados=\"800.00\"><cfdi:Traslados><cfdi:Traslado Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"800.00\" /></cfdi:Traslados></cfdi:Impuestos></cfdi:Comprobante>";
//			IResponse response2 = test.Stamp(cadena, "v1");
//			System.out.println(response2.message);
//			System.out.println(response2.messageDetail);
//		} catch (GeneralException e) {
//			e.printStackTrace();
//		} catch (AuthException e) {
//			e.printStackTrace();
//		}
//	}
//	
	public SecretKeySpec secretKey(String cipherKey) {
		try {
			byte[] key = cipherKey.getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			return new SecretKeySpec(key, "AES");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String encrypt(String strToEncrypt, String cipherKey) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey(cipherKey));
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
		String llave = "-----BEGIN PRIVATE KEY-----\n" + 
				"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCC+8KIIUMVVphf\n" + 
				"vF1VGGn/fxQta2j8soNJnLXKtcqlqoVeoRElYXvxgcp/c3kYGheDPAU+4Nf7mS9j\n" + 
				"nC1g3HH87C7ayE1moUWHoQqzKxAx4/mvPqQTWAVh6do8RQ/3OP6HRsvfFHlqqhOy\n" + 
				"oYXbiYoEuiJorjilcHMbOCzFmKyFUlUzpdvM0lWicRQ1sjbkOGBYB3zp/ZpTcGY4\n" + 
				"fYrVFnHjV21NPnpSyzMmWzi08U4Ov9xpa5kS5S8unUzeePsPK7zyaEPZ/7V8D+Du\n" + 
				"TNxT/W1ykVcX+sjL3cA++RTvaPC+SuemvOz5KcbzX78XXpqG4axvYRnzx0ofCrES\n" + 
				"TDOEzJNtAgMBAAECggEAZmlD4z3mfoOTZZ8YdlU9Y1fPxedqUdGdVuvhM5pJ1Jfr\n" + 
				"6V4T0t9SkjSByH7XmmGkKqa+DzirhntyEvbkgAw8T5220La11d2YSIBApm+wHOH7\n" + 
				"sepPTRSQOMsoJ+Fzpz93uvs+obAXcq+B8TPbPhhewm1qQ+CcR7YROaIFV4WUtjPT\n" + 
				"2yrh4+L1KhaZ1Tgd7RcDETBJTnWcX6/zOOYdMHq6gS/6Nua+EbycyVgz4TSQuxy0\n" + 
				"ngTJELlIfGk4YTITGhz+apbX6YzQl99BNuSi1cgxGuw0bd9QzsuDw9YOimjdfsjy\n" + 
				"f1aywZwYrfqEYehRHQTGpcIquk4UIpkitlPMeidwQQKBgQDZ54PYaKIPcGp9mVxO\n" + 
				"AX/rV4JmU0A4mzzETdEGLQaqXGBvjTQ4uvNIOf7F8TUd2i1MQNOVPGlPlKNKTnG6\n" + 
				"MuxoHRNxWPCYLYt1H6bsxYN1lVn+dqO983w2ANm2UBTiyimfTM6tbqbjtodQZvJ/\n" + 
				"mN3NNq/l1y8jRgyfqYhwCA35vQKBgQCZ4gd1j6O4tBe+tueRPWIcQxhElVD6ty0H\n" + 
				"OfBRhkOc6/dtyTpZDU0L15Z7D5eWgpKnhrdniRL59PcX6I9nIPY+BMgzzyr4sU3N\n" + 
				"7tK7M43aVQsvUByANzfs79dTTyFqYFnkEK7u9YQuH1FvBZITHov8ENCn6+3lWMD4\n" + 
				"arxOOfajcQKBgQCRhJCB1u5TDSmwktgXp9y7V4dXukTrCJB+L9FlJHCNGH+2P0Ae\n" + 
				"moqZM1G28qwcMXWKtMgXo25YnNSnbNd6F0PVC4XObizOE6xMHUUmXYi633BoBOa4\n" + 
				"lX/fuJWrsLMh+QvuvCZRn9GHxH987CMJnxaQUvMItLlf/wmzHk5lMpvSqQKBgGje\n" + 
				"wESfAm2i1jfTwJabpZwrIYPD2q8yXI57LsrwXG255iK8q3X6+ZHjd5sgMieRjs56\n" + 
				"ocz+fp84EZkKlYgrz4TOj323ZsqbUrHQW27WoIq1wGybBQFgIE3p2e3dlKFBTqF9\n" + 
				"uSoCyfUAxOeLfVRoUfATqY922uadCM2DOBd7qxkRAoGAUq47U4CIkIYp1SdcMCL5\n" + 
				"zD9gww2Qf9/g4KnAcy+Ec6EtBZQ5Wf6j131vfD6GxCMuCJJ4RUuha7mzGVqWtlDJ\n" + 
				"wmU5n6b0hpAVDf90B9AoIpy/lVbc0WyR1QnvwBeUSjL80yqP3p6nXBohs45XsbF1\n" + 
				"kEGx50X8z7yLGNYY34YDPIU=\n" + 
				"-----END PRIVATE KEY-----\n" + 
				"";
		FacturaTest test = new FacturaTest("edgar.picon@cybord.net", "cybord+sw", "http://services.test.sw.com.mx");
		String cadena=test.encrypt("||3.3|Testing1|515|2019-11-16T09:35:01|04|20001000000300022815|5000.00|MXN|1|5800.00|I|PUE|01210|LAN7008173R5|CINDEMEX SA DE CV|601|XEXX010101000|Cliente|USA|152849858|P01|81112201|001|1|E48|E48|Test|5000.00|5000.00|5000.00|002|Tasa|0.160000|800.00|002|Tasa|0.160000|800.00|800.000||", llave);
		System.out.println(cadena);
	}
}
