package edu.mssm.pharm.maayanlab.Convertr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

@WebServlet(urlPatterns= {"/convert"})
public class Convertr extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8436947486144190717L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = request.getParameter("filename") + "." + request.getParameter("outputType");
		
		response.setHeader("Pragma", "public");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename  + "\"");		
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		byte[] decodedSVG = DatatypeConverter.parseBase64Binary(URLDecoder.decode(request.getParameter("data"), "UTF-8"));
		
		if (request.getParameter("outputType").equals("png")) {
			PNGTranscoder transcoder = new PNGTranscoder();
			TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(decodedSVG));
			TranscoderOutput output = new TranscoderOutput(response.getOutputStream());
			try {
				transcoder.transcode(input, output);
			} catch (TranscoderException e) {
				e.printStackTrace();
			}
		}
		else {
			response.getOutputStream().write(decodedSVG);
		}
		
		response.getOutputStream().close();
	}
}
