insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Marko', 'Markovic', 'U3dhZ2dlciByb2Nrcw==', '+381123123', 'marko@gmail.com', 'Bulevar Oslobodjenja 23', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, true);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Ana', 'Pajić', 'U3dhZ2dlciByb2Nrcw==', '+381124343', 'ana@gmail.com', 'Backa 41', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, true);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Pera', 'Perić', 'U3dhZ2dlciByb2Nrcw==', '+381444123', 'pera@gmail.com', 'Sremska 22', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, true);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Sima', 'Simić', 'U3dhZ2dlciByb2Nrcw==', '+38111123', 'sima@gmail.com', 'Sumadijska 111', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, true);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Zlata', 'Popov', 'U3dhZ2dlciByb2Nrcw==', '+3811111111', 'zlata@gmail.com', 'Danila Kisa 81', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, true);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Boris', 'Petrov', 'U3dhZ2dlciByb2Nrcw==', '+381123123', 'boki@gmail.com', 'Sarajevska 2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, true);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Dusko', 'Dabić', 'U3dhZ2dlciByb2Nrcw==', '+381133333', 'duki@gmail.com', 'Omladinska 3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, true);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Danka', 'Danić', 'U3dhZ2dlciByb2Nrcw==', '+381222222', 'danka@gmail.com', 'Fruskogorska 23', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, true);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Mara', 'Marić', 'U3dhZ2dlciByb2Nrcw==', '+381333333', 'mara@gmail.com', 'Crnogorska 18', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, false);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Siniša', 'Vasić', 'U3dhZ2dlciByb2Nrcw==', '+38189022', 'sinisa@gmail.com', 'Rumenacka 1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, false);
insert into user (name, surname, profile_picture, telephone_number, email, address, password, last_password_reset_date, blocked, active) values
    ('Darko', 'Lazić', 'U3dhZ2dlciByb2Nrcw==', '+381009333', 'darko@gmail.com', 'Zarka Zrenjanina 18', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '2022-01-01T12:33:24.893Z', false, false);

insert into passenger values(1);
insert into passenger values(2);
insert into passenger values(3);
insert into passenger values(4);

insert into admin values(9);
insert into admin values(10);
insert into admin values(11);

insert into location(address, latitude, longitude) values ('Bulevar Despota stefana 5A', 10.22, 25.94);
insert into location(address, latitude, longitude) values ('Bulevar Despota stefana 7', 788.23, 124.78);
insert into location(address, latitude, longitude) values ('Nikole Pasica 24', 1004.2, 5.26);
insert into location(address, latitude, longitude) values ('Bulevar Oslobodjenja 13', 19.223, 199.4758);
insert into location(address, latitude, longitude) values ('Fruskogorska 25', 19.223, 199.4758);

insert into passenger_favorite_locations (passenger_id, favorite_locations_id) values (1, 1);
insert into passenger_favorite_locations (passenger_id, favorite_locations_id) values (2, 2);
insert into passenger_favorite_locations (passenger_id, favorite_locations_id) values (3, 3);
insert into passenger_favorite_locations (passenger_id, favorite_locations_id) values (4, 4);

insert into route(departure_id, destination_id) values (1, 3);
insert into route(departure_id, destination_id) values (1, 4);
insert into route(departure_id, destination_id) values (2, 3);
insert into route(departure_id, destination_id) values (2, 4);
insert into route(departure_id, destination_id) values (2, 5);
insert into route(departure_id, destination_id) values (3, 5);

insert into vehicle_type(name, price_per_km) values (0, 150.5);
insert into vehicle_type(name, price_per_km) values (1, 300.2);
insert into vehicle_type(name, price_per_km) values (2, 225.7);

insert into vehicle(license_number, model, type_id, current_location_id, baby_transport, pet_transport, passenger_seats)
values ('123-T-321', 'Renault clio', 1, 1, true, false, 3);
insert into vehicle(license_number, model, type_id, current_location_id, baby_transport, pet_transport, passenger_seats)
values ('554-M-889', 'Golf 7', 1, 3, false, true, 3);
insert into vehicle(license_number, model, type_id, current_location_id, baby_transport, pet_transport, passenger_seats)
values ('879-T-446', 'Hummer H2 Limuzina', 2, 4, false, true, 9);
insert into vehicle(license_number, model, type_id, current_location_id, baby_transport, pet_transport, passenger_seats)
values ('123-T-321', 'Toyota Hiace Van', 3, 2, false, true, 6);

insert into driver (id, driving_license, vehicle_id) values (5, 00198456, 1);
insert into driver (id, driving_license, vehicle_id) values (6, 00223789, 2);
insert into driver (id, driving_license, vehicle_id) values (7, 00346523, 3);
insert into driver (id, driving_license, vehicle_id) values (8, 00478956, 4);

insert into document (document_image, name, driver_id) values ('U3dhZ2dlciByb2Nrcw==', 'document1', 5);
insert into document (document_image, name, driver_id) values ('U3dhZ2dlciByb2Nrcw==', 'document2', 6);
insert into document (document_image, name, driver_id) values ('U3dhZ2dlciByb2Nrcw==', 'document3', 7);
insert into document (document_image, name, driver_id) values ('U3dhZ2dlciByb2Nrcw==', 'document4', 8);

insert into user_activation (date, life, user_id) values ('2022-01-01T12:33:24.893Z', 180, 1);
insert into user_activation (date, life, user_id) values ('2022-01-01T11:42:24.893Z', 180, 2);
insert into user_activation (date, life, user_id) values ('2022-12-11T21:57:24.893Z', 180, 3);
insert into user_activation (date, life, user_id) values ('2022-02-18T14:18:24.893Z', 180, 4);

insert into rejection(reason, time_of_rejection, user_id) values ('Was rude to me', '2022-12-24T22:56:24.893Z', 1);
insert into rejection(reason, time_of_rejection, user_id) values ('Gave me some inappropriate comments',
                                                                  '2022-11-22T18:43:24.893Z', 5);

insert into ride(start_time, end_time, total_cost, estimated_time_in_minutes, status, baby_transport, pet_transport,
                 driver_id, rejection_id, vehicle_id)
values ('2023-01-13T16:20:24.893Z', null, 550.45, 190, 3, false, false, 5, 1, 1);
insert into ride(start_time, end_time, total_cost, estimated_time_in_minutes, status, baby_transport, pet_transport,
                 driver_id, rejection_id, vehicle_id)
values ('2023-01-13T16:00:24.893Z', null, 400.53, 180.89, 3, false, false, 6, 2, 2);
insert into ride(start_time, end_time, total_cost, estimated_time_in_minutes, status, baby_transport, pet_transport,
                 driver_id, rejection_id, vehicle_id)
values ('2023-01-13T19:00:24.893Z', null, 10000, 180, 1, false, true, 7, null, 3);
insert into ride(start_time, end_time, total_cost, estimated_time_in_minutes, status, baby_transport, pet_transport,
                 driver_id, rejection_id, vehicle_id)
values ('2023-01-13T19:00:24.893Z', null, 450, 180, 1, false, false, 8, null, 4);
insert into ride(start_time, end_time, total_cost, estimated_time_in_minutes, status, baby_transport, pet_transport,
                 driver_id, rejection_id, vehicle_id)
values ('2022-12-22T13:42:24.893Z', null, 350, 7, 1, false, false, 8, 1, 2);
insert into ride(start_time, end_time, total_cost, estimated_time_in_minutes, status, baby_transport, pet_transport,
                 driver_id, rejection_id, vehicle_id)
values ('2022-12-22T13:42:24.893Z', null, 350, 7, 1, false, false, 6, 2, 1);

insert into ride_passengers(ride_id, passengers_id) values (1, 1);
insert into ride_passengers(ride_id, passengers_id) values (1, 2);
insert into ride_passengers(ride_id, passengers_id) values (1, 3);
insert into ride_passengers(ride_id, passengers_id) values (2, 4);
insert into ride_passengers(ride_id, passengers_id) values (2, 1);
insert into ride_passengers(ride_id, passengers_id) values (3, 2);
insert into ride_passengers(ride_id, passengers_id) values (4, 3);
insert into ride_passengers(ride_id, passengers_id) values (4, 4);

insert into ride_locations(ride_id, locations_id) values (1, 1);
insert into ride_locations(ride_id, locations_id) values (2, 2);
insert into ride_locations(ride_id, locations_id) values (3, 3);
insert into ride_locations(ride_id, locations_id) values (4, 4);
insert into ride_locations(ride_id, locations_id) values (5, 5);
insert into ride_locations(ride_id, locations_id) values (6, 1);
insert into ride_locations(ride_id, locations_id) values (6, 6);

insert into working_hour(start, end, driver_id) values ('2022-12-24T20:54:24.893Z', '2022-12-24T23:54:24.893Z', 5);
insert into working_hour(start, end, driver_id) values ('2022-12-25T12:00:04.893Z', '2022-12-25T20:07:11.193Z', 5);
insert into working_hour(start, end, driver_id) values ('2022-11-22T20:54:24.893Z', '2022-11-23T04:11:55.453Z', 6);
insert into working_hour(start, end, driver_id) values ('2022-11-26T05:41:55.485Z', '2022-11-26T13:15:56.485Z', 6);
insert into working_hour(start, end, driver_id) values ('2022-12-15T11:02:45.456Z', '2022-12-15T21:05:45.456Z', 6);
insert into working_hour(start, end, driver_id) values ('2022-09-30T15:45:45.466Z', '2022-09-30T23:47:45.456Z', 7);
insert into working_hour(start, end, driver_id) values ('2022-10-10T16:22:22.156Z', '2022-10-11T04:12:59.486Z', 8);
insert into working_hour(start, end, driver_id) values ('2022-12-22T07:07:45.456Z', '2022-12-22T18:56:22.222Z', 8);

insert into message(message, time_of_sending, type, receiver_id, ride_id, sender_id) values ('Hi, I am on  location',
                                                                                             '2022-12-24T20:54:24.893Z', 1, 1, 1, 5);
insert into message(message, time_of_sending, type, receiver_id, ride_id, sender_id) values ('Please help me',
                                                                                             '2022-11-22T18:15:24.893Z', 2, 2, 1, 1);
insert into message(message, time_of_sending, type, receiver_id, ride_id, sender_id) values ('Hurry up, I am waiting',
                                                                                             '2022-12-24T20:54:24.893Z', 1, 1, 2, 5);
insert into message(message, time_of_sending, type, receiver_id, ride_id, sender_id) values ('Accept my unconditional love',
                                                                                             '2022-12-24T22:17:24.893Z', 1, 2, 2, 6);
insert into message(message, time_of_sending, type, receiver_id, ride_id, sender_id) values ('Your ride is here!',
                                                                                             '2022-12-24T22:17:24.893Z', 0, 1, 1, 3);
insert into message(message, time_of_sending, type, receiver_id, ride_id, sender_id) values ('Your ride is here!',
                                                                                             '2022-12-24T22:17:24.893Z', 0, 1, 2, 4);
insert into message(message, time_of_sending, type, receiver_id, ride_id, sender_id) values ('Your ride is here!',
                                                                                             '2022-12-15T20:17:24.893Z', 0, 3, 3, 4);
insert into message(message, time_of_sending, type, receiver_id, ride_id, sender_id) values ('Your ride is here!',
                                                                                             '2022-12-24T22:17:24.893Z', 0, 4, 4, 6);

insert into note(date, message, user_id) values ('2022-12-24T22:17:24.893Z', 'He is always late for his rides', 5);
insert into note(date, message, user_id) values ('2022-12-20T04:58:57.893Z', 'Sent inappropriate message to passenger', 6);
insert into note(date, message, user_id) values ('2022-10-11T09:11:07.893Z', 'Was rude to driver', 1);

insert into review(comment, rating, type, passenger_id, ride_id) values ('I did not like the driver at all', 1, 0, 1, 2);
insert into review(comment, rating, type, passenger_id, ride_id) values (null, 4, 0, 1, 2);
insert into review(comment, rating, type, passenger_id, ride_id) values ('Comfortable car!!!', 5, 1, 3, 2);
insert into review(comment, rating, type, passenger_id, ride_id) values ('', 0, 0, 3, 2);
insert into review(comment, rating, type, passenger_id, ride_id) values ('Everything was great', 5, 0, 3, 4);
insert into review(comment, rating, type, passenger_id, ride_id) values ('Great car', 5, 1, 3, 4);
insert into review(comment, rating, type, passenger_id, ride_id) values ('Music was toooooo loud', 3, 0, 4, 4);
insert into review(comment, rating, type, passenger_id, ride_id) values ('Car is could be newer -.-', 2, 1, 4, 4);

insert into role (name) values ('ROLE_PASSENGER');
insert into role (name) values ('ROLE_DRIVER');
insert into role (name) values ('ROLE_ADMIN');

insert into user_role (user_id, role_id) VALUES (1, 1); 
insert into user_role (user_id, role_id) VALUES (2, 1); 
insert into user_role (user_id, role_id) VALUES (3, 1);
insert into user_role (user_id, role_id) VALUES (4, 1); 
insert into user_role (user_id, role_id) VALUES (5, 2); 
insert into user_role (user_id, role_id) VALUES (6, 2);
insert into user_role (user_id, role_id) VALUES (7, 2); 
insert into user_role (user_id, role_id) VALUES (8, 2); 
insert into user_role (user_id, role_id) VALUES (9, 3); 
insert into user_role (user_id, role_id) VALUES (10, 3); 
insert into user_role (user_id, role_id) VALUES (11, 3); 