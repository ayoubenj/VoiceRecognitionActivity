package com.example.voicerecognitionactivity.p1;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService(targetNamespace ="http://10.126.3.178:8080")

public interface inverti{

	public String invertchaine(String chaine);

}
