// cypress/integration/navigation_spec.js

describe('App Navigation', () => {
    it('should navigate to the home page', () => {
      cy.visit('/');
      cy.contains('Inicio').click();
      cy.url().should('include', '/');
    });
  
    it('should navigate to the categories page', () => {
      cy.visit('/');
      cy.contains('Categorías').click();
      cy.url().should('include', '/categoria');
    });
  
    it('should navigate to the products page', () => {
      cy.visit('/');
      cy.contains('Productos').click();
      cy.url().should('include', '/productos');
    });
  });