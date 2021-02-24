Insert into VTS_BRANCH
   (ID, name, address, phone, open_time, close_time)
 Values
   (1, 'MOH branch 1', 'Cairo', '123456', PARSEDATETIME('08:00', 'HH:MM'), PARSEDATETIME('20:00', 'HH:MM'));

Insert into VTS_BRANCH
   (ID, name, address, phone, open_time, close_time)
 Values
   (2, 'MOH branch 2', 'Alex', '456789', PARSEDATETIME('08:00', 'HH:MM'), PARSEDATETIME('20:00', 'HH:MM'));

Insert into VTS_BRANCH
   (ID, name, address, phone, open_time, close_time)
 Values
   (3, 'Vaccination Center', 'Giza', '223344', PARSEDATETIME('09:00', 'HH:MM'), PARSEDATETIME('22:00', 'HH:MM'));


Insert into VTS_BRANCH_STORE
   (ID, branch_id, vaccine_id, quantity, name, address, production_date, expiration_date)
 Values
   (1, 1, 1, 200, 'MOH branch 1 store 1', 'Cairo', PARSEDATETIME('20-01-2020', 'dd-MM-yyyy'), PARSEDATETIME('20-01-2023', 'dd-MM-yyyy'));

Insert into VTS_BRANCH_STORE
   (ID, branch_id, vaccine_id, quantity, name, address, production_date, expiration_date)
 Values
   (2, 1, 2, 200, 'MOH branch 1 store 1', 'Cairo', PARSEDATETIME('25-07-2020', 'dd-MM-yyyy'), PARSEDATETIME('25-07-2022', 'dd-MM-yyyy'));

Insert into VTS_BRANCH_VISITS
   (ID, branch_id, vaccine_id, client_id, visit_date, status, payment_method)
 Values
   (1, 1, 3, 1, PARSEDATETIME('25-03-2021 19:30', 'dd-MM-yyyy HH:mm'), 'SCHEDULED', 'CASH');

Insert into VTS_BRANCH_VISITS
   (ID, branch_id, vaccine_id, client_id, visit_date, status, payment_method)
 Values
   (2, 1, 3, 2, PARSEDATETIME('25-01-2021 12:30', 'dd-MM-yyyy HH:mm'), 'COMPLETED', 'FAWRY');