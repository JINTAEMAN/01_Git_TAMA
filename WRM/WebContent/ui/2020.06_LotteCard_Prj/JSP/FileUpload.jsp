<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page import="java.io.File
				, java.io.IOException
				, java.io.PrintWriter
				, java.util.List
				, java.text.SimpleDateFormat
				, java.util.Date
				, java.util.Locale
				, javax.servlet.ServletException
				, javax.servlet.http.HttpServlet
				, javax.servlet.http.HttpServletRequest
				, javax.servlet.http.HttpServletResponse
				, org.apache.commons.fileupload.FileItem
				, org.apache.commons.fileupload.disk.DiskFileItemFactory
				, org.apache.commons.fileupload.servlet.ServletFileUpload
				, java.lang.*"
%><%!

	// location to store file uploaded
	private static final String UPLOAD_DIRECTORY = "upload";
	
	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	

%><%
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	response.setHeader("Access-Control-Allow-Headers", "content-type,submissionid");
	//request.setCharacterEncoding("EUC-KR");
	request.setCharacterEncoding("UTF-8");		// 한글 깨짐 방지
	
	//System.out.printlnn("upload.jsp start==============");
	System.out.println("[//upload.jspl] ==> [TEST_01] [파일 업로드 @@@@] ===============================>  ");
	//System.out.println("[//upload.jspl] ==> [TEST_01] [파일 업로드 @@@@] [URL]"+ url +"[파일 경로]"+ filePath +"[파일 타입]"+ contType );
	
	// configures upload settings
	DiskFileItemFactory factory = new DiskFileItemFactory();
	
	// sets memory threshold - beyond which files are stored in disk
	factory.setSizeThreshold(MEMORY_THRESHOLD);
	
	// sets temporary location to store files
	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	// sets maximum size of upload file
	upload.setFileSizeMax(MAX_FILE_SIZE);
	
	// sets maximum size of request (include file + form data)
	upload.setSizeMax(MAX_REQUEST_SIZE);
	
	// constructs the directory path to store upload file
	// this path is relative to application's directory
	//String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
	String uploadPath = "/svc/ui/fileUpload";			// 파일 경로 @@@
	//String uploadPath = "/svc/ui/fileUpload/up";		// 파일 경로 @@@
	
	// creates the directory if it does not exist
	File uploadDir = new File(uploadPath);
	if (!uploadDir.exists()) {
		uploadDir.mkdir();
	}
	
	try {
		List<FileItem> formItems = upload.parseRequest(request);
		
		String savedfilename = "";
		String filename = "";
		String filesize = "";
		String callbackFunction = request.getParameter("callbackFunction");
		System.out.println("[//upload.jspl] ==> [TEST_21] [파일 업로드 @@@@] [formItems.size(]"+ formItems.size() );

		if (formItems != null && formItems.size() > 0) {
			
			for (FileItem item : formItems) {
				if (item.isFormField()) {
					String tmp = item.getString();
					if(tmp.indexOf("fakepath") > -1){
						// 단일 업로드에서 호출하는 경우
						tmp = tmp.substring(tmp.indexOf("fakepath")+9);	
					}
					String lu = new String(tmp.getBytes("8859_1"), "UTF-8");
					filename = lu;
					
					String fieldName = item.getFieldName();
					fieldName = new String(fieldName.getBytes("8859_1"), "UTF-8");
				}
				else
				{
					filename = new File(item.getName()).getName();
					System.out.println("[//upload.jspl] ==> [TEST_51] [파일 명]"+ filename );
					 
					SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMddHHmmssSSS", Locale.KOREA );
					String newdate = mSimpleDateFormat.format ( new Date() );
					//savedfilename = filename + "_" + newdate;		// 업로드 파일명
					savedfilename = newdate + newdate;		// 업로드 파일명
					System.out.println("[//upload.jspl] ==> [TEST_52] [업로드 파일명] [savedfilename]]"+ savedfilename );
					
					String filePath = uploadPath + File.separator + savedfilename;
					
					File storeFile = new File(filePath);
					item.write(storeFile);
					filesize = String.valueOf(storeFile.length());
				}
			}
		}
			
		StringBuilder stb1 = new StringBuilder();
		
		stb1.append("<script>window.onload = doInit;function doInit() {");
		stb1.append("parent."+callbackFunction+"(\"<ret>");
		stb1.append("<key>"+uploadPath+"</key>");			// 파일 경로
		stb1.append("<storedFileList>"+savedfilename+"</storedFileList>");	// 업로드 파일명
		stb1.append("<localfileList>"+filename+"</localfileList>");		// 원본 파일명
		stb1.append("<fileSizeList>"+filesize+"</fileSizeList>");		// 파일 크기
		stb1.append("<fileExt>"+filesize+"</fileExt>");		// 파일 확장자명 
		stb1.append("<maxUploadSize></maxUploadSize>");
		stb1.append("<deniedList></deniedList>");
		stb1.append("<deniedCodeList></deniedCodeList>");
		stb1.append("</ret>\");}</script>");	
		
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
		wrapper.setContentType("text/html;charset=UTF-8");
		wrapper.setHeader("Content-length", "" + stb1.toString().getBytes().length);
	
		response.getWriter().print(stb1.toString());
		
	} catch (Exception ex) {
		request.setAttribute("message", "There was an error: " + ex.getMessage());
	}
%>