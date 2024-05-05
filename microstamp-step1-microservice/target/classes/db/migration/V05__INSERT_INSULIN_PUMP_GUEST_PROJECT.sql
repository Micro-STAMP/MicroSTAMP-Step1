INSERT INTO projects (id, description, name, type, url, user_id)
VALUES(1, "Insulin pump example", "Insulin pump with continuous glucose monitor", null, null, 3);


INSERT INTO system_goals (id, name, project_id)
VALUES(1, "G-1: monitor the patient’s glucose level", 1);

INSERT INTO system_goals (id, name, project_id)
VALUES(2, "G-2: control the injection of insulin", 1);

INSERT INTO system_goals (id, name, project_id)
VALUES(3, "G-3: provide alerts about the system’s operation", 1);


INSERT INTO assumptions (id, name, project_id)
VALUES(1, "A-1: The system operates with a smartphone with Internet connection", 1);

INSERT INTO assumptions (id, name, project_id)
VALUES(2, "A-2: The smartphone has an app to aid the control", 1);


INSERT INTO losses (id, name, project_id)
VALUES(1, "L-1: Patient is injured or killed from overdose or underdose", 1);

INSERT INTO losses (id, name, project_id)
VALUES(2, "L-2: Loss of the manufacturer’s credibility", 1);

INSERT INTO losses (id, name, project_id)
VALUES(3, "L-3: Loss of personal information (e.g. level of glucose, amount of glucose, and etc.)", 1);


INSERT INTO system_safety_constraints (id, name, project_id)
VALUES(1, "SSC-1: The pumping of insulin must be stopped when the glucose level goes below a configurable minimum level (for both Bolus and Basal)", 1);

INSERT INTO system_safety_constraints (id, name, project_id)
VALUES(2, "SSC-2: The system must automatically start pumping insulin after reaching some maximum configurable level", 1);

INSERT INTO system_safety_constraints (id, name, project_id)
VALUES(3, "SSC-3: The system must send an alert when detects a battery or reservoir level near the below level", 1);

INSERT INTO system_safety_constraints (id, name, project_id)
VALUES(4, "SSC-4: The system must never exposure patient data without the patient's consent", 1);

INSERT INTO system_safety_constraints (id, name, project_id)
VALUES(5, "SSC-5: Reservoir must be filled only with the recommended insulin", 1);

INSERT INTO system_safety_constraints (id, name, project_id)
VALUES(6, "SSC-6: Mobile device always must be paired with insulin pump", 1);


INSERT INTO hazards (id, name, father_id, project_id)
VALUES(1, "H-1: Pumping insulin when glucose level is going down – hypoglycemia", null, 1);

INSERT INTO hazards (id, name, father_id, project_id)
VALUES(2, "H-2: Not pumping insulin when glucose level is going up – hyperglycemia", null, 1);

INSERT INTO hazards (id, name, father_id, project_id)
VALUES(3, "H-3: System in operation with battery or reservoir level below recommended values", null, 1);

INSERT INTO hazards (id, name, father_id, project_id)
VALUES(4, "H-4: Disclosure sensitive information (exposure of patient data)", null, 1);

INSERT INTO hazards (id, name, father_id, project_id)
VALUES(5, "H-5: Reservoir filled with not recommended insulin or another product", null, 1);

INSERT INTO hazards (id, name, father_id, project_id)
VALUES(6, "H-6: Mobile device not paired with insulin pump", null, 1);


INSERT INTO hazard_loss (hazard_id, loss_id)
VALUES(1, 1);

INSERT INTO hazard_loss (hazard_id, loss_id)
VALUES(2, 1);

INSERT INTO hazard_loss (hazard_id, loss_id)
VALUES(3, 1);

INSERT INTO hazard_loss (hazard_id, loss_id)
VALUES(4, 2);

INSERT INTO hazard_loss (hazard_id, loss_id)
VALUES(4, 3);

INSERT INTO hazard_loss (hazard_id, loss_id)
VALUES(5, 1);

INSERT INTO hazard_loss (hazard_id, loss_id)
VALUES(6, 1);

INSERT INTO hazard_loss (hazard_id, loss_id)
VALUES(6, 2);

INSERT INTO system_safety_constraint_hazard (system_safety_constraint_id, hazard_id)
VALUES(1, 1);

INSERT INTO system_safety_constraint_hazard (system_safety_constraint_id, hazard_id)
VALUES(2, 2);

INSERT INTO system_safety_constraint_hazard (system_safety_constraint_id, hazard_id)
VALUES(3, 3);

INSERT INTO system_safety_constraint_hazard (system_safety_constraint_id, hazard_id)
VALUES(4, 4);

INSERT INTO system_safety_constraint_hazard (system_safety_constraint_id, hazard_id)
VALUES(5, 5);

INSERT INTO system_safety_constraint_hazard (system_safety_constraint_id, hazard_id)
VALUES(6, 6);