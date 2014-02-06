package com.df.angularfileupload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import portal.users.FacadeUser;

import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = -8244073279641189889L;

	private static final int BUFFER_SIZE = 2 * 1024 * 1024;
	private static FacadeUser facadeUser = new FacadeUser();
	public static final String BUCKETNAME = "caakmn";
	public static final String FILENAME = "YOUR_FILE_NAME";
	public static int count = 0;
	private final GcsService gcsService = GcsServiceFactory
			.createGcsService(new RetryParams.Builder()
			.initialRetryDelayMillis(10).retryMaxAttempts(10)
			.totalRetryPeriodMillis(15000).build());

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		ServletFileUpload upload = new ServletFileUpload();
		try {
			FileItemIterator iterator = upload.getItemIterator(req);
			while (iterator.hasNext()){
				FileItemStream itemStream = iterator.next();
				GcsFileOptions options = new GcsFileOptions.Builder().mimeType("image").acl("public-read").build();
				GcsOutputChannel outputChannel = gcsService.createOrReplace(new GcsFilename(BUCKETNAME, itemStream.getName()), options);
				
				copy(itemStream.openStream(), Channels.newOutputStream(outputChannel));
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		res.setContentType("application/json");
		res.getWriter().write("{ 'message' : 'success'}");
		res.getWriter().flush();
		res.getWriter().close();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.getWriter().write("{ 'message' : 'success'}");
		
		res.getWriter().flush();
		res.getWriter().close();
	}
	private void copy(InputStream input, OutputStream output)
			throws IOException {
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = input.read(buffer);
			while (bytesRead != -1) {
				output.write(buffer, 0, bytesRead);
				bytesRead = input.read(buffer);
			}
		} finally {
			input.close();
			output.close();
		}
	}

}