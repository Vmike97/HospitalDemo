insert into patient (first_Name, last_Name, age, patient_Id) values ('Navin', 'David', 44, 1337);
insert into patient (first_Name, last_Name, age, patient_Id) values ('David', 'Lay',36, 1892);
insert into patient (first_Name, last_Name, age, patient_Id) values ('Amelia', 'Lay',25, 1695);
insert into patient (first_Name, last_Name, age, patient_Id) values ('David', 'Palma',25, 4298);
insert into patient (first_Name, last_Name, age, patient_Id) values ('David', 'Gallo', 26, 5809);

insert into medicine values (default, 'Painkiller', 4, 'Analgin painkillers');
insert into medicine (name, effect, in_stock) values ('Vaseline', 'Painkiller', 2);
insert into medicine (name, effect, in_stock) values ('Propital', 'Healing', 1);
insert into medicine (name, effect, in_stock) values ('Splint', 'Repair fracture', 6);
insert into medicine values (default, 'Antibiotic', 3, 'Augmentin');
insert into medicine values (default, 'Painkiller', 7, 'Morphine');
insert into medicine values (default, 'Painkiller', 4, 'Ibuprofen');
insert into medicine values (default, 'Removes lgiht bleed', 15, 'Aseptic Bandage');
insert into medicine values (default, 'Surgery', 2, 'CMS Kit');
insert into medicine values (default, 'Removes heavy bleed', 12, 'Esmarch');
insert into medicine values (default, 'Increases weight limit (+50%)', 1, 'M.U.L.E. stimulant injector');

insert into prescription  values (default, 2, 1892);
insert into prescription (med_Id, p_ID) values (4, 1337);
insert into prescription (med_Id, p_ID) values (2, 1337);


/* To get prescription relation to patient with names
SELECT prescription.pre_id, patient.patient_id,  prescription.med_id, patient.first_name, patient.last_name, medicine.name
FROM prescription
INNER JOIN patient ON prescription.P_id=patient.Patient_id
INNER JOIN medicine ON prescription.med_id=medicine.med_id*/