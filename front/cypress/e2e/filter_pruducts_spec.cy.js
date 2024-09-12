// cypress/integration/filter_products_spec.js

describe('Filter Products by Category', () => {
  beforeEach(() => {
    cy.visit('/productos');
  });

  it('should filter products by category', () => {
    cy.get('select').select('Categoría Editada');
    cy.get('.card-title').each(($el) => {
      cy.wrap($el).should('contain', 'Categoría Editada');
    });
  });
});