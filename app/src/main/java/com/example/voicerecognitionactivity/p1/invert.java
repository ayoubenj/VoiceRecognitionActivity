package com.example.voicerecognitionactivity.p1;
import javax.jws.WebService;

//Service Implementation
	@WebService(targetNamespace ="http://10.126.3.178:8080", endpointInterface = "p1.inverti")
	public class invert {


		public  String invertchaine(String input) {
			char[] in = input.toCharArray();
			int begin=0;
			int end=in.length-1;
			char temp;
				while(end>begin){
				temp = in[begin];
				in[begin]=in[end];
				in[end] = temp;
				end--;
				begin++;
		}
		return new String(in);
		};

	}

