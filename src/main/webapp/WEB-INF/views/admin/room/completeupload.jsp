<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
window.opener.formm.imagefilename.value='${image}';
window.opener.formm.getElementById('imagefilename').innerHTML='${originalFilename}';
self.close();
</script>

</head>
<body>

</body>
</html>