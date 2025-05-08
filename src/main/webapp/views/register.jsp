<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>用户注册</title>
  <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h2 class="text-center mb-4">用户注册</h2>
      <form action="<%= request.getContextPath() %>/register" method="post">
        <div class="mb-3">
          <label for="username" class="form-label">用户名</label>
          <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">密码</label>
          <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">注册</button>
      </form>
      <div class="mt-3 text-center">
        <a href="${pageContext.request.contextPath}/signin">已有账号？立即登录</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>