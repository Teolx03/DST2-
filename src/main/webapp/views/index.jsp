<%--
  Created by IntelliJ IDEA.
  User: hello
  Date: 2019-12-3
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="generator" content="">
    <title>Dashboard Template · Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/static/jquery/jquery-3.4.1.js"></script>
    <script src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/static/css/app.css" rel="stylesheet">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
</head>
<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Precision Medicine Matching System</a>

</nav>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="nav.jsp" >
            <jsp:param name="active" value="dashboard" />
        </jsp:include>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h2>Dashboard</h2>
            </div>
            <div class="mx-auto">
                <h4 class="text-center fw-bold mb-4">Welcome to the Precision Medicine Matching System</h4>

                <p class="mb-5">
                    The <strong>Precision Medicine Matching System</strong> is a user-friendly platform which enables researchers and clinicians to easily explore pharmacogenomic <strong>drug labels</strong>,
                    <strong>dosing guidelines</strong>, and <strong>genetic information</strong>, helping to accelerate precision healthcare and research.
                </p>
            </div>

            <!-- 🧬 Matching Section -->
            <div class="bg-light p-3 mb-4 rounded shadow-sm">
                <h5 class="fw-bold mb-2">🧬 Matching</h5>
                <p class="mb-0">
                    This page allows users to upload their genetic variant files for analysis. Uploaded variants are matched against pre-annotated data to identify pharmacogenomic relevance, helping users explore how genetic differences may affect drug responses.
                </p>
            </div>

            <!-- 📂 Samples Section -->
            <div class="bg-light p-3 mb-4 rounded shadow-sm">
                <h5 class="fw-bold mb-2">📂 Samples</h5>
                <p class="mb-0">
                    This page manages the uploaded genetic variant files. Users can view and manage their submitted samples, including information about the uploader and upload time, ensuring organized tracking of genetic data.
                </p>
            </div>


            <!-- 🧬 Drugs Section -->
            <div class="bg-light p-3 mb-4 rounded shadow-sm">
                <h5 class="fw-bold mb-2">
                    🧬 Drugs
                </h5>
                <p class="mb-0">
                    This page displays a curated list of pharmacogenomic-related drugs sourced from PharmGKB. For each drug, users can view its unique identifier, name, and a direct link to its official PharmGKB page for more detailed information.
                    Additionally, related drug labels, dosing guideline IDs, and biomarker status are provided to help users explore the genetic factors influencing drug responses.
                </p>
            </div>

            <!-- 📝 Drug Labels Section -->
            <div class="bg-light p-3 mb-4 rounded shadow-sm">
                <h5 class="fw-bold mb-2">
                    📝 Drug Labels
                </h5>
                <p class="mb-0">
                    This page presents a curated collection of pharmacogenomic drug labels from PharmGKB.
                    Each label includes a unique ID, source organization, dosing information, and a summary highlighting
                    important genetic factors affecting drug efficacy, safety, and dosing.
                </p>
            </div>

            <!-- 📋 Dosing Guidelines Section -->
            <div class="bg-light p-3 mb-4 rounded shadow-sm">
                <h5 class="fw-bold mb-2">
                    📋 Dosing Guidelines
                </h5>
                <p class="mb-0">
                    This page provides curated pharmacogenomic dosing guidelines sourced from PharmGKB.
                    Each guideline links a drug to genetic factors that influence dosing decisions,
                    highlighting actionable recommendations where available.
                </p>
            </div>

            <!-- 💬 Comment Section -->
            <div class="bg-light p-3 mb-4 rounded shadow-sm">
                <h5 class="fw-bold mb-2">💬 Comment Section</h5>
                <p class="mb-0">
                    This page provides a communication area where users can leave comments, feedback, or discuss pharmacogenomic findings, supporting collaboration and knowledge sharing within the platform.
                </p>
            </div>

            <!-- 🔎 Query Section -->
            <div class="bg-light p-3 mb-5 rounded shadow-sm">
                <h5 class="fw-bold mb-2">🔎 Query</h5>
                <p class="mb-0">
                    This page allows users to search for specific drug IDs or names to quickly retrieve relevant dosing guidelines and pharmacogenomic information, enhancing accessibility to precision medicine knowledge.
                </p>
            </div>



        </main>
    </div>
</div>
</body>
</html>
