// cypress/integration/products_crud_spec.js

describe('CRUD Operations for Products', () => {
    beforeEach(() => {
      cy.visit('/productos');
    });
  
    it('should create a new product', () => {
      cy.contains('Añadir').click();
      cy.get('#nombreProducto').type('Nuevo Producto');
      cy.get('#descripcionProducto').type('Descripción del nuevo producto');
      cy.get('#origenProducto').type('Origen del producto');
      cy.get('#marcaProducto').type('Marca del producto');
      cy.get('#precioProducto').type('100');
      cy.get('#categoriaProducto').select('Categoría Editada');
      cy.contains('Guardar').click();
      cy.contains('Producto agregado exitosamente.');
    });
  
    it('should edit an existing product', () => {
      cy.contains('Nuevo Producto').parent().find('.btn-warning').click();
      cy.get('#nombreProducto').clear().type('Producto Editado');
      cy.get('#descripcionProducto').clear().type('Descripción editada');
      cy.get('#origenProducto').clear().type('Origen editado');
      cy.get('#marcaProducto').clear().type('Marca editada');
      cy.get('#precioProducto').clear().type('200');
      cy.contains('Actualizar').click();
      cy.contains('Producto actualizado exitosamente.');
    });
  
    it('should delete a product', () => {
      cy.contains('Producto Editado').parent().find('.btn-danger').click();
      cy.contains('Eliminar').click();
      cy.contains('Producto eliminado exitosamente.');
    });
  });