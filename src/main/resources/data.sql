-- Insert sample data into course table
INSERT INTO course (courseName, credits)
VALUES
('Introduction to Computer Science', 3),
('Data Structures and Algorithms', 4),
('Database Systems', 3),
('Web Development', 3),
('Operating Systems', 4);

-- Insert sample data into student table
INSERT INTO student (name, rollno, courseID)
VALUES
('Alice Green', 'R12345', 1),
('Bob White', 'R12346', 2),
('Charlie Black', 'R12347', 1),
('Diana Blue', 'R12348', 3);

-- Insert sample data into student_course table
INSERT INTO student_course (studentId, courseID)
VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(1, 3);

-- Insert sample data into teacher table
INSERT INTO teacher (teacherName, department)
VALUES
('Dr. Alice Green', 'Computer Science'),
('Prof. Bob White', 'Mathematics'),
('Dr. Charlie Black', 'Database Systems'),
('Dr. Diana Blue', 'Web Development'),
('Prof. Emily Brown', 'Operating Systems');

-- Insert sample data into course_teacher table
INSERT INTO course_teacher (courseId, teacherId)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);


