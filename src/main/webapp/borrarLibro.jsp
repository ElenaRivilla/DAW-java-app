<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ca">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrar Libro</title>
    <!-- Incluyendo Bootstrap CSS desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="text-center my-4">Confirmar Borrado</h1>
        <%
            // Obtener los parámetros de la URL
            String libroId = request.getParameter("id");

            // Verificar que se haya recibido correctamente la información
            if (libroId == null || libroId.isEmpty()) {
        %>
            <div class="alert alert-danger text-center">
                <p>Error: Información del libro no proporcionada o incompleta.</p>
                <a href="Consulta" class="btn btn-primary">Volver</a>
            </div>
        <%
            } else {
        %>
            <div class="container text-center">
                <p>Estás seguro de que quieres borrar el libro?</p>
                <form action="borrarLibro" method="get">
                    <!-- Campo oculto para enviar el ID del libro -->
                    <input type="hidden" name="id" value="<%= libroId %>">
                    <button type="submit" class="btn btn-outline-danger">Sí, borrar</button>
                    <a href="Consulta" class="btn btn-secondary">Cancelar</a>
                </form>
            </div>
        <%
            }
        %>
    </div>
    <!-- Incluyendo Popper.js y Bootstrap JS desde CDN -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>