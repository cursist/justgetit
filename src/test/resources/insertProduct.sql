insert into producten(naam, inkoopprijs, verkoopprijs, minimumprijs, omschrijving, voorraad, besteld, merkId, subcategorieId, versie)
values ('testP',1,10,10,'testP',12,2,(select merkId from merken where merken.naam='testM'),(select subcategorieId from subcategorieen where subcategorieen.naam = 'testSC'), 1);