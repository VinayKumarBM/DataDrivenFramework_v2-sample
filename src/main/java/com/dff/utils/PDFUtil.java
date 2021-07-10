package com.dff.utils;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.dff.configs.GlobalVariables;

public class PDFUtil implements GlobalVariables{

	private static final Logger LOG = LogManager.getLogger(PDFUtil.class);

	public synchronized static String read(String fileName) throws InterruptedException {
		String text = null; 
		try {
			File pdfFile = new File(DOWNLOAD_FOLDER.concat(fileName));
			waitUntilDownloadCompleted(pdfFile);
			PDDocument doc = PDDocument.load(pdfFile);
			text = new PDFTextStripper().getText(doc);;
			LOG.info(text);			
		} catch (IOException e) {
			LOG.error("Error while reading"+fileName+" file", e);
		}
		return text;
	}

	private static void waitUntilDownloadCompleted(File pdfFile) throws InterruptedException {
		for(int i=0; i< DOWNLOAD_WAIT/IMPLICIT_WAIT; i++) {
			if (pdfFile.exists())
				break;
			Thread.sleep(IMPLICIT_WAIT*1000);
			System.out.println("Waiting for file download");
		}
	}
}
