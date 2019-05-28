insert into subcategorieen(subcategorieId, naam, minimumMargePercent, minimumMargeBedrag, categorieId)
values (1,'testSC',10,15,(select categorieId from categorieen where categorieen.naam = 'testC'));