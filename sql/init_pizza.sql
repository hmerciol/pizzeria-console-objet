CREATE DATABASE pizzeria;

CREATE TABLE pizzeria.pizza (
	pizza_id INT PRIMARY KEY AUTO_INCREMENT,
    pizza_code CHAR(3),
    pizza_nom VARCHAR(20),
    pizza_categorie VARCHAR(20),
    pizza_prix FLOAT(5,2));

INSERT INTO pizzeria.pizza (pizza_code, pizza_nom, pizza_categorie, pizza_prix) VALUES 
	('PEP', 'Pépéroni', 'VIANDE', 12.50),
	('MAR', 'Margherita', 'VIANDE', 14.00),
	('REI', 'La Reine', 'VIANDE', 11.50),
	('FRO', 'La 4 fromages', 'SANS_VIANDE', 12.00),
	('CAN', 'La cannibale', 'VIANDE', 12.50),
	('SAV', 'La savoyarde', 'VIANDE', 13.00),
	('ORI', 'L''orientale', 'VIANDE', 13.50),
	('IND', 'L''indienne', 'VIANDE', 14.00);
