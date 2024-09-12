// cypress/integration/categories_crud_spec.js

describe('CRUD Operations for Categories', () => {
    beforeEach(() => {
      cy.visit('/categoria');
    });
  
    it('should create a new category', () => {
      cy.contains('Añadir').click();
      cy.get('#nombreCategoria').type('Nueva Categoría');
      cy.get('#descripcionCategoria').type('Descripción de la nueva categoría');
      cy.contains('Guardar').click();
      cy.contains('Categoría agregada exitosamente.');
    });
  
    it('should edit an existing category', () => {
      cy.contains('Nueva Categoría').parent().find('.btn-warning').click();
      cy.get('#nombreCategoria').clear().type('Categoría Editada');
      cy.get('#descripcionCategoria').clear().type('Descripción editada');
      cy.contains('Actualizar').click();
      cy.contains('Categoría actualizada exitosamente.');
    });
  
    it('should delete a category', () => {
      cy.contains('Categoría Editada').parent().find('.btn-danger').click();
      cy.contains('Eliminar').click();
      cy.contains('Categoría eliminada exitosamente.');
    });
  });