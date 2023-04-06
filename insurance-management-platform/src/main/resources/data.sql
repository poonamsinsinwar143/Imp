INSERT INTO imp_policy_type_master (name, description) VALUES('Home Insurance', 'Coverage against damage or loss to home');
INSERT INTO imp_policy_type_master (name, description) VALUES('Motor Insurance', 'Coverage for your vehicle against damage, accidents, vandalism, theft');
INSERT INTO imp_policy_type_master (name, description) VALUES('Travel Insurance', 'Coverage for baggage loss, trip cancellation, or delay in flight');
INSERT INTO imp_policy_type_master (name, description) VALUES('Health Insurance', 'Coverage for hospitalization expenses up to the sum insured');


INSERT INTO imp_claim_status_master (name, description) VALUES('PENDING', 'Claim is pending');
INSERT INTO imp_claim_status_master (name, description) VALUES('APPROVED', 'Claim approved');
INSERT INTO imp_claim_status_master (name, description) VALUES('REJECTED', 'Claim rejected');


INSERT INTO imp_client (name, dob, address, city, state, country, zipcode, email, phone, created_on, updated_on) VALUES('Geeta Sharma', '1993-08-16', 'G104, Shree Vardhman Mantra', 'Gurgaon', 'Haryana', 'India', '122102', 'sc5896@gmail.com', '9867987656', '2023-03-31 00:45:14', NULL);
INSERT INTO imp_client (name, dob, address, city, state, country, zipcode, email, phone, created_on, updated_on) VALUES('Poonam Kumari', '1996-10-15', 'G204, SVM Ansal Esencia', 'Jaipur', 'Rajasthan', 'India', '122001', 'poonam5896@gmail.com', '9756982436', '2023-03-31 19:31:31', NULL);
INSERT INTO imp_client (name, dob, address, city, state, country, zipcode, email, phone, created_on, updated_on) VALUES('Varsha', '1993-08-16', 'G104, Shree Vardhman Mantra', 'Gurgaon', 'Haryana', 'India', '122102', 'test@gmail.com', '9267987656', '2023-04-02 21:49:58', NULL);
INSERT INTO imp_client (name, dob, address, city, state, country, zipcode, email, phone, created_on, updated_on) VALUES('Sanju', '1993-08-16', 'G104, Shree Vardhman Mantra', 'Gurgaon', 'Haryana', 'India', '122102', 'abc@gmail.com', '8867987655', '2023-04-05 00:34:33', NULL);
INSERT INTO imp_client (name, dob, address, city, state, country, zipcode, email, phone, created_on, updated_on) VALUES('Pooja', '1993-08-16', 'G104, Shree Vardhman Mantra', 'Gurgaon', 'Haryana', 'India', '122102', 'bharat@gmail.com', '6756764567', '2023-04-05 00:43:48', NULL);

INSERT INTO imp_insurance_policy (policy_type, coverage_amount, premium, start_date, end_date, client, created_on, updated_on) VALUES(4, 90000.0, 6000.0, '2020-03-03', '2021-03-03', 1, '2023-04-02 00:22:27', NULL);

insert into imp_claim (claim_amount, claim_date, claim_status, created_on, description, policy, updated_on) values (100000, '2023-04-04', 1, '2023-04-05 10:42:24', 'Claim is created.', 1, NULL);


