<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ca">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertar Libro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="text-center my-4">Insertar Nuevo Libro</h1>
        <form action="insertarLibro" method="post">
            <div class="mb-3">
                <label for="titol" class="form-label">Título</label>
                <input type="text" class="form-control" id="titol" name="titol" required>
            </div>
            <div class="mb-3">
                <label for="isbn" class="form-label">ISBN</label>
                <input type="text" class="form-control" id="isbn" name="isbn" required>
            </div>
            <div class="mb-3">
                <label for="any_publicacio" class="form-label">Año de Publicación</label>
                <input type="number" class="form-control" id="any_publicacio" name="any_publicacio" required>
            </div>
            <div class="mb-3">
                <label for="editorial" class="form-label">Editorial</label>
                <select class="form-select" id="editorial" name="editorial" required>
                    <option value="1">Penguin Random House</option>
                    <option value="2">HarperCollins</option>
                    <option value="3">Simon & Schuster</option>
                    <option value="4">Macmillan Publishers</option>
                    <option value="5">Hachette Livre</option>
                </select>
            </div>
            <button type="submit" class="btn btn-success">Insertar Libro</button>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
