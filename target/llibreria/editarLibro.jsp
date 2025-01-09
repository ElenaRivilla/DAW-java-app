<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%
    String errorMessage = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="ca">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Editar Libro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 class="text-center my-4">Editar Libro</h1>

            <% if (errorMessage != null) {%>
            <div class="alert alert-danger"><%= errorMessage%></div>
            <% }%>

            <form action="editarLibro" method="post">
                <input type="hidden" name="id" value="<%= request.getParameter("id") %>">

                <!-- Campo Título -->
                <div class="mb-3">
                    <label for="titol" class="form-label">Título</label>
                    <input type="text" class="form-control" id="titol" name="titol" value="<%= request.getAttribute("titol") != null ? request.getAttribute("titol") : "" %>">
                </div>

                <!-- Campo ISBN -->
                <div class="mb-3">
                    <label for="isbn" class="form-label">ISBN</label>
                    <input type="text" class="form-control" id="isbn" name="isbn" value="<%= request.getAttribute("isbn") != null ? request.getAttribute("isbn") : "" %>">
                </div>

                <!-- Campo Año de Publicación -->
                <div class="mb-3">
                    <label for="any_publicacio" class="form-label">Año de Publicación</label>
                    <input type="number" class="form-control" id="any_publicacio" name="any_publicacio" value="<%= request.getAttribute("anyPublicacio") != null ? request.getAttribute("anyPublicacio") : "" %>">
                </div>

                <!-- Campo Editorial -->
                <div class="mb-3">
                    <label for="editorial" class="form-label">Editorial</label>
                    <select class="form-select" id="editorial" name="editorial">
                        <option value="1" <%= (request.getAttribute("idEditorial") != null && (int) request.getAttribute("idEditorial") == 1 ? "selected" : "") %>>Penguin Random House</option>
                        <option value="2" <%= (request.getAttribute("idEditorial") != null && (int) request.getAttribute("idEditorial") == 2 ? "selected" : "") %>>HarperCollins</option>
                        <option value="3" <%= (request.getAttribute("idEditorial") != null && (int) request.getAttribute("idEditorial") == 3 ? "selected" : "") %>>Simon & Schuster</option>
                        <option value="4" <%= (request.getAttribute("idEditorial") != null && (int) request.getAttribute("idEditorial") == 4 ? "selected" : "") %>>Macmillan Publishers</option>
                        <option value="5" <%= (request.getAttribute("idEditorial") != null && (int) request.getAttribute("idEditorial") == 5 ? "selected" : "") %>>Hachette Livre</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-warning">Actualizar Libro</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    </body>
</html>