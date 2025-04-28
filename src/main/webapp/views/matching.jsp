<%--
  Created by IntelliJ IDEA.
  User: 24181
  Date: 2025/4/26
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="generator" content="">
    <title>Upload File</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/static/jquery/jquery-3.4.1.js"></script>
    <script src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.bundle.min.js"></script>
    <link href="<%=request.getContextPath()%>/static/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="head.jsp" />

<div class="container-fluid">
    <div class="row">
        <jsp:include page="nav.jsp">
            <jsp:param name="active" value="matching" />
        </jsp:include>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h2>Upload File</h2>
            </div>
            <div class="container">
                <form method="post" action="<%=request.getContextPath()%>/matching" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="uploaded_by">Uploaded By:</label>
                        <input type="text" class="form-control" id="uploaded_by" name="uploaded_by" required>
                    </div>
                    <div class="custom-file">
                        <label for="file">File:</label>
                        <input
                                type="file"
                                class="custom-file-input"
                                id="file"
                                name="file"
                                required
                                lang="en">
                        <label class="custom-file-label" for="file">Choose file</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Upload</button>
                </form>
            </div>
        </main>
    </div>
</div>

<script src="<%=request.getContextPath()%>/static/js/bs-custom-file-input.min.js"></script>
<script>
    bsCustomFileInput.init();
</script>

</body>
</html>