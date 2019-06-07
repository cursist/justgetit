insert into subcategorieen (naam,categorieId) values('TestSubcategorie', (select categorieId from categorieen where naam = 'testD'));
insert into subcategorieen (naam,categorieId) values('Test2Subcategorie', (select categorieId from categorieen where naam = 'testD'));
insert into subcategorieen (naam,categorieId) values('testSC', (select categorieId from categorieen where naam = 'testD'));