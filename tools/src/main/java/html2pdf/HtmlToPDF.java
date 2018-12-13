package html2pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;


import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;



public class HtmlToPDF {
	public static void main(String[] args) {
		//HtmlToPDF.createPDF1();
		HtmlToPDF.createPDF2();
		
	}
	/*
	 * itex-2.0.8.jar
	 * core-renderer-R8.jar
	 */
	public static void createPDF1() {
		ITextRenderer renderer = new ITextRenderer(); 
		try {
			renderer.setDocument(new File("E:/jar/jxfp.html"));
			// 解决中文问题  
			ITextFontResolver fontResolver = renderer.getFontResolver();  
			//System.out.println(request.getSession().getServletContext().getRealPath("fonts"));
			try {  
				fontResolver.addFont("E:/jar/simsun.ttc",  BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
			} catch (DocumentException e) {  
				// TODO Auto-generated catch block  
				e.printStackTrace();  
			} 
			renderer.layout();  
			try {
				renderer.createPDF(new FileOutputStream(new File("E:/jar/jxfp7.pdf"))); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/*
	 * itexpdf-5.4.1.jar
	 * xmlworker-5.4.1.jar
	 */
	public static void createPDF2() {
		try {
	        Document document = new Document();
	        FileOutputStream fos = new FileOutputStream("E:/jar/jxfp6.pdf");
	        PdfWriter writer = PdfWriter.getInstance(document, fos);
	        document.open();
	     // 使用我们的字体提供器，并将其设置为unicode字体样式
			XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider();
			fontProvider.getFont("E:/jar/simsun.ttc");
	        fontProvider.addFontSubstitute("lowagie", "garamond");
	        fontProvider.setUseUnicode(true);
	        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	        XMLWorkerHelper.getInstance().getDefaultCssResolver(false);

	        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("E:/jar/jxfp.html"), null, Charset.forName("UTF-8"),fontProvider);

	        document.close();
	        writer.close();
	        
			System.out.println("转换成功！");  
			fos.close();
		} catch (Exception e) {
			System.out.println("转换异常");
			e.printStackTrace();
		}
		
	}
	/*
	 * flying-saucer-pdf-itext5-9.0.1.jar
	 * 还是要用itext-2.0.8.jar包
	 */
	
}
