<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liberate PDF: Remove Restrictions from PDF files</title>
</head>
<body>

	<h1>Liberate PDF: Remove Restrictions from PDF files</h1>
	<p>PDFs may have several restrictions: They can be encrypted with a
		password, disallow printing them (why?), disallow copy their content
		(why??) or even disallow commenting them (why???). If you think that
		this is unnecessary discomfort, this tool is for you: Upload your PDF,
		provide a password (if any) and you will be rewarded with an usable
		PDF.</p>

	<form method="post" action="UploadPdf" enctype="multipart/form-data">
		<label for="pdfFileInput">Select file to upload:</label> <input
			type="file" name="pdfFile" id="pdfFileInput" required multiple /> <br />

		<label for="pdfPasswordInput">Password for the PDF (if any):</label> <input
			type="text" name="pdfPassword" id="pdfPasswordInput"
			placeholder="the password of the PDF (if any)" /> <br> <input
			type="submit" value="Remove Restrictions" />
	</form>

</body>
</html>
